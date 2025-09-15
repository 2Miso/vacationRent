package com.rent.vaca.payment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.rent.vaca.acco.AccoHasFacilVO;
import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;

@Mapper
public interface PaymentRepository {
	
	// ���� ���� ����
	// void insertPayment(ReservVO vo);
	
	// ���� �ߺ� üũ
	int duplicationReservations(
			@Param("roomNo") int roomNo,
			@Param("checkin") String checkin,
			@Param("checkout") String checkout);
	
	// ���� �ڵ� �ߺ� üũ
	boolean existsByReservCode(String code);
	
	// ���� ���� ����
    void insertReservation(ReservVO vo);
	
	// ����, ��, ���� ��ȸ
	AccoVO selectAccoByNo(int accoNo);
	RoomVO selectRoomByNo(int roomNo);
	AccoHasFacilVO selectPriceInfo(int roomNo);
	
	// ���� ���� ��ȸ
	int selectPaymentOne();
	
	// ���� ���� ������Ʈ ��� ���� Ȯ��
	void updatePaymentStatus(
			@Param("reservCode") String reservCode,
			@Param("cancelyn") String cancelyn);
}
