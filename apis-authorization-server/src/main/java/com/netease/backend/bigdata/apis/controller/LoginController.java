package com.netease.backend.bigdata.apis.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 和login相关的controller
 * User: zhangxiaojie
 * Date: 1/2/14
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "login")
public class LoginController {
    private Environment env;
    public LoginController(Environment env) {
        this.env = env;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String check(){
        return "redirect:/manage/index";
    }
}
