$(function($) {
	
	$('#person form').find('input[name=username]').blur(function() {
		checkUsername($(this).val(), $('#person form').find("#usernameError"));
	});
	$('#enterprise form').find('input[name=username]').blur(function() {
		checkUsername($(this).val(), $('#enterprise form').find("#usernameError"));
	});
	
	var checkUsername = function(username, errorMsg) {
		var exist = false;
    	
    	$.ajax({
    		"url" : "web/user/exist",
    		"type" : "GET",
    		"async": true,
    		/*"headers" : {
    			"Content-Type" : "application/json"
    		},*/
    		"dataType" : 'json',
    		"data": ({
    			username: username
            }),
            success: function(data, textStatus, jqXHR ) {
            	if (data.success) {
            		exist = true;
            		errorMsg.html('账号存在');
            	} else {
            		exist = false;
            		errorMsg.html('账号不存在, 可以使用');
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
	
})