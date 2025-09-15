package com.rent.vaca.user;

import java.util.List;

import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.notice.NoticeVO;
import com.rent.vaca.notice.QuestionAttachVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;


public interface BizService {

	// ����Ͻ� ȸ��
	
	// 1. ȸ������
	int insertBizOne(BizVO vo);
	
	// 2. �α���
	BizVO selectBizOne(BizVO vo);
	
	// 3. �̸��� �ߺ�üũ
	int selectBizCntByEmail(String email);
	
	// 4. ���� ���� ���
	void addAccoInfo(AccoVO vo);
	
	// 5. ���� ���� ����
	void updateAccoInfo(AccoVO vo);
	
	// 6. ���� ���� ���
	void addRoom(RoomVO vo);
	
	// 7. ���� ���� ����
	void updateRoom(RoomVO vo);
	
	// 8. ������ ����Ʈ ��ȸ
	List<ReservVO> reservList(BizVO vo);
	
	// 9. ������ ���� ����
	void updateReservStatus(ReservVO vo);
	
	// 10. ȸ������ ����
	void updateBizInfo(BizVO vo);
	
	// 11. �����ڿ��� �����ϱ�
	void adminQna(QuestionAttachVO vo);
	
	// 12. �� ���ǳ��� ��ȸ
	List<NoticeVO> myQnaList(NoticeVO vo);
	
}
