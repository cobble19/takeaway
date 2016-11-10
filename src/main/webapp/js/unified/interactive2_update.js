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
	$('#prizeEndDateTime').datetimepicker({
		lang:'ch',
		timepicker:true,
		/*format: 'Y-m-d H:i'*/
		value: '2015-02-20 21:22:23'
	});
	/*getPrizeProvider();*/
	
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
		var prizeEndDateTime = $('#prizeEndDateTime').val();
		if (startDateTime >= endDateTime) {
			alert('结束时间不能小于开始时间');
			return;
		}
		
		if (prizeEndDateTime <= endDateTime) {
			alert('奖品领取截止日期不能小于活动结束日期');
			return;
		}
		
		if (ue.getContent() == null || ue.getContent() == '') {
			alert('活动介绍不能为空');
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
    		$('#prizeEndDateTime').val(new Date(data.prizeEndDateTime).format('Y/m/d H:i'));
        	$('#numOfWinner').val(data.numOfWinner);
        	$('#answer').val(data.answer);
        	$('#awardNumberPer').val(data.awardNumberPer);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

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
            	$("#userIdX").append("<option value='" + userPOJO.userId +
            			"'>"  + userPOJO.nickname + 
            			"</option>");
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});	// ajax
}


