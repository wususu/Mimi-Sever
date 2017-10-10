package com.spittr.websocket.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spittr.message.model.Comment;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="ntc_rcmmnt", uniqueConstraints=@UniqueConstraint(columnNames={"comment", "rComment"}))
@JsonIgnoreProperties(value={"cUser", "mUser", "rcUser", "tmRecived", "isRecived", "comment", "message", "rComment"})
public class NtcRCmmnt implements Serializable, NtcBody{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private Long cid;
	
	@Column(nullable=false)
	private Long rcid;
	
	@Column(nullable=false)
	private Long mid;
	
	@Column(nullable=false)
	private Long cUid;
	
	@Column(nullable=false)
	private Long rcUid;
	
	@Column(nullable=false)
	private Long mUid;
	
	@Column(nullable=false)
	private String cUname;
	
	@Column(nullable=false)
	private String mUname;
	
	@Column(nullable=false)
	private String rcUname;
	
	@Column(nullable=false)
	private String cNname;
	
	@Column(length=13)
	private String cShrtCntnt;
	
	@Column(length=13)
	private String rcShrtCntnt;
	
	@ManyToOne
	@JoinColumn(name="comment", nullable=false)
	private Comment comment;
	
	@ManyToOne
	@JoinColumn(name="rComment", nullable=false)
	private Comment rComment;
	
	@ManyToOne
	@JoinColumn(name="message", nullable=false)
	private Message message;
	
	@ManyToOne
	@JoinColumn(name="cUser", nullable=false)
	private User cUser;
	
	@ManyToOne
	@JoinColumn(name="mUser", nullable=false)
	private User mUser;
	
	@ManyToOne
	@JoinColumn(name="rcUser", nullable=false)
	private User rcUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmCreated;
	
	private Boolean isRecived;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmRecived;

	public NtcRCmmnt() {
		// TODO Auto-generated constructor stub
	}
	
	public NtcRCmmnt(Comment comment, Comment rComment) {
		// TODO Auto-generated constructor stub
		this(comment, rComment, comment.getUnderWhichMessage(), comment.getUser());
	}
	
	public NtcRCmmnt(Comment comment, Comment rComment, Message message, User cUser){
		this(comment.getCid(), rComment.getCid(), message.getMid(), comment.getUid(), rComment.getUid(),
				message.getUid(), cUser.getUname(), message.getUser().getUname(),rComment.getUser().getUname(), cUser.getNname(),
				comment.getContent().length()>18?comment.getContent().substring(0, 18):comment.getContent(),
				rComment.getContent().length()>18?comment.getContent().substring(0, 18):comment.getContent(),
				comment, rComment, message, cUser, message.getUser(), rComment.getUser(),
				new Date(), comment.getUid() == rComment.getUid()?true:false, null);
	}
	
	public NtcRCmmnt(Long cid, Long rcid, Long mid, Long cUid, Long rcUid, Long mUid, String cUname, String mUname,
			String rcUname, String cNname, String cShrtCntnt, String rcShrtCntnt, Comment comment, Comment rComment,
			Message message, User cUser, User mUser, User rcUser, Date tmCreated, Boolean isRecived, Date tmRecived) {
		super();
		this.cid = cid;
		this.rcid = rcid;
		this.mid = mid;
		this.cUid = cUid;
		this.rcUid = rcUid;
		this.mUid = mUid;
		this.cUname = cUname;
		this.mUname = mUname;
		this.rcUname = rcUname;
		this.cNname = cNname;
		this.cShrtCntnt = cShrtCntnt;
		this.rcShrtCntnt = rcShrtCntnt;
		this.comment = comment;
		this.rComment = rComment;
		this.message = message;
		this.cUser = cUser;
		this.mUser = mUser;
		this.rcUser = rcUser;
		this.tmCreated = tmCreated;
		this.isRecived = isRecived;
		this.tmRecived = tmRecived;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getRcid() {
		return rcid;
	}

	public void setRcid(Long rcid) {
		this.rcid = rcid;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public Long getcUid() {
		return cUid;
	}

	public void setcUid(Long cUid) {
		this.cUid = cUid;
	}

	public Long getRcUid() {
		return rcUid;
	}

	public void setRcUid(Long rcUid) {
		this.rcUid = rcUid;
	}

	public Long getmUid() {
		return mUid;
	}

	public void setmUid(Long mUid) {
		this.mUid = mUid;
	}

	public String getcUname() {
		return cUname;
	}

	public void setcUname(String cUname) {
		this.cUname = cUname;
	}

	public String getmUname() {
		return mUname;
	}

	public void setmUname(String mUname) {
		this.mUname = mUname;
	}

	public String getRcUname() {
		return rcUname;
	}

	public void setRcUname(String rcUname) {
		this.rcUname = rcUname;
	}

	public String getcNname() {
		return cNname;
	}

	public void setcNname(String cNname) {
		this.cNname = cNname;
	}

	public String getcShrtCntnt() {
		return cShrtCntnt;
	}

	public void setcShrtCntnt(String cShrtCntnt) {
		this.cShrtCntnt = cShrtCntnt;
	}

	public String getRcShrtCntnt() {
		return rcShrtCntnt;
	}

	public void setRcShrtCntnt(String rcShrtCntnt) {
		this.rcShrtCntnt = rcShrtCntnt;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Comment getrComment() {
		return rComment;
	}

	public void setrComment(Comment rComment) {
		this.rComment = rComment;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public User getcUser() {
		return cUser;
	}

	public void setcUser(User cUser) {
		this.cUser = cUser;
	}

	public User getmUser() {
		return mUser;
	}

	public void setmUser(User mUser) {
		this.mUser = mUser;
	}

	public User getRcUser() {
		return rcUser;
	}

	public void setRcUser(User rcUser) {
		this.rcUser = rcUser;
	}

	public Date getTmCreated() {
		return tmCreated;
	}

	public void setTmCreated(Date tmCreated) {
		this.tmCreated = tmCreated;
	}

	public Boolean getIsRecived() {
		return isRecived;
	}

	public void setIsRecived(Boolean isRecived) {
		this.isRecived = isRecived;
	}

	public Date getTmRecived() {
		return tmRecived;
	}

	public void setTmRecived(Date tmRecived) {
		this.tmRecived = tmRecived;
	}

	@Override
	public String toString() {
		return "NtcRCmmnt [cid=" + cid + ", rcid=" + rcid + ", mid=" + mid + ", cUid=" + cUid + ", rcUid=" + rcUid
				+ ", mUid=" + mUid + ", cUname=" + cUname + ", mUname=" + mUname + ", rcUname=" + rcUname + ", cNname="
				+ cNname + ", cShrtCntnt=" + cShrtCntnt + ", rcShrtCntnt=" + rcShrtCntnt + ", tmCreated=" + tmCreated
				+ ", isRecived=" + isRecived + ", tmRecived=" + tmRecived + "]";
	}
	
	
}
