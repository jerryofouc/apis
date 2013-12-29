/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.surfnet.oaaas.authentication;

import java.io.IOException;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.surfnet.oaaas.auth.AbstractAuthenticator;
import org.surfnet.oaaas.auth.principal.AuthenticatedPrincipal;
import org.surfnet.oaaas.model.AccessRestApi;
import org.surfnet.oaaas.model.ResourceOwner;
import org.surfnet.oaaas.repository.ResourceOwnerRepository;

/**
 * {@link AbstractAuthenticator} that redirects to a form. Note that other
 * implementations can go wild because they have access to the
 * 这个地方简单实现一个formLoginAuthenticator认证
 * {@link HttpServletRequest} and {@link HttpServletResponse}.
 * 
 */
@Named("formAuthenticator")
public class FormLoginAuthenticator extends AbstractAuthenticator {

  @Inject
  private ResourceOwnerRepository resourceOwnerRepository;
  private static final String SESSION_IDENTIFIER = "AUTHENTICATED_PRINCIPAL";

  @Override
  public boolean canCommence(HttpServletRequest request) {
    return request.getMethod().equals("POST") && request.getParameter(AUTH_STATE) != null
        && request.getParameter("j_username") != null;
  }

  @Override
  public void authenticate(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      String authStateValue, String returnUri) throws IOException, ServletException {
    HttpSession session = request.getSession(false);
    AuthenticatedPrincipal principal = (AuthenticatedPrincipal) (session != null ? session
        .getAttribute(SESSION_IDENTIFIER) : null);
    if (request.getMethod().equals("POST")) {
      if(hasPermission(request,response)){
          processForm(request);
          chain.doFilter(request, response);
      }
    } else if (principal != null) {
      // we stil have the session
      setAuthStateValue(request, authStateValue);
      setPrincipal(request, principal);
      chain.doFilter(request, response);
    } else {
      processInitial(request, response, returnUri, authStateValue);
    }
  }

    /**
     * 验证该用户是否有权限，没有权限则提醒用户没有权限
     * @param request
     * @param response
     * @return
     */
    private boolean hasPermission(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName =  request.getParameter("j_username");
        if(StringUtils.isNotEmpty(userName)){//名字为空
            ResourceOwner resourceOwner = resourceOwnerRepository.findByName(userName);
            if(resourceOwner == null){
                request.getRequestDispatcher("/WEB-INF/error/403.jsp").forward(request,response);
                return false;
            }
            Set<AccessRestApi> accessRestApiSet =  resourceOwner.getAccessRestApiSet();
            if(accessRestApiSet == null || accessRestApiSet.isEmpty()){//访问api为空
                request.getRequestDispatcher("/WEB-INF/error/403.jsp").forward(request,response);
                return false;
            }
            request.getSession().setAttribute("RESOURCE_OWNER_KEY",resourceOwner);
        }else{
            request.getRequestDispatcher("/WEB-INF/error/403.jsp").forward(request,response);
            return false;
        }

        return true;
    }

    private void processInitial(HttpServletRequest request, ServletResponse response, String returnUri,
      String authStateValue) throws IOException, ServletException {
    request.setAttribute(AUTH_STATE, authStateValue);
    request.setAttribute("actionUri", returnUri);
    request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
  }

  /**
   * 
   * Hook for actually validating the username/ password against a database,
   * ldap, external webservice or whatever to perform authentication
   * 
   * @param request
   *          the {@link HttpServletRequest}
   */
  protected void processForm(final HttpServletRequest request) throws ServletException, IOException {
      String userName =  request.getParameter("j_username");
      setAuthStateValue(request, request.getParameter(AUTH_STATE));
      AuthenticatedPrincipal principal = new AuthenticatedPrincipal(userName);
      request.getSession().setAttribute(SESSION_IDENTIFIER, principal);
      //TODO 这里是一个假登录，正式实现时会使用openid进行登录，返回用户登录成功的id，如果登录成功，
      // 应该检查权限，如果用户没有任何api的访问权限，就应该申请api的访问权限
      //如果有则应该引导用户进入统一界面。
      setPrincipal(request, principal);
  }
}
