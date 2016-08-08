$(document).ready(function() {
	showDetail();
	
	$('#applyForm').find('#uploadBtn').click(function(e) {
		var form1 = $(this).parents('form');
		var div = $(this).parent('div');
		var formData;
	    formData = new FormData();

	    var userId = $('#userId').val();
	    formData.append('pic', div.find('input[name=pic]').get(0).files[0]);
	    formData.append('userId', userId);
	    /*formData.append('wxTemplateId', 999);
	    formData.append('orderNo', 999);*/
	    $.ajax({
	        url: '../../htmleditor/pic/add',
	        contentType:"multipart/form-data",
	        data: formData,
	        processData: false,
	        type: 'POST',
	        contentType: false, // tell jQuery not to set contentType
	        success : function(data) {
	            alert('上传成功');
	            div.find('#imgUrlX').val(data.file_url);
	        }
	    });	// ajax end
	    
	})	// upload pic to picture files
	
	/*$('#applyForm').validate();*/
	/*apply*/
	$('#applyBtn').click(function(e) {
		if (!$('#applyForm').valid()) {
			return ;
		}
    	e.preventDefault();
    	var title = $("#titleX").val();
    	var imgUrl = $("#imgUrlX").val();
    	var description = $("#descriptionX").val();
    	var voteId = $("#voteId").val();
    	
		/*if (existApply()) {
			alert('号码：' + phone + '已报名成功，请勿重复报名！');
			return;
		}*/
    	
    	$.ajax({
    		"url" : "../../api/media/voteItem/addOrUpdate",
    		"type" : "POST",
    		/*"headers" : {
    			"Content-Type" : "application/json"
    		},*/
    		"dataType" : 'json',
    		"data": ({
    			title: title,
    			imgUrl: imgUrl,
    			description: description,
    			totalNum: 0,
    			voteId: voteId
            }),
            success: function(data, textStatus, jqXHR ) {
            	console.log("data = " + data);
            	alert('恭喜您报名成功！')
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});	// ajax
    	//return false;
	})
	
})

/*var existApply = function() {
		var exist = false;
    	var username = $("#usernameX").val();
    	var phone = $("#phone").val();
    	var activityId = $("#activityId").val();
    	
    	$.ajax({
    		"url" : "../../web/person/apply/exist",
    		"type" : "POST",
    		"async": false,
    		"headers" : {
    			"Content-Type" : "application/json"
    		},
    		"dataType" : 'json',
    		"data": ({
    			username: username,
    			phone: phone,
    			activityId: activityId
            }),
            success: function(data, textStatus, jqXHR ) {
            	if (data.success) {
            		exist = true;
            	} else {
            		exist = false;
            	}
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Ajax error');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});	// ajax
    	return exist;
}*/

var showDetail = function() {
	var activityId = getParam('voteId');
	$.ajax({
		"url" : "../../api/media/vote/" + activityId,
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
        	$("#voteId").html(data.voteId);
        	$("#title").html(data.title);
        	$("#content").html(data.content);
        	$("#voteType").html(data.voteType);
        	$("#publishType").html(data.publishType);
        	/*if (!!data.userPOJO) {
            	$('#publisher').text(data.userPOJO.nickname != null ? data.userPOJO.nickname : data.userPOJO.username);
        	}*/
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
