package com.spittr.tools;

import com.spittr.config.StatusCodeConf;

public enum ExceptionStatus {
	 SUCCESS(StatusCodeConf.SUCCESS, "成功"),
     ERROR(StatusCodeConf.ErrorCode, "失败");
	
	public static String DiY_OK_MESSAGE = "(=ↀωↀ=)";
	public static String DiY_ERROR_MESSAGE = "(゜Д゜;)";
	/**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    private ExceptionStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
	
}
