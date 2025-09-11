<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/header_nosearchbar.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- 부트스트랩 css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
	<!-- 오렌지 테마 css -->
    <link href="<c:url value="/resources/css/color_orange.css" />" rel="stylesheet" type="text/css">
    <!-- jQuery js -->
	<script src="<c:url value="/resources/js/jquery-3.7.1.min.js" />"></script>
	<!-- 부트스트랩 js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <style>
        a{
            text-decoration:none;
            color:inherit;
        }
        a:hover{
            text-decoration:underline;
        }
        .pageTitle, form, input:not([type=file]), textarea{
            width:1024px;
            margin:10px auto;
        }
        textarea{
            height: 500px;
            resize:none;
        }
        .buttonGroup{
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

        });
    </script>
</head>
<body>
    <section>
    <div class="pageTitle">
        <h1>공지사항</h1>
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
<%@ include file="../include/footer.jsp"%>