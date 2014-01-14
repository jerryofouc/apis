package com.netease.backend.bigdata.apis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.surfnet.oaaas.model.AccessToken;
import org.surfnet.oaaas.model.Client;
import org.surfnet.oaaas.model.ResourceServer;
import org.surfnet.oaaas.repository.AccessTokenRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * AccessToken 相关的controller
 * User: zhangxiaojie
 * Date: 1/13/14
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping(value = "accessToken")
public class AccessTokenController extends  AbstractBaseController{
    @Inject
    private AccessTokenRepository accessTokenRepository;
    /**
     * AccessToken列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllAccessTokens(Model model){
        List<AccessToken> accessTokenList = (List<AccessToken>)accessTokenRepository.findAll();
        model.addAttribute("accessTokenList",accessTokenList);
        return "accesstoken/accesstoken-list";
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public String toDetail(@PathVariable long id,RedirectAttributes redirectAttrs,Model model){
        AccessToken accessToken = accessTokenRepository.findOne(id);
        if(accessToken == null){
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE, "该AccessToken不存在");
            return "redirect:/manage/accessToken";
        }else{
            model.addAttribute("accessToken", accessToken);
            return "accesstoken/accesstoken-detail";
        }
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Long id,RedirectAttributes redirectAttrs){
        if(accessTokenRepository.findOne(id) != null){
            accessTokenRepository.delete(id);
            redirectAttrs.addFlashAttribute(SUCCESS_MESSAGE,"删除成功");
        }else{
            redirectAttrs.addFlashAttribute(FAIL_MESSAGE,"该AccessToken不存在");
        }
        return "redirect:/manage/accessToken";
    }
}
