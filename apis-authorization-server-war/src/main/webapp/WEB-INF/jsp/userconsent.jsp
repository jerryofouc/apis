<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <title>Consent</title>
  <!-- Le styles -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/bootstrap.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css" />

  <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
    <style>
        .app-thumbnail{
            color: blue;
            font-size: 20px;
        }
    </style>
</head>
<body>
<div class="head">
  <h2>Oauth 2</h2>
</div>
<div class="main">
  <div class="full">


    <div class="page-header">
      <h1>第三方应用<strong>${client.name}</strong> 希望访问应用 <strong>${client.resourceServer.name}</strong> 的Rest API</h1>
    </div>

    <div class="consent">
        <span class="app-thumbnail">${client.name}</span>
      <img src="${pageContext.request.contextPath}/client/img/arrow.png" />
        <span class="app-thumbnail">${client.resourceServer.name}</span>
    </div>

    <form id="accept" method="post" action="${pageContext.request.contextPath}${actionUri}">
      <input type="hidden" name="AUTH_STATE" value="${AUTH_STATE}"/>
      <h2>您可以授权以下scope的openapi</h2>
      <fieldset>
          <c:forEach var="scope" items="${scopeList}">
              <table class="table table-bordered " style="width: 50%">
                  <thead>
                    <tr>
                     <th></th>
                     <th>scope</th>
                     <th>API 列表</th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="api" items="${scope.accessRestApis}" varStatus="status">
                    <tr>
                        <c:if test="${status.first}">
                            <td rowspan="${fn:length(scope.accessRestApis)}">
                                <input type="checkbox" checked="true" id="GRANTED_APIS" name="GRANTED_APIS" value="${scope.resourceServerScope.id}"/>
                            </td>
                            <td rowspan="${fn:length(scope.accessRestApis)}">
                                    ${scope.resourceServerScope.name}
                            </td>
                        </c:if>
                            <td>
                                ${api.completeUrl}
                            </td>
                    </tr>
                  </c:forEach>
                  </tbody>
              </table>
          </c:forEach>
      </fieldset>
      <fieldset>
        <div class="form-actions">
          <button id="user_oauth_approval" name="user_oauth_approval" value="true" type="submit"
                  class="btn btn-success">授权</button>
          &nbsp;&nbsp;&nbsp;<em>or</em>&nbsp;&nbsp;&nbsp;
          <button type="submit" name="user_oauth_approval" value="false"
                  class="btn btn-danger">拒绝授权</button>
        </div>
      </fieldset>
    </form>
  </div>
</div>

<div class="foot">
  <p>Powered by 后台技术部大数据技术组 2014</p>
</div>
</body>
</html>