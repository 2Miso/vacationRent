<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>비즈니스 약관동의</title>
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
    section{
      width:1024px;
      margin:0 auto;
      margin-top:200px;
    }
    .login_form{
      margin:0 auto;
      width:512px;
    }
    </style>
      
    <script>
    
      
	function termsallcheckFn(){
		let isChecked = $('#termsall').is(':checked');
		
		$('#termsofuse').prop('checked', isChecked);
		$('#termsover14').prop('checked', isChecked);
		$('#termspersonal1').prop('checked', isChecked);
		$('#termspersonal2').prop('checked', isChecked);
		$('#termsmaketing').prop('checked', isChecked);
		$('#termslocate').prop('checked', isChecked);
	}
      
      // 하나라도 체크 해제되면 전체 약관 동의 체크 해제
	$(document).ready(function(){
		$('#termsofuse, #termsover14, #termspersonal1, #termspersonal2, #termsmaketing, #termslocate').on('change', function() {
	    	let allChecked = $('#termsofuse').is(':checked') &&
	                      $('#termsover14').is(':checked') &&
	                      $('#termspersonal1').is(':checked') &&
	                      $('#termspersonal2').is(':checked') &&
	                      $('#termsmaketing').is(':checked') &&
	                      $('#termslocate').is(':checked');
	
			$('#termsall').prop('checked', allChecked);
		});
	});
</script>
</head>

<body>
<section>
  <div class="login_form">
    <h1 class="fw-bolder">비즈니스 회원 약관 동의하기 </h1>
    <p>비즈니스 회원 약관에 동의해주세요</p>
	<c:if test="${param.error != null and param.error eq 'missing'}">
		<div class="alert alert-warning mt-2">
		  필수 약관에 모두 동의해주세요.
		</div>
	</c:if>
    <form action="<c:url value="/join/biz_join_form" />" method="get" onsubmit="return termscheckFn();">
    <div class="d-flex mb-3 align-items-center" style="background-color: rgb(214, 214, 214); border-radius: 6px; height:55px;"><!--전체동의니까 좀 눈에 잘 띄게 수정할 필요가 있습니다.-->
      <input class="form-check-input p-2 form-check-inline my-auto mx-2" type="checkbox" value=""  id="termsall" onclick="termsallcheckFn()">
      <p class="p-2 my-auto" style="font-size: 18px; font-weight: bold;">약관 전체동의(선택항목 포함)</p>
    </div>

    <div class="d-flex mb-3">
      <input class="form-check-input p-2 form-check-inline my-auto mx-2" type="checkbox" name="termsofuse"  id="termsofuse">
      <p class="p-2 my-auto">(필수)이용약관</p>
      <a href="#" class="ms-auto p-2" style="color: gray;"><i class="bi bi-chevron-right my-auto"></i></a> <!--링크를 걸어야 합니다-->
    </div>

    <div class="d-flex mb-3">
      <input class="form-check-input p-2 form-check-inline my-auto mx-2" type="checkbox" name="termsover14"  id="termsover14">
      <p class="p-2 my-auto">(필수)만 14세 이상 확인</p>
      <a href="#" class="ms-auto p-2" style="color: gray;"><i class="bi bi-chevron-right my-auto"></i></a> <!--링크를 걸어야 합니다-->
    </div>

    <div class="d-flex mb-3">
      <input class="form-check-input p-2 form-check-inline my-auto mx-2" type="checkbox" name="termspersonal1"  id="termspersonal1">
      <p class="p-2 my-auto">(필수)개인정보 수집 및 이용 동의</p>
      <a href="#" class="ms-auto p-2" style="color: gray;"><i class="bi bi-chevron-right my-auto"></i></a> <!--링크를 걸어야 합니다-->
    </div>

    <div class="d-flex mb-3">
      <input class="form-check-input p-2 form-check-inline my-auto mx-2 " type="checkbox" name="termspersonal2"  id="termspersonal2">
      <p class="p-2 my-auto">(선택)개인정보 수집 및 이용 동의</p>
      <a href="#" class="ms-auto p-2" style="color: gray;"><i class="bi bi-chevron-right my-auto"></i></a> <!--링크를 걸어야 합니다-->
    </div>

    <div class="d-flex mb-3">
      <input class="form-check-input p-2 form-check-inline my-auto mx-2" type="checkbox" name="termsmaketing"  id="termsmaketing">
      <p class="p-2 my-auto">(선택)마케팅 알림 수신 동의</p>
      <a href="#" class="ms-auto p-2" style="color: gray;"><i class="bi bi-chevron-right my-auto"></i></a> <!--링크를 걸어야 합니다-->
    </div>

    <div class="d-flex mb-3">
      <input class="form-check-input p-2 form-check-inline my-auto mx-2" type="checkbox" name="termslocate"  id="termslocate">
      <p class="p-2 my-auto">(선택)위치기반 서비스 이용약관 동의</p>
      <a href="#" class="ms-auto p-2" style="color: gray;"><i class="bi bi-chevron-right my-auto"></i></a> <!--링크를 걸어야 합니다-->
    </div>

    <div class="d-grid gap-2">
   		<button class="btn btn-primary " type="submit" style="height:50px;">다음</button><!--링크를 걸어야 합니다-->
    </div>
    </form>
  </div>
</section>
</body>
</html>