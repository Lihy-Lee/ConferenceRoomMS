package com.meetingroom.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.meetingroom.BaseTest;
import com.meetingroom.entity.User;

public class UserDaoTest extends BaseTest {
	@Autowired
	private UserDao userDao;

	public void insertUserTest() {
		User user = new User();
		user.setUserId(666);
		user.setName("张三");
		user.setPassword("123456");
		user.setGender("female");
		user.setRole("经理");
		user.setDepartment("技术部");
		user.setStatus(1);
		userDao.insertUser(user);
	}

	public void updateUserTest() {
		User user = new User();
		user.setUserId(111);
		user.setStatus(0);
		user.setEmail("1582012932@qq.com");
		user.setPassword("111111");
		userDao.updatePersonInfo(user);
	}

	@Test
	public void queryUserforLoginTest() {
		User user = userDao.queryUserforLogin(111);
		System.out.println(user);
	}

	public void queryUserByUserIdTest() {
		User user = userDao.queryUserByUserId(111);
		System.out.println(user);
	}

}
