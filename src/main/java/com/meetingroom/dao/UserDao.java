package com.meetingroom.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.meetingroom.entity.User;

public interface UserDao {
	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	public int insertUser(User user);

	/**
	 * 根据用户查询用户信息密码,状态信息，用户登录
	 * 
	 * @param uid
	 * @return
	 */
	public User queryUserforLogin(Integer userId);

	/**
	 * 根据用户查询用户所有信息,用于用户个人信息查询
	 * 
	 * @param uid
	 * @return
	 */
	public User queryUserByUserId(Integer userId);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	public ArrayList<User> queryAllUser();

	/**
	 * 根据用户id查询用户名
	 * 
	 * @param userId
	 * @return
	 */
	public User queryUserNameByUserId(Integer userId);

	/**
	 * 用户更改个人信息
	 * 
	 * @param user
	 * @return
	 */
	public int updatePersonInfo(User user);

	int checkUserId(Integer userId);

	int checkEmail(String pwd);

	User selectLogin(@Param("userId") Integer userId, @Param("password") String password);

	int checkPassword(@Param("passwordOld") String passwordOld, @Param("userId") Integer userId);

	int checkEmailByUserId(@Param("email") String email, @Param("userId") Integer userId);

}
