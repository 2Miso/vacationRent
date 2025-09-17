package com.rent.vaca.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	private final SqlSession template;

	@Autowired
	public UserRepository(SqlSession template) {
		this.template = template;
	}
	
	//ȸ������ �޼���
	public int join(UserVO vo){
		return template.insert("userMapper.join", vo);
	}
	
	//�α��� �޼���
	public UserVO login(UserVO vo){
		return template.selectOne("userMapper.login", vo);
	}
	
	//�̸��� �ߺ�Ȯ�� �޼���
	public int emailCheck(String email){
		return template.selectOne("userMapper.emailCheck", email);
	}
	
	public UserVO findEmail(UserVO vo) {
		return template.selectOne("userMapper.findEmail",vo);
	}
	

	
}
