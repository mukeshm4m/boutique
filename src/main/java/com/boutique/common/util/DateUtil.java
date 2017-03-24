package com.boutique.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class DateUtil {
	/**
	 * Default private constructor.
	 */
	private DateUtil() {

	}

	private static final int HOURS_IN_DAY = 24;
	private static final int MINUTES_IN_HOUR = 60;
	private static final int SECONDS_IN_MINUTE = 60;
	private static final int MILLI_SECONDS_IN_SECOND = 1000;
	private static final Double ONE_DAY_IN_SECONDS = 86400d;

	public static String dateAndTimeOnlyPattern = "MM/dd/yyyy k:mm";
	public static String timeOnlyPattern = "k:mm";
	public static String timePattern = "hh:mm:ss a";

	public static String mobilePattern = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Pattern for including in file names.
	 */
	public static String fileNameDatePattern = "MMddyyyy";
	public static String fileNameDatePatternIEReport = "MMddyyyy-HHmm";
	/**
	 * Pattern for date formating only.
	 */
	public static String dateOnlyPatternYearMonthDay = "yyyy-MM-dd";

	public static String usDateAndTimeOnlyPattern = "MM/dd/yyyy HH:mm";

	/**
	 * This pattern to show Date and Time everywhere on UI 
	 * (proposed By Danial Richards) Sun Jul 22, 2012 23:00
	 */
	private static String grDateTimePattern = "EEE MMM dd, yyyy HH:mm";

	public static String generalDatePatternStoredInDB = "yyyy-MM-dd HH:mm:ss";
	public static final String EXCEL_IMPORT_DATE_FORMAT = "dd-MMM-yyyy";
	
	/**
	 * This pattern is to show Date on ui everywhere. Sun Jul 22, 2012
	 */
	private static String grDatePattern = "EEE MMM dd, yyyy";

	/**
	 * This pattern is used to display date in user friendly format.
	 */
	private static String generalDatePattern = "EEEE, MMMM dd, yyyy";

	/**
	 * This pattern is used to display date in user friendly short format.
	 */
	private static String generalDateShortPattern = "EE, MMM dd, yyyy";
	
	private static String generalDatePatternWithoutDay = "MMMM dd, yyyy";

	private static String generalDateShortPattern2 = "MMM dd, yyyy";
	private static String generalDateShortPattern3 = "d MMM, yyyy";

	private static String generalDateTimeShortPattern = "EE, MMM dd, yyyy, hh:mm a";

	private static String commDatePattern = "EEEE, MMMM dd, yyyy hh:mm:ss a";

	private static String generalDateTimeShortPatternNoComma = "EE MMM dd, yyyy HH:mm";
	
	/**
	 * Pattern for date formating only.
	 */
	public static String DateOnlyPattern = "MM/dd/yyyy";
	public static String TimeOnlyPattern = "hh:mm aa";

	public static String dateOnlyPattern = "MM/dd/yyyy";

	private static String datePattern = "MM/dd/yyyy k:mm:ss";
	private static String jodaPattern = "yyyy-MM-dd'T'HH:mm:ss";
	public static String UK_FORMAT = "dd/MM/yyyy";

	private static final SimpleDateFormat standardFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat ukFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat usFormat = new SimpleDateFormat("MM/dd/yyyy");
	private static final SimpleDateFormat mySqlDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm z");
	private static final SimpleDateFormat jodaFormat = new SimpleDateFormat(jodaPattern);
	private static final SimpleDateFormat hoursOnly = new SimpleDateFormat("hh");
	private static final SimpleDateFormat minOnly = new SimpleDateFormat("mm");

	private static final String[] dateFormats = {
		"hh:mm:ss a",
		"hh:mm aa",
		"k:mm",
		"yyyy-MM-dd'T'HH:mm:ss",
		"yyyy-MM-dd HH:mm:ss",
		"yyyy-MM-dd",
		"dd-MMM-yy",
		"EE, MMM dd, yyyy, hh:mm a",
		"EE MMM dd, yyyy HH:mm",
		"EE, MMM dd, yyyy",
		"EEE MMM dd, yyyy HH:mm",
		"EEE MMM dd, yyyy",
		"EEEE, MMMM dd, yyyy hh:mm:ss a",
		"EEEE, MMMM dd, yyyy",
		"MMM dd, yyyy",
		"M/d/yy h:m a",
		"M/d/yy h:m",
		"M/d/yy",
		"MM/dd/yyyy hh:mm aa",
		"MM/dd/yyyy hh:mmaa",
		"MM/dd/yyyy HH:mm",
		"MM/dd/yyyy k:mm:ss",
		"MM/dd/yyyy k:mm",
		"MM/dd/yyyy",
		"dd-MMM-yyyy hh:mmaa",
		"dd-MMM-yyyy HH:mm",
		"dd-MMM-yyyy",
		"d MMM, yyyy",
		"MMddyyyy"
	};
	
	private static final String[] yearPatterns = { 
		"[0-9]{4}",
		"[\\d]{2}/[\\d]{2}/[\\d]{2}",
		"[\\d]{1}/[\\d]{2}/[\\d]{2}",
		"[\\d]{1}/[\\d]{1}/[\\d]{2}",
		"[\\d]{1,2}-[A-Za-z]{3}-[\\d]{2}"
	};

	/**
	 * This method formats date with given pattern.
	 * 
	 * @param date
	 *            Specifies date to be formated.
	 * @param datePattern
	 *            Specifies format pattern.
	 * @return Formatted date.
	 */
	public static String formatDate(Date date, String datePattern) {
		SimpleDateFormat customFormat = new SimpleDateFormat(datePattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * This method formats date with yyyy-MM-dd HH:mm:ss.
	 * 
	 * @param date
	 *            Specifies date to be formatted.
	 * @return Formatted date.
	 */
	public static String formatDateStoredInDB(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(
				generalDatePatternStoredInDB);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}
	
	
	/**
	 * This method formats date with yyyy-MM-dd HH:mm:ss.
	 * 
	 * @param date
	 *            Specifies date to be formatted.
	 * @return Formatted date.
	 */
	public static String formatDateOnlyPatternYearMonthDay(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(
				dateOnlyPatternYearMonthDay);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}
	

	/**
	 * This method formats with GMT time zone ant pattern dd MMMM yyyy, HH:MM z.
	 * 
	 * @param date
	 *            Specifies date to be formatted.
	 * @return Formatted date.
	 */
	public static String formatDateInGMT(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(
				"dd MMMM yyyy, HH:mm z");
		customFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * This method formats date with pattern EEE MMM dd, yyyy HH:mm.
	 * 
	 * @param date
	 *            Specifies date to be formatted.
	 * @return Formatted date.
	 */
	public static String getGRDateTime(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(grDateTimePattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	
	public static String getDateAndTimeOnly(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(dateAndTimeOnlyPattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}
	/**
	 * Return Date in the format of MM/dd/yyyy k:mm UTC/GMT +5
	 */
	public static String getDateAndTimeOnlyWithTimeZone(Date date, Date utcDate) {
		return getDateAndTimeOnlyWithTimeZone(date, utcDate, true);
	}

	/**
	 * Return Date in the format of MM/dd/yyyy k:mm UTC/GMT +5
	 */
	public static String getDateAndTimeOnlyWithTimeZone(Date date, Date utcDate, boolean includeOffsetStr) {
		if (date == null || utcDate == null) {
			return "";
		}

		SimpleDateFormat customFormat = new SimpleDateFormat(dateAndTimeOnlyPattern);
		customFormat.setLenient(false);

		int offset = (int) (diffInSeconds(date, utcDate) / 3600);

		String offsetStr;
		if (offset > 0) {
			offsetStr = "+" + offset;
		} else {
			offsetStr = "" + offset;
		}

		String dateAndTime = customFormat.format(date);
		if (includeOffsetStr) {
			dateAndTime += " - UTC/GMT " + offsetStr;
		}

		return dateAndTime;
	}

	/**
	 * Return time lag in form of a String i.e. x Days, y Hours, z Mins
	 * 
	 * @return
	 */
	public static String getTimeLagBetweenDates(Date start, Date end) {
		if (start == null || end == null) {
			return "";
		}
		long secs = (end.getTime() - start.getTime()) / 1000;

		long days = secs / 86400;
		secs %= 86400;

		long hours = secs / 3600;
		secs %= 3600;

		long mins = secs / 60;

		return days + " Days, " + hours + " Hours, " + mins + " Mins";
	}
	
	
	/**
	 * This method formats date with pattern EEE MMM dd, yyyy.
	 * 
	 * @param date
	 *            Specifies date to be formatted.
	 * @return Formatted date.
	 */
	public static String getGRDate(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(grDatePattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * This method formats and returns date in general format according to the
	 * pattern "EEEE, MMMM dd, yyyy". For example, Monday, April 30, 2012.
	 * 
	 * @param date
	 *            Specifies the given java.util.Date instance.
	 * @return Returns date string formated in general form.
	 */
	public static String getGeneralFormatedDate(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(generalDatePattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}
	
	/**
	 * This method formats and returns date in general format according to the
	 * pattern "MMMM dd, yyyy". For example, April 30, 2012.
	 * 
	 * @param date
	 *            Specifies the given java.util.Date instance.
	 * @return Returns date string formated in general form.
	 */
	public static String getGeneralFormatedDateWithoutDay(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(generalDatePatternWithoutDay);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * This method formats and returns date in general format according to the
	 * pattern "EE, MMM dd, yyyy". For example, Mon, Apr 30, 2012.
	 * 
	 * @param date
	 *            Specifies the given java.util.Date instance.
	 * @return Returns date string formated in general short form.
	 */
	public static String getGeneralShortFormatedDate(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(
				generalDateShortPattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * This method formats date in general form i.e. EE, MMM dd, yyyy, hh:mm a.
	 * 
	 * @param date
	 *            Specifies date to be formatted.
	 * @return Date formatted in general form i.e. EE, MMM dd, yyyy, hh:mm a.
	 */
	public static String getGeneralShortFormatedDateTime(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(
				generalDateTimeShortPattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * This method formats date in general form i.e. EE, MMM dd, yyyy, hh:mm a.
	 * 
	 * @param date
	 *            Specifies date to be formatted.
	 * @return Date formatted in general form i.e. EE, MMM dd, yyyy, hh:mm a.
	 */
	public static String getCommDetailFormatedDateTime(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(commDatePattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * This method formats date in general short form i.e. EE MMM dd, yyyy
	 * HH:mm.
	 * 
	 * @param date
	 *            Specifies date to be formatted.
	 * @return Date formatted in general short form i.e. EE MMM dd, yyyy HH:mm.
	 */
	public static String getGeneralShortFormatedDateTimeNoComma(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(
				generalDateTimeShortPatternNoComma);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * This method formats date only in general form i.e. MMM dd, yyyy.
	 * 
	 * @param date
	 *            Specifies date to be formatted.
	 * @return Date formatted in general form i.e. MMM dd, yyyy.
	 */
	public static String getDateOnlyAsString(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(
				generalDateShortPattern2);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}
	
	public static String getDateOnlyAsStringDMMMYYYY(Date date) {
		if(date == null) {
			return "Unknown Date Time";
		}
		SimpleDateFormat customFormat = new SimpleDateFormat(
				generalDateShortPattern3);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * This method formats date in time form i.e. hh:mm:ss a
	 * 
	 * @param date
	 *            Specifies date to be formatted.
	 * @return Date formatted in given time form i.e. hh:mm:ss a
	 */
	public static String getTimeOnlyAsString(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(timePattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * This method formats the given according pattern "MM/dd/yyyy k:mm:ss"
	 * 
	 * @param date
	 *            the given java.util.Date instance to be formated
	 * @return returns the formated date in string form
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(datePattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * This method formats the given date according pattern "MM/dd/yyyy"
	 * 
	 * @param date
	 *            Specifies date.
	 * @return returns the formated date in string form
	 */
	public static String formatDateOnly(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(dateOnlyPattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * This method formats the current date according pattern
	 * "MM/dd/yyyy k:mm:ss"
	 * 
	 * @return returns the formated date in string form
	 */
	public static String getFormatedDate() {
		SimpleDateFormat customFormat = new SimpleDateFormat(datePattern);
		customFormat.setLenient(false);
		return customFormat.format(new Date());
	}

	/**
	 * The getTimestamp() method converts java.util.Date to java.sql.Timestamp
	 * 
	 * @param date
	 *            the given java.util.Date instance for converting to
	 *            java.sql.Timestamp
	 * @return returns java.sql.Timestamp after converting the given
	 *         java.util.Date instance
	 */
	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * This method creates time stamp from given milli seconds.
	 * 
	 * @param millis
	 *            Specifies milli seconds.
	 * @return Time stamp created from given milli seconds.
	 */
	public static Timestamp getTimestamp(long millis) {
		return getTimestamp(getDateTimeFromMillis(millis));
	}

	/**
	 * The getTimestamp() method converts the current java.util.Date to
	 * java.sql.Timestamp
	 * 
	 * @return returns java.sql.Timestamp after converting the current
	 *         java.util.Date instance
	 */
	public static Timestamp getTimestamp() {
		return getTimestamp(new Date());
	}

	/**
	 * The monthsBetween() method returns the months between two dates
	 * 
	 * @param dateOne
	 *            specifies first date
	 * @param dateTwo
	 *            specifies second date
	 * @return returns the number of months between two dates
	 */
	public static int monthsBetween(Date dateOne, Date dateTwo) {
		Calendar presentCalendar = Calendar.getInstance();
		Calendar pastCalendar = Calendar.getInstance();

		if (dateOne.after(dateTwo)) {
			presentCalendar.setTime(dateOne);
			pastCalendar.setTime(dateTwo);
		} else {
			presentCalendar.setTime(dateTwo);
			pastCalendar.setTime(dateOne);
		}

		int yearsBetween = presentCalendar.get(Calendar.YEAR)
				- pastCalendar.get(Calendar.YEAR);
		int monthsBetween = presentCalendar.get(Calendar.MONTH)
				- pastCalendar.get(Calendar.MONTH);

		return (yearsBetween * 12) + monthsBetween;
	}

	/**
	 * This method returns minutes from date.
	 * 
	 * @param date
	 *            Specifies date.
	 * @return Minutes from date.
	 */
	public static String formatMinuteOnly(Date date) {
		return minOnly.format(date);
	}

	/**
	 * This method returns hours from date.
	 * 
	 * @param date
	 *            Specifies date.
	 * @return Hours from date.
	 */
	public static String formatHourOnly(Date date) {
		return hoursOnly.format(date);
	}

	/**
	 * This method returns date in US format, i.e. MM/dd/yyyy.
	 * 
	 * @param date
	 *            Specifies date.
	 * @return Date in US format, i.e. MM/dd/yyyy.
	 */
	public static String formatDateUS(Date date) {
		if (date == null) {
			return null;
		}
		return usFormat.format(date);
	}

	/**
	 * This method creates date from string value.
	 * 
	 * @param dateString
	 *            Specifies date in string format.
	 * @return Date in US format, i.e. MM/dd/yyyy.
	 * @throws ParseException
	 *             Errors in parsing string for date creation.
	 */
	public static Date parseDateUS(String dateString) throws ParseException {
		usFormat.setLenient(false);
		return usFormat.parse(dateString);
	}

	/**
	 * This method creates date from string value.
	 * 
	 * @param dateString
	 *            Specifies date in string format.
	 * @return Date in MySQL format, i.e. yyyy-MM-dd HH:mm:ss.
	 * @throws ParseException
	 *             Errors in parsing string for date creation.
	 */
	public static Date parseDateMySql(String dateString) throws ParseException {
		return mySqlDateTime.parse(dateString);
	}

	/**
	 * This method returns date in UK format, i.e. dd/MM/yyyy.
	 * 
	 * @param date
	 *            Specifies date.
	 * @return Date in UK format, i.e. dd/MM/yyyy.
	 */
	public static String formatDateUK(Date date) {
		return ukFormat.format(date);
	}

	/**
	 * This method creates date from string value.
	 * 
	 * @param dateString
	 *            Specifies date in string format.
	 * @return Date in US format, i.e. dd/MM/yyyy.
	 * @throws ParseException
	 *             Errors in parsing string for date creation.
	 */
	public static Date parseDateUK(String dateString) throws ParseException {
		return ukFormat.parse(dateString);
	}

	/**
	 * This method returns MySQL date in standard format, i.e. yyyy-MM-dd.
	 * 
	 * @param date
	 *            Specifies date to be formatted.
	 * @return MySQL date in standard format, i.e. yyyy-MM-dd.
	 */
	public static String formatDateMySQL(Date date) {
		return standardFormat.format(date);
	}

	/**
	 * This method creates date in MySQL format from string value.
	 * 
	 * @param dateString
	 *            Specifies date in string format.
	 * @return Date in MySQL format, i.e. yyyy-MM-dd HH:mm:ss.
	 * @throws ParseException
	 *             Errors in parsing string for date creation.
	 */
	public static Date parseDateMySQL(String dateString) throws ParseException {
		return mySqlDateTime.parse(dateString);
	}

	/**
	 * This method returns time only from date object, i.e. hh:mm z.
	 * 
	 * @param date
	 *            Specifies the input date.
	 * @return Time only from date object, i.e. hh:mm z.
	 */
	public static String formatTime(Date date) {
		return timeFormat.format(date);
	}

	/**
	 * This method creates date from its string form.
	 * 
	 * @param dateString
	 *            Specifies date in string form.
	 * @return Date created from its string form.
	 * @throws ParseException
	 *             Errors occurred while creating date from its string form.
	 */
	public static Date parseDate(String dateString) throws ParseException {
		return standardFormat.parse(dateString);
	}

	/**
	 * This method formats date in given format.
	 * 
	 * @param datePattern
	 *            Specifies date format.
	 * @param date
	 *            Specifies date.
	 * @return Formatted date.
	 */
	public static String formatDate(String datePattern, Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(datePattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}

	/**
	 * This method creates date from its string form in given format.
	 * 
	 * @param datePattern
	 *            Specifies date format.
	 * @param dateString
	 *            Specifies date in its string form
	 * @return Date created from its string form in given format.
	 * @throws ParseException
	 *             Errors occurred while parsing date.
	 */
	public static Date parseDate(String datePattern, String dateString)
			throws ParseException {
		SimpleDateFormat customFormat = new SimpleDateFormat(datePattern);
		customFormat.setLenient(false);
		return customFormat.parse(dateString);
	}

	/**
	 * This method creates date from milli seconds.
	 * 
	 * @param millis
	 *            Specifies milli seconds.
	 * @return Date created from milli seconds.
	 */
	public static Date getDateTimeFromMillis(long millis) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);

		return calendar.getTime();
	}

	/**
	 * The lessThan() method compares the first date argument with second date.
	 * It returns true if the first date is less than the second date. Otherwise
	 * it returns false.
	 * 
	 * @param firstDate
	 *            Specifies the first date argument.
	 * @param secondDate
	 *            Specifies the second date argument.
	 * @return Returns true if the first date is less than the second date.
	 *         Otherwise it returns false.
	 */
	public static boolean lessThan(Date firstDate, Date secondDate) {
		firstDate = getDateOnlyFromDateAndTime(firstDate);
		secondDate = getDateOnlyFromDateAndTime(secondDate);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(firstDate);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(secondDate);

		return calendar1.before(calendar2);
	}

	public static long lessThanInDays(Date firstDate, Date secondDate) {

		long diff = 0;
		long days = 0;
		diff = firstDate.getTime() - secondDate.getTime();

		days = diff / (24 * 60 * 60 * 1000);

		return days;
	}

	/**
	 * The lessThanOrEqual() method compares the first date argument with second
	 * date. It returns true if the first date is less than the second date OR
	 * Equal Second Date. Otherwise it returns false.
	 * 
	 * @param firstDate
	 *            Specifies the first date argument.
	 * @param secondDate
	 *            Specifies the second date argument.
	 * @return Returns true if the first date is less than the second date.
	 *         Otherwise it returns false.
	 */

	public static boolean lessThanOrEqual(Date firstDate, Date secondDate) {
		firstDate = getDateOnlyFromDateAndTime(firstDate);
		secondDate = getDateOnlyFromDateAndTime(secondDate);

		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(firstDate);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(secondDate);

		if (calendar1.before(calendar2) || calendar1.equals(calendar2)) {
			return true;
		}

		return false;
	}

	/**
	 * The greaterThan() method compares the first date argument with second
	 * date. It returns true if the first date is greater than the second date.
	 * Otherwise it returns false.
	 * 
	 * @param firstDate
	 *            Specifies the first date argument.
	 * @param secondDate
	 *            Specifies the second date argument.
	 * @return Returns true if the first date is greater than the second date.
	 *         Otherwise it returns false.
	 */
	public static boolean greaterThan(Date firstDate, Date secondDate) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(firstDate);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(secondDate);

		return calendar1.after(calendar2);
	}

	/**
	 * The greaterThanOrEqual() method compares the first date argument with
	 * second date. It returns true if the first date is greater than the second
	 * date OR Equal to seconds Date. Otherwise it returns false.
	 * 
	 * @param firstDate
	 *            Specifies the first date argument.
	 * @param secondDate
	 *            Specifies the second date argument.
	 * @return Returns true if the first date is greater than the second date.
	 *         Otherwise it returns false.
	 */
	public static boolean greaterThanOrEqual(Date firstDate, Date secondDate) {
		secondDate = getDateOnlyFromDateAndTime(secondDate);
		firstDate = getDateOnlyFromDateAndTime(firstDate);

		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(firstDate);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(secondDate);

		if (calendar1.after(calendar2) || calendar1.equals(calendar2)) {
			return true;
		}

		return false;
	}
	
	/**
	 * The equals() method compares the first date argument with second date. It
	 * true if the first date is equal to the second date. Otherwise it returns
	 * false.
	 * 
	 * @param firstDate
	 *            Specifies the first date argument.
	 * @param secondDate
	 *            Specifies the second date argument.
	 * @return Returns true if the first date is equal to the second date.
	 *         Otherwise it returns false.
	 */
	public static boolean equals(Date firstDate, Date secondDate) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(firstDate);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(secondDate);

		return calendar1.equals(calendar2);
	}

	/**
	 * The equals() method compares the first date argument with second date. It
	 * true if the first date is equal to the second date. Otherwise it returns
	 * false. This method remote time information from date object and only compare date
	 * 
	 * @param firstDate
	 *            Specifies the first date argument.
	 * @param secondDate
	 *            Specifies the second date argument.
	 * @return Returns true if the first date is equal to the second date.
	 *         Otherwise it returns false.
	 */
	public static boolean equalsIgnoreTime(Date firstDate, Date secondDate) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(firstDate);
		calendar1.clear(Calendar.HOUR);
		calendar1.clear(Calendar.HOUR_OF_DAY);
		calendar1.clear(Calendar.AM_PM);
		calendar1.clear(Calendar.MINUTE);
		calendar1.clear(Calendar.MILLISECOND);
		calendar1.clear(Calendar.SECOND);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(secondDate);
		calendar2.clear(Calendar.HOUR);
		calendar2.clear(Calendar.HOUR_OF_DAY);
		calendar2.clear(Calendar.AM_PM);
		calendar2.clear(Calendar.MINUTE);
		calendar2.clear(Calendar.MILLISECOND);
		calendar2.clear(Calendar.SECOND);
		
		return calendar1.equals(calendar2);
	}
	
	

	/**
	 * This method checks whether the a date is in date range.
	 * 
	 * @param date
	 *            Specifies the date that needs to checked in date range.
	 * @param fromDate
	 *            Specifies start date of date range.
	 * @param toDate
	 *            Specifies end date of date range.
	 * @return True if a date is in date range. Otherwise returns false.
	 */
	public static boolean isDateBetweenRange(Date date, Date fromDate,
			Date toDate) {

		if (equalsIgnoreTime(date, fromDate) || greaterThan(date, fromDate)
				&& (equalsIgnoreTime(date, toDate) || lessThan(date, toDate))) {
			return true;
		}

		return false;
	}

	/**
	 * The getDateOnlyFromDateAndTime() method returns the date only part from
	 * the given date time object.
	 * 
	 * @param dateTime
	 *            Specifies the date object.
	 * @return Returns the date only part from the given date and time.
	 */
	public static Date getDateOnlyFromDateAndTime(Date dateTime) {
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(dateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, dateCalendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, dateCalendar.get(Calendar.MONTH));
		calendar.set(Calendar.DATE, dateCalendar.get(Calendar.DATE));

		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * This method creates a date object from the given date and time.
	 * 
	 * @param date
	 *            Specifies the date object.
	 * @param time
	 *            Specifies the time string containing hour, minute and second
	 *            values concatenated by full colon .eg. 11:50:40.
	 * @param isAM
	 *            Specifies whether the given time string is in AM or PM period.
	 * @return Date created from the given date and time.
	 */
	public static Date getDateFromDateAndTime(Date date, String time,
			boolean isAM) {
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(date);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, dateCalendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, dateCalendar.get(Calendar.MONTH));
		calendar.set(Calendar.DATE, dateCalendar.get(Calendar.DATE));

		String[] timeParts = time.split(":");
		calendar.set(Calendar.HOUR, Integer.parseInt(timeParts[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
		calendar.set(Calendar.SECOND, Integer.parseInt(timeParts[2]));
		if (isAM) {
			calendar.set(Calendar.AM, 0);
		} else {
			calendar.set(Calendar.PM, 1);
		}

		return calendar.getTime();
	}

	/**
	 * This method creates a date object from the given date and time.
	 * 
	 * @param date
	 *            Specifies the date object.
	 * @param time
	 *            Specifies the time string containing hour and minute values
	 *            concatenated by full colon .eg. 11:50.
	 * @param isAM
	 *            Specifies whether the given time string is in AM or PM period.
	 * @return Date created from the given date and time.
	 */
	public static Date getDateFromDateHourAndMinue(Date date, String time,
			boolean isAM) {
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(date);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, dateCalendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, dateCalendar.get(Calendar.MONTH));
		calendar.set(Calendar.DATE, dateCalendar.get(Calendar.DATE));

		String[] timeParts = time.split(":");
		calendar.set(Calendar.HOUR, Integer.parseInt(timeParts[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
		if (isAM) {
			calendar.set(Calendar.AM_PM, Calendar.AM);
		} else {
			calendar.set(Calendar.AM_PM, Calendar.PM);
		}

		return calendar.getTime();
	}

	/**
	 * The getDateHourAndMinuteOnly() method creates a date object from the
	 * given date with date, hour and minute only.
	 * 
	 * @param date
	 *            Specifies the date object.
	 * @return Returns the date object.
	 */
	public static Date getDateHourAndMinuteOnly(Date date) {
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(date);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, dateCalendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, dateCalendar.get(Calendar.MONTH));
		calendar.set(Calendar.DATE, dateCalendar.get(Calendar.DATE));
		calendar.set(Calendar.HOUR, dateCalendar.get(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, dateCalendar.get(Calendar.MINUTE));
		calendar.set(Calendar.AM_PM, dateCalendar.get(Calendar.AM_PM));

		return calendar.getTime();
	}

	/**
	 * This method returns start date time of the day.
	 * 
	 * @return start date time of the day.
	 */
	public static Date getStartDateTimeOfToday() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR, 12);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.AM_PM, Calendar.AM);

		return c.getTime();
	}

	/**
	 * This method adds given seconds to date.
	 * 
	 * @param startDate
	 *            Specifies date object.
	 * @param seconds
	 *            Specifies seconds to be added to date.
	 * @return Date after adding given seconds.
	 */
	public static Date getDateXSecondsFromStartDate(Date startDate,
			Integer seconds) {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		startCalendar.add(Calendar.SECOND, seconds);

		return startCalendar.getTime();
	}

	/**
	 * This method adds given days to date.
	 * 
	 * @param startDate
	 *            Specifies date object.
	 * @param days
	 *            Specifies days to be added to date.
	 * @return Date after adding given days.
	 */
	public static Date getDateXDaysFromStartDate(Date startDate, Integer days) {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		startCalendar.add(Calendar.DAY_OF_MONTH, days);

		return startCalendar.getTime();
	}

	/**
	 * This method adds given hours to date.
	 * 
	 * @param startDate
	 *            Specifies date object.
	 * @param hours
	 *            Specifies hours to be added to date.
	 * @return Date after adding given hours.
	 */
	public static Date getDateXHoursFromStartDate(Date startDate, int hours) {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		startCalendar.add(Calendar.HOUR_OF_DAY, hours);

		return startCalendar.getTime();
	}


	/**
	 * This method converts seconds into days.
	 * 
	 * @param seconds
	 *            Specifies seconds.
	 * @return Days.
	 */
	public static Integer convertSecondsToDays(Double seconds) {
		if (seconds == null) {
			return null;
		}
		int days = (int) (double) (seconds / ONE_DAY_IN_SECONDS);
		if (days < 0) {
			return -days;
		}

		return days;
	}

	/**
	 * This method converts seconds to hours.
	 * 
	 * @param seconds
	 *            Specifies seconds.
	 * @return Hours created from seconds.
	 */
	public static Integer convertSecondsToHours(Double seconds) {
		if (seconds == null) {
			return null;
		}

		int hours = (int) (double) (seconds / 3600);
		if (hours < 0) {
			return -hours;
		}

		return hours;

	}

	/**
	 * This method returns number of days between two dates.
	 * 
	 * @param startDate
	 *            Specifies start date.
	 * @param endDate
	 *            Specifies end date.
	 * @return Number of days between two dates.
	 */
	public static Integer daysBetween(Calendar startDate, Calendar endDate) {
		Calendar date = (Calendar) startDate.clone();
		Integer daysBetween = 0;
		while (date.before(endDate)) {
			date.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		if (daysBetween == 0) {
			while (endDate.before(date)) {
				endDate.add(Calendar.DAY_OF_MONTH, 1);
				daysBetween--;
			}
		}

		return daysBetween;
	}

	/**
	 * This method returns number of days between two dates.
	 * 
	 * @param startDate
	 *            Specifies start date.
	 * @param endDate
	 *            Specifies end date.
	 * @return Number of days between two dates.
	 */
	public static Integer daysBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return null;
		}

		Calendar start = new GregorianCalendar();
		start.setTime(startDate);

		start.clear(Calendar.HOUR);
		start.clear(Calendar.HOUR_OF_DAY);
		start.clear(Calendar.AM_PM);
		start.clear(Calendar.MINUTE);
		start.clear(Calendar.MILLISECOND);
		start.clear(Calendar.SECOND);
		long smillis = start.getTime().getTime();

		Calendar end = new GregorianCalendar();
		end.setTime(endDate);

		end.clear(Calendar.HOUR);
		end.clear(Calendar.HOUR_OF_DAY);
		end.clear(Calendar.AM_PM);
		end.clear(Calendar.MINUTE);
		end.clear(Calendar.MILLISECOND);
		end.clear(Calendar.SECOND);
		long emillis = end.getTime().getTime();

		float f = (emillis - smillis)
				/ (MILLI_SECONDS_IN_SECOND * SECONDS_IN_MINUTE
						* MINUTES_IN_HOUR * HOURS_IN_DAY);

		return (int) (f);
	}

	/**
	 * This method returns number of seconds between two dates.
	 * 
	 * @param startDate
	 *            Specifies start date.
	 * @param endDate
	 *            Specifies end date.
	 * @return Number of seconds between two dates.
	 */
	public static Double diffInSeconds(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return null;
		}
		return (double) (endDate.getTime() - startDate.getTime()) / 1000;

	}

	/**
	 * This method finds out whether a given date is between two respective
	 * dates.
	 * 
	 * @param actual
	 *            Specifies actual date.
	 * @param start
	 *            Specifies start date.
	 * @param end
	 *            Specifies end date.
	 * @return True if a given date is between two respective dates. Otherwise
	 *         returns false.
	 */
	public static boolean between(Double actual, Double start, Double end) {
		if (actual > start && actual < end) {
			return true;
		}

		return false;
	}

	/**
	 * This method adds the Number of Days to the Given Date and returns New
	 * Date
	 * 
	 * @param daysToAdd
	 *            Specifies number of days to be added to given date.
	 * @param date
	 *            Specifies given date.
	 * @return the Date object after adding the Number of Days to the Given Date
	 */
	public static Date getNewDate(Integer daysToAdd, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, daysToAdd);
		return cal.getTime();
	}

	/**
	 * This method checks two dates for equality.
	 * 
	 * @param firstDate
	 *            Specifies first date.
	 * @param secondDate
	 *            Specifies first date.
	 * @return True if two dates are equal. Otherwise returns false.
	 */
	public static Boolean compareDatesEqualityWithoutTimeStamp(Date firstDate,
			Date secondDate) {
		if (firstDate == null && secondDate == null) {
			return true; // because both are null
		} else if ( (firstDate != null && secondDate == null)
				|| (firstDate == null && secondDate != null) ) {
			return false; // because one is null and second is not null
		}
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (sdf.format(firstDate).equals(sdf.format(secondDate))) {
			return true;
		}

		return false;
	}

	/**
	 * This method calculates the absolute days between two Dates without regard
	 * for time offsets.
	 * 
	 * @param currentDate
	 *            Date two.
	 * @param previousDate
	 *            Date one.
	 * @param considerTimePart
	 *            Specifies whether to consider hours or not.
	 * @return Absolute days between two Dates without regard for time offsets.
	 */

	public static Integer getDaysBetween(Date currentDate, Date previousDate,
			Boolean considerTimePart) {
		if (!considerTimePart) {
			currentDate = parseDateInGivenPattern(currentDate, DateOnlyPattern);
			previousDate = parseDateInGivenPattern(previousDate, DateOnlyPattern);
		}
		//if (currentDate.after(previousDate)) {
			return (int) ((currentDate.getTime() - previousDate.getTime()) / (MILLI_SECONDS_IN_SECOND
					* SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY));
		//} 
//		else {
//			return (int) ((previousDate.getTime() - currentDate.getTime()) / (MILLI_SECONDS_IN_SECOND
//					* SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY));
//		}
		//return null;
	}


	public static Date getDateFromAndroidDate(String androidDateString)
			throws ParseException {
		DateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
		Date date = null;

		date = sdf.parse(androidDateString);

		return date;
	}

	
	public static Date getDateFromMobileDate(String iPhoneDateString) throws ParseException {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		date = sdf.parse(iPhoneDateString);
		return date;
	}
	
	public static String convertLocalDateToUTCString(Date date, String format) throws ParseException {
		SimpleDateFormat customFormat = new SimpleDateFormat(format);
		customFormat.setLenient(false);
		customFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return customFormat.format(date);
	}
	
	public static String formatDateToMobileDateString(Date date) throws ParseException {
		SimpleDateFormat customFormat = new SimpleDateFormat(mobilePattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}
	
	public static String convert24HoursTo12Hours(String time) {
    	if(!time.toLowerCase().contains("am") && !time.toLowerCase().contains("pm")){
    		DateFormat _24HoursFormat = null;
			
    		if (!time.contains(":") && time.length() == 4) {
				_24HoursFormat = new SimpleDateFormat("HHmm");
			} else {
				_24HoursFormat = new SimpleDateFormat("HH:mm");
			}
    		
    		Date d = null;
    		try {
    			d = _24HoursFormat.parse(time);
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		DateFormat _12HoursFormat = new SimpleDateFormat("hh:mm aa");
    		return _12HoursFormat.format(d).toLowerCase(); // "12:18 am"
    	}
    	else{
			return time;
    	}
    }
	
	public static Date parseDateInGivenPattern(Date date, String format){
		try {
			DateFormat sdf = new SimpleDateFormat(format);
			date = (Date) sdf.parse(sdf.format(date));
		} catch (ParseException pe) {
		}
		return date;
	}

	public static boolean compareDateToGivenDateFormat(String date, String dateFormat){
		DateFormat format = new SimpleDateFormat(dateFormat);
		format.setLenient(false);
		try{
			format.parse(date);
		}catch(ParseException e){
			return false;
		}
		return true;
	}
	
	/**
	 * getCurrentDateWithWholeDayTime method will append 23:59:00 time portion
	 * 			with the current date.
	 * @return
	 * 			return current date object with 23:59:00 (time)
	 */
	public static Date getCurrentDateWithWholeDayTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	 /* This method adds given minutes to date.
	 * 
	 * @param date
	 *            Specifies date object.
	 * @param minute
	 *            Specifies minutes to be added to date.
	 * @return Date after adding given minutes.
	 */
	public static Date getDateXMinutesFromGivenDate(Date date,
			Integer minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND,0);

		return calendar.getTime();
	}
	
	public static Boolean isYearExists(String date) {
		// check for 4 consecutive number in given date string
		// i.e 12/2/2013 return true, Nov 14 return false.
		Boolean yearExists = false;
		for (String pattern : yearPatterns) {
			Pattern regex = Pattern.compile(pattern);
			Matcher regexMatcher = regex.matcher(date);
			while (regexMatcher.find()) {
				yearExists = true;
				return yearExists;
			}
		}
		return yearExists;
	}
	
	/**
	 * The getSplitTimeString() method convert miliseconds into a string in this
	 * format 'x days y hrs z min'
	 * 
	 * @param miliseconds
	 * @param calcSecs
	 * @param useGTOperator
	 * @return
	 */
	public static String getSplitTimeString(long miliseconds, boolean calcSecs, boolean useGTOperator) {
		StringBuilder str = new StringBuilder("");
		miliseconds = Math.abs(miliseconds);

		long secs = miliseconds / 1000;

		long days = secs / 86400;
		secs %= 86400;
		if (days != 0) {
			str.append(days + " days ");
		}

		long hours = secs / 3600;
		secs %= 3600;
		if (hours != 0) {
			str.append(hours + " hrs ");
		}

		long mins = secs / 60;
		secs %= 60;
		if (mins != 0) {
			str.append(mins + " min ");
		}

		if (calcSecs) {
			str.append(secs + " sec");
		}

		return str.toString();
	}
	
	/*
	 * As When we get Date from Axis2 WS Calendar object, it gives us Date in
	 * Local Server Timezone So this method is used to subtract the time
	 * returned by Axis2 WS to the one returned by Actual WS
	 */
	public static Date getSqlDateTimeFromAxis2WSCalendar(Calendar cal) {
		TimeZone serverTimezone = TimeZone.getDefault();
		return new Date(cal.getTimeInMillis() - serverTimezone.getRawOffset());
	}
	
	public static boolean validateDateFormat(String dateFormat){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat.trim());
			
			Date currentDate = new Date();
			
			String formatedDate = sdf.format(currentDate);
			if(Util.isNotNullAndEmpty(formatedDate)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		
		return false;
	}
	
	/**
	 * This method formats date with MM/dd/yyyy HH:mm.
	 * 
	 * @param date
	 *            Specifies date to be formatted.
	 * @return Formatted date.
	 */
	public static String formatDateByDateTime(Date date) {
		SimpleDateFormat customFormat = new SimpleDateFormat(
				usDateAndTimeOnlyPattern);
		customFormat.setLenient(false);
		return customFormat.format(date);
	}
	
	/**
	 * formatTimeIfColonNotPresent, checks for number of character in given string and formats string in hh:mm:aa 
	 *  currently works for 0530 and 530P or 530A type input values
	 * @param time
	 * 		String object as time 
	 * @return
	 * 		String object as time with hh:mm aa format
	 */
	public static String formatTimeIfColonNotPresent(String time) {
		if (Boolean.FALSE.equals(time.contains(":")) && time.matches("[0-9]{4}")) {
			String hours = time.substring(0, 2);
			String minutes = time.substring(2, 4);
			time = hours + ":" + minutes;
			DateFormat readFormat = new SimpleDateFormat("hh:mm");
			DateFormat writeFormat = new SimpleDateFormat("hh:mm aa");
			try {
				Date date = readFormat.parse(time);
				if (date != null) {
					time = writeFormat.format(date);
				}
			} catch (Exception e) {
				
			}
		} else if (Boolean.FALSE.equals(time.contains(":")) && time.matches("[\\d]{3}[A-Z]{1,2}")) {
			String hours = time.substring(0, 1);
			String minutes = time.substring(1, 3);
			if("A".equals(time.substring(3, 4))){
				time = hours + ":" + minutes + " AM";
			}else{
				time = hours + ":" + minutes + " PM";
			}
		} else if (Boolean.FALSE.equals(time.contains(":")) && time.matches("[\\d]{4}[A-Z]{1,2}")) {
			String hours = time.substring(0, 2);
			String minutes = time.substring(2, 4);
			if("A".equals(time.substring(4, 5))){
				time = hours + ":" + minutes + " AM";
			}else{
				time = hours + ":" + minutes + " PM";
			}
		}
		return time;
	}
	
	public static Date removeTimeFromDate(Date date) {
		return removeTimeFromDate(date, DateOnlyPattern);
	}
	
	public static Date removeTimeFromDate(Date date, String formatStr) {
		Date dateOnly = null;
		try {
			DateFormat format = new SimpleDateFormat(formatStr);
			String dateOnlyStr = format.format(date);
			dateOnly = format.parse(dateOnlyStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateOnly;
	}
	
	public static String formatDateJodaString(Date date) {
		jodaFormat.setLenient(false);
		return jodaFormat.format(date);
	}

	public static String convertTimeZones(final String fromTimeZoneId,
			final String toTimeZoneId, final String fromDateTime, final String dateFormat) {
		final DateTimeZone fromTimeZone = DateTimeZone.forID(fromTimeZoneId);
		final DateTimeZone toTimeZone = DateTimeZone.forID(toTimeZoneId);
		final DateTime utcDateTime = new DateTime(fromDateTime, fromTimeZone);
		final DateTime toDateTime = utcDateTime.toDateTime(toTimeZone);

		final DateTimeFormatter outputFormatter = DateTimeFormat.forPattern(dateFormat);
		return outputFormatter.print(toDateTime);
	}
	
	/**
	 * convertMillisToHoursMinutesSeconds is used to get HH:MM:SS From Millis
	 * @param millis
	 * @return String HH:MM:SS
	 */
	public static String convertMillisToHoursMinutesSeconds(Long millis) {
		String hoursMinutesSeconds = "00:00:00"; 
		if(millis != null) {
			long hours = TimeUnit.MILLISECONDS.toHours(millis);
			long minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
					- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis));
			long seconds = TimeUnit.MILLISECONDS.toSeconds(millis)
					- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis));
			hoursMinutesSeconds = String.format("%s%02d:%02d:%02d",
					(hours >= 0 ? "+" : "-"),
					(hours >= 0 ? hours : -1 * hours), Math.abs(minutes),
					Math.abs(seconds));
		}
		return hoursMinutesSeconds;
	}
	
	/**
	 * convertMillisToHoursMinutes is used to get HH:MM From Millis
	 * @param millis
	 * @return String HH:MM
	 */
	public static String convertMillisToHoursMinutes(Long millis) {
		String hoursMinutes = "+00:00"; 
		if(millis != null) {
			long hours = TimeUnit.MILLISECONDS.toHours(millis);
			long minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
					- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis));
			hoursMinutes = String.format("%s%02d:%02d",
					(hours >= 0 ? "+" : "-"), Math.abs(hours),
					Math.abs(minutes));
		}
		return hoursMinutes;
	}
	
	public static String parse(String date) {
        if (date != null) {
            for (String parse : dateFormats) {
                SimpleDateFormat sdf = new SimpleDateFormat(parse);
                try {
                    sdf.parse(date);
                    return parse;
                } catch (ParseException e) {
                }
            }
        }
        return generalDatePatternStoredInDB;
    }
	
	public static String[] getDateAndTimeFromDateTime(final String dateTime, final String dateFormat, final String timeFormat) {
		String[] dateAndTime = new String[] { null, null };
		if (Util.isNotNullAndEmpty(Util.trim(dateTime))) {
			Date date = null;
			try {
				date = DateUtil.parseDate(parse(dateTime), dateTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
			SimpleDateFormat timeFormatter = new SimpleDateFormat(timeFormat);

			dateAndTime[0] = dateFormatter.format(date);
			dateAndTime[1] = timeFormatter.format(date);
		}
		return dateAndTime;
	}
	
	public static Date addDaysInDate(Integer daysToAdd, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, daysToAdd);
		return cal.getTime();
	}
	
	public static Date setMonthsInDate(Integer month, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, 0);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	public static Date addmonthsInDate(Integer monthsToAdd, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, monthsToAdd);
		return cal.getTime();
	}

	public static Date addYearsInDate(Integer yearsToAdd, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, yearsToAdd);
		return cal.getTime();
	}
	
	public static Date setYearsInDate(Integer years, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, 0);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}

	public static Date subtractDaysInDate(Integer daysToSubtract, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -daysToSubtract);
		return cal.getTime();
	}
	
	
	public static Calendar getCalendarFromDateString(String dateOfBirth) throws ParseException {
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.dateOnlyPattern);
			cal.setTime(dateFormat.parse(dateOfBirth));
			
			return cal;
	}
	
	public static Calendar getCalendarFromDate(Date date){
	
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	
		return cal;
	}
	
	/**
	 * getCurrentTimestamp returns current datetime in yyyyMMddHHmmss format
	 * 
	 * @return String object
	 */
	public static String getCurrentTimestamp() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	}
	
	/**
	 * This method will take Month, Year in Integer and return the last 
	 * day on Month, mind that Month starts with - i.e 0 = January and Decemeber = 11
	 * @param month
	 * @param year
	 * @return
	 */
	public static String getLastMonthDateforGivenMonthYear(Integer month, Integer year) {
		String lastMonthDate = "";
		try {
			Date date = new Date();
			date = DateUtil.setMonthsInDate(month, date);
			date = DateUtil.setYearsInDate(year, date);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.DATE, -1);

			Date lastDayOfMonth = calendar.getTime();
			DateFormat sdf = new SimpleDateFormat("dd");
			return sdf.format(lastDayOfMonth);
		} catch (Exception ex) {

		}
		return lastMonthDate;
	}
	
	/**
	 * This getDayNameByDate() Method return day name by provide date, date
	 * format and day name format i.e. (Sun or Sunday)
	 * 
	 * @param inputDate
	 *            specify date of string type
	 * @param dateFormat
	 *            specify date format
	 * @return dayNameFormat specify day name format i.e. (EEEEE for 'S', EEE for 'Sun' and EEEE
	 *         for 'Sunday')
	 * @throws Exception 
	 */
	public static String getDayFromDate(String inputDate, String dateFormat, String dayFormat) throws Exception {
		Date date = null;
		String dayName = "";
		try {
			date = new SimpleDateFormat(dateFormat).parse(inputDate);
			dayName = new SimpleDateFormat(dayFormat, Locale.ENGLISH).format(date);
		} catch (Exception e) {
			throw e;
		}

		return dayName;
	}
}

