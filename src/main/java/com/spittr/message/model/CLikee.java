package com.spittr.message.model;

import javax.persistence.*;

import com.spittr.user.model.User;

@Entity
@Table(name="clikees")
public class CLikee {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private long cid;
	
	@Column(nullable=false)
	private long uid;
	
	@Column(nullable=false)
	private boolean isLike;
	
	@ManyToOne
	@JoinColumn(nullable=false, updatable=false)
	private User user;
 	
	@ManyToOne
	@JoinColumn(nullable=false, updatable=false)
	private Comment comment;

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	
}
