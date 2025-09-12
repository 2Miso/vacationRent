package com.rent.vaca.notice;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface NoticeService {
	//공지사항 목록조회
	List<NoticeVO> selectAllNotice();
	
	//공지사항 단건조회
	NoticeVO selectNoticeByNoticeNo(int noticeNo);
	
	//1대1 문의 작성 + 첨부파일 첨부
	void insertQuestionOne(NoticeVO vo, List<MultipartFile> attach) throws IllegalArgumentException, IOException;
	
	//1대1 문의 단건조회, 답변 확인
	NoticeVO selectQuestionByNoticeNo(int noticeNo);
}
