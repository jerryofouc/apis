<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Open API${PAGE_TYPE}</title>
    <link href="${ctx}/static/lib/bootstrap-select.min.css" rel="stylesheet" type="text/css">
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
                <form class="form-horizontal" action="${ctx}/manage/api/${FORM_ACTION}" method="post">
                    <fieldset>
                        <div class="control-group">
                            <label class="control-label">ResourceOwner</label>
                            <div class="controls bootstrap-select">
                                <select class="selectpicker" name="resourceOwnerId">
                                    <c:forEach items="${resourceOwners}" var="ro">
                                        <option value="${ro.id}">${ro.name}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-inline">该api的授权者</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">scope</label>
                            <div class="controls bootstrap-select">
                                <select class="selectpicker" name="scopeId">
                                    <c:forEach items="${allResourceServers}" var="rs">
                                        <optgroup label="${rs.name}">
                                            <c:forEach items="${rs.resourceServerScopes}" var="rss">
                                                <option value="${rss.id}">${rs.name} : ${rss.name}</option>
                                            </c:forEach>
                                        </optgroup>
                                    </c:forEach>
                                </select>
                                <span class="help-inline">根据resource Server来选择scope</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">API url</label>
                            <div class="controls">
                                <input class="input-xlarge focused" name="completeUrl" placeholder="http://johndoe.com" required pattern="(http|https)://.+"  type="text" >
                                <span class="help-inline">可以使用通配符来表示</span>
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
<script>
    $(document).ready(function(){
        $('.selectpicker').selectpicker({
        });
    }) ;
</script>
<script type="text/javascript" src="${ctx}/static/lib/bootstrap-select.min.js"></script>
<script src="${ctx}/static/js/api.js"></script>
</body>
</html>
