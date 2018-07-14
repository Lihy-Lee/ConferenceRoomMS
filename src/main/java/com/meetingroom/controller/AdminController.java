package com.meetingroom.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meetingroom.common.Const;
import com.meetingroom.common.ServerResponse;
import com.meetingroom.entity.Admin;
import com.meetingroom.entity.Booking;
import com.meetingroom.entity.ExportBookingExcel;
import com.meetingroom.entity.MeetingRoom;
import com.meetingroom.entity.User;
import com.meetingroom.service.IAdminService;
import com.meetingroom.util.ExportExcel;
import com.meetingroom.util.MD5Util;

/**
 * Created by yzx on 2018/7/7.
 */
@CrossOrigin
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IAdminService adminService;

	@RequestMapping(value = "/export_excel.do", method = RequestMethod.GET)
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Booking> list = adminService.exportExcel().getData();
		ArrayList<ExportBookingExcel> excelList = new ArrayList<>();

		for (int index = 0; index < list.size(); index++) {
			ExportBookingExcel excel = new ExportBookingExcel();

			excel.setBookingId(list.get(index).getBookingId());
			excel.setUserName(list.get(index).getUser().getName());
			excel.setMeetingId(list.get(index).getMeetingId());
			excel.setCreateTime(list.get(index).getCreateTime());
			excel.setStartTime(list.get(index).getStartTime());
			excel.setEndTime(list.get(index).getEndTime());
			excel.setStatus(list.get(index).getStatus());

			excelList.add(excel);
		}
		ExportExcel<ExportBookingExcel> ee = new ExportExcel<ExportBookingExcel>();
		String[] headers = { "预约序号", "预约用户", "会议室号", "预约创建时间", "预约开始时间", "预约结束时间", "预约状态(0:已用;1:已取消)" };
		String fileName = "用户预约表";
		ee.exportExcel(headers, excelList, fileName, request, response);
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<Admin> login(@RequestBody LinkedHashMap<String, Object> data, HttpSession session) {
		Integer adminId = Integer.parseInt((String) data.get("adminId"));
		String password = (String) data.get("password");
		ServerResponse<Admin> response = adminService.login(adminId, password);
		return response;
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> logout(HttpSession session) {
		session.removeAttribute(Const.CURRENT_USER);
		return ServerResponse.createBySuccess();
	}

	@RequestMapping(value = "/add_user.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> addUser(@RequestBody LinkedHashMap<String, Object> data, HttpServletRequest request) {

		Integer userId = Integer.parseInt((String) data.get("userId"));
		String name = (String) data.get("name");
		String gender = (String) data.get("gender");
		String department = (String) data.get("department");
		String role = (String) data.get("role");
		User user = new User();
		user.setUserId(userId);
		user.setName(name);
		user.setGender(gender);
		user.setPassword(MD5Util.MD5EncodeUtf8("123456"));// MD5对密码加密
		user.setDepartment(department);
		user.setRole(role);
		user.setStatus(0);
		return adminService.insertUser(user);
	}

	@RequestMapping(value = "/update_user.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> updateUser(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {

		Integer userId = Integer.parseInt((String) data.get("userId"));
		String name = (String) data.get("name");
		String gender = (String) data.get("gender");
		String password = (String) data.get("password");
		String department = (String) data.get("department");
		String role = (String) data.get("role");
		Integer status = Integer.parseInt((String) data.get("status"));
		
		System.out.println(userId);
		System.out.println(name);
		System.out.println(gender);
		System.out.println(password);
		System.out.println(department);
		System.out.println(role);
		
		User user = new User();
		user.setUserId(userId);
		user.setName(name);
		user.setGender(gender);
		user.setPassword(MD5Util.MD5EncodeUtf8(password));// MD5对密码加密
		user.setDepartment(department);
		user.setRole(role);
		if (status != -1) {
			user.setStatus(status);
		}
		return adminService.updateUser(user);
	}

	@RequestMapping(value = "/all_meetingRoom.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<ArrayList<MeetingRoom>> allMeetingRoom(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {
		return adminService.allMeetingRoom();
	}

	@RequestMapping(value = "/add_meetingroom.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<MeetingRoom> insertMeetingRoom(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {
		String floor = (String) data.get("floor");
		String room = (String) data.get("room");
		Integer capacity = Integer.parseInt((String) data.get("capacity"));
		String dev = (String) data.get("dev");

		MeetingRoom meetingRoom = new MeetingRoom(floor, room, capacity, dev);
		return adminService.insertMeetingRoom(meetingRoom);
	}

	@RequestMapping(value = "/update_meetingroom.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<MeetingRoom> updateMeetingRoom(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {

		Integer meetingRoomId = Integer.parseInt((String) data.get("meetingRoomId"));

		Integer capacity = Integer.parseInt((String) data.get("capacity"));
		String dev = (String) data.get("dev");

		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setMeetingId(meetingRoomId);
		meetingRoom.setCapacity(capacity);
		meetingRoom.setDev(dev);
		return adminService.updateMeetingRoom(meetingRoom);
	}

	@RequestMapping(value = "/delete_meetingroom.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> deleteMeetingRoom(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {
		Integer meetingRoomId = Integer.parseInt((String) data.get("meetingRoomId"));
		return adminService.deleteMeetingRoom(meetingRoomId);
	}

	@RequestMapping(value = "/exportExcel.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<ArrayList<Booking>> exportExcel(HttpServletRequest request) {
		return adminService.exportExcel();

	}

	@RequestMapping(value = "/reset_user_psw.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> resetUserPsw(@RequestBody LinkedHashMap<String, Object> data,
			HttpServletRequest request) {
		Integer userId = Integer.parseInt((String) data.get("userId"));
		return adminService.resetUserPsw(userId);

	}

}
