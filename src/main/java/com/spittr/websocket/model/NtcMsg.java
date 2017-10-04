package com.spittr.websocket.model;

public class NtcMsg {
	
	private NtcType ntcType;
	
	private NtcBody ntcBody;
	
	public NtcMsg(){
		
	}
	
	public NtcMsg(NtcType ntcType, NtcBody ntcBody){
		this.ntcBody = ntcBody;
		this.ntcType = ntcType;
	}
	
	public NtcType getNtcType() {
		return ntcType;
	}

	public void setNtcType(NtcType ntcType) {
		this.ntcType = ntcType;
	}

	public NtcBody getNtcBody() {
		return ntcBody;
	}

	public void setNtcBody(NtcBody ntcBody) {
		this.ntcBody = ntcBody;
	}

	@Override
	public String toString() {
		return "NtcMsg [ntcType=" + ntcType + ", ntcBody=" + ntcBody + "]";
	}
	
}
