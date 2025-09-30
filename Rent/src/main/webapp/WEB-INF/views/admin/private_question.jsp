<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header_nosearchbar.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <style>
        hr {
            width: 750px;
            margin: 0 auto;
        }
        a {
            margin: 0 auto;
            text-decoration: none;
            color: black;
            width: 100%;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .title {
            margin: 0 auto 5px auto;
            font-weight: bold;
        }
        .answer-done {
            font-weight: bold;
            color: orange;
            margin: 0 20px;
        }
        .answer-no {
            font-weight: bold;
            margin: 0 20px;
        }
        .check {
            margin: 0 20px 0 28px;
        }
        table.board {
            width: 900px;
            margin: 0 auto;
            border-collapse: collapse;
            table-layout: fixed;
            border:none;
        }
        table.board th,
        table.board td {
            height:45px;
            border:none;
            vertical-align: middle; 
            text-align: center;
        }
        table.board td a {
            text-align: left;
            padding: 0 5px;
        }
        table.board td.form-check {
            width: 50px;
            padding: 0;
            margin: 0;
            display: table-cell;
            border-bottom: 1px solid #ddd;
            vertical-align: middle;
        }
        table.board thead th {
            border-bottom: 1px solid #ddd;
        }

        table.board tbody tr td {
            border-bottom: 1px solid #ddd;
        }
        .content{
            margin:30px 0 0 30px;
        }
        h2{
            margin-bottom:30px;
            font-weight:bold;
        }
    </style>
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
                    <i class="bi bi-house me-2  "></i>예약 내역
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link text-white"><!--객실 관리 페이지로 링크 걸어야 합니다-->
                    <i class="bi bi-file me-2"></i>찜 목록
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link text-white"><!--예약자 관리 페이지로 링크 걸어야 합니다-->
                    <i class="bi bi-people me-2"></i>내 정보 관리
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link active"><!--계정 설정 페이지로 링크 걸어야 합니다-->
                    <i class="bi bi-person-circle me-2"></i>1대1 문의
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
        <div class="content">
            <h2>1대1 문의 내역 조회</h2>
            <table class="board">
                <thead>
                    <tr>
                    <th>문의 회원</th>
                    <th>회원 타입</th>
                    <th>문의 제목</th>
                    <th>답변</th>
                    <th>답변여부</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="question" items="${list}">
                    <tr>
                        <td>${question.user.nickname}</td>
                        <td>개인회원</td>
                        <td>${question.title}</td>
                    <c:choose>
                      <c:when test='${question.answeryn=="Y"}'>
                        <td><button class="btn btn-primary" style="background-color:white; color:var(--bs-orange);" onclick="location.href='${pageContext.request.contextPath}/admin/QnA/answer/${question.noticeNo}'">답변완료</button></td>
                      </c:when>
                      <c:otherwise>
                        <td><button class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/admin/QnA/${question.noticeNo}'">답변하기</button></td>
                      </c:otherwise>                        
                    </c:choose>
                        <td>${question.answeryn}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</body>
</html>