package com.rent.vaca.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService{
	private final NoticeRepository noticeRepository;
	
	@Autowired
	public NoticeServiceImpl(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
	}
	
	@Override
	public List<NoticeVO> selectAllNotice() {
		return noticeRepository.selectAllNotice();
	}

	@Override
	public NoticeVO selectNoticeByNoticeNo(int noticeNo) {
		return noticeRepository.selectNoticeByNoticeNo(noticeNo);
	}

	/*
	 * @Override public NoticeVO selectNoticeByNoticeNo(int noticeNo) { return null;
	 * }
	 */
}
