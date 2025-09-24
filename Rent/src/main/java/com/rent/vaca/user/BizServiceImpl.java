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
	
	// application.properties �Ǵ� yaml�� ����
	@Value("${file.upload-dir}") 
    private String uploadDir;
	
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
		BizVO biz = repository.selectBizOne(vo);
		
		if(biz == null) {
			return null;
		}else {
			// db�� ����� ��й�ȣ�� ����ڰ� �Է��� ��й�ȣ�� ��ġ�ϴ��� Ȯ��
			boolean match =  passwordEncoder.matches(vo.getPw(), biz.getPw());
			if(match == true) {
				return biz;
			}else {
				return null;
			}
		}
		
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

	// ���� ���� ������Ʈ
	@Override
	public void updateAccoInfo(AccoVO vo) {
		repository.updateAccoInfo(vo);
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
	
	// ���� ����(delyn�� ����)
	@Override
	public void deleteAccoDelyn(int accoNo) {
		repository.deleteAccoDelyn(accoNo);
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
	
	@Override
	public List<AccoPhotoVO> getPhotosByBizIdAndRoomNo(int accoNo, int roomNo){
		return repository.getPhotosByBizIdAndRoomNo(accoNo, roomNo);
	}
	
	// ���� ���
	@Override
	public void insertRoomOne(RoomVO vo) {
		repository.insertRoomOne(vo);
	}
	
	// ���� ���� ���
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
	
	// ���� �Ѱ� ��ȸ
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