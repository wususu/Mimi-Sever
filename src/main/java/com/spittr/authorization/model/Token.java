package com.spittr.authorization.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.spittr.user.model.User;


@Entity
@Table(name="token")
public class Token {

	@Id
	@GeneratedValue
	@Column(name="tid")
	private Long tid;
	
	@Column(name="secret", length=32)
	private String secret;
	
	@ManyToOne
	@JoinColumn(name="user", updatable=false)
	private User user;
	
	
	@Column(name="uid", updatable=false, nullable=false)
	private Long uid;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmCreate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmExpire;

	public Token() {
		// TODO Auto-generated constructor stub
	}
	
	public static Token newInstance(String secret,User user){
		return new Token(secret, user);
	}
	
	public Token(String secret, User user){
		this(secret, user, null);
	}
	
	public Token(String secret, User user, Date tmExpire){
		this(secret, user, new Date(), tmExpire, user.getId());
	}
	
	public Token(String secret, User user, Date tmCreate, Date tmExpire, Long uid){
		this.secret = secret;
		this.user = user;
		this.uid = uid;
		this.tmCreate = tmCreate;
		this.tmExpire = tmExpire;
	}
	
	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getTmCreate() {
		return tmCreate;
	}

	public void setTmCreate(Date tmCreate) {
		this.tmCreate = tmCreate;
	}

	public Date getTmExpire() {
		return tmExpire;
	}

	public void setTmExpire(Date tmExpire) {
		this.tmExpire = tmExpire;
	}

	@Override
	public String toString() {
		return "Token [tid=" + tid + ", secret=" + secret + ", user=" + user + ", tmCreate=" + tmCreate + ", tmExpire="
				+ tmExpire + "]";
	}
	
	
}
