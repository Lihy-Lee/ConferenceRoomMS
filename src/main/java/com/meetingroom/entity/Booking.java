package com.meetingroom.entity;

public class Booking {
	private Integer bookingId;
	private User user;// 预约用户
	private MeetingRoom meetingRoom;// 会议室号
	private String createTime;// 预约创建时间
	//private Date startTime;// 预约开始时间
	//private Date endTime;// 预约结束时间
	private String startTime;
	private String endTime;
	
	private Integer status;// 预订记录状态，0可用/1取消
	private Integer userId;
	private Integer meetingId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public Booking() {
		super();
	}

	public Booking(Integer bookingId) {
		super();
		this.bookingId = bookingId;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
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

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", user=" + user + ", meetingRoom=" + meetingRoom + ", createTime="
				+ createTime + ", startTime=" + startTime + ", endTime=" + endTime + ", status="
				+ status + "]";
	}

}
