package com.meetingroom.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.meetingroom.BaseTest;
import com.meetingroom.entity.Admin;

public class AdminDaoTest extends BaseTest {
	@Autowired
	private AdminDao adminDao;

	public void insertAdminDaoTest() {
		Admin admin = new Admin();
		admin.setAdminId(123);
		admin.setPassword("1123");
		adminDao.insertAdmin(admin);
	}

	public void deleteAdminTest() {
		adminDao.deleteAdmin(111);
	}

	public void queryAdminTest() {
		System.out.println(adminDao.queryAdmin(123));
	}

	public void updateAdminTest() {
		Admin admin = new Admin();
		admin.setAdminId(123);
		admin.setPassword("123");
		adminDao.updateAdmin(admin);
		
	}

}
