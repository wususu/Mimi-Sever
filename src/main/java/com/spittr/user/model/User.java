package com.spittr.user.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="user")
@JsonIgnoreProperties({"plogin", "id"})
public class User implements Serializable{

	private static final long serialVersionUID = 7302289073823113966L;

	public static User newInstance(String uname){
		return newInstance(uname, null);
	}
	
	public static User newInstance(String uname, String nname){
		return  new User(uname, nname);
	}
	
	public User() {
	}
	
	public User(String uname){
		this(uname, null);
	}
	
	public User (String uname, String nname) {
		this(uname, nname, new Date(), true);
	}
	
	public User(String uname, String nname, Date tmCreated, Boolean plogin) {
		// TODO Auto-generated constructor stub
		this.uname = uname;
		this.nname = nname;
		this.tmCreated = tmCreated;
		this.plogin = plogin;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="uid")
	private Long id;
	
	@Column(name="uname", unique=true, nullable=false)
	private String uname;
	
	@Column(name="nname", unique=true)
	private String nname;
	
	@Column(name="login_permission", nullable=false)
	private Boolean plogin;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time", nullable=true)
	private Date tmCreated;
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public Date getTmCreated() {
		return tmCreated;
	}

	public void setTmCreated(Date tmCreated) {
		this.tmCreated = tmCreated;
	}

	public Boolean getPlogin() {
		return plogin;
	}

	public void setPlogin(Boolean plogin) {
		this.plogin = plogin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", nname=" + nname + ", plogin=" + plogin + ", tmCreated="
				+ tmCreated + "]";
	}
	
	
}
