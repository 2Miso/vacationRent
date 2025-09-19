package com.rent.vaca.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rent.vaca.acco.AccoPhotoVO;
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
	public Integer insertBizOne(BizVO vo) {
		return template.insert("bizMapper.insertBizOne", vo);
	}
	
	// �α���
	public BizVO selectBizOne(BizVO vo) {
		return template.selectOne("bizMapper.selectBizOne", vo);
	}
	
	// �̸��� �ߺ�Ȯ��
	public Integer selectBizCntByEmail(String email) {
		return template.selectOne("bizMapper.selectBizCntByEmail", email);
	}
	
	// ���� �Ѱ� ���
	public Integer insertAccoOne(AccoVO vo) {
		return template.insert("accoMapper.insertAccoOne", vo);
	}
	
	// ���� ���� ���
	public Integer insertAccoPhoto(AccoPhotoVO vo) {
		return template.insert("accoMapper.insertAccoPhoto", vo);
	}
	
	// ���� �Ѱ� ��ȸ
	public AccoVO selectBizAccoOne(int bizId) {
		return template.selectOne("accoMapper.selectBizAccoOne", bizId);
	}
	
	// ���� ���� ���� ��ȸ
	public int existsAccoByBizIdAndDelyn(int bizId, String delyn) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("bizId", bizId);
		params.put("delyn", delyn);

		return template.selectOne("accoMapper.existsAccoByBizIdAndDelyn", params);
	}
	
	// �� ���� ����� ���� ��ȸ
	public List<AccoPhotoVO> getPhotosByBizId(int accoNo){
		return template.selectList("accoPhotoMapper.getPhotosByBizId", accoNo);
	}
	
	// ���� ���� ����
	public int deleteAccoPhotoByAccoNo(int accoNo) {
		return template.delete("accoPhotoMapper.getDeleteAccoPhotoByAccoNo", accoNo);
	}
	
	// ���� �Ѱ� ���
	public Integer insertRoomOne(RoomVO vo) {
		return template.insert("bizMapper.insertRoomOne", vo);
	}
	
	// ���� ���� ���
	public Integer insertRoomPhoto(AccoPhotoVO vo) {
		return template.insert("roomMapper.insertRoomPhoto", vo);
	}
	
	// ��� ����� ���� ��ȸ(���� ��Ͽ�)
	public Integer selectLastInsertedRoomNo(int roomNo) {
		return template.insert("accoMapper.selectBizCntByBizId", roomNo);
	}
	
}
