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
package org.surfnet.oaaas.consent;

import java.io.IOException;
import java.util.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpMethod;
import org.surfnet.oaaas.auth.AbstractAuthenticator;
import org.surfnet.oaaas.auth.AbstractUserConsentHandler;
import org.surfnet.oaaas.auth.principal.AuthenticatedPrincipal;
import org.surfnet.oaaas.model.*;
import org.surfnet.oaaas.repository.AccessTokenRepository;
import org.surfnet.oaaas.repository.AuthorizationRequestRepository;
import org.surfnet.oaaas.repository.ResourceOwnerRepository;

/**
 * Example {@link AbstractUserConsentHandler} that forwards to a form.
 * 
 */
@Named("formConsentHandler")
public class FormUserConsentHandler extends AbstractUserConsentHandler {

  private static final String USER_OAUTH_APPROVAL = "user_oauth_approval";

  @Inject
  private AccessTokenRepository accessTokenRepository;

  @Inject
  private AuthorizationRequestRepository authorizationRequestRepository;

  @Inject
  private ResourceOwnerRepository resourceOwnerRepository;

  @Override
  public void handleUserConsent(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      String authStateValue, String returnUri, Client client) throws IOException, ServletException {
    if (isUserConsentPost(request)) { //是否是用户同意表单post
      if (processForm(request, response)) {
        chain.doFilter(request, response);
      }
    } else {
      processInitial(request, response, chain, returnUri, authStateValue, client);
    }
  }

  private boolean isUserConsentPost(HttpServletRequest request) {
    String oauthApproval = request.getParameter(USER_OAUTH_APPROVAL);
    return request.getMethod().equals(HttpMethod.POST.toString()) && StringUtils.isNotBlank(oauthApproval);
  }

  private void processInitial(HttpServletRequest request, ServletResponse response, FilterChain chain,
      String returnUri, String authStateValue, Client client) throws IOException, ServletException {
    AuthenticatedPrincipal principal = (AuthenticatedPrincipal) request.getAttribute(AbstractAuthenticator.PRINCIPAL);
    ResourceOwner resourceOwner = (ResourceOwner)request.getSession().getAttribute(AbstractAuthenticator.RESOURCE_OWNER_KEY);
    List<AccessToken> tokens = accessTokenRepository.findByResourceOwnerAndClient(resourceOwner, client);
    if (!CollectionUtils.isEmpty(tokens)) {//TO后面再改
      // If another token is already present for this resource owner and client, no new consent should be requested
      List<String> grantedScopes = tokens.get(0).getScopes(); // take the scopes of the first access token found.
      setGrantedScopes(request, grantedScopes);
      chain.doFilter(request, response);
    } else {
        AuthorizationRequest authorizationRequest = authorizationRequestRepository.findByAuthState(authStateValue);
        List<String> requestScopes = authorizationRequest.getRequestedScopes();
        Set<ResourceOwnerToScope> resourceOwnerToScopes = resourceOwner.getResourceOwnerToScopes();
        removeUnrequestScopes(requestScopes,resourceOwnerToScopes);
        request.setAttribute("scopeList",resourceOwnerToScopes);
        request.setAttribute("client", client);
        request.setAttribute(AUTH_STATE, authStateValue);
        request.setAttribute("actionUri", returnUri);
        request.getRequestDispatcher(getUserConsentUrl()).forward(request, response);
    }
  }

    /**
     * 删除没有request scope
     * @param requestScopes
     * @param resourceOwnerToScopes
     */
    private void removeUnrequestScopes(List<String> requestScopes, Set<ResourceOwnerToScope> resourceOwnerToScopes) {
        Iterator<ResourceOwnerToScope> resourceOwnerToScopeIterator = resourceOwnerToScopes.iterator();
        while(resourceOwnerToScopeIterator.hasNext()){
            ResourceOwnerToScope resourceOwnerToScope = resourceOwnerToScopeIterator.next();
            if(resourceOwnerToScope.getResourceServerScope()!=null){
                if(!requestScopes.contains(resourceOwnerToScope.getResourceServerScope().getName())){
                    resourceOwnerToScopeIterator.remove();
                }
            }
        }
    }

    /**
   * 
   * Return the path to the User Consent page. Subclasses can use this hook by
   * providing a custom html/jsp.
   * 
   * @return the path to the User Consent page
   */
  protected String getUserConsentUrl() {
    return "/WEB-INF/jsp/userconsent.jsp";
  }

  private boolean processForm(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    if (Boolean.valueOf(request.getParameter(USER_OAUTH_APPROVAL))) {
      setAuthStateValue(request, request.getParameter(AUTH_STATE));
      String[] scopes = request.getParameterValues(GRANTED_SCOPES);
      List<String> scopeList = (scopes==null?(new ArrayList<String>()):Arrays.asList(scopes));
      setGrantedScopes(request,scopeList);
      return true;
    } else {
      request.getRequestDispatcher(getUserConsentDeniedUrl()).forward(request, response);
      return false;
    }
  }

  /**
   * @return
   */
  protected String getUserConsentDeniedUrl() {
    return "/WEB-INF/jsp/userconsent_denied.jsp";
  }
}
