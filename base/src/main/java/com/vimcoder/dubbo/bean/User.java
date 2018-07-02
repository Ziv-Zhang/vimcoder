package com.vimcoder.dubbo.bean;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String pwd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", pwd=" + pwd + "]";
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int id, String username, String pwd) {
		super();
		this.id = id;
		this.username = username;
		this.pwd = pwd;
	}

}
