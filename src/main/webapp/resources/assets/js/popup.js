/**
 * The loadBootstrapModel() method is use to load bootstrap modal and set its
 * height
 */
function loadBootstrapModel() {
	if ($('div.modal-wide').size() > 0) {
		$(".modal-wide").on("show.bs.modal", function() {
			var height = $(window).height() - 200;
			$(this).find(".modal-body").css("max-height", height);
		});
	}
}

//initialise on document ready
function loadModalPosition(modal) {
	'use strict';

	loadBootstrapModel();

	// CENTERED MODALS
	// phase one - store every dialog's height
	$(modal).each(function() {
		var t = $(this), d = t.find('.modal-dialog'), fadeClass = (t.is('.fade') ? 'fade' : '');
		// render dialog
		t.removeClass('fade').addClass('invisible').css('display', 'block');
		// read and store dialog height
		d.data('height', d.height());
		// hide dialog again
		t.css('display', '').removeClass('invisible').addClass(fadeClass);
	});
	// phase two - set margin-top on every dialog show
	$(modal).on('show.bs.modal', function() {
		var t = $(this), d = t.find('.modal-dialog'), dh = d.data('height'), w = $(window).width(), h = $(window).height();
		// if it is desktop & dialog is lower than viewport (set your own values)
		if (w > 380 && (dh + 60) < h) {
			d.css('margin-top', Math.round(0.96 * (h - dh) / 2));
		} else {
			d.css('margin-top', '');
		}
	});

}

/**
 * The showInvalidDatePopup() method is use to show invalid date popup and load
 * bind the click event again. It also set the focus to the rich calendar text
 * field
 * 
 * @param calendarInput
 *            The HTML element whose date is invalid
 */
function showInvalidDatePopup(calendarInput) {

	showStickyPopupById('invalidDateModal');
	$('div.modal-backdrop').addClass('popupOverlay');

	$('#invalidDateCloseBtn').unbind('click');

	$('#invalidDateCloseBtn').click(function() {
		$('#invalidDateModal').modal('hide');
		
		setTimeout(function() {
			$('div.modal-backdrop').removeClass('popupOverlay');
		}, 2000);

		$(calendarInput).val("");
		$(calendarInput).focus();
	});
}

/**
 * The showPopupById() method is use to show simple popup by {@code id}
 * 
 * @param id
 *            The HTML element's id
 */
function showPopupById(id) {
	$('#' + id).modal({
		show : 'false'
	});

	loadModalPosition('#' + id);

	$('#' + id).modal('show');
}

/**
 * The hidePopupById() method is use to hide popup by {@code id}
 * 
 * @param id
 *            The HTML element's id
 */
function hidePopupById(id) {
	$('#' + id).modal('hide');

	if ($('div.modal-backdrop').size() > 0 && $('div.modal-backdrop').hasClass('in')) {
		$('div.modal-backdrop').remove();
	}
}

/**
 * The showStickyPopupById() method is use to show sticky popup by {@code id}
 * 
 * @param id
 *            The HTML element's id
 */
function showStickyPopupById(id) {
	$('#' + id).modal({
		backdrop : 'static',
		keyboard : false,
		show : 'false'
	});

	loadModalPosition('#' + id);

	$('#' + id).modal('show');
}

/**
 * The showInvalidDatePopup() method is use to show invalid date popup and load
 * bind the click event again. It also set the focus to the rich calendar text
 * field
 * 
 * @param calendarInput
 *            The HTML element whose date is invalid
 */
function showSessionTimeoutPopup() {
	showStickyPopupById('sessionExpiredModal');
	$('div.modal-backdrop').addClass('popupOverlay');
}
