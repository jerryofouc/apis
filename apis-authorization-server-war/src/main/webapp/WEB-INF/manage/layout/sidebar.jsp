<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="span3" id="sidebar">
    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
        <li class="active">
            <a href="${ctx}/manage/resourceServer"><i class="icon-chevron-right"></i>Resource Server管理</a>
        </li>
        <li>
            <a href="calendar.html"><i class="icon-chevron-right"></i>Client Application管理</a>
        </li>
        <li>
            <a href="stats.html"><i class="icon-chevron-right"></i>Resource Owner管理</a>
        </li>
        <li>
            <a href="form.html"><i class="icon-chevron-right"></i>OpenApi 管理</a>
        </li>
    </ul>
</div>
