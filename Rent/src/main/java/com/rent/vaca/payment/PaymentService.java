package com.rent.vaca.payment;

import java.util.List;

import com.rent.vaca.acco.AccoHasFacilVO;
import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;
import com.rent.vaca.user.UserVO;

public interface PaymentService {
	
	// 결제 수단 조회
	ReservVO selectPaymentOne(int payment);
	
	// 숙소 정보 조회
	AccoVO selectAccoNoOne(int accoNo);
	
	// 객실 정보 조회
	RoomVO selectAccoRoomOne(int roomNo);
	
	// 결제 금액 조회
	AccoHasFacilVO selectPriceOne(int price);
	
	// 계좌이체
	// void processAccountTransfer(ReservVO vo);
	
	// 카드결제
	// String processCardPayment(ReservVO vo);
	
	// 카카오페이
	String processKakaoPayment(ReservVO vo, AccoHasFacilVO vo1);
	
	// 예약코드 생성
	String createCode(ReservVO vo);
	
	// DB 저장
	ReservVO saveReservation(ReservVO vo);
	
	//마이페이지 예약내역 조회 : 다가오는 예약
	List<ReservVO> selectReservList(int user);
	
	//마이페이지 예약내역 조회 : 지나간 예약, 취소한 예약
	List<ReservVO> deprecatedReservList(int user);
	
}
