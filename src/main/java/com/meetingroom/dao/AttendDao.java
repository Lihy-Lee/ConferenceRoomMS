package com.meetingroom.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.meetingroom.entity.User;

public interface AttendDao {

	/**
	 * 插入与会者
	 * 
	 * @param attend
	 * @return
	 */
	public int insertAttend(@Param("bookingId") Integer bookingId, @Param("userId") Integer usersId);

	/**
	 * 根据预订id删除与会者
	 * 
	 * @param bookingId
	 * @return
	 */
	public int deleteAttend(Integer bookingId);

	/**
	 * 根据预订id号查询出所有参与会议的该会议的员工
	 * 
	 * @param bookingId
	 * @return
	 */
	public ArrayList<User> queryAttendByBookingId(Integer bookingId);

}
