package com.boutique.common.util;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Util {

    /**
     * Default private constructor.
     */
    private Util() {
    }

    /**
     * This method returns true if the given string is either null or empty. Otherwise it returns false.
     * 
     * @param string
     *            Specifies the element that is to be checked for null or empty state
     * @return Returns true if the given string is either null or empty. Otherwise it returns false.
     */
    public static boolean isNullOrEmpty(String string) {
        return (((string == null) || (string.trim().length() == 0)) ? true : false);
    }
    
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * This method returns true if the given string array is not null and not empty. Otherwise it returns false.
     * 
     * @param str
     *            Specifies the string array that is to be checked for null and empty state
     * @return Returns true if the given string array is not null and not empty. Otherwise it returns false.
     */
    public static boolean isNotNullAndEmpty(String[] str) {
        return (((str != null) && (str.length > 0)) ? true : false);
    }

    /**
     * The isNotNullAndEmpty() method returns true if the given string is not null and not empty. Otherwise it returns
     * false.
     * 
     * @param string
     *            Specifies the element that is to be checked for null and empty state
     * @return Returns true if the given string is not null and not empty. Otherwise it returns false.
     */
    public static boolean isNotNullAndEmpty(String string) {
        return (((string != null) && (string.trim().length() > 0)) ? true : false);
    }
    
    /**
     * The isNotNull() method returns true if the given object is not null. Otherwise it returns false.
     * 
     * @param object Specifies the element that is to be checked for null and empty state
     * @return Returns true if the given string is not null and not empty. Otherwise it returns false.
     */
    public static boolean isNotNull(Object object) {
        return object != null;
    }

    /**
     * The isNullOrEmpty() method returns true if the given collection is either null or empty. Otherwise it returns
     * false.
     * 
     * @param list
     *            Specifies the collection that is to be checked for null or empty state
     * @return Returns true if the given list is either null or empty. Otherwise it returns false.
     */
    public static boolean isNullOrEmpty(List<?> list) {
        return (((list == null) || (list.size() == 0)) ? true : false);
    }
    
    /**
     * The isNullOrEmpty() method returns true if the given map is either null or empty. Otherwise it returns
     * false.
     * 
     * @param map
     *            Specifies the map that is to be checked for null or empty state
     * @return Returns true if the given map is either null or empty. Otherwise it returns false.
     */
    public static boolean isNullOrEmpty(Map<?, ?> map) {
    	 return (((map == null) || (map.size() == 0)) ? true : false);
    }
    
    /**
     * The isNotNullAndEmpty() method returns true if the given collection is not null and not empty. Otherwise it
     * returns false.
     * 
     * @param list
     *            Specifies the collection that is to be checked for null and empty state
     * @return Returns true if the given collection is not null and not empty. Otherwise it returns false.
     */
    public static boolean isNotNullAndEmpty(List<?> list) {
        return (((list != null) && (list.size() > 0)) ? true : false);
    }

    /**
     * The isNotNullAndEmpty() method returns true if the given set is not null and not empty. Otherwise it returns
     * false.
     * 
     * @param set
     *            Specifies the set that is to be checked for null and empty state
     * @return Returns true if the given set is not null and not empty. Otherwise it returns false.
     */
    public static boolean isNotNullAndEmpty(Set<?> set) {
        return (((set != null) && (set.size() > 0)) ? true : false);
    }

    /**
     * The isNotNullAndEmpty() method returns true if the given map is not null and not empty. Otherwise it returns
     * false.
     * 
     * @param map
     *            Specifies the map that is to be checked for null and empty state
     * @return Returns true if the given map is not null and not empty. Otherwise it returns false.
     */
    public static boolean isNotNullAndEmpty(Map<?, ?> map) {
        return (((map != null) && (map.size() > 0)) ? true : false);
    }

    /**
     * The isNumber() method returns true if the given string is a number. Otherwise it returns false.
     * 
     * @param string
     *            Specifies the element that is to be checked for number type
     * @return Returns true if the given string is a number. Otherwise it returns false.
     */
    public static boolean isNumber(String string) {
        boolean flag = false;
        if (isNotNullAndEmpty(string)) {
            try {
                Double.parseDouble(string);
                flag = true;
            } catch (NumberFormatException nfe) {
            }
        }
        return flag;
    }

    /**
     * The validateZipCode() method returns true if the given string is a 5 or 9 digit number. Otherwise it returns
     * false.
     * 
     * @param zipCode
     *            Specifies the zipcode.
     * @return Returns true if the given string is a number. Otherwise it returns false. example: 12456-7892
     */
    public static boolean validateZipCode(String zipCode) {
        String text = zipCode;
        String searchPattern = "\\d\\d\\d\\d\\d(-\\d\\d\\d\\d)?";

        Pattern pattern = Pattern.compile(searchPattern);
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }

    /**
     * The validateFaxPhone() method returns true if the given string is a 10/7 digit number. Otherwise it returns
     * false.
     * 
     * @param number
     *            Specifies the element that is to be checked for number type
     * @return Returns true if the given string is a number. Otherwise it returns false. example: xxx-xxx-xxxx
     */
    public static boolean validateFaxPhone(String number) {
        String text = number;
        String searchPattern = "\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d";

        Pattern pattern = Pattern.compile(searchPattern);
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }

    /**
     * The validateEmail() method returns true if the given string is valid email address. Otherwise it returns false.
     * 
     * @param email
     *            Specifies the element that is to be checked for email of the following format. asmith@mactec.com |
     *            foo12@foo.edu | bob.smith@foo.tv | a_smith@mactec.com | foo-12@foo.edu
     */
    public static boolean validateEmail(String email) {
        String text = email;
        String searchPattern = "^([a-zA-Z0-9_\'-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

        Pattern pattern = Pattern.compile(searchPattern);
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }

    /**
     * The validatePassword() method returns true if the given string is valid password. Otherwise it returns false.
     * 
     * @param password
     *            Specifies the element that is to be checked for password of the following format. Password should
     *            contain at least 1 number.
     * @return True if the given string is valid password. Otherwise returns false.
     */
    public static boolean validatePassword(String password) {

        String text = password;

        String searchPattern = "^(([a-zA-Z]+\\d+)|(\\d+[a-zA-Z]+))[a-zA-Z0-9]*$";

        Pattern pattern = Pattern.compile(searchPattern);
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }

    /**
     * The roundTwoDecimals() method is used to round a double value to two decimals.
     * 
     * @param number
     *            Specifies the the double value.
     * @return Returns the two decimals rounded number.
     */
    public static Double roundTwoDecimals(Double number) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(number));
    }

    /**
     * The roundTwoDecimals() method is used to round a double value to two decimals.
     * 
     * @param number
     *            Specifies the the number to be formated.
     * @return Returns the two decimals rounded number.
     * @throws Exception 
     */
    public static Double roundTwoDecimals(String number) throws Exception {
        double price = 0;

        try {
            price = Double.valueOf(number);
        } catch (Exception exception) {
            throw new Exception("Error: Invalid number.");
        }

        return roundTwoDecimals(price);
    }

    /**
     * This method returns file name from fully qualified name.
     * 
     * @param fullyQualifiedName
     *            Specifies fully qualified name.
     * @return File name from fully qualified name.
     */
    public static String getFileName(String fullyQualifiedName) {

        String fileName = fullyQualifiedName;

        int lastIndexOfSlash = fullyQualifiedName.lastIndexOf("/");
        if (lastIndexOfSlash != -1) {
            fileName = fullyQualifiedName.substring(lastIndexOfSlash + 1);
        }

        return fileName;
    }


    /**
     * This method returns getter method a given property.
     * 
     * @param clazz
     *            Specifies class of the property.
     * @param propertyName
     *            Specifies name of the property.
     * @return Getter method a given property.
     * @throws NoSuchMethodException
     *             When a given property/method does not exists.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    static public Method findGetter(Class clazz, String propertyName) throws NoSuchMethodException {
        return clazz.getMethod(findGetterName(propertyName));
    }

    /**
     * This method returns getter name of a given property.
     * 
     * @param propertyName
     *            Specifies name of the property.
     * @return Getter name of a given property.
     */
    static public String findGetterName(String propertyName) {
        if (propertyName == null || propertyName.length() == 0) {
            throw new IllegalArgumentException("property name cannot be null or empty");
        }
        String getter = "get" + Character.toUpperCase(propertyName.charAt(0));
        if (propertyName.length() > 1) {
            return getter + propertyName.substring(1);
        } else {
            return getter;
        }
    }

    /**
     * This method returns an object to its string from.
     * 
     * @param obj
     *            Specifies object.
     * @return String form an object.
     */
    static public String toString(Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            Method getId = findGetter(obj.getClass(), "id");
            return String.valueOf(getId.invoke(obj));
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    /**
     * This method returns title case of given string.
     * 
     * @param input
     *            Specifies input string.
     * @return Title case of given string.
     */
    public static String toTitleCase(String input) {
        if (Util.isNotNullAndEmpty(input)) {
            StringBuilder titleCase = new StringBuilder();
            boolean nextTitleCase = true;

            input = input.toLowerCase();
            for (char c : input.toCharArray()) {
                if (Character.isSpaceChar(c)) {
                    nextTitleCase = true;
                } else if (nextTitleCase) {
                    c = Character.toTitleCase(c);
                    nextTitleCase = false;
                }
                titleCase.append(c);
            }
            return titleCase.toString();
        }

        return input;
    }

    /**
     * This method checks whether a given number is in between start and end numbers.
     * 
     * @param start
     *            Specifies start number.
     * @param end
     *            Specifies end number.
     * @param toCompare
     *            Specifies the number that needs to be checked in range.
     * @return True if a given number is in between start and end numbers. Otherwise returns false.
     */
    public static boolean isBetween(int start, int end, int toCompare) {
        if (toCompare > start && toCompare < end) {
            return true;
        }
        return false;
    }

    /**
     * This method checks whether a number lies given range. It excludes edges of range.
     * 
     * @param start
     *            Specifies the start range.
     * @param end
     *            Specifies end of range.
     * @param toCompare
     *            Specifies given number of changed in range.
     * @return True if a number lies in given range. Otherwise returns false.
     */
    public static boolean isBetweenExcluded(int start, int end, int toCompare) {
        if (toCompare > start && toCompare < end) {
            return true;
        }
        return false;
    }

    /**
     * This method checks whether a number is in given range.
     * 
     * @param start
     *            Specifies the start range.
     * @param end
     *            Specifies end of range.
     * @param toCompare
     *            Specifies given number of changed in range.
     * @return True if a number is in given range. Otherwise returns false.
     */
    public static boolean isBetweenIncluded(int start, int end, int toCompare) {
        if (toCompare >= start && toCompare <= end) {
            return true;
        }
        return false;
    }
    
    /**
     * convertIntegerToStringList
     * 				converts list of Integers to List of String
     * @param list of integers
     * @return list of strings
     */
	public static List<String> convertIntegerToStringList(List<Integer> list) {
		List<String> strings = new ArrayList<String>();
		if (Util.isNotNullAndEmpty(list)) {
			for (Integer item : list) {
				strings.add(item.toString());
			}
		}
		return strings;
	}
	
	/**
     * convertStringToIntegerList
     * 				converts list of Strings to List of Integer
     * @param list of strings
     * @return list of integers
     */
	public static List<Integer> convertStringToIntegerList(List<String> list) {
		List<Integer> integers = new ArrayList<Integer>();
		if (Util.isNotNullAndEmpty(list)) {
			for (String item : list) {
				try {
					integers.add(Integer.parseInt(item));
				} catch (Exception exception) {
				}
			}
		}
		return integers;
	}

    /**
     * This method converts a double in scientific notation to non scientific notation String
     * 
     */
    public static String nosci(double d) {
        if(d < 0){
            return "-" + nosci(-d);
        }
        String javaString = String.valueOf(d);
        int indexOfE =javaString.indexOf("E"); 
        if(indexOfE == -1){
            return javaString;
        }
        StringBuffer sb = new StringBuffer();
        if(d > 1){//big number
            int exp = Integer.parseInt(javaString.substring(indexOfE + 1));
            String sciDecimal = javaString.substring(2, indexOfE);
            int sciDecimalLength = sciDecimal.length();
            if(exp == sciDecimalLength){
                sb.append(javaString.charAt(0));
                sb.append(sciDecimal);              
            }else if(exp > sciDecimalLength){
                sb.append(javaString.charAt(0));
                sb.append(sciDecimal);
                for(int i = 0; i < exp - sciDecimalLength; i++){
                    sb.append('0');
                }
            }else if(exp < sciDecimalLength){
                sb.append(javaString.charAt(0));
                sb.append(sciDecimal.substring(0, exp));
                sb.append('.');
                for(int i = exp; i < sciDecimalLength ; i++){
                    sb.append(sciDecimal.charAt(i));
                }
            }
          return sb.toString();
        }else{
            //for little numbers use the default or you will
            //loose accuracy
            return javaString;
        }       


    }
    
    /**
     * listToCommaSeparatedString
     * @param list
     * @return
     */
	public static String listToCommaSeparatedString(List<String> list) {
		StringBuffer result = new StringBuffer("");
		int count = 0;
		for (String type : list) {
			if (count > 0) {
				result.append(", ");
			}
			result.append(type);
			count++;
		}
		return result.toString();
	}
    
    /**
     * listToQuotedCommaSeparatedString
     * @param list
     * @return
     */
	public static String listToQuotedCommaSeparatedString(List<String> list) {
		StringBuffer result = new StringBuffer("");
		int count = 0;
		for (String type : list) {
			if (count > 0) {
				result.append(", ");
			}
			result.append("'" + type + "'");
			count++;
		}
		return result.toString();
	}
        
    /**
     * listToDelimeterSeparatedString
     * @param list
     * 			Specifies List of String items
     * @param delimeter
     * 			Specifies delimiter to add between items
     * @return
     */
    public static String listToDelimeterSeparatedString(List<String> list, String delimeter) {
    	StringBuffer result = new StringBuffer("");
		int count = 0;
		for(String item : list) {
			if(count > 0 && Util.isNotNullAndEmpty(item)) {
				result.append(delimeter);
			}
			if(Util.isNotNullAndEmpty(item)) {
				result.append(item);
			}
			count++;
		}
    	return result.toString();
    }
    
    /**
     * matchCount method is used to count character match in the given string
     * 		i.e characterToMatch "/" and source is "bacha/khan awan" it will return the count
     * 		for "/" in given source i.e 1   
     * @param source
     * 		String object as source for finding character
     * @param characterToMatch
     * 		char type as character to count
     * @return
     * 		number of occurrences of given character
     */
    public static int matchCount(String source,char characterToMatch){
		int count = 0;
		for(int i =0; i < source.length(); i++)
		    if(source.charAt(i) == characterToMatch)
		        count++;
		return count;
	}
    
    /**
     * 
     * @param list
     * @param separator
     * @return
     */
    public static String listToCommaSeparatedString(List<String> list, String separator) {
    	StringBuffer result = new StringBuffer("");
		int count = 0;
		for(String type : list) {
			if(count > 0) {
				result.append(separator);
			}
			result.append(type);
			count++;
		}
    	return result.toString();
    }
    
    /**
     * This method returns true if the given number is either null or 0. Otherwise it returns false.
     * 
     * @param number
     *            Specifies the element that is to be checked for null or 0 state
     * @return Returns true if the given string is either null or 0. Otherwise it returns false.
     */
    public static boolean isNullOrZero(Integer number) {
        return (((number == null) || (number == 0)) ? true : false);
    }
    
    /**
     * removeAccents method is used to Removes Accents/Diacritics from a string. 
     * For instance, 'à' will be replaced by 'a'.
     * @param text
     * 			String to remove Accents
     * @return
     */
    public static String removeAccents(String str) {
        return str == null ? null
            : Normalizer.normalize(str, Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static String getClientOS(String userAgent) {
		String os = null;
		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			os = "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		} else {
			os = "UnKnown, More-Info: " + userAgent;
		}
		return os;
	}
    
    public static String getClientBrowser(String userAgent, String user) {
		String browser = null;
		if (user.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera"))
				browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			else if (user.contains("opr"))
				browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
		} else if (user.contains("chrome")) {
			browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			// browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/",
			// "-");
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else {
			browser = "UnKnown, More-Info: " + userAgent;
		}
		return browser;
	}
    
    public static boolean compareStringLists(List<String> listA, List<String> listB) {
    	Set<Object> set1 = new HashSet<Object>();
    	set1.addAll(listA);
    	Set<Object> set2 = new HashSet<Object>();
    	set2.addAll(listB);
    	return set1.equals(set2);
    }
    
    public static String validateExcelFileName(String fileName){
		String validFileName = "";
		if(Util.isNotNullAndEmpty(fileName)){
			validFileName = fileName.replaceAll("[\\:*?<>/|\"]", "-");
		}
		return validFileName;
	}
    
    
    /**
     * trim method is used to remove leading or trailing no-break spaces(\u00A0) and normal spaces from the given string
     * @param input
     * 		specifies the string object to be trimmed
     * @return
     * 		return string without spaces
     */
    public static String trim(String input){
    	return (input == null ? input : input.replaceAll("\u00A0", " ").replaceAll("\u2007", " ").replaceAll("\u202F", " ").replaceAll("\\xA0", " ").replaceAll("Â", "").replaceAll("­", "-").trim());
    }
    
    public static String addCommasToNumericString(String digits) {
		String result = "";
		int length = digits.length();
		int nDigits = 0;

		for (int i = length - 1; i >= 0; i--) {
			result = digits.charAt(i) + result;
			nDigits++;
			if (((nDigits % 3) == 0) && (i > 0)) {
				result = "," + result;
			}
		}
		
		return (result);
	}
    
    public static String addCommasToNumber(Integer number) {
    	return new DecimalFormat("#,###").format(number);
    }
    
    public static String addCommasToDecimalNumber(Double number) {
    	return new DecimalFormat("#,###.00").format(number);
    }
    
    /**
     * This method returns true if the given string array is null or all items are empty 
     * @param str
     *      Specifies the string array that is to be checked for null and empty state
     * @return Returns true if the given string array is not null and not empty and all items of 
     * 		given array are not null and not empty. Otherwise it returns false.
     */
    public static boolean isArrayItemsNullOrEmpty(String[] array) {
    	Boolean nullOrEmpty = true;
    	if((array != null) && (array.length > 0)){
    		for(String str : array){
    			if(!Util.isNullOrEmpty(str)){
    				nullOrEmpty = false;
    				break;
    			}
    		}
    	}
    	return nullOrEmpty;
    }
    
    /**
     * removeMultipleSpaces, removes non-breaking spaces in given string
     *   i.e \u2007, \u00A0 , \u202F
     * @param content
     * @return
     * 		String object after removing non-breaking space
     */
	public static String removeMultipleSpaces(String content) {
		return content.replaceAll("\u2007", " ").replaceAll("\u00A0", " ").replaceAll("\u202F", " ").replaceAll("'", "\'");
	}
	
	
	public static String getEncryptedCreditCardNumber(String ccNumber) {
		if (Util.isNullOrEmpty(ccNumber))
			return ccNumber;

		String last4Digits = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
		String remainingBeginingDigits = ccNumber.substring(0, ccNumber.length() - 4);
		String steriks = "";

		for (int x = 0; x < remainingBeginingDigits.length(); x++) {
			steriks = steriks + "x";
		}

		return steriks + last4Digits;
	}
	
	public static String getCreditCardLast4Digits(String ccNumber) {
		if (Util.isNullOrEmpty(ccNumber))
			return null;

		if (ccNumber.length() < 4)
			return ccNumber;

		String last4Digits = ccNumber.substring(ccNumber.length() - 4, ccNumber.length());
		
		return last4Digits;
	}
	
	public static boolean isLeapYear(int year) {

		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		} else {
			return false;
		}
	}
}
