package com.spittr.authorization.model;

public class HMTOAuthModel {

	private String access_token;
	
	private String scope;
	
	private Long uid;
	
	private Long expires_in;
	
	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	@Override
	public String toString() {
		return "HMTOAuthModel [access_token=" + access_token + ", scope=" + scope + ", uid=" + uid + ", expires_in="
				+ expires_in + "]";
	}
	
	
}
