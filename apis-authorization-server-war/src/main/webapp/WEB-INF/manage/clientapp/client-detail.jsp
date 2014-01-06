<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Client APP 详情</title>
</head>

<body>
<div class="row-fluid">
    <div class="navbar">
        <div class="navbar-inner">
            <ul class="breadcrumb">
                <i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
                <i class="icon-chevron-right show-sidebar" style="display:none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
                <li>
                    <a href="#">Client APP管理</a> <span class="divider">/</span>
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
                        <td><strong>名称</strong></td>
                        <td>${client.name}</td>
                    </tr>
                    <tr>
                        <td><strong>ClientID</strong></td>
                        <td>${client.clientId}</td>
                    </tr>
                    <tr>
                        <td><strong>Resource Server</strong></td>
                        <td>${client.resourceServer.name}</td>
                    </tr>
                    <tr>
                        <td><strong>创建时间</strong></td>
                        <td><fmt:formatDate value="${client.creationDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                    </tr>
                    <tr>
                        <td><strong>修改时间</strong></td>
                        <td><fmt:formatDate value="${client.modificationDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                    </tr>
                    <tr>
                        <td><strong>联系人姓名</strong></td>
                        <td>${client.contactName}</td>
                    </tr>
                    <tr>
                        <td><strong>联系人Email</strong></td>
                        <td>${client.contactEmail}</td>
                    </tr>
                    <tr>
                        <td><strong>过期时间</strong></td>
                        <td><c:out value="${client.expireDuration==0 ? '永久': client.expireDuration}"/>(单位s)</td>
                    </tr>
                    <tr>
                        <td><strong>redirect Uris</strong></td>
                        <td>
                            <c:forEach var="re" items="${client.redirectUris}" varStatus="status">
                                ${re} <c:out value="${status.last? '':','}"></c:out>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>是否允许Client Credentials</strong></td>
                        <td><c:out value="${client.allowedClientCredentials? '是':'否'}"></c:out></td>
                    </tr>
                    <tr>
                        <td><strong>是否允许implicit grant</strong></td>
                        <td><c:out value="${client.allowedImplicitGrant? '是':'否'}"></c:out></td>
                    </tr>
                    <tr>
                        <td><strong>是否允许使用refresh tokens</strong></td>
                        <td><c:out value="${client.useRefreshTokens? '是':'否'}"></c:out></td>
                    </tr>
                    <tr>
                        <td><strong>网站缩略图url</strong></td>
                        <td>${client.thumbNailUrl}</td>
                    </tr>
                    <tr>
                        <td><strong>描述</strong></td>
                        <td>${client.description}</td>
                    </tr>
                    <tr>
                        <td><strong>生成密钥（用于access token的解密）</strong></td>
                        <td>${client.secret}</td>
                    </tr>
                 </table>

            </div>
        </div>
    </div>
    <!-- /block -->
</div>
<script src="${ctx}/static/js/clientapp.js"></script>
</body>
</html>
