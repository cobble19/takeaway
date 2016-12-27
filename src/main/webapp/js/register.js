$(document).ready(function() {
	
	/*$('#person form').find('input[name=username]').blur(function() {
		checkUsername($(this).val(), $('#person form').find("#usernameError"));
	});
	$('#enterprise form').find('input[name=username]').blur(function() {
		checkUsername($(this).val(), $('#enterprise form').find("#usernameError"));
	});*/
	
    jQuery.validator.addMethod("duplicateUsername", function(value, element) {    //用jquery ajax的方法验证电话是不是已存在
        var exist = true;
        $.ajax({
    		"url" : $('#basePath').val() + "/web/user/exist",
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
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
        });

        return !exist;

    }, "用户名不能重复");

    jQuery.validator.addMethod("duplicateNickname", function(value, element) {    //用jquery ajax的方法验证电话是不是已存在
        var exist = true;
        $.ajax({
    		"url" : $('#basePath').val() + "/web/user/nickname/exist",
    		"type" : "GET",
            async:false,                                             //同步方法，如果用异步的话，flag永远为1
    		"dataType" : 'json',
    		"data": ({
    			nickname: value
            }),
            success: function(data, textStatus, jqXHR ) {
            	if (data.success) {
            		exist = true;
//            		errorMsg.html('账号存在');
            		console.log(value + '昵称存在');
            	} else {
            		exist = false;
//            		errorMsg.html('账号不存在, 可以使用');
            		console.log(value + '昵称不存在, 可以使用');
            	}
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Ajax error');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
        });

        return !exist;

    }, "昵称不能重复");
    
    $('#person form').find('#registerBtn').click(function(e) {
    	e.preventDefault();
    	console.log('person form');
    	
    	var form = $('#person form');
    	var username = form.find('input[name=username]').val();
    	var nickname = form.find('input[name=nickname]').val();
    	var password = form.find('input[name=password]').val();
    	var email = form.find('input[name=email]').val();
    	$.ajax({
    		"url" : $('#basePath').val() + "/web/user/person/reg",
    		"type" : "POST",
            async:true,                                             
    		"dataType" : 'json',
    		"data": ({
    			username: username,
    			nickname: nickname,
    			password: password,
    			email: email
            }),
            success: function(data, textStatus, jqXHR ) {
        		console.log(data);
        		if (data.success) {
        			window.location.href = $('#basePath').val() + '/web/regSuccess';
        		} else {
        			alert(data.desc);
        		}
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Ajax error');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
        });
    })
    
    $('#enterprise form').find('#registerBtn').click(function(e) {
    	e.preventDefault();
    	console.log('enterprise form');
    	
    	var form = $('#enterprise form');
    	var username = form.find('input[name=username]').val();
    	var nickname = form.find('input[name=nickname]').val();
    	var password = form.find('input[name=password]').val();
    	var email = form.find('input[name=email]').val();
    	$.ajax({
    		"url" : $('#basePath').val() + "/api/user/enterprise/reg",
    		"type" : "POST",
            async:true,                                             
    		"dataType" : 'json',
    		"data": ({
    			username: username,
    			nickname: nickname,
    			password: password,
    			email: email
            }),
            success: function(data, textStatus, jqXHR ) {
        		console.log(data);
        		if (data.success) {
        			//window.location.href = $('#basePath').val() + '/web/regSuccess';
        			//@09/27/2016, redirect to wxComLogin
        			window.location.href = data.wxComLoginUrl;
        		} else {
        			alert(data.desc);
        		}
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Ajax error');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
        });
    })

    $("#person form").validate({
        errorPlacement: function(error, element) {
             error.insertAfter(element);
         },
         rules:{
             username:{
               required:true,
               duplicateUsername: true
             },
             nickname:{
                 required:true,
                 duplicateNickname: true
             }
          },
          messages:{
        	  username:{
                  required:"用户名不能为空",
                  duplicateUsername:"用户名不能重复"
              },
              nickname:{
                  required:"昵称不能为空",
                  duplicateUsername:"昵称不能重复"
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
             },
             nickname:{
                 required:true,
                 duplicateNickname: true
             }
          },
          messages:{
        	  username:{
                  required:"用户名不能为空",
                  duplicateUsername:"用户名不能重复"
              },
              nickname:{
                  required:"昵称不能为空",
                  duplicateUsername:"昵称不能重复"
              }
          },
         debug:true
       })
       
       

   	clearInput();

	
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
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});	// ajax
    	return exist;
	}	*/
	
})

function clearInput() {
	$('#nickname').val("");
	$('#password').val("");
	
}





