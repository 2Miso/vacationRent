package com.rent.vaca.user;

public class BizPwChangeVO {
	// ���� ��й�ȣ
	private String currentPw; 
	
	// �� ��й�ȣ
	private String newPw;
	
	// �� ��й�ȣ Ȯ��
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
