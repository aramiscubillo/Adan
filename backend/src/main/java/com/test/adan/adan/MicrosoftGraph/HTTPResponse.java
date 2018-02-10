package com.test.adan.adan.MicrosoftGraph;

import org.json.JSONObject;

public class HTTPResponse {
	private int code;
	
	private String message;
	
	private JSONObject body;
	
	private String errorCode;
	
	private String errorMessage;
	
	private boolean needRenew;

	public HTTPResponse(int code, String message, JSONObject body) {
		setCode(code);
		setMessage(message);
		setBody(body);
	}

	public int getCode() {
		return code;
	}

	private void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	private void setMessage(String message) {
		this.message = message;
	}

	public JSONObject getBody() {
		return body;
	}

	private void setBody(JSONObject body) {
		this.body = body;
	}

	public boolean isNeedRenew() {
		return needRenew;
	}

	public void setNeedRenew(boolean needRenew) {
		this.needRenew = needRenew;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
