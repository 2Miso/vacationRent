package com.rent.vaca.user;

import java.util.List;

import com.rent.vaca.acco.AccoPhotoVO;
import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.notice.NoticeVO;
import com.rent.vaca.notice.QuestionAttachVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;


public interface BizService {

	// ����Ͻ� ȸ��
	
	// ȸ������
	Integer insertBizOne(BizVO vo);
	
	// �α���
	BizVO selectBizOne(BizVO vo);
	
	// �̸��� �ߺ�üũ
	Integer selectBizCntByEmail(String email);
	
	// ���� ���� ���
	void insertAccoOne(AccoVO vo);
	
	// ���� ���� ���
	void insertAccoPhoto(AccoPhotoVO vo);
	
	// ���� ���� ����
	void updateAccoInfo(AccoVO vo);
	
	// ��ϵ� ���� ��ȸ
	int existsAccoByBizIdAndDelyn(int bizId, String delyn);
	
	// ���� �Ѱ� ��ȸ
	AccoVO selectBizAccoOne(int bizId);
	
	// ���� �Ѱ� ���
	void insertRoomOne(RoomVO vo);
	
	// ���� ���� ���
	void insertRoomPhoto(AccoPhotoVO vo);
	
	// ���� ���� ����
	void updateRoom(RoomVO vo);
	
	// ��� ����� ���� ���� ��ȸ(���� ���� ��Ͽ�)
	Integer selectLastInsertedRoomNo(int roomNo);
	
	// ������ ����Ʈ ��ȸ
	List<ReservVO> reservList(BizVO vo);
	
	// ������ ���� ����
	void updateReservStatus(ReservVO vo);
	
	//  ȸ������ ����
	void updateBizInfo(BizVO vo);
	
	// �����ڿ��� �����ϱ�
	void adminQna(QuestionAttachVO vo);
	
	// �� ���ǳ��� ��ȸ
	List<NoticeVO> myQnaList(NoticeVO vo);
	// �� ���� ��� ���� ��ȸ
	List<AccoVO> getPhotosByBizId(int bizId);
	
}
