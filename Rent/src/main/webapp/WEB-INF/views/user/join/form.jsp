<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../include/header_nosearchbar.jsp" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>회원가입</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
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
    let emailcheck = false;
    let pwcheck = false;
    let pwcheckcheck = false;
    let namecheck = false;
    let nicknamecheck = false;
    let phonecheck = false;
    let emailCheckFlag=false;
    
    function joincheckFn(){
    	$.ajax({
			url : "/vaca/user/join/form/emailCheck",
			type : "post",
			data : {
				"email" : $("input[name=pw]").val()
			},
			//요청부분
			success : function(response){
				console.log(response.code);
				//response의 code가 1이면 가입불가 0이면 가능
				if(response.code == 1){
					 $("#emailspan").text(" 중복된 이메일입니다").css("color","red");
					emailCheckFlag = false;
				}else{
					 $("#emailspan").text(" ").css("color","red");
					emailCheckFlag = true;
				}
			},
			error : function(){
				console.log("에러")
				emailCheckFlag = false;
			}
		});
    	
      if($("input[name=email]").val() == ""){//이메일유효성검사
        $("#emailspan").text("이메일을 입력해 주세요").css("color","red");
        emailcheck = false;
      }else if(!/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test($("input[name=email]").val())){
        $("#emailspan").text("사용할 수 없는 이메일 주소입니다.").css("color","red");
        emailcheck = false;
      }else{
        $("#emailspan").text(" ").css("color","red");
        emailcheck = true;
      }

      if($("input[name=pw]").val() == ""){//비밀번호유효성검사
        $("#pwspan").text("비밀번호를 입력해 주세요").css("color","red");
        pwcheck = false;
      }else if($("input[name=pw]").val().length <= 8){
        $("#pwspan").text("비밀번호는 8~20자리입니다. ").css("color","red");
        pwcheck = false;
      }else if($("input[name=pw]").val().length >= 20){
        $("#pwspan").text("비밀번호는 8~20자리입니다. ").css("color","red");
        pwcheck = false;
      }else{
        $("#pwspan").text("").css("color","red");
        pwcheck = true;
      }

      if($("input[name=pwcheck]").val() == ""){//비밀번호확인유효성검사
        $("#pwcheckspan").text("사용할 비밀번호를 입력해 주세요.").css("color","red");
        pwcheckcheck = false;
      }else if($("input[name=pw]").val() != $("input[name=pwcheck]").val()){
        $("#pwcheckspan").text("비밀번호가 일치하지 않습니다.").css("color","red");
        pwcheckcheck = false;
      }else{
        $("#pwcheckspan").text(" ").css("color","red");
        pwcheckcheck = true;
      }

      if($("input[name=name]").val() == ""){//이름유효성검사
        $("#namespan").text("이름을 입력해 주세요").css("color","red");
        namecheck = false;
      }else if($("input[name=name]").val().length >= 20){
        $("#namespan").text(" 이름이 너무 깁니다").css("color","red");
        namecheck = false;
      }else{
        $("#namespan").text(" ").css("color","red");
        namecheck = true;
      }

      if($("input[name=nickname]").val() == ""){//닉네임유효성검사
        $("#nicknamespan").text("닉네임을 입력해 주세요").css("color","red");
        nicknamecheck = false;
      }else if($("input[name=nickname]").val().length >= 20){
        $("#nicknamespan").text("닉네임이 너무 깁니다").css("color","red");
        nicknamecheck = false;
      }else{
        $("#nicknamespan").text(" ").css("color","red");
        nicknamecheck = true;
      }

      if($("input[name=phone]").val() == ""){//전화번호유효성검사
        $("#phonespan").text("전화번호를 입력해 주세요").css("color","red");
        phonecheck = false;
      }else{
        $("#phonespan").text(" ").css("color","red");
        phonecheck = true;
      }

      if(emailcheck&&pwcheck&&pwcheckcheck&&namecheck&&nicknamecheck&&phonecheck&&emailCheckFlag){
			return false;
		}else{
			return false;
		}
     }
    
    </script>
</head>


<body>
<section>
  <div class="login_form"><!--수직수평정렬용-->
    <h1 class="fw-bolder"> 회원가입 </h1>
    <form onsubmit="return joincheckFn()" action="#" method="POST" > <!--회원가입 정보를 전송할 링크를 걸어야 합니다-->
      <div class="form-floating">
        <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="email">
        <label for="floatingInput">이메일 주소 입력</label>
        <span id="emailspan"></span>
      </div>

      <div class="form-floating">
        <input type="password" class="form-control" id="floatingInput" placeholder="name@example.com" name="pw">
        <label for="floatingInput">사용할 비밀번호 입력</label>
        <span id="pwspan"></span>
      </div>
      
      <div class="form-floating">
        <input type="password" class="form-control" id="floatingInput" placeholder="name@example.com" name="pwcheck">
        <label for="floatingInput">비밀번호 확인</label>
        <span id="pwcheckspan"></span>
      </div>

      <div class="form-floating">
        <input type="text" class="form-control" id="floatingInput" placeholder="name@example.com" name="name">
        <label for="floatingInput">이름</label>
        <span id="namespan"></span>
      </div>

      <div class="form-floating">
        <input type="text" class="form-control" id="floatingInput" placeholder="name@example.com" name="nickname">
        <label for="floatingInput">닉네임</label>
        <span id="nicknamespan"></span>
      </div>

      <div class="form-floating">
        <input type="text" class="form-control" id="floatingInput" placeholder="name@example.com" name="phone">
        <label for="floatingInput">전화번호</label>
        <span id="phonespan"></span>
      </div>

      <div class="d-grid gap-2"><!--버튼-->
        <button link="#" class="btn btn-primary " type="submit" onclick="joincheckFn()" style="height:50px;">회원가입</button><!--링크를 걸어야 합니다-->
      </div>
    </form>
    

  </div>
</section>
</body>
</html>