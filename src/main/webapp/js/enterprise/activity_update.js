var ue;
$(document).ready(function() {
	if (!ue) {
		ue = UE.getEditor('editor', {
			initialFrameHeight: 400
		});
		ue.ready(function() {
			showDetail();
		});
	}
	

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
//			alert('结束时间不能小于开始时间');
		}
	})
	
	$('#activityForm').validate();
	
	$('#addBtn').click(function(e) {
		e.preventDefault();

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
	})
	
})

var showDetail = function() {
	var activityId = getParam('activityId');
	$.ajax({
		"url" : "../../web/enterprise/activity/" + activityId,
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		/*"dataType" : 'json',*/
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	$('#titleE').val(data.title);
    		ue.setContent(data.content);
    		$('#startDateTime').val(new Date(data.startDateTime).format('Y/m/d H:i'));
    		$('#endDateTime').val(new Date(data.endDateTime).format('Y/m/d H:i'));
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}