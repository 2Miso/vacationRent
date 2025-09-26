<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>비즈니스 회원가입</title>
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
    span{
      display: inline-block;
    }
    </style>

    <script>
    
     function joincheckFn(){
    	 
		function validatePhone(phone) {
			// 숫자만 10~11자리 또는 010-xxxx-xxxx 형식 검사
			let phonePattern = /^(01[016789])[-]?(\d{3,4})[-]?(\d{4})$/;
			return phonePattern.test(phone);
		}
		
		function validateCertificateNo(certificateNo) {
			// 숫자만 10자리 또는 xxx-xx-xxxxx 형식 검사
			let certificateNoPattern = /^\d{10}$|^\d{3}-\d{2}-\d{5}$/;
			return certificateNoPattern.test(certificateNo);
		}
    	 
		 // 이메일 유효성검사
	     if($("input[name=email]").val() == ""){ 
	       $("#emailspan").text("이메일을 입력해 주세요").css("color","red");
	       return false;
	     }else if(!/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test($("input[name=email]").val())){
	       $("#emailspan").text("이메일 주소가 아닌 것 같습니다.").css("color","red");
	       return false;
	     }else{
	       $("#emailspan").text("");
	     }
		 
	  	 // 비밀번호 유효성검사
	     if($("input[name=pw]").val() == ""){ 
	       $("#pwspan").text("비밀번호를 입력해 주세요").css("color","red");
	       return false;
	     }else if($("input[name=pw]").val().length < 8){
	       $("#pwspan").text("비밀번호는 8~20자리입니다. ").css("color","red");
	       return false;
	     }else if($("input[name=pw]").val().length > 20){
	       $("#pwspan").text("비밀번호는 8~20자리입니다. ").css("color","red");
	       return false;
	     }else{
	       $("#pwspan").text("");
	     }
	  	 
	  	 // 비밀번호확인 유효성검사
	     if($("input[name=pwcheck]").val() == ""){ 
	       $("#pwcheckspan").text("사용할 비밀번호를 입력해 주세요.").css("color","red");
	       return false;
	     }else if($("input[name=pw]").val() != $("input[name=pwcheck]").val()){
	       $("#pwcheckspan").text("비밀번호가 일치하지 않습니다.").css("color","red");
	       return false;
	     }else{
	       $("#pwcheckspan").text("");
	     }
	  	 
	  	 // 핸드폰번호 유효성검사
	     if($("input[name=phone]").val() == ""){ 
	         $("#phonespan").text("핸드폰번호를 입력해 주세요").css("color","red");
	         return false;
	     }else if(!validatePhone($("input[name=phone]").val())){
	         $("#phonespan").text("유효한 핸드폰번호를 입력해 주세요").css("color","red");
	         return false;
	     }else{
	       $("#phonespan").text("");
	     }
	  	 
	  	 // 회사이름 유효성검사
	     if($("input[name=name]").val() == ""){ 
	       $("#namespan").text("회사명을 입력해 주세요").css("color","red");
	       return false;
	     }else{
	       $("#namespan").text("");
	     }
	
	  	 // 대표이름 유효성검사
	     if($("input[name=owner]").val() == ""){ 
	       $("#ownerspan").text("이름을 입력해 주세요").css("color","red");
	       return false;
	     }else if($("input[name=owner]").val().length >= 20){
	       $("#ownerspan").text("이름이 너무 깁니다").css("color","red");
	       return false;
	     }else{
	       $("#ownerspan").text("");
	     }
	     
	 	 // 사업자등록번호 유효성검사
	     if($("input[name=certificateNo]").val() == ""){ 
	       $("#certificateNospan").text("사업자등록번호를 입력해 주세요").css("color","red");
	       return false;
	     }else if(!validateCertificateNo($("input[name=certificateNo]").val())){
	         $("#certificateNospan").text("유효한 사업자등록번호를 입력해 주세요").css("color","red");
	         return false;
	     }else{
	       $("#certificateNospan").text("");
	     }
	 	 
	     let fileInput = document.getElementById("formFileLg");
	     if (!fileInput || fileInput.files.length == 0) {
	    	 $("#formFileLgspan").text("사업자등록증 사진을 첨부해주세요.").css("color","red");
	       return false;
	     }else{
	    	 $("#formFileLgspan").text("");
	     }
	     
	     if (!emailValid) {
    	    $("#emailspan").text("이미 등록된 이메일입니다.").css("color", "red");
    	    return false;
    	 }
	 	 
		 return true;
     }
     
     let emailValid = false;

     $(document).ready(function() {
       $("input[name=email]").on("blur", function() {
         let email = $(this).val();
         if (email === '') return;
         
         // 이메일 형식 검사 (정규식)
         let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
         if (!emailPattern.test(email)) {
             $("#emailspan").text("유효한 이메일 형식이 아닙니다.").css("color", "red");
             emailValid = false;
             return;
         }

         $.ajax({
           url: "<c:url value='/join/check-email' />",
           method: "GET",
           data: { email: email },
           success: function(response) {
             if (!response) {
               $("#emailspan").text("이미 등록된 이메일입니다.").css("color", "red");
               emailValid = false;
             } else {
               $("#emailspan").text("사용 가능한 이메일입니다.").css("color", "green");
               emailValid = true;
             }
           },
           error: function() {
             $("#emailspan").text("이메일 확인 중 오류 발생").css("color", "red");
             emailValid = false;
           }
         });
       });
     });
     
    </script>
</head>


<body>
<section>
  <div class="login_form"><!--수직수평정렬용-->
    <h1 class="fw-bolder"> 비즈니스 회원가입 </h1>
    <c:url var="bizJoinFinishedUrl" value="/join/biz_join_finished" />
    <form:form modelAttribute="bizVO" enctype="multipart/form-data"
    	action="${bizJoinFinishedUrl}" 
    	method="post" onsubmit="return joincheckFn();">
      <div class="form-floating">
      	<form:input name="email" path="email" class="form-control" id="floatingInputGroup1" />
        <label for="floatingInputGroup1">이메일 주소</label>
        <form:errors path="email" cssClass="text-warning" />
		<c:if test="${not empty emailError}">
			<div class="text-danger">${emailError}</div>
		</c:if>
        <span id="emailspan"></span>
      </div>

      <div class="form-floating">
      	<form:input type="password" name="pw" path="pw" class="form-control" id="floatingInputGroup2" />
        <label for="floatingInputGroup2">사용할 비밀번호</label>
        <form:errors path="pw" cssClass="text-warning" />
        <span id="pwspan"></span>
      </div>
      
      <div class="form-floating">
        <input type="password" name="pwcheck" class="form-control" id="floatingInputGroup3" />
        <label for="floatingInputGroup3">비밀번호 확인</label>
        <form:errors path="pw" cssClass="text-warning" />
        <span id="pwcheckspan"></span>
      </div>
      
      <div class="form-floating">
        <form:input name="phone" path="phone" class="form-control" id="floatingInputGroup4" />
        <label for="floatingInputGroup4">핸드폰번호</label>
        <form:errors path="phone" cssClass="text-warning" />
        <span id="phonespan"></span>
      </div>

      <div class="form-floating">
        <form:input name="name" path="name" class="form-control" id="floatingInputGroup5" />
        <label for="floatingInputGroup5">회사명</label>
        <form:errors path="name" cssClass="text-warning" />
        <span id="namespan"></span>
      </div>

      <div class="form-floating">
        <form:input name="owner" path="owner" class="form-control" id="floatingInputGroup6" />
        <label for="floatingInputGroup6">사업주명</label>
        <form:errors path="owner" cssClass="text-warning" />
        <span id="ownerspan"></span>
      </div>

      <div class="form-floating">
        <form:input name="certificateNo" path="certificateNo" class="form-control" id="floatingInputGroup7" />
        <label for="floatingInputGroup7">사업자등록번호</label>
        <form:errors path="certificateNo" cssClass="text-warning" />
        <span id="certificateNospan"></span>
      </div>

      <div>
        <label for="formFileLg">사업자등록번호 사진</label>
        <input class="form-control form-control-lg" 
        	id="formFileLg" name="certificateFile" 
			type="file" style="font-size:20px;" />
			<span id="formFileLgspan"></span>
        <span style="display: inline-block;"></span>
      </div>
      

      <div class="d-grid gap-2"><!--버튼-->
        <button class="btn btn-primary " type="submit" style="height:50px;">회원가입</button><!--링크를 걸어야 합니다-->
      </div>
    </form:form>
  </div>
</section>
</body>
</html>