<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header_nosearchbar.jsp" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>검색</title>
    <link href="size.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/@xpressengine/xeicon@2.3.3/xeicon.min.css" rel="stylesheet">
    <style>
    body {
        --bs-font-sans-serif:margin:0; padding:0;font-size:14px;line-height:1.6;font-family:'Pretendard','Noto Sans KR', 'Apple SD Gothic Neo', '돋움', Dotum, Arial, Sans-serif;color:#464646;letter-spacing:0;-webkit-text-size-adjust:none;font-weight: 400
    }
    .header_wrap{
        width:1024px;
        height:100px;
        margin: 0 auto;
    }


    
    .navbar{
       background-color:white !important;
    }
    section{
        width:1024px; 
        margin: 0 auto;
    }
    </style>
    <script>
		$(function(){
			//체크박스 전체 선택시 모든 체크박스 선택 또는 해제
			$("#everything").change(function(){
		        if ($(this).is(':checked')) {
		        	$(".form-check-input").prop('checked', true);
		        } else {
		        	$(".form-check-input").prop('checked', false);
		        }
			});
			
		});
    
    
        let category = false;
        function categorychangeFn(obj){
            if(category == false){
                $(obj).text("카테고리 접기");
                category=true;
            }else{
                $(obj).text("카테고리 펼치기")
                category=false;
            }
           
        }
        
        
    </script>
</head>
<body>
<section>
    <div class="d-flex" ><!--사이드바와 내용 수평정렬-->
        <div class="my-2 mx-2" style="width: 200px;"><!--사이드바 시작-->

            <div class="my-2"><!--정렬 순서 시작-->
               <h5 style="font-weight: bold;">정렬</h5>
                <select onchange="location.href=this.value;" class="form-select" >
                    <option value="이동할 페이지 경로">높은가격순</option>
                    <option value="이동할 페이지 경로">낮은가격순</option>
                    <option value="이동할 페이지 경로">별점높은순</option>
                </select> 
            </div><!--정렬 순서 끝-->
            
            <div class="my-2"><!--가격 시작-->
                <h5 style="font-weight: bold;">가격</h5>
                    <div class="d-flex align-items-center">
                        <input type="text" class="form-control " placeholder="최소">-
                        <input type="text" class="form-control " placeholder="최대">
                    </div>          
            </div><!--가격 끝-->
            
            <div class="my-2"><!--숙소유형 시작-->
                <h5 style="font-weight: bold;">숙소유형</h5>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="everything">
                    <label class="form-check-label" for="everything">  전체</label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="1" id="hotel">
                    <label class="form-check-label" for="hotel">  호텔</label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="2" id="motel">
                    <label class="form-check-label" for="motel">  모텔</label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="3" id="resort">
                    <label class="form-check-label" for="resort"> 리조트</label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="4" id="cottage">
                    <label class="form-check-label" for="cottage">  펜션</label>
                </div>
            </div><!--숙소유형 끝-->
        </div><!--사이드바 끝-->

        <div><!--내용 시작-->
            <div><!--숙소 시작-->
                <div class="d-flex align-items-center my-2" style=" width:800px;"><!--개별숙소리스트-->
                    <div style="background-color: rgb(99, 41, 94); width: 400px; height:200px; border-radius: 6px;">여기에 이미지 입력</div>
                    <div  class="mx-2">
                        <h5>숙소 이름 입력</h5>
                        <p>여기에 숙소 타입입력(호텔,모텔등등)</p>
                        <p>여기에 별점 입력</p>
                        <p>여기에 가격 입력 ex)500,000원</p>
                    </div>
                </div>

                <div class="d-flex align-items-center my-2" style=" width:800px;"><!--개별숙소리스트-->
                    <div style="background-color: rgb(99, 41, 94); width: 400px; height:200px; border-radius: 6px;">여기에 이미지 입력</div>
                    <div  class="mx-2">
                        <h5>숙소 이름 입력</h5>
                        <p>여기에 숙소 타입입력(호텔,모텔등등)</p>
                        <p>여기에 별점 입력</p>
                        <p>여기에 가격 입력 ex)500,000원</p>
                    </div>
                </div>

                <div class="d-flex align-items-center my-2" style=" width:800px;"><!--개별숙소리스트-->
                    <div style="background-color: rgb(99, 41, 94); width: 400px; height:200px; border-radius: 6px;">여기에 이미지 입력</div>
                    <div  class="mx-2">
                        <h5>숙소 이름 입력</h5>
                        <p>여기에 숙소 타입입력(호텔,모텔등등)</p>
                        <p>여기에 별점 입력</p>
                        <p>여기에 가격 입력 ex)500,000원</p>
                    </div>
                </div>

                <div class="d-flex align-items-center my-2" style=" width:800px;"><!--개별숙소리스트-->
                    <div style="background-color: rgb(99, 41, 94); width: 400px; height:200px; border-radius: 6px;">여기에 이미지 입력</div>
                    <div  class="mx-2">
                        <h5>숙소 이름 입력</h5>
                        <p>여기에 숙소 타입입력(호텔,모텔등등)</p>
                        <p>여기에 별점 입력</p>
                        <p>여기에 가격 입력 ex)500,000원</p>
                    </div>
                </div>

                <div class="d-flex align-items-center my-2" style=" width:800px;"><!--개별숙소리스트-->
                    <div style="background-color: rgb(99, 41, 94); width: 400px; height:200px; border-radius: 6px;">여기에 이미지 입력</div>
                    <div  class="mx-2">
                        <h5>숙소 이름 입력</h5>
                        <p>여기에 숙소 타입입력(호텔,모텔등등)</p>
                        <p>여기에 별점 입력</p>
                        <p>여기에 가격 입력 ex)500,000원</p>
                    </div>
                </div>
            </div><!--숙소 끝-->

            <div><!--페이지네이션 시작-->
                <nav aria-label="Page navigation example" style="margin: 0 auto;">
                    <ul class="pagination align-items-center" style="margin: 0 auto;">
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span></a>
                        </li>

                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>

                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span></a>
                        </li>
                    </ul>
                </nav>
            </div><!--페이지네이션 끝-->

        </div><!--숙소리스트끝-->
    </div>

</section>
</body>
</html>