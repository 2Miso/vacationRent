package com.rent.vaca.payment;

import com.rent.vaca.acco.AccoHasFacilVO;
import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;

public interface PaymentService {
	
	// ���� ���� ��ȸ
	ReservVO selectPaymentOne(int payment);
	
	// ���� ���� ��ȸ
	AccoVO selectAccoNoOne(int accoNo);
	
	// ���� ���� ��ȸ
	RoomVO selectAccoRoomOne(int roomNo);
	
	// ���� �ݾ� ��ȸ
	AccoHasFacilVO selectPriceOne(int price);
	
	// ������ü
	// void processAccountTransfer(ReservVO vo);
	
	// ī�����
	// String processCardPayment(ReservVO vo);
	
	// īī������
	String processKakaoPayment(ReservVO vo, AccoHasFacilVO vo1);
	
	// �����ڵ� ����
	String createCode(ReservVO vo);
	
	// DB ����
	ReservVO saveReservation(ReservVO vo);
	
}
