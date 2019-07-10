package com.thinhvp.masterdemo.dto;

public class RestResponse<T extends ResponseData> {
	private T data;

	public RestResponse() {
	}

	public RestResponse(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
