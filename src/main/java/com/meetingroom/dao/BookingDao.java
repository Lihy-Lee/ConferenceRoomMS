package com.meetingroom.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.meetingroom.entity.Booking;

public interface BookingDao {
	/**
	 * 预订会议室
	 * 
	 * @param booking
	 * @return
	 */
	public int insertBooking(Booking booking);

	/**
	 * 查询数据库中所有的数据
	 * 
	 * @return
	 */
	public ArrayList<Booking> selectAllForExcel();

	/**
	 * 根据用户id查询用户的用户的所有记录
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList<Booking> selectAllBookingByUserId(Integer userId);

	public ArrayList<Booking> selectAllBooking();

	/**
	 * 根据用户id查询用户已经预约但是还没开始的预定记录
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList<Booking> selectHadBookingByUserId(Integer userId);

	/**
	 * 根据订单id查询用户的用户的所有记录
	 * 
	 * @param bookingId
	 * @return
	 */
	public Booking selectBookingByBookingId(Integer bookingId);

	/**
	 * 
	 * 更新订单状态status(取消预订)
	 * 
	 * @param booking
	 * @return
	 */
	public int updateBooking(Booking booking);

	public Booking checkBookingUser(@Param("bookingId") Integer bookingId, @Param("userId") Integer userId);

}
