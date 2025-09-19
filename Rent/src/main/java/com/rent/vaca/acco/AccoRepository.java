package com.rent.vaca.acco;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rent.vaca.search.SearchVO;

@Repository
public class AccoRepository {
	
	private final SqlSession template;

	@Autowired
	public AccoRepository(SqlSession template) {
		this.template = template;
	}
	
	//¼÷¼Ò 1°Ç Á¶È¸
	public AccoVO selectAccoByAccoNo(int accoNo) {
		return template.selectOne("accoMapper.selectAccoByAccoNo", accoNo);
	}

	//¼÷¼Ò°Ë»ö
	public List<AccoVO> search(SearchVO vo){
		return template.selectList("accoMapper.search", vo);
	}
}
