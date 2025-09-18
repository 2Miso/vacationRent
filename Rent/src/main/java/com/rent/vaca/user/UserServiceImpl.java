package com.rent.vaca.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.vaca.user.UserRepository;
import com.rent.vaca.user.UserService;

//����Ͻ�����
//repository�� ���
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

}

