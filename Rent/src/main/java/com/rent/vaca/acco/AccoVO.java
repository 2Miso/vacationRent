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
	private String orderBy;
	private List<AccoPhotoVO> photoList;

	public int getAccoNo() {
		return accoNo;
	}
	public void setAccoNo(int accoNo) {
		this.accoNo = accoNo;
	}
	public String getType() {
		String typeKo;
		switch(type) {
			case 1:
				typeKo="호텔";
				break;
			case 2:
				typeKo="모텔";
				break;
			case 3:
				typeKo="리조트";
				break;
			case 4:
				typeKo="펜션";
				break;
			default:
				typeKo=""+type;
		}
		return typeKo;
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
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public List<AccoPhotoVO> getPhotoList() {
		return photoList;
	}
	public void setPhotoList(List<AccoPhotoVO> photoList) {
		this.photoList = photoList;
	}
	@Override
	public String toString() {
		return "AccoVO [accoNo=" + accoNo + ", type=" + type + ", name=" + name + ", addr=" + addr + ", phone=" + phone
				+ ", description=" + description + ", bizHour=" + bizHour + ", delyn=" + delyn + ", checkin=" + checkin
				+ ", checkout=" + checkout + ", bizId=" + bizId + ", biz=" + biz + ", roomList=" + roomList + "]";
	}
}
