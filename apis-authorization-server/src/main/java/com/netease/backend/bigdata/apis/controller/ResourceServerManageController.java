package com.netease.backend.bigdata.apis.controller;

import com.netease.backend.bigdata.apis.utils.ApisUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.surfnet.oaaas.model.ResourceServer;
import org.surfnet.oaaas.repository.ResourceOwnerRepository;
import org.surfnet.oaaas.repository.ResourceServerRepository;

import javax.inject.Inject;
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
public class ResourceServerManageController {
    @Inject
    private ResourceServerRepository resourceServerRepository;

    /**
     * Resource Server列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String getAllResourceServers(Model model){
        List<ResourceServer> resourceServerList = (List<ResourceServer>)resourceServerRepository.findAll();
        model.addAttribute("resourceServerList",resourceServerList);
        return "resourceserver/resourceserver-list";
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping(value = "create",method = RequestMethod.GET)
    public String goToCreate(){
        return "resourceserver/resourceserver-add";
    }

    /*
     *保存一个Resource Server
     */
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public String createResourceServer(ResourceServer resourceServer){
        resourceServer.setSecret(ApisUtils.generateRandomId());
        resourceServer.setKey(ApisUtils.generateRandomId());
        resourceServerRepository.save(resourceServer);
        return "redirect:/manage/resourceServer";
    }
}
