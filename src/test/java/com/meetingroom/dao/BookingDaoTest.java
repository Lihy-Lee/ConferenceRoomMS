package com.meetingroom.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.meetingroom.BaseTest;
import com.meetingroom.entity.Booking;
import com.meetingroom.entity.User;

public class BookingDaoTest extends BaseTest {
	@Autowired
	private BookingDao bookingDao;

	public void insertBookingTest() {
		Booking booking = new Booking();
		User user = new User();
		user.setUserId(111);
		booking.setUser(user);
		//	booking.setCreateTime(new Date());

		System.out.println(bookingDao.insertBooking(booking));
	}

	public void selectAllTest() {
		System.out.println(bookingDao.selectAllForExcel());
	}

	public void selectBookingByUserIdTest() {
		System.out.println(bookingDao.selectAllBookingByUserId(111));
	}

	public void updateBookingTest() {
		Booking booking  = new Booking();
		booking.setBookingId(10);
		booking.setStatus(1);
		bookingDao.updateBooking(booking);
	}
}
