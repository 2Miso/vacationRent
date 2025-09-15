package com.rent.vaca.user;

import java.util.List;

import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.notice.NoticeVO;
import com.rent.vaca.notice.QuestionAttachVO;
import com.rent.vaca.reserv.ReservVO;
import com.rent.vaca.room.RoomVO;


public interface BizService {

	// 비즈니스 회원
	
	// 1. 회원가입
	int insertBizOne(BizVO vo);
	
	// 2. 로그인
	BizVO selectBizOne(BizVO vo);
	
	// 3. 이메일 중복체크
	int selectBizCntByEmail(String email);
	
	// 4. 숙소 정보 등록
	void addAccoInfo(AccoVO vo);
	
	// 5. 숙소 정보 수정
	void updateAccoInfo(AccoVO vo);
	
	// 6. 객실 정보 등록
	void addRoom(RoomVO vo);
	
	// 7. 객실 정보 수정
	void updateRoom(RoomVO vo);
	
	// 8. 예약자 리스트 조회
	List<ReservVO> reservList(BizVO vo);
	
	// 9. 예약자 상태 변경
	void updateReservStatus(ReservVO vo);
	
	// 10. 회원정보 수정
	void updateBizInfo(BizVO vo);
	
	// 11. 관리자에게 문의하기
	void adminQna(QuestionAttachVO vo);
	
	// 12. 내 문의내역 조회
	List<NoticeVO> myQnaList(NoticeVO vo);
	
}
