package com.rent.vaca.notice;

import java.util.List;

import com.rent.vaca.user.BizVO;
import com.rent.vaca.user.UserVO;

public class NoticeVO {
	private int noticeNo;
	private int userId;
	private String type;
	private String title;
	private String content;
	private String wdate;
	private String delyn;
	private String answeryn;
	private String answerContent;
	private String answerDate;
	private UserVO user;
	private BizVO biz;
	private List<QuestionAttachVO> questionAttachList;

	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWdate() {
		return wdate.substring(0,10);
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}
	public String getAnsweryn() {
		return answeryn;
	}
	public void setAnsweryn(String answeryn) {
		this.answeryn = answeryn;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	public BizVO getBiz() {
		return biz;
	}
	public void setBiz(BizVO biz) {
		this.biz = biz;
	}
	public List<QuestionAttachVO> getQuestionAttachList() {
		return questionAttachList;
	}
	public void setQuestionAttachList(List<QuestionAttachVO> questionAttachList) {
		this.questionAttachList = questionAttachList;
	}
	@Override
	public String toString() {
		return "NoticeVO [noticeNo=" + noticeNo + ", userId=" + userId + ", type=" + type + ", title=" + title
				+ ", content=" + content + ", wdate=" + wdate + ", delyn=" + delyn + ", answeryn=" + answeryn
				+ ", answerContent=" + answerContent + ", answerDate=" + answerDate + ", user=" + user + ", biz=" + biz
				+ ", questionAttachList=" + questionAttachList + "]";
	}
}
