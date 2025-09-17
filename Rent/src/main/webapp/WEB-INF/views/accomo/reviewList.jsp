<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
						<div class="reviewPhoto">
							<div class="row justify-content-evenly">
								<div class="col-2" data-bs-toggle="modal"
									data-bs-target="#reviewPhotoModal">
									<img src="resources/img/거실.jpg" alt="">
								</div>
								<div class="col-2" data-bs-toggle="modal"
									data-bs-target="#reviewPhotoModal${cnt.count}">
									<img src="resources/img/오션뷰.jpg" alt="">
								</div>
								<div class="col-2" data-bs-toggle="modal"
									data-bs-target="#reviewPhotoModal">
									<img src="resources/img/쿼드룸.jpg" alt="">
								</div>
								<div class="col-2" data-bs-toggle="modal"
									data-bs-target="#reviewPhotoModal">
									<img src="resources/img/쿼드룸.jpg" alt="">
								</div>
							</div>
							<!-- end:.row -->
							<div class="morePhoto" data-bs-toggle="modal"
								data-bs-target="#reviewPhotoModal">+n</div>
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
										<div class="swiper reviewSwiper">
											<div class="swiper-wrapper">

												<!-- 
	                                        swiper-slide클래스에 swiper-slide-prev, swiper-slide-active, swiper-slide-next 클래스 추가/삭제
	                                        -->

												<div class="swiper-slide">
													<img src="resources/img/거실.jpg" alt="">
												</div>
												<div class="swiper-slide">Slide 2</div>
												<div class="swiper-slide">
													<img src="resources/img/오션뷰.jpg" alt="">
												</div>
												<div class="swiper-slide">Slide 4</div>
												<div class="swiper-slide">
													<img src="resources/img/쿼드룸.jpg" alt="">
												</div>
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
									</div>
									<!-- end:movda-body -->
								</div>
								<!-- end: .modal-content -->
							</div>
							<!-- end: .modal-dialog -->
						</div>
						<!-- end: .modal -->
						<!-- end:Modal -->
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