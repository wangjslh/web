package com.jslh.util.bean;

import java.util.Collection;

public class BackData {

	private Integer status;//fail:0，success:1
	private String msg;//tip message
	private Collection<?> data;//business data

	public BackData(boolean success) {
		this.status = success == true ? 1 : 0;
	}

	public BackData(boolean success, String msg) {
		this.status = success == true ? 1 : 0;
		this.msg = msg;
	}
	
	public BackData(boolean success, Collection<?> data) {
		this.status = success == true ? 1 : 0;
		this.data = data;
	}
	
	public BackData(boolean success, String msg, Collection<?> data) {
		this.status = success == true ? 1 : 0;
		this.msg = msg;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Collection<?> getData() {
		return data;
	}

	public void setData(Collection<?> data) {
		this.data = data;
	}

}
