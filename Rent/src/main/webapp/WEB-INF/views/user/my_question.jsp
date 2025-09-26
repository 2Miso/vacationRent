<!-- URL : /mypage/myQnA -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header_nosearchbar.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 문의 내역</title>
    <link href="<c:url value="/resources/css/board_style.css" />" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        section h2{
            font-weight:bold;
            font-size:32px;
            margin-bottom:30px;
            margin:30px 0 30px 30px;
        }
        .content{
            margin-left:30px;
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
          <span class="fs-4" style="font-weight: bold;">일반 회원</span>
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
              <i class="bi bi-person-circle me-2"></i>내 문의 내역
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
        <h2>내 문의 내역</h2>
        <table class="board">
            <thead>
                <tr>
                    <th class="answer-no">답변여부</th>
                    <th class="title">제목</th>
                    <th class="answer-no">작성날짜</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="answer-done">답변완료</td>
                    <td><a href="#">질문내용의 제목ddddd</a></td>
                    <td class="answer-no">작성날짜</td>
                </tr>
                <tr>
                    <td class="answer-done">답변완료</td>
                    <td><a href="#">질문내용의 제목</a></td>
                    <td class="answer-no">작성날짜</td>
                </tr>
                <tr>
                    <td class="answer-no">답변대기</td>
                    <td><a href="#">질문내용의 제목</a></td>
                    <td class="answer-no">작성날짜</td>
                </tr>
                <tr>
                    <td class="answer-no">답변대기</td>
                    <td><a href="#">질문내용의 제목</a></td>
                    <td class="answer-no">작성날짜</td>
                </tr>
                <tr>
                    <td class="answer-no">답변대기</td>
                    <td><a href="#">질문내용의 제목</a></td>
                    <td class="answer-no">작성날짜</td>
                </tr>
            </tbody>
        </table>
    </div>
</section>
</body>
</html>
<%@include file="../include/footer.jsp" %>