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
	
	////��� ���� 5��
	public List<AccoPhotoVO> selectTopPhotos(int accoNo){
		return accoPhotoRepository.selectTopPhotos(accoNo);
	}
	
	////����, �����, ���Ǹ�ϰ� ���ǻ���1��
	public AccoVO selectAccoByAccoNo(int accoNo) {
		return accoRepository.selectAccoByAccoNo(accoNo);
	}
	
	//���� ��� ��ȸ
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
	
	//���ɼ��� ��ư Ŭ��
	@Override
	public int hitInterestBtn(InterestVO vo) {
		if(selectInterestOne(vo)) {//true: ��ȸ������ ����, false: ��ȸ������ ����
			logger.info("hitInterestBtn�� true");
			interestRepository.deleteInterestOne(vo);
			return 0;
		} else {
			logger.info("hitInterestBtn�� false");
			interestRepository.insertInterestOne(vo);
			return 1;
		}
	}
		

	//���ɼ��� ��ȸ
	@Override
	public boolean selectInterestOne(InterestVO vo) {
		if(interestRepository.selectInterestOne(vo) == 1) {
			return true; //��ȸ ������ ����
		} else {
			return false; //��ȸ ������ ����
		}
	}

	//���䰳�� ��ȸ
	@Override
	public int countReview(int accoNo) {
		return reviewRepository.countReview(accoNo);
	}

	//������� ��ȸ
	@Override
	public Double starAvg(int accoNo) {
		return reviewRepository.starAvg(accoNo);
	}
	
	//������� ������ ��ü
	public List<AccoPhotoVO> photoModal(AccoPhotoVO vo){
		return accoPhotoRepository.photoModal(vo);
	}
}
