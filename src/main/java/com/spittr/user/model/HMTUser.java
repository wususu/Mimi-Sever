package com.spittr.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="hmt_user")
public class HMTUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long hmtUid;
	
	@Column(nullable=false, unique=true)
	private String hmtUname;
	
	@Column(nullable=false, unique=true)
	private String hmtAvatar;
	
	@OneToOne
	@JoinColumn(nullable=true, unique=true)
	private User user;
	
	@Column(nullable=true)
	private Long uid;

	public HMTUser(Long hmtUid, String hmtUname, String hmtAvatar) {
		// TODO Auto-generated constructor stub
		this(hmtUid, hmtUname, hmtAvatar, null, null);
	}
	
	public HMTUser(Long hmtUid, String hmtUname, String hmtAvatar, User user, Long uid) {
		super();
		this.hmtUid = hmtUid;
		this.hmtUname = hmtUname;
		this.hmtAvatar = hmtAvatar;
		this.user = user;
		this.uid = uid;
	}

	public Long getHmtUid() {
		return hmtUid;
	}

	public void setHmtUid(Long hmtUid) {
		this.hmtUid = hmtUid;
	}

	public String getHmtUname() {
		return hmtUname;
	}

	public void setHmtUname(String hmtUname) {
		this.hmtUname = hmtUname;
	}

	public String getHmtAvatar() {
		return hmtAvatar;
	}

	public void setHmtAvatar(String hmtAvatar) {
		this.hmtAvatar = hmtAvatar;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
}
