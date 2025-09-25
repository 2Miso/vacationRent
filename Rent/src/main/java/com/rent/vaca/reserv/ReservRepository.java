package com.rent.vaca.reserv;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservRepository {
	private final SqlSession template;

	@Autowired
	public ReservRepository(SqlSession template) {
		this.template = template;
	}
	
	//특정일, 특정 객실 예약여부 조회
	public boolean checkReservation(ReservVO vo){
		return template.selectOne("reservMapper.checkReservation", vo);
	}
}
