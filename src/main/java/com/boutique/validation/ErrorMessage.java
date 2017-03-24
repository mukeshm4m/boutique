package com.boutique.validation;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ErrorMessage implements Serializable {

	private String fieldName = "";
	private String messageKey = "";
	private String message = "";

	public ErrorMessage() {
		super();
	}
	
	/**
	 * 
	 * The getMessageKey() method is used to return the message key.
	 *
	 * @return Specifies the message key.
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * 
	 * The setMessageKey() method is used to set message key, business rule or validation case. e.g. user.self_register.successful_100.
	 *
	 * @param messageKey Specifies the message key to be set.
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	
	public ErrorMessage(String message) {
		super();
		this.message = message;
	}
	
	public ErrorMessage(String fieldName, String message) {
		this(fieldName, null, message);
	}
	
	public ErrorMessage(String fieldName, String messageKey, String message) {
		super();
		this.fieldName = fieldName;
		this.messageKey = messageKey;
		this.message = message;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
//		String string = "";
//		if((fieldName != null) && (!fieldName.isEmpty())){
//			string = fieldName + " - ";
//		}
//		string += message;
		return message;
	}

}
