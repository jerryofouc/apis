package com.netease.backend.bigdata.apis.controller;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.netease.backend.bigdata.apis.utils.ApisContants;
import com.netease.backend.bigdata.apis.utils.ApisUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.surfnet.oaaas.model.Client;
import org.surfnet.oaaas.model.ResourceOwner;
import org.surfnet.oaaas.model.ResourceServer;
import org.surfnet.oaaas.repository.ResourceOwnerRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * 负责Resource Owner的controller
 * User: zhangxiaojie
 * Date: 1/7/14
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "resourceOwner")
public class ResourceOwnerController extends AbstractBaseController{
    @Inject
    private ResourceOwnerRepository resourceOwnerRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model){
        List<ResourceOwner> resourceOwnerList = Lists.newArrayList(resourceOwnerRepository.findAll());
        model.addAttribute("resourceOwnerList",resourceOwnerList);
        return "resourceowner/resourceowner-list";
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping(value = "create",method = RequestMethod.GET)
    public String goToCreate(Model model){
        model.addAttribute(PAGE_TYPE,"添加");
        model.addAttribute(FORM_ACTION,"create");
        return "resourceowner/resourceowner-add-edit";
    }

    /**
     * 判断id是否唯一
     * @param emailUserName   不包含邮箱后缀的用户名
     * @param id              用户id
     * @return
     */
    @RequestMapping(value = "isUnique",method = RequestMethod.GET)
    public @ResponseBody
    String isUnique(@RequestParam(value = "emailUserName",required = true) String emailUserName,@RequestParam(value = "id",required = false) Long id){
        final String email = emailUserName + ApisContants.CORP_EMAIL;
        ResourceOwner resourceOwner = resourceOwnerRepository.findByEmail(email);
        Gson gson = new Gson();
        if(resourceOwner != null && !resourceOwner.getId().equals(id)){
            return gson.toJson(Boolean.FALSE);
        }
        return gson.toJson(Boolean.TRUE);
    }

    /**
     * 创建新的ResourceOwner
     * @param resourceOwner
     * @param redirectAttrs
     * @return
     */
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public String createResourceOwner(@Valid ResourceOwner resourceOwner,RedirectAttributes redirectAttrs){
        resourceOwner.setEmail(getCorpMailSuffix(resourceOwner.getEmail()));
        if(resourceOwnerRepository.findByEmail(resourceOwner.getEmail()) != null){
            throw new IllegalArgumentException("不允许同名邮箱账号存在");
        }
        resourceOwnerRepository.save(resourceOwner);
        redirectAttrs.addAttribute(SUCCESS_MESSAGE,"保存成功");
        return "redirect:/manage/resourceOwner";
    }

    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public String goToEdit(@PathVariable(value = "id") Long id,Model model,RedirectAttributes redirectAttrs){
        ResourceOwner resourceOwner = resourceOwnerRepository.findOne(id);
        final String emailUserName = resourceOwner.getEmail().substring(0,resourceOwner.getEmail().indexOf(ApisContants.CORP_EMAIL));
        resourceOwner.setEmail(emailUserName);
        if(resourceOwner != null){
            model.addAttribute("resourceOwner",resourceOwner);
            model.addAttribute(PAGE_TYPE,"编辑");
            model.addAttribute(FORM_ACTION,"edit/" + id);
            return "resourceowner/resourceowner-add-edit";
        }else{
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE,"该Resource Owner不存在");
            return "redirect:/manage/resourceOwner";
        }
    }

    /**
     * 保存编辑结果
     * @param resourceOwner
     * @param redirectAttrs
     * @return
     */
    @RequestMapping(value = "edit/{id}",method = RequestMethod.POST)
    public String editSave(ResourceOwner resourceOwner,RedirectAttributes redirectAttrs){
        ResourceOwner oldResourceOwner = resourceOwnerRepository.findOne(resourceOwner.getId());
        if(oldResourceOwner == null){
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE,"不存在的Resource owner");
            return "redirect:/manage/clientapp";
        }
        resourceOwner.setEmail(getCorpMailSuffix(resourceOwner.getEmail()));
        //update old ResourceOwner
        oldResourceOwner.setEmail(resourceOwner.getEmail());
        oldResourceOwner.setName(resourceOwner.getName());
        resourceOwnerRepository.save(oldResourceOwner);
        redirectAttrs.addFlashAttribute(SUCCESS_MESSAGE,"保存成功");
        return "redirect:/manage/resourceOwner";
    }

    /**
     * 删除一个Client app
     */
    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Long id,RedirectAttributes redirectAttrs){
        if(resourceOwnerRepository.findOne(id) != null){
            resourceOwnerRepository.delete(id);
            redirectAttrs.addFlashAttribute(SUCCESS_MESSAGE,"删除成功");
        }else{
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE,"该Client APP不存在");
        }
        return "redirect:/manage/resourceOwner";
    }


    private String getCorpMailSuffix(String mailUserName){
        return mailUserName + ApisContants.CORP_EMAIL;
    }
}
