package com.boutique.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import com.boutique.common.util.Util;


@SuppressWarnings("serial")
public class ValidationErrors implements Serializable {

	public static final String GENERAL_ERROR = "General";
	protected Hashtable<String, ErrorMessage> errorMessages = new Hashtable<String, ErrorMessage>();
	protected boolean anyError;
	protected boolean isHTML;
	protected String cssStyle = "color:#555;font-weight:normal;width:450px;padding:2px;font-size:13px;";
	protected String warningStyle = "color:#8a6d3b;font-weight:normal;width:450px;padding:2px;font-size:13px;";
	protected String cssClass = "errorMessage";

	public ValidationErrors() {
		this(true);
	}

	public ValidationErrors(boolean isHTML) {
		this.anyError = false;
		this.isHTML = isHTML;
	}

	public ValidationErrors(boolean isHTML, String cssStyle) {
		this(isHTML);
		this.cssStyle = cssStyle;
	}

	public void clear() {
		this.errorMessages.clear();
		this.anyError = false;
	}

	public void addError(String fieldName, String error) {
		this.addError(fieldName, null, error);
	}

	public void addError(String fieldName, String messageKey, String error) {
		this.errorMessages.put(fieldName, createErrorMessage(fieldName, messageKey, error));
		this.anyError = true;
	}

	protected ErrorMessage createErrorMessage(String fieldName, String message) {
		return createErrorMessage(fieldName, null, message);
	}

	protected ErrorMessage createErrorMessage(String fieldName, String messageKey, String message) {
		return new ErrorMessage(fieldName, messageKey, message);
	}

	protected ErrorMessage createErrorMessage(String message) {
		return createErrorMessage("", message);
	}

	public void addError(String error) {
		ErrorMessage errorMessage = this.errorMessages.get(GENERAL_ERROR);
		if (errorMessage == null) {
			errorMessage = new ErrorMessage(GENERAL_ERROR, error);
			this.errorMessages.put(GENERAL_ERROR, errorMessage);
		} else {
			errorMessage.setMessage(errorMessage.getMessage() + "\n" + error);
		}
		this.anyError = true;
	}

	public String getError(String fieldName) {
		return (this.errorMessages.get(fieldName) != null) ? ("<br><span class=" + this.cssClass + ">"
				+ this.errorMessages.get(fieldName) + "</span>") : "";
	}

	public String getSimpleError(String fieldName) {
		return (this.errorMessages.get(fieldName) != null) ? this.errorMessages.get(fieldName).toString() : "";
	}

	public boolean containsField(String fieldName) {
		return this.errorMessages.containsKey(fieldName);
	}

	public boolean getAnyError() {
		return this.anyError;
	}

	public void reset() {
		this.errorMessages.clear();
		this.anyError = false;
	}

	public Hashtable<String, ErrorMessage> getActionErrors() {
		return this.errorMessages;
	}

	@Override
	public String toString() {
		return toString(true);
	}

	protected String getNewLineHtml(){
		return "";//"<BR>\n"
	}
	public String toString(boolean generalOnly) {
		if (!this.anyError) {
			return ValidationUtil.EMPTY_SPACE;
		}

		StringBuffer message = new StringBuffer();

		if (generalOnly) {
			if (this.isHTML) {
				message.append(getNewLineHtml()+"<TABLE BRODER=\"0\">\n").append("<TR><TD class=" + this.cssClass + "> - ").append(
						"</TD></TR>\n");
			} else {
				message.append("Errors found, please fix\n");
			}

			if (this.errorMessages.get(GENERAL_ERROR) != null) {
				if (this.isHTML) {
					message.append("<TR><TD class=" + this.cssClass + "> - ").append(this.errorMessages.get(GENERAL_ERROR))
							.append("</TD></TR>\n");
				} else {
					message.append(this.errorMessages.get(GENERAL_ERROR)).append("\n");
				}
			}
		} else {
			if (this.isHTML) {
				message.append(getNewLineHtml()+"<TABLE BRODER=\"0\">\n").append("<TR><TD class=" + this.cssClass + "> - ").append(
						"</TD></TR>\n");
			} else {
				message.append("Errors found, please fix\n");
			}

			Collection<ErrorMessage> errs = this.errorMessages.values();

			for (ErrorMessage err : errs) {
				if (this.isHTML) {
					message.append("<TR><TD class=" + this.cssClass + "> - ").append(err.toString()).append("</TD></TR>\n");
				} else {
					message.append(err.toString()).append("\n");
				}
			}
		}

		if (this.isHTML) {
			message.append("</TABLE>\n<BR>\n");
		}

		return message.toString();
	}

	public String getAllErrors(boolean generalOnly) {
		return populateErrorMessagesPanel(generalOnly, true);
	}

	public String getAllWarnings(boolean generalOnly) {
		return populateErrorMessagesPanel(generalOnly, false);
	}

	protected String populateErrorMessagesPanel(boolean generalOnly, boolean isError) {
		if (!this.anyError) {
			return ValidationUtil.EMPTY_SPACE;
		}

		StringBuffer message = new StringBuffer();

		if (this.isHTML) {

			message.append("<div> <a class=\"closeLink\" onclick=\"hideErrorMessagePanel(this);\" style=\"float: right !important; color:red; margin-right: 0.5em;cursor: pointer; font-size:13px;\">X</a> <br /> </div>");

			message.append("<div class=\"slideContent2\" style=\"width: 100%;float: left;\">");
		}
		if (generalOnly) {
			if (this.isHTML) {
				message.append(getNewLineHtml()+"<TABLE STYLE=\"width: 100%;\" BORDER=\"0\">\n").append("<TR><TD style=" + (isError ? this.cssStyle : this.warningStyle) + ">").append(
						"</TD></TR>\n");
			} else {
				message.append("Errors found, please fix\n");
			}

			if (this.errorMessages.get(GENERAL_ERROR) != null) {
				if (this.isHTML) {
					message.append("<TR><TD style=" + this.cssStyle + "> - ").append(this.errorMessages.get(GENERAL_ERROR))
							.append("</TD></TR>\n");
				} else {
					message.append(this.errorMessages.get(GENERAL_ERROR)).append("\n");
				}
			}
		} else {
			if (this.isHTML) {
				message.append(getNewLineHtml()+"<TABLE STYLE=\"width: 100%;\" BORDER=\"0\">\n").append("<TR><TD style=" + (isError ? this.cssStyle : this.warningStyle) + ">").append(
						"</TD></TR>\n");
			} else {
				message.append("Errors found, please fix\n");
			}

			Collection<ErrorMessage> errs = this.errorMessages.values();

			for (ErrorMessage err : errs) {
				if (this.isHTML) {
					message.append("<TR><TD style=" + (isError ? this.cssStyle : this.warningStyle) + "> - ").append(err.toString()).append("</TD></TR>\n");
				} else {
					message.append(err.toString()).append("\n");
				}
			}
		}

		if (this.isHTML) {
			message.append("</TABLE>\n<BR>\n");

			message.append("</div>");
		}

		return message.toString();
	}

	public void removeError(String fieldName) {
		this.errorMessages.remove(fieldName);

		if(this.errorMessages.size() == 0) {
			this.anyError = false;
		}
	}

	public String getErrorMessage(String fieldName) {
		String message = "";
		if (this.errorMessages.containsKey(fieldName)) {
			message = this.errorMessages.get(fieldName).getMessage();
		}
		return message;
	}

	public Boolean matchFailureCase(String messageKey) {
		try {
			if (Util.isNotNullAndEmpty(messageKey)) {
				List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>(this.errorMessages.values());

				if (Util.isNotNullAndEmpty(errorMessages)) {
					for (ErrorMessage errorMessage : errorMessages) {
						if (errorMessage.getMessageKey() != null && errorMessage.getMessageKey().equals(messageKey)) {
							return true;
						}
					}
				}
			}
		} catch (Exception ex) {
			return false;
		}

		return false;
	}

	public String getAllErrorsInPlainFormat() {
		try {
			List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>(this.errorMessages.values());
			String errors = "";

			if (Util.isNotNullAndEmpty(errorMessages)) {
				for (ErrorMessage errorMessage : errorMessages) {
					errors += errorMessage.getMessage() + "\n";
				}
			}

			return errors;
		} catch (Exception ex) {
			return "";
		}
	}
}
