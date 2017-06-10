$(document).ready(function() {
	/*showDetail();*/
	
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
    		"url" : "../../api/unified/voteItem/addOrUpdate",
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
            	alert('恭喜您添加投票选项成功！')
            	window.location.reload();
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
	
    $('#progress').dialog({
    	autoOpen: false,
    	modal: true
    });
	
	$('input[type=button].btn4del').click(function(e) {
		var voteItemId = $(this).attr('voteItemId');
		var ids = [];
		ids.push(voteItemId);
    	console.log('ids: ' + ids);
    	if (ids == null || ids.length <= 0) {
    		alert('请选择一条记录');
    		return;
    	}

    	var confirm = window.confirm('确定删除');
    	if (!confirm) {
    		return;
    	}

    	$('#progress').dialog('open');
    	
    	$.ajax({
    		"url" : "../../mgr/voteItem/delete",
    		"type" : "GET",
    		"headers" : {
    			"Content-Type" : "application/json"
    		},
    		"dataType" : 'json',
    		traditional :true, 
    		"data": {
                "ids": ids
            },
            success: function(data, textStatus, jqXHR ) {
            	$('#progress').dialog('close');
            	window.location.reload();
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});
    })
	
})

var showDetail = function() {
	var voteId = getParam('voteId');
	$.ajax({
		"url" : "../../api/unified/vote/" + voteId,
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
