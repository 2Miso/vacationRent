package com.rent.vaca.user;

import javax.validation.constraints.*;

public class BizVO{
	private Integer id;
	
	@NotBlank(message = "�̸����� �ʼ��Դϴ�.")
	@Email(message = "�ùٸ� �̸��� ������ �ƴմϴ�.")
	private String email;
	
	@NotBlank(message = "��й�ȣ�� �ʼ��Դϴ�.")
	@Size(min = 8, max = 20, message = "��й�ȣ�� 8~20�� �Դϴ�.")
	private String pw;
	
	@NotBlank(message = "�ڵ�����ȣ�� �ʼ��Դϴ�.")
	@Pattern(regexp = "^(01[016789])[-]?(\\d{3,4})[-]?(\\d{4})$", message = "��ȿ�� �ڵ�����ȣ�� �Է����ּ���.")
	private String phone;
	
	private String wdate;
	private String approveyn;
	private String delyn;
	
	@NotBlank(message = "ȸ����� �Է����ּ���.")
	private String name;
	
	@NotBlank(message = "����ָ��� �Է����ּ���.")
	private String owner;
	
	@NotBlank(message = "����ڵ�Ϲ�ȣ�� �Է����ּ���.")
	@Pattern(regexp = "^\\d{10}$|^\\d{3}-\\d{2}-\\d{5}$", message = "��ȿ�� ����ڵ�Ϲ�ȣ�� �Է����ּ���.")
	private String certificateNo;
	
	
	private String certificateOriginalName;
	private String certificateSavedName;
	private String banyn;
	private String banReason;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getApproveyn() {
		return approveyn;
	}
	public void setApproveyn(String approveyn) {
		this.approveyn = approveyn;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getCertificateOriginalName() {
		return certificateOriginalName;
	}
	public void setCertificateOriginalName(String certificateOriginalName) {
		this.certificateOriginalName = certificateOriginalName;
	}
	public String getCertificateSavedName() {
		return certificateSavedName;
	}
	public void setCertificateSavedName(String certificateSavedName) {
		this.certificateSavedName = certificateSavedName;
	}
	public String getBanyn() {
		return banyn;
	}
	public void setBanyn(String banyn) {
		this.banyn = banyn;
	}
	public String getBanReason() {
		return banReason;
	}
	public void setBanReason(String banReason) {
		this.banReason = banReason;
	}

}
