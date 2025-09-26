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
	
	// ����� ���� ����
	public int updateBizInfo(BizVO vo) {
		return template.update("bizMapper.updateBizInfo", vo);
	}
	
	// ����ڵ���� ���� ����
	public int updateCertificateDeleted(int id) {
		return template.delete("bizMapper.updateCertificateDeleted", id);
	}
	
	// ���� 1�� ���
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
	
	// ���� 1�� ��ȸ
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
	
	// ���� ����� ���� ��ȸ
	public List<AccoPhotoVO> getPhotosByBizId(int accoNo){
		return template.selectList("accoPhotoMapper.getPhotosByBizId", accoNo);
	}
	
	// ����� ���� ���� ��ȸ
	public List<AccoPhotoVO> getPhotosByBizIdAndRoomNo(int accoNo, int roomNo){
		Map<String, Object> params = new HashMap<>();
		params.put("accoNo", accoNo);
		params.put("roomNo", roomNo);		
		return template.selectList("accoPhotoMapper.getPhotosByBizIdAndRoomNo", params);
	}
	
	// 
	public Integer deleteAccoDelyn(int accoNo) {
		return template.delete("accoMapper.deleteAccoDelyn", accoNo);
	}
	
	// ���� ���� ����
	public int deleteAccoPhotoByAccoNo(int accoNo) {
		return template.delete("accoPhotoMapper.deleteAccoPhotoByAccoNo", accoNo);
	}
	
	// ���� 1�� ���
	public Integer insertRoomOne(RoomVO vo) {
		return template.insert("roomMapper.insertRoomOne", vo);
	}
	
	// ���� ���� ���
	public Integer insertRoomPhoto(AccoPhotoVO vo) {
		return template.insert("roomMapper.insertRoomPhoto", vo);
	}
	
	public int getPhotosByAccoNo(int roomNo) {
		return template.delete("roomMapper.deleteAccoPhotoByRoomNo", roomNo);
	}
	
	// ���ҳ� ���� ��ü ��ȸ
	public List<RoomVO> selectRoomsByAccoNo(int accoNo) {
		return template.selectList("roomMapper.selectRoomsByAccoNo", accoNo);
	}
	
	public List<AccoPhotoVO> getPhotosByBizIdAndRoomNo(int roomNo){
		return template.selectList("accoPhotoMapper.getPhotosByBizIdAndRoomNo", roomNo);
	}
	
	// ���� 1�� ��ȸ
	public RoomVO selectAccoRoomOne(int accoNo) {
		return template.selectOne("roomMapper.selectAccoRoomOne", accoNo);
	}
	
	// ���� 1�� ����
	public void updateRoomDelYn(int accoNo, int roomNo) {
		Map<String, Object> params = new HashMap<>();
        params.put("accoNo", accoNo);
        params.put("roomNo", roomNo);

        template.update("roomMapper.updateRoomDelYn", params);
	}
	// ���� 1�� ���� ����
	public void deleteAccoPhotoByRoomNo(int roomNo) {
		template.delete("accoPhotoMapper.deleteAccoPhotoByRoomNo", roomNo);
    }
	
}
