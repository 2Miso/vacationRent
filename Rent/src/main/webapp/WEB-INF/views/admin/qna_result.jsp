<%@page import="com.rent.vaca.notice.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문의내역 답변작성</title>
    <!-- css 초기화 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <!-- 부트스트랩 css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
	<!-- 부트스트랩 아이콘 css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <!-- fontawesome 아이콘 css -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- 글꼴 css -->
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <!-- 오렌지 테마 css -->
    <link href="<c:url value="/resources/css/color_orange.css" />" rel="stylesheet" type="text/css">
    <!--  -->
    <link href="<c:url value="/resources/css/board_style.css" />" rel="stylesheet" type="text/css">
    <!-- jQuery -->
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
        .listTitle, .answer{
            width:1024px;
            margin:10px auto;
        }
        .buttonGroup{
            float:right;
        }
        .con{
            margin:30px 0 0 30px;
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
        let title;
        let content;

        $(function(){
            title = $("[name=title]");
            content = $("[name=content]");
            $("#delete").on( "click", function() {
                
            } );
        });
    </script>
</head>
<body>
    <section class="d-flex">
    <div><!--사이드바 시작-->
        <div class="d-flex" style="height:100vh;" >
            <div style="width: 280px;" class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" >
        <a class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
            <i class="bi bi-gear me-2" style="font-size:24px;"></i>
            <span class="fs-4" style="font-weight: bold;">관리자 페이지</span>
        </a>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li class="nav-item"> 
            <a href="#" class="nav-link text-white" aria-current="page"><!--숙소 관리 페이지로 링크 걸어야 합니다--> <!--aria current가 현재 표시되는 페이지를 강조합니다-->
                <i class="bi bi-house me-2  "></i>비즈니스 회원 승인
            </a>
            </li>
            <li>
            <a href="#" class="nav-link text-white"><!--객실 관리 페이지로 링크 걸어야 합니다-->
                <i class="bi bi-file me-2"></i>회원 관리
            </a>
            </li>
            <li>
            <a href="#" class="nav-link active"><!--예약자 관리 페이지로 링크 걸어야 합니다-->
                <i class="bi bi-people me-2"></i>1대1 문의 내역
            </a>
            </li>
        </ul>
        <hr>
        <div class="dropdown">
            <a href="#" class="nav-link text-white">
                <i class="bi bi-house-fill"></i><!--홈으로 이동하는 페이지로 링크 걸어야 합니다-->
                홈으로
            </a>
        </div>
        </div>
        </div>
    </div><!--사이드바 끝-->

    <div class="con">
        <div class="listTitle">
        	<i class="bi bi-arrow-left" onclick="location.href='${pageContext.request.contextPath}/admin/private_question'"></i><h1 style="display:inline-block; margin-left:20px;">문의 내역 답변작성</h1>
        </div> <!-- end:listTitle -->
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
    <c:if test="${question.answeryn == 'Y'}">
	    <h2 style="clear:both;">A.</h2>
	    <div class="answer">
	        ${question.answerContent}
	    </div> <!-- end: .answer -->
    </c:if>
    </div>
</body>
</html>