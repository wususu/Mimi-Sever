package com.spittr.message.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.spittr.user.model.User;

@Entity(name="likees")
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"message", "user"}))
public class Like implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3159819550714948458L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private long mid;
	
	@Column(nullable=false)
	private long uid;
	
	@Column(nullable=false)
	private boolean isLike;
	
	@ManyToOne
	@JoinColumn(name="message", nullable=false, updatable=false)
	private Message message;
	
	@ManyToOne
	@JoinColumn(name="user", nullable=false, updatable=false)
	private User user;

	public Like() {
		// TODO Auto-generated constructor stub
	}
	
	public Like(Message message, User user){
		this(message.getMid(), user.getUid(), false, message, user);
	}
	
	public Like(long mid, long uid, boolean isLike, Message message, User user) {
		super();
		this.mid = mid;
		this.uid = uid;
		this.isLike = isLike;
		this.message = message;
		this.user = user;
	}

	public long getMid() {
		return mid;
	}

	public void setMid(long mid) {
		this.mid = mid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + ", mid=" + mid + ", uid=" + uid + ", isLike=" + isLike + ", user=" + user + "]";
	}
	
}
