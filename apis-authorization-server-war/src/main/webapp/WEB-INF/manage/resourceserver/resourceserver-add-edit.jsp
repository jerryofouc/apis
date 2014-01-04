<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Resource Server${PAGE_TYPE}</title>
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
                <li class="active">${PAGE_TYPE}</li>
            </ul>
        </div>
    </div>
</div>

<div class="row-fluid">
    <!-- block -->
    <div class="block">
        <div class="navbar navbar-inner block-header">
            <div class="muted pull-left">Resource Server${PAGE_TYPE}</div>
        </div>
        <div class="block-content collapse in">
            <div class="span12">
                <form class="form-horizontal" action="${ctx}/manage/resourceServer/${FORM_ACTION}" method="post">
                    <fieldset>
                        <div class="control-group">
                            <label class="control-label">名称</label>
                            <div class="controls">
                                <input class="input-xlarge focused" name="name" placeholder="名称" value="${resourceServer.name}" required type="text" >
                                <span class="help-inline">名称</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">服务器域名</label>
                            <div class="controls">
                                <input class="input-xlarge focused" name="serverURL" value="${resourceServer.serverURL}" placeholder="http://johndoe.com" required pattern="(http|https)://.+"  type="text" >
                                <span class="help-inline">请输入http://johndoe.com格式的域名</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">联系人姓名</label>
                            <div class="controls">
                                <input class="input-xlarge focused" placeholder="联系人姓名"  name="contactName" value="${resourceServer.contactName}"  type="text" >
                                <span class="help-inline">负责该服务器的联系人</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">联系人Email</label>
                            <div class="controls">
                                <input class="input-xlarge focused" name="contactEmail" placeholder="联系人Email" value="${resourceServer.contactEmail}"  type="email" >
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">网站缩略图url</label>
                            <div class="controls">
                                <input class="input-xlarge focused" name="thumbNailUrl" placeholder="网站缩略图url" value="${resourceServer.thumbNailUrl}" pattern="(http|https)://.+"  type="text" >
                                <span class="help-inline">缩略图将会显示在系统中</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">描述</label>
                            <div class="controls">
                                <textarea name="description" class="input-xlarge focused" >${resourceServer.description}</textarea>
                            </div>
                        </div>
                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">Save changes</button>
                            <button type="reset" class="btn">Cancel</button>
                        </div>
                    </fieldset>
                </form>

            </div>
        </div>
    </div>
    <!-- /block -->
</div>
</body>
</html>
