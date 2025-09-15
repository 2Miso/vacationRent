package com.rent.vaca.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.vaca.user.UserRepository;
import com.rent.vaca.user.User.UserService;

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

}

