package com.spittr.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.spittr.authorization.model.HMTUserInfoModel;


@Entity
@Table(name="hmt_user")
public class HMTUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2052470711532692863L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, unique=true)
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
	
	public HMTUser() {
		// TODO Auto-generated constructor stub
	}
	
	public HMTUser(HMTUserInfoModel hmtUserInfoModel, User user){
		this(hmtUserInfoModel.getUid(), hmtUserInfoModel.getUsername(), hmtUserInfoModel.getAvatar(), user);
	}

	public HMTUser(Long hmtUid, String hmtUname, String hmtAvatar, User user) {
		// TODO Auto-generated constructor stub
		this(hmtUid, hmtUname, hmtAvatar, user, user.getUid());
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

	@Override
	public String toString() {
		return "HMTUser [hmtUid=" + hmtUid + ", hmtUname=" + hmtUname + ", hmtAvatar=" + hmtAvatar + ", user=" + user
				+ ", uid=" + uid + "]";
	}
	
	
}
