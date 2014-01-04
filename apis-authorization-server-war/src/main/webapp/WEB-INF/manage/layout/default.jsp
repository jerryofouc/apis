<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html class="no-js">
<head>
    <title><sitemesh:title/></title>
    <!-- Bootstrap -->
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${ctx}/static/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="${ctx}/static/js/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
    <link href="${ctx}/static/css/styles.css" rel="stylesheet" media="screen">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="${ctx}/static/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <sitemesh:head/>
</head>

<body>
<%@ include file="/WEB-INF/manage/layout/header.jsp"%>
<div class="container-fluid">
<div class="row-fluid">
<%@ include file="/WEB-INF/manage/layout/sidebar.jsp"%>
<!--/span-->
<div class="span9" id="content">
    <sitemesh:body/>
</div>
</div>
<hr>
<footer>
    <p>&copy; Powered by 后台技术部大数据技术组 2014</p>
</footer>
</div>
<!--/.fluid-container-->
<script src="${ctx}/static/js/jquery-1.9.1.min.js"></script>
<script src="${ctx}/static/js/bootstrap.min.js"></script>
<script src="${ctx}/static/js/jquery.easy-pie-chart.js"></script>
<script src="${ctx}/static/js/scripts.js"></script>
<script>
    $(function() {
        // Easy pie charts
        $('.chart').easyPieChart({animate: 1000});
    });
</script>
</body>
</html>