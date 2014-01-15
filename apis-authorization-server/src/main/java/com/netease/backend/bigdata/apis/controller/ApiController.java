package com.netease.backend.bigdata.apis.controller;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.surfnet.oaaas.model.AccessRestApi;
import org.surfnet.oaaas.model.ResourceOwner;
import org.surfnet.oaaas.model.ResourceOwnerToScope;
import org.surfnet.oaaas.model.ResourceServerScope;
import org.surfnet.oaaas.repository.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Open API管理相关的Controller
 * User: zhangxiaojie
 * Date: 1/8/14
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "api")
public class ApiController extends AbstractBaseController{
    @Inject
    private AccessRestApiRepository accessRestApiRepository;

    @Inject
    private ResourceServerRepository resourceServerRepository;

    @Inject
    private ResourceServerScopeRepository resourceServerScopeRepository;

    @Inject
    private ResourceOwnerRepository resourceOwnerRepository;

    @Inject
    private ResourceOwnerToScopeRepository resourceOwnerToScopeRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model){
        List<ResourceOwnerToScope> resourceOwnerToScopes = Lists.newArrayList(resourceOwnerToScopeRepository.findAll());
        model.addAttribute("resourceOwnerToScopes",resourceOwnerToScopes);
        return "openapi/api-list";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String goToCreate(Model model){
        model.addAttribute(PAGE_TYPE,"添加");
        model.addAttribute(FORM_ACTION,"create");
        model.addAttribute("allResourceServers",resourceServerRepository.findAll());
        List<ResourceOwner> resourceOwners = Lists.newArrayList(resourceOwnerRepository.findAll());
        model.addAttribute("resourceOwners",resourceOwners);
        return "openapi/api-add-edit";
    }

    /**
     * 保存创建的api
     * @param accessRestApi
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String saveCreated(AccessRestApi accessRestApi,RedirectAttributes redirectAttrs){
        Long resourceOwnerId = accessRestApi.getResourceOwnerId();
        ResourceOwner resourceOwner = null;
        //判断ResourceOwner是否存在
        if(resourceOwnerId == null || (resourceOwner = resourceOwnerRepository.findOne(resourceOwnerId) )== null){
            throw new IllegalArgumentException("resourceOwner 不存在");
        }

        ResourceServerScope resourceServerScope = null;
        Long resourceServerScopeId = accessRestApi.getScopeId();
        if(resourceServerScopeId == null || (resourceServerScope = resourceServerScopeRepository.findOne(resourceServerScopeId))==null){
            throw new IllegalArgumentException("该scope不存在");
        }

        ResourceOwnerToScope resourceOwnerToScope = null;
        List<ResourceOwnerToScope> resourceOwnerToScopes = resourceOwnerToScopeRepository.findByResourceOwnerIdAndResourceServerScopeId(resourceOwnerId,resourceServerScopeId);
        if(resourceOwnerToScopes !=null && resourceOwnerToScopes.size() > 1){
            throw new IllegalArgumentException("不正常的resourceOwnerToScope");
        }
        if(resourceOwnerToScopes == null || resourceOwnerToScopes.isEmpty()){
            ResourceOwnerToScope temp = new ResourceOwnerToScope();
            temp.setResourceServerScope(resourceServerScope);
            temp.setResourceOwner(resourceOwner);
            resourceOwnerToScope = resourceOwnerToScopeRepository.save(temp);
        }else{
            resourceOwnerToScope = resourceOwnerToScopes.get(0);
        }

        accessRestApi.setResourceOwner(resourceOwner);
        accessRestApi.setResourceServer(resourceServerScope.getResourceServer());
        accessRestApi.setResourceOwnerToScope(resourceOwnerToScope);
        accessRestApiRepository.save(accessRestApi);
        redirectAttrs.addAttribute(SUCCESS_MESSAGE,"保存成功");
        return "redirect:/manage/api";
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String deleteApi(@PathVariable(value = "id") Long id,RedirectAttributes redirectAttrs){
        if(accessRestApiRepository.findOne(id) != null){
            accessRestApiRepository.delete(id);
            redirectAttrs.addFlashAttribute(SUCCESS_MESSAGE,"删除成功");
        }else{
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE,"该Client APP不存在");
        }
        return "redirect:/manage/api";
    }





}
