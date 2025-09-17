package com.rent.vaca.user;

import java.util.List;

import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.notice.NoticeVO;
import com.rent.vaca.notice.QuestionAttachVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;


public interface BizService {

	// ����Ͻ� ȸ��
	
	// ȸ������
	int insertBizOne(BizVO vo);
	
	// �α���
	BizVO selectBizOne(BizVO vo);
	
	// �̸��� �ߺ�üũ
	int selectBizCntByEmail(String email);
	
	// ���� ���� ���
	void addAccoInfo(AccoVO vo);
	
	// ���� ���� ����
	void updateAccoInfo(AccoVO vo);
	
	// ���� �Ѱ� ��ȸ
	int selectBizCntByAccoNo(int bizId);
	
	// ���� �Ѱ� ���
	void insertRoomOne(RoomVO vo);
	
	// ���� ���� ����
	void updateRoom(RoomVO vo);
	
	// ��� ����� ���� ���� ��ȸ(���� ���� ��Ͽ�)
	int selectLastInsertedRoomNo(int roomNo);
	
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
	
}
