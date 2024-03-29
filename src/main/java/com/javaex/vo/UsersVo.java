package com.javaex.vo;

public class UsersVo {
	
	private int userNo;
	private String id;
	private String userName;
	private String password;
	private String joinDate;
	
	public UsersVo() {}
	
	public UsersVo(int numberNo, String id, String userName, String password, String joinDate) {
		super();
		this.userNo = numberNo;
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.joinDate = joinDate;
	}
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int numberNo) {
		this.userNo = numberNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "UsersVo [userNo=" + userNo + ", id=" + id + ", userName=" + userName + ", password=" + password
				+ ", joinDate=" + joinDate + "]";
	}
	
}
