package com.meetingroom.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetingroom.common.ResponseCode;
import com.meetingroom.common.ServerResponse;
import com.meetingroom.dao.AttendDao;
import com.meetingroom.dao.BookingDao;
import com.meetingroom.dao.MeetingRoomDao;
import com.meetingroom.dao.UserDao;
import com.meetingroom.entity.Booking;
import com.meetingroom.entity.MeetingRoom;
import com.meetingroom.entity.User;
import com.meetingroom.service.IUserService;
import com.meetingroom.util.EmailUtil;
import com.meetingroom.util.MD5Util;

/**
 * Created by yzx on 2018/7/7.
 */

@Service("iUserService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private MeetingRoomDao meetingRoomDao;
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private AttendDao attendDao;

	@Override
	public Boolean checkUserPassword(Integer userId, String password) {
		User user = userDao.selectLogin(userId, MD5Util.MD5EncodeUtf8(password));
		if (user == null) {
			return false;
		}
		return true;
	}

	public ServerResponse<User> login(Integer userId, String password) {
		int resultCount = userDao.checkUserId(userId);// 根据id查找用户是否存在
		if (resultCount == 0) {
			return ServerResponse.createByErrorMessage("用户不存在");
		}
		String md5Passsword = MD5Util.MD5EncodeUtf8(password);
		// System.out.println("密码:"+md5Passsword);
		User user = userDao.selectLogin(userId, md5Passsword);

		if (user == null) {
			return ServerResponse.createByErrorMessage("密码错误");
		}
		if (user.getStatus() == 1) {
			return ServerResponse.createByErrorMessage("用户已经被禁用!");
		}
		user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
		return ServerResponse.createBySuccess("登录成功", user);
	}

	@Override
	public ServerResponse<User> updateInformation(User user) {

		User updateUser = new User();
		updateUser.setUserId(user.getUserId());
		// 需要修改邮箱
		if (user.getEmail() != null) {
			updateUser.setEmail(user.getEmail());
			System.out.println("修改邮箱:" + user.getEmail());
			// email也要进行一个校验,校验新的email是不是已经存在,并且存在的email如果相同的话,不能是我们当前的这个用户的.
			int resultCount = userDao.checkEmailByUserId(user.getEmail(), user.getUserId());
			if (resultCount > 0) {
				return ServerResponse.createByErrorMessage("email已存在,请更换email再尝试更新");
			}
		}
		// 需要修改密码
		if (user.getPassword() != null) {
			updateUser.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));// 密码加密
			System.out.println("修改密码:" + user.getPassword());
		}
		System.out.println("test:" + updateUser);
		int updateCount = userDao.updatePersonInfo(updateUser);
		if (updateCount > 0) {
			return ServerResponse.createBySuccess("更新个人信息成功", userDao.queryUserByUserId(updateUser.getUserId()));
		}
		return ServerResponse.createByErrorMessage("更新个人信息失败");
	}

	@Override
	public ServerResponse<User> queryUserByUserId(Integer userId) {
		User user = userDao.queryUserByUserId(userId);
		if (user != null) {
			return ServerResponse.createBySuccess("查询用户成功", user);
		}
		return ServerResponse.createByErrorMessage("查询用户失败");
	}

	@Override
	public ServerResponse<String> verifyEmail(String email) {
		try {
			EmailUtil.sendVerifyCode(email);// 调用方法发送验证码
		} catch (UnsupportedEncodingException | GeneralSecurityException | MessagingException e) {
			e.printStackTrace();
		}

		return ServerResponse.createBySuccessMessage("验证码已经发送");
	}

	@Override
	public ServerResponse<ArrayList<User>> allUser() {
		ArrayList<User> userList = userDao.queryAllUser();
		return ServerResponse.createBySuccess("查看所有用户", userList);

	}

	/**
	 * 返回空闲的会议室列表
	 * 
	 * @return
	 */
	@Override
	public ServerResponse<ArrayList<MeetingRoom>> listForFree(String startTime, String endTime) {
		if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
					ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		}
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-HH-dd HH:mm:ss");
		ArrayList<MeetingRoom> response = new ArrayList<>();

		response = meetingRoomDao.queryMeetingRoomforFree(startTime, endTime);
		// response = meetingRoomDao.queryMeetingRoomforFree(startTime,endTime);

		return ServerResponse.createBySuccess(response);
	}

	/**
	 * 用户预订会议室
	 * 
	 * @param bookingId
	 * @return
	 */
	@Override
	public ServerResponse<Booking> bookingMeetingRoom(Booking booking) {
		int result = bookingDao.insertBooking(booking);
		if (result == -1) {
			return ServerResponse.createByErrorMessage("预约会议室失败");
		}
		booking.setMeetingRoom(meetingRoomDao.queryMeetingRoomById(booking.getBookingId()));
		booking.setUser(userDao.queryUserByUserId(booking.getUserId()));
		return ServerResponse.createBySuccess("预约会议室成功", booking);
	}

	/**
	 * 添加与会人员
	 */
	@Override
	public ServerResponse<String> addAttend(Integer bookingId, ArrayList<Integer> userIdList) {
		// 根据订单id查询订单的基本信息
		Booking booking = bookingDao.selectBookingByBookingId(bookingId);

		MeetingRoom meetingRoom = meetingRoomDao.queryMeetingRoomById(booking.getMeetingId());// 获取会议室对应的信息

		// 转化时间格式
		String startTime = booking.getStartTime();// 开始时间
		String endTime = booking.getEndTime();// 结束时间
		String address = "会议地址地址:" + meetingRoom.getFloor() + "--" + meetingRoom.getRoom();
		String message = null;// 发送的内容
		User user = null;// 接收对象
		System.out.println("impl:" + userIdList);

		// 发送邮件
		for (int i = 0; i < userIdList.size(); i++) {
			System.out.println(i);
			// 检查数据库中是否已经插入
			Booking check = bookingDao.checkBookingUser(bookingId, userIdList.get(i));
			if (check == null) {

				user = userDao.queryUserByUserId(userIdList.get(i));
				message = user.getName() + "女士/先生，您好！系统提醒您，您将有一场会议在" + startTime + "--" + endTime + "，" + address
						+ "进行，请您留意!";
				try {
					// 发送邮件
					EmailUtil.sendEmails(user.getEmail(), message, 1);
					// 写入数据库
					System.out.println(userIdList.get(i));
					attendDao.insertAttend(bookingId, userIdList.get(i));
					System.out.println("插入数据成功");
				} catch (UnsupportedEncodingException | GeneralSecurityException | MessagingException e) {
					e.printStackTrace();
				}
			}
		}

		return ServerResponse.createBySuccessMessage("添加与会人员成功");
	}

	/**
	 * 取消预订
	 */
	@Override
	public ServerResponse<String> cancelBooking(Integer bookingId) {
		Booking booking = bookingDao.selectBookingByBookingId(bookingId);
		ArrayList<User> userList = attendDao.queryAttendByBookingId(bookingId);// 参与会议的人员列表

		String startTime = booking.getStartTime();
		String endTime = booking.getEndTime();

		MeetingRoom meetingRoom = meetingRoomDao.queryMeetingRoomById(booking.getMeetingId());// 获取会议室对应的信息

		String address = "会议地址:" + meetingRoom.getFloor() + "-" + meetingRoom.getRoom();
		String message = null;
		for (int i = 0; i < userList.size(); i++) {
			userList.set(i, userDao.queryUserByUserId(userList.get(i).getUserId()));
			message = userList.get(i).getName() + "女士/先生，您好！" + "\n" + "系统提醒您，您在" + startTime + "--" + endTime + "，"
					+ address + "进行的会议已取消，请您留意!";
			try {
				// 发送邮件
				EmailUtil.sendEmails(userList.get(i).getEmail(), message, 2);
			} catch (UnsupportedEncodingException | GeneralSecurityException | MessagingException e) {
				e.printStackTrace();
			}
		}
		booking.setStatus(1);
		int result = bookingDao.updateBooking(booking);
		int result1 = attendDao.deleteAttend(bookingId);// 从与会表中删除记录
		if (result == -1 || result1 == -1) {
			ServerResponse.createByErrorMessage("取消失败");
		}
		return ServerResponse.createByErrorMessage("取消预订会议室成功");
	}

	/**
	 * 查看用户预订的历史记录
	 * 
	 * @param userId
	 * @return
	 */
	public ServerResponse<ArrayList<Booking>> showHistory(Integer userId) {

		if (userId == -1) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
					ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		}
		ArrayList<Booking> response = bookingDao.selectAllBookingByUserId(userId);
		return ServerResponse.createBySuccess("查看会议室记录成功", response);
	}

	/**
	 * 根据用户id查看已经预定成功但是还没有开始的预定记录
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public ServerResponse<ArrayList<Booking>> hadBooking(Integer userId) {
		if (userId == -1) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
					ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		}
		ArrayList<Booking> response = bookingDao.selectHadBookingByUserId(userId);
		for (int index = 0; index < response.size(); index++) {
			int meetingId = response.get(index).getMeetingId();
			response.get(index).setMeetingRoom(meetingRoomDao.queryMeetingRoomById(meetingId));
		}
		return ServerResponse.createBySuccess("用户已经预定但是还没开始的记录", response);
	}

}
