package com.gasl.taskmanagement.vo;

//import lombok.Data;
//
//@Data
public class RequestResponse {

	private Object model;
	private Boolean hasError;
	private String message;

	
	  public Object getModel() { return model; }
	  
	  public void setModel(Object model) { this.model = model; }
	  
	  public Boolean getHasError() { return hasError; }
	  
	  public void setHasError(Boolean hasError) { this.hasError = hasError; }
	  
	  public String getMessage() { return message; }
	  
	  public void setMessage(String message) { this.message = message; }


}
