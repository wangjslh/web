<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>user</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script></script>
</head>
<body>
<c:forEach items="${users}" var="item" varStatus="status">
${status.count}:
<c:choose>  
	<c:when test="${fn:length(item.account) > 6}">  
		<c:out value="${fn:substring(item.account, 0, 6)}..." />
	</c:when>
	<c:otherwise>  
		<c:out value="${item.account}" />  
	</c:otherwise>  
</c:choose>、
<fmt:formatDate value="${item.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
<br>
</c:forEach>
</body>
</html>
