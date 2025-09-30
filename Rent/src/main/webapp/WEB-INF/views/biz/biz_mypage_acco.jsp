<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/color_orange.css" />">
    <style>
    body {
        --bs-font-sans-serif:margin:0; padding:0;font-size:14px;line-height:1.6;font-family:'Pretendard','Noto Sans KR', 'Apple SD Gothic Neo', '돋움', Dotum, Arial, Sans-serif;color:#464646;letter-spacing:0;-webkit-text-size-adjust:none;font-weight: 400
    }
    .swiper {
    	margin:20px 0 20px 0;
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
    
		alert("숙소가 등록되었습니다.");
        $(".edit-field").prop("disabled", false);
		
     	return true;
      }
      
      let photoDeleted = false;
      
      function editchangeFn(){

    	    let imageInput = document.getElementById("editFile");
    	    let files = imageInput.files;
    		let hasNewPhotos = files.length > 0;
    		let existingPhotoCount = parseInt(document.getElementById("existingPhotoCount").value);
    		
		    // 기존 값과 현재 값 비교
		    let isEdited = false;
		
		    $("#editType, #editName, #editAddr, #editPhone, #editInfo, #editBizHour, #editCheckin, #editCheckout").each(function () {
		        let original = $(this).data("original");
		        let current = $(this).val().trim();
		        if (original != current) {
		            isEdited = true;
		        }
		    });
		    
		    // 기존 사진 삭제 + 새 사진 업로드 시 수정
		    if (!isEdited && photoDeleted && hasNewPhotos) {
		    	if (existingPhotoCount > 0) {
		            alert("기존 숙소 사진이 존재합니다. 사진을 먼저 삭제해주세요.");
		            return false;
		        } else {
		            return true; // 기존 사진 없으면 등록 가능
		        }
		    }
		
		    if (photoDeleted && !hasNewPhotos) {
		        alert("사진을 삭제한 경우, 새 이미지를 반드시 업로드해야 합니다.");
		        return false;
		    }
		    
		    if (!isEdited && !photoDeleted && !hasNewPhotos) {
		        alert("수정할 내용을 입력하세요.");
		        return false;
		    }
  
    	    return true;
      }

    	    
      
      function deleteAccoFn(){
    	  let hasEmpty = false;
    	  
    	  $("#editType, #editName, #editAddr, #editPhone, #editInfo, #editBizHour, #editCheckin, #editCheckout").each(function () {
    		  if (!$(this).val() || $(this).val().trim() === "") {
    	            hasEmpty = true;
    	            return false; // 하나라도 비어있으면 루프 종료
    	        }
    	  });
    	  
    	  if (hasEmpty) {
    	        alert("숙소 정보가 없습니다."); 
    	        return; 
    	  }
    	  
    	  if (!confirm("정말 숙소 정보를 삭제하시겠습니까?")) return;
    	   
    	  $.ajax({
    	        url: "<c:url value='/biz/delete_acco'/>",
    	        type: "POST",
    	        data: { accoNo: $("#editAccoNo").val() }, // hidden input
    	        success: function () {
    	            alert("숙소 정보가 삭제되었습니다.");

    	    	    // 수정폼 비우고 비활성화
    	    	    $(".edit-field").val("").prop("disabled", true);

    	            $("#editImagePreviewContainer").empty();
    	        },
    	        error: function () {
    	            alert("삭제 중 오류가 발생했습니다.");
    	        }
    	    });
      }
      
      function deleteAccoPhotoFn(){
    	  
    	  let hasEmpty = false;
    	  $("#editType, #editName, #editAddr, #editPhone, #editInfo, #editBizHour, #editCheckin, #editCheckout").each(function () {
    		  if (!$(this).val() || $(this).val().trim() === "") {
    	            hasEmpty = true;
    	            return false; // 하나라도 비어있으면 루프 종료
    	        }
    	  });
    	  
    	  if (hasEmpty) {
	  	        alert("숙소 정보가 없습니다."); // 경고 메시지
	  	        return false; // 삭제 막기
	  	  }
	  	  
	  	  if (!confirm("정말 숙소 사진을 삭제하시겠습니까?")) return false;

    	  $.ajax({
    	    url: "<c:url value='/biz/delete_acco_photo' />",
    	    type: "POST",
    	    data: { accoNo: "${acco.accoNo}" },
    	    success: function() {
    	      alert("사진이 삭제되었습니다.");
    	      photoDeleted = true;
    	      $("#existingPhotoCount").val(0);
    	      // 필요한 경우 UI 갱신 (사진 목록 갱신 등)
    	    },
    	    error: function() {
    	      alert("사진 삭제에 실패했습니다.");
    	    }
    	  });
	  	  return true;
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
        <a href="<c:url value="/biz/biz_mypage_acco" />" class="nav-link active" aria-current="page"><!--숙소 관리 페이지로 링크 걸어야 합니다--> <!--aria current가 현재 표시되는 페이지를 강조합니다-->
          <i class="bi bi-house me-2  "></i>숙소 관리
        </a>
      </li>
      <li>
        <a href="<c:url value="/biz/biz_mypage_room" />" class="nav-link text-white"><!--객실 관리 페이지로 링크 걸어야 합니다-->
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
        <a href="<c:url value="/biz/biz_mypage_account" />" class="nav-link text-white"><!--계정 설정 페이지로 링크 걸어야 합니다-->
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
      <a href="<c:url value='/'/>" class="nav-link text-white">
          <i class="bi bi-house-fill"></i><!--홈으로 이동하는 페이지로 링크 걸어야 합니다-->
          홈으로
        </a>
    </div>
    </div>
    </div>
  </div><!--사이드바 끝-->

  <div class="d-flex mx-3 my-3"><!--내용 시작입니다. 이곳에 내용을 작성하면 됩니다-->
          
        <div style="min-width:400px;"><!--숙소 정보 등록-->
          <h3 class="fw-bold">숙소 관리</h3>
          <h4>숙소 등록</h4>
          <form action="<c:url value="/biz/biz_mypage_acco" />" method="post" enctype="multipart/form-data">
          <input type="hidden" name="bizId" value="${biz.id}" />
          <div>숙소 타입</div>
            <select class="form-select" id="accoType" name="type" <c:if test="${disableInput}">disabled</c:if>>
			  <option value="">-- 숙소 타입 선택 --</option>
			  <option value="1">호텔</option>
			  <option value="2">모텔</option>
			  <option value="3">리조트</option>
			  <option value="4">펜션</option>
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
			<div id="imagePreviewContainer" style="margin-top: 10px; display: flex; gap: 10px; flex-wrap: wrap;"></div>
			
			<!-- 대표 이미지 인덱스를 서버로 보내기 위한 hidden input -->
			<input type="hidden" id="mainImageIndex" name="mainImageIndex" value="${mainImageIndex}">
			<br><button class="btn btn-primary " type="submit" onclick="return accochangeFn()">저장하기</button><!--링크를 걸어야 합니다-->
        </form>
        </div><!--숙소 정보 등록 끝-->
        <input type="hidden" name="accoNo" value="${acco.accoNo}" />
        <div class="mx-3" style="min-width:400px; border-left:2px solid var(--bs-orange); padding-left:20px;"><!--이미지 업로드 or 확인-->
          <h3 class="fw-bold">등록된 숙소 관리</h3><!-- 숙소 수정 -->
          <h4>숙소 수정</h4>
			<form action="<c:url value="/biz/edit_acco" />" method="post" enctype="multipart/form-data">
           	<input type="hidden" name="bizId" value="${biz.id}" />
           	<input type="hidden" name="accoNo" value="${acco.accoNo}" />
          <div>숙소 타입</div>
            <select class="form-select" id="editType" name="type"
            data-original="${acco.type}"
            <c:if test="${empty acco.type}">disabled</c:if>>
			  <option value="">-- 숙소 타입 선택 --</option>
			  <option value="1" <c:if test="${acco.type == 1}">selected</c:if>>호텔</option>
			  <option value="2" <c:if test="${acco.type == 2}">selected</c:if>>모텔</option>
			  <option value="3" <c:if test="${acco.type == 3}">selected</c:if>>리조트</option>
			  <option value="4" <c:if test="${acco.type == 4}">selected</c:if>>펜션</option>
			</select>
            <span></span>
          
          <div>숙소 이름</div>
            <input type="text" class="form-control"
            id="editName" value="${empty acco ? '' : acco.name}"
            data-original="${empty acco ? '' : acco.name}" name="name"
            <c:if test="${empty acco.name}">disabled</c:if>>
            <span></span>
          
          <div>숙소 주소</div>
            <input type="text" class="form-control"
            id="editAddr" value="${empty acco ? '' : acco.addr}"
            data-original="${empty acco ? '' : acco.addr}" name="addr"
            <c:if test="${empty acco.addr}">disabled</c:if>>
            <span></span>
          
          <div>숙소 전화번호</div>
            <input type="text" class="form-control" id="editPhone"
            value="${empty acco ? '' : acco.phone}"
            data-original="${empty acco ? '' : acco.phone}" name="phone"
            <c:if test="${empty acco.phone}">disabled</c:if>>
            <span></span>
          
          <div>숙소 정보</div>
            <input type="text" class="form-control" id="editInfo"
            value="${empty acco ? '' : acco.description}"
            data-original="${empty acco ? '' : acco.description}"
            name="description"
            <c:if test="${empty acco.description}">disabled</c:if>>
            <span></span>
          
          <div>상담가능시간</div>
            <input type="text" class="form-control" id="editBizHour"
            value="${empty acco ? '' : acco.bizHour}"
            data-original="${empty acco ? '' : acco.bizHour}"
            name="bizHour"
            <c:if test="${empty acco.bizHour}">disabled</c:if>>
            <span></span>

          <div>운영 시간</div>
          <div class="d-flex">
              <div>
                <input type="text" class="form-control" id="editCheckin"
                value="${empty acco ? '' : acco.checkin}"
                data-original="${empty acco ? '' : acco.checkin}"
                name="checkin"
                <c:if test="${empty acco.checkin}">disabled</c:if>>
                <span></span>
              </div>

           
              <div>
                <input type="text" class="form-control" id="editCheckout"
                value="${empty acco ? '' : acco.checkout}"
                data-original="${empty acco ? '' : acco.checkout}"
                name="checkout"
                <c:if test="${empty acco.checkout}">disabled</c:if>>
                <span></span>
              </div>
          </div>
          
          <div>숙소 사진 첨부(기존 사진 삭제 후 첨부해주세요.)</div>
          <div>
	            <input id="editFile" class="form-control" type="file"
	            multiple accept="image/*" name="image"
	            <c:if test="${empty acco.name}">disabled</c:if>>
	            <span></span>
          </div>
          
          	<!-- 기존 + 새 사진 미리보기 영역 -->
			<div class="edit-field" id="editImagePreviewContainer" style="margin-top: 10px; display: flex; gap: 10px; flex-wrap: wrap;"></div>

			<!-- 기존 사진 개수 hidden -->
			<input type="hidden" id="existingPhotoCount" value="${fn:length(accoList)}">
       
			<br><button class="btn btn-primary " type="submit" onclick="return editchangeFn()">수정하기</button><!--링크를 걸어야 합니다-->
        </form>
        
        <form action="<c:url value='/biz/delete_acco' />" style="position:relative; bottom:37.5px; left:100px;" method="post">
        	<input type="hidden" name="accoNo" value="${acco.accoNo}" />
        	<button class="btn btn-primary btn-delete-acco" type="button" onclick="return deleteAccoFn()">삭제하기</button> 
        </form>
        
        <form action="<c:url value="/biz/delete_acco_photo" />" method="post">
         <input type="hidden" name="accoNo" value="${acco.accoNo}" />
         
         <div class="selectAcco">
				  <div class="swiper mySwiper">
		              <div class="swiper-wrapper">
		              	<c:forEach var="img" items="${accoList}" varStatus="status">
		                  <div class="swiper-slide" style="position:relative;">
		                  	<c:url var="imageUrl" value="/resources/img/acco/" />
		                  	<img src="${imageUrl}${img.savedName}" alt="숙소 이미지" style="width:100%; height:auto;"/>
		                  	<c:if test="${status.index == mainImageIndex}">
		                  		<div style="
		                  			position:absolute;
		                  			bottom:5px;
		                  			left:5px;
		                  			background-color:#fd7e14;
		                  			color:white;
		                  			padding:2px 6px;
		                  			border-radius:4px;
		                  			font-size:0.9rem;
		                  			user-select:none;
		                  		">대표</div>
		                  	</c:if>
		                  </div>
		                </c:forEach>
		              </div>
		              <div class="swiper-button-next"></div>
		              <div class="swiper-button-prev"></div>
		          </div>
	          <button class="btn btn-primary " type="submit" onclick="return deleteAccoPhotoFn()">사진삭제</button> 
          </div>
        </form>
        <c:if test="${param.success == 'edit'}">
		    <div class="alert alert-success">
		        숙소 정보가 성공적으로 수정되었습니다!
		    </div>
		</c:if> 
        <c:if test="${param.error == 'notfound'}">
		    <div class="alert alert-success">
		        숙소 정보가 존재하지 않습니다. 숙소 정보를 등록해주세요.
		    </div>
		</c:if> 
        </div>
  </div><!--내용 끝-->
</section>
<script>
	document.addEventListener('DOMContentLoaded', function () {
		
		<c:forEach var="acco" items="${accoList}">
			new Swiper('.mySwiper', {
				// Optional parameters
				loop: true,
				slidesPerView:3,
				spaceBetween:20,
			
				// Navigation arrows
				navigation: {
					nextEl: '.mySwiper .swiper-button-next',
					prevEl: '.mySwiper .swiper-button-prev',
				}
			});
		</c:forEach>
	
	    let imageUpload = document.getElementById('accoUpload');
	    let previewContainer = document.getElementById('imagePreviewContainer');

	    imageUpload.addEventListener('change', function () {
	        previewContainer.innerHTML = ''; // 기존 미리보기 초기화

	        let files = imageUpload.files;

	        Array.from(files).forEach((file, index) => {
	            if (!file.type.startsWith('image/')) {
	                alert("이미지 파일만 업로드할 수 있습니다.");
	                return;
	            }

	            let reader = new FileReader();

	            reader.onload = function (e) {
	                let imgDiv = document.createElement('div');
	                imgDiv.style.position = 'relative';

	                // 이미지 미리보기 정보
	                let img = document.createElement('img');
	                img.src = e.target.result;
	                img.style.width = '150px';
	                img.style.height = '150px';
	                img.style.objectFit = 'cover';
	                img.style.border = '1px solid #ccc';
	                img.style.borderRadius = '5px';

	                // 대표 사진 선택
	                /*
	                let mainBtn = document.createElement('button');
	                mainBtn.innerText = '대표';
	                mainBtn.type = 'button';
	                mainBtn.style.position = 'absolute';
	                mainBtn.style.bottom = '5px';
	                mainBtn.style.left = '5px';
	                mainBtn.style.background = 'white';
	                mainBtn.style.color = 'black';
	                mainBtn.style.border = '1px solid black';
	                mainBtn.style.borderRadius = '4px';
	                mainBtn.style.fontSize = '12px';
	                mainBtn.style.padding = '2px 6px';
	                mainBtn.style.cursor = 'pointer';
	                
                    mainBtn.addEventListener('click', function () {
                        document.getElementById('mainImageIndex').value = index; // 대표 이미지 인덱스 설정

                        // UI 강조 효과 (선택된 대표만 표시)
                        document.querySelectorAll('#imagePreviewContainer div').forEach(div => {
                            mainBtn.style.background = '#fd7e14';
                            mainBtn.style.color = 'white';
                            let btn = div.querySelector('button');
                            if (btn && btn.innerText.includes('대표(선택됨)')) {
                                btn.innerText = '대표';
                            }
                        });
                        mainBtn.innerText = '대표(선택됨)';
                    });
                    
	                // 삭제 버튼
	                let delBtn = document.createElement('button');
	                delBtn.innerText = 'X';
	                delBtn.style.position = 'absolute';
	                delBtn.style.top = '5px';
	                delBtn.style.right = '5px';
	                delBtn.style.background = 'black';
	                delBtn.style.color = 'white';
	                delBtn.style.border = 'none';
	                delBtn.style.borderRadius = '50%';
	                delBtn.style.width = '24px';
	                delBtn.style.height = '24px';
	                delBtn.style.cursor = 'pointer';
	                delBtn.onclick = function () {
	                    
                	// 삭제 클릭 시 미리보기에서 제거
                    imgDiv.remove();
                    
                    // 파일 리스트 재구성
                    let dataTransfer = new DataTransfer();
                    Array.from(imageUpload.files).forEach((f, i) => {
                        if (i !== index) {
                            dataTransfer.items.add(f);
                        }
                    });
                    
                    imageUpload.files = dataTransfer.files;
                    
                    // document.getElementById('mainImageIndex').value = '';
                };
                */
	                imgDiv.appendChild(img);
	                // imgDiv.appendChild(mainBtn);
	                // imgDiv.appendChild(delBtn);
	                previewContainer.appendChild(imgDiv);
	            };

	            reader.readAsDataURL(file);
	        });
	    });
	    
	    if (editFileInput) {
	        const swiperWrapper = document.querySelector('.mySwiper .swiper-wrapper');

	        editFileInput.addEventListener('change', function () {
	            const files = this.files;

	            // 기존 슬라이드 초기화
	            swiperWrapper.innerHTML = '';

	            if (files.length === 0) return;

	            Array.from(files).forEach((file) => {
	                if (!file.type.startsWith('image/')) {
	                    alert("이미지 파일만 업로드할 수 있습니다.");
	                    return;
	                }

	                const reader = new FileReader();
	                reader.onload = function (e) {
	                    const slide = document.createElement('div');
	                    slide.classList.add('swiper-slide');
	                    slide.style.display = 'flex';
	                    slide.style.justifyContent = 'center';
	                    slide.style.alignItems = 'center';

	                    const img = document.createElement('img');
	                    img.src = e.target.result;
	                    img.style.width = '200px';
	                    img.style.height = '200px';
	                    img.style.objectFit = 'cover';
	                    img.style.borderRadius = '5px';

	                    slide.appendChild(img);
	                    swiperWrapper.appendChild(slide);

	                };

	                reader.readAsDataURL(file);
	            });
	        });
	    }
	   
	    const editFileInput = document.getElementById('editFile');
	    const editPreviewContainer = document.getElementById('editImagePreviewContainer');

	    if (editFileInput) {
	        editFileInput.addEventListener('change', function () {
	            editPreviewContainer.innerHTML = ''; // 기존 미리보기 초기화

	            const files = this.files;
	            if (files.length === 0) return;

	            Array.from(files).forEach((file) => {
	                if (!file.type.startsWith('image/')) {
	                    alert("이미지 파일만 업로드할 수 있습니다.");
	                    return;
	                }

	                const reader = new FileReader();
	                reader.onload = function (e) {
	                    const imgDiv = document.createElement('div');
	                    imgDiv.style.position = 'relative';

	                    const img = document.createElement('img');
	                    img.src = e.target.result;
	                    img.style.width = '150px';
	                    img.style.height = '150px';
	                    img.style.objectFit = 'cover';
	                    img.style.border = '1px solid #ccc';
	                    img.style.borderRadius = '5px';

	                    imgDiv.appendChild(img);
	                    editPreviewContainer.appendChild(imgDiv);
	                };
	                reader.readAsDataURL(file);
	            });
	        });
	    }
	    
	});

</script>
</body>
</html>