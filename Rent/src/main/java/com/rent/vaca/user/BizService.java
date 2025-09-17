package com.rent.vaca.user;

import java.util.List;

import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.notice.NoticeVO;
import com.rent.vaca.notice.QuestionAttachVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;


public interface BizService {

	// 비즈니스 회원
	
	// 회원가입
	int insertBizOne(BizVO vo);
	
	// 로그인
	BizVO selectBizOne(BizVO vo);
	
	// 이메일 중복체크
	int selectBizCntByEmail(String email);
	
	// 숙소 정보 등록
	void addAccoInfo(AccoVO vo);
	
	// 숙소 정보 수정
	void updateAccoInfo(AccoVO vo);
	
	// 숙소 한건 조회
	int selectBizCntByAccoNo(int bizId);
	
	// 객실 한건 등록
	void insertRoomOne(RoomVO vo);
	
	// 객실 정보 수정
	void updateRoom(RoomVO vo);
	
	// 방금 등록한 객실 정보 조회(객실 사진 등록용)
	int selectLastInsertedRoomNo(int roomNo);
	
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
	
}
