package com.meetingroom.dao;

import org.apache.ibatis.annotations.Param;

import com.meetingroom.entity.Admin;

public interface AdminDao {
	/**
	 * 添加管理员账号
	 * 
	 * @param admin
	 * @return
	 */
	public int insertAdmin(Admin admin);

	/**
	 * 删除管理员账号
	 * 
	 * @param aid
	 * @return
	 */
	public int deleteAdmin(Integer adminId);

	/**
	 * 查询管理员信息
	 * 
	 * @param aid
	 * @return
	 */
	public Admin queryAdmin(Integer adminId);

	/**
	 * 更新管理员信息，修改密码
	 * 
	 * @param admin
	 * @return
	 */
	public int updateAdmin(Admin admin);

	public int checkAdminId(Integer adminId);

	Admin selectLogin(@Param("adminId") Integer adminId, @Param("password") String password);

}
