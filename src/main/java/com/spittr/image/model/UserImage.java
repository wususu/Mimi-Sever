package com.spittr.image.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spittr.user.model.User;

@Entity
@Table(name="user_image")
@JsonIgnoreProperties({"user", "realPath", "isDelete", "tmDelete", "tmCreated", "uid"})
public class UserImage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5056757253998074817L;

	@Id
	@GeneratedValue
	private long Imageid;
	
	private Long uid;
	
	@Column(nullable=false, unique=true)
	private String realPath;
	
	@Column(nullable=false, unique=true)
	private String webPath;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmCreated;
	
	@Column(nullable=false)
	private boolean isDelete;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tmDelete;

	@OneToOne
	@JoinColumn(name="user", unique=true, nullable=false)
	private User user;
	
	public UserImage() {
		// TODO Auto-generated constructor stub
	}
	
	public UserImage(String realPath, String webPath, User user){
		this(user.getUid(), realPath, webPath, new Date(), false, null, user);
	}
	
	public UserImage(Long uid, String realPath, String webPath, Date tmCreated, boolean isDelete, Date tmDelete,
			User user) {
		this.uid = uid;
		this.realPath = realPath;
		this.webPath = webPath;
		this.tmCreated = tmCreated;
		this.isDelete = isDelete;
		this.tmDelete = tmDelete;
		this.user = user;
	}

	public long getImageid() {
		return Imageid;
	}

	public void setImageid(long imageid) {
		Imageid = imageid;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getWebPath() {
		return webPath;
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	public Date getTmCreated() {
		return tmCreated;
	}

	public void setTmCreated(Date tmCreated) {
		this.tmCreated = tmCreated;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Date getTmDelete() {
		return tmDelete;
	}

	public void setTmDelete(Date tmDelete) {
		this.tmDelete = tmDelete;
	}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Imageid ^ (Imageid >>> 32));
		result = prime * result + (isDelete ? 1231 : 1237);
		result = prime * result + ((realPath == null) ? 0 : realPath.hashCode());
		result = prime * result + ((tmCreated == null) ? 0 : tmCreated.hashCode());
		result = prime * result + ((tmDelete == null) ? 0 : tmDelete.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((webPath == null) ? 0 : webPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserImage other = (UserImage) obj;
		if (Imageid != other.Imageid)
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (realPath == null) {
			if (other.realPath != null)
				return false;
		} else if (!realPath.equals(other.realPath))
			return false;
		if (tmCreated == null) {
			if (other.tmCreated != null)
				return false;
		} else if (!tmCreated.equals(other.tmCreated))
			return false;
		if (tmDelete == null) {
			if (other.tmDelete != null)
				return false;
		} else if (!tmDelete.equals(other.tmDelete))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (webPath == null) {
			if (other.webPath != null)
				return false;
		} else if (!webPath.equals(other.webPath))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserImage [Imageid=" + Imageid + ", realPath=" + realPath + ", webPath=" + webPath + ", tmCreated="
				+ tmCreated + ", isDelete=" + isDelete + ", tmDelete=" + tmDelete + "]";
	}
	
	
	
}
