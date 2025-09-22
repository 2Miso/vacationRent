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
import org.springframework.web.multipart.MultipartFile;

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
	
	// ���� ���� ������Ʈ
	@Override
	public void updateAccoImages(int accoNo, MultipartFile[] imageFiles) {
		 for (MultipartFile image : imageFiles) {
	            if (!image.isEmpty()) {
	                try {
	                    String originalName = image.getOriginalFilename();
	                    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	                    String savedName = timeStamp + "_" + originalName;

	                    // ���� ���� ����
	                    File saveFile = new File(uploadDir, savedName);
	                    image.transferTo(saveFile);

	                    // VO ����
	                    AccoPhotoVO photoVO = new AccoPhotoVO();
	                    photoVO.setAccoNo(accoNo);
	                    photoVO.setRoomNo(0); // ���� ���� �̹������ 0
	                    photoVO.setOriginalName(originalName);
	                    photoVO.setSavedName(savedName);

	                    // DB ����
	                    repository.insertAccoPhoto(photoVO);

	                } catch (IOException e) {
	                    throw new RuntimeException("�̹��� ���� ����", e);
	                }
	            }
		 }
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