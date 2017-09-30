package com.spittr.authorization.model;

import java.io.Serializable;

import org.hibernate.annotations.Check;

public class HMTUserInfoModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3410919400236420376L;

	private Long uid;
	
	private String username;
	
	private String avatar;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean check(){
		if (uid != null && username != null ) 
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "HMTUserInfoModel [uid=" + uid + ", username=" + username + ", avatar=" + avatar + "]";
	}
}
