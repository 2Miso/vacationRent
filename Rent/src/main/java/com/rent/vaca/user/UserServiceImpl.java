package com.rent.vaca.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.vaca.notice.NoticeVO;
import com.rent.vaca.user.UserRepository;
import com.rent.vaca.user.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository repository;
	
	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public int join(UserVO vo) {
		return repository.join(vo);
	}

	@Override
	public UserVO login(UserVO vo) {
		return repository.login(vo);
	}

	@Override
	public int emailCheck(String id) {
		return repository.emailCheck(id);
	}
	
	@Override
	public int phoneCheck(String phone) {
		return repository.emailCheck(phone);
	}

	@Override
	public UserVO findEmail(UserVO vo) {
		return repository.findEmail(vo);
	}
	
	@Override
	public UserVO pwauto(UserVO vo) {
		return repository.pwauto(vo);
	}
	
	@Override
	public UserVO findPw(UserVO vo) {
		return repository.findPw(vo);
	}
	
	@Override
	public int kakaojoin(String accesstoken) {
		return repository.kakaojoin(accesstoken);
	}

	@Override
	public UserVO kakaologin(String accesstoken) {
		return repository.kakaologin(accesstoken);
	}
	
	@Override
	public int naverjoin(String accesstoken) {
		return repository.naverjoin(accesstoken);
	}

	@Override
	public UserVO naverlogin(String accesstoken) {
		return repository.naverlogin(accesstoken);
	}
	
	@Override
	public UserVO useraccountupdate(UserVO vo) {
		return repository.useraccountupdate(vo);
	}
	
	@Override
	public List<Object> getPostsByUserId(int userId) {
		return repository.getPostsByUserId(userId);
	}

	@Override
	public UserVO useraccount(UserVO vo) {
		return repository.useraccount(vo);
	}

	@Override
	public UserVO useraccountsocial(UserVO vo) {
		return repository.useraccount(vo);
	}

}

