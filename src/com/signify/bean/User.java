package com.signify.bean;

public class User {
	private String userId, username, password, name, address;
	private int roleid;

	public User() {
	}

	public User(String userId, String username) {
		super();
		this.userId = userId;
		this.username = username;
	}

	public User(String userid, String name, String password, String address, int roleid) {
		super();
		this.userId = userid;
		this.name = name;
		this.password = password;
		this.address = address;
		this.setRoleid(roleid);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return roleid;
	}

	public void setRoleId(int roleid) {
		this.roleid = roleid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

}
