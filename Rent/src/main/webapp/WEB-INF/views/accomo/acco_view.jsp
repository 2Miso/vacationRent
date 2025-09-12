<%@page import="com.rent.vaca.acco.AccoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
AccoVO board = (AccoVO)request.getAttribute("acco");
%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <link href="<c:url value="/resources/css/color_orange.css" />" rel="stylesheet" type="text/css">
    <!-- jQuery js -->
    <script src="<c:url value="/resources/js/jquery-3.7.1.min.js"/>"></script>
    <!-- 부트스트랩 js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
    <!-- 부트스트랩 아이콘 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <!-- 부트스트랩 글꼴 -->
    <link rel="stylesheet" as="style" crossorigin
        href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <!-- 스와이퍼 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link rel="stylesheet" href="<c:url value="/resources/css/mainSwiper.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/reviewSwiper.css" />">
	<!-- 지도 -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=767914bf0cfc904dd9cb8b0fd6dd25bc&libraries=services"></script>
    <!-- 스와이퍼 js -->
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <!-- 하트 -->
    <link rel="stylesheet" href="<c:url value="/resources/css/heart_button.css" />">

    <style>
        section {
            padding: 50px 0;
            width: 1024px;
            margin: 0 auto;
        }

        .mainPhoto .container {
            width: 1024px;
            height: 500px;
            position:relative;
        }

        .bigImage,
        .smallImage {
            width: 500px;
            height: 500px;
        }

        .row {
            margin: 0;
        }

        .row>* {
            padding: 1px;
        }

        .smallImage .row {
            height: 50%;
        }

        .smallImage .col {
            width: 50%;
        }

        .imgContainer {
            width: 100%;
            height: 100%;
            overflow: hidden;
            border-radius: 20px;
        }

        .imgContainer img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        .morePhoto{
            display:inline-block;
            background:rgba(255, 255, 255, 0.451);
            border-radius:5px;
            padding:0 5px;
            position:absolute;
        }
        .mainPhoto .morePhoto{
            right:30px;
            bottom:15px;
        }
        .titleArea {
            padding: 30px;
            display: flex;
            justify-content: space-between;
        }

        .subInfo {
            display: flex;
            justify-content: space-between;
            width: 100%;
        }
      .orangeContainer{
            border-radius:15px;
            border:3px solid var(--bs-orange);
            padding:10px;
        }
        .subInfo>div {
            width: 30%;
            height: 150px;
        }

        .roomList {
            padding: 30px 0;
        }

        .room {
            height: 200px;
        }
        .roomPhoto{
            padding:0;
            border-radius:15px;
            overflow:hidden;
        }
        .roomPhoto img{
            width:100%;
            height:100%;
            object-fit:cover;
            object-position:center center;
        }
        .room .morePhoto{
            position:relative;
            bottom:30px;
            left:210px;
        }
        .roomInfo{
            padding-left:20px;
        }
        .roomInfo .row *{
            width:auto;
        }
        .roomName{
            font-size:3em;
        }
        .cursorPointer:hover{
            cursor:pointer;
            font-weight:bold;
        }
        .roomDetail{
            position:relative;
        }
        .roomDetail span{
            position:absolute;
            right:20px;
        }
        .roomPrice{
            padding-top:26px;
        }
        hr{
            color:var(--bs-orange);
            border-top:2px solid;
            opacity:1;
            margin:20px 0;
        }
        .owner_info table{
            margin:0 auto;
            width:100%;
        }
        .owner_info table, .owner_info table * {
            border:1px solid black;
        }
        .owner_info tr > *{
            padding:10px;
        }
        .owner_info th{
            text-align:end;
            background-color:#FFC067;
        }
        .owner_info h3{
            margin:0;
        }
        .reviewTitle{
            display:flex;
            justify-content: space-between;
            align-items:baseline;
        }
        .reviewList > div{
            padding: 10px;
            width:100%;
            border-radius: 15px;
            border: 1px solid var(--bs-orange);
        }
        .reviewPhoto{
            position:relative;
        }
        .room .col-2, .reviewPhoto .col-2{
            padding:0;
            border-radius:15px;
            height:167px;
            overflow:hidden;
        }
        .room .col-2, .reviewPhoto img{
            width:100%;
            height:100%;
            object-fit:cover;
            object-position:center center;
        }
        .reviewPhoto .morePhoto{
            right:75px;
            bottom:10px;
        }
        .Page{
            padding-top:20px;
        }

    #mainPhotoModal .content_area, #mainPhotoModal .thumb_area {
        display: none;
    }

    #mainPhotoModal .content_area.active, #mainPhotoModal .thumb_area.active {
        display: block;
    }
    </style>
    <script>
        /* 숙소 찜 ajax 사용 */
        $(function(){
            let navItem = $(".nav-item");
            $(navItem).on("click", function(){
                navItem.children("a").removeClass("active");
                $(this).children("a").addClass("active");
            });
            $(".subInfo").children().on("click", function(){
                  $('html, body').animate({scrollTop:$(this.id).position().top}, 'fast');
            });

            //카카오지도
            /* var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
    		var options = { //지도를 생성할 때 필요한 기본 옵션
    			center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표. 위도, 경도 순서로.
    			level: 3 //지도의 레벨(확대, 축소 정도)
    		};
    		
    		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴 */
            var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
            mapOption = {
                center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };  

        // 지도를 생성합니다    
        var map = new kakao.maps.Map(mapContainer, mapOption); 

        // 주소-좌표 변환 객체를 생성합니다
        var geocoder = new kakao.maps.services.Geocoder();

        // 주소로 좌표를 검색합니다
        geocoder.addressSearch('${acco.addr}', function(result, status) {

            // 정상적으로 검색이 완료됐으면 
             if (status === kakao.maps.services.Status.OK) {

                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                // 결과값으로 받은 위치를 마커로 표시합니다
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: coords
                });

                // 인포윈도우로 장소에 대한 설명을 표시합니다
                var infowindow = new kakao.maps.InfoWindow({
                    content: '<div style="width:150px;text-align:center;padding:6px 0;">${acco.name}</div>'
                });
                infowindow.open(map, marker);

                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                map.setCenter(coords);
            } 
        });    
        });
        //객실사진 조회 모달 탭 바꾸기
        function changeTabMenu(obj){
            let tabId = $(obj).data("tabId");
            $("#mainPhotoModal [data-content-id]").removeClass("active");
            $("#mainPhotoModal [data-thumb-id]").removeClass("active");
            
            $("[data-content-id="+tabId+"]").addClass("active");
            $("[data-thumb-id="+tabId+"]").addClass("active");
        }
    </script>
</head>

<body>
    <section>
        <article class="mainPhoto">
            <div class="container text-center">
                <div class="row">
                    <div class="col bigImage">
                        <div class="imgContainer" data-bs-toggle="modal" data-bs-target="#mainPhotoModal">
                            <img src="" alt="">
                        </div>
                    </div>
                    <div class="col smallImage">
                        <div class="row">
                            <div class="col">
                                <div class="imgContainer" data-bs-toggle="modal" data-bs-target="#mainPhotoModal">
                                    <img src="" alt="">
                                </div>
                            </div>
                            <div class="col">
                                <div class="imgContainer" data-bs-toggle="modal" data-bs-target="#mainPhotoModal">
                                    <img src="" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="imgContainer" data-bs-toggle="modal" data-bs-target="#mainPhotoModal">
                                    <img src="" alt="">
                                </div>
                            </div>
                            <div class="col">
                                <div class="imgContainer" data-bs-toggle="modal" data-bs-target="#mainPhotoModal">
                                    <img src="" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div> <!-- end: .row -->
                <div class="morePhoto" data-bs-toggle="modal" data-bs-target="#mainPhotoModal">
                    +n
                </div>
            </div> <!-- end: .container -->
        </article>
        <div class="titleArea">
            <div>
                <h6>${acco.type}</h6>
                <h2>${acco.name}</h2>
            </div>
            <div class="heartContainer">
                <div id='heart' class='button'></div>
            </div>
        </div>
        <article class="subInfo">
            <div id="#review" class="orangeContainer">리뷰</div>
            <div id="#facil" class="orangeContainer">서비스 및 부대시설</div>
            <div id="#location" class="orangeContainer">주소</div>
        </article>
        <article class="roomList">
            <h3>객실선택</h3>
            <div class="room container orangeContainer row">
                <div class="col-3 roomPhoto" data-bs-toggle="modal" data-bs-target="#mainPhotoModal">
                    <img src="" alt="">
                    <div class="morePhoto" data-bs-toggle="modal" data-bs-target="#mainPhotoModal">
                        +n
                    </div>
                </div>
                <div class="col roomInfo align-content-between">
                    <h4 class="row roomName">
                        객실이름
                    </h4>
                    <div class="row roomDetail">
                        <span class="cursorPointer"  data-bs-toggle="modal" data-bs-target="#roomModal">상세정보&gt;</span>
                    </div>
                    <!-- Modal -->
                    <div class="modal fade" id="roomModal" tabindex="-1" aria-labelledby="roomModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="roomModalLabel">객실이름</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <h4>객실정보</h4>
                                    <div>체크인 ${acco.checkin} / 체크아웃 ${acco.checkout}</div>
                                    <div>기준인원/최대인원</div>
                                    <div>더블침대 1개</div>
                                    <div>거실1개 방2개 욕실2개 / 00.0㎡</div>
                                    <hr>
                                    <h4>추가정보</h4>
                                    <div>추가인원 요금 0,000원</div>
                                    <hr>
                                    <h4>편의시설</h4>
                                    <div>TV, 전기포트, 금고, 에어컨, 냉장고, 와이파이</div>
                                </div><!-- end:movda-body -->
                            </div><!-- end: modal-content -->
                        </div><!-- end: modal-dialog -->
                    </div>
                    <!-- end:Modal -->
                    <div class="row roomPrice">
                        <strong>100,000</strong>원
                    </div>
                    <div class="row roomSimpleInfo">
                        기준n인/추가n인<br>
                        추가정보 설명
                    </div>
                </div>
            </div><!-- end:room -->
            <div class="roomInfo" data-bs-toggle="modal" data-bs-target="#roomModal">

            </div>
            <!-- Modal -->
            <div class="modal fade" id="roomModal" tabindex="-1" aria-labelledby="roomModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="roomModalLabel">객실이름</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <h4>객실정보</h4>
                    <div>체크인/체크아웃</div>
                    <div>기준인원/최대인원</div>
                    <div>더블침대 1개</div>
                    <div>거실1개 방2개 욕실2개 / 00.0㎡</div>
                    <hr>
                    <h4>추가정보</h4>
                    <div>추가인원 요금 0,000원</div>
                    <hr>
                    <h4>편의시설</h4>
                    <div>TV, 전기포트, 금고, 에어컨, 냉장고, 와이파이</div>
                </div><!-- end:movda-body -->
            </div><!-- end: modal-content -->
            </div><!-- end: modal-dialog -->
            </div>
            <!-- end:Modal -->
        </article><!-- end: .roomList -->
        <hr>
        <article class="acco_intro">
            <h3>숙소소개</h3>
            <div>
                ${acco.description}
            </div>
        </article><!-- end:acco_intro -->
        <hr>
        <article class="facil" id="facil">
            <h3>서비스 및 부대시설</h3>
            <div class="container text-center">
                <div class="row">
                    <div class="col-3">1</div>
                    <div class="col-3">2</div>
                    <div class="col-3">3</div>
                    <div class="col-3">4</div>
                    <div class="col-3">5</div>
                    <div class="col-3">6</div>
                    <div class="col-3">7</div>
                </div>
            </div>
        </article>
        <hr>
        <article class="acco_info">
            <h3>숙소정보</h3>
            <div>체크인 ${acco.checkin} / 체크아웃 ${acco.checkout}</div>
            <div>추가요금</div>
            <div>등등 공통정보</div>
        </article>
        <hr>
        <div class="owner_info">
            <h3  style="display:inline-block; cursor:pointer;" data-bs-toggle="modal" data-bs-target="#ownerModal">판매자정보 &gt;</h3>
            <!-- Modal -->
            <div class="modal fade" id="ownerModal" tabindex="-1" aria-labelledby="ownerModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ownerModalLabel">판매자 정보</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table>
                        <tr>
                            <th>상호</th>
                            <td>${acco.name}</td>
                        </tr>
                        <tr>
                            <th>대표자명</th>
                            <td>${acco.biz.owner}</td>
                        </tr>
                        <tr>
                            <th>주소</th>
                            <td>${acco.addr}</td>
                        </tr>
                        <tr>
                            <th>이메일</th>
                            <td>${acco.biz.email}</td>
                        </tr>
                        <tr>
                            <th>사업자번호</th>
                            <td>${acco.biz.certificateNo}</td>
                        </tr>
                    </table><br>
                    <ul>
                        <li>
                            판매자 정보는 판매자의 명시적 동의 없이 영리 목적의 마케팅, 광고 등에 활용할 수 없습니다.<br>
                            이를 어길 시 정보통신망법 등 관련 법령에 의거하여 과태료 부과 및 민형사상 책임을 지게 될 수 있습니다.
                        </li>
                    </ul>
                </div><!-- end:movda-body -->
            </div><!-- end: modal-content -->
            </div><!-- end: modal-dialog -->
            </div>
            <!-- end:Modal -->
        </div><!-- end:.owner_info -->
        <hr>
        <article id="location">
            <h3>위치</h3>
            <div>${acco.addr}</div>
            <div id="map" style="border:1px solid black; width:1024px; height:426px;">
            </div>
        </article>
        <hr>
        <article id="review">
            <div class="reviewTitle">
                <h3 style="font-size:1em;">★리뷰 (별점평균) (리뷰개수)</h3>
                <select id="review_order">
                    <option value="1" selected>최신순</option>
                    <option value="2">평점 높은 순</option>
                    <option value="3">평점 낮은 순</option>
                </select>
            </div>
            <div class="reviewList">
                <div class="review">
                    <div>(닉네임)</div>
                    <div><span style="color:orange;">★★★★☆</span> (작성일자)</div>
                    <div class="reviewPhoto">
                        <div class="row justify-content-evenly">
                            <div class="col-2" data-bs-toggle="modal" data-bs-target="#reviewPhotoModal">
                                <img src="resources/img/거실.jpg" alt="">
                            </div>
                            <div class="col-2" data-bs-toggle="modal" data-bs-target="#reviewPhotoModal">
                                <img src="resources/img/오션뷰.jpg" alt="">
                            </div>
                            <div class="col-2" data-bs-toggle="modal" data-bs-target="#reviewPhotoModal">
                                <img src="resources/img/쿼드룸.jpg" alt="">
                            </div>
                            <div class="col-2" data-bs-toggle="modal" data-bs-target="#reviewPhotoModal">
                                <img src="resources/img/쿼드룸.jpg" alt="">
                            </div>
                        </div><!-- end:.row -->
                        <div class="morePhoto" data-bs-toggle="modal" data-bs-target="#reviewPhotoModal">
                            +n
                        </div>
                    </div><!-- end:.reviewPhoto -->
                        <!-- Modal -->
                        <div class="modal fade" id="reviewPhotoModal" tabindex="-1" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered  modal-xl">
                            <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                    <!-- Swiper -->
                                    <div class="swiper reviewSwiper">
                                        <div class="swiper-wrapper">

                                        <!-- 
                                        swiper-slide클래스에 swiper-slide-prev, swiper-slide-active, swiper-slide-next 클래스 추가/삭제
                                        -->

                                        <div class="swiper-slide"><img src="resources/img/거실.jpg" alt=""></div>
                                        <div class="swiper-slide">Slide 2</div>
                                        <div class="swiper-slide"><img src="resources/img/오션뷰.jpg" alt=""></div>
                                        <div class="swiper-slide">Slide 4</div>
                                        <div class="swiper-slide"><img src="resources/img/쿼드룸.jpg" alt=""></div>
                                        <div class="swiper-slide">Slide 6</div>
                                        <div class="swiper-slide">Slide 7</div>
                                        <div class="swiper-slide">Slide 8</div>
                                        <div class="swiper-slide">Slide 9</div>
                                        </div>
                                        <div class="swiper-button-next"></div>
                                        <div class="swiper-button-prev"></div>
                                        <div class="swiper-pagination"></div>
                                    </div>
                                    <!-- Initialize Swiper -->
                                    <script>
                                        var reviewSwiper = new Swiper(".reviewSwiper", {
                                        pagination: {
                                            el: ".swiper-pagination",
                                            type: "fraction",
                                        },
                                        navigation: {
                                            nextEl: ".swiper-button-next",
                                            prevEl: ".swiper-button-prev",
                                        },
                                        });
                                    </script>
                            </div><!-- end:movda-body -->
                        </div><!-- end: .modal-content -->
                        </div><!-- end: .modal-dialog -->
                        </div><!-- end: .modal -->
                        <!-- end:Modal -->
                    <div class="reviewText">
    
                    </div><!-- end:.reviewText -->
                </div><!-- end:.review -->
            </div><!-- end:reviewList -->
            <div class="Page">
                <ul class="pagination justify-content-center">
                    <li class="page-item"><a class="page-link" href="#">&lt;</a></li>
                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item disabled"><a class="page-link" href="#">&gt;</a></li>
                </ul>
            </div> <!-- end:Page -->
        </article><!-- end:#review -->

    </section>
    <!-- mainPhoto Modal -->
    <div class="modal fade" id="mainPhotoModal" tabindex="-1" aria-labelledby="mainPhotoModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="mainPhotoModalLabel">숙소이름</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="photo_type_tab">
                    <ul class="nav nav-tabs">
                        <li class="nav-item" data-tab-id="content01" onclick="changeTabMenu(this)">
                            <a class="nav-link active" href="#">전경</a>
                        </li>
                        <li class="nav-item" data-tab-id="content02" onclick="changeTabMenu(this)">
                            <a class="nav-link" href="#">객실1</a>
                        </li>
                        <li class="nav-item" data-tab-id="content03" onclick="changeTabMenu(this)">
                            <a class="nav-link" href="#">객실2</a>
                        </li>
                        <li class="nav-item" data-tab-id="content04" onclick="changeTabMenu(this)">
                            <a class="nav-link" href="#">객실3</a>
                        </li>
                        <li class="nav-item" data-tab-id="content05" onclick="changeTabMenu(this)">
                            <a class="nav-link" href="#">객실4</a>
                        </li>
                        <li class="nav-item" data-tab-id="content06" onclick="changeTabMenu(this)">
                            <a class="nav-link" href="#">객실5</a>
                        </li>
                        <li class="nav-item" data-tab-id="content07" onclick="changeTabMenu(this)">
                            <a class="nav-link" href="#">객실6</a>
                        </li>
                        <li class="nav-item" data-tab-id="content08" onclick="changeTabMenu(this)">
                            <a class="nav-link" href="#">객실7</a>
                        </li>
                        <li class="nav-item" data-tab-id="content09" onclick="changeTabMenu(this)">
                            <a class="nav-link" href="#">객실8</a>
                        </li>
                        <li class="nav-item" data-tab-id="content10" onclick="changeTabMenu(this)">
                            <a class="nav-link" href="#">객실9</a>
                        </li>
                        <li class="nav-item" data-tab-id="content11" onclick="changeTabMenu(this)">
                            <a class="nav-link" href="#">객실10</a>
                        </li>
                    </ul>
                </div><!-- end:.modal-header -->
                <div class="modal-body">
                    <!-- Swiper -->
                    <div style="--swiper-navigation-color: #fff; --swiper-pagination-color: #fff"
                        class="accomo swiper mySwiper2 content_area active" data-content-id="content01">
                        <div class="swiper-wrapper" style="align-items:center;">
                            <div class="swiper-slide">
                                <img src="resources/img/거실.jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="https://swiperjs.com/demos/images/nature-2.jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="resources/img/오션뷰.jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="https://swiperjs.com/demos/images/nature-4.jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="resources/img/쿼드룸.jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="https://swiperjs.com/demos/images/nature-6.jpg" />
                            </div>
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                    </div>
                    <div style="--swiper-navigation-color: #fff; --swiper-pagination-color: #fff"
                        class="accomo swiper mySwiper2 content_area" data-content-id="content02">
                        <div class="swiper-wrapper" style="align-items:center;">
                            <div class="swiper-slide">
                                <img src="resources/img/다운로드 (1).jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="resources/img/sample1.png" />
                            </div>
                            <div class="swiper-slide">
                                <img src="resources/img/다운로드 (2).jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="https://swiperjs.com/demos/images/nature-4.jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="resources/img/쿼드룸.jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="https://swiperjs.com/demos/images/nature-6.jpg" />
                            </div>
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                    </div>
                </div> <!-- end: modal-body -->
                <div class="modal-footer">
                    <div thumbsSlider="" class="swiper mySwiper">
                        <div class="swiper-wrapper" style="align-items:center;">
                            <!-- 
                            swiper-slide클래스에 swiper-slide-prev, swiper-slide-active, swiper-slide-next 추가/삭제
                            -->
                            <div class="swiper-slide">
                                <img src="resources/img/거실.jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="https://swiperjs.com/demos/images/nature-2.jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="resources/img/오션뷰.jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="https://swiperjs.com/demos/images/nature-4.jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="resources/img/쿼드룸.jpg" />
                            </div>
                            <div class="swiper-slide">
                                <img src="https://swiperjs.com/demos/images/nature-6.jpg" />
                            </div>
                        </div> <!-- end: swiper-wrapper -->
                    </div><!-- end:thumbSlider -->
                    <!-- end:Swiper -->
                </div> <!-- end: modal-footer -->
            </div><!-- end:.modal-content -->
        </div> <!-- end:.modal-dialog -->
    </div> <!-- end: mainPhoto Modal -->

    <!-- mainPhoto Swiper -->
    <script src="<c:url value="/resources/js/initialize_mainSwiper.js" />"></script>
    <!-- heart button -->
    <script src="https://cdn.jsdelivr.net/npm/@mojs/core"></script>
    <script src="<c:url value="/resources/js/heart_button.js" />"></script>

</body>

</html>