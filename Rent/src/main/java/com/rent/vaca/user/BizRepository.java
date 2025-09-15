package com.rent.vaca.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BizRepository {
	private final SqlSession template;

	@Autowired
	public BizRepository(SqlSession template) {
		this.template = template;
	}
	
	// 회원가입
	public int insertBizOne(BizVO vo) {
		return template.insert("bizMapper.insertBizOne", vo);
	}
	
	// 로그인
	public BizVO selectBizOne(BizVO vo) {
		return template.selectOne("bizMapper.insertBizOne", vo);
	}
	
	// 이메일 중복확인
	public int selectBizCntByEmail(String email) {
		return template.selectOne("bizMapper.insertBizOne", email);
	}
	
}
