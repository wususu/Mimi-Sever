package com.spittr.authorization.model;

public class HMTUserInfoModel {
	
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

	@Override
	public String toString() {
		return "HMTUserInfoModel [uid=" + uid + ", username=" + username + ", avatar=" + avatar + "]";
	}
	
	
	
}
