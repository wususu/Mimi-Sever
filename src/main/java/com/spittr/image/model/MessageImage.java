package com.spittr.image.model;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spittr.message.model.Message;


@Entity
@Table(name="message_image")
@JsonIgnoreProperties({"message", "realPath", "isDelete", "tmDelete", "tmCreated"})
public class MessageImage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2598387040918479699L;

	@Id
	@GeneratedValue
	private Long imageid;
	
	@Column(nullable=false, unique=true)
	private String realPath;
	
	@Column(nullable=false, unique=true)
	private String webPath;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmCreated;
	
	private Boolean isDelete;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tmDelete;
	
	private Long mid;
	
	@ManyToOne
	@JoinColumn(name="message")
	private Message message;

	public MessageImage() {
		// TODO Auto-generated constructor stub
	}
	
	public MessageImage(String realPath, String webPath){
		this(realPath, webPath, null);
	}
	
	public MessageImage(String realPath, String webPath, Message message){
		this(realPath, webPath, message == null ? null : message.getMid(), message);
	}
	
	public MessageImage(String realPath, String webPath,Long mid, Message message) {
		this(realPath, webPath, new Date(), false, null, mid, message);
	}
	
	public MessageImage(String realPath, String webPath, Date tmCreated, Boolean isDelete, Date tmDelete, Long mid,
			Message message) {
		super();
		this.realPath = realPath;
		this.webPath = webPath;
		this.tmCreated = tmCreated;
		this.isDelete = isDelete;
		this.tmDelete = tmDelete;
		this.mid = mid;
		this.message = message;
	}

	public Long getImageid() {
		return imageid;
	}

	public void setImageid(Long imageid) {
		this.imageid = imageid;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getWebPath() {
		return webPath;
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
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
	
	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageImage [imageid=" + imageid + ", realPath=" + realPath + ", webPath=" + webPath + ", tmCreated="
				+ tmCreated + ", isDelete=" + isDelete + ", tmDelete=" + tmDelete + ", mid=" + mid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imageid == null) ? 0 : imageid.hashCode());
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
		result = prime * result + ((realPath == null) ? 0 : realPath.hashCode());
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
		MessageImage other = (MessageImage) obj;
		if (imageid == null) {
			if (other.imageid != null)
				return false;
		} else if (!imageid.equals(other.imageid))
			return false;
		if (mid == null) {
			if (other.mid != null)
				return false;
		} else if (!mid.equals(other.mid))
			return false;
		if (realPath == null) {
			if (other.realPath != null)
				return false;
		} else if (!realPath.equals(other.realPath))
			return false;
		return true;
	}
}
