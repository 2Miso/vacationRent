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
	public Integer insertBizOne(BizVO vo);
	
	// �α���
	public BizVO selectBizOne(BizVO vo);
	
	// ��й�ȣ ���� ó��
	public boolean bizPwChange(String email, BizPwChangeVO vo);
	
	// �̸��� �ߺ�üũ
	public Integer selectBizCntByEmail(String email);
	
	// ����� ���� ����
	public int updateBizInfo(BizVO vo);
	
	// ����ڵ���� ���� ����
	public void updateCertificateDeleted(int bizId);
	
	// ���� ���� ���
	public void insertAccoOne(AccoVO vo);
	
	// ���� ���� ���
	public void insertAccoPhoto(AccoPhotoVO vo);
	
	// ���� ���� ����
	public void updateAccoInfo(AccoVO vo);
	
	// ��ϵ� ���� ��ȸ
	public int existsAccoByBizIdAndDelyn(int bizId, String delyn);
	
	// ���� 1�� ��ȸ
	public AccoVO selectBizAccoOne(int bizId);
	
	// ���� ����(delyn�� ����)
	public void deleteAccoDelyn(int accoNo);
	
	// ���� ���� ����
	public int deleteAccoPhotoByAccoNo(int accoNo);
	
	// ���� 1�� ���
	public void insertRoomOne(RoomVO vo);
	
	// ���� ���� ���
	public void insertRoomPhoto(AccoPhotoVO vo);
	
	// ���� 1�� ����
	public void deleteRoomByAccoNo(int accoNo, int roomNo);
	
	// ���� ���� ����
	public void updateRoom(RoomVO vo);
	
	// ���ҳ� ���� ��ü ��ȸ
	public List<RoomVO> selectRoomsByAccoNo(int accoNo);
	
	// ���� �Ѱ� ��ȸ
	public RoomVO selectAccoRoomOne(int accoNo);
	
	// ������ ����Ʈ ��ȸ
	public List<ReservVO> reservList(BizVO vo);
	
	// ������ ���� ����
	public void updateReservStatus(ReservVO vo);
	
	// �����ڿ��� �����ϱ�
	public void adminQna(QuestionAttachVO vo);
	
	// �� ���ǳ��� ��ȸ
	public List<NoticeVO> myQnaList(NoticeVO vo);
	
	// �� ���� ��� ���� ��ȸ
	public List<AccoPhotoVO> getPhotosByBizId(int accoNo);
	
	// ���� �� ���� ���� ��ȸ
	public List<AccoPhotoVO> getPhotosByBizIdAndRoomNo(int accoNo, int roomNo);
	
}
