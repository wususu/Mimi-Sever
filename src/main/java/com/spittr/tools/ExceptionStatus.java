package com.spittr.tools;

public enum ExceptionStatus {
	 SUCCESS(200, "成功"),
     ERROR(-1001, "失败"),
     USER_NOT_FOUND(-1002, "用户不存在");
	
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
