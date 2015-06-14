$(document).ready(function() {
	$('#editDiv').hide();
	$('#showDiv').show();
	showDetail();
	
	$('#addBtn').click(function(e) {
		e.preventDefault();
		$('#activityForm').submit();
	})
	
	/*apply*/
	$('#applyBtn').click(function(e) {
	    	e.preventDefault();
	    	var username = $("#username").val();
	    	var phone = $("#phone").val();
	    	var activityId = $("#activityId").val();
	    	
	    	$.ajax({
	    		"url" : "../../web/person/apply/add",
	    		"type" : "POST",
	    		/*"headers" : {
	    			"Content-Type" : "application/json"
	    		},*/
	    		"dataType" : 'json',
	    		"data": ({
	    			username: username,
	    			phone: phone,
	    			activityId: activityId
	            }),
	            success: function(data, textStatus, jqXHR ) {
	            	console.log("data = " + data);
	            	alert('添加成功！')
	            },
	            error: function(jqXHR, textStatus, errorThrown) {
	            	alert('Load Error!');
	            },
	            complete: function(jqXHR, textStatus) {
	            	console.log('Ajax complete.');
	            }
	    	});	// ajax
	    	//return false;
	})
	
	/*for edit*/
	$('#editBtn').click(function(e) {
		e.preventDefault();
		$('#editDiv').show();
		$('#showDiv').hide();
		$('#titleE').val($('#title').text());
		UE.getEditor('editor').setContent($('#content').html());
	})
	
	$('#backBtn').click(function(e) {
		e.preventDefault();
		$('#editDiv').hide();
		$('#showDiv').show();
		$('#titleE').val($('#title').text());
		UE.getEditor('editor').setContent($('#content').html());
	})
	
})

var showDetail = function() {
	var activityId = getParam('activityId');
	$.ajax({
		"url" : "../../web/enterprise/activity/" + activityId,
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
        	$("#activityId").html(data.activityId);
        	$("#title").html(data.title);
        	$("#content").html(data.content);
        	$('#publisher').text(data.userPOJO.username);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}