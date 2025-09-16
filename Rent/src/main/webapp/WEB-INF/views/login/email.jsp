<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>이메일로 로그인</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.slim.js" integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc=" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <link href="color_orange.css" rel="stylesheet" type="text/css">
    <link href="size.css" rel="stylesheet" type="text/css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js" integrity="sha512-rstIgDs0xPgmG6RX1Aba4KV5cWJbAMcvRCVmglpam9SoHZiUCyQVDdH2LPlxoHtrv17XWblE/V/PP+Tr04hbtA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
    function loginFn(){
      if($("input[name=email]").val() == ""){//이메일유효성검사
        $("#emailspan").text("이메일을 입력해 주세요").css("color","red");
      }else if(!/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test($("input[name=email]").val())){
        $("#emailspan").text("이메일 주소가 아닌 것 같습니다.").css("color","red");
      }else{
        $("#emailspan").text(" ").css("color","red");
      }

      if($("input[name=pw]").val() == ""){//비밀번호유효성검사
        $("#pwspan").text("비밀번호를 입력해 주세요").css("color","red");
      }else{
        $("#pwspan").text(" ").css("color","red");
      }
     }
    </script>
    
</head>


<body>
<section>
  <div class="login_form"><!--수직수평정렬용-->
    <h1 class="fw-bolder"> 이메일로 로그인 </h1>

    <form action="#" method="POST"> <!--이메일과 비밀번호 전송할 주소를 지정해야 합니다-->
      <div class="form-floating form-field">
        <input type="email" class="form-control" id="floatingInput" placeholder="이메일 주소 입력" name="email">
        <label for="floatingInput">이메일 주소 입력</label>
        <span id="emailspan" style="display:inline-block;"></span>
      </div>

      <div class="form-floating">
          <input type="password" class="form-control" id="floatingInput" placeholder="비밀번호 입력" name="pw">
          <label for="floatingInput">비밀번호</label>
          <span id="pwspan" style="display:inline-block;"></span>
      </div>

      <div class="d-grid gap-2"><!--버튼-->
        <button link="#" class="btn btn-primary " type="submit" style="height:50px;" onclick="loginFn()">로그인</button><!--이메일과 비밀번호를 전송할 버튼-->
      </div>
    </form>
  
    <div class="mx-auto py-3">
      <p class="my-0 mx-auto text-center "><a href="#" class="mx-auto text-center link-secondary link-offset-2 link-underline-opacity-0 link-underline-opacity-100-hover">아이디 찾기</a></p><!--아이디 찾기 링크를 걸어야 합니다-->
      <p class="my-0 text-center"><a href="#" class="my-0 link-secondary link-offset-2 link-underline-opacity-0 link-underline-opacity-100-hover">비밀번호 찾기</a></p><!--비밀번호 찾기 링크를 걸어야 합니다-->
      <p class="my-0 text-center  "><a href="#" class="my-0 link-secondary link-offset-2 link-underline-opacity-0 link-underline-opacity-100-hover">회원가입</a></p><!--회원가입 링크를 걸어야 합니다-->
    </div>
  </div>
</section>
</body>
</html>