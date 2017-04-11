/*var ue;*/
$(document).ready(function() {
	/*if (!ue) {
		ue = UE.getEditor('editor', {
			initialFrameHeight: 400
		});
		ue.ready(function() {
			showDetail();
		});
	}*/
	

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
	});*/
	
	/*$('#startDateTime, #endDateTime').change(function() {
		var startDateTime = $('#startDateTime').val();
		var endDateTime = $('#endDateTime').val();
		if (startDateTime >= endDateTime) {
//			alert('结束时间不能小于开始时间');
		}
	})*/
//	showDetail();
	
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
	
	$('#addBtn').click(function(e) {
		e.preventDefault();

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
	})
	
	/*getPrizeProvider();*/
	
	initMsgType();
	///
	$('#wxRespMsgForm #msgType').change(function(){
		var value = $(this).val();
		var text = $(this).find('option:selected').text();
		console.log('value: ' + value + ", text: " + text);
		
		//隐藏 04/11/2017
		if (value == '0') {
			$('#wxRespMsgForm #msgSendWrap').parent().hide();
		} else if (value == '1') {
			$('#wxRespMsgForm #msgSendWrap').parent().show();
		}
		
		// 04/11/2017 comment, input/select switch
//		var msgSend = $('#wxRespMsgForm #msgSend').val();
//		
//		$('#wxRespMsgForm #msgSendWrap').find('#msgSend').remove();
//		
//		if (value == '0') {
//			var input = '<select class="form-control" id="msgSend" name="msgSend" required="required">'
//						+ '	<option value="001"' +
//						('001' == msgSend ? 'selected' : '') +
//								'>加入会员</option>'
//						+ '	<option value="002"' +
//						('002' == msgSend ? 'selected' : '') +
//								'>重新加入会员</option>'
//						+ '	<option value="003"' +
//						('003' == msgSend ? 'selected' : '') +
//								'>退出会员</option>'
//						+ '</select>';
//			$('#wxRespMsgForm #msgSendWrap').append(input);
//		} else if (value == '1') {
//			var input = '<input class="form-control" id="msgSend" name="msgSend" value="' + msgSend + '" ' +
//					'required="required" placeholder="请输入回复关键字"/>';
//			console.log(input);
//			$('#wxRespMsgForm #msgSendWrap').append(input);
//		} 
	});
	///
	
	$('#wxRespMsgForm #msgType').change();
	
})
///initMsgType
var initMsgType = function() {
	var msgType = getParam('msgType');
	$('#wxRespMsgForm #msgType').val(msgType);
}
/// end initMsgType

///
var showDetail = function() {
	var wxRespMsgId = getParam('wxRespMsgId');
	$.ajax({
		"url" : "../../api/media/wxRespMsg/" + wxRespMsgId,
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		/*"dataType" : 'json',*/
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	$('#title').val(data.title);
        	$('#content').val(data.content);
    		$('#wxRespMsgType').val(data.wxRespMsgType);
    		$('#publishType').val(data.publishType);
    		
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
///






