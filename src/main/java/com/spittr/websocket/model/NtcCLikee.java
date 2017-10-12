package com.spittr.websocket.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spittr.message.model.CLikee;
import com.spittr.message.model.Comment;
import com.spittr.user.model.User;

@Entity
@Table(name="ntc_c_likee", uniqueConstraints=@UniqueConstraint(columnNames={"cLikee", "cUser"}))
@JsonIgnoreProperties(value={"cLikee", "cUser", "tmRecived", "isRecived", "comment"})
public class NtcCLikee implements Serializable, NtcBody{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7588870577993092531L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private Long mid;
	
	@Column(nullable=false)
	private Long cUid;
	
	@Column(nullable=false)
	private String cUname;
	
	@Column(nullable=false)
	private Long cid;
	
	@Column(nullable=false)
	private Long lkUid;
	
	@Column(nullable=false)
	private String lkUname;
	
	@Column(nullable=false)
	private String lkNname;
	
	@Column(length=20)
	private String cShrtCntnt;
	
	@ManyToOne
	@JoinColumn(name="cLikee", nullable=false)
	private CLikee cLikee;
	
	@ManyToOne
	@JoinColumn(name="cUser", nullable=false)
	private User cUser;
	
	@ManyToOne
	@JoinColumn(name="lkUser", nullable=false)
	private User lkUser;
	
	@ManyToOne
	@JoinColumn(name="comment", nullable=false)
	private Comment comment;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmCreated;
	
	@Column(nullable=false)
	private Boolean isRecived;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmRecived;

	public NtcCLikee() {
		// TODO Auto-generated constructor stub
	}
	
	public NtcCLikee(CLikee cLikee){
		this(cLikee, cLikee.getUser(), cLikee.getComment().getUser(), cLikee.getComment());
	}
	
	private NtcCLikee(CLikee cLikee,User lkUser, User cUser, Comment comment){
		this(cUser.getUid(), comment.getMid(), cUser.getUname(), comment.getCid(), lkUser.getUid(), 
				lkUser.getUname(), lkUser.getNname(), cLikee, cUser, lkUser, comment,
				cUser.equals(lkUser)?true:false, cUser.equals(lkUser)?new Date(/* NOW */):null, new Date(), 
				comment.getContent().length()>18?comment.getContent().substring(0, 18): comment.getContent());
	}

	public NtcCLikee(Long cUid, Long mid,  String cUname, Long cid, Long lkUid, String lkUname, String lkNname,
			CLikee cLikee, User cUser,	User lkUser, Comment comment, Boolean isRecived, Date tmRecived,
			Date tmCreated, String cShrtCntnt) {
		super();
		this.cUid = cUid;
		this.mid = mid;
		this.cUname = cUname;
		this.cid = cid;
		this.lkUid = lkUid;
		this.lkUname = lkUname;
		this.lkNname = lkNname;
		this.cLikee = cLikee;
		this.cUser = cUser;
		this.lkUser = lkUser;
		this.comment = comment;
		this.isRecived = isRecived;
		this.tmRecived = tmRecived;
		this.tmCreated = tmCreated;
		this.cShrtCntnt = cShrtCntnt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getcUid() {
		return cUid;
	}

	public void setcUid(Long cUid) {
		this.cUid = cUid;
	}
	
	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public String getcUname() {
		return cUname;
	}

	public void setcUname(String cUname) {
		this.cUname = cUname;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getLkUid() {
		return lkUid;
	}

	public void setLkUid(Long lkUid) {
		this.lkUid = lkUid;
	}

	public String getLkUname() {
		return lkUname;
	}

	public void setLkUname(String lkUname) {
		this.lkUname = lkUname;
	}

	public String getLkNname() {
		return lkNname;
	}

	public void setLkNname(String lkNname) {
		this.lkNname = lkNname;
	}

	public String getcShrtCntnt() {
		return cShrtCntnt;
	}

	public void setcShrtCntnt(String cShrtCntnt) {
		this.cShrtCntnt = cShrtCntnt;
	}

	public CLikee getcLikee() {
		return cLikee;
	}

	public void setcLikee(CLikee cLikee) {
		this.cLikee = cLikee;
	}

	public User getcUser() {
		return cUser;
	}

	public void setcUser(User cUser) {
		this.cUser = cUser;
	}

	public User getLkUser() {
		return lkUser;
	}

	public void setLkUser(User lkUser) {
		this.lkUser = lkUser;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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
		return "NtcCLikee [id=" + id + ", cUid=" + cUid + ", cUname=" + cUname + ", cid=" + cid + ", lkUid=" + lkUid
				+ ", lkUname=" + lkUname + ", lkNname=" + lkNname + ", cShrtCntnt=" + cShrtCntnt + ", cUser=" + cUser
				+ ", lkUser=" + lkUser + ", tmCreated=" + tmCreated + ", isRecived=" + isRecived + ", tmRecived="
				+ tmRecived + "]";
	}


	
}
