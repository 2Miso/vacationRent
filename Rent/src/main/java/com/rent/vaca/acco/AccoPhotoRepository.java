package com.rent.vaca.acco;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccoPhotoRepository {
	
	private final SqlSession template;

	@Autowired
	public AccoPhotoRepository(SqlSession template) {
		this.template = template;
	}
	
	//��� ���� 5��
	public List<AccoPhotoVO> selectTopPhotos(int accoNo){
		return template.selectList("accoPhotoMapper.selectTopPhotos", accoNo);
	}
	
	//������� ����
	public List<AccoPhotoVO> photoModalAcco(AccoPhotoVO vo){
		return template.selectList("accoPhotoMapper.photoModalAcco", vo);
	}
	
	//������� ������ ��ü
	public List<AccoPhotoVO> photoModal(AccoPhotoVO vo){
		return template.selectList("accoPhotoMapper.photoModal", vo);
	}

}
