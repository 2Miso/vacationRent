package com.rent.vaca.notice;

import java.io.File;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class NoticeRepository {
	private final SqlSession template;
	
	@Autowired
	public NoticeRepository(SqlSession template) {
		this.template = template;
	}
	
	//�������� �����ȸ
	public List<NoticeVO> selectAllNotice(){
		return template.selectList("noticeMapper.selectAllNotice");
	}
	
	//�������� �ܰ���ȸ
	public NoticeVO selectNoticeByNoticeNo(int noticeNo) {
		return template.selectOne("noticeMapper.selectNoticeByNoticeNo", noticeNo);
	}
	
	//1��1 ���� �ۼ�
	public int insertQuestionOne(NoticeVO vo){
		return template.insert("noticeMapper.insertQuestionOne", vo);
	}

	//1��1 ���� ��ȸ
	public NoticeVO selectQuestionByNoticeNo(int noticeNo) {
		return template.selectOne("noticeMapper.selectQuestionByNoticeNo", noticeNo);
	}
	
	//1��1 ���� ����
	public int deleteQuestionOne(int noticeNo) {
		return template.delete("noticeMapper.deleteQuestionOne", noticeNo);
	}
	
	//������1��1 ���� �亯���
	public int updateAnswerOne(NoticeVO vo) {
		return template.update("noticeMapper.updateAnswerOne", vo);
	}
}
