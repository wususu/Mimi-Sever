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
	private long mid;
	
	private long uid;
	
	private long lid;
	
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
	
	private boolean isDelete;
	
	private boolean isFake;
	
	private String fakeName;
	
	private long likeCount;
	
	@Transient
	private Like like;
	
	private long commentCount;
	
	private long commentNextVal;
	
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
	
	public Message(String content, User user, long uid,long lid, Location location,
			long likeCount, long commentCount, long commentNextVal,
			boolean isFake, String fakeName, 
			Date tmCreated,boolean isDelete, Date tmDelete){
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

	public long getMid() {
		return mid;
	}

	public void setMid(long mid) {
		this.mid = mid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getLid() {
		return lid;
	}

	public void setLid(long lid) {
		this.lid = lid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Set<MessageImage> getMessageImageSet() {
		return messageImageSet;
	}

	public void setMessageImageSet(Set<MessageImage> messageImageSet) {
		this.messageImageSet = messageImageSet;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public boolean isFake() {
		return isFake;
	}

	public void setFake(boolean isFake) {
		this.isFake = isFake;
	}

	public String getFakeName() {
		return fakeName;
	}

	public void setFakeName(String fakeName) {
		this.fakeName = fakeName;
	}

	public long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}

	public Like getLike() {
		return like;
	}

	public void setLike(Like like) {
		this.like = like;
	}

	public long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	public long getCommentNextVal() {
		return commentNextVal;
	}

	public void setCommentNextVal(long commentNextVal) {
		this.commentNextVal = commentNextVal;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (commentCount ^ (commentCount >>> 32));
		result = prime * result + (int) (commentNextVal ^ (commentNextVal >>> 32));
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((fakeName == null) ? 0 : fakeName.hashCode());
		result = prime * result + (isDelete ? 1231 : 1237);
		result = prime * result + (isFake ? 1231 : 1237);
		result = prime * result + (int) (lid ^ (lid >>> 32));
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((messageImageSet == null) ? 0 : messageImageSet.hashCode());
		result = prime * result + (int) (mid ^ (mid >>> 32));
		result = prime * result + ((tmCreated == null) ? 0 : tmCreated.hashCode());
		result = prime * result + ((tmDelete == null) ? 0 : tmDelete.hashCode());
		result = prime * result + (int) (uid ^ (uid >>> 32));
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
		if (commentCount != other.commentCount)
			return false;
		if (commentNextVal != other.commentNextVal)
			return false;
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
		if (isDelete != other.isDelete)
			return false;
		if (isFake != other.isFake)
			return false;
		if (lid != other.lid)
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
		if (mid != other.mid)
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
		if (uid != other.uid)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [mid=" + mid + ", uid=" + uid + ", lid=" + lid + ", user=" + user + ", content=" + content
				+ ", tmCreated=" + tmCreated + ", tmDelete=" + tmDelete + ", messageImageSet=" + messageImageSet
				+ ", isDelete=" + isDelete + ", isFake=" + isFake + ", fakeName=" + fakeName + ", likeCount="
				+ likeCount +  ", commentCount=" + commentCount + ", commentNextVal=" + commentNextVal
				+ ", location=" + location + "]";
	}
}
