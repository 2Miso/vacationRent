package com.rent.vaca.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rent.vaca.acco.AccoPhotoVO;
import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.notice.NoticeVO;
import com.rent.vaca.notice.QuestionAttachVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;

@Service
public class BizServiceImpl implements BizService{

	private final BizRepository repository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public BizServiceImpl(BizRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}
	
	// ȸ������
	@Override
	public Integer insertBizOne(BizVO vo) {
		
		// �̸��� �ߺ�Ȯ��
		int count = repository.selectBizCntByEmail(vo.getEmail());
		if (count > 0) {
			throw new IllegalArgumentException("�̹� ��ϵ� �̸����Դϴ�.");
		}
		// ��й�ȣ ��ȣȭ �� �μ�Ʈ
		String encPw = passwordEncoder.encode(vo.getPw());
		vo.setPw(encPw);
		return repository.insertBizOne(vo);
	}

	// �α���
	@Override
	public BizVO selectBizOne(BizVO vo) {
		return repository.selectBizOne(vo);
	}

	// �̸��� �ߺ� Ȯ��
	@Override
	public Integer selectBizCntByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.selectBizCntByEmail(email);
	}

	// ���� ���
	@Override
	public void insertAccoOne(AccoVO vo) {
		repository.insertAccoOne(vo);
	}
	
	// ���� ���� ���
	@Override
	public void insertAccoPhoto(AccoPhotoVO vo) {
		repository.insertAccoPhoto(vo);
	}

	@Override
	public void updateAccoInfo(AccoVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	// ���� ��ȸ
	@Override
	public AccoVO selectBizAccoOne(int bizId) {		
		return repository.selectBizAccoOne(bizId);
	}

	// ���� �������� ��ȸ
	@Override
	public int existsAccoByBizIdAndDelyn(int bizId, String delyn) {
		return repository.existsAccoByBizIdAndDelyn(bizId, delyn);
	}
	
	// ���� ���� ����
	@Override
	public int deleteAccoPhotoByAccoNo(int accoNo) {
		return repository.deleteAccoPhotoByAccoNo(accoNo);
	}
	
	// �� ���� ����� ���� ��ȸ
	@Override
	public List<AccoPhotoVO> getPhotosByBizId(int accoNo) {
		return repository.getPhotosByBizId(accoNo);
	}
	
	// ���� ���
	@Override
	public void insertRoomOne(RoomVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	// ���� ���� ���
	@Override
	public void insertRoomPhoto(AccoPhotoVO vo) {
		repository.insertRoomPhoto(vo);
	}

	@Override
	public void updateRoom(RoomVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public Integer selectLastInsertedRoomNo(int roomNo) {
		return repository.selectLastInsertedRoomNo(roomNo);
	}

	@Override
	public List<ReservVO> reservList(BizVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateReservStatus(ReservVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBizInfo(BizVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adminQna(QuestionAttachVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<NoticeVO> myQnaList(NoticeVO vo) {
		// TODO Auto-generated method stub
		return null;
	}






	
}