package com.rent.vaca.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InterestRepository {
	
	private final SqlSession template;

	@Autowired
	public InterestRepository(SqlSession template) {
		this.template = template;
	}
	
	//관심숙소 등록
	public int insertInterestOne(InterestVO vo){
		return template.insert("interestMapper.insertInterestOne", vo);
	}
	
	//관심숙소 삭제
	public int deleteInterestOne(InterestVO vo){
		return template.delete("interestMapper.deleteInterestOne", vo);
	}
	
	//관심숙소 조회
	public int selectInterestOne(InterestVO vo) {
		return template.selectOne("interestMapper.selectInterestOne", vo);
	}
}
