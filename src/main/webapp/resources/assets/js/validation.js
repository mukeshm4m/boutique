
/**
 * The isNumberKey() method is used to check given input is a number or not
 * 
 * @param evt
 *            Specifies the event instance
 * @param input
 *            Specifies the user input
 * @returns Return true, if input is a number
 */
function isNumberKey(evt, input) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;

	if (charCode == 47 && $(input).is('.input-number-only')) {
		return false;
	}

	if (charCode == 47 && $(input).is('.cal-month-inp') && $(input).val().indexOf('/') > 0) {
		return false;
	}

	if (charCode != 47 && charCode > 31 && (charCode < 48 || charCode > 57)){
		return false;
	}

	return true;
}

/**
 * The isDateValid() method is used to check given date string is in valid
 * format or not
 * 
 * @param dateStr
 *            Specifies the date string
 * @returns Return true, if date string validated successfully
 */
function isDateValid(dateStr) {
	var msg = "";
	// Checks for the following valid date formats:
	// MM/DD/YY MM/DD/YYYY MM-DD-YY MM-DD-YYYY
	// Also separates date into month, day, and year variables

	// To require a 2 & 4 digit year entry, use this line instead:
	// var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{2}|\d{4})$/;
	// To require a 4 digit year entry, use this line instead:
	var datePat = /^(\d{1,2})(\/)(\d{1,2})\2(\d{4})$/;

	var matchArray = dateStr.match(datePat); // is the format ok?
	if (matchArray == null) {
		msg = "Date is not in a valid format.";
		return false;
	}

	month = matchArray[1]; // parse date into variables
	day = matchArray[3];
	year = matchArray[4];	

	if (year === '0000') {
		msg = "Date is not in a valid format.";
		return false;
	}

	if (month < 1 || month > 12) { // check month range
		msg = "Month must be between 1 and 12.";
		return false;
	}

	if (day < 1 || day > 31) {
		msg = "Day must be between 1 and 31.";
		return false;
	}

	if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
		msg = "Month " + month + " doesn't have 31 days!";
		return false;
	}

	if (month == 2) { // check for february 29th
		var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
		if (day > 29 || (day == 29 && !isleap)) {
			msg = "February " + year + " doesn't have " + day + " days!";
			return false;
		}
	}

	if (day.charAt(0) == '0')
		day = day.charAt(1);

	// Incase you need the value in CCYYMMDD format in your server program
	// msg = (parseInt(year,10) * 10000) + (parseInt(month,10) * 100) +
	// parseInt(day,10);

	return true; // date is valid
}

/**
 * The validateDate() method is used to validate given date
 * 
 * @param calendarInput
 *            Specifies the date string
 */
function validateDate(calendarInput) {
	var dateString = $(calendarInput).val();

	if (dateString == 'mm/dd/yyyy' || dateString == 'MM/DD/YYYY') {
		dateString = '';
	}

	if (dateString != "") {
		var now = new Date(dateString);

		if (!isDateValid(dateString) || now == 'Invalid Date') {

			$('#popup_loader').show();
			showInvalidDatePopup(calendarInput);

			setTimeout(function() {
				$('#popup_loader').hide();
			}, 500);

			return false;
		}
	}
}

/**
 * The isPhoneNumberAllowdKeys() method is used to check given input is allowed
 * for phone number field or not
 * 
 * @param evt
 *            Specifies the event instance
 * @param input
 *            Specifies the user input
 * @returns Return true, if input is valid
 */
function isPhoneNumberAllowdKeys(evt, input) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;

	if (charCode == 47 && $(input).is('.input-phonenumber')) {
		return false;
	}

	var allowedCharactersWithNumbers = [ 32, 45, 46 ]; // 32 space,45 hyphen,46 is dot
	var exists = allowedCharactersWithNumbers.indexOf(charCode);

	if (charCode != 47 && charCode > 31 && (charCode < 48 || charCode > 57) && exists == -1)
		return false;

	return true;
}

/**
 * The hideErrors() method is used to register click event on cross (x) icon of
 * validation error message panel to hide error panel
 */
function hideErrors() {
	$('span.error').children('i.close-btn').unbind('click');

	$('span.error').children('i.close-btn').click(function() {
		$(this).parent('span.error').hide();
		
		if($(this).parent('span.error').is('.calendar-error')) {
			$('#mainForm\\:dateOfBirthInputDate').removeClass('input-error');
		} else {
			
			if($(this).parent('span.error').parent('div.form-group').children("input.input-error").size() > 0) {
				$(this).parent('span.error').parent('div.form-group').children("input.input-error").removeClass('input-error');				
			}
			else if($(this).parent('span.error').parent('div.form-group').children("select.input-error").size() > 0) {
				$(this).parent('span.error').parent('div.form-group').children("select.input-error").removeClass('input-error');
			}
			else if ($(this).parent('span.error').closest('div.form-group').children("input.input-error").size() > 0) {
				$(this).parent('span.error').closest('div.form-group').children("input.input-error").removeClass('input-error');
			}
		}
	});
}


function validateDigitsAndHyphen(evt)
{
   var charCode = (evt.which) ? evt.which : evt.keyCode;
   if (charCode != 45  && charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}