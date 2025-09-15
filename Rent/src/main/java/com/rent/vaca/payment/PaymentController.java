package com.rent.vaca.payment;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rent.vaca.acco.AccoHasFacilVO;
import com.rent.vaca.reserv.ReservVO;

@Controller
public class PaymentController {
	
	private final PaymentService paymentService;
	private final KakaoPayService kakaoPayService;

    @Autowired
    public PaymentController(PaymentService paymentService, KakaoPayService kakaoPayService) {
        this.paymentService = paymentService;
		this.kakaoPayService = kakaoPayService;
    }
	
	// 결제 실패 화면
	@RequestMapping(value = "/payment/payment_fail", method = RequestMethod.GET)
	public String paymentFailPage() {
	    return "payment/payment_fail";
	}
    
	// 숙소 결제 화면
	@RequestMapping(value = "/reservation/reserv_ok_payment", method = RequestMethod.GET)
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
	
	// 결제 완료
	@RequestMapping(value = "/payment/payment_ok", method = RequestMethod.GET)
	public String paymentOkPage() {
	    return "payment/payment_ok"; // 뷰 이름 리턴
	}
	
	// 숙소 결제 처리
	@RequestMapping(value = "/payment/payment_ok", method = RequestMethod.POST)
	public String reservOkPayment(
			@ModelAttribute ReservVO vo, HttpSession session) {
		// 화면에서 전달한 데이터를 받아 결제 수단 조회
		
		
		String reservCode = RandomCodeGenerator.generateRandomCode(8);
	    vo.setReservCode(reservCode);

	    // 임시 값
	    vo.setUserId(1);  // 임의의 사용자 ID
	    vo.setRoomNo(1); // 임의의 객실 번호
	    vo.setCheckin("2025-09-20 15:00:00");  // 체크인 임시값 (MySQL timestamp 형식)
	    vo.setCheckout("2025-09-22 11:00:00"); // 체크아웃 임시값
	    vo.setAdultNo(2); // 성인 2명
	    vo.setChildNo(1); // 어린이 1명

	    vo.setReservDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	    vo.setCancelyn("N");
	    vo.setEmail("test@example.com");
	    
	    paymentService.saveReservation(vo);
		
	    AccoHasFacilVO vo1 = new AccoHasFacilVO();
	    vo1.setDescription("테스트 숙소 이름");
	    vo1.setPrice(50000);
	    
	    // 세션에 예약 정보 저장
	    session.setAttribute("reservVO", vo);
	    session.setAttribute("accoVO", vo1);
	    
		// 0 : 계좌이체   1 : 카드결제   2 : 카카오페이
		
		switch (vo.getPayment()) {
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
        	
            String kakaoRedirectUrl = kakaoPayService.kakaoPayReady(vo, vo1);
            return "redirect:" + kakaoRedirectUrl;

        default:
            // 잘못된 결제 수단 처리
            return "redirect:/payment/payment_fail";
		}
	}
	
	@RequestMapping(value = "/payment/kakaoPaySuccess", method = RequestMethod.GET)
	public String kakaoPaySuccess(
			@RequestParam("pg_token") String pg_token,
			HttpSession session,
			Model model) {
	    
		ReservVO vo = (ReservVO) session.getAttribute("reservVO");
	    AccoHasFacilVO vo1 = (AccoHasFacilVO) session.getAttribute("accoVO");
		
		// 카카오페이 성공 후 처리 로직
	    System.out.println("kakaoPaySuccess pg_token : " + pg_token);
	    
	    // 결제 승인 요청 (pg_token 포함)
	    KakaoPayApprovalVO approveResponse = kakaoPayService.kakaoPayInfo(pg_token, vo, vo1);
	    
	    if (approveResponse != null) {
	        // 결제 성공 처리 (DB 저장 등)
	        model.addAttribute("approveInfo", approveResponse);
	        return "payment/payment_ok";
	    } else {
	        return "redirect:/payment/payment_fail";
	    }
	}
}
