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
	
	//상단 사진 5개
	public List<AccoPhotoVO> selectTopPhotos(int accoNo){
		return template.selectList("accoPhotoMapper.selectTopPhotos", accoNo);
	}
	
	//사진모달 전경
	public List<AccoPhotoVO> photoModalAcco(AccoPhotoVO vo){
		return template.selectList("accoPhotoMapper.photoModalAcco", vo);
	}
	
	//사진모달 데이터 교체
	public List<AccoPhotoVO> photoModal(AccoPhotoVO vo){
		return template.selectList("accoPhotoMapper.photoModal", vo);
	}

}
