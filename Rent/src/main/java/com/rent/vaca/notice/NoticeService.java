package com.rent.vaca.notice;

import java.util.List;

public interface NoticeService {
	//�������� �����ȸ
	List<NoticeVO> selectAllNotice();
	
	//�������� �ܰ���ȸ
	NoticeVO selectNoticeByNoticeNo(int noticeNo);
	
	
}
