package com.spittr.websocket.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spittr.message.model.MLikee;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;

@Entity
@Table(name="ntc_m_likee", uniqueConstraints=@UniqueConstraint(columnNames={"message", "lkUser"}))
@JsonIgnoreProperties(value={"mUser","lkUser", "mLikee", "tmRecived", "isRecived", "message"})
public class NtcMLikee implements Serializable, NtcBody{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8622434480995812391L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private Long mid;
	
	@Column(nullable=false)
	private Long mUid;
	
	@Column(nullable=false)
	private Long lkUid;
	
	@Column(nullable=false)
	private String mUname;
	
	@Column(nullable=false)
	private String lkUname;
	
	@Column(nullable=false)
	private String lkNname;
	
	@Column(length=13)
	private String mShrtCntnt;
	
	@ManyToOne
	@JoinColumn(name="mUser", nullable=false)
	private User mUser;
	
	@ManyToOne
	@JoinColumn(name="lkUser", nullable=false)
	private User lkUser;
	
	@ManyToOne
	@JoinColumn(name="message", nullable=false)
	private Message message;
	
	@ManyToOne
	@JoinColumn(name="mLikee", nullable=false)
	private MLikee mLikee;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmCreated;
	
	private Boolean isRecived;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmRecived;

	public NtcMLikee() {
		// TODO Auto-generated constructor stub
	}
	
	public NtcMLikee(MLikee mLikee){
		this(mLikee, mLikee.getUser(), mLikee.getMessage(), mLikee.getMessage().getUser());
	}
	
	public NtcMLikee(MLikee mLikee, User lkUser, Message message, User mUser){
		this(message.getMid(), message.getMid(), mLikee.getUid(), 
				mUser.getUname(), lkUser.getUname(), lkUser.getNname(), mUser, lkUser, message, mLikee,
				( (mUser.equals(lkUser) && mLikee.isLike() )?true:false), mUser.equals(lkUser)?new Date(/* NOW */):null, new Date(), message.getContent().length()>10?message.getContent().substring(0, 10):message.getContent());
	}
	
	public NtcMLikee(Long mid, Long mUid, Long lkUid, String mUname, String lkUname, 
			String lkNname, User mUser, User lkUser, Message message, MLikee mLikee,
			Boolean isRecived, Date tmRecived, Date tmCreated, String mShrtCntnt) {
		super();
		this.mid = mid;
		this.mUid = mUid;
		this.lkUid = lkUid;
		this.mUname = mUname;
		this.lkUname = lkUname;
		this.lkNname = lkNname;
		this.mUser = mUser;
		this.lkUser = lkUser;
		this.message = message;
		this.mLikee = mLikee;
		this.isRecived = isRecived;
		this.tmRecived = tmRecived;
		this.tmCreated = tmCreated;
		this.mShrtCntnt = mShrtCntnt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public Long getmUid() {
		return mUid;
	}

	public void setmUid(Long mUid) {
		this.mUid = mUid;
	}

	public Long getLkUid() {
		return lkUid;
	}

	public void setLkUid(Long lkUid) {
		this.lkUid = lkUid;
	}

	public String getmUname() {
		return mUname;
	}

	public void setmUname(String mUname) {
		this.mUname = mUname;
	}

	public String getLkUname() {
		return lkUname;
	}

	public void setLkUname(String lkUname) {
		this.lkUname = lkUname;
	}

	public User getmUser() {
		return mUser;
	}

	public void setmUser(User mUser) {
		this.mUser = mUser;
	}

	public User getLkUser() {
		return lkUser;
	}

	public void setLkUser(User lkUser) {
		this.lkUser = lkUser;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public MLikee getmLikee() {
		return mLikee;
	}

	public void setmLikee(MLikee mLikee) {
		this.mLikee = mLikee;
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
	
	public String getLkNname() {
		return lkNname;
	}

	public void setLkNname(String lkNname) {
		this.lkNname = lkNname;
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
		return "NtcMLikee [mid=" + mid + ", mUid=" + mUid + ", lkUid=" + lkUid + ", mUname=" + mUname
				+ ", lkUname=" + lkUname + ", mUser=" + mUser + ", lkUser=" + lkUser + ", message=" + message
				+ ", mLikee=" + mLikee + ", isRecived=" + isRecived + ", tmRecived=" + tmRecived + "]";
	}
	
}
