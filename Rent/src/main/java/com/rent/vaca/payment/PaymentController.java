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
	
	// ���� ���� ȭ��
	@RequestMapping(value = "/payment/payment_fail", method = RequestMethod.GET)
	public String paymentFailPage() {
	    return "payment/payment_fail";
	}
    
	// ���� ���� ȭ��
	@RequestMapping(value = "/reservation/reserv_ok_payment", method = RequestMethod.GET)
	public String reservOkPayment() {
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
	public String reservOkPayment(
			@ModelAttribute ReservVO vo, HttpSession session) {
		// ȭ�鿡�� ������ �����͸� �޾� ���� ���� ��ȸ
		
		
		String reservCode = RandomCodeGenerator.generateRandomCode(8);
	    vo.setReservCode(reservCode);

	    // �ӽ� ��
	    vo.setUserId(1);  // ������ ����� ID
	    vo.setRoomNo(1); // ������ ���� ��ȣ
	    vo.setCheckin("2025-09-20 15:00:00");  // üũ�� �ӽð� (MySQL timestamp ����)
	    vo.setCheckout("2025-09-22 11:00:00"); // üũ�ƿ� �ӽð�
	    vo.setAdultNo(2); // ���� 2��
	    vo.setChildNo(1); // ��� 1��

	    vo.setReservDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	    vo.setCancelyn("N");
	    vo.setEmail("test@example.com");
	    
	    paymentService.saveReservation(vo);
		
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
}
