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
    <style>
        header{
        	margin-bottom:50px;
        }
        section a{
            text-decoration:none;
            color:inherit;
        }
        section a:hover{
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
	    footer{
	    	margin-top:150px;
	    }
    </style>
    <script>
        let title;
        let content;

        $(function(){
            title = $("[name=title]");
            content = $("[name=content]");
            
            //등록버튼 클릭
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
            $("#cancel").on("click", function(){
            	location.href="<c:url value="/notice/list" />";
            });
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