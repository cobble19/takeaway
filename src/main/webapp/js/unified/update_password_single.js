$(document).ready(function() {
	$('#pwdChg').click(function() {
		var userId = $('#update_password').find('#userId').val();
		var password = $('#update_password').find('#password').val();
		var passwordOld = $('#update_password').find('#passwordOld').val();
		console.log(password + $('#password').val() + " " + $('#password').text()+ " " + $('#password').html())
    	$.ajax({
    		"url" : $('#basePath').val() + "/api/user/updatePassword",
    		"type" : "POST",
    		/*"headers" : {
    			"Content-Type" : "application/json"
    		},*/
    		/*"dataType" : 'json',*/
    		"data": ({
    			userId: userId,
    			password: password,
    			passwordOld: passwordOld
            }),
            success: function(data, textStatus, jqXHR ) {
            	console.log("data = " + data);
            	if (data.success) {
                	alert('修改密码成功！')
            	} else {
                	alert('修改密码失败！ 可能是旧密码不正确！');
            	}
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	alert('加载失败!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});	// ajax
	})	//#pwdChg click end
    
    
} );



 