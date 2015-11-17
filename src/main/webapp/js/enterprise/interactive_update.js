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
			alert('结束时间不能小于开始时间');
		}
	})
	$('#interactiveForm').validate();
	$('#addBtn').click(function() {
		var startDateTime = $('#startDateTime').val();
		var endDateTime = $('#endDateTime').val();
		if (startDateTime >= endDateTime) {
			alert('结束时间不能小于开始时间');
			return;
		}
		if ($('#interactiveForm').valid()) {
			$('#interactiveForm').submit();
		}
		return ;
	})
})

var showDetail = function() {
	var interactiveId = getParam('interactiveId');
	$.ajax({
		"url" : "../../web/enterprise/interactive/" + interactiveId,
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		/*"dataType" : 'json',*/
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	$('#name').val(data.name);
    		ue.setContent(data.content);
    		$('#startDateTime').val(new Date(data.startDateTime).format('Y/m/d H:i'));
    		$('#endDateTime').val(new Date(data.endDateTime).format('Y/m/d H:i'));
        	$('#rule').val(data.rule);
        	$('#prize').val(data.prize);
        	$('#numOfWinner').val(data.numOfWinner);
        	$('#answer').val(data.answer);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}


