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
	
	//공지사항 목록조회
	public List<NoticeVO> selectAllNotice(){
		return template.selectList("noticeMapper.selectAllNotice");
	}
	
	//공지사항 단건조회
	public NoticeVO selectNoticeByNoticeNo(int noticeNo) {
		return template.selectOne("noticeMapper.selectNoticeByNoticeNo", noticeNo);
	}
	
	//1대1 문의 작성
	public int insertQuestionOne(NoticeVO vo){
		return template.insert("noticeMapper.insertQuestionOne", vo);
	}

	//1대1 문의 조회
	public NoticeVO selectQuestionByNoticeNo(int noticeNo) {
		return template.selectOne("noticeMapper.selectQuestionByNoticeNo", noticeNo);
	}
	
	//1대1 문의 삭제
	public int deleteQuestionOne(int noticeNo) {
		return template.delete("noticeMapper.deleteQuestionOne", noticeNo);
	}
	
	//관리자1대1 문의 답변등록
	public int updateAnswerOne(NoticeVO vo) {
		return template.update("noticeMapper.updateAnswerOne", vo);
	}
}
