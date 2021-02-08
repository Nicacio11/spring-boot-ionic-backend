package com.vitornicacio.cursomc.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
	
	
	public FieldMessage() {
		
	}


	public FieldMessage(String fieldMessage, String message) {
		super();
		this.fieldName = fieldMessage;
		this.message = message;
	}


	public String getFieldName() {
		return fieldName;
	}


	public void setFieldName(String fieldMessage) {
		this.fieldName = fieldMessage;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
}
