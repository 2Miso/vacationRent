package com.rent.vaca.payment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.rent.vaca.acco.AccoHasFacilVO;
import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;

@Mapper
public interface PaymentRepository {
	
	// 결제 정보 저장
	// void insertPayment(ReservVO vo);
	
	// 예약 중복 체크
	int duplicationReservations(
			@Param("roomNo") int roomNo,
			@Param("checkin") String checkin,
			@Param("checkout") String checkout);
	
	// 예약 코드 중복 체크
	boolean existsByReservCode(String code);
	
	// 예약 정보 저장
    void insertReservation(ReservVO vo);
	
	// 숙소, 방, 가격 조회
	AccoVO selectAccoByNo(int accoNo);
	RoomVO selectRoomByNo(int roomNo);
	AccoHasFacilVO selectPriceInfo(int roomNo);
	
	// 결제 수단 조회
	int selectPaymentOne();
	
	// 결제 상태 업데이트 취소 여부 확인
	void updatePaymentStatus(
			@Param("reservCode") String reservCode,
			@Param("cancelyn") String cancelyn);
}
