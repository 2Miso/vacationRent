package com.rent.vaca.payment;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.rent.vaca.acco.AccoHasFacilVO;
import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;
import com.rent.vaca.user.UserVO;


@Controller
public class PaymentController {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
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
	public String reservOkPayment(@RequestParam("accoNo") int accoNo, @RequestParam("roomNo") int roomNo, Model model) {
		AccoVO acco = paymentService.selectAccoNoOne(accoNo);
		model.addAttribute("acco", acco);
		RoomVO room = paymentService.selectAccoRoomOne(roomNo);
		model.addAttribute("room", room);
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
	@Transactional
	public String reservOkPayment(
			ReservVO vo, @SessionAttribute("user") UserVO user, HttpSession session) {
		// 화면에서 전달한 데이터를 받아 결제 수단 조회
		
		String reservCode = RandomCodeGenerator.generateRandomCode(8);
		
		logger.debug(vo.toString());
		
		//데이터베이스에 예약 insert
	    vo.setReservCode(reservCode);

	    vo.setUserId(user.getId());
	    
	    ReservVO result = paymentService.saveReservation(vo);
	    //조회 결과 같은 날 같은 객실 예약 있으면 예약실패 페이지 보여주기(아직 결제 안 됨)
		
	    if (result == null) {
	    	return "redirect:/reservation/reserv_fail";
	    }
	    
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
	
	//예약실패 페이지(예약객실과 예약일 중복 시 튕겨냄)
	@GetMapping("/reservation/reserv_fail")
	public String reserv_fail() {
		return "reservation/reserv_fail";
	}
}
