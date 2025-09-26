package com.rent.vaca.user;

import javax.validation.constraints.*;

public class BizVO{
	private Integer id;
	
	@NotBlank(message = "이메일은 필수입니다.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;
	
	@NotBlank(message = "비밀번호는 필수입니다.")
	@Size(min = 8, max = 20, message = "비밀번호는 8~20자 입니다.")
	private String pw;
	
	@NotBlank(message = "핸드폰번호는 필수입니다.")
	@Pattern(regexp = "^(01[016789])[-]?(\\d{3,4})[-]?(\\d{4})$", message = "유효한 핸드폰번호를 입력해주세요.")
	private String phone;
	
	private String wdate;
	private String approveyn;
	private String delyn;
	
	@NotBlank(message = "회사명을 입력해주세요.")
	private String name;
	
	@NotBlank(message = "사업주명을 입력해주세요.")
	private String owner;
	
	@NotBlank(message = "사업자등록번호를 입력해주세요.")
	@Pattern(regexp = "^\\d{10}$|^\\d{3}-\\d{2}-\\d{5}$", message = "유효한 사업자등록번호를 입력해주세요.")
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
