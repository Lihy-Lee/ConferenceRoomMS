package com.meetingroom.entity;

import java.util.ArrayList;

/**
 * 会议参与人员表
 * 
 * @author LHY
 *
 */
public class Attend {
	private Booking booking;
	private ArrayList<User> user;

	private ArrayList<String> email;

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public ArrayList<String> getEmail() {
		return email;
	}

	public void setEmail(ArrayList<String> email) {
		this.email = email;
	}

	public ArrayList<User> getUser() {
		return user;
	}

	public void setUser(ArrayList<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Attend [booking=" + booking + ", user=" + user;
	}

}
