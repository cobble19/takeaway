$(function($) {
	
	/*$('#person form').find('input[name=username]').blur(function() {
		checkUsername($(this).val(), $('#person form').find("#usernameError"));
	});
	$('#enterprise form').find('input[name=username]').blur(function() {
		checkUsername($(this).val(), $('#enterprise form').find("#usernameError"));
	});*/
	

    jQuery.validator.addMethod("duplicateUsername", function(value, element) {    //用jquery ajax的方法验证电话是不是已存在
        var exist = true;
        $.ajax({
    		"url" : "web/user/exist",
    		"type" : "GET",
            async:false,                                             //同步方法，如果用异步的话，flag永远为1
    		"dataType" : 'json',
    		"data": ({
    			username: value
            }),
            success: function(data, textStatus, jqXHR ) {
            	if (data.success) {
            		exist = true;
//            		errorMsg.html('账号存在');
            		console.log('账号存在');
            	} else {
            		exist = false;
//            		errorMsg.html('账号不存在, 可以使用');
            		console.log('账号不存在, 可以使用');
            	}
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Ajax error');
            	alert('Ajax error');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
        });

        return !exist;

    }, "用户名不能重复");

    $("#person form").validate({
        errorPlacement: function(error, element) {
             error.insertAfter(element);
         },
         rules:{
             username:{
               required:true,
               duplicateUsername: true
             }
          },
          messages:{
        	  username:{
                  required:"用户名不能为空",
                  duplicateUsername:"用户名不能重复"
              }
          },
         debug:true
       })

    $("#enterprise form").validate({
        errorPlacement: function(error, element) {
             error.insertAfter(element);
         },
         rules:{
             username:{
               required:true,
               duplicateUsername: true
             }
          },
          messages:{
        	  username:{
                  required:"用户名不能为空",
                  duplicateUsername:"用户名不能重复"
              }
          },
         debug:true
       })

	
	/*var checkUsername = function(username, errorMsg) {
		var exist = false;
    	
    	$.ajax({
    		"url" : "web/user/exist",
    		"type" : "GET",
    		"async": true,
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
	}	*/
	
})