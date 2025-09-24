package com.rent.vaca.search;

import java.util.List;

public class SearchVO {
	private String text;
	private String checkIn;
	private String checkOut;
	private Integer head;
	private Integer priceLow;
	private Integer priceHigh;
	private List<Integer> type;
	private String orderBy;
	private String orderQuery;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public Integer getHead() {
		return head;
	}
	public void setHead(Integer head) {
		this.head = head;
	}
	public Integer getPriceLow() {
		return priceLow;
	}
	public void setPriceLow(Integer priceLow) {
		this.priceLow = priceLow;
	}
	public Integer getPriceHigh() {
		return priceHigh;
	}
	public void setPriceHigh(Integer priceHigh) {
		this.priceHigh = priceHigh;
	}
	public List<Integer> getType() {
		return type;
	}
	public void setType(List<Integer> type) {
		this.type = type;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrderQuery() {
		return orderQuery;
	}
	public void setOrderQuery(String orderQuery) {
		this.orderQuery = orderQuery;
	}
	@Override
	public String toString() {
		return "SearchVO [text=" + text + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", head=" + head
				+ ", priceLow=" + priceLow + ", priceHigh=" + priceHigh + ", type=" + type + ", orderBy=" + orderBy
				+ ", orderQuery=" + orderQuery + "]";
	}
}
