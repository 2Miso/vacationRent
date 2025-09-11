package com.rent.vaca.acco;

import java.util.List;

import com.rent.vaca.review.ReviewPhotoVO;
import com.rent.vaca.review.ReviewVO;
import com.rent.vaca.room.RoomVO;
import com.rent.vaca.user.BizVO;


public class AccoVO {
	private int accoNo;
	private int type;
	private String name;
	private String addr;
	private String phone;
	private String description;
	private String bizHour;
	private String delyn;
	private String checkin;
	private String checkout;
	private int bizId;
	private BizVO biz;
	private List<RoomVO> roomList;
	
	public AccoVO() {}
	public AccoVO(int accoNo, int type, String name, String addr, String phone, String description, String bizHour,
			String delyn, String checkin, String checkout, int bizId, BizVO biz, List<RoomVO> roomList) {
		this.accoNo = accoNo;
		this.type = type;
		this.name = name;
		this.addr = addr;
		this.phone = phone;
		this.description = description;
		this.bizHour = bizHour;
		this.delyn = delyn;
		this.checkin = checkin;
		this.checkout = checkout;
		this.bizId = bizId;
		this.biz = biz;
		this.roomList = roomList;
	}

	public int getAccoNo() {
		return accoNo;
	}
	public void setAccoNo(int accoNo) {
		this.accoNo = accoNo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBizHour() {
		return bizHour;
	}
	public void setBizHour(String bizHour) {
		this.bizHour = bizHour;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}
	public String getCheckin() {
		return checkin.substring(0, 5);
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout.substring(0,5);
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public BizVO getBiz() {
		return biz;
	}
	public void setBiz(BizVO biz) {
		this.biz = biz;
	}
	public List<RoomVO> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<RoomVO> roomList) {
		this.roomList = roomList;
	}
}
