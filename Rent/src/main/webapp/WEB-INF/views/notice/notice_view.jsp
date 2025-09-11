<%@page import="com.rent.vaca.notice.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
NoticeVO board = (NoticeVO)request.getAttribute("notice");
%>
<%@include file="../include/header_nosearchbar.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <script src="<c:url value="/resources/js/jquery-3.7.1.min.js" />"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <style>
        a{
            text-decoration:none;
            color:inherit;
        }
        a:hover{
            text-decoration: underline;
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
            margin:50px auto;
        }
        .titleArea tr{
            font-size:1.5em;
            border-bottom:1px solid var(--bs-orange);
        }
        .titleArea tr:first-child{
            border-top:1px solid var(--bs-orange);
        }
        .titleArea td{
            padding:20px 5px;
            vertical-align:top;
        }
        .titleArea .title{
            width:80%;
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
        /* *{
            border:1px solid red;
        } */
    </style>
</head>
<body>
    <section>
    <p><a href="#"><i class="bi bi-arrow-left"></i></a></p>
    <div class="titleArea">
        <table>
            <tr>
                <td class="title">
                    ${notice.title}
                </td>
                <td class="space"></td>
                <td class="wdate">
                    ${notice.wdate}
                </td>
            </tr>
        </table>
    </div> <!-- end: .titleArea -->
    <div class="content">
        ${notice.content}
    </div> <!-- end: .content -->
    </section>
</body>
</html>
<%@ include file="../include/footer.jsp"%>