<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
%>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bootstrap demo</title>
  <!-- 초기화 css -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
  <!-- 부트스트랩 css -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <!-- 부트스트랩 아이콘 css -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
  <!-- 글꼴 css -->
  <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
  <!-- 오렌지테마 css -->
  <link rel="stylesheet" href="<c:url value="/resources/css/color_orange.css" />">
  <!-- jQuery -->
  <script src="<c:url value="/resources/js/jquery-3.7.1.min.js" />"></script>
  <!-- 부트스트랩 js -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
  <style>
  	header a{
  		text-decoration:none;
  	}
    header {
      --bs-font-sans-serif:margin:0; padding:0;font-size:14px;line-height:1.6;font-family:'Pretendard','Noto Sans KR', 'Apple SD Gothic Neo', '돋움', Dotum, Arial, Sans-serif;color:#464646;letter-spacing:0;-webkit-text-size-adjust:none;font-weight: 400;
      color:black;
      border-bottom:1px solid gray;
    }
    header .navvar_wrapper{
      width:1024px;
      background-color: black;
      margin: 0 auto;
      
    }
    header .navbar{
       background-color:white !important;
    }
  </style>
</head>
<body>
<header>
<div class="navvar_wrapper"><!--navvarwarp start-->
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid d-flex justify-content-between">

    <a class="navbar-brand" href="<c:url value="/" />"><img src="${pageContext.request.contextPath}/resources/img/CI.png" style="width:60px;"><h1 style="display:inline-block; color:var(--bs-orange); position:relative; top:7px;">Rent</h1></a>
    
    <div class="collapse navbar-collapse" style="margin-left: 670px;" id="navbarNavDropdown"><!--로그인버튼 또는 닉네임-->
      <ul class="navbar-nav">
      <c:choose>
      	<c:when test='${not empty sessionScope.user && user.grade=="U"}'>
        <span class="navbar-text">
          "${user.nickname}"님
          </span>
        <li class="nav-item dropdown">
          
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <i class="bi bi-list"></i>
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reservation/reserv">마이페이지</a></li> <!--로그인 링크를 걸어야 합니다-->
            <li><a class="dropdown-item" href="<c:url value="/customer/faq" />">고객센터</a></li><!--고객센터 링크를 걸어야 합니다-->
            <li><a class="dropdown-item" href="<c:url value="/logout" />">로그아웃</a></li><!--고객센터 링크를 걸어야 합니다-->
          </ul>
        </li>
        </c:when>
      	<c:when test='${not empty sessionScope.user && user.grade=="A"}'>
        <span class="navbar-text">
          "${user.nickname}"님
          </span>
        <li class="nav-item dropdown">
          
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <i class="bi bi-list"></i>
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/private_question">관리자 페이지</a></li> <!--로그인 링크를 걸어야 합니다-->
            <li><a class="dropdown-item" href="<c:url value="/customer/faq" />">고객센터</a></li><!--고객센터 링크를 걸어야 합니다-->
            <li><a class="dropdown-item" href="<c:url value="/logout" />">로그아웃</a></li><!--고객센터 링크를 걸어야 합니다-->
          </ul>
        </li>
        </c:when>
      	<c:when test="${not empty sessionScope.biz}">
        <span class="navbar-text">
          "${sessionScope.biz.name}"님
          </span>
        <li class="nav-item dropdown">
          
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <i class="bi bi-list"></i>
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/biz/biz_mypage_acco">마이페이지</a></li> <!--로그인 링크를 걸어야 합니다-->
            <li><a class="dropdown-item" href="<c:url value="/customer/faq" />">고객센터</a></li><!--고객센터 링크를 걸어야 합니다-->
            <li><a class="dropdown-item" href="<c:url value="/logout" />">로그아웃</a></li><!--고객센터 링크를 걸어야 합니다-->
          </ul>
        </li>
        </c:when>
       	<c:otherwise>
        <span class="navbar-text">
          <button class="btn btn-primary" onclick="location.href='<c:url value="/login/main" />'">로그인</button>
          </span>
        <li class="nav-item dropdown">
          
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <i class="bi bi-list"></i>
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<c:url value="/join" />">회원가입</a></li> <!--로그인 링크를 걸어야 합니다-->
            <li><a class="dropdown-item" href="<c:url value="/login/main" />">로그인</a></li> <!--로그인 링크를 걸어야 합니다-->
            <li><a class="dropdown-item" href="<c:url value="/customer/faq" />">고객센터</a></li><!--고객센터 링크를 걸어야 합니다-->
          </ul>
        </li>
        </c:otherwise>
      </c:choose>
      </ul>
    </div>

  </div>
  </nav>
</div><!--navvarwarp end-->
</header>
</body>
</html>