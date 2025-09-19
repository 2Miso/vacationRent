package com.rent.vaca.acco;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.vaca.review.ReviewRepository;
import com.rent.vaca.review.ReviewVO;
import com.rent.vaca.room.RoomRepository;
import com.rent.vaca.user.InterestRepository;
import com.rent.vaca.user.InterestVO;

@Service
public class AccoServiceImpl implements AccoService {
	private static final Logger logger = LoggerFactory.getLogger(AccoRepository.class);
	
	private final AccoRepository accoRepository;
	private final ReviewRepository reviewRepository;
	private final InterestRepository interestRepository;
	private final AccoPhotoRepository accoPhotoRepository;
	
	@Autowired
	public AccoServiceImpl(AccoRepository accoRepository, ReviewRepository reviewRepository,
			InterestRepository interestRepository, AccoPhotoRepository accoPhotoRepository) {
		this.accoRepository = accoRepository;
		this.reviewRepository = reviewRepository;
		this.interestRepository = interestRepository;
		this.accoPhotoRepository = accoPhotoRepository;
	}
	
	////상단 사진 5개
	public List<AccoPhotoVO> selectTopPhotos(int accoNo){
		return accoPhotoRepository.selectTopPhotos(accoNo);
	}
	
	////숙소, 사업자, 객실목록과 객실사진1개
	public AccoVO selectAccoByAccoNo(int accoNo) {
		return accoRepository.selectAccoByAccoNo(accoNo);
	}
	
	//리뷰 목록 조회
	public List<ReviewVO> selectReviewsByAccoNo(AccoVO acco){
		if(acco.getOrderBy()==null) {
			acco.setOrderBy("newest");
		}
		switch(acco.getOrderBy()) {
			
			case "highest":
				acco.setOrderBy("reviewtb.star desc");
				break;
			case "lowest":
				acco.setOrderBy("reviewtb.star asc");
				break;
			default:
				acco.setOrderBy("reviewtb.wdate desc");
		}
		
		return reviewRepository.selectReviewsByAccoNo(acco);
	}
	
	//관심숙소 버튼 클릭
	@Override
	public int hitInterestBtn(InterestVO vo) {
		if(selectInterestOne(vo)) {//true: 조회데이터 있음, false: 조회데이터 없음
			logger.info("hitInterestBtn의 true");
			interestRepository.deleteInterestOne(vo);
			return 0;
		} else {
			logger.info("hitInterestBtn의 false");
			interestRepository.insertInterestOne(vo);
			return 1;
		}
	}
		

	//관심숙소 조회
	@Override
	public boolean selectInterestOne(InterestVO vo) {
		if(interestRepository.selectInterestOne(vo) == 1) {
			return true; //조회 데이터 있음
		} else {
			return false; //조회 데이터 없음
		}
	}

	//리뷰개수 조회
	@Override
	public int countReview(int accoNo) {
		return reviewRepository.countReview(accoNo);
	}

	//별점평균 조회
	@Override
	public Double starAvg(int accoNo) {
		return reviewRepository.starAvg(accoNo);
	}
	
	//사진모달 데이터 교체
	public List<AccoPhotoVO> photoModal(AccoPhotoVO vo){
		return accoPhotoRepository.photoModal(vo);
	}
}
