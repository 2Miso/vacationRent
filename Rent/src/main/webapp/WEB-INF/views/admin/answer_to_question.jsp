<!-- URL : /admin/QnA/answer/{noticeNo} -->
<%@page import="com.rent.vaca.notice.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/header_nosearchbar.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문의내역 답변작성</title>
    <!-- fontawesome 아이콘 css -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="<c:url value="/resources/css/board_style.css" />" rel="stylesheet" type="text/css">
    <style>
        section a{
            text-decoration:none;
            color:inherit;
        }
        section a:hover{
            text-decoration:underline;
        }
        .listTitle, form, input:not([type=file]), textarea{
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
        .con{
            margin:30px 0 0 30px;
        }
    </style>
    <script>
        let content;

        $(function(){
            title = $("[name=title]");
            content = $("[name=answerContent]");
            $("#register").on( "click", function() {
                if(content.val().trim()==""){
                    alert("내용을 입력하세요.");
                } else {
                    $(".write").submit();
                }
            } );
            
            $("#cancel").click(function(){
            	location.href="${pageContext.request.contextPath}/admin/private_question";
            });
        });/* document.ready 끝 */
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
            <h1>문의 내역 답변작성</h1>
        </div> <!-- end:listTitle -->
        <div style="margin-left:70px;">${question.title}</div>
        <div style="margin-left:70px;">${question.content}</div>
        <form class="write" action="" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="exampleFormControlTextarea1" class="form-label">답변</label>
                <textarea name="answerContent" class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="답변을 입력해주세요."></textarea>
            </div>
            <div class="buttonGroup">
                <button type="button" class="btn btn-primary write-btn" id="cancel">취소</button>
                <button type="button" class="btn btn-primary write-btn" id="register">등록</button>
            </div>
        </form>
    </div>
</body>
</html>