$(document).ready(function() {
	showDetail();
	
	$('#applyForm').validate();
	/*apply*/
	$('#applyBtn').click(function(e) {
		if (!$('#applyForm').valid()) {
			return ;
		}
    	e.preventDefault();
    	var username = $("#usernameX").val();
    	var phone = $("#phone").val();
    	var sex = $("input[type=radio][name=sex]:checked").val();
    	var description = $("#description").val();
    	var activityId = $("#activityId").val();
    	
		if (existApply()) {
			alert('号码：' + phone + '已报名成功，请勿重复报名！');
			return;
		}
    	
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
    			activityId: activityId,
    			sex: sex,
    			description: description
            }),
            success: function(data, textStatus, jqXHR ) {
            	console.log("data = " + data);
            	alert('恭喜您报名成功！')
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

var existApply = function() {
		var exist = false;
    	var username = $("#usernameX").val();
    	var phone = $("#phone").val();
    	var activityId = $("#activityId").val();
    	
    	$.ajax({
    		"url" : "../../web/person/apply/exist",
    		"type" : "POST",
    		"async": false,
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
            	if (data.success) {
            		exist = true;
            	} else {
            		exist = false;
            	}
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Ajax error');
            	alert('Ajax error');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});	// ajax
    	return exist;
}

var showDetail = function() {
	var activityId = getParam('activityId');
	var hidContent = getParam('hidContent');
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
			$("#title_1").html(data.title);
			/*$("#title_2").html(data.title);*/
        	$("#content").html(data.content);
			/*$("#content_1").html(data.content);*/
        	if (!!data.userPOJO) {
            	$('#publisher').text(data.userPOJO.nickname != null ? data.userPOJO.nickname : data.userPOJO.username);
            	/*$('#publisher_1').text(data.userPOJO.nickname != null ? data.userPOJO.nickname : data.userPOJO.username);*/
        	}
        	$('#organiser').text(data.usernameEnterprise);
        	/*$('#organiser_1').text(data.usernameEnterprise);*/
        	if ('1' == hidContent) {
        		$("#logoImg").show();
    			$("#logoImg").attr('src', $('#basePath').val() + "/" + data.logoImg);
        	} else {
        		$("#logoImg").hide();
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
