<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Open API列表</title>
</head>

<body>
<div class="row-fluid">
    <div class="navbar">
        <div class="navbar-inner">
            <ul class="breadcrumb">
                <i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
                <i class="icon-chevron-right show-sidebar" style="display:none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
                <li>
                    <a href="#">Open API管理</a> <span class="divider">/</span>
                </li>
                <li class="active">列表</li>
            </ul>
        </div>
    </div>
</div>

<div class="row-fluid">
    <!--增加和删除的message-->
    <c:if test="${not empty successMessage}">
    <div class="alert alert-success">
        <button type="button" class="close" data-dismiss="alert">×</button>
        <h4>${successMessage}</h4>
    </div>
    <script type="text/javascript">
        window.setTimeout(function() { $(".alert-success").alert('close'); }, 2000);
    </script>
    </c:if>
    <c:if test="${not empty failMessage}">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <h4>${failMessage}</h4>
        </div>
        <script type="text/javascript">
            window.setTimeout(function() { $(".alert-error").alert('close'); }, 2000);
        </script>
    </c:if>
    <!-- block -->
    <div class="block">
        <div class="navbar navbar-inner block-header">
            <div class="muted pull-left">Open API列表</div>
            <div class="muted pull-right"><a href="${ctx}/manage/api/create"><i class="icon-plus icon-blue   "></i>添加Open API</a></div>
        </div>
        <div class="block-content collapse in">
            <div class="span12">
                <table class="table">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>Resource Owner</th>
                        <th>Resource Server</th>
                        <th>scope</th>
                        <th>URL</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${accessRestApiList}" var="api" varStatus="status">
                        <tr>
                            <td>${status.index}</td>
                            <td>${api.resourceOwner.name}</td>
                            <td>${api.resourceServer.name}</td>
                            <td>${api.resourceOwnerToScope.resourceServerScope.name}</td>
                            <td>${api.completeUrl}</td>
                            <td>
                                <button class="btn btn-danger btn-mini" onclick="location.href='${ctx}/manage/api/delete/${api.id}'"><i class="icon-remove icon-white"></i> 删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- /block -->
</div>
<script src="${ctx}/static/js/api.js"></script>
</body>
</html>
