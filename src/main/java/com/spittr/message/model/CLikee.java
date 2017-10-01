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
	
	public CLikee(Comment comment,User user) {
		// TODO Auto-generated constructor stub
		this(comment.getCid(), user.getUid(), false, user, comment);
	}
	
	public CLikee(long cid, long uid, boolean isLike, User user, Comment comment) {
		this.cid = cid;
		this.uid = uid;
		this.isLike = isLike;
		this.user = user;
		this.comment = comment;
	}

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

	@Override
	public String toString() {
		return "CLikee [id=" + id + ", cid=" + cid + ", uid=" + uid + ", isLike=" + isLike + ", user=" + user
				+ ", comment=" + comment + "]";
	}
}
