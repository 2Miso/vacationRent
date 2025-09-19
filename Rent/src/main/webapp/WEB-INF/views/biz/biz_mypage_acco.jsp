<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>비즈니스 회원 - 숙소 관리</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <script src="<c:url value='/resources/js/jquery-3.7.1.min.js' />"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/color_orange.css" />">
    <style>
    body {
        --bs-font-sans-serif:margin:0; padding:0;font-size:14px;line-height:1.6;font-family:'Pretendard','Noto Sans KR', 'Apple SD Gothic Neo', '돋움', Dotum, Arial, Sans-serif;color:#464646;letter-spacing:0;-webkit-text-size-adjust:none;font-weight: 400
    }
    .swiper {
    	width:100%;
    	max-width:800px;
		height:200px;
	}
    .swiper-slide{
    	width:200px !important;
    	height:200px;
    	display: flex;
		justify-content: center;
		align-items: center;
    }
    .swiper-slide img {
		width: 200px;
		height: 200px;
		object-fit: cover;
		border-radius: 5px;
	}
	.swiper-button-next,
	.swiper-button-prev {
		top: 50% !important;
		transform: translateY(-50%);
	}   
    .active{
		--bs-nav-pills-link-active-bg:var(--bs-orange);
	}
    </style>
    <script>
      function accochangeFn(){
    	// 숙소 타입 유효성검사
        if($("#accoType").val() == ""){
          $("#accoType").next("span").text("숙소 타입을 입력해 주세요.").css("color","red");
          return false;
        }else{
          $("#accoType").next("span").text("");
        }

      	// 숙소 이름 유효성검사
        if($("#accoName").val() == ""){
          $("#accoName").next("span").text("숙소 이름을 입력해 주세요.").css("color","red");
          return false;
        }else{
          $("#accoName").next("span").text("");
        }

      	// 숙소 주소 유효성검사
        if($("#accoAddr").val() == ""){
          $("#accoAddr").next("span").text("숙소 주소를 입력해 주세요.").css("color","red");
          return false;
        }else{
          $("#accoAddr").next("span").text("");
        }
        
      	// 숙소 전화번호 유효성검사 
        if($("#accoPhone").val() == ""){
          $("#accoPhone").next("span").text("숙소 전화번호를 입력해 주세요.").css("color","red");
          return false;
        }else{
          $("#accoPhone").next("span").text("");
        }

     	// 숙소 정보 유효성검사
        if($("#accoInfo").val() == ""){
          $("#accoInfo").next("span").text("숙소 정보를 입력해 주세요.").css("color","red");
          return false;
        }else{
          $("#accoInfo").next("span").text("");
        }

     	// 숙소 상담 시간 유효성검사
        if($("#accoBizHour").val() == ""){
          $("#accoBizHour").next("span").text("상담 가능 시간을 입력해 주세요.").css("color","red");
          return false;
        }else{
          $("#accoBizHour").next("span").text("");
        }
     	
      	// 숙소 체크인 시간 유효성검사
        if($("#accoCheckin").val() == ""){
          $("#accoCheckin").next("span").text("체크인 시간을 입력해 주세요.").css("color","red");
          return false;
        }else{
          $("#accoCheckin").next("span").text("");
        }
      	
     	// 숙소 체크아웃 시간 유효성검사
        if($("#accoCheckout").val() == ""){
          $("#accoCheckout").next("span").text("체크아웃 시간을 입력해 주세요.").css("color","red");
          return false;
        }else{
          $("#accoCheckout").next("span").text("");
        }
     
     	// 숙소 사진 업로드 유효성검사
   
		let imageInput = document.getElementById("accoUpload");
		let files = imageInput.files;

		if (files.length == 0) {
		    $(imageInput).next("span").text("숙소 이미지를 등록해 주세요.").css("color", "red");
		    return false;
		}

		for (let i = 0; i < files.length; i++) {
		    if (!files[i].type.startsWith("image/")) {
		        $(imageInput).next("span").text("이미지 파일만 등록해 주세요.").css("color", "red");
		        return false;
		    }
		}

		$(imageInput).next("span").text("");
    
     	return true;
      }
      
      function deleteAccoFn(){
    	  
      }
      
    </script>
</head>


<body>
<section class="d-flex">
  <div><!--사이드바 시작-->
  <div class="d-flex" style="height:100vh;" >
      <div style="width: 280px;" class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" >
    <a class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
      <i class="bi bi-gear me-2" style="font-size:24px;"></i>
      <span class="fs-4" style="font-weight: bold;">비즈니스 회원</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item"> 
        <a href="#" class="nav-link active" aria-current="page"><!--숙소 관리 페이지로 링크 걸어야 합니다--> <!--aria current가 현재 표시되는 페이지를 강조합니다-->
          <i class="bi bi-house me-2  "></i>숙소 관리
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white"><!--객실 관리 페이지로 링크 걸어야 합니다-->
          <i class="bi bi-file me-2"></i>
          객실 관리
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white"><!--예약자 관리 페이지로 링크 걸어야 합니다-->
          <i class="bi bi-people me-2"></i>
          예약자 관리
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white"><!--계정 설정 페이지로 링크 걸어야 합니다-->
          <i class="bi bi-person-circle me-2"></i>
          계정 설정
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white">
          <i class="bi bi-question-circle me-2"></i>
          1대1 문의 확인
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
          
        <div><!--숙소 정보 등록-->
          <h3 class="fw-bold">숙소 관리</h3>
          <h4>숙소 등록</h4>
          <form action="<c:url value="/biz/biz_mypage_acco" />" method="post" enctype="multipart/form-data">
          <input type="hidden" name="bizId" value="${biz.id}" />
          <div>숙소 타입</div>
            <select class="form-control" id="accoType" name="type" <c:if test="${disableInput}">disabled</c:if>>
			  <option value="">-- 숙소 타입 선택 --</option>
			  <option value="0">호텔, 리조트</option>
			  <option value="1">모텔</option>
			  <option value="2">펜션</option>
			</select>
            <span style="display: inline-block;"></span>

          <div>숙소 이름</div>
            <input class="form-control" type="text" id="accoName" placeholder="숙소 이름을 입력해주세요." name="name" <c:if test="${disableInput}">disabled</c:if>>
            <span style="display: inline-block;"></span>
          
          <div>숙소 주소</div>
            <input class="form-control" type="text" id="accoAddr" placeholder="숙소 주소를 입력해주세요." name="addr" <c:if test="${disableInput}">disabled</c:if>>
            <span style="display: inline-block;"></span>

           
          <div>숙소 전화번호</div>
            <input class="form-control" type="text" id="accoPhone" placeholder="숙소 전화번호를 입력해주세요." name="phone" <c:if test="${disableInput}">disabled</c:if>>
            <span style="display: inline-block;"></span>

          
          <div>숙소 정보</div>
            <input class="form-control" type="text"id="accoInfo" placeholder="숙소 정보를 입력해주세요. 예) 숙소 장점이나 홍보등" name="description" <c:if test="${disableInput}">disabled</c:if>>
			<span style="display: inline-block;"></span>

          
          <div>상담가능시간</div>
            <input class="form-control" type="text" id="accoBizHour" placeholder="상담가능 시간을 입력해주세요." name="bizHour" <c:if test="${disableInput}">disabled</c:if>>
            <span style="display: inline-block;"></span>

          <div>운영 시간</div>
          <div class="d-flex">
              <div>
                <input class="form-control" type="text" id="accoCheckin" placeholder="입실 시간 예) 00:00" name="checkin" <c:if test="${disableInput}">disabled</c:if>>
                <span style="display: inline-block;"></span>
              </div>

              <div>
                <input class="form-control" type="text" id="accoCheckout" placeholder="퇴실 시간 예) 24:00" name="checkout" <c:if test="${disableInput}">disabled</c:if>>
                <span style="display: inline-block;"></span>
              </div>
          </div>
          <div>숙소 사진 첨부</div>
          <div>
	            <input class="form-control" id="accoUpload" type="file" accept="image/*" name="image" multiple <c:if test="${disableInput}">disabled</c:if> />
	            <span style="display: inline-block;"></span>
          </div>
          
			<br><button class="btn btn-primary " type="submit" onclick="return accochangeFn()">저장하기</button><!--링크를 걸어야 합니다-->
        </form>
        </div><!--숙소 정보 등록 끝-->
          
        <div class="mx-3" style="border-left:2px solid var(--bs-orange); padding-left:20px;"><!--이미지 업로드 or 확인-->
          <h3 class="fw-bold">등록된 숙소 관리</h3><!-- 숙소 수정 -->
          <h4>숙소 수정</h4>
			<form action="<c:url value="/biz/biz_mypage_acco" />" method="post" enctype="multipart/form-data">
          
          <div>숙소 타입</div>
            <input type="text" class="form-control" id="editType" placeholder="${type}" name="type">
            <span></span>
          
          <div>숙소 이름</div>
            <input type="text" class="form-control" id="editName" placeholder="${name}" name="name">
            <span></span>
          
          <div>숙소 주소</div>
            <input type="text" class="form-control" id="editAddr" placeholder="${addr}" name="addr">
            <span></span>
          
          <div>숙소 전화번호</div>
            <input type="text" class="form-control" id="editPhone" placeholder="${phone}" name="phone">
            <span></span>
          
          <div>숙소 정보</div>
            <input type="text" class="form-control" id="editInfo" placeholder="${description}" name="description">
            <span></span>
          
          <div>상담가능시간</div>
            <input type="text" class="form-control" id="editBizHour" placeholder="${biz_hour}" name="bizHour">
            <span></span>

          <div>운영 시간</div>
          <div class="d-flex">
              <div>
                <input type="text" class="form-control" id="editCheckin" placeholder="${checkin}" name="checkin">
                <span></span>
              </div>

           
              <div>
                <input type="text" class="form-control" id="editCheckout" placeholder="${checkout}" name="checkout">
                <span></span>
              </div>
          </div>
          <div class="selectAcco">
          	  <c:forEach var="acco" items="${accoList}">
	          	  <div>${acco.name}</div>
				  <div class="swiper mySwiper-${acco.accoNo}">
		              <div class="swiper-wrapper">
		              	<c:forEach var="img" items="${acco.photoList}">
		                  <div class="swiper-slide">
		                  	<img src="<c:url value='/resources/img/acco/${img.savedName}' />" alt="acco image" />
		                  </div>
		                </c:forEach>
		              </div>
		              <div class="swiper-button-next"></div>
		              <div class="swiper-button-prev"></div>
		          </div>
	          </c:forEach>
	          <button link="#" class="btn btn-primary " type="button">사진삭제</button> 
          </div>
          
          <div>숙소 사진 첨부(기존 사진 삭제 후 첨부해주세요.)</div>
          <div>
	            <input id="imageUpload" class="form-control" type="file" multiple accept="image/*" name="image">
	            <span></span>
          </div>
          
			<br><button class="btn btn-primary " type="submit" onclick="return accochangeFn()">수정하기</button><!--링크를 걸어야 합니다-->
        	<button class="btn btn-primary btn-delete-acco" type="button" onclick="deleteAccoFn()">삭제하기</button> 
        </form>
        </div>
          

  </div><!--내용 끝-->
</section>
</body>
</html>