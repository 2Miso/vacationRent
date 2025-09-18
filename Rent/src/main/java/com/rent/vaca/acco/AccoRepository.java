package com.rent.vaca.acco;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccoRepository {
	
	private final SqlSession template;

	@Autowired
	public AccoRepository(SqlSession template) {
		this.template = template;
	}
	
	//���� 1�� ��ȸ
	public AccoVO selectAccoByAccoNo(int accoNo) {
		return template.selectOne("accoMapper.selectAccoByAccoNo", accoNo);
	}

}
