<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="Rent" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>예약취소 상세</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <link href="<Rent:url value ='/resources/css/color_orange.css' />" rel="stylesheet" type="text/css">
    <style>
        .content{
            padding:10px;
            width:500px;
            height:500px;
            margin:50px auto;
            border:1px solid #d9d9d9;
            border-radius:9px;
        }
        .info{
            margin:0 0 10px 10px;
        }
    </style>
</head>
<body>
    <header></header>
    <section class="d-flex">
        <div><!--사이드바 시작-->
            <div class="d-flex" style="height:100vh;" >
                <div style="width: 280px;" class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" >
            <a class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                <i class="bi bi-gear me-2" style="font-size:24px;"></i>
                <span class="fs-4" style="font-weight: bold;">일반 회원</span>
            </a>
            <hr>
            <ul class="nav nav-pills flex-column mb-auto">
                <li class="nav-item"> 
                <a href="#" class="nav-link active" aria-current="page"><!--숙소 관리 페이지로 링크 걸어야 합니다--> <!--aria current가 현재 표시되는 페이지를 강조합니다-->
                    <i class="bi bi-house me-2  "></i>예약 내역
                </a>
                </li>
                <li>
                <a href="#" class="nav-link text-white"><!--객실 관리 페이지로 링크 걸어야 합니다-->
                    <i class="bi bi-file me-2"></i>찜 목록
                </a>
                </li>
                <li>
                <a href="#" class="nav-link text-white"><!--예약자 관리 페이지로 링크 걸어야 합니다-->
                    <i class="bi bi-people me-2"></i>내 정보 관리
                </a>
                </li>
                <li>
                <a href="#" class="nav-link text-white"><!--계정 설정 페이지로 링크 걸어야 합니다-->
                    <i class="bi bi-person-circle me-2"></i>내 문의 내역
                </a>
                </li>
            </ul>
            <hr>
            <div class="dropdown">
                <a href="#" class="nav-link text-white">
                    <i class="bi bi-house-fill"></i><!--홈으로 이동하는 페이지로 링크 걸어야 합니다-->
                    홈으로
                </a>
            </div>
            </div>
            </div>
        </div><!--사이드바 끝-->
        <div class="content"><!--내용 시작입니다. 이곳에 내용을 작성하면 됩니다-->
            <h3 style="margin-bottom:40px;">예약취소 숙소</h3>
            <!--하단 내용은 실제 클릭한 숙소의 정보가 들어가야 합니다.-->
            <div class="info">숙소 이름 : </div>
            <div class="info">숙소 주소 : </div>
            <div class="info">취소 날짜 : </div>
            <div class="info">결제 정보 : </div>
            <div class="info">취소 내역 : </div>
        </div>
    </section>
    <footer></footer>
</body>
</html>