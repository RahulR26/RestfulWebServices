package com.rahul.SpringRestfulJPA.rahulSpring.exception;

import java.util.Date;

public class ExceptionResponse {
	private Date timestamp;
	private String title;
	private String content;
	
	public ExceptionResponse(Date timestamp, String title, String content) {
		super();
		this.timestamp = timestamp;
		this.title = title;
		this.content = content;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	
	
}
