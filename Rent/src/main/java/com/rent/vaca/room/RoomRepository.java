package com.rent.vaca.room;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepository {
	private final SqlSession template;

	@Autowired
	public RoomRepository(SqlSession template) {
		this.template = template;
	}
	
	public RoomVO selectRoomOne(RoomVO vo){
		return template.selectOne("roomMapper.selectRoomOne", vo);
	}
}
