package com.spittr.user.model;

import java.io.Serializable;
import java.util.Arrays;
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


@Entity
@Table(name="passwd")
public class Passwd implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5572763900263655106L;

	@Id
	@GeneratedValue
	@Column(name="pid")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="uid", unique=true, nullable=false)
	private User user;
	
	@Column(name="login", nullable=false, updatable=false)
	private String login;
	
	@Column(name="upass", nullable=false)
	private byte[] upass;
	
	@Column(name="salt", nullable=false)
	private byte[] salt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_change_time", nullable=false)
	private Date tmLastChanged;

	public static Passwd newInstance(String loginName){
		return new Passwd(loginName);
	}
	
	public Passwd() {
		// TODO Auto-generated constructor stub
	}
	
	public Passwd(String login){
		this(login, null, null);
	}
	
	public Passwd(String login, byte[] upass , byte[] salt) {
		// TODO Auto-generated constructor stub
		this(login, upass, salt, null);	
	}
	
	public Passwd(String login, byte[] upass , byte[] salt, User user) {
		// TODO Auto-generated constructor stub
		this(login, upass, salt, new Date(), user);
	}
	
	public Passwd(String login, byte[] upass, byte[] salt, Date tmLastChange, User user){
		this.login = login;
		this.upass = upass;
		this.salt = salt;
		this.tmLastChanged = tmLastChange;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public byte[] getUpass() {
		return upass;
	}

	public void setUpass(byte[] upass) {
		this.upass = upass;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public Date getTmLastChanged() {
		return tmLastChanged;
	}

	public void setTmLastChanged(Date tmLastChanged) {
		this.tmLastChanged = tmLastChanged;
	}

	public String setLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "Passwd [id=" + id + ", user=" + user + ", loginName=" + login + ", upass=" + Arrays.toString(upass)
				+ ", salt=" + Arrays.toString(salt) + ", tmLastChanged=" + tmLastChanged + "]";
	}
	
}
