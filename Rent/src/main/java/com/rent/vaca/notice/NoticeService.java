package com.rent.vaca.notice;

import java.util.List;

public interface NoticeService {
	//공지사항 목록조회
	List<NoticeVO> selectAllNotice();
	
	//공지사항 단건조회
	NoticeVO selectNoticeByNoticeNo(int noticeNo);
	
	
}
