package com.rent.vaca.user;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
	
	// ���� 1�� ��ȸ
	AccoVO selectBizAccoOne(int bizId);
	
	// ���� ����(delyn�� ����)
	void deleteAccoDelyn(int accoNo);
	
	// ���� ���� ����
	int deleteAccoPhotoByAccoNo(int accoNo);
	
	// ���� 1�� ���
	void insertRoomOne(RoomVO vo);
	
	// ���� ���� ���
	void insertRoomPhoto(AccoPhotoVO vo);
	
	// ���� 1�� ����
	void deleteRoomByAccoNo(int accoNo, int roomNo);
	
	// ���� ���� ����
	void updateRoom(RoomVO vo);
	
	// ���ҳ� ���� ��ü ��ȸ
	List<RoomVO> selectRoomsByAccoNo(int accoNo);
	
	// ���� �Ѱ� ��ȸ
	RoomVO selectAccoRoomOne(int accoNo);
	
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
	List<AccoPhotoVO> getPhotosByBizId(int accoNo);
	
	// ���� �� ���� ���� ��ȸ
	List<AccoPhotoVO> getPhotosByBizIdAndRoomNo(int accoNo, int roomNo);
	
}
