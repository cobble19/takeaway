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
//		format: 'Y-m-d H:i'
		value: '2015-02-20 20:20:20'
	});
	$('#endDateTime').datetimepicker({
		lang:'ch',
		timepicker:true,
//		format: 'Y-m-d H:i'
		value: '2015-02-20 21:22:23'
	});
	
//	$('#startDateTime, #endDateTime').change(function() {
//		var startDateTime = $('#startDateTime').val();
//		var endDateTime = $('#endDateTime').val();
//		if (startDateTime >= endDateTime) {
////			alert('结束时间不能小于开始时间');
//		}
//	})
//	showDetail();
	
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
	
	$('#addBtn').click(function(e) {
		e.preventDefault();

		var startDateTime = $('#startDateTime').val();
		var endDateTime = $('#endDateTime').val();
		if (startDateTime >= endDateTime) {
			alert('结束时间不能小于开始时间');
			return;
		}
		if ($('#voteForm').valid()) {
			$('#voteForm').submit();
		}
		return ;
	})
	
	/*getPrizeProvider();*/
})

var showDetail = function() {
	var voteId = getParam('voteId');
	$.ajax({
		"url" : $('#basePath').val() + "/api/unified/vote/" + voteId,
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
        	$('#logoImg').val(data.logoImg);
        	$('#activityId').val(data.activityId);
        	$('#period').val(data.period);
        	$('#numOfPeriod').val(data.numOfPeriod);
        	$('#apply2AttrModelIds').val(data.apply2AttrModelIds);
        	ue.setContent(data.content);
    		$('#voteType').val(data.voteType);
    		$('#publishType').val(data.publishType);
    		
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






