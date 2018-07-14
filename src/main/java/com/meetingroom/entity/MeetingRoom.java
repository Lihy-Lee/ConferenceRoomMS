package com.meetingroom.entity;

/**
 * 会议室实体类
 * 
 * @author LHY
 *
 */
public class MeetingRoom {
	private Integer meetingId;
	private String floor;
	private String room;
	private Integer capacity;// 会议室人数限制
	private String dev;// 会议室中包含的设备

	public MeetingRoom() {
		super();
	}

	public MeetingRoom(Integer meetingId) {
		super();
		this.meetingId = meetingId;
	}

	public MeetingRoom(String floor, String room, Integer capacity, String dev) {
		super();
		this.floor = floor;
		this.room = room;
		this.capacity = capacity;
		this.dev = dev;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getDev() {
		return dev;
	}

	public void setDev(String dev) {
		this.dev = dev;
	}

	@Override
	public String toString() {
		return "MeetingRoom [meetingId=" + meetingId + ", floor=" + floor + ", room=" + room + ", capacity=" + capacity
				+ ", dev=" + dev + "]";
	}

}
