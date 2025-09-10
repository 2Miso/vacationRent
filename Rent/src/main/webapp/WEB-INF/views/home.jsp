<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty sessionScope.user}">
			환영합니다 ${sessionScope.user.nick} 님.
			<a href="<c:url value="/user/logout" />">로그아웃</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value="/user/login" />">로그인</a>
		</c:otherwise>
	</c:choose>
</body>
</html>
