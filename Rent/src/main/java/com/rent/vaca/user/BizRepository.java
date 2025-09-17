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
	
	// ȸ������
	public int insertBizOne(BizVO vo) {
		return template.insert("bizMapper.insertBizOne", vo);
	}
	
	// �α���
	public BizVO selectBizOne(BizVO vo) {
		return template.selectOne("bizMapper.insertBizOne", vo);
	}
	
	// �̸��� �ߺ�Ȯ��
	public int selectBizCntByEmail(String email) {
		return template.selectOne("bizMapper.selectBizCntByEmail", email);
	}
	
	// ���� �Ѱ� ��ȸ
	public int selectBizCntByBizId(int bizId) {
		return template.insert("accoMapper.selectBizCntByBizId", bizId);
	}
	
	// ���� �Ѱ� ���
	public int insertRoomOne(RoomVO vo) {
		return template.insert("bizMapper.insertRoomOne", vo);
	}
	
	// ��� ����� ���� ��ȸ(���� ��Ͽ�)
	public int selectLastInsertedRoomNo(int roomNo) {
		return template.insert("accoMapper.selectBizCntByBizId", roomNo);
	}
	
}
