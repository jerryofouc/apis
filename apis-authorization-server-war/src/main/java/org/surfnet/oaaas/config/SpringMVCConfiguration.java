package org.surfnet.oaaas.config;

import com.netease.backend.bigdata.apis.controller.LoginController;
import com.netease.backend.bigdata.apis.filter.ManageLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.inject.Inject;

/**
 * SpringMVC的配置类
 * User: zhangxiaojie
 * Date: 1/2/14
 * Time: 18:01
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableWebMvc
public class SpringMVCConfiguration extends WebMvcConfigurerAdapter {
    @Inject
    private Environment env;

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/manage/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Bean
    public LoginController loginController(){
        return new LoginController(env);
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
