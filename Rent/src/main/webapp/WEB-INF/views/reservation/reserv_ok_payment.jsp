<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="Rent" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>예약 및 결제</title>
    <script src="<Rent:url value='/resources/js/jquery-3.7.1.min.js' />"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <link href="<Rent:url value ='/resources/css/color_orange.css' />" rel="stylesheet" type="text/css">
    <style>
        body {
            --bs-font-sans-serif;
            margin:0;
            padding:0;
            font-size:14px;
            line-height:1.6;
            font-family:'Pretendard','Noto Sans KR', 'Apple SD Gothic Neo', '돋움', Dotum, Arial, Sans-serif;
            color:#464646;
            letter-spacing:0;
            -webkit-text-size-adjust:none;
            font-weight:400;
        }
        .custom-kakaopay-button, .custom-kakaopay-button:hover,
        .custom-kakaopay-button:checked, #option3 + label, #option3:hover + label,
        #option3:checked + label{
        background-image: url('<Rent:url value="/resources/img/kakao_pay_logo.svg" />'); /* 준비한 로고 이미지 경로 */
        background-repeat: no-repeat;
        background-position:center;
        background-size:50%;
        background-color: #FFEB00; /* 카카오페이 주황색 배경 */
        padding: 19px 60px 19px 60px; /* 좌우 패딩 조정 */
        border:none;
        border-radius: 6px;
        }
        .payment{
            margin:20px 20px 20px 0;
            width:120px;
        }
        .form-select{
            width:400px;
            margin-bottom:20px;
        }
        #option1 + label, #option2 + label{
            background-color:var(--bs-orange)/* #FFC067 */;
            border:none;
        }
        #option1:checked + label, #option2:checked + label{
            outline:2px solid black;
            color:black;
            font-weight:bold;
        }
        #option3:checked + label{
            outline:2px solid black;
        }
        .mb-3{
            width:200px;
        }
        .total{
        	display:flex;
        }
        .content{
            margin:50px 0 0 200px;
        }
        .payinfo{
        	margin:50px 0 0 200px;
        }
        h2, h3{
            margin-bottom:20px;
        }
        .agree{
            display:flex;
            justify-content:space-between;
            margin-left:10px;
        }
        .form-check-label{
            width:160px;
            line-height:21.5px;
            margin-bottom:10px;
        }
        .bi{
            margin-left:10px;
        }
    </style>
    <script>
	    $(function () {
	        let $checkAll = $('#checkAll');
	        let $items = $('.item'); // 개별 약관 체크박스
	
	        $checkAll.on('change', function () {
	            $items.prop('checked', this.checked);
	        });
	
	        $items.on('change', function () {
	            let allChecked = $items.length === $items.filter(':checked').length;
	            $checkAll.prop('checked', allChecked);
	        });
	
	        // 카드 결제 선택 시 select 활성화
	        $("input[name='options-base']").on("change", function () {
	            if ($("#option2").is(":checked")) {
	                $("#card-select-container").show();
	            } else {
	                $("#card-select-container").hide();
	            }
	        });
	
	        // 초기에는 숨기기
	        $("#card-select-container").hide();
	
	        $("#payBtn").on("click", function (e) {
	        	e.preventDefault(); // submit 방지
	        	
	        	let name = $("input[name='name']").val().trim();
	            let phone = $("input[name='phone']").val().trim();

	            if (name === "") {
	                alert("예약자 이름을 입력해주세요.");
	                return;
	            }

	            if (phone === "") {
	                alert("핸드폰 번호를 입력해주세요.");
	                return;
	            }
	        	
	        	let allChecked =
	                $("#agreeRule").is(":checked") &&
	                $("#agreePersonal").is(":checked") &&
	                $("#agreeThird").is(":checked") &&
	                $("#agreeAge").is(":checked");
	
	            if (!allChecked) {
	                alert("약관에 모두 동의하셔야 합니다.");
	                return;
	            }
	            
	            // 모든 체크가 완료되었을 때 모달 열기
	            let confirmModal = new bootstrap.Modal(document.getElementById('exampleModal'));
	            confirmModal.show();
	        });
	        
	     		// 모달 내 '동의 후 결제' 버튼 클릭 시 폼 제출
	        	$("#confirmPayBtn").on("click", function () {
	            	$("#paymentForm").submit();
	        	});
	        
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
                <a href="#" class="nav-link text-white"><!--계정 설정 페이지로 링크 걸어야 합니다-->
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
    	<form id="paymentForm" action="<Rent:url value="/payment/payment_ok" />" method="post">
        <div class="total">
        <div class="content">
        	<input type="hidden" name="roomNo" value="${param.roomNo}">
        	체크인 : <input type="date" name="checkin"><br>
        	체크아웃 : <input type="date" name="checkout"><br>
        	성인 : <input type="number" name="adultNo"><br>
        	아동 : <input type="number" name="childNo"><br>
        	이메일 : <input typep="text" name="email"><br>
            <h2>예약 확인 및 결제</h2>
            <h3>예약자 정보</h3>
            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">예약자 이름</label>
                <input type="text" name="name" class="form-control" id="exampleFormControlInput1" placeholder="이름">
            </div>
            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">핸드폰 번호</label>
                <input type="text" name="phone" class="form-control" id="exampleFormControlInput2" placeholder="010-0000-0000">
            </div>
            <div>결제수단</div>
            <input type="radio" class="btn-check" name="payment" id="option1" value="0" autocomplete="off" checked>
            <label class="btn btn-primary payment" for="option1">계좌이체</label>

            <input type="radio" class="btn-check" name="payment" id="option2" value="1" autocomplete="off">
            <label class="btn btn-primary payment" for="option2">카드결제</label>

            <input type="radio" class="btn-check" name="payment" id="option3" value="2" autocomplete="off">
            <label class="btn custom-kakaopay-button" for="option3"></label>

            <div  id="card-select-container" style="display:none;">
	            <select class="form-select" aria-label="select example">
	                <option selected>카드를 선택해주세요.</option>
	                <option value="kb">국민카드</option>
	                <option value="nh">농협카드</option>
	                <option value="ss">삼성카드</option>
	                <option value="jb">전북카드</option>
	                <option value="sh">신한카드</option>
	                <option value="hd">현대카드</option>
	                <option value="lt">롯데카드</option>
	            </select>
	            <select class="form-select" aria-label="select example">
	                <option selected>일시불</option>
	                <option value="2">2개월</option>
	                <option value="3">3개월</option>
	                <option value="4">4개월</option>
	                <option value="5">5개월</option>
	                <option value="6">6개월</option>
	            </select>
           	</div>
        </div>
        <div><!--사이드바 시작-->
            <div class="payinfo" style="height:80vh;" >
                <div style="width: 280px;" class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" >
                    <div class="pay">숙소정보</div>
                    <div class="pay">결제정보</div>
                    <div style="margin-right:44px;" class="form-check-reverse">
                        <label style="margin-right:13px;" class="form-check-label" for="checkAll">약관 전체동의</label>
                        <input class="form-check-input" type="checkbox" value="" id="checkAll">
                    </div>
                    <div class="agree form-check-reverse">
                        <label class="form-check-label" for="checkAll">숙소 이용규칙 및 취소/환불규정 동의 (필수)</label>
                        <input class="form-check-input item" type="checkbox" value="" id="agreeRule">
                        <i class="bi bi-chevron-right" data-bs-toggle="modal" data-bs-target="#cancelModal"></i>
                        <!-- Modal -->
                        <div class="modal fade" id="cancelModal" tabindex="-1" aria-labelledby="cancelModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="cancelModalLabel">숙소 이용규칙 및 취소/환불규정 동의 (필수)</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body" style="height:500px; overflow-y:auto; text-align:left;">
                                        <p style="font-weight:bold;">이용규칙</p>
                                        <p>최대 인원 초과 시 입실이 불가합니다.</p>
                                        <p>정원 기준 요금 외 인원 추가 요금을 포함하여 조식, 침구, 침대 등의 추가 요금은 예약 시 옵션으로 선택하여 선결제하실 수 있습니다. 선결제 하지 않거나 예약 시 옵션에 포함되지 않은 추가 비용이 있을 시 이는 현장결제 대상입니다.</p>
                                        <p>제공 이미지는 배정된 객실과 다를 수 있습니다.</p>
                                        <p>제공 정보는 숙소의 사정에 따라 변경될 수 있습니다.</p>
                                        <p>미성년자는 보호자 동반 시 투숙이 가능합니다.</p>
                                        <p>반려동물은 숙소 규정에 따라 출입이 제한되오니 숙소별 상세정보를 꼭 확인해 주세요.</p>
                                        <p>체크인 시 배정의 경우, 객실과 베드 타입을 보장하지 않습니다.</p>
                                        <p>객실 타입에 시간이 별도 기재된 경우, 체크인/아웃 시간이 상이할 수 있습니다.</p>
                                        <p>업체 현장에서 객실 컨디션 및 서비스로 인해 발생된 분쟁은 여기어때에서 책임지지 않습니다.</p>
                                        <div style="font-weight:bold;">취소/환불규정</div>
                                        <p>OO에서 판매되는 국내 호텔/리조트/펜션/게스트하우스/캠핑/홈앤빌라 상품은 예약/결제 후 10분 이내에는 무료취소 가능합니다. (단, 체크인 시간 경과 시 취소불가)</p>
                                        <p>숙소 사정에 의해 취소 발생 시 100% 환불이 가능합니다.</p>
                                        <p>예약 상품 별 숙소 정보에 기재된 취소, 환불 규정을 반드시 확인 후 이용해주시기 바랍니다.</p>
                                        <p>예약/결제 10분 이후의 취소는 업체의 취소/환불 규정에 의거하여 적용됩니다.</p>
                                        <p>취소, 변경 불가 상품은 규정과 상관없이 취소, 변경이 불가합니다.</p>
                                        <p>당일 결제를 포함한 체크인 당일 취소는 취소, 변경이 불가합니다.</p>
                                        <p>숙소의 객실 및 숙소 정보의 최신성을 유지하기 위해 정보가 수시로 변경될 수 있음에 유의 바랍니다.</p>
                                        <p>취소/환불 규정에 따라 취소 수수료가 발생한 경우, 취소 수수료는 판매가(객실가격+추가옵션요금) 기준으로 계산됩니다.</p>
                                        <p>객실과 추가옵션의 취소/환불 규정은 동일합니다.</p>
                                        <p>추가옵션만 취소는 불가합니다.</p>
                                    </div>
                                </div>
                            </div>
                        </div><!--Modal end-->
                    </div>
                    <div class="agree form-check-reverse">
                        <label class="form-check-label" for="checkAll">개인정보 수집 및 이용 동의 (필수)</label>
                        <input class="form-check-input item" type="checkbox" value="" id="agreePersonal">
                        <i class="bi bi-chevron-right" data-bs-toggle="modal" data-bs-target="#useModal"></i>
                        <!-- Modal -->
                        <div class="modal fade" id="useModal" tabindex="-1" aria-labelledby="useModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="useModalLabel">개인정보 수집 및 이용 (필수)</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body" style="height:500px; overflow-y:auto; text-align:left;">
                                         <p style="font-weight:bold;">수집 목적</p>
                                         <p>예약/구매 서비스 제공 상담 및 부정거래 기록 확인</p>
                                         <p style="font-weight:bold;">수집 항목</p>
                                         <p>[예약구매]</p>
                                         <p>예약자 정보(이름, 휴대전화번호)</p>
                                         <p>[결제]</p>
                                         <p>거래내역</p>
                                         <p>*결제 시 개인정보는 PG사(결제대행업체)에서 수집 및 저장하고 있으며, 회사는 PG사에서 제공하는 거래 내역만 제공받음</p>
                                         <p>[거래명세서 발급]</p>
                                         <p>이메일주소</p>
                                         <p>[현금영수증 발급]</p>
                                         <p>휴대전화번호, 이메일주소</p>
                                         <p>[취소·환불]</p>
                                         <p>은행명, 계좌번호, 예금주명</p>
                                         <p style="font-weight:bold;">보유 및 이용기간</p>
                                         <p>- 회원 탈퇴 시 까지</p>
                                         <p>* 관계 법령에 따라 보존할 필요가 있는 경우 해당 법령에서 요구하는 기한까지 보유</p>
                                         <p>※ 위 동의 내용을 거부하실 수 있으나, 동의를 거부하실 경우 서비스를 이용하실 수 없습니다.</p>
                                         <p>※ 개인정보 처리와 관련된 상세 내용은 '개인정보처리방침'을 참고</p>
                                    </div>
                                </div>
                            </div>
                        </div><!--Modal end-->
                    </div>
                    <div class="agree form-check-reverse">
                        <label class="form-check-label" for="checkAll">개인정보 제3자 제공 동의 (필수)</label>
                        <input class="form-check-input item" type="checkbox" value="" id="agreeThird">
                        <i class="bi bi-chevron-right" data-bs-toggle="modal" data-bs-target="#agreeModal"></i>
                        <!-- Modal -->
                        <div class="modal fade" id="agreeModal" tabindex="-1" aria-labelledby="agreeModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="agreeModalLabel">개인정보 제3자 제공 동의 (필수)</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body" style="height:500px; overflow-y:auto; text-align:left;">
                                         <p style="font-weight:bold;">제공받는 자</p>
                                         <p>${acco.name}</p>
                                         <p style="font-weight:bold;">제공 목적</p>
                                         <p>숙박예약서비스 이용계약 이행(서비스 제공, 확인, 이용자 정보 확인)</p>
                                         <p style="font-weight:bold;">제공하는 항목</p>
                                         <p>예약한 숙박서비스의 이용자 정보(예약자 이름, 휴대폰번호, 예약번호, 예약 업체명, 예약한 객실명, 결제금액)</p>
                                         <p style="font-weight:bold;">제공받는 자의 개인정보 보유 및 이용기간</p>
                                         <p>예약서비스 제공 완료 후 6개월</p>
                                         <p>※ 위 동의 내용을 거부하실 수 있으나, 동의를 거부하실 경우 서비스를 이용하실 수 없습니다.</p>
                                         <p>※ 개인정보 처리와 관련된 상세 내용은 '개인정보처리방침'을 참고</p>
                                    </div>
                                </div>
                            </div>
                        </div><!--Modal end-->
                    </div>
                    <div class="agree form-check-reverse">
                        <label class="form-check-label" for="checkAll">만 14세 이상 확인 (필수)</label>
                        <input class="form-check-input item" type="checkbox" value="" id="agreeAge">
                        <i class="bi bi-chevron-right" data-bs-toggle="modal" data-bs-target="#ageModal"></i>
                        <!-- Modal -->
                        <div class="modal fade" id="ageModal" tabindex="-1" aria-labelledby="ageModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="ageModalLabel">만 14세 이상 확인 (필수)</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body" style="height:500px; overflow-y:auto; text-align:left;">
                                         <p style="color:red;">OO는 만 14세 미만 아동의 서비스 이용을 제한하고 있습니다.</p>
                                         <p>개인정보 보호법에는 만 14세 미만 아동의 개인정보 수집 시 법정대리인 동의를 받도록 규정하고 있으며, 만 14세 미만 아동이 법정대리인 동의없이 서비스 이용이 확인된 경우 서비스 이용이 제한될 수 있음을 알려드립니다.</p>
                                    </div>
                                </div>
                            </div>
                        </div><!--Modal end-->
                    </div>
                    <button id="payBtn" class="btn btn-primary">결제하기</button>
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">예약내역 확인</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div>
                                        <div>${acco.name}</div>
                                        <div>${room.name}</div>
                                        <hr>
                                        <div>체크인 ${acco.checkin} - 체크아웃 ${acco.checkout}</div>
                                        <ul>
                                            <li>19세 미만 청소년은 보호자 동반 시 투숙이 가능합니다.</li>
                                            <li><span style="color:red;">취소/환불 규정</span>에 따라 웹내에서 예약취소 가능한 상품입니다. 예약취소 시 취소수수료가 발생할 수 있습니다.</li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                                    	<button id="confirmPayBtn" type="submit" class="btn btn-primary">동의 후 결제</button>
                                	
                                </div>
                            </div>
                        </div>
                    </div><!--Modal end-->
                </div>
            </div>
            </div><!--사이드바 끝-->
        </div>
		</form>
    </section>
</body>
</html>