package com.rent.vaca.acco;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.vaca.room.RoomRepository;
import com.rent.vaca.user.InterestVO;

@Service
public class AccoServiceImpl implements AccoService {
	private static final Logger logger = LoggerFactory.getLogger(AccoRepository.class);
	
	private final AccoRepository accoRepository;
	private final RoomRepository roomRepository;
	
	@Autowired
	public AccoServiceImpl(AccoRepository accoRepository, RoomRepository roomRepository) {
		this.accoRepository = accoRepository;
		this.roomRepository = roomRepository;
	}
	
	//숙소 1건 조회
	public AccoVO selectAccoByAccoNo(int accoNo) {
		return accoRepository.selectAccoByAccoNo(accoNo);
	}

	//관심숙소 버튼 클릭
	@Override
	public int hitInterestBtn(InterestVO vo) {
		if(selectInterestOne(vo)) {//true: 조회데이터 있음, false: 조회데이터 없음
			logger.info("hitInterestBtn의 true");
			accoRepository.deleteInterestOne(vo);
			return 0;
		} else {
			logger.info("hitInterestBtn의 false");
			accoRepository.insertInterestOne(vo);
			return 1;
		}
	}
		

	//관심숙소 조회
	@Override
	public boolean selectInterestOne(InterestVO vo) {
		if(accoRepository.selectInterestOne(vo) == 1) {
			return true; //조회 데이터 있음
		} else {
			return false; //조회 데이터 없음
		}
	}

	//리뷰개수 조회
	@Override
	public int countReview(int accoNo) {
		return accoRepository.countReview(accoNo);
	}

	//별점평균 조회
	@Override
	public Double starAvg(int accoNo) {
		return accoRepository.starAvg(accoNo);
	}
}
