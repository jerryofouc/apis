<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
  <head>
    <title>用户登录</title>
    <!-- Bootstrap -->
    <link href="${ctx}/static/lib/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${ctx}/static/lib/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="${ctx}/static/lib/styles.css" rel="stylesheet" media="screen">
     <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="${ctx}/static/js/modernizr-2.6.2-rmodernizr-2.6.2-respond-1.1.0.min.jsespond-1.1.0.min.js"></script>
  </head>
  <body id="login">
    <div class="container">

      <form class="form-signin" action="${ctx}/manage/login" method="post">
        <h2 class="form-signin-heading">请登录oauth2授权管理后台</h2>
        <input type="text" name="userName" class="input-block-level" placeholder="corp邮箱前缀">
        <input type="password" name="password" class="input-block-level" placeholder="密码">
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-large btn-primary" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->
    <script src="${ctx}/static/lib/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>