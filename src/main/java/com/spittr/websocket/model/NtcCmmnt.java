package com.spittr.websocket.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spittr.message.model.Comment;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Entity
@Table(name="ntc_cmmnt", uniqueConstraints=@UniqueConstraint(columnNames={"comment", "mUser"}))
@JsonIgnoreProperties(value={"cUser", "mUser", "tmRecived", "isRecived", "comment", "message"})
public class NtcCmmnt implements Serializable, NtcBody{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8140878691832022863L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private Long cid;
	
	@Column(nullable=false)
	private Long mid;
	
	@Column(nullable=false)
	private Long cUid;
	
	@Column(nullable=false)
	private Long mUid;
	
	@Column(nullable=false)
	private String cUname;
	
	@Column(nullable=false)
	private String mUname;
	
	@Column(nullable=false)
	private String cNname;
	
	@Column(length=13)
	private String cShrtCntnt;
	
	@Column(length=13)
	private String mShrtCntnt;
	
	@ManyToOne
	@JoinColumn(name="comment", nullable=false)
	private Comment comment;
	
	@ManyToOne
	@JoinColumn(name="message", nullable=false)
	private Message message;
	
	@ManyToOne
	@JoinColumn(name="cUser", nullable=false)
	private User cUser;
	
	@ManyToOne
	@JoinColumn(name="mUser", nullable=false)
	private User mUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmCreated;
	
	private Boolean isRecived;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmRecived;

	public NtcCmmnt() {
		// TODO Auto-generated constructor stub
	}
	
	public NtcCmmnt(Comment comment){
		this(comment, comment.getUser(), comment.getUnderWhichMessage(), comment.getUnderWhichMessage().getUser());
	}
	
	public  NtcCmmnt(Comment comment, User cUser, Message message, User mUser) {
		this(comment.getCid(), message.getMid(), cUser.getUid(), mUser.getUid(),cUser.getNname(),
				cUser.getUname(), mUser.getUname(), comment, message, cUser, mUser,
				cUser.equals(mUser)?true:false,cUser.equals(mUser)?new Date(/* NOW */):null, new Date(),
				comment.getContent().length()>10?comment.getContent().substring(0, 10):comment.getContent(), 
				message.getContent().length()>10?message.getContent().substring(0, 10): message.getContent()		
				);
	}

	public NtcCmmnt(Long cid, Long mid, Long cUid, Long mUid, String cUname, String cNname,
			String mUname, Comment comment, Message message, User cUser, User mUser, Boolean isRecived,
			Date tmRecived, Date tmCreated, String cShrtCntnt, String mShrtCntnt) {
		super();
		this.cid = cid;
		this.mid = mid;
		this.cUid = cUid;
		this.mUid = mUid;
		this.cUname = cUname;
		this.cNname = cNname;
		this.mUname = mUname;
		this.comment = comment;
		this.message = message;
		this.cUser = cUser;
		this.mUser = mUser;
		this.isRecived = isRecived;
		this.tmRecived = tmRecived;
		this.tmCreated = tmCreated;
		this.cShrtCntnt = cShrtCntnt;
		this.mShrtCntnt = mShrtCntnt;
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

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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

	public String getmShrtCntnt() {
		return mShrtCntnt;
	}

	public void setmShrtCntnt(String mShrtCntnt) {
		this.mShrtCntnt = mShrtCntnt;
	}

	public Date getTmCreated() {
		return tmCreated;
	}

	public void setTmCreated(Date tmCreated) {
		this.tmCreated = tmCreated;
	}

	@Override
	public String toString() {
		return "NtcCmmnt [id=" + id + ", cid=" + cid + ", mid=" + mid + ", cUid=" + cUid + ", mUid=" + mUid
				+ ", cUname=" + cUname + ", mUname=" + mUname + ", isRecived=" + isRecived + ", tmRecived=" + tmRecived
				+ "]";
	}

}
