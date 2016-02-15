package com.training.entity;

public class User {
	private String userName;
	private String passWord;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}


	public String getUserName() {
		return userName;
	}
	public String getPassWord() {
		return passWord;
	}

	
}
