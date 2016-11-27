/*ue = UE.getEditor('editor', {
	initialFrameHeight: 400
});
ue.ready(function() {
	
});*/

$(document).ready(function() {
	/*$('#startDateTime').datetimepicker({
		lang:'ch',
		timepicker:true,
		format: 'Y-m-d H:i'
		value: '2015-02-20 20:20:20'
	});
	$('#endDateTime').datetimepicker({
		lang:'ch',
		timepicker:true,
		format: 'Y-m-d H:i'
		value: '2015-02-20 21:22:23'
	});
	
	$('#startDateTime, #endDateTime').change(function() {
		var startDateTime = $('#startDateTime').val();
		var endDateTime = $('#endDateTime').val();
		if (startDateTime >= endDateTime) {
			alert('结束时间不能小于开始时间');
		}
	})*/
	
	$('#voteForm').validate();
	
//	$('#voteForm').find('#uploadBtn').click(function(e) {
//		var form1 = $(this).parents('form');
//		var formData;
//	    formData = new FormData();
//
//	    var userId = $('#userId').val();
//		
//		formData.append('pic', form1.find('input[name=pic]').get(0).files[0]);
//	    formData.append('userId', userId);
//	    /*formData.append('wxTemplateId', wxTemplateId);
//	    formData.append('orderNo', orderNo);*/
//
//	    $.ajax({
//	        url: '../../htmleditor/pic/add',
//	        contentType:"multipart/form-data",
//	        data: formData,
//	        processData: false,
//	        type: 'POST',
//	        contentType: false, // tell jQuery not to set contentType
//	        success : function(data) {
//	            alert('上传成功');
//	            form1.find('#logoImg').val(data.file_url);
//	        }
//	    });	// ajax end
//	    
//	});	// upload pic to picture files
	
	$('#addBtn').click(function() {
		/*var startDateTime = $('#startDateTime').val();
		var endDateTime = $('#endDateTime').val();
		if (startDateTime >= endDateTime) {
			alert('结束时间不能小于开始时间');
			return;
		}*/
		if ($('#wxMenuMgrFullForm').valid()) {
			$('#wxMenuMgrFullForm').submit();
		}
		return ;
	});
})
