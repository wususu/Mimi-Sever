package com.spittr.message.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spittr.image.model.MessageImage;
import com.spittr.location.model.Location;
import com.spittr.message.core.MessageService;
import com.spittr.user.model.User;

@Entity
@Table(name="message")
@JsonIgnoreProperties({"tmDelete", "isDelete"})
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -589400348701346192L;

	@Id
	@GeneratedValue
	private Long mid;
	
	private Long uid;
	
	private Long lid;
	
	@ManyToOne
	@JoinColumn(name="user", updatable=false)
	private User user;
	
	@Column(length=500, nullable=false, updatable=false)
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmCreated;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmDelete;
	
	@OneToMany(mappedBy="message", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Set<MessageImage> messageImageSet;
	
	private Boolean isDelete;
	
	private Boolean isFake;
	
	private String fakeName;
	
	private Long likeCount;
	
	private Long commentCount;
	
	private Long commentNextVal;
	
	@ManyToOne
	@JoinColumn(name="location")
	private Location location;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(String content){
		this(content, null);
	}
	
	public Message(String content, User user){
		this(content, user, null);
	}
	
	public Message(String content, User user, Location localtion){
		this(content, user, user.getUid(),localtion!=null?localtion.getLid() : null,  localtion, (long)0, (long)0, (long)1, false, null, new Date(/* now */), false, null);
	}
	
	public Message(String content, User user, Long uid,Long lid, Location location,
			Long likeCount, Long commentCount, Long commentNextVal,
			Boolean isFake, String fakeName, 
			Date tmCreated,Boolean isDelete, Date tmDelete){
		this.content = content;
		this.user = user;
		this.uid = uid;
		this.lid = lid;
		this.location = location;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
		this.commentNextVal = commentNextVal;
		this.isFake = isFake;
		this.fakeName = fakeName;
		this.tmCreated = tmCreated;
		this.isDelete = isDelete;
		this.tmDelete = tmDelete;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTmCreated() {
		return tmCreated;
	}

	public void setTmCreated(Date tmCreated) {
		this.tmCreated = tmCreated;
	}

	public Date getTmDelete() {
		return tmDelete;
	}

	public void setTmDelete(Date tmDelete) {
		this.tmDelete = tmDelete;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsFake() {
		return isFake;
	}

	public void setIsFake(Boolean isFake) {
		this.isFake = isFake;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getFakeName() {
		return fakeName;
	}

	public void setFakeName(String fakeName) {
		this.fakeName = fakeName;
	}

	public Long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}
	
	public Set<MessageImage> getMessageImageSet() {
		return messageImageSet;
	}

	public void setMessageImageSet(Set<MessageImage> messageImageSet) {
		this.messageImageSet = messageImageSet;
	}

	public Long getCommentNextVal() {
		return commentNextVal;
	}

	public void setCommentNextVal(Long commentNextVal) {
		this.commentNextVal = commentNextVal;
	}

	@Override
	public String toString() {
		return "Message [mid=" + mid + ", uid=" + uid + ", user=" + user + ", content=" + content + ", tmCreated="
				+ tmCreated + ", tmDelete=" + tmDelete + ", isDelete=" + isDelete + ", isFake=" + isFake + ", fakeName="
				+ fakeName + ", likeCount=" + likeCount + ", commentCount=" + commentCount + ", location=" + location
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((fakeName == null) ? 0 : fakeName.hashCode());
		result = prime * result + ((isDelete == null) ? 0 : isDelete.hashCode());
		result = prime * result + ((isFake == null) ? 0 : isFake.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((messageImageSet == null) ? 0 : messageImageSet.hashCode());
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
		result = prime * result + ((tmCreated == null) ? 0 : tmCreated.hashCode());
		result = prime * result + ((tmDelete == null) ? 0 : tmDelete.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Message other = (Message) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (fakeName == null) {
			if (other.fakeName != null)
				return false;
		} else if (!fakeName.equals(other.fakeName))
			return false;
		if (isDelete == null) {
			if (other.isDelete != null)
				return false;
		} else if (!isDelete.equals(other.isDelete))
			return false;
		if (isFake == null) {
			if (other.isFake != null)
				return false;
		} else if (!isFake.equals(other.isFake))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (messageImageSet == null) {
			if (other.messageImageSet != null)
				return false;
		} else if (!messageImageSet.equals(other.messageImageSet))
			return false;
		if (mid == null) {
			if (other.mid != null)
				return false;
		} else if (!mid.equals(other.mid))
			return false;
		if (tmCreated == null) {
			if (other.tmCreated != null)
				return false;
		} else if (!tmCreated.equals(other.tmCreated))
			return false;
		if (tmDelete == null) {
			if (other.tmDelete != null)
				return false;
		} else if (!tmDelete.equals(other.tmDelete))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public Long getLid() {
		return lid;
	}

	public void setLid(Long lid) {
		this.lid = lid;
	}
}
