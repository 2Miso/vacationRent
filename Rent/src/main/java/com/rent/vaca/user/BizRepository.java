package com.rent.vaca.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.room.RoomVO;

@Repository
public class BizRepository {
	private final SqlSession template;

	@Autowired
	public BizRepository(SqlSession template) {
		this.template = template;
	}
	
	// 회원가입
	public int insertBizOne(BizVO vo) {
		return template.insert("bizMapper.insertBizOne", vo);
	}
	
	// 로그인
	public BizVO selectBizOne(BizVO vo) {
		return template.selectOne("bizMapper.insertBizOne", vo);
	}
	
	// 이메일 중복확인
	public int selectBizCntByEmail(String email) {
		return template.selectOne("bizMapper.selectBizCntByEmail", email);
	}
	
	// 숙소 한건 조회
	public int selectBizCntByBizId(int bizId) {
		return template.insert("accoMapper.selectBizCntByBizId", bizId);
	}
	
	// 객실 한건 등록
	public int insertRoomOne(RoomVO vo) {
		return template.insert("bizMapper.insertRoomOne", vo);
	}
	
	// 방금 등록한 객실 조회(사진 등록용)
	public int selectLastInsertedRoomNo(int roomNo) {
		return template.insert("accoMapper.selectBizCntByBizId", roomNo);
	}
	
}
