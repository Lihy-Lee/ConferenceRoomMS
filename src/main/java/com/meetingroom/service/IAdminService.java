package com.meetingroom.service;

import java.util.ArrayList;

import com.meetingroom.common.ServerResponse;
import com.meetingroom.entity.Admin;
import com.meetingroom.entity.Booking;
import com.meetingroom.entity.MeetingRoom;
import com.meetingroom.entity.User;

/**
 * Created by yzx on 2018/7/7.
 */

public interface IAdminService {
	/**
	 * 管理员登录
	 * 
	 * @param adminId
	 * @param password
	 * @return
	 */
	ServerResponse<Admin> login(Integer adminId, String password);

	/**
	 * 添加管理员账号
	 * 
	 * @param admin
	 * @return
	 */
	ServerResponse<String> insertAdmin(Admin admin);

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	ServerResponse<User> insertUser(User user);

	/**
	 * 更新用户信息,禁用用户等
	 * 
	 * @param user
	 * @return
	 */
	ServerResponse<User> updateUser(User user);

	/**
	 * 添加会议室
	 * 
	 * @param meetingRoom
	 * @return
	 */
	ServerResponse<MeetingRoom> insertMeetingRoom(MeetingRoom meetingRoom);

	/**
	 * 查看所有会议室
	 * 
	 * @return
	 */
	ServerResponse<ArrayList<MeetingRoom>> allMeetingRoom();

	/**
	 * 修改会议室
	 * 
	 * @param meetingRoom
	 * @return
	 */
	ServerResponse<MeetingRoom> updateMeetingRoom(MeetingRoom meetingRoom);

	/**
	 * 删除会议室
	 * 
	 * @param meetingRoomId
	 * @return
	 */
	ServerResponse<String> deleteMeetingRoom(Integer meetingRoomId);

	ServerResponse<ArrayList<Booking>> exportExcel();

	//重置用户密码
	ServerResponse<String> resetUserPsw(Integer userId);

}
