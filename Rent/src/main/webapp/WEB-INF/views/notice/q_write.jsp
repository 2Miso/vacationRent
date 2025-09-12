<%@page import="com.rent.vaca.user.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/* 작성자 입력을 위한 임시 로그인 객체. 나중에 삭제할 것.*/
	UserVO user = new UserVO();
	user.setId(1);
	session.setAttribute("user", user);
/* 작성자 입력을 위한 임시 로그인 객체. 나중에 삭제할 것.*/
%>
<%@ include file="../include/header_nosearchbar.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<!--     부트스트랩 css
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
	오렌지테마 css
    <link href="resources/css/color_orange.css" rel="stylesheet" type="text/css">
    jQuery
	<script src="resources/js/jquery-3.7.1.min.js"></script>
	부트스트랩 js
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script> -->
    <style>
        a{
            text-decoration:none;
            color:inherit;
        }
        a:hover{
            text-decoration:underline;
        }
        .pageTitle, .writeFrm, .writeFrm input:not([type=file]), .writeFrm textarea{
            width:1024px;
            margin:10px auto;
        }
        .writeFrm textarea{
            height: 500px;
            resize:none;
        }
        .writeFrm .buttonGroup{
            float:right;
        }
        /* *{
            border:1px solid red;
        } */
    </style>
    <script>
        let title;
        let content;

        $(function(){
            title = $("[name=title]");
            content = $("[name=content]");
            $("#register").on( "click", function() {
                if(title.val().trim()==""){
                    alert("제목을 입력하세요.");
                } else if(content.val().trim()==""){
                    alert("내용을 입력하세요.");
                } else {
                    $(".writeFrm").submit();
                }
            } );

			//취소버튼 클릭
            $("#cencel").on("click", function(){
            	location.href="<c:url value="/customer/faq" />";
            });
        });
    </script>
</head>
<body>
    <section>
    <div class="pageTitle">
        <h1>1대1 문의</h1>
    </div> <!-- end:pageTitle -->
    <form class="writeFrm" action="" method="post" enctype="multipart/form-data">
        <div class="title">
            <input type="text" name="title">
        </div>
        <div class="content">
            <textarea name="content"></textarea>
        </div>
        <div class="attachment">
            <input type="file" name="attachment" multiple>
        </div>
        <div class="buttonGroup">
            <button type="button" class="btn btn-primary write-btn" id="cancel">취소</button>
            <button type="button" class="btn btn-primary write-btn" id="register">등록</button>
        </div>
    </form>
    </section>
</body>
</html>