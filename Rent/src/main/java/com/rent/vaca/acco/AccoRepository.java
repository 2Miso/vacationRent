package com.rent.vaca.acco;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rent.vaca.user.InterestVO;


@Repository
public class AccoRepository {
	
	private final SqlSession template;

	@Autowired
	public AccoRepository(SqlSession template) {
		this.template = template;
	}
	
	//숙소 1건 조회
	public AccoVO selectAccoByAccoNo(int accoNo) {
		return template.selectOne("accoMapper.selectAccoByAccoNo", accoNo);
	}
	
	//관심숙소 등록
	int insertInterestOne(InterestVO vo){
		return template.insert("accoMapper.insertInterestOne", vo);
	}
	
	//관심숙소 삭제
	int deleteInterestOne(InterestVO vo){
		return template.delete("accoMapper.deleteInterestOne", vo);
	}
	
	//관심숙소 조회
	public InterestVO selectInterestOne(InterestVO vo) {
		return template.selectOne("accoMapper.selectInterestOne", vo);
	}
}
