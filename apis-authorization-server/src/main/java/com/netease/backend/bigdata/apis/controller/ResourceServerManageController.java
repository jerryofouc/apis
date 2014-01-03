package com.netease.backend.bigdata.apis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.surfnet.oaaas.model.ResourceServer;
import org.surfnet.oaaas.repository.ResourceOwnerRepository;
import org.surfnet.oaaas.repository.ResourceServerRepository;

import javax.inject.Inject;
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
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String getAllResourceServers(Model model){
        List<ResourceServer> resourceServerList = (List<ResourceServer>)resourceServerRepository.findAll();
        model.addAttribute("resourceServerList",resourceServerList);
        return "resourceserver/resourceserver-list";
    }
}
