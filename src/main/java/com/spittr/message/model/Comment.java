package com.spittr.message.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spittr.user.model.User;


@Entity
@Table(name="comment")
@JsonIgnoreProperties({"replayWhichComment", "underWhichMessage", "uid", "rcuid", "muid", "isDelete", "tmDelete"})
public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7342640071383498510L;

	@Id
	@GeneratedValue
	private Long cid;
	
	private Long uid;
	
	private Long rcuid;
	
	private Long muid;
	
	private Long mid;
	
	private Long rcid;
	
	@Column(updatable=false, nullable=false)
	private Long commentVal;
	
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
	
	private Boolean isDelete;
	
	private Long likeCount;
	
	@Column(nullable=false)
	private Boolean isFake;
	
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
				new Date(), null, false, false, null, (long)0);

	}
	
	public Comment(Long uid, Long rcuid, Long muid, Long mid, Long rcid, Long commentVal, User user, Message underWhichMessage,
			Comment replayWhichComment, String content, Date tmCreated, Date tmDelete, Boolean isDelete, Boolean isFake,
			String fakeName, Long likeCount) {
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
	}
	
	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public Long getRcuid() {
		return rcuid;
	}

	public void setRcuid(Long rcuid) {
		this.rcuid = rcuid;
	}

	public Long getMuid() {
		return muid;
	}

	public void setMuid(Long muid) {
		this.muid = muid;
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

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public Long getRcid() {
		return rcid;
	}

	public void setRcid(Long rcid) {
		this.rcid = rcid;
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

	public Long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	public Boolean getIsFake() {
		return isFake;
	}

	public void setIsFake(Boolean isFake) {
		this.isFake = isFake;
	}

	public String getFakeName() {
		return fakeName;
	}

	public void setFakeName(String fakeName) {
		this.fakeName = fakeName;
	}

	public Long getCommentVal() {
		return commentVal;
	}

	public void setCommentVal(Long commentVal) {
		this.commentVal = commentVal;
	}

	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", uid=" + uid + ", rcuid=" + rcuid + ", muid=" + muid + ", mid=" + mid
				+ ", rcid=" + rcid + ", user=" + user + ", underWhichMessage=" + underWhichMessage
				+ ", replayWhichComment=" + replayWhichComment + ", content=" + content + ", tmCreate=" + tmCreated
				+ ", tmDelete=" + tmDelete + ", isDelete=" + isDelete + ", likeCount=" + likeCount + ", isFake="
				+ isFake + ", fakeName=" + fakeName + "]";
	}
	
	
}
