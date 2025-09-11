<%@page import="com.rent.vaca.notice.NoticeVO"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header_nosearchbar.jsp" %>
<%
List<NoticeVO> list = (List<NoticeVO>)request.getAttribute("noticeList");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- 부트스트랩 css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <!-- 부트스트랩 아이콘 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <!-- 부트스트랩 글꼴 -->
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <!-- 오렌지 테마 css -->
    <link href="<c:url value="/resources/css/color_orange.css" />" rel="stylesheet" type="text/css">
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
        .pageTitle{
            width:1024px;
            margin:auto;
        }
        h1{
            display:inline-block;
        }
        .write-btn{
            position:relative;
            left:70%;
            bottom: 10px;
        }
        .listArea table{
            table-layout: fixed;
            width:1024px;
            margin:50px auto;
        }
        .listArea tr{
            font-size:1.5em;
            border-bottom:1px solid var(--bs-orange);
        }
        .listArea tr:first-child{
            border-top:1px solid var(--bs-orange);
        }
        .listArea td{
            padding:20px 5px;
        }
        .listArea .title{
            width:80%;
            overflow:hidden;
            text-overflow:ellipsis;
            white-space:nowrap;
        }
        .listArea .space{
            width:5%;
        }
        .listArea .wdate{
            width:15%;
            color:gray;
        }
        /* *{
            border:1px solid red;
        } */
    </style>
    <script>
        $(function(){
            $(".page-item").on( "click", function() {
                console.log(this);
            } );
        });
    </script>
</head>
<body>

    <section>
    <div class="pageTitle">
        <h1>공지사항</h1>
        <%-- <c:if test=${not empty sessionScope.user}"> --%>
        	<button type="button" onclick="location.href='<c:url value="/notice/write" />'" class="btn btn-primary write-btn">글쓰기</button>
        <%-- </c:if> --%>
    </div> <!-- end:pageTitle -->
    <div class="listArea">
        <table>
        <c:forEach var="notice" items="${noticeList}">
            <tr>
                <td class="title">
                    <a href="<c:url value="/notice/view/${notice.noticeNo}" />">
                    	${notice.title}
                    </a>
                </td>
                <td class="space"></td>
                <td class="wdate">
                    ${notice.wdate}
                </td>
            </tr>
           </c:forEach>
        </table>
    </div> <!-- end:listArea -->
    <div class="Page">
        <ul class="pagination justify-content-center">
            <li class="page-item"><a class="page-link" href="#">&lt;</a></li>
            <li class="page-item active"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item disabled"><a class="page-link" href="#">&gt;</a></li>
        </ul>
    </div> <!-- end:Page -->
    </section>
</body>
</html>
<%@ include file="../include/footer.jsp"%>