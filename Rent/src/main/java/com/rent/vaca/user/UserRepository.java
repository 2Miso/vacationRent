package com.rent.vaca.user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rent.vaca.notice.NoticeVO;

@Repository
public class UserRepository {
	
	private final SqlSession template;

	@Autowired
	public UserRepository(SqlSession template) {
		this.template = template;
	}
	
	public int join(UserVO vo){
		return template.insert("userMapper.join", vo);
	}
	
	public UserVO login(UserVO vo){
		return template.selectOne("userMapper.login", vo);
	}
	
	public int emailCheck(String email){
		return template.selectOne("userMapper.emailCheck", email);
	}
	
	public int phoneCheck(String phone){
		return template.selectOne("userMapper.phoneCheck", phone);
	}
	
	public UserVO findEmail(UserVO vo) {
		return template.selectOne("userMapper.findEmail",vo);
	}
	
	public UserVO pwauto(UserVO vo) {
		return template.selectOne("userMapper.pwauto",vo);
	}
	
	public UserVO findPw(UserVO vo) {
		return template.selectOne("userMapper.findPw",vo);
	}
	
	public int kakaojoin(String accesstoken){
		return template.insert("userMapper.kakaojoin", accesstoken);
	}	
		
	public UserVO kakaologin(String accesstoken){
		return template.selectOne("userMapper.kakaologin", accesstoken);
	}	
	
	public int naverjoin(String accesstoken){
		return template.insert("userMapper.naverjoin", accesstoken);
	}	
			
	public UserVO naverlogin(String accesstoken){
		return template.selectOne("userMapper.naverlogin", accesstoken);
	}		
	
	public UserVO useraccountupdate(UserVO vo) {
		return template.selectOne("userMapper.useraccountupdate",vo);
	}

	public List<Object> getPostsByUserId(int userId) {
		return template.selectList("userMapper.getPostsByUserId",userId);
	}
	
	public List<Object> getReservByUserId(int userId) {
		return template.selectList("userMapper.getPostsByUserId",userId);
	}
	
	public UserVO useraccount(UserVO vo) {
		return template.selectOne("userMapper.useraccount",vo);
	}
	
	public UserVO useraccountchange(UserVO vo) {
		return template.selectOne("userMapper.useraccountchange",vo);
	}
	
	public UserVO useraccountsocial(UserVO vo) {
		return template.selectOne("userMapper.useraccount",vo);
	}
}
