package com.rent.vaca.user;

import java.util.List;

import com.rent.vaca.acco.AccoPhotoVO;
import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.notice.NoticeVO;
import com.rent.vaca.notice.QuestionAttachVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;


public interface BizService {

	// 비즈니스 회원
	
	// 회원가입
	Integer insertBizOne(BizVO vo);
	
	// 로그인
	BizVO selectBizOne(BizVO vo);
	
	// 이메일 중복체크
	Integer selectBizCntByEmail(String email);
	
	// 숙소 정보 등록
	void insertAccoOne(AccoVO vo);
	
	// 숙소 사진 등록
	void insertAccoPhoto(AccoPhotoVO vo);
	
	// 숙소 정보 수정
	void updateAccoInfo(AccoVO vo);
	
	// 등록된 숙소 조회
	int existsAccoByBizIdAndDelyn(int bizId, String delyn);
	
	// 숙소 한건 조회
	AccoVO selectBizAccoOne(int bizId);
	
	// 객실 한건 등록
	void insertRoomOne(RoomVO vo);
	
	// 객실 사진 등록
	void insertRoomPhoto(AccoPhotoVO vo);
	
	// 객실 정보 수정
	void updateRoom(RoomVO vo);
	
	// 방금 등록한 객실 정보 조회(객실 사진 등록용)
	Integer selectLastInsertedRoomNo(int roomNo);
	
	// 예약자 리스트 조회
	List<ReservVO> reservList(BizVO vo);
	
	// 예약자 상태 변경
	void updateReservStatus(ReservVO vo);
	
	//  회원정보 수정
	void updateBizInfo(BizVO vo);
	
	// 관리자에게 문의하기
	void adminQna(QuestionAttachVO vo);
	
	// 내 문의내역 조회
	List<NoticeVO> myQnaList(NoticeVO vo);
	// 내 숙소 등록 사진 조회
	List<AccoVO> getPhotosByBizId(int bizId);
	
}
