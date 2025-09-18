package com.rent.vaca.review;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rent.vaca.acco.AccoVO;


@Repository
public class ReviewRepository {
	
	private final SqlSession template;

	@Autowired
	public ReviewRepository(SqlSession template) {
		this.template = template;
	}
	
	//������ ��ȸ
	public List<ReviewVO> selectReviewsByAccoNo(AccoVO acco) {
		return template.selectList("reviewMapper.selectReviewsByAccoNo", acco);
	}
	
	//���䰳�� ��ȸ
	public int countReview(int accoNo) {
		return template.selectOne("reviewMapper.countReview", accoNo);
	}
	
	//������� ��ȸ
	public Double starAvg(int accoNo) {
		return template.selectOne("reviewMapper.starAvg", accoNo);
	}
}
