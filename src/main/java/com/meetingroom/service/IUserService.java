package com.meetingroom.service;

import java.util.ArrayList;

import com.meetingroom.common.ServerResponse;
import com.meetingroom.entity.Booking;
import com.meetingroom.entity.MeetingRoom;
import com.meetingroom.entity.User;

/**
 * Created by yzx on 2018/7/7.
 */
public interface IUserService {

	/**
	 * 登录
	 * 
	 * @param userId
	 *            用户id
	 * @param password
	 *            密码
	 * @return
	 */
	ServerResponse<User> login(Integer userId, String password);

	/**
	 * 更新用户个人信息
	 * 
	 * @param user
	 * @return
	 */
	ServerResponse<User> updateInformation(User user);

	/**
	 * 根据用户id查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	ServerResponse<User> queryUserByUserId(Integer userId);

	/**
	 * 检查用户密码是否正确
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	Boolean checkUserPassword(Integer userId, String password);

	/**
	 * 用户邮箱验证
	 * 
	 * @param email
	 * @return
	 */
	ServerResponse<String> verifyEmail(String email);


	/**
	 * 查询空闲会议室
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	ServerResponse<ArrayList<MeetingRoom>> listForFree(String startTime, String endTime);

	/**
	 * 预订会议室
	 * 
	 * @param booking
	 * @return
	 */
	ServerResponse<Booking> bookingMeetingRoom(Booking booking);

	/**
	 * 查看所有人
	 * 
	 * @return
	 */
	ServerResponse<ArrayList<User>> allUser();

	/**
	 * 选择参会人员
	 * 
	 * @param userList
	 * @return
	 */
	ServerResponse<String> addAttend(Integer bookingId, ArrayList<Integer> userList);

	/**
	 * 取消订单
	 * 
	 * @param bookingId
	 * @return
	 */
	ServerResponse<String> cancelBooking(Integer bookingId);

	/**
	 * 查看个人预订记录
	 * 
	 * @param userId
	 * @return
	 */
	ServerResponse<ArrayList<Booking>> showHistory(Integer userId);

	/**
	 * 查看用户已经预定但是还没开始的记录
	 * 
	 * @param userId
	 * @return
	 */
	ServerResponse<ArrayList<Booking>> hadBooking(Integer userId);
}
