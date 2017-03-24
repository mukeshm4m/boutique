package com.boutique.validation;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.FloatValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.LongValidator;
import org.apache.commons.validator.routines.ShortValidator;

import com.boutique.common.util.DateUtil;
import com.boutique.common.util.Util;

public class ValidationUtil {

	public static String[] monthNames = { "January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December" };
	public static final String EMPTY_SPACE = "";
	public static final int NUMBER_TYPE_SHORT = 1;
	public static final int NUMBER_TYPE_INTEGER = 2;
	public static final int NUMBER_TYPE_LONG = 3;
	public static final int NUMBER_TYPE_FLOAT = 4;
	public static final int NUMBER_TYPE_DOUBLE = 5;
	// True and False to be used
	protected static final boolean TRUE = true;
	protected static final boolean FALSE = false;
	protected static final double ZERO = 0; // Zero
	protected static final char SINGLE_QUOTE = '\''; // null String
	protected static final String NULL_STR = "null"; // null String
	protected static final String DASHES = "--"; // dashes
	// Validators
	protected static GenericValidator genericValidator = new GenericValidator();
	protected static DoubleValidator doubleValidator = new DoubleValidator();
	protected static IntegerValidator integerValidator = new IntegerValidator();
	protected static ShortValidator shortValidator = new ShortValidator();
	protected static LongValidator longValidator = new LongValidator();
	protected static FloatValidator floatValidator = new FloatValidator();

	protected static final String DATE_PATTERN = "([0-9]{2})/([0-9]{2})/([0-9]{4})";
	protected static final String MONTH_PATTERN = "([0-9]{2})/([0-9]{4})";
	protected static final String PHONE_NUMBER_CHARACTER_SET = "^[0-9\\s\\.-]+$";
	public static final String COUNTRY_CODE_FORMAT = "^\\(\\+[0-9]{1,4}$";

	public static boolean isNullOrEmpty(String string) {
		return (((string == null) || (string.trim().length() == 0)) ? true
				: false);
	}

	@SuppressWarnings("static-access")
	public static boolean validateTextField(boolean isNecessary, String fieldHeading, String fieldName,
			String fieldValue, ValidationErrors errorMessage, int maxLength) {

		if (isNecessary && Util.isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, "common.validation.field_cannot_be_empty_102", fieldHeading + " cannot be empty");
			return FALSE;
		}
		if (fieldValue != null) {
			fieldValue.contains(DASHES);
			if (!genericValidator.maxLength(fieldValue, maxLength)) {
				errorMessage.addError(fieldName, fieldHeading + " cannot be longer than " + maxLength + " characters");
				return FALSE;
			}
		}
		return TRUE;
	}

	@SuppressWarnings("static-access")
	public static boolean validateEmail(boolean isNecessary, String fieldHeading, String fieldName, String fieldValue, ValidationErrors errorMessage, int maxLength) {
		if (isNecessary && Util.isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, "common.validation.field_cannot_be_empty_102", fieldHeading + " cannot be empty");
			return FALSE;
		}
		if (Util.isNotNullAndEmpty(fieldValue)) {
			Pattern p = Pattern.compile("^['_A-Za-z0-9-\\+]+(\\.['_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(fieldValue);
			if (!m.find()) {
				errorMessage.addError(fieldName, "common.validation.invalid_field_101", "invalid email address");
				return FALSE;
			}
			fieldValue.contains(DASHES);
			if (!genericValidator.maxLength(fieldValue, maxLength)) {
				errorMessage.addError(fieldName, fieldHeading + " cannot be longer than " + maxLength + " characters");
				return FALSE;
			}
		}
		return TRUE;
	}

	@SuppressWarnings("static-access")
	public static boolean validateTextFieldWithRegx(boolean isNecessary, String fieldHeading, String fieldName, String fieldValue, ValidationErrors errorMessage, int maxLength) {
		if (isNecessary && Util.isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, "common.validation.field_cannot_be_empty_102", fieldHeading + " cannot be empty");
			return FALSE;
		}
		if (Util.isNotNullAndEmpty(fieldValue)) {
			Pattern p = Pattern.compile("^[a-z0-9]", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(fieldValue.trim());
			if (!m.find()) {
				errorMessage.addError(fieldName, fieldHeading + " contains invalid characters");
			}
			fieldValue.contains(DASHES);
			if (!genericValidator.maxLength(fieldValue, maxLength)) {
				errorMessage.addError(fieldName, fieldHeading + " cannot be longer than " + maxLength + " characters");
				return FALSE;
			}
		}
		return TRUE;
	}

	@SuppressWarnings("static-access")
	public static boolean validateTextFieldWithCustomRegx(boolean isNecessary,
			String fieldHeading, String fieldName, String fieldValue, String customRegx,
			ValidationErrors errorMessage, int maxLength)
		{
		if (isNecessary && Util.isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}

		if (Util.isNotNullAndEmpty(fieldValue)) {
			Pattern p = Pattern.compile(customRegx, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(fieldValue.trim());
			if (!m.find()) {
				errorMessage.addError(fieldName,fieldHeading + " contains invalid character(s).");
				return FALSE;
			}
			fieldValue.contains(DASHES);
			if (!genericValidator.maxLength(fieldValue, maxLength)) {
				errorMessage
						.addError(fieldName, fieldHeading
								+ " cannot be longer than " + maxLength
								+ " characters");
				return FALSE;
			}
		}
		return TRUE;
	}

	@SuppressWarnings("static-access")
	public static boolean validateTextField(boolean isNecessary,
			String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage, int maxLength, boolean isNumeric) {
		if (isNecessary && isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}
		if (!isNumeric) {
			Boolean containNumeric = false;
			String[] sequence = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
					"0" };
			for (String string : sequence) {
				if (fieldValue.contains(string)) {
					containNumeric = true;
					break;
				}
			}
			if (containNumeric) {
				errorMessage.addError(fieldName, fieldHeading
						+ " contains numeric value.");
				return FALSE;
			}
		}
		fieldValue.contains(DASHES);
		if (!genericValidator.maxLength(fieldValue, maxLength)) {
			errorMessage.addError(fieldName, fieldHeading
					+ " cannot be longer than " + maxLength + " characters");
			return FALSE;
		}
		return TRUE;
	}

	public static boolean validateTextField(boolean isNecessary,
			String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage) {
		if (isNecessary && Util.isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}

		return TRUE;
	}

	public static boolean validateLatitudeField(String fieldName,
			String fieldHeading, String fieldValue,
			ValidationErrors errorMessage) {
		if (!isNullOrEmpty(fieldValue)) {
			if (!ValidationUtil.validateTextField(true, fieldHeading, fieldName, fieldValue, errorMessage, 50)) {
				return false;
			}
			try {
				float latitude = Float.parseFloat(fieldValue);
				if (latitude > 90 || latitude < -90) {
					errorMessage.addError(fieldName, fieldHeading + " should be between -90 and 90");
					return false;
				}
			} catch (Exception e) {
				errorMessage.addError(fieldName, fieldHeading + " should be a decimal numeric value");
				return false;
			}
		}
		return true;
	}

	public static boolean validateLongitudeField(String fieldName,
			String fieldHeading, String fieldValue,
			ValidationErrors errorMessage) {
		if (!isNullOrEmpty(fieldValue)) {
			if (!ValidationUtil.validateTextField(true, fieldHeading, fieldName, fieldValue, errorMessage, 50)) {
				return false;
			}
			try {
				float longitude = Float.parseFloat(fieldValue);
				if (longitude > 180 || longitude < -180) {
					errorMessage.addError(fieldName, fieldHeading + " should be between -180 and 180");
					return false;
				}
			} catch (Exception e) {
				errorMessage.addError(fieldName, fieldHeading + " should be a decimal numeric value");
				return false;
			}
		}
		return true;
	}

	public static boolean validateUTCOffsetField(String fieldName,
			String fieldHeading, String fieldValue,
			ValidationErrors errorMessage) {
		if (!isNullOrEmpty(fieldValue)) {
			float utcOffset = Float.parseFloat(fieldValue);
			if (utcOffset > 14 || utcOffset < -12) {
				errorMessage.addError(fieldName, fieldHeading
						+ " should be between -12 and 14");
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("static-access")
	public static boolean validateNumericField(boolean isNecessary,
			String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage, int numType, double minValue,
			double maxValue, boolean isNegativeValueAllowed) {

		System.out.println("Value is: " + fieldValue);
		if (isNecessary && genericValidator.isBlankOrNull(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}

		if (!isNecessary && fieldValue.equals(EMPTY_SPACE)) {
			return TRUE;
		}

		switch (numType) {
		case NUMBER_TYPE_SHORT:
			return validateShort(fieldHeading, fieldName, fieldValue,
					errorMessage, minValue, maxValue, isNegativeValueAllowed);

		case NUMBER_TYPE_INTEGER:
			return validateInteger(fieldHeading, fieldName, fieldValue,
					errorMessage, minValue, maxValue, isNegativeValueAllowed);

		case NUMBER_TYPE_LONG:
			return validateLong(fieldHeading, fieldName, fieldValue,
					errorMessage, minValue, maxValue, isNegativeValueAllowed);

		case NUMBER_TYPE_FLOAT:
			return validateFloat(fieldHeading, fieldName, fieldValue,
					errorMessage, minValue, maxValue, isNegativeValueAllowed);

		case NUMBER_TYPE_DOUBLE:
			return validateDouble(fieldHeading, fieldName, fieldValue,
					errorMessage, minValue, maxValue, isNegativeValueAllowed);

		default: {
			errorMessage.addError(fieldName, "Invalid validation request for "
					+ fieldHeading + ", possibly a bug in the application");

			return FALSE;
		}
		}
	}

	public static boolean validateNumericField(boolean isNecessary,
			String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage, int numType,
			boolean isNegativeValueAllowed) {
		return validateNumericField(isNecessary, fieldHeading, fieldName,
				fieldValue, errorMessage, numType, 0, 0, isNegativeValueAllowed);
	}

	protected static boolean validateShort(String fieldHeading, String fieldName,
			String fieldValue, ValidationErrors errorMessage, double minValue,
			double maxValue, boolean isNegativeValueAllowed) {
		try {
			short num = Short.parseShort(fieldValue);

			if ((maxValue != ZERO)
					&& !shortValidator.maxValue(
							shortValidator.validate(fieldValue), maxValue)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be larger than " + (short) maxValue);
				return FALSE;
			}

			if ((minValue != ZERO)
					&& !shortValidator.minValue(
							shortValidator.validate(fieldValue), maxValue)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be smaller than " + (short) minValue);
				return FALSE;
			}

			if (!isNegativeValueAllowed && (num < ZERO)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be negative");

				return FALSE;
			}
		} catch (NumberFormatException e) {
			if (e.getMessage().contains("Value out of range")) {
				errorMessage
						.addError(
								fieldName,
								"Value out of range for "
										+ fieldHeading
										+ ", Only a non-floating point numeric value less than "
										+ Short.MAX_VALUE + " is permitted!");
			} else {
				errorMessage.addError(fieldName,
						"Only a non-floating point numeric value is permitted for "
								+ fieldHeading);
			}
			return FALSE;
		}
		return TRUE;
	}

	protected static boolean validateInteger(String fieldHeading,
			String fieldName, String fieldValue, ValidationErrors errorMessage,
			double minValue, double maxValue, boolean isNegativeValueAllowed) {
		try {
			int num = Integer.parseInt(fieldValue);

			if ((maxValue != ZERO) && !integerValidator.maxValue(num, maxValue)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be larger than " + (int) maxValue);
				return FALSE;
			}

			if ((minValue != ZERO) && !integerValidator.minValue(num, minValue)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be smaller than " + (int) minValue);
				return FALSE;
			}

			if (!isNegativeValueAllowed && (num < ZERO)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be negative");
				return FALSE;
			}
		} catch (NumberFormatException e) {
			if (e.getMessage().contains("Value out of range")) {
				errorMessage
						.addError(
								fieldName,
								"Value out of range for "
										+ fieldHeading
										+ ", Only a non-floating point numeric value less than "
										+ Integer.MAX_VALUE + " is permitted!");
			} else {
				errorMessage.addError(fieldName,
						"Only a non-floating point numeric value is permitted for "
								+ fieldHeading);
			}

			return FALSE;
		}

		return TRUE;
	}

	protected static boolean validateLong(String fieldHeading, String fieldName,
			String fieldValue, ValidationErrors errorMessage, double minValue,
			double maxValue, boolean isNegativeValueAllowed) {
		try {
			long num = Long.parseLong(fieldValue);

			if ((maxValue != ZERO) && !longValidator.maxValue(num, maxValue)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be larger than " + (long) maxValue);

				return FALSE;
			}

			if ((minValue != ZERO) && !longValidator.minValue(num, minValue)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be smaller than " + (long) minValue);

				return FALSE;
			}

			if (!isNegativeValueAllowed && (num < ZERO)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be negative");

				return FALSE;
			}
		} catch (NumberFormatException e) {
			if (e.getMessage().contains("Value out of range")) {
				errorMessage
						.addError(
								fieldName,
								"Value out of range for "
										+ fieldHeading
										+ ", Only a non-floating point numeric value less than "
										+ Long.MAX_VALUE + " is permitted!");
			} else {
				errorMessage.addError(fieldName,
						"Only a non-floating point numeric value is permitted for "
								+ fieldHeading);
			}

			return FALSE;
		}

		return TRUE;
	}

	protected static boolean validateFloat(String fieldHeading, String fieldName,
			String fieldValue, ValidationErrors errorMessage, double minValue,
			double maxValue, boolean isNegativeValueAllowed) {
		try {
			float num = Float.parseFloat(fieldValue);

			if ((maxValue != ZERO) && !floatValidator.maxValue(num, maxValue)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be larger than " + maxValue);
				return FALSE;
			}

			if ((minValue != ZERO) && !floatValidator.minValue(num, minValue)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be smaller than " + minValue);
				return FALSE;
			}

			if (!isNegativeValueAllowed && (num < ZERO)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be negative");

				return FALSE;
			}
		} catch (NumberFormatException e) {
			if (e.getMessage().contains("Value out of range")) {
				errorMessage.addError(fieldName, "Value out of range for "
						+ fieldHeading
						+ ", Only a floating point numeric value less than "
						+ Float.MAX_VALUE + " is permitted!");
			} else {
				errorMessage.addError(fieldName,
						"Only a floating point numeric value is permitted for "
								+ fieldHeading);
			}

			return FALSE;
		}

		return TRUE;
	}

	protected static boolean validateDouble(String fieldHeading,
			String fieldName, String fieldValue, ValidationErrors errorMessage,
			double minValue, double maxValue, boolean isNegativeValueAllowed) {
		try {
			double num = Double.parseDouble(fieldValue);

			if ((maxValue != ZERO) && !doubleValidator.maxValue(num, maxValue)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be larger than " + maxValue);
				return FALSE;
			}

			if ((minValue != ZERO) && !doubleValidator.minValue(num, minValue)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be smaller than " + minValue);
				return FALSE;
			}

			if (!isNegativeValueAllowed && (num < ZERO)) {
				errorMessage.addError(fieldName, "Value of " + fieldHeading
						+ " cannot be negative");

				return FALSE;
			}
		} catch (NumberFormatException e) {
			if (e.getMessage().contains("Value out of range")) {
				errorMessage.addError(fieldName, "Value out of range for "
						+ fieldHeading
						+ ", Only a floating point numeric value less than "
						+ Double.MAX_VALUE + " is permitted!");
			} else {
				errorMessage.addError(fieldName,
						"Only a floating point numeric value is permitted for "
								+ fieldHeading);
			}

			return FALSE;
		}

		return TRUE;
	}

	protected static boolean validateDate(int year, int month, int date,
			String dateTimeHeading, String fieldName,
			ValidationErrors errorMessage) {
		if (month > 11) {
			errorMessage.addError(fieldName, "Invalid " + dateTimeHeading
					+ "; " + " Invalid Month, months cann't be more than 12 ");

			return false;
		}

		if (date > 31) {
			errorMessage.addError(fieldName, "Invalid " + dateTimeHeading
					+ "; " + " Invalid Date, date can't be greater than 31st ");

			return false;
		}

		// check if year is between 1900 and 9999
		if ((year > 9999) || (year < 1900)) {
			errorMessage.addError(fieldName, "Invalid " + dateTimeHeading
					+ " Year; year value must be between 1900 and 9999");

			return false;
		} else {
			// checks for the month of February
			// if Leap year
			if ((year % 4) == 0) {
				if (month == 1) {
					if (date > 29) {
						errorMessage.addError(fieldName, "Invalid "
								+ dateTimeHeading + "; February can't have "
								+ date + ((date < 31) ? "th" : "st") + " day");

						return false;
					}
				}
			} // if not Leap year
			else {
				if (month == 1) {
					if (date > 28) {
						errorMessage.addError(fieldName, "Invalid "
								+ dateTimeHeading + "; February can't have "
								+ date + ((date < 31) ? "th" : "st") + " day");

						return false;
					}
				}
			}

			// checks for other 30 dayed months
			if ((month == 3) || (month == 5) || (month == 8) || (month == 10)) {
				if (date > 30) {
					errorMessage.addError(fieldName, "Invalid "
							+ dateTimeHeading + "; " + monthNames[month]
							+ " can't have " + date + "st day");

					return false;
				}
			}
		}

		return true;
	}

	public static boolean validateDate(boolean isNecessary,
			String fieldHeading, String fieldName, Date fieldValue,
			ValidationErrors errorMessage) {
		if (isNecessary && fieldValue == null) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}

		return TRUE;
	}

	public static boolean validateDate(boolean isNecessary, String fieldHeading, String fieldName, String fieldValue, ValidationErrors errorMessage) {
		if (isNecessary && isNullOrEmpty(fieldValue.toString())) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}

		try {
			if (Util.isNotNullAndEmpty(fieldValue.toString())) {
				Date date = DateUtil.parseDateUS(fieldValue);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				return TRUE;
			}
			return false;
		} catch (Exception e) {
			errorMessage.addError(fieldName, "Invalid value of " + fieldHeading);
			return FALSE;
		}
	}

	public static String convertDatetoString(java.util.Date dateGet) {
		String dateString = "";
		if (dateGet != null) {
			dateString = DateUtil.formatDateUS(dateGet);
		}
		return dateString;
	}

	public static boolean validatePhoneNumber(String phoneNumber) {
		String text = phoneNumber;
		String searchPattern = "\\(\\d\\d\\d\\)\\d\\d\\d-\\d\\d\\d\\d";
		// example: (123)-456-789

		Pattern pattern = Pattern.compile(searchPattern);
		Matcher matcher = pattern.matcher(text);

		return matcher.matches();
	}

	public static boolean validateZipCode(String zipCode) {
		String text = zipCode;
		String searchPattern = "\\d\\d\\d\\d\\d(-\\d\\d\\d\\d)?";
		// example: (123)-456-789

		Pattern pattern = Pattern.compile(searchPattern);
		Matcher matcher = pattern.matcher(text);

		return matcher.matches();
	}

	public static boolean validateZipCodeField(boolean isNecessary,
			String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage) {

		if (isNecessary && isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty for cities in selected country.");
			return FALSE;
		}

		if (!isNecessary && fieldValue.equals(EMPTY_SPACE)) {
			return TRUE;
		}

		if (!validateZipCode(fieldValue)) {
			errorMessage
					.addError(
							fieldName,
							"Invalid value of "
									+ fieldHeading
									+ ". Valid format is ddddd or ddddd-dddd. E.g., 12345, 12345-6789");
			return FALSE;
		}

		return true;
	}

	public static boolean validatePhoneNumberField(boolean isNecessary,
			String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage) {

		if (isNecessary && isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}

		if (!isNecessary && fieldValue.equals(EMPTY_SPACE)) {
			return TRUE;
		}

		if (!validatePhoneNumber(fieldValue)) {
			errorMessage.addError(fieldName, "Invalid value of " + fieldHeading
					+ ". Valid format is (ddd)ddd-dddd. E.g., (123)456-7890");
			return FALSE;
		}

		return true;
	}

	public static boolean validateCommEmail(String email) {
		String regex = "^['_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		return validateByRegex(email, regex);
	}

	public static boolean validateEmail(String email) {
		String regex = "^((['a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+([;.]((['a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+)*$";
		return validateByRegex(email, regex);
	}

	public static boolean validateByRegex(String fieldValue, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fieldValue);

		return matcher.matches();
	}

	public static boolean validateByRegex(boolean isNecessary,
			String fieldValue, String regex) {
		if (Util.isNullOrEmpty(fieldValue)) {
			if (isNecessary) {
				return false;
			} else {
				return true;
			}

		}

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fieldValue);

		return matcher.matches();
	}

	public static boolean validateEmailField(boolean isNecessary,
			String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage, int maxLength) {

		if (isNecessary && isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}

		if (!isNecessary && fieldValue.equals(EMPTY_SPACE)) {
			return TRUE;
		}

		if (!validateEmail(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading
					+ " contains an invalid email address");
			return FALSE;
		}

		return TRUE;
	}

	public static boolean validateMultipleEmailField(boolean isNecessary,
			String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage, int maxLength) {

		if (isNecessary && isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}

		if (!isNecessary && fieldValue.equals(EMPTY_SPACE)) {
			return TRUE;
		}

		String[] emails = fieldValue.split(",");
		if (emails.length > 0) {
			for (int i = 0; i < emails.length; i++) {
				if (!validateEmail(emails[i])) {
					errorMessage.addError(fieldName, fieldHeading
							+ " contains an invalid email address");
					return FALSE;
				}
			}
		} else if (!validateEmail(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading
					+ " contains an invalid email address");
			return FALSE;
		}

		return TRUE;
	}

	/**
	 *
	 * The isNumber() method returns true if the given string is a number.
	 * Otherwise it returns false.
	 *
	 * @param string
	 *            Specifies the element that is to be checked for number type
	 * @return Returns true if the given string is a number. Otherwise it
	 *         returns false.
	 */
	@SuppressWarnings("static-access")
	public static boolean isNumber(String string) {
		boolean flag = false;
		if (!genericValidator.isBlankOrNull(string)) {
			try {
				Double.parseDouble(string);
				flag = true;
			} catch (NumberFormatException nfe) {
			}
		}
		return flag;
	}

	public static Boolean validatePassword(String oldPassword, String password, String confirmPassword, ValidationErrors validationErrors) {

		validateTextField(true, "Old Password", "OldPassword", oldPassword, validationErrors, 100);

		validateTextField(true, "New Password", "NewPassword", password, validationErrors, 100);

		validateTextField(true, "Confirm Password", "ConfirmPassword", confirmPassword, validationErrors, 100);

		if (!validationErrors.getAnyError()) {

			if (!password.equals(confirmPassword)) {
				validationErrors.addError("PasswordMisMatch", "Password Mismatch");
			} else {
				if (password != null && password.length() < 8) {
					validationErrors.addError("NewPassword", "Enter atleast 8 characters password");
				} else {
					String regex = "^[a-zA-Z0-9_]+$";
					Boolean isValidated = validateByRegex(password, regex);
					if (!isValidated) {
						validationErrors.addError("NewPassword", "Password Must Contain only Alphanumeric values");
					}
				}
			}

			if (Util.isNotNullAndEmpty(oldPassword) && password.equals(oldPassword)) {
				validationErrors.addError("NewPassword", "New Password must be different from current password");
			}
		}

		return !validationErrors.getAnyError();
	}

	public static Boolean validateEmailDomain(String contactEmailAddress, String accountEmailDomain){
		String[] accountEmailDomains = accountEmailDomain.split(",");
		String[] contactEmailDomain = contactEmailAddress.split("@");
		if (contactEmailDomain.length < 2) {
			return false;
		} else {
			for (String emailDomain : accountEmailDomains) {
				if (emailDomain.contains("*")) {
					emailDomain = emailDomain.replace("*.", "");
					if (contactEmailDomain[1].toLowerCase().endsWith(emailDomain)) {
						return true;
					} else {
						continue;
					}
				} else if (emailDomain.trim().equalsIgnoreCase(contactEmailDomain[1])) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean validateAlphaNumericTextField(boolean isNecessary,
			String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage) {
		if (isNecessary && Util.isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}

		String regex = "^[a-zA-Z0-9]+$";
		Boolean isValidated = ValidationUtil.validateByRegex(fieldValue, regex);
		if (!isValidated) {
			errorMessage.addError(fieldHeading, "Only alphanumeric values are allowed for " + fieldName + ".");
		}

		return isValidated;
	}

	public static boolean validateAlphaNumericTextField(boolean isNecessary,
			String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage, int maxLength) {
		if (isNecessary && Util.isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}

		String regex = "^[a-zA-Z0-9]+$";
		Boolean isValidated = ValidationUtil.validateByRegex(fieldValue, regex);
		if (!isValidated) {
			errorMessage.addError(fieldHeading, "Only alphanumeric values are allowed for " + fieldName + ".");
		} else if (isValidated && fieldValue.length() > maxLength) {
			errorMessage.addError(fieldHeading + " Length", "Length of " + fieldName + " can not be greater than " + maxLength + ".");
		}

		return isValidated;
	}

	public static boolean validateAlphaNumericDashSeparatedField(boolean isNecessary,
			String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage, int maxLength) {
		if (isNecessary && Util.isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}

		boolean isValidated = true;
		if (Util.isNotNullAndEmpty(fieldValue)) {
			String regex = "^[a-zA-Z0-9]+[-]{0,}[a-zA-Z0-9]+$";
			isValidated = ValidationUtil.validateByRegex(fieldValue, regex);
			if (!isValidated) {
				errorMessage.addError(fieldHeading, "Only alphanumeric values with optional dash in between are allowed for " + fieldName + ".");
			} else if (isValidated && fieldValue.length() > maxLength) {
				errorMessage.addError(fieldHeading + " Length", "Length of " + fieldName + " can not be greater than " + maxLength + ".");
			}
		}

		return isValidated;
	}

	public static boolean validateDialInCodeField(boolean isNecessary,
			String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage) {
		int dialInCodeLength = 10;
		if(ValidationUtil.validateAlphaNumericTextField(isNecessary, fieldHeading, fieldName, fieldValue, errorMessage)
				&& Util.isNotNullAndEmpty(fieldValue)
				&& fieldValue.length() <= dialInCodeLength) {
			return true;
		} else if (fieldValue.length() > dialInCodeLength) {
			errorMessage.addError(fieldHeading + " Length", "Length of " + fieldName + " can not be greater than " + dialInCodeLength + ".");
		}

		return false;
	}

	public static boolean validatePhoneNumber(boolean isNecessary, String fieldHeading, String fieldName, String phoneNumber, ValidationErrors validationErrors) {
		boolean isPhoneNumberValidated = ValidationUtil.validateTextField(isNecessary, fieldHeading, fieldName, phoneNumber, validationErrors);

		if (isPhoneNumberValidated && Util.isNotNullAndEmpty(phoneNumber)) {
			if(phoneNumber.contains(")")){
				String[] countryCodeAndPhoneNumber = phoneNumber.split("\\)");

				if(countryCodeAndPhoneNumber != null && countryCodeAndPhoneNumber.length == 1 && !isNecessary) {
					validationErrors.addError("OnlyCountryCode", "Number contains only country code");
					return TRUE;
				}

				//check country code exists
				Boolean countryCodeExists = ValidationUtil.validateByRegex(isNecessary,countryCodeAndPhoneNumber[0], COUNTRY_CODE_FORMAT);
				if(!countryCodeExists){
					validationErrors.addError(fieldName, "Please select a valid country code from the dropdown.");
					return FALSE;
				}
				//check if phone number is contains invalid characters.
				if (countryCodeAndPhoneNumber.length == 2) {
					Boolean mobileNumberValidate = ValidationUtil.validateByRegex(isNecessary,countryCodeAndPhoneNumber[1], PHONE_NUMBER_CHARACTER_SET);
					if(!mobileNumberValidate){
						validationErrors.addError(fieldName, " Only digit, spaces, hyphens and periods are allowed.");
					}
					return mobileNumberValidate;
				}
				else if (countryCodeAndPhoneNumber.length > 2) {
					validationErrors.addError(fieldName, " Only digit, spaces, hyphens and periods are allowed.");
					return FALSE;
				}
				else if (isNecessary) {
					validationErrors.addError(fieldName, fieldHeading + " cannot be empty");
					return FALSE;
				}
			}
			Boolean mobileNumberValidate = ValidationUtil.validateByRegex(isNecessary,phoneNumber, PHONE_NUMBER_CHARACTER_SET);
			if(!mobileNumberValidate){
				validationErrors.addError(fieldName, " Only digit, spaces, hyphens and periods are allowed.");
			}
			Boolean countryCodeExists = ValidationUtil.validateByRegex(isNecessary,phoneNumber, COUNTRY_CODE_FORMAT);
			if(!countryCodeExists){
				validationErrors.addError(fieldName, "Please select a valid country code from the dropdown.");
				return FALSE;
			}
		}
		return isPhoneNumberValidated;
	}

	public static boolean validateCreditCardNumber(String creditCardNumber) {

		// check whether input string is null or empty
		if (Util.isNullOrEmpty(creditCardNumber) || creditCardNumber.length() < 13 || creditCardNumber.length() > 16) {
			return false;
		}

		// Check for Credit Card Type i.e. if firstDigit = 3, 4, 5,6 then CreditCardType is American Express, Visa, MasterCard
		int firstDigit = Character.digit(creditCardNumber.charAt(0), 10);

		if(firstDigit < 3 || firstDigit > 7) {
			return false;
		}

		int s1 = 0, s2 = 0;
		String reversedCreditCardNumber = new StringBuffer(creditCardNumber).reverse().toString();

		for (int i = 0; i < reversedCreditCardNumber.length(); i++) {
			int digit = Character.digit(reversedCreditCardNumber.charAt(i), 10);

			if (i % 2 == 0) {// this is for odd digits, they are 1-indexed in the algorithm
				s1 += digit;
			} else {// add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
				s2 += 2 * digit;
				if (digit >= 5) {
					s2 -= 9;
				}
			}
		}

		return (s1 + s2) % 10 == 0;
	}

	public static boolean isDate(String dateString, boolean monthOnly) {
		boolean isDate = false;

		if(monthOnly) {
			isDate = dateString.matches(MONTH_PATTERN);
		} else {
			isDate = dateString.matches(DATE_PATTERN);
		}

		return isDate;

	}
	
	@SuppressWarnings("static-access")
	public static boolean validateNameFieldWithRegx(boolean isNecessary, String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage, int maxLength) {

		if (isNecessary && Util.isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}
		if (Util.isNotNullAndEmpty(fieldValue)) {
			Pattern p = Pattern.compile("[*$&%_+~^,<>{}\\[\\]\\(\\):;=?@#|]", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(fieldValue);

			if (m.find()) {
				errorMessage.addError(fieldName, fieldHeading + " contains invalid character(s)");
				return FALSE;
			}

			p = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
			m = p.matcher(fieldValue);

			if (!m.find()) {
				errorMessage.addError(fieldName, fieldHeading + " should contain at least one alphabet");
				return FALSE;
			}

			fieldValue.contains(DASHES);

			if (isNecessary && !genericValidator.minLength(fieldValue, 2)) {
				errorMessage.addError(fieldName, fieldHeading + " cannot be shorter than 2 characters");
				return FALSE;
			}

			if (!genericValidator.maxLength(fieldValue, maxLength)) {
				errorMessage.addError(fieldName, fieldHeading + " cannot be longer than " + maxLength + (maxLength == 1 ? " character" : " characters"));
				return FALSE;
			}
		}
		return TRUE;
	}
	
	@SuppressWarnings("static-access")
	public static boolean validateNameCheck(boolean isNecessary, String fieldHeading, String fieldName, String fieldValue,
			ValidationErrors errorMessage, int maxLength) {
		
		if (isNecessary && Util.isNullOrEmpty(fieldValue)) {
			errorMessage.addError(fieldName, fieldHeading + " cannot be empty");
			return FALSE;
		}
		
		if (Util.isNotNullAndEmpty(fieldValue)) {
			Pattern p = Pattern.compile("^[\\x00-\\x7F]{1,}$", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(fieldValue);
			
			if (!m.find()) {
				errorMessage.addError(fieldName, fieldHeading + " must contain English alphabets.");
				return FALSE;
			}
			
			p = Pattern.compile("[*$&%_+~^,<>{}\\[\\]\\(\\):;=?@#|]", Pattern.CASE_INSENSITIVE);
			m = p.matcher(fieldValue);

			if (m.find()) {
				errorMessage.addError(fieldName, fieldHeading + " contains invalid character(s)");
				return FALSE;
			}

			p = Pattern.compile("^[A-Za-z]", Pattern.CASE_INSENSITIVE);
			m = p.matcher(fieldValue);

			if (!m.find()) {
				errorMessage.addError(fieldName, "First character should be an English alphabet.");
				return FALSE;
			}

			fieldValue.contains(DASHES);

			if (isNecessary && !genericValidator.minLength(fieldValue, 2)) {
				errorMessage.addError(fieldName, fieldHeading + " must be between 2 - 45 characters.");
				return FALSE;
			}

			if (!genericValidator.maxLength(fieldValue, maxLength)) {
				errorMessage.addError(fieldName, fieldHeading + " must be between 2 - 45 characters.");
				return FALSE;
			}
		}
		return TRUE;
	}

}
