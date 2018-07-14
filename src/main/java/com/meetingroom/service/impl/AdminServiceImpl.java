package com.meetingroom.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetingroom.common.ResponseCode;
import com.meetingroom.common.ServerResponse;
import com.meetingroom.dao.AdminDao;
import com.meetingroom.dao.BookingDao;
import com.meetingroom.dao.MeetingRoomDao;
import com.meetingroom.dao.UserDao;
import com.meetingroom.entity.Admin;
import com.meetingroom.entity.Booking;
import com.meetingroom.entity.MeetingRoom;
import com.meetingroom.entity.User;
import com.meetingroom.service.IAdminService;
import com.meetingroom.util.MD5Util;

/**
 * Created by yzx on 2018/7/7.
 */
@Service("iAdminService")
public class AdminServiceImpl implements IAdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private MeetingRoomDao meetingRoomDao;
	@Autowired
	private BookingDao bookingDao;

	@Override
	public ServerResponse<Admin> login(Integer aId, String password) {

		int resultCount = adminDao.checkAdminId(aId);// 根据id查找用户是否存在
		if (resultCount == 0) {
			return ServerResponse.createByErrorMessage("管理员不存在");
		}
		String md5Passsword = MD5Util.MD5EncodeUtf8(password);
		Admin admin = adminDao.selectLogin(aId, md5Passsword);
		if (admin == null) {
			System.out.println(md5Passsword);
			return ServerResponse.createByErrorMessage("密码错误");
		}
		admin.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
		return ServerResponse.createBySuccess("管理员登录成功", admin);
	}

	@Override
	public ServerResponse<String> resetUserPsw(Integer userId) {
		User user = userDao.queryUserByUserId(userId);
		if (user == null) {
			ServerResponse.createByErrorMessage("用户不存在");
		}
		User resetUser = new User();
		resetUser.setUserId(userId);
		resetUser.setPassword(MD5Util.MD5EncodeUtf8("123456"));
		int result = userDao.updatePersonInfo(resetUser);
		if (result == -1) {
			return ServerResponse.createByErrorMessage("重置用户密码失败");
		}
		return ServerResponse.createByErrorMessage("重置用户密码成功");

	}

	@Override
	public ServerResponse<String> insertAdmin(Admin admin) {

		int count = adminDao.insertAdmin(admin);
		if (count == 0) {
			return ServerResponse.createByErrorMessage("插入管理员账号失败");
		}
		return ServerResponse.createBySuccess("插入成功");
	}

	@Override
	public ServerResponse<User> insertUser(User user) {
		User oldUser = userDao.queryUserByUserId(user.getUserId());
		if (oldUser != null) {
			return ServerResponse.createByErrorMessage("用户id已存在!");
		}
		int count = userDao.insertUser(user);
		if (count == 0) {
			return ServerResponse.createByErrorMessage("添加用户失败");
		}
		return ServerResponse.createBySuccess("添加用户成功", user);
	}

	@Override
	public ServerResponse<User> updateUser(User user) {
		if (user.getUserId() == -1) {
			ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
					ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		}
		int result = userDao.updatePersonInfo(user);
		if (result > 0) {
			return ServerResponse.createBySuccess("更新用户信息成功", user);
		}
		return ServerResponse.createByErrorMessage("更新用户信息失败");
	}

	/**
	 * 查看所有的会议室
	 * 
	 * @return
	 */
	public ServerResponse<ArrayList<MeetingRoom>> allMeetingRoom() {
		ArrayList<MeetingRoom> meetingList = meetingRoomDao.queryAllMeetingRoom();
		return ServerResponse.createBySuccess("查看所有会议室", meetingList);
	}

	@Override
	public ServerResponse<MeetingRoom> insertMeetingRoom(MeetingRoom meetingRoom) {
		int result = meetingRoomDao.insertMeetingRoom(meetingRoom);
		if (result > 0) {

			return ServerResponse.createBySuccess("添加会议室成功", meetingRoom);
		}
		return ServerResponse.createByErrorMessage("添加会议室失败");
	}

	@Override
	public ServerResponse<MeetingRoom> updateMeetingRoom(MeetingRoom meetingRoom) {
		if (meetingRoom.getMeetingId() == -1) {
			ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
					ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		}
		int result = meetingRoomDao.updateMeetingRoom(meetingRoom);
		if (result > 0) {
			return ServerResponse.createBySuccess("更新会议室成功", meetingRoom);
		}
		return ServerResponse.createByErrorMessage("更新会议室失败");
	}

	@Override
	public ServerResponse<String> deleteMeetingRoom(Integer meetingRoomId) {
		int result = meetingRoomDao.deleteMeetingRoom(meetingRoomId);
		if (result > 0) {
			return ServerResponse.createBySuccess("删除会议室成功");
		}
		return ServerResponse.createByErrorMessage("删除会议室失败");
	}

	/**
	 * 导出Excel表
	 */
	@Override
	public ServerResponse<ArrayList<Booking>> exportExcel() {
		ArrayList<Booking> bookingList = bookingDao.selectAllForExcel();
		for (int index = 0; index < bookingList.size(); index++) {
			int userId = bookingList.get(index).getUserId();
			int roomId = bookingList.get(index).getMeetingId();
			System.out.println("test" + userId + " " + roomId);
			bookingList.get(index).setMeetingRoom(meetingRoomDao.queryMeetingRoomById(roomId));
			bookingList.get(index).setUser(userDao.queryUserNameByUserId(userId));
		}
		return ServerResponse.createBySuccess(bookingList);
	}

}
