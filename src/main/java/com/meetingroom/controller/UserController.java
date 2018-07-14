package com.meetingroom.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meetingroom.common.Const;
import com.meetingroom.common.ResponseCode;
import com.meetingroom.common.ServerResponse;
import com.meetingroom.entity.Booking;
import com.meetingroom.entity.MeetingRoom;
import com.meetingroom.entity.User;
import com.meetingroom.service.IUserService;
import com.meetingroom.util.EmailUtil;

import net.sf.json.JSONArray;

/**
 * Created by yzx on 2018/7/7.
 */

@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService iUserService;

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> login(@RequestBody LinkedHashMap<String, Object> data, HttpServletRequest request) {

		Integer userId = Integer.parseInt((String) data.get("userId"));
		String password = (String) data.get("password");
		System.out.println(userId);
		System.out.println(password);
		ServerResponse<User> response = iUserService.login(userId, password);
		return response;

	}

	/**
	 * 用户退出登录
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> logout(HttpSession session) {
		session.removeAttribute(Const.CURRENT_USER);
		return ServerResponse.createBySuccess();
	}

	@RequestMapping(value = "/update_password.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> updatePassord(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {
		Integer userId = Integer.parseInt((String) data.get("userId"));
		String oldPassword = (String) data.get("oldPassword");// 原密码
		String password = (String) data.get("password");

		// 验证原密码是否正确
		boolean pswMatch = iUserService.checkUserPassword(userId, oldPassword);
		if (!pswMatch) {// 密码不正确
			return ServerResponse.createByErrorMessage("原密码错误");
		}
		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		ServerResponse<User> response = iUserService.updateInformation(user);
		return response;
	}

	/**
	 * 用户更新个人信息
	 * 
	 * @param session
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update_email.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> update_email(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {
		Integer userId = Integer.parseInt((String) data.get("userId"));
		String email = (String) data.get("email");
		String code = (String) data.get("code");

		boolean result = EmailUtil.confirmVerifyCode(code);
		if (!result) {// 验证码错误
			return ServerResponse.createByErrorMessage("验证码错误");
		}
		// 参数读取错误
		if (email == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
					ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		}

		User user = new User();
		user.setUserId(userId);
		user.setEmail(email);

		ServerResponse<User> response = iUserService.updateInformation(user);
		if (response.isSuccess()) {
			User newUser = iUserService.queryUserByUserId(userId).getData();
			response.getData().setName(newUser.getName());
			response.getData().setEmail(newUser.getEmail());
		}
		return response;
	}

	/**
	 * 验证用户邮箱，发送验证码
	 * 
	 * @param email
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/verify_email.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> verifyEmail(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {
		String email = (String) data.get("email");
		ServerResponse<String> response = iUserService.verifyEmail(email);
		return response;
	}



	/**
	 * 查询空闲会议室
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/free_meetingroom.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<ArrayList<MeetingRoom>> listForFree(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {
		// 读取参数
		String startTime = (String) data.get("startTime");
		String endTime = (String) data.get("endTime");

		System.out.println(startTime);
		System.out.println(endTime);
		return iUserService.listForFree(startTime, endTime);
	}

	@RequestMapping(value = "/book_meetingroom.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<Booking> bookMeetingRoom(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 读取json中的userList队列
		Booking booking = new Booking();
		booking.setUser(new User(Integer.parseInt((String) data.get("userId"))));
		booking.setMeetingRoom(new MeetingRoom(Integer.parseInt((String) data.get("meetingId"))));

		// booking.setStartTime(formatter.parse((String) data.get("startTime")));
		// booking.setEndTime(formatter.parse((String) data.get("endTime")));
		booking.setStartTime((String) data.get("startTime"));
		booking.setEndTime((String) data.get("endTime"));
		
		System.out.println(booking.getStartTime());
		System.out.println(booking.getEndTime());

		booking.setCreateTime(formatter.format(new Date()));
		booking.setStatus(0);
		return iUserService.bookingMeetingRoom(booking);
	}

	@RequestMapping(value = "/all_user.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<ArrayList<User>> allUser(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {

		return iUserService.allUser();
	}

	@RequestMapping(value = "/add_attend.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> addAttend(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {
		Integer bookingId = Integer.parseInt((String) data.get("bookingId"));
		System.out.println(bookingId);
		JSONArray array = JSONArray.fromObject(data.get("userList"));// 读取json中的userList队列
	
		System.out.println(array);
		
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			Integer userId = Integer.parseInt(array.getJSONObject(i).getString("userId"));
			System.out.println(userId);
			arrayList.add(userId);
		}
		return iUserService.addAttend(bookingId, arrayList);
	}

	/**
	 * 用户取消预约会议室
	 * 
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cancel_booking.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> cancelBooking(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {
		Integer bookingId = Integer.parseInt((String) data.get("bookingId"));
		return iUserService.cancelBooking(bookingId);
	}

	/**
	 * 用户查看个人订阅记录
	 * 
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/show_history.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<ArrayList<Booking>> showHistory(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {
		Integer userId = Integer.parseInt((String) data.get("userId"));
		return iUserService.showHistory(userId);

	}

	/*
	 * 用户查看已经预约但是没完成的记录
	 * 
	 * @param data
	 * 
	 * @param request
	 * 
	 * @return
	 */
	@RequestMapping(value = "/had_booking.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<ArrayList<Booking>> hadBooking(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {
		Integer userId = Integer.parseInt((String) data.get("userId"));
		return iUserService.hadBooking(userId);

	}

}
