<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>AccessToken 详情</title>
</head>

<body>
<div class="row-fluid">
    <div class="navbar">
        <div class="navbar-inner">
            <ul class="breadcrumb">
                <i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
                <i class="icon-chevron-right show-sidebar" style="display:none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
                <li>
                    <a href="#">AccessToken管理</a> <span class="divider">/</span>
                </li>
                <li class="active">详情</li>
            </ul>
        </div>
    </div>
</div>

<div class="row-fluid">
    <!-- block -->
    <div class="block">
        <div class="navbar navbar-inner block-header">
            <div class="muted pull-left">Client APP详情</div>
        </div>
        <div class="block-content collapse in">
            <div class="span12">
                <table class="table">
                    <tbody>
                    <tr>
                        <td><strong>token</strong></td>
                        <td>${accessToken.token}</td>
                    </tr>
                    <tr>
                        <td><strong>refreshToken</strong></td>
                        <td>${accessToken.refreshToken}</td>
                    </tr>
                    <tr>
                        <td><strong>principle</strong></td>
                        <td>${accessToken.principal.name}</td>
                    </tr>
                    <tr>
                        <td><strong>client</strong></td>
                        <td>${accessToken.client.clientId}</td>
                    </tr>
                    <tr>
                        <td><strong>resourceOwner</strong></td>
                        <td>${accessToken.resourceOwner.name}</td>
                    </tr>

                    <tr>
                        <td><strong>创建时间</strong></td>
                        <td><fmt:formatDate value="${accessToken.creationDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                    </tr>
                    <tr>
                        <td><strong>修改时间</strong></td>
                        <td><fmt:formatDate value="${accessToken.modificationDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                    </tr>
                    <tr>
                        <td><strong>scope以及api</strong></td>
                        <td><table class="table">
                            <thead>
                            <tr>
                                <th>scope</th>
                                <th>open api</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="scope" items="${accessToken.resourceOwnerScopeToAccessTokens}" varStatus="status">
                                  <tr>
                                      <td>${scope.resourceOwnerToScope.resourceServerScope.name}</td>
                                      <td>
                                          <c:forEach items="${scope.resourceOwnerToScope.accessRestApis}" var="api" varStatus="status">
                                              ${api.completeUrl}
                                              <c:out value="${status.last?' ':','}"></c:out>
                                          </c:forEach>
                                      </td>
                                  </tr>
                            </c:forEach>
                            </tbody>
                            </table>
                         </td>
                    </tr>
                    <tr>
                        <td><strong>过期时间</strong></td>
                        <td><c:out value="${accessToken.expires==0 ? '永久': accessToken.expires}"/>(单位s)</td>
                    </tr>
                 </table>
            </div>
        </div>
    </div>
    <!-- /block -->
</div>
<script src="${ctx}/static/js/accesstoken.js"></script>
</body>
</html>
