$(document).ready(function() {
	
    $('#loginForm').find('#loginBtn').click(function(e) {
    	e.preventDefault();
    	console.log('loginForm form');
    	
    	var canLogin = false;
    	var errorMsg = "";
    	
    	var form = $('#loginForm');
    	var username = form.find('input[name=j_username]').val();
    	var password = form.find('input[name=j_password]').val();
    	$.ajax({
    		"url" : $('#basePath').val() + "/web/user/check",
    		"type" : "POST",
            async:false,                                             
    		"dataType" : 'json',
    		"data": ({
    			username: username,
    			password: password
            }),
            success: function(data, textStatus, jqXHR ) {
        		if (data.success) {
        			if (data.errorCode == '0') {
        				canLogin = true;
        			} else if (data.errorCode == '1') {
        				canLogin = false;
        				errorMsg = "用户名不存在";
        			} else if (data.errorCode == '2') {
        				canLogin = false;
        				errorMsg = "密码不正确"
        			}
        		} else {
        			errorMsg = data.desc;
        		}
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Ajax error');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
        });
    	
    	if (canLogin) {
    		form.submit();
    	} else {
    		$('#errorMsg').html(errorMsg);
    	}
    	
    })

	
})