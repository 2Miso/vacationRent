<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="Rent" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>예약 내역</title>
    <script src="<Rent:url value='/resources/js/jquery-3.7.1.min.js' />"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <link rel="stylesheet" type="text/css" href="<Rent:url value="/resources/css/color_orange.css" />">
    <style>
      body {
        --bs-font-sans-serif:margin:0; padding:0;font-size:14px;line-height:1.6;font-family:'Pretendard','Noto Sans KR', 'Apple SD Gothic Neo', '돋움', Dotum, Arial, Sans-serif;color:#464646;letter-spacing:0;-webkit-text-size-adjust:none;font-weight: 400
      }
      img{
        width:300px;
        height:200px;
        border-radius:9px;
      }
      .swiper {
        width: 100%;
        max-width: 960px;
        height: 320px;
      }
      .swiper-slide{
        margin-left:10px;
      }
      .swiper-button-prev, .swiper-button-next{
        top:var(--swiper-navigation-top-offset, 33%);
      }
    </style>
</head>
<body>

    <header></header>
    <br>
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
      <div class="d-flex mx-3 my-3"><!--내용 시작입니다. 이곳에 내용을 작성하면 됩니다-->
        <div style="margin-left:30px;">
          <h2>예약내역</h2><br><br>
          <h3>예약한 숙소</h3>
          <br>
          <div class="swiper swiper1">
              <div class="swiper-wrapper">
              <Rent:forEach var="r" items="${reserv}">
                  <div class="swiper-slide"><img src="<Rent:url value="/resources/img/acco/${r.thumbnail}" />" alt="${r.accoName} 사진"></div>
              </Rent:forEach>
              </div>
              <div class="swiper-button-next swiper1-button-next"></div>
              <div class="swiper-button-prev swiper1-button-prev"></div>
          </div>
          <br>
          <h3>이용완료 및 예약취소</h3>
          <br>
          <div class="swiper swiper2">
              <div class="swiper-wrapper">
              <Rent:forEach var="d" items="${depre}">
                  <div class="swiper-slide"><img src="<Rent:url value="/resources/img/acco/${d.thumbnail}" />" alt="${d.accoName} 사진"></div>
              </Rent:forEach>
              </div>
              <div class="swiper-button-next swiper2-button-next"></div>
              <div class="swiper-button-prev swiper2-button-prev"></div>
          </div>
        </div>
      <br>
      <footer></footer>
    <!-- Swiper JS -->
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

    <!-- Initialize Swiper -->
        <script>
          function getDirection() {
              return window.innerWidth <= 760 ? 'vertical' : 'horizontal';
          }

          $(document).ready(function () {
            function createSwiper(selector, nextBtn, prevBtn) {
            return new Swiper(selector, {
              slidesPerView:3,
              spaceBetween:20,
              direction: getDirection(),
              navigation: {
                nextEl: nextBtn,
                prevEl: prevBtn,
              },
              loop:false,
              watchOverflow:true,
              watchSlidesProgress: true,
              watchOverflow: true,
              slidesOffsetAfter:60
            });
            }

            var swiper1 = createSwiper('.swiper1', '.swiper1-button-next', '.swiper1-button-prev');
            var swiper2 = createSwiper('.swiper2', '.swiper2-button-next', '.swiper2-button-prev');

            window.addEventListener('resize', function () {
            var newDirection = getDirection();
            swiper1.changeDirection(newDirection);
            swiper2.changeDirection(newDirection);
            });
          });
        </script>
      </div><!--내용 끝-->
    </section>
</body>
</html>