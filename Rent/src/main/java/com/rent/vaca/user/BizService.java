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

	// 비즈니스 회원
	
	// 회원가입
	public Integer insertBizOne(BizVO vo);
	
	// 로그인
	public BizVO selectBizOne(BizVO vo);
	
	// 비밀번호 변경 처리
	public boolean bizPwChange(String email, BizPwChangeVO vo);
	
	// 이메일 중복체크
	public Integer selectBizCntByEmail(String email);
	
	// 사업자 정보 수정
	public int updateBizInfo(BizVO vo);
	
	// 사업자등록증 사진 삭제
	public void updateCertificateDeleted(int bizId);
	
	// 숙소 정보 등록
	public void insertAccoOne(AccoVO vo);
	
	// 숙소 사진 등록
	public void insertAccoPhoto(AccoPhotoVO vo);
	
	// 숙소 정보 수정
	public void updateAccoInfo(AccoVO vo);
	
	// 등록된 숙소 조회
	public int existsAccoByBizIdAndDelyn(int bizId, String delyn);
	
	// 숙소 1건 조회
	public AccoVO selectBizAccoOne(int bizId);
	
	// 숙소 삭제(delyn만 변경)
	public void deleteAccoDelyn(int accoNo);
	
	// 숙소 사진 삭제
	public int deleteAccoPhotoByAccoNo(int accoNo);
	
	// 객실 1건 등록
	public void insertRoomOne(RoomVO vo);
	
	// 객실 사진 등록
	public void insertRoomPhoto(AccoPhotoVO vo);
	
	// 객실 1건 삭제
	public void deleteRoomByAccoNo(int accoNo, int roomNo);
	
	// 객실 정보 수정
	public void updateRoom(RoomVO vo);
	
	// 숙소내 객실 전체 조회
	public List<RoomVO> selectRoomsByAccoNo(int accoNo);
	
	// 객실 한건 조회
	public RoomVO selectAccoRoomOne(int accoNo);
	
	// 예약자 리스트 조회
	public List<ReservVO> reservList(BizVO vo);
	
	// 예약자 상태 변경
	public void updateReservStatus(ReservVO vo);
	
	// 관리자에게 문의하기
	public void adminQna(QuestionAttachVO vo);
	
	// 내 문의내역 조회
	public List<NoticeVO> myQnaList(NoticeVO vo);
	
	// 내 숙소 등록 사진 조회
	public List<AccoPhotoVO> getPhotosByBizId(int accoNo);
	
	// 숙소 내 객실 사진 조회
	public List<AccoPhotoVO> getPhotosByBizIdAndRoomNo(int accoNo, int roomNo);
	
}
