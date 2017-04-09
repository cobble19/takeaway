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
	
	$('#wxRespMsgForm').validate();
	
//	$('#wxRespMsgForm').find('#uploadBtn').click(function(e) {
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
		if ($('#wxRespMsgForm').valid()) {
			$('#wxRespMsgForm').submit();
		}
		return ;
	});
	
	
	initMsgType();
	///
	$('#wxRespMsgForm #msgType').change(function(){
		var value = $(this).val();
		var text = $(this).find('option:selected').text();
		console.log('value: ' + value + ", text: " + text);
		
		var msgSend = $('#wxRespMsgForm #msgSend').val();
		
		$('#wxRespMsgForm #msgSendWrap').find('#msgSend').remove();
		
		if (value == '0') {
			var input = '<select class="form-control" id="msgSend" name="msgSend" required="required">'
						+ '	<option value="001"' +
						('001' == msgSend ? 'selected' : '') +
								'>加入会员</option>'
						+ '	<option value="002"' +
						('002' == msgSend ? 'selected' : '') +
								'>重新加入会员</option>'
						+ '	<option value="003"' +
						('003' == msgSend ? 'selected' : '') +
								'>退出会员</option>'
						+ '</select>';
			$('#wxRespMsgForm #msgSendWrap').append(input);
		} else if (value == '1') {
			var input = '<input class="form-control" id="msgSend" name="msgSend" value="' + msgSend + '" ' +
					'required="required" placeholder="请输入回复关键字"/>';
			console.log(input);
			$('#wxRespMsgForm #msgSendWrap').append(input);
		} 
	});
	///
	
	$('#wxRespMsgForm #msgType').change();
	
});

///initMsgType
var initMsgType = function() {
	var msgType = getParam('msgType');
	$('#wxRespMsgForm #msgType').val(msgType);
}
/// end initMsgType
