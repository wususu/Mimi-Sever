package com.spittr.location.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"tmCreated", "isDelete", "tmDelete"})
public class Location implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7295262773090140574L;

	@Id
	@GeneratedValue
	private Long lid;
	
	@Column(length=10, unique=true)
	private String locale;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmCreated;
	
	private Boolean isDelete;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmDelete;

	public Location(){
		
	}
	
	public Location(String locale){
		this(locale, new Date(), false, null);
	}
	
	public Location(String locale, Date tmCreated, Boolean isDelete, Date tmDelete) {
		// TODO Auto-generated constructor stub
		this.locale = locale;
		this.tmCreated = tmCreated;
		this.tmDelete = tmDelete;
		this.isDelete = isDelete;
	}
	
	public Long getLid() {
		return lid;
	}

	public void setLid(Long lid) {
		this.lid = lid;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Date getTmCreated() {
		return tmCreated;
	}

	public void setTmCreated(Date tmCreated) {
		this.tmCreated = tmCreated;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Date getTmDelete() {
		return tmDelete;
	}

	public void setTmDelete(Date tmDelete) {
		this.tmDelete = tmDelete;
	}

	@Override
	public String toString() {
		return "Location [lid=" + lid + ", locale=" + locale + ", tmCreated=" + tmCreated+ ", isDelete=" + isDelete
				+ ", tmDelete=" + tmDelete + "]";
	}
	
	
}
