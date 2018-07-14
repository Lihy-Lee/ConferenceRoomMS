package com.meetingroom.entity;

/**
 * 用户
 * 
 * @author LHY
 *
 */
public class User {
	private Integer userId;// 用户id
	private String name;
	private String gender;// 用户性别
	private String department;// 用户所属部门
	private String role;// 用户类型，经理/普通用户
	private String password;// 密码
	private String email;// 邮箱
	private Integer status;// 状态，0可用/1禁用状态

	public User() {
		super();
	}

	public User(Integer userId) {
		super();
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", gender=" + gender + ", department=" + department
				+ ", role=" + role + ", password=" + password + ", email=" + email + ", status=" + status + "]";
	}



}
