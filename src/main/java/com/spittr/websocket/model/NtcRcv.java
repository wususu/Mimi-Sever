package com.spittr.websocket.model;

public class NtcRcv {

	private Long id;
	
	private NtcType ntcType;
	
	public NtcRcv() {
		// TODO Auto-generated constructor stub
	}
	
	public NtcRcv(Long id){
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NtcType getNtcType() {
		return ntcType;
	}

	public void setNtcType(NtcType ntcType) {
		this.ntcType = ntcType;
	}
}
