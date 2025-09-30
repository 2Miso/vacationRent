<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 스와이퍼 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<style>
	.review .modal-body{
        position:relative;
    }

    .review .modal-body {
      background: #000;
      font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
      font-size: 14px;
      color: #fff;
      margin: 0;
      padding: 0;
    }

    .review .swiper {
      width: 100%;
      height: 100%;
    }

    .review .swiper-slide {
      text-align: center;
      font-size: 18px;
      background: #444;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .review .swiper-slide img {
      display: block;
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
	
</style>
</head>
<body>
	<div class="reviewList">
				<c:forEach var="review" items="${reviewList}" varStatus="cnt">
				<c:if test="${reviewCount != 0}">
					<div class="review">
						<div>${review.author.nickname}</div>
						<div>
							<span style="color: orange;">
								<c:forEach var="i" begin="1" end="${review.star}">
								★
								</c:forEach>
								<c:forEach var="i" begin="${review.star}" end="4">
								☆
								</c:forEach>
							</span> ${review.wdate}
						</div>
						<c:if test="${not empty review.photos[0].savedName}">
								<div class="reviewPhoto">
									<div class="row justify-content-evenly">
									<c:forEach var="photo" items="${review.photos}" begin="0" end="3">
										<div class="col-2" data-bs-toggle="modal"
											data-bs-target="#reviewPhotoModal${cnt.count}">
											<img src="<c:url value="/resources/img/acco/" />${photo.savedName}" alt="">
										</div>
									</c:forEach>
									</div>
									<!-- end:.row -->
									<%-- <div class="morePhoto" data-bs-toggle="modal"
										data-bs-target="#reviewPhotoModal${cnt.count}">+n</div> --%>
								</div>
								<!-- end:.reviewPhoto -->
						<!-- Modal -->
						<div class="modal fade" id="reviewPhotoModal${cnt.count}" tabindex="-1"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered  modal-xl">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<!-- Swiper -->
										<div class="swiper reviewSwiper${cnt.count}">
											<div class="swiper-wrapper">

												<!-- 
	                                        swiper-slide클래스에 swiper-slide-prev, swiper-slide-active, swiper-slide-next 클래스 추가/삭제
	                                        -->
	                                        <c:forEach var="photo" items="${review.photos}">
												<div class="swiper-slide">
													<img src="<c:url value="/resources/img/acco/" />${photo.savedName}" alt="">
												</div>
											</c:forEach>
											</div>
											<div class="swiper-button-next"></div>
											<div class="swiper-button-prev"></div>
											<div class="swiper-pagination"></div>
										</div>
										<!-- Initialize Swiper -->
										 <!-- Swiper JS -->
										<script>
	                                        var swiper = new Swiper(".reviewSwiper${cnt.count}", {
	                                        pagination: {
	                                            el: ".swiper-pagination",
	                                            type: "fraction",
	                                        },
	                                        navigation: {
	                                            nextEl: ".swiper-button-next",
	                                            prevEl: ".swiper-button-prev",
	                                        }
	                                        });
	                                    </script>
									</div>
									<!-- end:modal-body -->
								</div>
								<!-- end: .modal-content -->
							</div>
							<!-- end: .modal-dialog -->
						</div>
						<!-- end: .modal -->
						<!-- end:Modal -->
						</c:if>
						<div class="reviewText">
							${review.content}
						</div>
						<!-- end:.reviewText -->
					</div>
					<!-- end:.review -->
				</c:if>
				</c:forEach>
			</div>
</body>
</html>