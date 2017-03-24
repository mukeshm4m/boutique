package com.boutique.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.boutique.validation.ValidationErrors;

public class StatusMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failure";

	private String statusCode;
	private String statusMessage = SUCCESS;
	private String messageKey = "";
	private String details;
	private HashMap<String, Serializable> objects = new HashMap<String, Serializable>();
	private ValidationErrors validationErrors = new ValidationErrors();
	private List<Error> errors = new ArrayList<Error>();

	public StatusMessage() {
		super();
	}

	/**
	 * 
	 * The getStatusCode() method is used to get the status code.
	 *
	 * @return Returns the status code.
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * 
	 * The setStatusCode() method is used to set the status code.
	 *
	 * @param statusCode
	 *            Specifies the status code to be set.
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * 
	 * The getStatusMessage() method is used to return the status message.
	 *
	 * @return Specifies the status message.
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * 
	 * The setStatusMessage() method is used to set the status message.
	 *
	 * @param statusMessage
	 *            Specifies the status message to be set.
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
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
	 * The setMessageKey() method is used to set message key, business rule or
	 * validation case. e.g. user.self_register.successful_100.
	 *
	 * @param messageKey
	 *            Specifies the message key to be set.
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	/**
	 * @return the validationErrors
	 */
	public ValidationErrors getValidationErrors() {
		return validationErrors;
	}

	/**
	 * @param validationErrors
	 *            the validationErrors to set
	 */
	public void setValidationErrors(ValidationErrors validationErrors) {
		this.validationErrors = validationErrors;
	}

	public Integer getIntegerId(String identifierName) {
		try {
			return Integer.parseInt(this.getObjects().get(identifierName).toString());
		} catch (Exception ex) {
		}

		return null;
	}

	/**
	 * 
	 * The getDetails() method is used to get the status detail information.
	 *
	 * @return Returns the status detail information.
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * 
	 * The setDetails() method is used to set the status detail information.
	 *
	 * @param details
	 *            Specifies the status detail information.
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * 
	 * The getObjects() method is used to get the objects map returned in the
	 * status message.
	 *
	 * @return Returns the objects map returned in the status message.
	 */
	public HashMap<String, Serializable> getObjects() {
		return objects;
	}

	/**
	 * 
	 * The setObjects() method is used to set the objects map within status
	 * message.
	 *
	 * @param objects
	 *            Specifies the objects map to be set within the status message.
	 */
	public void setObjects(HashMap<String, Serializable> objects) {
		this.objects = objects;
	}

	public Boolean getAnyError() {
		return validationErrors.getAnyError();
	}

	public Boolean matchFailureCase(String messageKey) {
		return this.validationErrors.matchFailureCase(messageKey);
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public Boolean hasErrors() {
		return !this.getErrors().isEmpty();
	}
}
