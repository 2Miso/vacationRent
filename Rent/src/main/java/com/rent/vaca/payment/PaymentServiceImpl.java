package com.rent.vaca.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.rent.vaca.acco.AccoHasFacilVO;
import com.rent.vaca.acco.AccoRepository;
import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomRepository;
import com.rent.vaca.room.RoomVO;


@Service
@Primary
public class PaymentServiceImpl implements PaymentService{
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	public static final int PAYMENT_ACCOUNT = 0;
    public static final int PAYMENT_CARD = 1;
    public static final int PAYMENT_KAKAO = 2;
	
	private final PaymentRepository paymentRepository;
	private final AccoRepository accoRepository;
	private final RoomRepository roomRepository;
	
    private final KakaoPayService kakaoPayService;

    @Value("${payment.kakao.adminKey}")
    private String kakaoAdminKey;
    
    @Autowired
    public PaymentServiceImpl(
        PaymentRepository paymentRepository,
        AccoRepository accoRepository,
        RoomRepository roomRepository,
        KakaoPayService kakaoPayService
    ) {
        this.paymentRepository = paymentRepository;
        this.accoRepository = accoRepository;
        this.roomRepository = roomRepository;
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
		return accoRepository.selectAccoByAccoNo(accoNo);
	}

	@Override
	public RoomVO selectRoomOne(RoomVO vo) {
		return roomRepository.selectRoomOne(vo);
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
	    logger.debug("페이먼트서비스임플 예약코드" + reservCode);
	    vo.setReservCode(reservCode);
	    
	    //해당날짜 해당 객실번호에 예약정보가 있는지 조회****
	    //있으면 
	    if(true) {
	    	return null;
	    }
	    
	    // DB에 저장
	    paymentRepository.insertReservation(vo);
	    
	    return vo;
	}

}
