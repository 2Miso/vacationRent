package com.rent.vaca.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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
	
	// ���� ���� ������Ʈ
	public int updateAccoInfo(AccoVO vo) {
		return template.update("accoMapper.updateAccoInfo", vo);
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
	
	// 
	public Integer deleteAccoDelyn(int accoNo) {
		return template.delete("accoMapper.deleteAccoDelyn", accoNo);
	}
	
	// ���� ���� ����
	public int deleteAccoPhotoByAccoNo(int accoNo) {
		return template.delete("accoPhotoMapper.deleteAccoPhotoByAccoNo", accoNo);
	}
	
	// ���� �Ѱ� ���
	public Integer insertRoomOne(RoomVO vo) {
		return template.insert("roomMapper.insertRoomOne", vo);
	}
	
	// ���� ���� ���
	public Integer insertRoomPhoto(AccoPhotoVO vo) {
		return template.insert("roomMapper.insertRoomPhoto", vo);
	}
	
	// ���� �Ѱ� ��ȸ
	public RoomVO selectAccoRoomOne(int accoNo) {
		return template.selectOne("roomMapper.selectAccoRoomOne", accoNo);
	}
	
	// ��� ����� ���� ��ȸ(���� ��Ͽ�)
	public Integer selectLastInsertedRoomNo(int roomNo) {
		return template.insert("accoMapper.selectBizCntByBizId", roomNo);
	}
	
}
