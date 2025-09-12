<%@page import="com.rent.vaca.user.UserVO"%>
<%@page import="com.rent.vaca.notice.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
UserVO user = new UserVO();
user.setId(1);
session.setAttribute("user", user);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/color_orange.css" />">
    <script src="<c:url value="/resources/js/jquery-3.7.1.min.js" />"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <style>
        a{
            text-decoration:none;
            color:inherit;
        }
        a:hover{
        	text-decoration:underline;
        }
        i{
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
        /* *{
            border:1px solid red;
        } */
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
    <p><a href="<c:url value="#" />"><i class="bi bi-arrow-left"></i></a></p>
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