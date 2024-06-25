package com.hostel.mgt.bean;

public class AllotmentBean extends BaseBean {
	
	private long HostelId;
	private String HostelName;
	private long userId;
	private String name;
	private long roomId;
	private String roomNo;
	

	
	public long getRoomId() {
		return roomId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	public long getHostelId() {
		return HostelId;
	}
	public void setHostelId(long hostelId) {
		HostelId = hostelId;
	}
	public String getHostelName() {
		return HostelName;
	}
	public void setHostelName(String hostelName) {
		HostelName = hostelName;
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
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
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
