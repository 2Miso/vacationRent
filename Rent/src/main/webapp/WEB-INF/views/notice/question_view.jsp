<!-- URL : /mypage/question/{noticeNo} -->
<%@page import="com.rent.vaca.notice.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="../include/header_nosearchbar.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>1대1 문의</title>
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
        section i{
            font-size:2em;
        }
        section{
            width:1024px;
            margin:0 auto;
        }
        .titleArea table{
            table-layout: fixed;
            width:1024px;
            margin:5px auto;
        }
        .titleArea tr{
            font-size:1.5em;
            border-bottom:1px solid var(--bs-orange);
        }
        .titleArea tr:first-child{
            border-top:1px solid var(--bs-orange);
        }
        .titleArea .title{
            width:80%;
            padding:25px;
        }
        .titleArea td{
            padding:20px 5px;
        }
        .titleArea .space{
            width:5%;
        }
        .titleArea .wdate{
            width:15%;
            color:gray;
        }
        .attachment{
            margin-top:23px;
        }
        #delete{
            float:right;
        }
	    footer{
	    	margin-top:150px;
	    }
    </style>
    <script>
        $(function(){
            $("#delete").on("click", function(){
                $(this).parent().submit();
            });
        });
    </script>
</head>
<body>
    <section>
    <p><a href="<c:url value="/mypage/myQnA" />"><i class="bi bi-arrow-left"></i></a></p>
    <div class="titleArea">
        <table>
            <tr>
                <td class="title">
                    ${question.title}
                </td>
                <td class="space"></td>
                <td class="wdate">
                    ${question.wdate}
                </td>
            </tr>
        </table>
    </div> <!-- end: .titleArea -->
    <h2>Q.</h2>
    <div class="content">
        ${question.content}
    </div> <!-- end: .content -->
    <div class="attachment">
    	<c:forEach var="file" items="${question.questionAttachList}">
        	<p><a href="<c:url value="/questions/${file.savedName}" />" download="${file.originalName}" style="color:gray;">${file.originalName}</a></p>
        </c:forEach>
    </div>
    <form action="" method="post">
        <button type="button" class="btn btn-primary write-btn" id="delete">삭제</button>
    </form>
    <c:if test="${question.answeryn == 'Y'}">
	    <h2 style="clear:both;">A.</h2>
	    <div class="answer">
	        ${question.answerContent}
	    </div> <!-- end: .answer -->
    </c:if>
    </section>
</body>
</html>
<%@include file="../include/footer.jsp" %>