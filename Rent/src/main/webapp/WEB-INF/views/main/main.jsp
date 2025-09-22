<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header_nosearchbar.jsp" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>메인페이지</title>
    <link href="size.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/@xpressengine/xeicon@2.3.3/xeicon.min.css" rel="stylesheet">
    
    <style>
        body {
            --bs-font-sans-serif:margin:0; padding:0;font-size:14px;line-height:1.6;font-family:'Pretendard','Noto Sans KR', 'Apple SD Gothic Neo', '돋움', Dotum, Arial, Sans-serif;color:#464646;letter-spacing:0;-webkit-text-size-adjust:none;font-weight: 400
        }
    </style>

    <script>
    	$(function(){
    		//체크박스 전체 선택시 모든 체크박스 선택 또는 해제
    		$("#everything").change(function(){
    	        if ($(this).is(':checked')) {
    	        	$(".form-check-input").prop('checked', true);
    	        } else {
    	        	$(".form-check-input").prop('checked', false);
    	        }
    		});
    		
    	});
    
    	let category = false;
        function categorychangeFn(obj){
            if(category == false){
                $(obj).text("카테고리 접기");
                category=true;
            }else{
                $(obj).text("카테고리 펼치기")
                category=false;
            }
        }
    </script>
</head>

<body>
<section>
<div class="main_search" style="position:relative;">
    <div><img src="<c:url value="/resources/img/main.png" />" style="filter: brightness(0.5); width:100vw; height:500px; object-fit:cover;"></div><!--이미지-->

    <div style="position:absolute; top:150px; width:100vw">
        <div style="margin:0 auto; width:1024px; height:140px; background-color: white; border-radius:6px; "><!--내용물-->
            <div style="height:10px;"></div><!--여백-->
            
            <h3 class="fw-bold mx-2 my-2">검색하기</h3><!--검색하기 시작-->
            <form action="<c:url value="/search" />" method="get">
            <div class="d-flex  justify-content-around " style="height:50px;">
                <input name="text" type="text" class="form-control " placeholder="검색하기" style="width:350px; height:50px;">
                <div class="d-flex align-items-center">
                <input name="checkIn" type="date" class="form-control" placeholder="일정(부터)" style="width:150px; height:50px;">-
                <input name="checkOut" type="date" class="form-control" placeholder="일정(까지)" style="width:150px; height:50px;">
                </div>
                <input name="head" type="text" class="form-control " placeholder="인원(명)" style="width:100px; height:50px;">
                <button class="btn btn-primary " type="submit" style="width:200px; height:50px;">검색하기</button>
            </div><!--검색하기 끝-->

            <div class="collapse" id="collapseExample" ><!--카테고리 열고 접기-->
                <div class="d-flex align-items-center" style="height:150px;  background-color: white;"><!--카테고리 내용물 시작-->
                    <div class="mx-2"><!--가격 카테고리 시작-->
                        <h3 class="fw-bold my-3">가격</h3>
                        <div class="d-flex align-items-center ">
                            <input name="priceLow" type="text" class="form-control" placeholder="최저" style="width:150px;">-
                            <input name="priceHigh" type="text" class="form-control" placeholder="최대" style="width:150px;">
                        </div>
                    </div><!--가격 카테고리 끝-->
                    
                    <div class="mx-2"><!--숙소이름 카테고리 시작-->
                        <h3 class="fw-bold my-3">숙소유형</h3>
                        <div class="d-flex">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="everything">
                                <label class="form-check-label">전체</label>
                            </div>

                            <div class="form-check mx-2">
                                <input name="type" class="form-check-input" type="checkbox" value="1" id="hotel">
                                <label class="form-check-label">호텔</label>
                            </div>

                            <div class="form-check mx-2">
                                <input name="type" class="form-check-input" type="checkbox" value="2" id="motel">
                                <label class="form-check-label">모텔</label>
                            </div>

                            <div class="form-check mx-2">
                                <input name="type" class="form-check-input" type="checkbox" value="3" id="resort">
                                <label class="form-check-label">리조트</label>
                            </div>

                            <div class="form-check mx-2">
                                <input name="type" class="form-check-input" type="checkbox" value="4" id="cottage">
                                <label class="form-check-label">펜션</label>
                            </div>
                        </div>
                    </div><!--숙소이름 카테고리끝-->
                    
                </div><!--카테고리 내용물 끝-->
                </form>
            </div><!--카테고리 열고 접기 끝-->

            <div style="background-color: white; height:10px;"></div><!--카테고리 펼치기 시작-->
            <div class="align-items-center" style="height:30px; background-color: white; border-bottom-left-radius:6px; border-bottom-right-radius:6px; ">
                <p class="d-flex justify-content-center">
                    <i class="bi bi-list"></i>
                    <a style="color:black; text-decoration: none;" data-bs-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample" onclick="categorychangeFn(this)">카테고리 펼치기</a>
                </p>
            </div><!--카테고리 펼치기 끝-->
            
        </div>
    </div>
</div>

</div>
</section>
</body>
</html>
<%@include file="../include/footer.jsp" %>