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
    	// 숙소이름유효성검사
        if($("input[name=acconame]").val() == ""){
          $("input[name=acconame]").parent().children("span").text("숙소 이름을 입력해 주세요.").css("color","red");
        }else{
          $("input[name=acconame]").parent().children("span").text("");
        }

      	// 숙소시작시간유효성검사
        if($("input[name=accostartime]").val() == ""){
          $("input[name=accostartime]").parent().children("span").text("시작시간을 입력해 주세요.").css("color","red");
        }else{
          $("input[name=accostartime]").parent().children("span").text("");
        }

      	//숙소마감시간유효성검사
        if($("input[name=accoendtime]").val() == ""){
          $("input[name=accoendtime]").parent().children("span").text("마감시간을 입력해 주세요.").css("color","red");
        }else{
          $("input[name=accoendtime]").parent().children("span").text("");
        }

      	// 숙소전화번호유효성검사
        if($("input[name=accophone]").val() == ""){
          $("input[name=accophone]").parent().children("span").text("전화번호를 입력해 주세요.").css("color","red");
        }else{
          $("input[name=accophone]").parent().children("span").text("");
        }

     	 // 숙소가격유효성검사
        if($("input[name=accoprice]").val() == ""){
          $("input[name=accoprice]").parent().children("span").text("숙소가격을 입력해 주세요.").css("color","red");
        }else{
          $("input[name=accoprice]").parent().children("span").text("");
        }

      	// 숙소설명유효성검사
        if($("input[name=accodesc]").val() == ""){
          $("input[name=accodesc]").parent().children("span").text("숙소설명을 입력해 주세요.").css("color","red");
        }else{
          $("input[name=accodesc]").parent().children("span").text("");
        }
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
          
          <div>숙소 타입</div>
          <div class="form-floating">
            <input type="text" class="form-control" id="accoType" name="type">
            <label for="accoType">숙소 타입을 입력해주세요. 예) 호텔, 모텔, 펜션 등</label>
            <span></span>
          </div>
          
          <div>숙소 이름</div>
          <div class="form-floating">
            <input type="text" class="form-control" id="accoName" name="name">
            <label for="accoName">숙소 이름을 입력해주세요.</label>
            <span></span>
          </div>
          
          <div>숙소 주소</div>
          <div class="form-floating">
            <input type="text" class="form-control" id="accoAddr" name="addr">
            <label for="accoAddr">숙소 주소를 입력해주세요.</label>
            <span></span>
          </div>
          
          <div>숙소 전화번호</div>
          <div class="form-floating">
            <input type="text" class="form-control" id="floatingInput4" name="phone">
            <label for="floatingInput4">숙소 전화번호를 입력해주세요.</label>
            <span></span>
          </div>
          
          <div>숙소 정보</div>
          <div class="form-floating">
            <input type="text" class="form-control" id="accoInfo" name="description">
            <label for="accoInfo">숙소 정보를 입력해주세요. 예) 숙소 장점이나 홍보등</label>
            <span></span>
          </div>
          
          <div>상담가능시간</div>
          <div class="form-floating">
            <input type="text" class="form-control" id="accoBizHour" name="biz_hour">
            <label for="accoBizHour">상담가능 시간을 입력해주세요.</label>
            <span></span>
          </div>

          <div>운영 시간</div>
          <div class="d-flex">
              <div>
                <div class="form-floating">
                <input type="text" class="form-control" id="accoCheckin" name="checkin">
                <label for="accoCheckin">입실 시간 ex) 00:00</label>
                <span></span>
                </div>
              </div>

           
              <div>
                <div class="form-floating">
                <input type="text" class="form-control" id="accoCheckout" name="checkout">
                <label for="accoCheckout">퇴실 시간 ex) 24:00</label>
                <span></span>
                </div>
              </div>
          </div>
          
			<br><button class="btn btn-primary " type="submit" onclick="return accochangeFn()">저장하기</button><!--링크를 걸어야 합니다-->
        </form>
        </div><!--숙소 정보 등록 끝-->
          
        <div class="mx-3" style="background-color: aqua; width:400px; height:600px; overflow: auto;"><!--이미지 업로드 or 확인-->
          <h3 class="fw-bold">등록된 숙소 관리</h3><!-- 숙소 수정 -->
			<form action="<c:url value="/biz/biz_mypage_acco" />" method="post" enctype="multipart/form-data">
          
          <div>숙소 타입</div>
          <div class="form-floating">
            <input type="text" class="form-control" id="editType" name="type">
            <label for="editType">숙소 타입을 입력해주세요. 예) 호텔, 모텔, 펜션 등</label>
            <span></span>
          </div>
          
          <div>숙소 이름</div>
          <div class="form-floating">
            <input type="text" class="form-control" id="editName" name="name">
            <label for="editName">숙소 이름을 입력해주세요.</label>
            <span></span>
          </div>
          
          <div>숙소 주소</div>
          <div class="form-floating">
            <input type="text" class="form-control" id="editAddr" name="addr">
            <label for="editAddr">숙소 주소를 입력해주세요.</label>
            <span></span>
          </div>
          
          <div>숙소 전화번호</div>
          <div class="form-floating">
            <input type="text" class="form-control" id="editPhone" name="phone">
            <label for="editPhone">숙소 전화번호를 입력해주세요.</label>
            <span></span>
          </div>
          
          <div>숙소 정보</div>
          <div class="form-floating">
            <input type="text" class="form-control" id="editInfo" name="description">
            <label for="editInfo">숙소 정보를 입력해주세요. 예) 숙소 장점이나 홍보등</label>
            <span></span>
          </div>
          
          <div>상담가능시간</div>
          <div class="form-floating">
            <input type="text" class="form-control" id="editBizHour" name="biz_hour">
            <label for="editBizHour">상담가능 시간을 입력해주세요.</label>
            <span></span>
          </div>

          <div>운영 시간</div>
          <div class="d-flex">
              <div>
                <div class="form-floating">
                <input type="text" class="form-control" id="editCheckin" name="checkin">
                <label for="editCheckin">입실 시간 ex) 00:00</label>
                <span></span>
                </div>
              </div>

           
              <div>
                <div class="form-floating">
                <input type="text" class="form-control" id="editCheckout" name="checkout">
                <label for="editCheckout">퇴실 시간 ex) 24:00</label>
                <span></span>
                </div>
              </div>
          </div>
          
			<br><button class="btn btn-primary " type="submit" onclick="return accochangeFn()">저장하기</button><!--링크를 걸어야 합니다-->
        </form>
        </div>
          

  </div><!--내용 끝-->
</section>
</body>
</html>