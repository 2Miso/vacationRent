package com.rent.vaca.acco;

import com.rent.vaca.user.InterestVO;

public interface AccoService {
	//숙소 1건 조회
	AccoVO selectAccoByAccoNo(int accoNo);
	
	//관심숙소 버튼 눌렀을 때
	int hitInterestBtn(InterestVO vo);
	
	//관심숙소 조회
	boolean selectInterestOne(InterestVO vo);
	
	//리뷰개수 조회
	int countReview(int accoNo);
	
	//별점 평균 조회
	Double starAvg(int accoNo);
}
