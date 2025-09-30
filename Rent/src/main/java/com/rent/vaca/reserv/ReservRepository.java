package com.rent.vaca.reserv;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rent.vaca.user.UserVO;

@Repository
public class ReservRepository {
	private final SqlSession template;

	@Autowired
	public ReservRepository(SqlSession template) {
		this.template = template;
	}
	
	//Ư����, Ư�� ���� ���࿩�� ��ȸ
	public boolean checkReservation(ReservVO vo){
		return template.selectOne("reservMapper.checkReservation", vo);
	}
	
	//���������� ���೻�� : �ٰ����� ����
	public List<ReservVO> selectReservList(int user){
		return template.selectList("reservMapper.selectReservList", user);
	}
	
	//���������� ���೻�� : ������ ����, ����� ����
	public List<ReservVO> deprecatedReservList(int user){
		return template.selectList("reservMapper.deprecatedReservList", user);
	}
}
