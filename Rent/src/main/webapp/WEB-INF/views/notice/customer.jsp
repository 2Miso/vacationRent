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
    <!-- 부트스트랩 아이콘 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <!-- 부트스트랩 글꼴 -->
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
	<!-- 오렌지테마 css -->
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
        section{
            padding: 50px 0;
        }
        .pageTitle{
            width:1024px;
            margin:auto;
        }
        .csInfo{
            width:1000px;
            height:200px;
            border-radius:20px;
            background-color: lightgray;
            margin:40px auto;
            vertical-align: center;
            padding:20px 40px;
            position:relative;
        }
        .csInfo .bi-telephone-fill{
            font-size:6em;
            position:relative;
            bottom:15px;
        }
        .csInfo .text, .csInfo .write-q{
            display:inline-block;
            padding-left:40px;
        }
        .write-q{
        	position:absolute;
        	top:83px;
        	right:70px;
        }
        .csInfo .phoneNo{
            font-size:5em;
        }
        .faqContainer{
            width:1024px;
            margin:0 auto;
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
        <h1>고객센터</h1>
    </div> <!-- end:pageTitle -->
    <div class="csInfo">
        <i class="bi bi-telephone-fill"></i>
        <div class="text">
            <span class="phoneNo">0000-0000</span><br>
            <p><strong class="phoneTime">전화상담가능 시간 : </strong><span>xx시 - xx시</span></p>
        </div>
        <div class="write-q">
			<button type="button" class="btn btn-primary" onclick="location.href='<c:url value="/customer/question" />'">문의하기</button>
        </div>
    </div> <!-- end:csInfo -->

    <div class="faqContainer">
    <h3>자주 묻는 질문</h3>
    <div class="accordion" id="accordionExample">
        <div class="accordion-item">
        <h2 class="accordion-header">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
            예약을 취소하고 싶어요.
            </button>
        </h2>
        <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
            <div class="accordion-body">
            예약취소는 <strong>마이페이지 > 예약내역확인</strong>에서 직접 가능합니다.<br><br>

            예약/결제 진행 당시 안내된 취소/환불 규정에 따라 처리됩니다.
            일부 숙소에 한해 취소가 가능한 시점이 다르거나, 취소가 불가할 수 있으니 이 경우에는 1대1 문의로 요청해 주시길 바랍니다.
            </div>
        </div>
        </div>
        <div class="accordion-item">
        <h2 class="accordion-header">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
            체크인날짜/객실타입을 변경하고 싶어요.
            </button>
        </h2>
        <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
            <div class="accordion-body">
            예약 결제 완료 후 날짜 및 객실타입 등 부분 변경은 불가합니다.<br>
            예약취소와 동일하게 취소 및 환불 규정에 따라 처리되므로 예약취소가 가능한 기간에는 <strong>예약취소 후 재결제</strong> 하셔서 이용 부탁드립니다.<br>
            </div>
        </div>
        </div>
        <div class="accordion-item">
        <h2 class="accordion-header">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
            회원탈퇴는 어떻게 하나요?
            </button>
        </h2>
        <div id="collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
            <div class="accordion-body">
            회원탈퇴는 아래 방법을 통해 가능하며,  반드시 주의사항을 확인 후 진행해 주시기 바랍니다.<br><br>

            <strong>- 내정보 > 내정보 관리 > 회원탈퇴</strong><br><br>

            *주의사항
            · 등록된 리뷰는 자동으로 삭제되지 않습니다. 삭제를 원하실 경우 탈퇴 전 개별적으로 삭제해 주시기 바랍니다.
            </div>
        </div>
        </div>
    </div> <!-- end: 아코디언 -->
    <!-- <div class="Page">
        <ul class="pagination justify-content-center">
            <li class="page-item"><a class="page-link" href="#">&lt;</a></li>
            <li class="page-item active"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item disabled"><a class="page-link" href="#">&gt;</a></li>
        </ul>
    </div> --> <!-- end:Page -->
    
    </div>
    </section>
</body>
</html>
<%@ include file="../include/footer.jsp" %>