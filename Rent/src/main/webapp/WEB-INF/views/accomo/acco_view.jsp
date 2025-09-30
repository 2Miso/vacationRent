<!-- URL : /acco/view/{accoNo} -->
<%@page import="com.rent.vaca.acco.AccoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../include/header_nosearchbar.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Rent - ${acco.name}</title>
<!-- 스와이퍼 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/mainSwiper.css" />">
<!-- 지도 -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=767914bf0cfc904dd9cb8b0fd6dd25bc&libraries=services"></script>
<!-- 하트 -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/heart_button.css" />">
<script src="https://cdn.jsdelivr.net/npm/@mojs/core"></script>
<style>
section {
	padding: 50px 0;
	width: 1024px;
	margin: 0 auto;
}

.mainPhoto .container {
	width: 1024px;
	height: 500px;
	position: relative;
}

.bigImage, .smallImage {
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

.morePhoto {
	display: inline-block;
	background: rgba(255, 255, 255, 0.451);
	border-radius: 5px;
	padding: 0 5px;
	position: absolute;
}

.mainPhoto .morePhoto {
	right: 30px;
	bottom: 15px;
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

.orangeContainer {
	border-radius: 15px;
	border: 3px solid var(--bs-orange);
	padding: 10px;
}

.subInfo>div {
	width: 30%;
	height: 150px;
}
.topOneReview{
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  white-space: normal;
  text-overflow: ellipsis;
  word-break:break-all;
}

.roomList {
	padding: 30px 0;
}

.room {
	margin-bottom: 10px;
}

.roomPhoto {
	padding: 0;
	border-radius: 15px;
	overflow: hidden;
	width:185px;
	height:185px;
}

.roomPhoto img {
	width: 100%;
	height: 100%;
	object-fit: cover;
	object-position: center center;
}

.room .morePhoto {
	position: relative;
	bottom: 30px;
	left: 145px;
}

.roomInfo {
	padding-left: 20px;
}

.roomInfo .row * {
	width: auto;
}

.roomName {
	font-size: 3em;
}

.cursorPointer:hover {
	cursor: pointer;
	font-weight: bold;
}

.roomDetail {
	position: relative;
}

.roomDetail>* {
	position: absolute;
	right: 20px;
}

.roomDetail>*:last-child {
	top: 30px;
	right: 25px;
}

.roomPrice {
	padding-top: 26px;
}
.roomSimpleInfo{
	white-space:normal;
	word-break:break-word;
}
hr {
	color: var(--bs-orange);
	border-top: 2px solid;
	opacity: 1;
	margin: 20px 0;
}

.owner_info table {
	margin: 0 auto;
	width: 100%;
}

.owner_info table, .owner_info table * {
	border: 1px solid black;
}

.owner_info tr>* {
	padding: 10px;
}

.owner_info th {
	text-align: end;
	background-color: #FFC067;
}

.owner_info h3 {
	margin: 0;
}

.reviewTitle {
	display: flex;
	justify-content: space-between;
	align-items: baseline;
}

.reviewList>div {
	padding: 10px;
	width: 100%;
	border-radius: 15px;
	border: 1px solid var(--bs-orange);
	margin-bottom:10px;
}

.reviewPhoto {
	position: relative;
}

.room .col-2, .reviewPhoto .col-2 {
	padding: 0;
	border-radius: 15px;
	height: 167px;
	overflow: hidden;
}

.room .col-2, .reviewPhoto img {
	width: 100%;
	height: 100%;
	object-fit: cover;
	object-position: center center;
}

.reviewPhoto .morePhoto {
	right: 75px;
	bottom: 10px;
}

.Page {
	padding-top: 20px;
}

footer {
	margin-top: 150px;
}
</style>
<script>
        $(function(){
        	//모달 탭 선택
            let navItem = $("#mainPhotoModal .nav-item");
            $(navItem).on("click", function(){
                navItem.children("a").removeClass("active");
                $(this).children("a").addClass("active");
            });
            

	        //상단 사진 클릭 시 모달 전경 탭 클릭 효과
	        $(".mainPhoto .imgContainer").on("click", function(){
	        	$(navItem[0]).click();
	        });
	        //객실사진 클릭 시 모달 객실탭 클릭 효과
	        $(".room .roomPhoto").on("click", function(){
	        	let tabIndex = $(this).parents(".roomList").find(".roomPhoto").index(this)+1;
	        	$(navItem[tabIndex]).click();
	        });
	        
	        //모달 탭에 따른 객실사진 조회
	        $("#mainPhotoModal .nav-item").on("click", function(){
        			$.ajax({
					//요청부분
					url : "<c:url value='/acco/photoModal' />",
					type : "get",
					data : {
						"accoNo" : ${acco.accoNo},
						"roomName" : $(this).find('.active').text()
					},
					
					//응답부분
					success : function(photoList){
						let modalBody = $("#mainPhotoModal .modal-body");
						$(modalBody).html(
							'<!-- Swiper -->'
							+'<div style="--swiper-navigation-color: #fff; --swiper-pagination-color: #fff" class="swiper mySwiper2">'
							    +'<div class="swiper-wrapper">'
					    );
						 
						$.each(photoList, function(index, photo){
							if(photo.savedName != null){
								$(".mySwiper2 .swiper-wrapper").append(
										'<div class="swiper-slide">'
								        	+'<img src="<c:url value="/resources/img/acco/" />' + photo.savedName + '" />'
									    +'</div>'
							    );
							}
						});
						$(modalBody).append(
							    '</div>'
							    +'<div class="swiper-button-next"></div>'
							    +'<div class="swiper-button-prev"></div>'
							  +'</div><!-- end: .mySwiper2 -->'
							  +'<div thumbsSlider="" class="swiper mySwiper">'
							    +'<div class="swiper-wrapper">'
						);
						
						
						$.each(photoList, function(index, photo){
							if(photo.savedName != null){
								$(".mySwiper .swiper-wrapper").append(
								      '<div class="swiper-slide">'
								        +'<img src="<c:url value="/resources/img/acco/" />' + photo.savedName + '" />'
								      +'</div>'
								);
							}
						});
						$(modalBody).append(
							    '</div><!-- end: .mySwiper -->'
							  +'</div><!-- end:Swiper -->'
						);
						swiperfn();
						
					},
					error : function(){
						console.log("사진모달 탭 사진 실패");
					}
				});
	        });
            
            //리뷰, 부대시설, 주소로 스크롤 이동
            $(".subInfo").children().on("click", function(){
                  $('html, body').animate({scrollTop:$(this.id).position().top}, 'fast');
            });

            //페이지 로딩 시 로그인 회원은 관심등록 조회 후 관심숙소면 빨간하트
            if(${not empty sessionScope.user}){
	            $.ajax({
						//요청부분
						url : "<c:url value='/mypage/interest' />",
						type : "get",
						data : {
							"userId" : "${sessionScope.user.id}",
							"accoNo" : ${acco.accoNo}
						},
						
						//응답부분
						success : function(response){
							if(response==true){
								$("#heart").addClass("active");
							}
						},
						error : function(){
							console.log("관심조회 에러");
						}
				});
            }
            
            //하트버튼 클릭 시 효과 생성
            var scaleCurve = mojs.easing.path('M0,100 L25,99.9999983 C26.2328835,75.0708847 19.7847843,0 100,0');
		    var el = document.querySelector('.button'),
		        // mo.js timeline obj
		        timeline = new mojs.Timeline(),
		
		        // tweens for the animation:
		
		        // burst animation
		        tween1 = new mojs.Burst({
		            parent: el,
		            radius: { 0: 100 },
		            angle: { 0: 45 },
		            y: -10,
		            count: 10,
		            radius: 50,
		            children: {
		                shape: 'circle',
		                radius: 15,
		                fill: ['red', 'white'],
		                strokeWidth: 15,
		                duration: 500,
		            }
		        });
		
		
		    tween2 = new mojs.Tween({
		        duration: 900,
		        onUpdate: function (progress) {
		            var scaleProgress = scaleCurve(progress);
		            el.style.WebkitTransform = el.style.transform = 'scale3d(' + scaleProgress + ',' + scaleProgress + ',1)';
		        }
		    });
		    tween3 = new mojs.Burst({
		        parent: el,
		        radius: { 0: 100 },
		        angle: { 0: -45 },
		        y: -10,
		        count: 10,
		        radius: 62,
		        children: {
		            shape: 'circle',
		            radius: 15,
		            fill: ['white', 'red'],
		            strokeWidth: 15,
		            duration: 400,
		        }
		    });
		
		    // add tweens to timeline:
		    timeline.add(tween1, tween2, tween3);
            
            //하트 클릭 시 관심에 등록 또는 제거
            $(".heartContainer").on("click", function(){
            	if(${empty sessionScope.user}){
            		alert('로그인한 회원만 관심숙소를 등록할 수 있습니다.');
            	} else {
	 				$.ajax({
						//요청부분
						url : "<c:url value='/mypage/interest' />",
						type : "post",
						data : {
							"userId" : "${sessionScope.user.id}",
							"accoNo" : ${acco.accoNo}
						},
						
						//응답부분
						success : function(response){
							if(response==1){
								timeline.play();
								$("#heart").addClass("active");
							} else if(response==0){
								$("#heart").removeClass("active");
							}
						},
						error : function(){
							console.log("관심버튼 클릭 에러");
						}
					});
            	}
            });
            
            //카카오지도
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
	        //리뷰 정렬기준 선택
	        $("#review_order").on("change", function(){
				let param = $("#review_order option:selected").val();
				$(".reviewListContainer").load("<c:url value='/accomo/reviewList/${acco.accoNo}' />?orderBy="+param);
	        });
        });/* document.ready 끝 */
        
		 function swiperfn(){
			    var swiper = new Swiper(".mySwiper", {
			      spaceBetween: 10,
			      slidesPerView: 4,
			      freeMode: true,
			      watchSlidesProgress: true,
			    });
			    var swiper2 = new Swiper(".mySwiper2", {
			      spaceBetween: 10,
			      navigation: {
			        nextEl: ".swiper-button-next",
			        prevEl: ".swiper-button-prev",
			      },
			      thumbs: {
			        swiper: swiper,
			      },
			    });
			  }
    </script>
</head>

<body>
	<section>
		<article class="mainPhoto">
			<div class="container text-center">
				<div class="row">
					<div class="col bigImage">
						<div class="imgContainer" data-bs-toggle="modal"
							data-bs-target="#mainPhotoModal">
							<img src="<c:url value="/resources/img/acco/" />${topPhotos[0].savedName}" alt="">
						</div>
					</div>
					<div class="col smallImage">
						<div class="row">
							<div class="col">
								<div class="imgContainer" data-bs-toggle="modal"
									data-bs-target="#mainPhotoModal">
									<img src="<c:url value="/resources/img/acco/" />${topPhotos[1].savedName}" alt="">
								</div>
							</div>
							<div class="col">
								<c:if test="${topPhotos[2].savedName != null }">
								<div class="imgContainer" data-bs-toggle="modal"
									data-bs-target="#mainPhotoModal">
									<img src="<c:url value="/resources/img/acco/" />${topPhotos[2].savedName}" alt="">
								</div>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<c:if test="${topPhotos[3].savedName != null }">
									<div class="imgContainer" data-bs-toggle="modal"
										data-bs-target="#mainPhotoModal">
										<img src="<c:url value="/resources/img/acco/" />${topPhotos[3].savedName}" alt="">
									</div>
								</c:if>
							</div>
							<div class="col">
								<c:if test="${topPhotos[4].savedName != null }">
									<div class="imgContainer" data-bs-toggle="modal"
										data-bs-target="#mainPhotoModal">
										<img src="<c:url value="/resources/img/acco/" />${topPhotos[4].savedName}" alt="">
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<!-- end: .row -->
				<c:if test="${topPhotos[4].savedName != null }">
				<div class="morePhoto" data-bs-toggle="modal" data-bs-target="#mainPhotoModal">+n</div>
				</c:if>
			</div>
			<!-- end: .container -->
		</article>
		<div class="titleArea">
			<div>
				<h6>${acco.typeKo}</h6>
				<h2>${acco.name}</h2>
			</div>
			<div class="heartContainer">
				<div id='heart' class='button'></div>
			</div>
		</div>
		<article class="subInfo">
			<div id="#reviewArea" class="orangeContainer">
				<div class="orangeContainer" style="display:inline-block; border-width:1px;">
					<span style="color:var(--bs-orange);">✮</span>${starAvg}
				</div> ${reviewCount}명 평가
				<div class="topOneReview">
					<c:out value="${reviewList[0].content}" />
				</div>
			</div>
			<div id="#facil" class="orangeContainer">서비스 및 부대시설</div>
			<div id="#location" class="orangeContainer">
				주소><br>
				<br>${acco.addr}</div>
		</article>
		<article class="roomList">
			<h3>객실선택</h3>
			<c:forEach var="room" items="${acco.roomList}" varStatus="cnt">
				<div class="room container orangeContainer row">
					<div class="col-3 roomPhoto" data-bs-toggle="modal"
						data-bs-target="#mainPhotoModal">
							<img src="<c:url value="/resources/img/acco/" />${room.thumbnailImage}" alt="">
						<div class="morePhoto" data-bs-toggle="modal"
							data-bs-target="#mainPhotoModal">+n</div>
					</div>
					<div class="col roomInfo align-content-between">
						<h4 class="row roomName">${room.name}</h4>
						<div class="row roomDetail">
							<span class="cursorPointer" data-bs-toggle="modal"
								data-bs-target="#roomModal${cnt.count}">상세정보&gt;</span><br>
							<c:if test="${not empty sessionScope.user}">
								<button class="btn btn-primary reserv-btn" onclick="location.href='/Rent/reservation/reserv_ok_payment?accoNo=${acco.accoNo}&roomNo=${room.roomNo}'">예약하기</button>
							</c:if>
						</div>
						<!-- Modal -->
						<div class="modal fade" id="roomModal${cnt.count}" tabindex="-1"
							aria-labelledby="roomModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="roomModalLabel">${room.name}</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<h4>객실정보</h4>
										<div>체크인 ${acco.checkin} / 체크아웃 ${acco.checkout}</div>
										<div>기준인원 ${room.standardHead}명 / 최대인원
											${room.standardHead + room.extraHead}명</div>
										<div>${room.bedType}</div>
										<div>화장실 ${room.restroomNo}개, ${room.description} /
											${room.area}㎡</div>
										<hr>
										<h4>추가정보</h4>
										<div></div>
										<hr>
										<h4>편의시설</h4>
										<div>TV, 전기포트, 금고, 에어컨, 냉장고, 와이파이</div>
									</div>
									<!-- end:movda-body -->
								</div>
								<!-- end: modal-content -->
							</div>
							<!-- end: modal-dialog -->
						</div>
						<!-- end:Modal -->
						<div class="row roomPrice">
							<strong><fmt:formatNumber value="${room.price}" type="number" pattern="#,##0" /></strong>원
						</div>
						<div class="row roomSimpleInfo">
							기준 ${room.standardHead}인 / 추가 ${room.extraHead}인<br>
							${room.description}
						</div><!-- end:.roomSimpleInfo -->
					</div><!-- end:.roomInfo -->
				</div> <!-- end:room -->
			</c:forEach>
		</article>
		<!-- end: .roomList -->
		<hr>
		<article class="acco_intro">
			<h3>숙소소개</h3>
			<div>${acco.description}</div>
		</article>
		<!-- end:acco_intro -->
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
			<h3 style="display: inline-block; cursor: pointer;"
				data-bs-toggle="modal" data-bs-target="#ownerModal">판매자정보 &gt;</h3>
			<!-- Modal -->
			<div class="modal fade" id="ownerModal" tabindex="-1"
				aria-labelledby="ownerModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="ownerModalLabel">판매자 정보</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
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
							</table>
							<br>
							<ul>
								<li>판매자 정보는 판매자의 명시적 동의 없이 영리 목적의 마케팅, 광고 등에 활용할 수 없습니다.<br>
									이를 어길 시 정보통신망법 등 관련 법령에 의거하여 과태료 부과 및 민형사상 책임을 지게 될 수 있습니다.
								</li>
							</ul>
						</div>
						<!-- end:movda-body -->
					</div>
					<!-- end: modal-content -->
				</div>
				<!-- end: modal-dialog -->
			</div>
			<!-- end:Modal -->
		</div>
		<!-- end:.owner_info -->
		<hr>
		<article id="location">
			<h3>위치</h3>
			<div>${acco.addr}</div>
			<div id="map"
				style="border: 1px solid black; width: 1024px; height: 426px;">
			</div>
		</article>
		<hr>
		<article id="reviewArea">
			<div class="reviewTitle">
				<h3 style="font-size: 1em;">★리뷰 ${starAvg} ${reviewCount}개</h3>
				<select id="review_order">
					<option value="newest" selected>최신순</option>
					<option value="highest">평점 높은 순</option>
					<option value="lowest">평점 낮은 순</option>
				</select>
			</div>
			<div class="reviewListContainer">
			<!-- start:reviewList -->
			<%@ include file="reviewList.jsp" %>
			<!-- end:reviewList -->
			</div>
			<!--<div class="Page">
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link" href="#">&lt;</a></li>
					<li class="page-item active"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item disabled"><a class="page-link" href="#">&gt;</a></li>
				</ul>
			</div> end:Page -->
		</article>
		<!-- end:#reviewArea -->
	</section>
	<!-- mainPhoto Modal -->
	<div class="modal fade" id="mainPhotoModal" tabindex="-1"
		aria-labelledby="mainPhotoModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="mainPhotoModalLabel">${acco.name}</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="photo_type_tab">
					<ul class="nav nav-tabs">
						<li class="nav-item"><a class="nav-link active">전경</a></li>
						<c:forEach var="room" items="${acco.roomList}">
						<li class="nav-item"><a class="nav-link">${room.name}</a>
						</li>
						</c:forEach>
					</ul>
				</div>
				<!-- end:.modal-header -->
				<div class="modal-body">
					<!-- Swiper -->
				  <div style="--swiper-navigation-color: #fff; --swiper-pagination-color: #fff" class="swiper mySwiper2">
				    <div class="swiper-wrapper">
				    	<c:forEach var="photo" items="${acco.photoList}">
				    		<c:if test="${not empty photo.savedName}">
						      <div class="swiper-slide">
						        <img src="<c:url value="/resources/img/acco/${photo.savedName}" />" />
						      </div>
						     </c:if>
				      	</c:forEach>
				    </div>
				    <div class="swiper-button-next"></div>
				    <div class="swiper-button-prev"></div>
				  </div><!-- end: .mySwiper2 -->
				  <div thumbsSlider="" class="swiper mySwiper">
				    <div class="swiper-wrapper">
				    	<c:forEach var="photo" items="${acco.photoList}">
				    		<c:if test="${not empty photo.savedName}">
						      <div class="swiper-slide">
						        <img src="https://swiperjs.com/demos/images/nature-1.jpg" />
						      </div>
						     </c:if>
				      	</c:forEach>
				    </div><!-- end: .mySwiper -->
				  </div><!-- end:Swiper -->
				</div> <!-- end: modal-body -->
				<div class="modal-footer">
				</div> <!-- end: modal-footer -->
			</div> <!-- end:.modal-content -->
		</div> <!-- end:.modal-dialog -->
	</div> <!-- end: mainPhoto Modal -->

	<!-- mainPhoto Swiper -->
	  <!-- Swiper JS -->
	  <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	
	  <!-- Initialize Swiper -->
	  <script>
	    var swiper = new Swiper(".mySwiper", {
	      spaceBetween: 10,
	      slidesPerView: 4,
	      freeMode: true,
	      watchSlidesProgress: true,
	    });
	    var swiper2 = new Swiper(".mySwiper2", {
	      spaceBetween: 10,
	      navigation: {
	        nextEl: ".swiper-button-next",
	        prevEl: ".swiper-button-prev",
	      },
	      thumbs: {
	        swiper: swiper,
	      },
	    });
	  </script>

</body>
</html>
<%@include file="../include/footer.jsp"%>