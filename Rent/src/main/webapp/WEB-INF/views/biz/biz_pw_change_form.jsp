<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>비밀번호 변경하기</title>
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
      function pwchangeFn(){
    	  
		// 비밀번호 유효성검사
		if($("input[name=nowPw]").val() == ""){
			$("#pwspan").text("비밀번호를 입력해 주세요").css("color","red");
			return false;
		}else{
			$("#nowPwSpan").text(" ").css("color","red");
		}
		
		// 비밀번호 유효성검사
		if($("input[name=editPw]").val() == ""){
			$("#editPwSpan").text("비밀번호를 입력해 주세요").css("color","red");
			return false;
		}else if($("input[name=editPw]").val() != $("input[name=editPwCheck]").val()){
			$("#pwcheckspan").text("비밀번호가 일치하지 않습니다").css("color","red");
			return false;
		}else{
			$("#pwcheckspan").text(" ").css("color","red");
		}
		}
    </script>
</head>

<body>
<section>
  <div class="login_form">
    <h1 class="fw-bolder"> 비밀번호 변경 </h1>
	<form action="<c:url value='/biz/biz_mypage_account' />" method="post">
    
    <div class="form-floating">
      <input type="password" class="form-control" name="nowPw">
      <label for="floatingInput">기존 비밀번호</label>
      <span id="nowPwSpan" style="display: inline-block;"></span>
    </div>
    
    <div class="form-floating">
      <input type="password" class="form-control" name="editPw">
      <label for="floatingInput">변경할 비밀번호</label>
      <span id="editPwSpan" style="display: inline-block;"></span>
    </div>

    <div class="form-floating">
      <input type="password" class="form-control" name="editPwCheck">
      <label for="floatingInput">비밀번호 확인</label>
      <span id="pwcheckspan" style="display: inline-block;"></span>
    </div>

    <div class="d-grid gap-2"><!--버튼-->
      <button class="btn btn-primary " type="submit" style="height:50px;" onclick="return pwchangeFn()">다음</button><!--링크를 걸어야 합니다-->
    </div>
	</form>
	<c:if test="${param.pwChange == 'success'}">
	    <div class="alert alert-success">
	        비밀번호가 성공적으로 변경되었습니다!
	    </div>
	</c:if>
	<c:if test="${param.pwChange == 'fail'}">
	    <div class="alert alert-danger">
	        비밀번호 변경에 실패했습니다. 다시 시도해주세요.
	    </div>
	</c:if>
  </div>
</section>
</body>
</html>