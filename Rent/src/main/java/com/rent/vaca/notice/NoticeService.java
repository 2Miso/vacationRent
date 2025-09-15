package com.rent.vaca.notice;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rent.vaca.user.UserVO;

public interface NoticeService {
	//�������� �����ȸ
	List<NoticeVO> selectAllNotice();
	
	//�������� �ܰ���ȸ
	NoticeVO selectNoticeByNoticeNo(int noticeNo);
	
	//�������� �ۼ�
	void insertNoticeOne(NoticeVO vo, String grade);
	
	//1��1 ���� �ۼ� + ÷������ ÷��
	void insertQuestionOne(NoticeVO vo, List<MultipartFile> attach) throws IllegalArgumentException, IOException;
	
	//1��1 ���� �ܰ���ȸ, �亯 Ȯ��
	NoticeVO selectQuestionByNoticeNo(int noticeNo);
	
	//1��1 ���� ����
	int deleteQuestionOne(int noticeNo, int id);
	
	//������ 1��1 �亯 ���(update)
	int updateAnswerOne(NoticeVO vo, UserVO user);
}
