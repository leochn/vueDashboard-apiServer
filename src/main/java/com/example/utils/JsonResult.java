package com.example.utils;

import com.example.config.Constant;

public class JsonResult {
	// 响应业务状态
	private Integer status;
	// data 数据条数
	private long total;
	
	// 响应中的数据
	private Object data;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public JsonResult() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 只返回成功状态
	 * 
	 * @return
	 */
	public static JsonResult ok() {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStatus(Constant.RESCODE_SUCCESS);
		return jsonResult;
	}

	/**
	 * 自定义返回状态
	 * 
	 * @param status
	 * @return
	 */
	public static JsonResult custom(Integer status) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStatus(status);
		return jsonResult;
	}
	
	public static JsonResult custom(Object data) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStatus(Constant.RESCODE_SUCCESS);
		jsonResult.setData(data);
		return jsonResult;
	}

	public static JsonResult custom(long total, Object data) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStatus(Constant.RESCODE_SUCCESS);
		jsonResult.setTotal(total);
		jsonResult.setData(data);
		return jsonResult;
	}

	public static JsonResult custom(Integer status, long total, Object data) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStatus(status);
		jsonResult.setTotal(total);
		jsonResult.setData(data);
		return jsonResult;
	}

}
