package com.netease.backend.bigdata.apis.controller;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.netease.backend.bigdata.apis.utils.ApisUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.surfnet.oaaas.model.Client;
import org.surfnet.oaaas.model.ResourceServer;
import org.surfnet.oaaas.repository.ClientRepository;
import org.surfnet.oaaas.repository.ResourceServerRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * 负责client app页面相关的controller
 * User: zhangxiaojie
 * Date: 1/5/14
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "clientapp")
public class ClientAppController extends AbstractBaseController{
    @Inject
    private ClientRepository clientRepository;
    @Inject
    private ResourceServerRepository resourceServerRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getClients(Model model){
        List<Client> clientList = (List<Client>)clientRepository.findAll();
        model.addAttribute("clientList",clientList);
        return "clientapp/client-list";
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping(value = "create",method = RequestMethod.GET)
    public String goToCreate(Model model){
        model.addAttribute(PAGE_TYPE,"添加");
        model.addAttribute(FORM_ACTION,"create");
        List<ResourceServer> resourceServers = Lists.newArrayList(resourceServerRepository.findAll());
        model.addAttribute("resourceServers",resourceServers);
        return "clientapp/client-add-edit";
    }



    /**
     * 判断id是否唯一
     * @param
     * @return
     */
    @RequestMapping(value = "isUnique",method = RequestMethod.GET)
    public @ResponseBody
    String isisUnique(@RequestParam(value = "clientId",required = true) String clientId,@RequestParam(value = "id",required = false) Long id){
        Client client = clientRepository.findByClientId(clientId);
        Gson gson = new Gson();
        if(client != null && !client.getId().equals(id)){
            return gson.toJson(Boolean.FALSE);
        }
        return gson.toJson(Boolean.TRUE);
    }


    /**
     * 创建新的client
     * @param client
     * @param redirectAttrs
     * @return
     */
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public String createClient(Client client,RedirectAttributes redirectAttrs){
        ResourceServer resourceServer = null;
        if(client.getResourceServerId() == null || (resourceServer=resourceServerRepository.findOne(client.getResourceServerId()))== null){
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE,"不存在的Resource server");
            return "redirect:/manage/clientapp";
        }
        client.setSecret(ApisUtils.generateRandomId());
        client.setResourceServer(resourceServer);
        clientRepository.save(client);
        redirectAttrs.addFlashAttribute(SUCCESS_MESSAGE,"保存成功");
        return "redirect:/manage/clientapp";
    }

    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public String goToEdit(@PathVariable(value = "id") Long id,Model model,RedirectAttributes redirectAttrs){
        Client client = clientRepository.findOne(id);
        if(client != null){
            List<ResourceServer> resourceServers = Lists.newArrayList(resourceServerRepository.findAll());
            model.addAttribute("resourceServers",resourceServers);
            model.addAttribute(PAGE_TYPE,"编辑");
            model.addAttribute(FORM_ACTION,"edit/" + id);
            model.addAttribute("client",client);
            return  "clientapp/client-add-edit";
        }else{
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE,"该client app不存在");
            return "redirect:/manage/clientapp";
        }
    }

    /**
     * 保存编辑结果
     * @param client
     * @param redirectAttrs
     * @return
     */
    @RequestMapping(value = "edit/{id}",method = RequestMethod.POST)
    public String editSave(Client client,RedirectAttributes redirectAttrs){
        Client oldClient = clientRepository.findOne(client.getId());
        ResourceServer resourceServer = null;
        if(client.getResourceServerId() == null || (resourceServer=resourceServerRepository.findOne(client.getResourceServerId()))== null){
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE,"不存在的Resource server");
            return "redirect:/manage/clientapp";
        }
        client.setResourceServer(resourceServer);
        updateOldClient(oldClient, client);
        clientRepository.save(oldClient);
        redirectAttrs.addFlashAttribute(SUCCESS_MESSAGE,"保存成功");
        return "redirect:/manage/clientapp";

    }

    private void updateOldClient(Client oldClient, Client newClient) {
        oldClient.setResourceServer(newClient.getResourceServer());
        oldClient.setClientId(newClient.getClientId());
        oldClient.setThumbNailUrl(newClient.getThumbNailUrl());
        oldClient.setContactName(newClient.getContactName());
        oldClient.setDescription(newClient.getDescription());
        oldClient.setAllowedClientCredentials(newClient.isAllowedClientCredentials());
        oldClient.setAllowedImplicitGrant(newClient.isAllowedImplicitGrant());
        oldClient.setExpireDuration(newClient.getExpireDuration());
        oldClient.setContactEmail(newClient.getContactEmail());
        oldClient.setUseRefreshTokens(newClient.isUseRefreshTokens());
        oldClient.setIncludePrincipal(newClient.isIncludePrincipal());
        oldClient.setRedirectUris(newClient.getRedirectUris());
        oldClient.setSkipConsent(newClient.isSkipConsent());
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public String toDetail(@PathVariable long id,RedirectAttributes redirectAttrs,Model model){
        Client client = clientRepository.findOne(id);
        if(client == null){
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE, "该Client APP不存在");
            return "redirect:/manage/resourceServer";
        }else{
            model.addAttribute("client", client);
            return "clientapp/client-detail";
        }
    }

    /**
     * 删除一个Client app
     */
    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String deleteResourceServer(@PathVariable(value = "id") Long id,RedirectAttributes redirectAttrs){
        if(clientRepository.findOne(id) != null){
            clientRepository.delete(id);
            redirectAttrs.addFlashAttribute(SUCCESS_MESSAGE,"删除成功");
        }else{
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE,"该Client APP不存在");
        }
        return "redirect:/manage/clientapp";
    }

}
