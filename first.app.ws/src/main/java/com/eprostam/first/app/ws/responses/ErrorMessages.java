package com.eprostam.first.app.ws.responses;

public enum ErrorMessages {
	
	MISSING_REQUIRED_FIELD("Missing required field!"),
	RECORD_ALREADY_EXISTS("Record already exists!"),
	INTERNAL_SERVER_ERROR("Internal server error!"),
	NO_RECORD_FOUND("Record with provided id is not found!");

	private String errorMessage;

	ErrorMessages(String message) {
		this.errorMessage = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
