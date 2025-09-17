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
import com.rent.vaca.user.InterestVO;

@Service
public class AccoServiceImpl implements AccoService {
	private static final Logger logger = LoggerFactory.getLogger(AccoRepository.class);
	
	private final AccoRepository accoRepository;
	private final RoomRepository roomRepository;
	private final ReviewRepository reviewRepository;
	
	@Autowired
	public AccoServiceImpl(AccoRepository accoRepository, RoomRepository roomRepository,
			ReviewRepository reviewRepository) {
		this.accoRepository = accoRepository;
		this.roomRepository = roomRepository;
		this.reviewRepository = reviewRepository;
	}
	
	//���� 1�� ��ȸ
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
			accoRepository.deleteInterestOne(vo);
			return 0;
		} else {
			logger.info("hitInterestBtn�� false");
			accoRepository.insertInterestOne(vo);
			return 1;
		}
	}
		

	//���ɼ��� ��ȸ
	@Override
	public boolean selectInterestOne(InterestVO vo) {
		if(accoRepository.selectInterestOne(vo) == 1) {
			return true; //��ȸ ������ ����
		} else {
			return false; //��ȸ ������ ����
		}
	}

	//���䰳�� ��ȸ
	@Override
	public int countReview(int accoNo) {
		return accoRepository.countReview(accoNo);
	}

	//������� ��ȸ
	@Override
	public Double starAvg(int accoNo) {
		return accoRepository.starAvg(accoNo);
	}
}
