package com.example.config;

public class Constant {
	/**
	 * 数据请求返回码
	 */
	public static final int RESCODE_SUCCESS = 1000;				//成功
	public static final int RESCODE_EXCEPTION = 1001;			//请求抛出异常
	public static final int RESCODE_NOLOGIN = 1002;				//未登陆状态
	public static final int RESCODE_NOEXIST = 1003;				//查询结果为空
	public static final int RESCODE_NOAUTH = 1004;				//无操作权限
	
	/**
	 * jwt
	 */
	public static final String JWT_ID = "jwt";
	public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
	public static final int JWT_EXPIRATION = 20*60*1000;  //millisecond,20分钟
	public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
	public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
}