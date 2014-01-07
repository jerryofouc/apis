<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Resource Owner${PAGE_TYPE}</title>
    <script src="${ctx}/static/lib/jquery.validate.js"></script>
    <link href="${ctx}/static/css/main.css" rel="stylesheet" >
</head>

<body>
<div class="row-fluid">
    <div class="navbar">
        <div class="navbar-inner">
            <ul class="breadcrumb">
                <i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
                <i class="icon-chevron-right show-sidebar" style="display:none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
                <li>
                    <a href="#">Resource Owner管理</a> <span class="divider">/</span>
                </li>
                <li class="active">${PAGE_TYPE}</li>
            </ul>
        </div>
    </div>
</div>

<div class="row-fluid">
    <!-- block -->
    <div class="block">
        <div class="navbar navbar-inner block-header">
            <div class="muted pull-left">Resource Owner${PAGE_TYPE}</div>
        </div>
        <div class="block-content collapse in">
            <div class="span12">
                <form class="form-horizontal" id="resourceowner-form" action="${ctx}/manage/resourceOwner/${FORM_ACTION}" method="post">
                    <input type="hidden" id="input-id" name="id" value="${resourceOwner.id}">
                    <fieldset>
                        <div class="control-group">
                            <label class="control-label">用户名</label>
                            <div class="controls">
                                <input class="input-xlarge focused" name="name" placeholder="用户名" value="${resourceOwner.name}"  type="text" >
                                <span class="help-inline">用户名</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">corp邮箱用户名</label>
                            <div class="controls">
                                <input class="input-xlarge focused" id="input-email" name="email" placeholder="corp邮箱用户名" value="${resourceOwner.email}"type="text"  >
                                <span class="input-group-addon">@corp.netease.com</span>
                            </div>
                            <div class="form-actions">
                                <button type="submit" class="btn btn-primary">Save changes</button>
                                <button type="reset" class="btn">Cancel</button>
                            </div>
                        </div>
                    </fieldset>
                </form>

            </div>
        </div>
    </div>
    <!-- /block -->
</div>
<script >
    var config = {
        uniqueURL : '${ctx}/manage/resourceOwner/isUnique'
    }
</script>
<script src="${ctx}/static/js/resourceowner.js"></script>
</body>
</html>
