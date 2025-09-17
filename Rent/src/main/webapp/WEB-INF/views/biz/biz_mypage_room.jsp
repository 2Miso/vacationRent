<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>비즈니스 회원 - 객실 관리</title>
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
    </style>
    <script>
		function roomAddFn(){
		
			// 객실이름 유효성검사
			if($("input[name=name]").val() == ""){
				$("input[name=name]").parent().children("span").text("객실 이름을 입력해 주세요.").css("color","red");
				return false;
			}else{
				$("input[name=name]").parent().children("span").text("");
			}
			
			// 객실가격 유효성검사
			if($("input[name=price]").val() == ""){
				$("input[name=price]").parent().children("span").text("객실 가격을 입력해 주세요.").css("color","red");
				return false;
			}else{
				$("input[name=price]").parent().children("span").text("");
			}
			
			 // 객실호실 유효성검사
			if($("input[name=ho]").val() == ""){
				$("input[name=ho]").parent().children("span").text("객실 호실을 입력해 주세요.").css("color","red");
				return false;
			}else{
				$("input[name=ho]").parent().children("span").text("");
			}
			
			// 객실 최소인원 유효성검사
			if($("input[name=standard_head]").val() == ""){
				$("input[name=standard_head]").parent().children("span").text("객실 최소인원을 입력해 주세요.").css("color","red");
				return false;
			}else{
				$("input[name=standard_head]").parent().children("span").text("");
			}
			
			// 객실 추가가능인원 유효성검사
			if($("input[name=extra_head]").val() == ""){
				$("input[name=extra_head]").parent().children("span").text("객실 추가가능 인원을 입력해 주세요.").css("color","red");
				return false;
			}else{
				$("input[name=extra_head]").parent().children("span").text("");
			}
			
			// 객실설명 유효성검사
			if($("input[name=description]").val() == ""){
				$("input[name=description]").parent().children("span").text("객실 설명을 입력해 주세요.").css("color","red");
				return false;
			}else{
				$("input[name=description]").parent().children("span").text("");
			}
						
			// 객실이미지 유효성검사
			let imageInput = document.getElementById("imageUpload");
			let files = imageInput.files;

			if (files.length == 0) {
			    $(imageInput).next("span").text("객실 이미지를 등록해 주세요.").css("color", "red");
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
		
		document.addEventListener('DOMContentLoaded', function () {
		    let imageUpload = document.getElementById('imageUpload');
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

		                let img = document.createElement('img');
		                img.src = e.target.result;
		                img.style.width = '150px';
		                img.style.height = '150px';
		                img.style.objectFit = 'cover';
		                img.style.border = '1px solid #ccc';
		                img.style.borderRadius = '5px';

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
		                };

		                imgDiv.appendChild(img);
		                imgDiv.appendChild(delBtn);
		                previewContainer.appendChild(imgDiv);
		            };

		            reader.readAsDataURL(file);
		        });
		    });
		});
		
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
        <a href="#" class="nav-link text-white"><!--숙소 관리 페이지로 링크 걸어야 합니다--> <!--aria current가 현재 표시되는 페이지를 강조합니다-->
          <i class="bi bi-house me-2  "></i>숙소 관리
        </a>
      </li>
      <li>
        <a href="#" class="nav-link active" ><!--객실 관리 페이지로 링크 걸어야 합니다-->
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
          
        <div>
          <h3 class="fw-bold">객실 관리</h3>
		  <h4>객실 등록</h4>
		  <form action="<c:url value="/biz/biz_mypage_room" />" method="post" enctype="multipart/form-data">
          <div style="width:500px;">
            <h5>객실 이름</h5>
            <input class="form-control" style="height:50px;" type="text" placeholder="객실 이름을 입력하세요." aria-label="default input example" name="name">
            <span id="" style="display: inline-block;"></span>
          </div>

          <div>
            <h5>객실 가격</h5>
            <input class="form-control" type="text" placeholder="객실 가격을 입력하세요." aria-label="default input example" name="price">
            <span id="" style="display: inline-block;"></span>
          </div>
		  
		  <div>
            <h5>객실 호실</h5>
            <input class="form-control" type="text" placeholder="객실 호수를 입력하세요." aria-label="default input example" name="ho">
            <span id="" style="display: inline-block;"></span>
          </div>

		  <div>
            <h5>객실 최소 수용인원</h5>
            <input class="form-control" type="text" placeholder="객실 최소 인원수를 입력하세요." aria-label="default input example" name="standard_head">
            <span id="" style="display: inline-block;"></span>
          </div>

		  <div>
            <h5>객실 추가가능 인원</h5>
            <input class="form-control" type="text" placeholder="객실 추가 가능한 인원수를 입력하세요." aria-label="default input example" name="extra_head">
            <span id="" style="display: inline-block;"></span>
          </div>

          <div>
            <h5>객실 설명</h5>
            <input class="form-control" type="text" placeholder="객실 설명을 적어주세요." aria-label="default input example" name="description">
            <span id="" style="display: inline-block;"></span>
          </div>
          
          <div>
            <h5>객실 이미지 등록</h5>
            <input id="imageUpload" class="form-control" type="file" multiple accept="image/*" name="image[]">
            <span id="" style="display: inline-block;"></span>
          </div>
          
          <div id="imagePreviewContainer" style="margin-top: 10px; display: flex; gap: 10px; flex-wrap: wrap;"></div>

          <!-- 대표 이미지 인덱스를 서버로 보내기 위한 hidden input -->
		  <input type="hidden" id="mainImageIndex" name="mainImageIndex" value="">
          
            <button class="btn btn-primary " type="submit" onclick="return roomAddFn()">등록하기</button><!--링크를 걸어야 합니다-->
        </form>
        </div><!--숙소 정보 글로 수정하기-->
          
        <div class="mx-3" style="width:400px; height:600px; overflow: auto;"><!--이미지 업로드 or 확인-->
          <h3 class="fw-bold">등록된 객실 관리</h3>
          <div class="selectRoom">
          	  <c:forEach var="room" items="${roomList}">
	          	  <div>${room.name}</div>
				  <div class="swiper mySwiper-${room.id}">
		              <div class="swiper-wrapper">
		              	<c:forEach var="img" items="${room.photos}">
		                  <div class="swiper-slide">
		                  	<img src="${photo}" alt="room image" />
		                  </div>
		                </c:forEach>
		              </div>
		              <div class="swiper-button-next"></div>
		              <div class="swiper-button-prev"></div>
		          </div>
	          </c:forEach>
	          <button link="#" class="btn btn-primary " type="button">삭제하기</button> 
          </div>
        </div>
  </div><!--내용 끝-->
</section>

<script>
	document.addEventListener('DOMContentLoaded', function () {
		<c:forEach var="room" items="${roomList}">
			new Swiper('.swiper-${room.id}', {
				// Optional parameters
				loop: true,
				slidesPerView:3,
				spaceBetween:20,
			
				// Navigation arrows
				navigation: {
					nextEl: '.mySwiper-${room.id} .swiper-button-next',
					prevEl: '.mySwiper-${room.id} .swiper-button-prev',
				}
			});
		</c:forEach>
	});
</script>

</body>
</html>