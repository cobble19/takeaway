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
	$('#interactiveForm').validate();
	$('#addBtn').click(function() {
		var startDateTime = $('#startDateTime').val();
		var endDateTime = $('#endDateTime').val();
		if (startDateTime >= endDateTime) {
			alert('结束时间不能小于开始时间');
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
