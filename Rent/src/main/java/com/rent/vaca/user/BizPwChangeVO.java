package com.rent.vaca.user;

public class BizPwChangeVO {
	// 현재 비밀번호
	private String currentPw; 
	
	// 새 비밀번호
	private String newPw;
	
	// 새 비밀번호 확인
	private String confirmPw;

	public String getCurrentPw() {
		return currentPw;
	}

	public void setCurrentPw(String currentPw) {
		this.currentPw = currentPw;
	}

	public String getNewPw() {
		return newPw;
	}

	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}

	public String getConfirmPw() {
		return confirmPw;
	}

	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}
}
