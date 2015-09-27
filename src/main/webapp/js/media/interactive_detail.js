$(document).ready(function() {
	showDetail();
	
	$('#applyForm').validate();
	/*apply*/
	$('#applyBtn').click(function(e) {
		if (!$('#applyForm').valid()) {
			return ;
		}
    	e.preventDefault();
    	var answer = $("#answer").val();
    	var interactiveId = $("#interactiveId").val();
    	
		/*if (existApply()) {
			alert('号码：' + phone + '已报名成功，请勿重复报名！');
			return;
		}*/
    	
    	
    	
    	$.ajax({
    		"url" : "../../web/person/interactiveApply/add",
    		"type" : "POST",
    		/*"headers" : {
    			"Content-Type" : "application/json"
    		},*/
    		"dataType" : 'json',
    		"data": ({
    			answer: answer,
    			interactiveId: interactiveId
            }),
            success: function(data, textStatus, jqXHR ) {
            	console.log("data = " + data);
            	if (data.success) {
                	alert('已成功提交答案！');
            	} else {
            		alert(data.desc);
            	}
            	winnerSorter();
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
    	var interactiveId = $("#interactiveId").val();
    	
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
    			interactiveId: interactiveId
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
	var interactiveId = getParam('interactiveId');
	
	$.ajax({
		"url" : "../../web/enterprise/interactive/" + interactiveId,
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
        	$("#interactiveId").html(data.interactiveId);
        	$("#title").html(data.name);
        	$("#content").html(data.content);
        	$("#rule").html(data.rule);
        	$("#numOfWinner").html(data.numOfWinner);
        	if (!!data.userPOJO) {
            	$('#publisher').text(data.userPOJO.nickname != null ? data.userPOJO.nickname : data.userPOJO.username);
        	};
        	$("#startDateTime").html(new Date(data.startDateTime).format('Y/m/d H:i:s'));;
        	$("#endDateTime").html(new Date(data.endDateTime).format('Y/m/d H:i:s'));
        	
        	if (new Date(data.endDateTime) < new Date()) {
        		$('#errorMsg').html('活动已经结束， 欢迎下次参加。');
            	$('#answer').prop('disabled', true);
            	$('#applyBtn').prop('disabled', true);
            	$('#realAnswer').html(data.answer);
        	} else if (new Date(data.startDateTime) > new Date()) {
        		$('#errorMsg').html('活动还没有开始， 请关注开始时间。');
            	$('#answer').prop('disabled', true);
            	$('#applyBtn').prop('disabled', true);
        	} else {
        		$('#errorMsg').html('活动正在进行中。。。');
            	$('#answer').prop('disabled', false);
            	$('#applyBtn').prop('disabled', false);
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
	
	winnerSorter();

	
}

var winnerSorter = function() {

	var interactiveId = getParam('interactiveId');
	
	$.ajax({
		"url" : "../../web/person/interactive/" + interactiveId + "/apply",
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
        	var interactiveApplyPOJOs = data.data;
        	var html = "<ul>";
        	for (var i = 0; i < interactiveApplyPOJOs.length; i++) {
            	html += "<li>" + (i + 1) + ". " + (interactiveApplyPOJOs[i].nickname == null ? interactiveApplyPOJOs[i].username : interactiveApplyPOJOs[i].nickname) + "</li>"
        	}
        	html += "</ul>";
        	$("#interactiveApplyContent").html(html);
        	/*$("#title").html(data.name);
        	$("#content").html(data.content);*/
        	/*if (!!data.userPOJO) {
            	$('#publisher').text(data.userPOJO.nickname != null ? data.userPOJO.nickname : data.userPOJO.username);
        	}*/
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
	
}





