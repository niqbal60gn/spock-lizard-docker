package com.mcnz.rps.spring;

public class HelloResponse {
	
	String message;
	
	public HelloResponse(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
