package com.netease.backend.bigdata.apis.filter;

import com.netease.backend.bigdata.apis.utils.ApisContants;
import org.surfnet.oaaas.model.SystemAdminstrator;

import javax.inject.Named;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaojie
 * Date: 1/2/14
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
@Named
public class ManageLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        final SystemAdminstrator loginUser = (SystemAdminstrator)((HttpServletRequest) request).getSession().getAttribute(ApisContants.LOGIN_USER);
        if(loginUser == null && !isLoginURL(request.getRequestURL().toString())){ //如果没有登录就提示登录
            request.getRequestDispatcher(ApisContants.LOGIN_URL).forward(request, response);
        }else {
            chain.doFilter(request,response);
        }
    }

    private boolean isLoginURL(String requestURL) {
        return requestURL.contains(ApisContants.LOGIN_URL);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


}
