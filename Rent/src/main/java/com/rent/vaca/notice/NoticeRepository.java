package com.rent.vaca.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
