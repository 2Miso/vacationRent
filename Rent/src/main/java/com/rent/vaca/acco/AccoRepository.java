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
	
	//���� 1�� ��ȸ
	public AccoVO selectAccoByAccoNo(int accoNo) {
		return template.selectOne("accoMapper.selectAccoByAccoNo", accoNo);
	}
	
	//���ɼ��� ���
	int insertInterestOne(InterestVO vo){
		return template.insert("accoMapper.insertInterestOne", vo);
	}
	
	//���ɼ��� ����
	int deleteInterestOne(InterestVO vo){
		return template.delete("accoMapper.deleteInterestOne", vo);
	}
	
	//���ɼ��� ��ȸ
	public InterestVO selectInterestOne(InterestVO vo) {
		return template.selectOne("accoMapper.selectInterestOne", vo);
	}
}
