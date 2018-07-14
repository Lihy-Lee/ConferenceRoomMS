package com.meetingroom.entity;

public class ExportBookingExcel {
	// String[] headers = { "预约序号", "预约用户", "会议室号", "预约创建时间", "预约开始时间", "预约结束时间",
	// "预约状态(0:已用;1:已取消)" };
	private Integer bookingId;
	private String userName;
	private Integer meetingId;
	private String createTime;
	private String startTime;
	private String endTime;
	private Integer status;


	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
