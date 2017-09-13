package com.spittr.user.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 用户关系表：　mainUser 关注objectUser
 * @author janke
 *
 */
@Entity
@JsonIgnoreProperties({"isDelete"})
@Table(name="user_relationship", uniqueConstraints=@UniqueConstraint(columnNames={"mainUser", "objectUSer"}))
public class UserRelationship {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="mainUser", nullable=false)
	private User mainUser;
	
	@ManyToOne
	@JoinColumn(name="objectUser",nullable=false)
	private User objectUser;
	
	@Column(nullable=false)
	private boolean isDelete;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date tmCreated;

	public UserRelationship() {
		// TODO Auto-generated constructor stub
	}
	
	public UserRelationship(User mainUser, User objectUser){
		this(mainUser, objectUser, false, new Date());
	}	
	
	public UserRelationship(User mainUser, User objectUser, boolean isDelete, Date tmCreated) {
		super();
		this.mainUser = mainUser;
		this.objectUser = objectUser;
		this.isDelete = isDelete;
		this.tmCreated = tmCreated;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getMainUser() {
		return mainUser;
	}

	public void setMainUser(User mainUser) {
		this.mainUser = mainUser;
	}

	public User getObjectUser() {
		return objectUser;
	}

	public void setObjectUser(User objectUser) {
		this.objectUser = objectUser;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Date getTmCreated() {
		return tmCreated;
	}

	public void setTmCreated(Date tmCreated) {
		this.tmCreated = tmCreated;
	}

	@Override
	public String toString() {
		return "UserRelationship [id=" + id + ", mainUser=" + mainUser + ", objectUser=" + objectUser + ", isDelete="
				+ isDelete + ", tmCreated=" + tmCreated + "]";
	}
}
