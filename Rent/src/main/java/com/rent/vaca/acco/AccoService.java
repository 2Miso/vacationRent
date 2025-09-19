package com.rent.vaca.acco;

import java.util.List;

import com.rent.vaca.review.ReviewVO;
import com.rent.vaca.user.InterestVO;

public interface AccoService {
	//상단 사진 5개
	List<AccoPhotoVO> selectTopPhotos(int accoNo);
	
	//숙소, 사업자, 객실목록과 객실사진1개
	AccoVO selectAccoByAccoNo(int accoNo);
	
	//리뷰 목록 조회
	List<ReviewVO> selectReviewsByAccoNo(AccoVO acco);
	
	//관심숙소 버튼 눌렀을 때
	int hitInterestBtn(InterestVO vo);
	
	//관심숙소 조회
	boolean selectInterestOne(InterestVO vo);
	
	//리뷰개수 조회
	int countReview(int accoNo);
	
	//별점 평균 조회
	Double starAvg(int accoNo);
	
	//사진모달 데이터 교체
	List<AccoPhotoVO> photoModal(AccoPhotoVO vo);
}
