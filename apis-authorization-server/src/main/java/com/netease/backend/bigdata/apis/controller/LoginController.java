package com.netease.backend.bigdata.apis.controller;

import com.netease.backend.bigdata.apis.utils.ApisContants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.surfnet.oaaas.model.SystemAdminstrator;
import org.surfnet.oaaas.repository.SystemAdminRepository;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 和login相关的controller
 * User: zhangxiaojie
 * Date: 1/2/14
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping()
public class LoginController {

    @Inject
    private SystemAdminRepository systemAdminRepository;
    private Environment env;
    public LoginController(Environment env) {
        this.env = env;
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String check(HttpSession session,@RequestParam String userName,@RequestParam String password){
        SystemAdminstrator systemAdminstrator = systemAdminRepository.findByName(userName);
        if(systemAdminstrator == null){
            return "error/403";
        }
        session.setAttribute(ApisContants.LOGIN_USER,systemAdminstrator);//set session
        return "redirect:/manage/index";
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        if(session.getAttribute(ApisContants.LOGIN_USER) != null){
            session.removeAttribute(ApisContants.LOGIN_USER);
        }
        return "login";
    }
}
