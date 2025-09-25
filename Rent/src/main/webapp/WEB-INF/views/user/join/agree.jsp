<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="../../include/header_nosearchbar.jsp" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>회원가입</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.slim.js" integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc=" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <link rel="stylesheet" href="<c:url value="/resources/css/color_orange.css" />">
    <style>
    body {
        --bs-font-sans-serif:margin:0; padding:0;font-size:14px;line-height:1.6;font-family:'Pretendard','Noto Sans KR', 'Apple SD Gothic Neo', '돋움', Dotum, Arial, Sans-serif;color:#464646;letter-spacing:0;-webkit-text-size-adjust:none;font-weight: 400
    }

    section{
      width:1024px;
      margin:0 auto;
      margin-top:100px;
    }

    .login_form{
      margin:0 auto;
      width:512px;
    }

    </style>
      
    <script>
    let termsallcheck = false;
      function termsallcheckFn(){
    	if (termsallcheck == false){
        $('#termsofuse').prop('checked', true);
        $('#termsover14').prop('checked', true);
        $('#termspersonal1').prop('checked', true);
        $('#termspersonal2').prop('checked', true);
        $('#termsmaketing').prop('checked', true);
        $('#termslocate').prop('checked', true);
        termsallcheck = true;
    	}else{
    		$('#termsofuse').prop('checked', false);
            $('#termsover14').prop('checked', false);
            $('#termspersonal1').prop('checked', false);
            $('#termspersonal2').prop('checked', false);
            $('#termsmaketing').prop('checked', false);
            $('#termslocate').prop('checked', false);
            termsallcheck= false;
    	}
      }

      function termscheckFn(){
        if($('#termsofuse').is(':checked')&&$('#termsover14').is(':checked')&&$('#termspersonal1').is(':checked') == true){
          //동의했으니 페이지 이동하는 링크를 걸어야 합니다
        	location.href="/vaca/user/join/form";
        }else{
          alert("필수 약관에 체크해주세요");
        }
      }
    </script>
</head>


<body>
<section>
  <div class="login_form"><!--수직수평정렬용-->
    <h1 class="fw-bolder">약관 동의하기 </h1>
    <form action="" method="POST">
    <p>약관에 동의해주세요</p>
    <div class="d-flex mb-3 align-items-center" style="background-color: rgb(214, 214, 214); border-radius: 6px; height:55px;"><!--전체동의니까 좀 눈에 잘 띄게 수정할 필요가 있습니다.-->
      <input class="form-check-input p-2 form-check-inline my-auto mx-2" type="checkbox" value=""  id="termsall" onclick="termsallcheckFn()">
      <p class="p-2 my-auto" style="font-size: 18px; font-weight: bold;">약관 전체동의(선택항목 포함)</p>
    </div>

    <div class="d-flex mb-3">
      <input class="form-check-input p-2 form-check-inline my-auto mx-2" type="checkbox" value="" id="termsofuse">
      <p class="p-2 my-auto">(필수)이용약관</p>
      <a href="#" class="ms-auto p-2" style="color: gray;"><i class="bi bi-chevron-right my-auto"></i></a> <!--링크를 걸어야 합니다-->
    </div>

    <div class="d-flex mb-3">
      <input class="form-check-input p-2 form-check-inline my-auto mx-2" type="checkbox" value=""  id="termsover14">
      <p class="p-2 my-auto">(필수)만 14세 이상 확인</p>
      <a href="#" class="ms-auto p-2" style="color: gray;"><i class="bi bi-chevron-right my-auto"></i></a> <!--링크를 걸어야 합니다-->
    </div>

    <div class="d-flex mb-3">
      <input class="form-check-input p-2 form-check-inline my-auto mx-2" type="checkbox" value=""  id="termspersonal1">
      <p class="p-2 my-auto">(필수)개인정보 수집 및 이용 동의</p>
      <a href="#" class="ms-auto p-2" style="color: gray;"><i class="bi bi-chevron-right my-auto"></i></a> <!--링크를 걸어야 합니다-->
    </div>

    <div class="d-flex mb-3">
      <input class="form-check-input p-2 form-check-inline my-auto mx-2 " type="checkbox" value=""  id="termspersonal2">
      <p class="p-2 my-auto">(선택)개인정보 수집 및 이용 동의</p>
      <a href="#" class="ms-auto p-2" style="color: gray;"><i class="bi bi-chevron-right my-auto"></i></a> <!--링크를 걸어야 합니다-->
    </div>

    <div class="d-flex mb-3">
      <input class="form-check-input p-2 form-check-inline my-auto mx-2" type="checkbox" value=""  id="termsmaketing">
      <p class="p-2 my-auto">(선택)마케팅 알림 수신 동의</p>
      <a href="#" class="ms-auto p-2" style="color: gray;"><i class="bi bi-chevron-right my-auto"></i></a> <!--링크를 걸어야 합니다-->
    </div>

    <div class="d-flex mb-3">
      <input class="form-check-input p-2 form-check-inline my-auto mx-2" type="checkbox" value=""  id="termslocate">
      <p class="p-2 my-auto">(선택)위치기반 서비스 이용약관 동의</p>
      <a href="#" class="ms-auto p-2" style="color: gray;"><i class="bi bi-chevron-right my-auto"></i></a> <!--링크를 걸어야 합니다-->
    </div>

    <div class="d-grid gap-2">
      <button link="#" class="btn btn-primary " type="button" onclick="termscheckFn()" style="height:50px;">다음</button><!--링크를 걸어야 합니다-->
    </div>
    </form>
  </div>
</section>
</body>
</html>