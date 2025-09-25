<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>로그인/회원가입</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.slim.js" integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc=" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <link href="color_orange.css" rel="stylesheet" type="text/css">
    <link href="size.css" rel="stylesheet" type="text/css">
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
    </style>
</head>

<body>
<section>
  <div class="login_form">
    <h1 class="fw-bolder"> 로그인/회원가입 </h1>

    <div class="d-grid gap-2">
      <button link="#" style="height:50px; background-color:#03C75A; color: white; border:none;" class="btn btn-primary " type="button"><i class="xi-naver me-2"></i>네이버로 시작하기</button><!--네이버 소셜로그인 링크를 걸어야 합니다-->
      <button onclick="location.href='https://kauth.kakao.com/oauth/authorize?client_id=d689d303f45b6b16979a1e2bcb396fe8&redirect_uri=http://localhost:8080/vaca/login/kakaocallback&response_type=code'" style="height:50px; background-color:#FEE500; color: black; border:none;" class="btn btn-primary " type="button"><i class="xi-kakaotalk me-2"></i>카카오로 시작하기</button><!--카카오 소셜로그인 링크를 걸어야 합니다-->
      <button link="#" style="height:50px; background-color:#F2F2F2; color: black; border:none;" class="btn btn-primary " type="button"><i class="bi bi-google me-2"></i>구글로 시작하기</button><!--구글 소셜로그인 링크를 걸어야 합니다-->
      <button link="#" style="height:50px;" class="btn btn-primary " type="button"><i class="bi bi-envelope me-2"></i>이메일로 로그인</button><!--이메일로 로그인하는 링크를 걸어야 합니다-->
    </div>

    <div class="mx-auto py-3">
      <p class="my-0 mx-auto text-center ">
      <a href="<c:url value='/login/biz_login' />"
   		class="mx-auto text-center link-secondary link-offset-2 
        link-underline-opacity-0 link-underline-opacity-100-hover">비즈니스 로그인/회원가입</a></p><!--비즈니스 로그인으로 이동하는 링크를 걸어야 합니다-->
    </div>
    
  </div>
</section>
</body>
</html>