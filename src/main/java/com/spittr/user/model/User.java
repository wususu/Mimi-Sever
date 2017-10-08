package com.spittr.user.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spittr.image.model.UserImage;


@Entity
@Table(name="user")
@JsonIgnoreProperties({"plogin", "id"})
public class User implements Serializable{

	private static final long serialVersionUID = 7302289073823113966L;
	
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
		this(uname, nname, plogin, tmCreated, null, null, null);
	}
	
	public User(String uname, String nname, Boolean plogin, Date tmCreated, String site, String signature,
			Boolean gender) {
		this.uname = uname;
		this.nname = nname;
		this.plogin = plogin;
		this.tmCreated = tmCreated;
		this.site = site;
		this.signature = signature;
		this.gender = gender;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="uid")
	private Long uid;
	
	@Column(name="uname", unique=true, nullable=false)
	private String uname;
	
	@Column(name="nname", unique=true)
	private String nname;
	
	@Column(name="login_permission", nullable=false)
	private Boolean plogin;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time", nullable=true)
	private Date tmCreated;
	
	@OneToOne
	@JoinColumn(name="userPic", nullable=true)
	private UserImage userPic;
	
	//所属学院
	@Column(nullable=true)
	private String faculty;
	
	// 入学年份: 2015/2016/2017
	@Column(nullable=true, length=4)
	private Integer grade;
	
	// 宿舍区
	@Column(nullable=true)
	private String site;
	
	// 个性签名
	@Column(nullable=true, length=50)
	private String signature;
	
	// 性别
	@Column(nullable=true)
	private Boolean gender;
	
	public Long getUid(){
		return uid;
	}
	
	public void setUid(Long uid) {
		this.uid = uid;
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
	
	public UserImage getUserPic() {
		return userPic;
	}

	public void setUserPic(UserImage userPic) {
		this.userPic = userPic;
	}
	

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	
	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "User [id=" + uid + ", uname=" + uname + ", nname=" + nname + ", plogin=" + plogin + ", tmCreated="
				+ tmCreated + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
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
		User other = (User) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		return true;
	}


	
}
