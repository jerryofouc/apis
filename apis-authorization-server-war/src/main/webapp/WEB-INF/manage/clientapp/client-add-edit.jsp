<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Client APP${PAGE_TYPE}</title>
    <link href="${ctx}/static/lib/bootstrap-select.css" rel="stylesheet" media="screen">
    <script src="${ctx}/static/lib/bootstrap-select.js"></script>
    <link href="${ctx}/static/lib/bootstrap-checkbox.css" rel="stylesheet" media="screen">
    <link href="${ctx}/static/css/clientapp.css" rel="stylesheet" >
    <script src="${ctx}/static/lib/bootstrap-checkbox.js"></script>
    <script src="${ctx}/static/lib/jquery.validate.js"></script>
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
                <li class="active">${PAGE_TYPE}</li>
            </ul>
        </div>
    </div>
</div>

<div class="row-fluid">
    <!-- block -->
    <div class="block">
        <div class="navbar navbar-inner block-header">
            <div class="muted pull-left">Client APP${PAGE_TYPE}</div>
        </div>
        <div class="block-content collapse in">
            <div class="span12">
                <form class="form-horizontal" action="${ctx}/manage/clientapp/${FORM_ACTION}" id="client-form" method="post">
                    <input type="hidden" id="input-id" name="id" value="${client.id}">
                    <fieldset>
                        <div class="control-group">
                            <label class="control-label">名称</label>
                            <div class="controls">
                                <input class="input-xlarge focused" name="name" placeholder="名称" value="${client.name}"  type="text" >
                                <span class="help-inline">名称</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">clientId</label>
                            <div class="controls">
                                <input class="input-xlarge focused" id="clientId-input" name="clientId" placeholder="clientId" value="${client.clientId}"   type="text"><span id="clientId-tip" style="display: none">ddd</span>
                                <span class="help-inline">是您获得授权唯一的id，必须唯一</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Resource Server</label>
                            <div class="controls">
                                <select class="selectpicker span4" name="resourceServerId">
                                    <c:forEach var="rs" items="${resourceServers}">
                                        <option value="${rs.id}" <c:if test="${rs.id == client.resourceServer.id}">selected="true"</c:if>>${rs.name}</option>
                                    </c:forEach>
                                 </select>
                            </div>
                        </div>
                        <div class="control-group" id="redirectInputs" >
                            <label class="control-label" style="margin-top:10px" >redirect URIs</label>
                                <c:forEach var="red" items="${client.redirectUris}" varStatus="status">
                                    <div class="controls" style="margin-top:10px" >
                                    <input class="input-xlarge focused" name="redirectUris"  value="${red}" placeholder="http://johndoe.com"  type="text" >
                                    <c:if test="${!status.last}">
                                             <button class="btn btn-primary btn-mini btn-delete" onclick="$(this).parent().remove()"><i class="icon-trash icon-white"></i>删除</button>
                                    </c:if>
                                    <c:if test="${status.last}">
                                            <button class="btn btn-primary btn-mini btn-add " onclick="addRedirectInput()"><i class="icon-plus-sign icon-white"></i>添加</button>
                                    </c:if>
                                    </div>
                                </c:forEach>
                            <c:if test="${client == null ||     client.redirectUris.size() == 0}">
                                <div class="controls" style="margin-top:10px" >
                                    <input class="input-xlarge focused" name="redirectUris"  value="" placeholder="http://johndoe.com"    type="text" >
                                    <button class="btn btn-primary btn-mini btn-add " onclick="addRedirectInput()"><i class="icon-plus-sign icon-white"></i>添加</button>
                                </div>
                            </c:if>
                        </div>
                        <div class="control-group">
                            <label class="control-label">联系人姓名</label>
                            <div class="controls">
                                <input class="input-xlarge focused" placeholder="联系人姓名"    name="contactName" value="${client.contactName}"  type="text" >
                                <span class="help-inline">该client app的联系人</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" >联系人Email</label>
                            <div class="controls">
                                <input class="input-xlarge focused" name="contactEmail" placeholder="联系人Email"   value="${client.contactEmail}"  type="email" >
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">网站缩略图url</label>
                            <div class="controls">
                                <input class="input-xlarge focused" name="thumbNailUrl" placeholder="网站缩略图url" value="${client.thumbNailUrl}"   type="text" >
                                <span class="help-inline">缩略图将会显示在系统中</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">是否允许implicit grant</label>
                            <div class="controls">
                                <input type="checkbox" name="allowedImplicitGrant" <c:if test="${client.allowedImplicitGrant}">checked="true"</c:if> />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">是否允许client credentials grant</label>
                            <div class="controls">
                                <input type="checkbox" name="allowedClientCredentials" <c:if test="${client.allowedClientCredentials}">checked="true"</c:if> />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">是否允许refresh tokens</label>
                            <div class="controls">
                                <input type="checkbox" name="useRefreshTokens" <c:if test="${client.useRefreshTokens}">checked="true"</c:if> />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">token过期时间</label>
                            <div class="controls">
                                <input type="number" name="expireDuration" value="${client.expireDuration}"/>
                                <span class="help-inline">单位秒,0代表永不过期</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">描述</label>
                            <div class="controls">
                                <textarea name="description" class="input-xlarge focused" >${client.description}</textarea>
                            </div>
                        </div>

                        <div class="form-actions">
                            <button type="submit" id="submit-btn" class="btn btn-primary submit-btn" >保存</button>
                            <button type="reset" class="btn">Cancel</button>
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
        uniqueURL : '${ctx}/manage/clientapp/isUnique'
    }
</script>
<script src="${ctx}/static/js/clientapp.js"></script>
</body>
</html>
