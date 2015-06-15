$(document).ready(function() {
	showDetail();
	
	/*apply*/
	$('#applyBtn').click(function(e) {
	    	e.preventDefault();
	    	var username = $("#usernameX").val();
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