package com.spittr.message.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spittr.user.model.User;


@Entity
@Table(name="comment")
@JsonIgnoreProperties({"replayWhichComment", "underWhichMessage", "uid", "muid", "isDelete", "tmDelete"})
public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7342640071383498510L;

	@Id
	@GeneratedValue
	private long cid;
	
	private long uid;
	
	@Column(nullable=true)
	private Long rcuid;
	
	private long muid;
	
	private long mid;
	
	@Column(nullable=true)
	private Long rcid;
	
	@Column(nullable=true)
	private String rcUname;
	
	@Column(updatable=false, nullable=false)
	private long commentVal;
	
	@ManyToOne
	@JoinColumn(name="user", updatable=false, nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="message", updatable=false, nullable=false)
	private Message underWhichMessage;
	
	@ManyToOne
	@JoinColumn(name="replay_comment")
	private Comment replayWhichComment;
	
	@Column(length=200, nullable=false)
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date tmCreated;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmDelete;
	
	private boolean isDelete;
	
	private long likeCount;
	
	@Column(nullable=false)
	private boolean isFake;
	
	@Column(nullable=true)
	private String fakeName;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	public Comment(String content, User user, Message underMessage){

		this(content, user, underMessage, null);

	}
	
	public Comment(String content, User user, Message underMessage, Comment replayComment){
		
		this(user.getUid(), replayComment == null ?null:replayComment.getUid(), underMessage.getUid(), underMessage.getMid(),
				replayComment == null ? null:replayComment.getCid(), underMessage.getCommentNextVal(), user, underMessage, replayComment, content,
				new Date(), null, false, false, null, (long)0, replayComment == null?null:replayComment.getUser().getUname());

	}
	
	public Comment(long uid, Long rcuid, long muid, long mid, Long rcid, long commentVal, User user, Message underWhichMessage,
			Comment replayWhichComment, String content, Date tmCreated, Date tmDelete, boolean isDelete, boolean isFake,
			String fakeName, Long likeCount, String rcUname) {
		this.uid = uid;
		this.rcuid = rcuid;
		this.muid = muid;
		this.mid = mid;
		this.rcid = rcid;
		this.commentVal = commentVal;
		this.user = user;
		this.underWhichMessage = underWhichMessage;
		this.replayWhichComment = replayWhichComment;
		this.content = content;
		this.tmCreated = tmCreated;
		this.tmDelete = tmDelete;
		this.isDelete = isDelete;
		this.isFake = isFake;
		this.fakeName = fakeName;
		this.likeCount = likeCount;
		this.rcUname =rcUname;
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

	public Long getRcuid() {
		return rcuid;
	}

	public void setRcuid(Long rcuid) {
		this.rcuid = rcuid;
	}

	public long getMuid() {
		return muid;
	}

	public void setMuid(long muid) {
		this.muid = muid;
	}

	public long getMid() {
		return mid;
	}

	public void setMid(long mid) {
		this.mid = mid;
	}

	public Long getRcid() {
		return rcid;
	}

	public void setRcid(Long rcid) {
		this.rcid = rcid;
	}

	public String getRcUname() {
		return rcUname;
	}

	public void setRcUname(String rcUname) {
		this.rcUname = rcUname;
	}

	public long getCommentVal() {
		return commentVal;
	}

	public void setCommentVal(long commentVal) {
		this.commentVal = commentVal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Message getUnderWhichMessage() {
		return underWhichMessage;
	}

	public void setUnderWhichMessage(Message underWhichMessage) {
		this.underWhichMessage = underWhichMessage;
	}

	public Comment getReplayWhichComment() {
		return replayWhichComment;
	}

	public void setReplayWhichComment(Comment replayWhichComment) {
		this.replayWhichComment = replayWhichComment;
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

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cid ^ (cid >>> 32));
		result = prime * result + (int) (commentVal ^ (commentVal >>> 32));
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((fakeName == null) ? 0 : fakeName.hashCode());
		result = prime * result + (isDelete ? 1231 : 1237);
		result = prime * result + (isFake ? 1231 : 1237);
		result = prime * result + (int) (likeCount ^ (likeCount >>> 32));
		result = prime * result + (int) (mid ^ (mid >>> 32));
		result = prime * result + (int) (muid ^ (muid >>> 32));
		result = prime * result + (int) (rcid ^ (rcid >>> 32));
		result = prime * result + (int) (rcuid ^ (rcuid >>> 32));
		result = prime * result + ((replayWhichComment == null) ? 0 : replayWhichComment.hashCode());
		result = prime * result + ((tmCreated == null) ? 0 : tmCreated.hashCode());
		result = prime * result + ((tmDelete == null) ? 0 : tmDelete.hashCode());
		result = prime * result + (int) (uid ^ (uid >>> 32));
		result = prime * result + ((underWhichMessage == null) ? 0 : underWhichMessage.hashCode());
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
		Comment other = (Comment) obj;
		if (cid != other.cid)
			return false;
		if (commentVal != other.commentVal)
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
		if (likeCount != other.likeCount)
			return false;
		if (mid != other.mid)
			return false;
		if (muid != other.muid)
			return false;
		if (rcid != other.rcid)
			return false;
		if (rcuid != other.rcuid)
			return false;
		if (replayWhichComment == null) {
			if (other.replayWhichComment != null)
				return false;
		} else if (!replayWhichComment.equals(other.replayWhichComment))
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
		if (underWhichMessage == null) {
			if (other.underWhichMessage != null)
				return false;
		} else if (!underWhichMessage.equals(other.underWhichMessage))
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
		return "Comment [cid=" + cid + ", uid=" + uid + ", rcuid=" + rcuid + ", muid=" + muid + ", mid=" + mid
				+ ", rcid=" + rcid + ", commentVal=" + commentVal + ", user=" + user + ", underWhichMessage="
				+ underWhichMessage + ", replayWhichComment=" + replayWhichComment + ", content=" + content
				+ ", tmCreated=" + tmCreated + ", tmDelete=" + tmDelete + ", isDelete=" + isDelete + ", likeCount="
				+ likeCount + ", isFake=" + isFake + ", fakeName=" + fakeName + "]";
	}
	
}
