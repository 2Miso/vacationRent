<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header_nosearchbar.jsp" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>로그인/회원가입</title>
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
	<link rel="stylesheet" href="<c:url value="/resources/css/color_orange.css" />">
    <link href="https://cdn.jsdelivr.net/npm/@xpressengine/xeicon@2.3.3/xeicon.min.css" rel="stylesheet">
    <style>
    body {
        --bs-font-sans-serif:margin:0; padding:0;font-size:14px;line-height:1.6;font-family:'Pretendard','Noto Sans KR', 'Apple SD Gothic Neo', '돋움', Dotum, Arial, Sans-serif;color:#464646;letter-spacing:0;-webkit-text-size-adjust:none;font-weight: 400
    }
    section{
      width:1024px;
      margin:0 auto;
      margin-top:200px;
    }
    .login_form{
      margin:0 auto;
      width:512px;
    }
    .joinBtn button{
    	margin-top:10px;
    	margin-bottom:10px;
    }
    </style>
</head>

<body>
<section>
  <div class="login_form">
    <h1 class="fw-bolder"> 회원가입 </h1>

    <div class="d-grid gap-2 joinBtn">
      <button onclick="location.href='${pageContext.request.contextPath}/login/main'" style="height:50px;" class="btn btn-primary " type="button">개인회원 가입</button>
      <button onclick="location.href='${pageContext.request.contextPath}/join/biz_join_terms'" style="height:50px;" class="btn btn-primary " type="button">비즈니스회원 가입</button>
    </div>
    
  </div>
</section>
</body>
</html>