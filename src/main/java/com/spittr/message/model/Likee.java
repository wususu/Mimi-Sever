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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spittr.user.model.User;

@Entity(name="likees")
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"message", "user"}))
@JsonIgnoreProperties({"message", "user"})
public class Likee implements Serializable{

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

	public Likee() {
		// TODO Auto-generated constructor stub
	}
	
	public Likee(Message message, User user){
		this(message.getMid(), user.getUid(), false, message, user);
	}
	
	public Likee(long mid, long uid, boolean isLike, Message message, User user) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (isLike ? 1231 : 1237);
		result = prime * result + (int) (mid ^ (mid >>> 32));
		result = prime * result + (int) (uid ^ (uid >>> 32));
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
		Likee other = (Likee) obj;
		if (id != other.id)
			return false;
		if (isLike != other.isLike)
			return false;
		if (mid != other.mid)
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Likee [id=" + id + ", isLike=" + isLike + "]";
	}
	
}
