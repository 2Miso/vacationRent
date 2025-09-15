package com.rent.vaca.user;

import com.rent.vaca.user.UserVO;

public interface UserService {
	//ȸ������ �߻�޼���
	int join(UserVO vo);
	
	//�α��� �߻�޼���
	UserVO login(UserVO vo);
	
	//�̸��� �ߺ�üũ �߻�޼���
	int emailCheck(String email);
}
