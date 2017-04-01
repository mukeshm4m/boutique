$(document).ready(function() {
	
	loadUiEvents();

});

/**
 * The loadUiEvents() method is use to load all the UI events. This is mainly
 * call whenever ajax request is completed
 */
function loadUiEvents() {

	$('input.rf-cal-inp, input.cal-month-inp, input.input-number-only').unbind('keypress');

	$('input.rf-cal-inp, input.cal-month-inp, input.input-number-only').keypress(function(evt) {
		return isNumberKey(evt, this);
	});
	
	$('.jqueryDatatable').DataTable({		
        dom: 'Bfrtip',		
        "searching": false,		
        buttons: [		
            'csv', 'excel', 		
            {		
				text : 'PDF',		
				extend : 'pdfHtml5',		
				exportOptions : {		
					columns : [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ]		
				}		
			}		
        ]		
    });
	
	disableEnterFormSubmit();
}


/**
 * The disableFutureDates() is method is use to disable future dates from
 * {@code day}
 * 
 * @param day
 *            The day from which to disable all future days
 * @returns {Boolean} Returns true for disable day
 */
function disableFutureDates(day) {
	var currentDate = new Date();
	currentDate.setHours(0, 0, 0, 0)

	return (day.date.getTime() - currentDate.getTime() <= 1);
}

/**
 *  The ajaxStart() is used to show Please wait message when ever any ajax
 * request is sent
 */
function ajaxStart() {
	$("#ajax_loader").show();
}

/**
 * The ajaxStart() is used to hide Please wait message when ever any ajax
 * request is completed. At the end it loads other events.
 */
function ajaxStop() {
	$("#ajax_loader").hide();

	loadUiEvents();
}

function disableEnterFormSubmit() {
	$(window).keypress(function(event) {
		if (event.keyCode == 13 && event.target.getAttribute('type') == 'text') {
			return false;
		}
	});
}

function loadInputMasking() {
	$("#mainForm\\:phoneNumber").mask("(999) 999-9999", {autoclear : false});
	$("#mainForm\\:ssn").mask("999-99-9999" , {autoclear : false});
	$("#mainForm\\:taxId").mask("99-9999999", {autoclear : false});
	$('#mainForm\\:dateOfBirthInputDate').mask("99/99/9999",{placeholder:"MM/DD/YYYY"});
	$("#contactUsForm\\:contactUsPhone").mask("(999) 999-9999", {autoclear : false});
}

function scrollToId(id) {
	id = '#' + id;
	
    $('html, body').animate({
        scrollTop: ($(id).offset().top - $('.hamBar').outerHeight(true) + 30)
    }, 1000);
}

/**
 * The loadTiptip() method is used to load tiptip. This method is called when we
 * render the screen using ajax.
 */
function loadTiptip() {
	$('.tip').tipTip({
		defaultPosition : 'top',
		delay : 0,
		maxWidth : '500px'
	});
}

/**
 * The loadLeavePageAlert() method is used load leaving page alert on self-care
 * when any form is editable.
 */
function loadLeavePageAlert() {
	window.addEventListener("beforeunload", loadAlert);
}

/**
 * The loadAlert() metohd is used to load page leave alert message
 * 
 * @param e
 *            Specifies the event instance
 * @returns Return alert message string
 */
function loadAlert(e) {
	var confirmationMessage = $('#leavePagePopupMessageHidden').val();	

	(e || window.event).returnValue = confirmationMessage; // Gecko + IE
	return confirmationMessage; // Gecko + Webkit, Safari, Chrome etc.
}

/**
 * The unloadLeavePageAlert() method is used unload leaving page alert from
 * form when user purchase distributorship successfully.
 */
function unloadLeavePageAlert() {
	window.removeEventListener("beforeunload", loadAlert);
}
