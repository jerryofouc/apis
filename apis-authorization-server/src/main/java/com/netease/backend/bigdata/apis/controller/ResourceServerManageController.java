package com.netease.backend.bigdata.apis.controller;

import com.netease.backend.bigdata.apis.utils.ApisUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.surfnet.oaaas.model.ResourceServer;
import org.surfnet.oaaas.repository.ResourceOwnerRepository;
import org.surfnet.oaaas.repository.ResourceServerRepository;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 进行ResourceServer进行管理
 * User: zhangxiaojie
 * Date: 1/3/14
 * Time: 11:59
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "resourceServer")
public class ResourceServerManageController extends AbstractBaseController{
    @Inject
    private ResourceServerRepository resourceServerRepository;

    /**
     * Resource Server列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllResourceServers(Model model){
        List<ResourceServer> resourceServerList = (List<ResourceServer>)resourceServerRepository.findAll();
        model.addAttribute("resourceServerList",resourceServerList);
        return "resourceserver/resourceserver-list";
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public String toDetail(@PathVariable long id,RedirectAttributes redirectAttrs,Model model){
        ResourceServer resourceServer = resourceServerRepository.findOne(id);
        if(resourceServer == null){
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE,"该Resource Server不存在");
            return "redirect:/manage/resourceServer";
        }else{
            model.addAttribute("resourceServer",resourceServer);
            return "resourceserver/resourceserver-detail";
        }
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping(value = "create",method = RequestMethod.GET)
    public String goToCreate(Model model){
        model.addAttribute(PAGE_TYPE,"添加");
        model.addAttribute(FORM_ACTION,"create");
        return "resourceserver/resourceserver-add-edit";
    }

    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public String goToEdit(@PathVariable(value = "id") Long id,Model model,RedirectAttributes redirectAttrs){
        ResourceServer resourceServer = resourceServerRepository.findOne(id);
        if(resourceServer != null){
            model.addAttribute(PAGE_TYPE,"编辑");
            model.addAttribute(FORM_ACTION,"edit/" + id);
            model.addAttribute("resourceServer",resourceServer);
            return  "resourceserver/resourceserver-add-edit";
        }else{
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE,"该resource server不存在");
            return "redirect:/manage/resourceServer";
        }
    }
    @RequestMapping(value = "edit/{id}",method = RequestMethod.POST)
    public String edit(@PathVariable(value = "id") Long id,ResourceServer resourceServer,RedirectAttributes redirectAttrs){
        ResourceServer loadResourceServer = resourceServerRepository.findOne(id);
        setloadResourceServer(loadResourceServer,resourceServer);
        loadResourceServer = resourceServerRepository.save(loadResourceServer);
        redirectAttrs.addFlashAttribute(SUCCESS_MESSAGE,"保存成功");
        return "redirect:/manage/resourceServer";
    }

    /**
     * 保存编辑的信息
     * @param loadResourceServer
     * @param resourceServer
     */
    private void setloadResourceServer(ResourceServer loadResourceServer, ResourceServer resourceServer) {
        loadResourceServer.setName(resourceServer.getName());
        loadResourceServer.setServerURL(resourceServer.getServerURL());
        loadResourceServer.setContactName(resourceServer.getContactName());
        loadResourceServer.setContactEmail(resourceServer.getContactEmail());
        loadResourceServer.setDescription(resourceServer.getDescription());
        loadResourceServer.setThumbNailUrl(resourceServer.getThumbNailUrl());
    }

    /*
     *保存一个Resource Server
     */
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public String createResourceServer(ResourceServer resourceServer,RedirectAttributes redirectAttrs){
        resourceServer.setSecret(ApisUtils.generateRandomId());
        resourceServer.setKey(ApisUtils.generateRandomId());
        resourceServerRepository.save(resourceServer);
        redirectAttrs.addFlashAttribute(SUCCESS_MESSAGE,"保存成功");
        return "redirect:/manage/resourceServer";
    }

    /**
     * 删除一个Resource Server
     */
    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String deleteResourceServer(@PathVariable(value = "id") Long id,RedirectAttributes redirectAttrs){
        if(resourceServerRepository.findOne(id) != null){
            resourceServerRepository.delete(id);
            redirectAttrs.addFlashAttribute(SUCCESS_MESSAGE,"删除成功");
        }else{
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE,"该Resource Server不存在");
        }
        return "redirect:/manage/resourceServer";
    }
}
