package com.crudProject.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorDetails{
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	    private Date timestamp;
	    private String message;
	    private String details;

	    public ErrorDetails(Date timestamp, String message, String details) {
	        super();
	        this.timestamp = timestamp;
	        this.message = message;
	        this.details = details;
	    }

	    public Date getTimestamp() {
	        return timestamp;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public String getDetails() {
	        return details;
	    }
}
