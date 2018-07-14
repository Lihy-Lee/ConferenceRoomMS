package com.meetingroom.dao;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.meetingroom.BaseTest;
import com.meetingroom.entity.Attend;
import com.meetingroom.entity.Booking;
import com.meetingroom.entity.User;

public class AttendDaoTest extends BaseTest {
	@Autowired
	private AttendDao attendDao;

	public void insertAttendTest() {
		ArrayList<User> list = new ArrayList<>();
		list.add(new User(111));
		list.add(new User(222));

		Attend attend = new Attend();
		Booking booking = new Booking(1);
		attend.setBooking(booking);
		attend.setUser(list);
		// attendDao.insertAttend(booking, list);
	}

	public void deleteAttendTest() {
		attendDao.deleteAttend(1);

	}

	@Test
	public void queryAttendByBookingIdTest() {
		ArrayList<User> userList = attendDao.queryAttendByBookingId(1);
		System.out.println(userList);

	}

}
