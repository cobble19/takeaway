$(document).ready(function() {
	showDetail();
	
	var forms = $('form');
	for (var i = 0; i < forms.length; i++) {
		form = forms.get(i);
		$(form).validate();
//		console.log($(form).valid());
		$(form).find('#uploadBtn').click(function(e) {
			var form1 = $(this).parents('form');
			var formData;
		    formData = new FormData();
		    formData.append('pic', form1.find('input[name=pic]').get(0).files[0]);

		    $.ajax({
		        url: '../../htmleditor/pic/add',
		        contentType:"multipart/form-data",
		        data: formData,
		        processData: false,
		        type: 'POST',
		        contentType: false, // tell jQuery not to set contentType
		        success : function(data) {
		            alert('上传成功');
		            form1.find('#imgSrc').val(data.file_url);
		        }
		    });	// ajax end
		    
		})	// upload pic to picture files
		
		$(form).find('#addBtn').click(function(e) {
			var form1 = $(this).parents('form');
			
			if (!$(form1).valid()) {
//				alert('请正确输入');
				return;
			}
			
			var formData;
		    formData = new FormData();
		    var imgSrc = $(form1.find('input[name=imgSrc]').get(0)).val();
		    
		    if (imgSrc == null || imgSrc == "") {
		    	alert("请上传图片");
		    	return;
		    }
		    
		    formData.append('title', $(form1.find('input[name=title]').get(0)).val());
		    formData.append('imgSrc', imgSrc);
		    formData.append('linkUrl', $(form1.find('input[name=linkUrl]').get(0)).val());
		    formData.append('display', 1/*$(form1.find('input[name=display]').get(0)).val()*/);
		    formData.append('orderNo', $(form1).attr('id').replace(new RegExp("[A-Za-z]+","g"),"")/*$(form1.find('input[name=orderNo]').get(0)).val()*/);
		    $.ajax({
		        url: '../../web/media/wxLink/add',
		        contentType:"application/x-www-form-urlencoded",
		        data: formData,
		        processData: false,
		        type: 'POST',
		        contentType: false, // tell jQuery not to set contentType
		        success : function(data) {
		            alert('创建链接成功');
		        }
		    });	// ajax end
		    
		})
		
	}
	
	/*$('#uploadBtn').click(function() {
		var formData;
	    formData = new FormData();
	    formData.append('pic', $('input[name=pic]').get(0).files[0]);
	
	    $.ajax({
	        url: '../../htmleditor/pic/add',
	        contentType:"multipart/form-data",
	        data: formData,
	        processData: false,
	        type: 'POST',
	        contentType: false, // tell jQuery not to set contentType
	        success : function(data) {
	            alert('上传成功');
	            $('#imgSrc').val(data.file_url);
	        }
	    });
    
	})*/
	
	/*$('#wxLinkForm').validate();
	$('#addBtn').click(function() {
		if ($('#wxLinkForm').valid()) {

			var formData;
		    formData = new FormData();
		    formData.append('pic', $('input[name=pic]').get(0).files[0]);
		
		    $.ajax({
		        url: '../../htmleditor/pic/add',
		        contentType:"multipart/form-data",
		        data: formData,
		        processData: false,
		        type: 'POST',
		        contentType: false, // tell jQuery not to set contentType
		        success : function(data) {
		            alert('上传成功');
		            $('#imgSrc').val(data.file_url);
		        }
		    });
		}
		return ;
	});*/
})


var showDetail = function() {
	$.ajax({
		"url" : $('#basePath').val() + "/web/media/wxLink/list",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		/*"dataType" : 'json',*/
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);

			var forms = $('form');
        	if (data.gridModelList != null && data.gridModelList.length > 0) {
        		for (var i = 0; i < data.gridModelList.length; i++) {
        			wxLink = data.gridModelList[i];
        			if (i >= 9) {
        				break;
        			}
        			
        			for (var j = 0; j < forms.length; j++) {
        				form = forms.get(j);
        				orderNo = $(form).attr('id').replace(new RegExp("[A-Za-z]+","g"),"");
//        				console.log('orderNo:' + orderNo);
        				if (orderNo == wxLink.orderNo) {
            				$(form).find('input[name=title]').val(wxLink.title);
            				$(form).find('input[name=imgSrc]').val(wxLink.imgSrc);
            				$(form).find('input[name=linkUrl]').val(wxLink.linkUrl);
        				}
        			}
        			
        		}
        	}
//        	$("#activityId").html(data.activityId);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}


