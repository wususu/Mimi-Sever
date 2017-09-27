package com.spittr.authorization.model;

public class Data {

	private HMTUserInfoModel data;

	public HMTUserInfoModel getData() {
		return data;
	}

	public void setData(HMTUserInfoModel data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Data [data=" + data + "]";
	}
	
}
