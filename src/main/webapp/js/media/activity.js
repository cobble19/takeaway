ue = UE.getEditor('editor', {
	initialFrameHeight: 400
});
ue.ready(function() {
	
});

$(document).ready(function() {
	$('#startDateTime').datetimepicker({
		lang:'ch',
		timepicker:true,
		/*format: 'Y-m-d H:i'*/
		value: '2015-02-20 20:20:20'
	});
	$('#endDateTime').datetimepicker({
		lang:'ch',
		timepicker:true,
		/*format: 'Y-m-d H:i'*/
		value: '2015-02-20 21:22:23'
	});
	
	$('#startDateTime, #endDateTime').change(function() {
		var startDateTime = $('#startDateTime').val();
		var endDateTime = $('#endDateTime').val();
		if (startDateTime >= endDateTime) {
			alert('结束时间不能小于开始时间');
		}
	})
	$('#activityForm').validate();
	$('#addBtn').click(function() {
		var startDateTime = $('#startDateTime').val();
		var endDateTime = $('#endDateTime').val();
		if (startDateTime >= endDateTime) {
			alert('结束时间不能小于开始时间');
			return;
		}
		if ($('#activityForm').valid()) {
			$('#activityForm').submit();
		}
		return ;
	});
	getPrizeProvider();
})


var getPrizeProvider = function() {
	console.log('getPrizeProvider');

	$.ajax({
		"url" : "../../web/enterprise/prize/provider",
		"type" : "GET",
		/*"headers" : {
			"Content-Type" : "application/json"
		},*/
		"dataType" : 'json',
		/*"data": ({
			username: username,
			phone: phone,
			activityId: activityId,
			sex: sex,
			description: description
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
        	for (i = 0; i < data.length; i++) {
        		var userPOJO = data[i];
        		console.log('userPOJO: ' + userPOJO);
            	$("#userIdEnterpriseX").append("<option value='" + userPOJO.userId +
            			"'>"  + userPOJO.nickname + 
            			"</option>");
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});	// ajax
}
