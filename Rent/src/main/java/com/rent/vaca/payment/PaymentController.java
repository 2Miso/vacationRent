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
    
    // ��������� ȭ��
    @RequestMapping(value = "/payment/payment_ok", method = RequestMethod.GET)
    public String paymentOkPage() {
        return "payment/payment_ok";  // -> /WEB-INF/views/payment/payment_ok.jsp
    }
	
	// ���� ���� ȭ��
	@RequestMapping(value = "/payment/payment_fail", method = RequestMethod.GET)
	public String paymentFailPage() {
	    return "payment/payment_fail";
	}
    
	// ���� ���� ȭ��
	@RequestMapping(value = "reservation/reserv_ok_payment", method = RequestMethod.GET)
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
	
	// ���� ���� ó��
	@RequestMapping(value = "reservation/reserv_ok_payment", method = RequestMethod.POST)
	public String reservOkPayment(
			@ModelAttribute ReservVO vo,
			@ModelAttribute AccoHasFacilVO vo1) {
		// ȭ�鿡�� ������ �����͸� �޾� ���� ���� ��ȸ
		
		int payment = vo.getPayment();
		
		// 0 : ������ü   1 : ī�����   2 : īī������
		
		switch (payment) {
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
            String kakaoRedirectUrl = kakaoPayService.readyToPay(vo, vo1);
            return "redirect:" + kakaoRedirectUrl;

        default:
            // �߸��� ���� ���� ó��
            return "redirect:/payment/payment_fail";
		}
	}
	
	
}
