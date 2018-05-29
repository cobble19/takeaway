$(function() {
	var noteStart = $('#noteStart');
	var noteEnd = $('#noteEnd');
	var startDateTime = new Date($('#startDateTime').val().replace('CST', '')).getTime();
    var endDateTime = new Date($('#endDateTime').val().replace('CST', '')).getTime();
    var curDateTime = new Date().getTime();

    ///
    if (startDateTime > curDateTime) {
    		$('#countdownStartDiv').show();
	    	$('#countdownStart').countdown({
	    		timestamp	: startDateTime,
	    		callback	: function(days, hours, minutes, seconds){
	    			var message = "";
	    			message += days + "天" + " ";
	    			message += hours + "小时" + " ";
	    			message += minutes + "分" + " ";
	    			message += seconds + "秒" + " <br />";
	    			noteStart.html(message);
	    		}
	    	});
    }
    ///
    ///
    if (endDateTime > curDateTime) {
    		$('#countdownEndDiv').show();
	    	$('#countdownEnd').countdown({
	    		timestamp	: endDateTime,
	    		callback	: function(days, hours, minutes, seconds){
	    			var message = "";
	    			message += days + "天" + " ";
	    			message += hours + "小时" + " ";
	    			message += minutes + "分" + " ";
	    			message += seconds + "秒" + " <br />";
	    			noteEnd.html(message);
	    		}
	    	});
    }
    ///
    
});