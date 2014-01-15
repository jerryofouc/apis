package org.surfnet.oaaas.resource;

import com.google.common.collect.Maps;
import com.netease.backend.bigdata.apis.dto.Oauth2APIDto;
import com.sun.jersey.api.client.ClientResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.surfnet.oaaas.model.*;
import org.surfnet.oaaas.repository.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaojie
 * Date: 1/14/14
 * Time: 15:38
 * To change this template use File | Settings | File Templates.
 */
/**
 *将服务开放出去供别的系统进行调用
 */
@Named
@Path("/openapi")
public class OpenAPIResource {
    private static final String CORP_MAIL_SUFFIX = "@corp.netease.com";

    @Inject
    private AccessRestApiRepository accessRestApiRepository;
    @Inject
    private AccessTokenRepository accessTokenRepository;
    @Inject
    private ResourceOwnerRepository resourceOwnerRepository;
    @Inject
    private ResourceServerRepository resourceServerRepository;
    @Inject
    private ResourceOwnerToScopeRepository resourceOwnerToScopeRepository;


    /**
     * 提供外部注册api接口
     * @param oauth2APIDto
     * @return
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOauthAPI(Oauth2APIDto oauth2APIDto){
        String resourceServerId = oauth2APIDto.getResourceServer();
        ResourceServer resourceServer = null;
        if(StringUtils.isEmpty(resourceServerId) || (resourceServer=resourceServerRepository.findByKey(resourceServerId)) ==null ){
             return sendErrorResponse("error invalid resourceServerId","resourceServer不存在");
        }
        String resourceOwnerName = oauth2APIDto.getResourceOwner();

        ResourceOwner resourceOwner = null;
        if(StringUtils.isEmpty(resourceOwnerName)){
            return sendErrorResponse("error invalid resourceOwner","resourceOwner不能为空");
        }

        Map<String,List<String>> scopes = oauth2APIDto.getScopes();
        if(scopes == null){
            return sendErrorResponse("error invalid scope","scope不能为空");
        }
        Map<ResourceServerScope,List<String>> resourceServerScopeListHashMap = Maps.newHashMap();
        for(String scopeKey : scopes.keySet()){
            ResourceServerScope resourceServerScope = getResourceServerScope(resourceServer.getResourceServerScopes(), scopeKey);
            if(resourceServerScope == null){
                return sendErrorResponse("error invalid scope","resource Server不存在该scope");
            }
            List<String> apis = scopes.get(scopeKey);
            if(apis != null){
                for(String api:apis){
                    if(!validateHTTP_URI(api)){
                        return sendErrorResponse("error api url 格式不正确","api url格式不正确");
                    }
                }
            }
            resourceServerScopeListHashMap.put(resourceServerScope,apis);
        }
        //所有validation都通过
        resourceOwner = resourceOwnerRepository.findByName(resourceOwnerName);
        if(resourceOwner == null){ //没有则先进行创建
            resourceOwner = new ResourceOwner();
            resourceOwner.setName(resourceOwnerName);
            resourceOwner.setEmail(resourceOwnerName + CORP_MAIL_SUFFIX);
            resourceOwnerRepository.save(resourceOwner);
        }

        Set<ResourceOwnerToScope> oldResourceOwnerToScopes = resourceOwner.getResourceOwnerToScopes();
        //如果又申请一次，则先删除再进行更新
        if(oldResourceOwnerToScopes != null ){
            for(ResourceOwnerToScope resourceOwnerToScope : oldResourceOwnerToScopes){
                for(AccessRestApi accessRestApi:resourceOwnerToScope.getAccessRestApis()){
                    accessRestApiRepository.delete(accessRestApi);
                }
                resourceOwnerToScope.setAccessRestApis(null);//这一步必须这么删，要不然会报异常
            }
        }

        if(oldResourceOwnerToScopes != null ){
            for(ResourceOwnerToScope resourceOwnerToScope : oldResourceOwnerToScopes){
                resourceOwnerToScopeRepository.delete(resourceOwnerToScope.getId());
            }
        }



        /**
         * 保存api和scope的对应关系
         */
        for(ResourceServerScope resourceServerScope : resourceServerScopeListHashMap.keySet()){
            ResourceOwnerToScope resourceOwnerToScope = new ResourceOwnerToScope();
            resourceOwnerToScope.setResourceOwner(resourceOwner);
            resourceOwnerToScope.setResourceServerScope(resourceServerScope);
            resourceOwnerToScope = resourceOwnerToScopeRepository.save(resourceOwnerToScope);
            for(String api : resourceServerScopeListHashMap.get(resourceServerScope)){
                AccessRestApi accessRestApi = new AccessRestApi();
                accessRestApi.setResourceOwnerToScope(resourceOwnerToScope);
                accessRestApi.setResourceOwner(resourceOwner);
                accessRestApi.setResourceServer(resourceServer);
                accessRestApi.setCompleteUrl(api);
                accessRestApiRepository.save(accessRestApi);
            }
        }


        return Response.status(Response.Status.CREATED).build();
    }

    public static boolean validateHTTP_URI(String uri) {
        final URL url;
        try {
            url = new URL(uri);
        } catch (Exception e1) {
            return false;
        }
        return "http".equals(url.getProtocol());
    }

    /**
     * 验证是否存在
     * @param resourceServerScopes
     * @param scopeKey
     * @return
     */
    private ResourceServerScope getResourceServerScope(Set<ResourceServerScope> resourceServerScopes, String scopeKey) {
        if(StringUtils.isEmpty(scopeKey)){
            return null;
        }
        for(ResourceServerScope resourceServerScope : resourceServerScopes){
            if(scopeKey.equals(resourceServerScope.getName())){
                return resourceServerScope;
            }
        }
        return null;
    }


    private Response sendErrorResponse(String error, String description) {
        return Response.status(ClientResponse.Status.BAD_REQUEST).entity(new ErrorResponse(error, description)).build();
    }

}
