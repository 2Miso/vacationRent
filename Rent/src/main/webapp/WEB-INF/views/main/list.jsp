<%@page import="com.rent.vaca.search.SearchVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    .searchBtn{
    	position:relative;
    	top:35px;
    	left: 140px;
    }
    </style>
    <script>
		$(function(){
			let priceFlag = true;
			let dateFlag = true;
			
			//체크박스 전체 선택시 모든 체크박스 선택 또는 해제
			$("#everything").change(function(){
		        if ($(this).is(':checked')) {
		        	$(".form-check-input").prop('checked', true);
		        } else {
		        	$(".form-check-input").prop('checked', false);
		        }
			});
			//전체 체크박스 외 모든 체크박스 선택 시 전체 체크박스도 선택되기
			if($(".form-check-input:checked").not('#everything').length == 4){
				$("#everything").prop('checked', true);
			}
			
			//입력한 검색어가 있다면 해당 input에 나타나기
			<c:if test="${not empty search.text}">
				$("[name=text]").val('${search.text}');
			</c:if>
			<c:if test="${not empty search.checkIn and not empty search.checkOut}">
				$(".date input:eq(0)").val('${search.checkIn}');
				$(".date input:eq(1)").val('${search.checkOut}');
			</c:if>
			<c:if test="${not empty search.head}">
				$("[name=head]").val(${search.head});
			</c:if>
			<c:if test="${not empty search.priceLow and not empty search.priceHigh}">
				$(".price input:eq(0)").val(${search.priceLow});
				$(".price input:eq(1)").val(${search.priceHigh});
			</c:if>
			<c:if test="${not empty search.type}">
				<c:forEach var="t" items="${search.type}">
				$(".typeCheck input[value=${t}]").prop('checked', true);
				</c:forEach>
			</c:if>
			<c:if test='${not empty search.orderBy}'>
				$("[value=${search.orderBy}]").prop('selected', true);
			</c:if>

			//오늘 이전 날짜는 비활성화
		    let dtToday = new Date();
    
		    let month = dtToday.getMonth() + 1;
		    let day = dtToday.getDate();
		    let year = dtToday.getFullYear();
		    if(month < 10)
		        month = '0' + month.toString();
		    if(day < 10)
		        day = '0' + day.toString();
		    
		    let maxDate = year + '-' + month + '-' + day;
		
		    $('.date input').attr('min', maxDate);
			
    		//체크인날짜<체크아웃날짜 검증
    		$(".date input").change(function(){
    			dateFlag = false;
    			if($(".date input:eq(0)").val()!="" && $(".date input:eq(1)").val()!=""){ //체크인, 체크아웃 날짜 둘 다 입력 시
    				dateFlag = $(".date input:eq(0)").val()<$(".date input:eq(1)").val(); //전후 옳으면 dateFlag = true
	    			if(!dateFlag){
	    				/* alert('체크아웃 날짜는 체크인 날짜보다 후여야 합니다.'); */
	    			}
    			} else if ($(".date input:eq(0)").val()=="" && $(".date input:eq(1)").val()==""){ //날짜 선택 안 했을 시
    				dateFlag = true;
    			}
    		});
    		
    		//가격, 인원엔 숫자만 입력 가능
    		$(".price input").add($("[name=head]")).keyup(function(){
    			let value = $(this).val();
    			let regex = /[^0-9]/g;
    			let onlyNumber = value.replace(regex, '');
    			
    			if (value != onlyNumber) {
    		        $(this).val(onlyNumber);
    		    }
    		});
    		
    		//최저가격<최대가격 검증
    		$(".price input").blur(function(){
    			priceFlag = false;
    			let priceLow = $("[name=priceLow]");
				let priceHigh = $("[name=priceHigh]");
				
				if(priceLow.val() != "" && priceHigh.val() != "" && Number(priceLow.val()) > Number(priceHigh.val())){
					/* alert('최대가격을 최저가격보다 높게 입력해야 합니다.'); */
				} else {
					priceFlag = true;
				}
    		});
    		
    		//날짜, 가격 앞뒤 맞을 때 form 제출
    		$(".searchBtn").on("click", function(){
    			if(priceFlag && dateFlag){
    				$("form").submit();
    			} else if (dateFlag==false){
    				alert('체크인/체크아웃 날짜를 확인하세요.');
    			} else if (priceFlag==false){
    				alert('최대가격을 최저가격보다 높게 입력해야 합니다.');
    			}
    		});
			
    		//목록 정렬기준 변경
	        $(".orderBy").on("change", function(){
	        	$(".searchBtn").trigger('click');
	        });
		});/* document.ready 끝 */
    
    
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
            <form action="" method="get">
            <div class="my-2"><!--정렬 순서 시작-->
               <h5 style="font-weight: bold;">정렬</h5>
                <select name="orderBy" class="form-select orderBy" >
                    <option value="popular">별점높은순</option>
                    <option value="highest">높은가격순</option>
                    <option value="lowest">낮은가격순</option>
                </select> 
            </div><!--정렬 순서 끝-->

            <button class="btn btn-primary searchBtn" type="button">검색</button>

            <div class="my-2"><!--검색어 시작-->
                <h5 style="font-weight: bold;">검색</h5>
                    <div class="d-flex align-items-center">
                        <input name="text" type="text" class="form-control " placeholder="검색하기">
                    </div>          
            </div><!--검색어 끝-->
            <div class="my-2"><!--날짜 시작-->
                <h5 style="font-weight: bold;">숙박일</h5>
                    <div class="d-flex flex-wrap align-items-center date">
                        <input name="checkIn" type="date" class="form-control" style="margin-bottom:5px;">
                        <input name="checkOut" type="date" class="form-control">
                    </div>          
            </div><!--날짜 끝-->
            <div class="my-2"><!--투숙인원 시작-->
                <h5 style="font-weight: bold;">투숙인원</h5>
                    <div class="d-flex align-items-center">
                        <input name="head" type="text" class="form-control " placeholder="인원(명)">
                    </div>
            </div><!--투숙인원 끝-->
            <div class="my-2"><!--가격 시작-->
                <h5 style="font-weight: bold;">가격</h5>
                    <div class="d-flex align-items-center price">
                        <input name="priceLow" type="text" class="form-control " placeholder="최소">-
                        <input name="priceHigh" type="text" class="form-control " placeholder="최대">
                    </div>          
            </div><!--가격 끝-->
            
            <div class="my-2 typeCheck"><!--숙소유형 시작-->
                <h5 style="font-weight: bold;">숙소유형</h5>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="everything">
                    <label class="form-check-label" for="everything">  전체</label>
                </div>

                <div class="form-check">
                    <input name="type" class="form-check-input" type="checkbox" value="1" id="hotel">
                    <label class="form-check-label" for="hotel">  호텔</label>
                </div>

                <div class="form-check">
                    <input name="type" class="form-check-input" type="checkbox" value="2" id="motel">
                    <label class="form-check-label" for="motel">  모텔</label>
                </div>

                <div class="form-check">
                    <input name="type" class="form-check-input" type="checkbox" value="3" id="resort">
                    <label class="form-check-label" for="resort"> 리조트</label>
                </div>

                <div class="form-check">
                    <input name="type" class="form-check-input" type="checkbox" value="4" id="cottage">
                    <label class="form-check-label" for="cottage">  펜션</label>
                </div>
            </div><!--숙소유형 끝-->
            </form>
        </div><!--사이드바 끝-->

        <div><!--내용 시작-->
            <div><!--숙소 시작-->
            	<c:choose>
	            	<c:when test='${empty accoList}'>
	            		<div class="align-items-center my-2" style=" width:800px;">
	            			<div style=""><img style="width:800px;" src="<c:url value="/resources/img/empty_bag.jpg" />" alt="빈 가방"></div>
	            			<div style="width: 100%; font-size:2em; text-align:center;">검색결과가 0건입니다.</div>
	            		</div>
	            	</c:when>
	            	<c:otherwise>
		            	<c:forEach var="acco" items="${accoList}">
			                <div class="d-flex align-items-center my-2" style=" width:800px; box-shadow:0 0 10px rgba(0, 0, 0, 0.1);" onclick="location.href='${pageContext.request.contextPath}/acco/view/${acco.accoNo}'"><!--개별숙소리스트-->
			                    <div style="background-color: rgb(99, 41, 94); width: 400px; height:200px; border-radius: 6px; overflow:hidden;">
									<img src="${pageContext.request.contextPath}/resources/img/${acco.thumbnail}" alt="${acco.name} 썸네일" style="width:100%; height:100%; object-fit:cover;">
								</div>
								
			                    <div  class="mx-2">
			                        <h5>${acco.name}</h5>
			                        <p>${acco.typeKo}</p>
			                        <p>
			                        <c:if test="${not empty acco.starAvg}">
			                        	<span style="color:orange;">★</span><fmt:formatNumber value="${acco.starAvg}" pattern="0.0"/>
			                       	</c:if>
			                        </p>
			                        <p><fmt:formatNumber value="${acco.price}" type="currency" currencyCode="KRW" currencySymbol="￦"/></p>
			                    </div>
			                </div>
						</c:forEach>
	            	</c:otherwise>
            	</c:choose>
            </div><!--숙소 끝-->

            <div><!--페이지네이션 시작-->
                <nav aria-label="Page navigation example" style="margin: 0 auto;">
                    <ul class="pagination justify-content-center" style="margin: 0 auto;">
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