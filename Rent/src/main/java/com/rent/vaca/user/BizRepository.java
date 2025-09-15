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
	
	// ȸ������
	public int insertBizOne(BizVO vo) {
		return template.insert("bizMapper.insertBizOne", vo);
	}
	
	// �α���
	public BizVO selectBizOne(BizVO vo) {
		return template.selectOne("bizMapper.insertBizOne", vo);
	}
	
	// �̸��� �ߺ�Ȯ��
	public int selectBizCntByEmail(String email) {
		return template.selectOne("bizMapper.insertBizOne", email);
	}
	
}
