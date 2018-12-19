var ue;

$(document).ready(function() {
	if (!ue) {
		ue = UE.getEditor('editor', {
			initialFrameHeight: 400
		});
		ue.ready(function() {
			var buyAbout = $('#buyAbout').val();
			ue.setContent(buyAbout);
		});
	}

	$('#startDateTime').datetimepicker({
		lang:'ch',
		timepicker:true,
		/*format: 'Y/m/d H:i',*/
		value: ''
	});
	$('#endDateTime').datetimepicker({
		lang:'ch',
		timepicker:true,
		/*format: 'Y/m/d H:i'*/
		value: ''
	});

	/*$('#startDateTime, #endDateTime').change(function() {
		var startDateTime = $('#startDateTime').val();
		var endDateTime = $('#endDateTime').val();
		if (startDateTime >= endDateTime) {
//			alert('结束时间不能小于开始时间');
		}
	})*/
	
	$('#ecProductForm').validate();

	$('#ecProductForm').find('#uploadBtn').click(function(e) {
//		var form1 = $(this).parents('form');
		var div = $(this).parent('div');
		var formData;
	    formData = new FormData();

	    var userId = $('#userId').val();
	    formData.append('pic', div.find('input[name=pic]').get(0).files[0]);
	    formData.append('userId', userId);
	    /*formData.append('wxTemplateId', 999);
	    formData.append('orderNo', 999);*/
	    $.ajax({
	        url: $('#basePath').val() + '/htmleditor/pic/add',
	        contentType:"multipart/form-data",
	        data: formData,
	        processData: false,
	        type: 'POST',
	        contentType: false, // tell jQuery not to set contentType
	        success : function(data) {
	            alert('上传图片成功');
	            div.find('#imgUrl').val(data.file_url);
	        }
	    });	// ajax end
	    
	})	// upload pic to picture files

    $('#ecProductForm').find('#upload2Btn').click(function(e) {
//		var form1 = $(this).parents('form');
        var div = $(this).parent('div');
        var formData;
        formData = new FormData();

        var userId = $('#userId').val();
        formData.append('pic2', div.find('input[name=pic2]').get(0).files[0]);
        formData.append('userId', userId);
        /*formData.append('wxTemplateId', 999);
        formData.append('orderNo', 999);*/
        $.ajax({
            url: $('#basePath').val() + '/htmleditor/pic/add',
            contentType:"multipart/form-data",
            data: formData,
            processData: false,
            type: 'POST',
            contentType: false, // tell jQuery not to set contentType
            success : function(data) {
                alert('上传图片2成功');
                div.find('#img2Url').val(data.file_url);
            }
        });	// ajax end

    })	// upload pic to picture files
	
	$('#addBtn').click(function(e) {
		e.preventDefault();

		var startDateTime = $('#startDateTime').val();
		var endDateTime = $('#endDateTime').val();
		if (startDateTime >= endDateTime) {
			alert('结束时间不能小于开始时间');
			return;
		}
		if ($('#ecProductForm').valid()) {
			$('#ecProductForm').submit();
		}
		return ;
	});

	// console.log(new Date($('#startDateTimeX').val().replace('CST', '')).format('Y/m/d H:i'));
    $('#startDateTime').val(new Date($('#startDateTimeX').val().replace('CST', '')).format('Y/m/d H:i'));
    $('#endDateTime').val(new Date($('#endDateTimeX').val().replace('CST', '')).format('Y/m/d H:i'));
	
})






