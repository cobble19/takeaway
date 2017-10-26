
$(document).ready(function() {
	
	$('#progress').dialog({
    	autoOpen: false,
    	modal: true
    });

    $('#updateInfoBtn').click(function() {
    	var confirm = window.confirm('确定修改');
    	if (!confirm) {
    		return;
    	}
    	/*var username = $('#profile').find('#username').val();*/
    	var nickname = $('#profile').find('#nickname').val();
    	var email = $('#profile').find('#email').val();
    	var userId = $('#userId').val();
    	
    	$.ajax({
    		"url" : $('#basePath').val() + "/api/user/updateInfo",
    		"type" : "POST",
    		/*"headers" : {
    			"Content-Type" : "application/json"
    		},*/
    		"dataType" : 'json',
//    		traditional :true, 
    		"data": {
                /*"username": username,*/
                "nickname": nickname,
                "email": email,
                "userId": userId
            },
            success: function(data, textStatus, jqXHR ) {
            	alert('修改成功');
//            	window.location.reload();
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});
    })

    
} );





