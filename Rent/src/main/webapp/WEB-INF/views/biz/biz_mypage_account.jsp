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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/color_orange.css" />">
    <style>
	    body {
	        --bs-font-sans-serif:margin:0; padding:0;font-size:14px;line-height:1.6;font-family:'Pretendard','Noto Sans KR', 'Apple SD Gothic Neo', '돋움', Dotum, Arial, Sans-serif;color:#464646;letter-spacing:0;-webkit-text-size-adjust:none;font-weight: 400
	    }
	    .active{
			--bs-nav-pills-link-active-bg:var(--bs-orange);
		}
		h5{
			margin:10px 0 10px 0;
		}
    </style>
    <script>

		function bizChangeFn(){
			
			// 기존 값과 현재 값 비교
			let isEdited = false;
			
			$("#bizOwner, #bizName, #bizPhone, #bizCertificateNo, #bizCertificateSavedName").each(function(){
				let original = $(this).data("original");
				let current = $(this).val().trim();
				if(original != current){
					isEdited = true;
				}
			});
			
			// 사진 삭제 시 업로드 필수를 위한 변수
			let fileInput = document.getElementById("bizCertificateSavedName");
			let files = fileInput.files;
			
			if(files.length == 0 && $("#currentCertificate").length == 0){
				alert("사업자등록증 사진을 반드시 업로드해주세요.");
				fileInput.focus();
				return false;
			}
			return true;
		}
		
		function deleteCertificate(){
			if(confirm("정말 사업자등록증 이미지를 삭제하시겠습니까?")) return;
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
        <a href="<c:url value="/biz/biz_mypage_acco" />" class="nav-link text-white"><!--숙소 관리 페이지로 링크 걸어야 합니다--> <!--aria current가 현재 표시되는 페이지를 강조합니다-->
          <i class="bi bi-house me-2  "></i>숙소 관리
        </a>
      </li>
      <li>
        <a href="<c:url value="/biz/biz_mypage_room" />" class="nav-link text-white" ><!--객실 관리 페이지로 링크 걸어야 합니다-->
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
        <a href="<c:url value="/biz/biz_mypage_account" />" class="nav-link active"><!--계정 설정 페이지로 링크 걸어야 합니다-->
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
          
        <div><!--숙소 정보 글로 수정하기-->
          <h3 class="fw-bold">숙소 관리</h3>
		<form action="<c:url value='/biz/biz_mypage_account'/>" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${biz.id != null ? biz.id : 0}" />
          <div style="width:500px;">
            <h5>이메일</h5>
            <div style="font-size:1.5em; height:50px;">
            ${biz.email}
            </div>
          </div>

          <div style="width:500px;">
            <h5>사업자명</h5>
            <input id="bizOwner" class="form-control" value="${biz.owner}" data-original="${biz.owner}" style="height:50px;" type="text" aria-label="default input example" name="owner">
          </div>

          <div style="width:500px;">
            <h5>회사명</h5>
            <input id="bizName" class="form-control" value="${biz.name}" data-original="${biz.name}" style="height:50px;" type="text" aria-label="default input example" name="name">
          </div>

          <div style="width:500px;">
            <h5>전화번호</h5>
            <input id="bizPhone" class="form-control" value="${biz.phone}" data-original="${biz.phone}" style="height:50px;" type="text" aria-label="default input example" name="phone">
          </div>

          <div style="width:500px;">
            <h5>사업자등록번호</h5>
            <input id="bizCertificateNo" class="form-control" value="${biz.certificateNo}" data-original="${biz.certificateNo}" style="height:50px;" type="text" aria-label="default input example" name="certificateNo">
          </div>
          
          <!-- 기존 저장된 이미지 출력 -->
            <c:if test="${not empty biz.certificateSavedName}">
              <div id="currentCertificate">
            	<p>현재 등록된 사업자등록증</p>
            	<img src="<c:url value='/resources/img/biz/${biz.certificateSavedName}'/>"
            		alt="사업자등록증" style="max-width:200px; border:1px solid #ccc; border-radius:8px;">
            	<button type="submit" class="btn btn-primary"
            	onclick="return deleteCertificate()"
            	name="action" value="delete"
            	>사진삭제</button>
              </div>
            </c:if>

          <div style="width:500px;">
            <h5>사업자등록증 사진 업로드</h5>
            <input id="bizCertificateSavedName" class="form-control" type="file" name="certificateFile">
          </div>
          
          <div class="d-grid gap-2">
            <br><button class="btn btn-primary " type="submit"
            onclick="return bizChangeFn()"
            name="action" value="update"
            >수정하기</button>
          </div>
          <c:if test="${param.success == 'edit'}">
		    <div class="alert alert-success">
		        정보가 수정되었습니다!
		    </div>
		</c:if>
        </form>  
        </div><!--숙소 정보 글로 수정하기-->
        
        <div class="mx-3" style="width:400px; height:600px; overflow: auto;"><!--이미지 업로드 or 확인-->
          <h3 class="fw-bold">비밀번호 변경</h3>
          <div class="d-grid gap-2">
          <a href="<c:url value='/biz/biz_pw_change_form'/>" class="btn btn-primary" style="height:50px;">변경하기</a>

         </div>
          
        </div>
          

  </div><!--내용 끝-->
</section>
</body>
</html>