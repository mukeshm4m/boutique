var IDLE_TIMEOUT = 3595; //3600 seconds = 60 mins
var _idleSecondsTimer = null;
var _idleSecondsCounter = 0;

$(document).ready(function() {
	document.onclick = function() {
	    _idleSecondsCounter = 0;
	};
	
	document.onmousemove = function() {
	    _idleSecondsCounter = 0;
	};
	
	document.onkeypress = function() {
	    _idleSecondsCounter = 0;
	};
	
	_idleSecondsTimer = window.setInterval(CheckIdleTime, 1000);
	
});


function CheckIdleTime() {
     _idleSecondsCounter++;
     /*var oPanel = document.getElementById("SecondsUntilExpire");
     if (oPanel)
         oPanel.innerHTML = (IDLE_TIMEOUT - _idleSecondsCounter) + "";*/
    if (_idleSecondsCounter >= IDLE_TIMEOUT) {
        window.clearInterval(_idleSecondsTimer);
        unloadLeavePageAlert();
        
        showSessionTimeoutPopup();
    }
}