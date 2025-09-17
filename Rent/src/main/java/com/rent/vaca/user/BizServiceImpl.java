package com.rent.vaca.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

	@Override
	public int insertBizOne(BizVO vo) {
		
		// 이메일 중복확인
		int count = repository.selectBizCntByEmail(vo.getEmail());
		if (count > 0) {
			throw new IllegalArgumentException("이미 등록된 이메일입니다.");
		}
		// 비밀번호 암호화 후 인서트
		String encPw = passwordEncoder.encode(vo.getPw());
		vo.setPw(encPw);
		return repository.insertBizOne(vo);
	}

	@Override
	public BizVO selectBizOne(BizVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectBizCntByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.selectBizCntByEmail(email);
	}

	@Override
	public void addAccoInfo(AccoVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAccoInfo(AccoVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int selectBizCntByAccoNo(int bizId) {
		return repository.selectBizCntByBizId(bizId);
	}

	@Override
	public void insertRoomOne(RoomVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRoom(RoomVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int selectLastInsertedRoomNo(int roomNo) {
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