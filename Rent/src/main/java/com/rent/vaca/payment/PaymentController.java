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
	
	// ���� ���� ȭ��
	@RequestMapping(value = "/payment/payment_fail", method = RequestMethod.GET)
	public String paymentFailPage() {
	    return "payment/payment_fail";
	}
    
	// ���� ���� ȭ��
	@RequestMapping(value = "/reservation/reserv_ok_payment", method = RequestMethod.GET)
	public String reservOkPayment(@RequestParam("accoNo") int accoNo, @RequestParam("roomNo") int roomNo, Model model) {
		AccoVO acco = paymentService.selectAccoNoOne(accoNo);
		model.addAttribute("acco", acco);
		RoomVO room = paymentService.selectAccoRoomOne(roomNo);
		model.addAttribute("room", room);
		return "reservation/reserv_ok_payment";
	}
	
	// ���� ��� ��
	@RequestMapping(value = "/reservation/reserv_cancel", method = RequestMethod.GET)
	public String reservFailPage() {
	    return "reservation/reserv_cancel";
	}
	
	// �̿�Ϸ� ����
	@RequestMapping(value = "/reservation/reserv_succes", method = RequestMethod.GET)
	public String reservSuccesPage() {
	    return "reservation/reserv_succes";
	}
	
	// ���� ���� ������
	@RequestMapping(value = "/reservation/reserv", method = RequestMethod.GET)
	public String reservPage() {
	    return "reservation/reserv";
	}
	
	// ���� �Ϸ�
	@RequestMapping(value = "/payment/payment_ok", method = RequestMethod.GET)
	public String paymentOkPage() {
	    return "payment/payment_ok"; // �� �̸� ����
	}
	
	// ���� ���� ó��
	@RequestMapping(value = "/payment/payment_ok", method = RequestMethod.POST)
	@Transactional
	public String reservOkPayment(
			ReservVO vo, @SessionAttribute("user") UserVO user, HttpSession session) {
		// ȭ�鿡�� ������ �����͸� �޾� ���� ���� ��ȸ
		
		String reservCode = RandomCodeGenerator.generateRandomCode(8);
		
		logger.debug(vo.toString());
		
		//�����ͺ��̽��� ���� insert
	    vo.setReservCode(reservCode);

	    vo.setUserId(user.getId());
	    
	    ReservVO result = paymentService.saveReservation(vo);
	    //��ȸ ��� ���� �� ���� ���� ���� ������ ������� ������ �����ֱ�(���� ���� �� ��)
		
	    if (result == null) {
	    	return "redirect:/reservation/reserv_fail";
	    }
	    
	    AccoHasFacilVO vo1 = new AccoHasFacilVO();
	    vo1.setDescription("�׽�Ʈ ���� �̸�");
	    vo1.setPrice(50000);
	    
	    // ���ǿ� ���� ���� ����
	    session.setAttribute("reservVO", vo);
	    session.setAttribute("accoVO", vo1);
	    
		// 0 : ������ü   1 : ī�����   2 : īī������
		
		switch (vo.getPayment()) {
        case PaymentServiceImpl.PAYMENT_ACCOUNT:
        	
        	// ������ü API �޾ƿ����� ����� �ʿ�
        	// ���� ���ٰ� �����ϰ� payment_ok�� �̵�
        	
            return "redirect:/payment/payment_ok";

        case PaymentServiceImpl.PAYMENT_CARD: // ī�� ����
            
        	// PG�� ī�� ���� �غ� API ȣ��
        	// ����ڰ� �־�� �ϹǷ� ���� ���ٰ� �����ϰ� payment_ok�� �̵�
        	
            return "redirect:/payment/payment_ok";

        case PaymentServiceImpl.PAYMENT_KAKAO: // īī������
            
        	// īī������ ���� �غ� API ȣ��
        	
            String kakaoRedirectUrl = kakaoPayService.kakaoPayReady(vo, vo1);
            return "redirect:" + kakaoRedirectUrl;

        default:
            // �߸��� ���� ���� ó��
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
		
		// īī������ ���� �� ó�� ����
	    System.out.println("kakaoPaySuccess pg_token : " + pg_token);
	    
	    // ���� ���� ��û (pg_token ����)
	    KakaoPayApprovalVO approveResponse = kakaoPayService.kakaoPayInfo(pg_token, vo, vo1);
	    
	    if (approveResponse != null) {
	        // ���� ���� ó�� (DB ���� ��)
	        model.addAttribute("approveInfo", approveResponse);
	        return "payment/payment_ok";
	    } else {
	        return "redirect:/payment/payment_fail";
	    }
	}
	
	//������� ������(���ఴ�ǰ� ������ �ߺ� �� ƨ�ܳ�)
	@GetMapping("/reservation/reserv_fail")
	public String reserv_fail() {
		return "reservation/reserv_fail";
	}
}
