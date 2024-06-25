package com.hostel.mgt.bean;

public class FeeBean extends BaseBean{
	
	private long userId;
	private String name;
	private long hostelId;
	private String hostelName;
	private long roomId;
	private String roomName;	
	private String totalfee;
	private String pay;
	private String paidfee;
	private String remainingfee;
	
	private long allotmentId;
	


	public long getAllotmentId() {
		return allotmentId;
	}

	public void setAllotmentId(long allotmentId) {
		this.allotmentId = allotmentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getHostelId() {
		return hostelId;
	}

	public void setHostelId(long hostelId) {
		this.hostelId = hostelId;
	}

	public String getHostelName() {
		return hostelName;
	}

	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getTotalfee() {
		return totalfee;
	}

	public void setTotalfee(String totalfee) {
		this.totalfee = totalfee;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getPaidfee() {
		return paidfee;
	}

	public void setPaidfee(String paidfee) {
		this.paidfee = paidfee;
	}

	public String getRemainingfee() {
		return remainingfee;
	}

	public void setRemainingfee(String remainingfee) {
		this.remainingfee = remainingfee;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
