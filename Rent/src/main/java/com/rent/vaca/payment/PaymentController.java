package com.rent.vaca.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rent.vaca.acco.AccoHasFacilVO;
import com.rent.vaca.reserv.ReservVO;

@Controller
public class PaymentController {
	
	private final KakaoPayService kakaoPayService;

    @Autowired
    public PaymentController(PaymentService paymentService, KakaoPayService kakaoPayService) {
        this.kakaoPayService = kakaoPayService;
    }
    
    // 보여지기용 화면
    @RequestMapping(value = "/payment/payment_ok", method = RequestMethod.GET)
    public String paymentOkPage() {
        return "payment/payment_ok";  // -> /WEB-INF/views/payment/payment_ok.jsp
    }
	
	// 결제 실패 화면
	@RequestMapping(value = "/payment/payment_fail", method = RequestMethod.GET)
	public String paymentFailPage() {
	    return "payment/payment_fail";
	}
    
	// 숙소 결제 화면
	@RequestMapping(value = "reservation/reserv_ok_payment", method = RequestMethod.GET)
	public String reservOkPayment() {
		return "reservation/reserv_ok_payment";
	}
	
	// 예약 취소 상세
	@RequestMapping(value = "/reservation/reserv_cancel", method = RequestMethod.GET)
	public String reservFailPage() {
	    return "reservation/reserv_cancel";
	}
	
	// 이용완료 숙소
	@RequestMapping(value = "/reservation/reserv_succes", method = RequestMethod.GET)
	public String reservSuccesPage() {
	    return "reservation/reserv_succes";
	}
	
	// 예약 내역 페이지
	@RequestMapping(value = "/reservation/reserv", method = RequestMethod.GET)
	public String reservPage() {
	    return "reservation/reserv";
	}
	
	// 숙소 결제 처리
	@RequestMapping(value = "reservation/reserv_ok_payment", method = RequestMethod.POST)
	public String reservOkPayment(
			@ModelAttribute ReservVO vo,
			@ModelAttribute AccoHasFacilVO vo1) {
		// 화면에서 전달한 데이터를 받아 결제 수단 조회
		
		int payment = vo.getPayment();
		
		// 0 : 계좌이체   1 : 카드결제   2 : 카카오페이
		
		switch (payment) {
        case PaymentServiceImpl.PAYMENT_ACCOUNT:
        	
        	// 계좌이체 API 받아오려면 사업자 필요
        	// 승인 났다고 가정하고 payment_ok로 이동
        	
            return "redirect:/payment/payment_ok";

        case PaymentServiceImpl.PAYMENT_CARD: // 카드 결제
            
        	// PG사 카드 결제 준비 API 호출
        	// 사업자가 있어야 하므로 승인 났다고 가정하고 payment_ok로 이동
        	
            return "redirect:/payment/payment_ok";

        case PaymentServiceImpl.PAYMENT_KAKAO: // 카카오페이
            // 카카오페이 결제 준비 API 호출
            String kakaoRedirectUrl = kakaoPayService.readyToPay(vo, vo1);
            return "redirect:" + kakaoRedirectUrl;

        default:
            // 잘못된 결제 수단 처리
            return "redirect:/payment/payment_fail";
		}
	}
	
	
}
