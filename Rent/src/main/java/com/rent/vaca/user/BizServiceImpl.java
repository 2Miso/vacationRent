package com.rent.vaca.user;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rent.vaca.acco.AccoPhotoVO;
import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.notice.NoticeVO;
import com.rent.vaca.notice.QuestionAttachVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;

@Service
@Transactional
public class BizServiceImpl implements BizService{

	private final BizRepository repository;
	private final PasswordEncoder passwordEncoder;
	
	// application.properties 또는 yaml에 설정
	@Value("${file.upload-dir}") 
    private String uploadDir;
	
	@Autowired
	public BizServiceImpl(BizRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}
	
	// 회원가입
	@Override
	public Integer insertBizOne(BizVO vo) {
		
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

	// 로그인
	@Override
	public BizVO selectBizOne(BizVO vo) {
		BizVO biz = repository.selectBizOne(vo);
		
		if(biz == null) {
			return null;
		}else {
			// db에 저장된 비밀번호랑 사용자가 입력한 비밀번호가 일치하는지 확인
			boolean match =  passwordEncoder.matches(vo.getPw(), biz.getPw());
			if(match == true) {
				return biz;
			}else {
				return null;
			}
		}
		
	}

	// 이메일 중복 확인
	@Override
	public Integer selectBizCntByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.selectBizCntByEmail(email);
	}

	// 숙소 등록
	@Override
	public void insertAccoOne(AccoVO vo) {
		repository.insertAccoOne(vo);
	}
	
	// 숙소 사진 등록
	@Override
	public void insertAccoPhoto(AccoPhotoVO vo) {
		repository.insertAccoPhoto(vo);
	}

	// 숙소 정보 업데이트
	@Override
	public void updateAccoInfo(AccoVO vo) {
		repository.updateAccoInfo(vo);
	}
	
	// 숙소 조회
	@Override
	public AccoVO selectBizAccoOne(int bizId) {		
		return repository.selectBizAccoOne(bizId);
	}

	// 숙소 삭제여부 조회
	@Override
	public int existsAccoByBizIdAndDelyn(int bizId, String delyn) {
		return repository.existsAccoByBizIdAndDelyn(bizId, delyn);
	}
	
	// 숙소 삭제(delyn만 변경)
	@Override
	public void deleteAccoDelyn(int accoNo) {
		repository.deleteAccoDelyn(accoNo);
	}
	
	// 숙소 사진 삭제
	@Override
	public int deleteAccoPhotoByAccoNo(int accoNo) {
		return repository.deleteAccoPhotoByAccoNo(accoNo);
	}
	
	// 내 숙소 등록한 사진 조회
	@Override
	public List<AccoPhotoVO> getPhotosByBizId(int accoNo) {
		return repository.getPhotosByBizId(accoNo);
	}
	
	@Override
	public List<AccoPhotoVO> getPhotosByBizIdAndRoomNo(int accoNo, int roomNo){
		return repository.getPhotosByBizIdAndRoomNo(accoNo, roomNo);
	}
	
	// 객실 등록
	@Override
	public void insertRoomOne(RoomVO vo) {
		repository.insertRoomOne(vo);
	}
	
	// 객실 사진 등록
	@Override
	public void insertRoomPhoto(AccoPhotoVO vo) {
		repository.insertRoomPhoto(vo);
	}
	
	@Override
	public int getPhotosByAccoNo(int roomNo) {
		return repository.getPhotosByAccoNo(roomNo);
	}

	@Override
	public void updateRoom(RoomVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<RoomVO> selectRoomsByAccoNo(int accoNo) {
		List<RoomVO> rooms = repository.selectRoomsByAccoNo(accoNo);
		for(RoomVO room : rooms) {
			List<AccoPhotoVO> photos = repository.getPhotosByBizIdAndRoomNo(room.getRoomNo());
			room.setPhotoList(photos);
		}
		return rooms;
	}
	
	// 객실 한건 조회
	@Override
	public RoomVO selectAccoRoomOne(int accoNo) {
		return repository.selectAccoRoomOne(accoNo);
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