package com.rent.vaca.room;

import java.util.List;

import com.rent.vaca.acco.AccoPhotoVO;
import com.rent.vaca.reserv.ReservVO;

public class RoomVO{
	private int roomNo;
	private int accoNo;
	private String ho;
	private String name;
	private int price;
	private double area;
	private String bedType;
	private int restroomNo;
	private String delyn;
	private int standardHead;
	private int extraHead;
	private String description;
	private List<ReservVO> reservList;
	private String thumbnailImage;
	private List<AccoPhotoVO> photoList;
	
	public String getThumbnailImage() {
		return thumbnailImage;
	}
	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public int getAccoNo() {
		return accoNo;
	}
	public void setAccoNo(int accoNo) {
		this.accoNo = accoNo;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public int getRestroomNo() {
		return restroomNo;
	}
	public void setRestroomNo(int restroomNo) {
		this.restroomNo = restroomNo;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}
	public int getStandardHead() {
		return standardHead;
	}
	public void setStandardHead(int standardHead) {
		this.standardHead = standardHead;
	}
	public int getExtraHead() {
		return extraHead;
	}
	public void setExtraHead(int extraHead) {
		this.extraHead = extraHead;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ReservVO> getReservList() {
		return reservList;
	}
	public void setReservList(List<ReservVO> reservList) {
		this.reservList = reservList;
	}
	public List<AccoPhotoVO> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<AccoPhotoVO> photoList) {
		this.photoList = photoList;
	}	
	@Override
	public String toString() {
		return "RoomVO [roomNo=" + roomNo + ", accoNo=" + accoNo + ", ho=" + ho + ", name=" + name + ", price=" + price
				+ ", area=" + area + ", bedType=" + bedType + ", restroomNo=" + restroomNo + ", delyn=" + delyn
				+ ", standardHead=" + standardHead + ", extraHead=" + extraHead + ", description=" + description
				+ ", reservList=" + reservList + "]";
	}
	
	
}
