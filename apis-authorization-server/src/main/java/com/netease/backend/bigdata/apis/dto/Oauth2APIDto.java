package com.netease.backend.bigdata.apis.dto;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *用于进行权限创建需要进行传递的对象
 * User: zhangxiaojie
 * Date: 1/14/14
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
public class Oauth2APIDto {
    private String resourceOwner;//corp账号前缀,服务器上如果不存在则进行新建
    private Map<String,List<String>> scopes;//需要传递scope与api的对应例如{dashboard:[http://server/project/1/awxa/,http://server/project/2/2]}
    private String resourceServer;//resourceServer

    public String getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(String resourceOwner) {
        this.resourceOwner = resourceOwner;
    }

    public Map<String, List<String>> getScopes() {
        return scopes;
    }

    public void setScopes(Map<String, List<String>> scopes) {
        this.scopes = scopes;
    }

    public String getResourceServer() {
        return resourceServer;
    }

    public void setResourceServer(String resourceServer) {
        this.resourceServer = resourceServer;
    }

    public static void main(String args[]){
        Oauth2APIDto oauth2APIDto = new Oauth2APIDto();
        oauth2APIDto.setResourceOwner("gufeiyong");
        oauth2APIDto.setResourceServer("9d2a09d3-088f-4372-bdc9-ae4c9ee2999c");
        Map<String,List<String>> map = Maps.newHashMap();
        List<String> apis = Lists.asList("http://kkk/sdfs/",new String[]{"http://xxxx/dsfs/"});
        Map<String,List<String>> maps = Maps.newHashMap();
        maps.put("report",apis) ;
        oauth2APIDto.setScopes(maps);
        System.out.println(oauth2APIDto);
    }
    @Override
    public String toString(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this) ;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

}
