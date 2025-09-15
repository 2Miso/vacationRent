package com.rent.vaca.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.rent.vaca.acco.AccoHasFacilVO;
import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;

@Service
@Primary
public class PaymentServiceImpl implements PaymentService{
	
	public static final int PAYMENT_ACCOUNT = 0;
    public static final int PAYMENT_CARD = 1;
    public static final int PAYMENT_KAKAO = 2;
	
	private final PaymentRepository paymentRepository;
	
    private final KakaoPayService kakaoPayService;

    @Value("${payment.kakao.adminKey}")
    private String kakaoAdminKey;
    
    @Autowired
    public PaymentServiceImpl(
        PaymentRepository paymentRepository,
        KakaoPayService kakaoPayService
    ) {
        this.paymentRepository = paymentRepository;
        this.kakaoPayService = kakaoPayService;
    }

    @Override
    public String processKakaoPayment(ReservVO vo, AccoHasFacilVO vo1) {
        return kakaoPayService.kakaoPayReady(vo, vo1);
    }

	@Override
	public ReservVO selectPaymentOne(int payment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccoVO selectAccoNoOne(int accoNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomVO selectRoomNoOne(int roomNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccoHasFacilVO selectPriceOne(int price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createCode(ReservVO vo) {
		String code = RandomCodeGenerator.generateRandomCode(8);
		
		while (paymentRepository.existsByReservCode(code)) {
            code = RandomCodeGenerator.generateRandomCode(8);
        }
		
		return code;
	}
	
	@Override
	public ReservVO saveReservation(ReservVO vo) {
	    // 1) 예약코드 생성
	    String reservCode = createCode(vo);
	    vo.setReservCode(reservCode);
	    
	    // DB에 저장
	    paymentRepository.insertReservation(vo);
	    
	    return vo;
	}

}
