<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>footer</title>
    <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <script src="https://code.jquery.com/jquery-3.7.1.slim.js" integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
     -->
    <script>
        
    </script>

    <style>
    footer {
        --bs-font-sans-serif:margin:0; padding:0;font-size:14px;line-height:1.6;font-family:'Pretendard','Noto Sans KR', 'Apple SD Gothic Neo', '돋움', Dotum, Arial, Sans-serif;color:#464646;letter-spacing:0;-webkit-text-size-adjust:none;font-weight: 400;
		margin-top:100px;
    }
    </style>
</head>

<body>
<footer style="height:500px;  background-color:#F2F2F2"><!--footer start-->

    <div style="height:10px;"></div><!--footer의 여백-->
    <div style="width:1024px; margin: 0 auto;"><!--footer 가운데 정렬용-->

        <div class="d-flex mb-3"><!--footer 상단부-->

            <div class="me-auto p-2" ><!--고객센터-->
                <h1 style="font-weight:bold; font-size:24px;">고객센터</h1>
                    <p>전화 운영시간 : xx시-xx시<br>채팅 상담 : 24시간</p>
                <div>
                    <button type="button" style="width:200px; height:50px; font-size:20px; border-radius:12px; border-width:0.5px;"><i class="bi bi-telephone-fill" style="margin-right:5px;"></i>0000-0000</button><!--링크를 걸어야 합니다-->
                    <button type="button" style="width:200px; height:50px; font-size:20px; border-radius:12px; border-width:0.5px;"><i class="bi bi-chat-fill" style="margin-right:5px;" ></i>채팅 상담</button><!--링크를 걸어야 합니다-->
                </div>
            </div>

            <div class="p-2"><!--회사소개-->
                <h1 style="font-weight:bold; font-size:24px;">회사</h1>
                <a href="#" style="text-decoration:none; color:black;">회사소개</a><!--링크를 걸어야 합니다-->
            </div>

            <div class="p-2"><!--서비스-->
                <h1 style="font-weight:bold; font-size:24px;">서비스</h1>
                <a href="#" style="text-decoration:none; color:black;">공지사항</a><br><!--링크를 걸어야 합니다-->
                <a href="#" style="text-decoration:none; color:black;">자주묻는질문</a><!--링크를 걸어야 합니다-->
            </div>

        </div><!--footer 상단부 끝-->
        

        <div class="me-auto p-2"><!--footer 하단부-->

            <div><!--회사상세내역소개-->
                (주)회사이름<br>
                주소 : 회사 주소| 대표이사 : 이름 | 사업자등록번호 : 사업자등록번호 입력<br>
                전자우편주소 : xxxx@xxxx.com | 통신판매번호 : 2017-서울강남-01779 | 관광사업자 등록번호 : 제1026-24호 | 전화번호 :xxxx-xxxx<br>
                호스팅서비스제공자의 상호 표시 : ㈜회사이름<br>
                (주)회사이름 통신판매중개자로서 통신판매의 당사자가 아니며, 상품의 예약, 이용 및 환불 등과 관련한 의무와 책임은 각 판매자에게 있습니다.<br>
            </div>

            <div class="d-flex mb-3" style="margin-top:24px; margin-bottom:24px;"><!--이용야관개인정보처리방침등등-->
                <a href="#" style="text-decoration:none; font-weight:bold; color:black; padding-right:40px;">이용약관</a><!--링크를 걸어야 합니다-->
                <a href="#" style="text-decoration:none; font-weight:bold; color:black; padding-right:40px;">개인정보처리방침</a><!--링크를 걸어야 합니다-->
                <a href="#" style="text-decoration:none; font-weight:bold; color:black; padding-right:40px;">소비자 분쟁해결 기준</a><!--링크를 걸어야 합니다-->
                <a href="#" style="text-decoration:none; font-weight:bold; color:black; padding-right:40px;">콘텐츠산업진흥법에 의한 표시</a><!--링크를 걸어야 합니다-->
            </div>

            <div><!--카피라이트-->
                Copyright xxxx Corp. All rights reserved
            </div>  

        </div><!--footer 하단부 끝-->
        
    </div>
</footer><!--footer end-->
</body>
</html>