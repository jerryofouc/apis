<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Resource Server 详情</title>
</head>

<body>
<div class="row-fluid">
    <div class="navbar">
        <div class="navbar-inner">
            <ul class="breadcrumb">
                <i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
                <i class="icon-chevron-right show-sidebar" style="display:none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
                <li>
                    <a href="#">Resource Server管理</a> <span class="divider">/</span>
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
            <div class="muted pull-left">Resource Server详情</div>
        </div>
        <div class="block-content collapse in">
            <div class="span12">
                <table class="table">
                    <tbody>
                    <tr>
                        <td><strong>名称</strong></td>
                        <td>${resourceServer.name}</td>
                    </tr>
                    <tr>
                        <td><strong>服务器域名</strong></td>
                        <td>${resourceServer.serverURL}</td>
                    </tr>
                    <tr>
                        <td><strong>创建时间</strong></td>
                        <td><fmt:formatDate value="${resourceServer.creationDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                    </tr>
                    <tr>
                        <td><strong>修改时间</strong></td>
                        <td><fmt:formatDate value="${resourceServer.modificationDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                    </tr>
                    <tr>
                        <td><strong>联系人姓名</strong></td>
                        <td>${resourceServer.contactName}</td>
                    </tr>
                    <tr>
                        <td><strong>联系人Email</strong></td>
                        <td>${resourceServer.contactEmail}</td>
                    </tr>
                    <tr>
                        <td><strong>网站缩略图url</strong></td>
                        <td>${resourceServer.thumbNailUrl}</td>
                    </tr>
                    <tr>
                        <td><strong>描述</strong></td>
                        <td>${resourceServer.description}</td>
                    </tr><tr>
                        <td><strong>生成密钥（用于access token的解密）</strong></td>
                        <td>${resourceServer.secret}</td>
                    </tr>
                 </table>

            </div>
        </div>
    </div>
    <!-- /block -->
</div>
</body>
</html>
