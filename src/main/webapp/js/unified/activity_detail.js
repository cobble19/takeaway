$(document).ready(function() {
	showDetail();
	
	/*$('#applyForm').validate();
	$('#applyBtn').click(function(e) {
    	e.preventDefault();
		
    	//return false;
	})*/
	showApply2();
	onClickApply2Summit();
	$('#apply2Form').validate();
	$('#apply2Btn').click(function(e) {
    	e.preventDefault();
		
    	//return false;
	})
	

    $('#qrcodeDiv').dialog({
    	autoOpen: false,
    	modal: true
    });
	
})

var showQrcode = function() {
		
	    var authorizerAppId = $('#authorizerAppId').val();
	    
	    /*if (authorizerAppId == null || authorizerAppId == "") {
	    	alert('微信公众号的ID为空， 请重新登录');
	    }*/
	    
	    var url = $('#basePath').val() + "/web/wx/oauth2/third/authorizer/qrcode?authorizerAppId=" + authorizerAppId
	    
	    if ('wx483bd8288ebe84b4' != authorizerAppId) {
	    	url += '&' + 'errorCode=NOTVIP'
	    }
	    
	    window.location.href = url;
    	
    	return ;
}

var needSubscribe = function() {
	var needSubscribeFlag = false;
	var activityId = getParam('activityId');
	var hidContent = getParam('hidContent');
	$.ajax({
		"url" : $('#basePath').val() + "/web/enterprise/activity/" + activityId,
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"async": false,
		"timeout": 300000,
		/*"dataType" : 'json',*/
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
        	needSubscribeFlag = !!data.needSubscribe;
        	
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
	
	return needSubscribeFlag;
}

var isSubscribe = function() {
		var exist = false;
		
	    var unionId = $('#unionId').val();
	    var authorizerAppId = $('#authorizerAppId').val();
	    var openId = $('#openId').val();
	    
	    /*if (unionId == null || authorizerAppId == "") {
	    	alert("微信用户的联合ID为空， 请重新登录");
	    	return;
	    }
	    if (authorizerAppId == null || authorizerAppId == "") {
	    	alert("微信公众号ID为空， 请重新登录");
	    	return;
	    }*/
    	
	    var params = {
	    		
	    };

    	params.unionId = unionId;
    	params.authorizerAppId = authorizerAppId;
    	params.openId = openId;
    	
    	$.ajax({
    		"url" : $('#basePath').val() + "/api/wx/oauth2/third/web/subscribe",
    		"type" : "POST",
    		"async": false,
    		/*"headers" : {
    			"Content-Type" : "application/json"
    		},*/
//    		"timeout": 300000,
    		"dataType" : 'json',
    		"data": params,
            success: function(data, textStatus, jqXHR ) {
            	if (data.success) {
            		exist = true;
            	} else {
            		exist = false;
            	}
            	
            	var needSubscribeFlag = needSubscribe();
            	
            	if (!needSubscribeFlag) {
            		exist = true;
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
}

var existApply2 = function() {
		var exist = false;
		
	    var activityId = $('#activityId').val();
    	
	    inputTexts = $('#apply2Form div.form-group textarea.form-control');
	    var apply2AttrPOJOs = [];
	    /*var count = 0;*/
	    
//	    var userId = $('#userId').val();
	    
	    console.log('inputTexts length: ' + inputTexts.length);
	    
	    var params = {
	    		
	    };

    	/*var inputText = inputTexts[1];	// 第2个是手机号
//    	params.apply2AttrModelName = $(inputText).prev().prev().prev().children('span').html();
    	params.apply2AttrData = $(inputText).val();
    	params.orderNo = 1;*/
    	params.activityId = activityId;
    	
    	$.ajax({
    		"url" : $('#basePath').val() + "/api/apply2/existByUnionId",
    		"type" : "POST",
    		"async": false,
    		/*"headers" : {
    			"Content-Type" : "application/json"
    		},*/
    		"dataType" : 'json',
    		"data": params,
            success: function(data, textStatus, jqXHR ) {
            	if (data.success) {
            		exist = true;
            	} else {
            		exist = false;
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
}

var onClickApply2Summit = function() {
		var form = $("form[id=apply2Form]");
		
		$(form).find('#apply2Btn').click(function(e) {
			var form1 = $(this).parents('form');
			
			if (!$(form1).valid()) {
//				alert('请正确输入');
				return;
			}
			
			var exist = existApply2();
			if (exist) {
				alert('请勿重复提交。')
				return;
			}
			
			var subscribe = isSubscribe();
			if (!subscribe) {
//				alert('请关注该活动发布方微信公众号');
				/// 跳出微信qrcode进行关注
				
				var authorizerAppId = $('#authorizerAppId').val();
			    if ('wx483bd8288ebe84b4' != authorizerAppId) {
			    	confirm = window.confirm('您还不是该公众号的会员，请关注该公众号并加入会员');
			    } else {
			    	confirm = window.confirm('提交信息，需要关注公众号， 是否关注');
			    }
			    
				if (confirm) {
					showQrcode();
				} else {
					return;
				}
				
				return;
			}
			
//			var formData;
//		    formData = new FormData();
		    
		    
		    inputTexts = $('#apply2Form div.form-group textarea.form-control');
		    var apply2AttrPOJOs = [];
		    /*var count = 0;*/
		    
//		    var userId = $('#userId').val();
		    var activityId = $('#activityId').val();
		    
		    console.log('inputTexts length: ' + inputTexts.length);

		    for (var i = 0; i < inputTexts.length; i++) {
		    	var inputText = inputTexts[i];
		    	var apply2AttrPOJO = {};
		    	apply2AttrPOJO.apply2AttrModelName = $(inputText).prev().prev().prev().children('span').html();
		    	apply2AttrPOJO.apply2AttrData = $(inputText).val();
		    	apply2AttrPOJO.orderNo = i;
		    	apply2AttrPOJO.activityId = activityId;
		    	apply2AttrPOJOs.push(apply2AttrPOJO);
		    }
		    
		    var basePath = $("#basePath").val();
//		    formData.append('apply2AttrModelPOJOs', apply2AttrModelPOJOs);
		    
		    console.log("apply2AttrPOJOs: " + JSON.stringify(apply2AttrPOJOs));
		    
		    $.ajax({
		        url: $("#basePath").val() + '/api/apply2/add',
		        data: JSON.stringify(apply2AttrPOJOs),
//		        processData: false,
		        type: 'POST',
		        contentType: "application/json",
		        success : function(data) {
		        	if (data.success) {
			            alert('创建申请人信息成功');
		        	} else {
			            alert('创建申请人信息失败！！！msg：' + data.desc);
		        	}

		    		/*$('#apply2Div').dialog('close');*/
		        }
		    });	// ajax end
		    
		});
		
}

var showApply2 = function() {
	var activityId = getParam('activityId');
	var hidContent = getParam('hidContent');
	$.ajax({
		"url" : $('#basePath').val() + "/api/apply2AttrModel/" + activityId,
		"type" : "GET",
		/*"headers" : {
			"Content-Type" : "application/json"
		},*/
		"dataType" : 'json',
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
		"timeout": 300000,
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
        	
        	var apply2AttrModels = data.data;
        	
        	if (apply2AttrModels == undefined || apply2AttrModels == null || apply2AttrModels.length == 0) {
        		$('#apply2Div').hide();
        		$('#lineSplit').hide();
        	} else {
            	$.each(apply2AttrModels, function(i, e) {
            		var inputText = '<div class="form-group ">'
            						+ '<div class="col-sm-8">'
            						+ '<label class="control-label" for="' + 'attr' + i + '">' + (i + 1) + ". " + '<span>' + e.apply2AttrModelName + '</span>' + ':' + '</label>'
            						+ '<br>'
            						+ '<p style="font-size:10px;">' + e.apply2AttrModelRemark + '</p>'
            						+  '<textarea class="form-control" required="required" id="' + 'attr' + i + '" name="' + 'attr' + i + '" placeholder="请输入' + '' + e.apply2AttrModelName + '">'
            						+ '</textarea>'
            						+ '</div>'
//            						+ '<div class="col-sm-8">'
//            						+  '<input type="text" class="form-control" id="' + 'attr' + i + '" name="' + 'attr' + i + '" placeholder="请输入' + '' + e.apply2AttrModelName + '">'
//            						+ '</div>'
            						+'</div>'
            			;
            		$('#apply2Form div.form-group:nth-last-child(1)').before(inputText);
            		var form = $('#apply2Form'); // trigger
            	});
        	}
        	
        	
        	window.setTimeout(function() {
            	var subscribe = isSubscribe();
            	if (!subscribe) {
//            		alert('请关注该活动发布方微信公众号');
            		/// 跳出微信qrcode进行关注
            		
					var authorizerAppId = $('#authorizerAppId').val();
				    if ('wx483bd8288ebe84b4' != authorizerAppId) {
				    	confirm = window.confirm('您还不是该公众号的会员，请关注该公众号并加入会员');
				    } else {
				    	confirm = window.confirm('提交信息，需要关注公众号， 是否关注');
				    }
			    
            		if (confirm) {
            			showQrcode();
            		} else {
            			return;
            		}
            		
            		return;
            	}
        	}, 1000);
        	
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var addApply = function() {
	if (!$('#applyForm').valid()) {
		return ;
	}
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
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});	// ajax
}

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
		"timeout": 300000,
		/*"dataType" : 'json',*/
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
//        	$("#authorizerAppId").html(data.wxAuthorizerInfoPOJO.authorizerAppId);
        	$("#activityId").html(data.activityId);
        	$("#title").html(data.title);
			/*$("#title_1").html(data.title);*/
			/*$("#title_2").html(data.title);*/
        	$("#content").html(data.content);
			/*$("#content_1").html(data.content);*/
        	var publisher = "";
        	if (!!data.userPOJO) {
            	publisher = data.userPOJO.nickname != null ? data.userPOJO.nickname : data.userPOJO.username;
            	$('#publisher').text(publisher);
            	/*$('#publisher_1').text(data.userPOJO.nickname != null ? data.userPOJO.nickname : data.userPOJO.username);*/
        	}
        	$('#organiser').text(data.usernameEnterprise);
        	var createDateTime = data.createDateTime;
        	if (createDateTime != null) {
            	$('#createDateTime').text(new Date(createDateTime).format('Y-m-d'));
        	}
        	/*$('#organiser_1').text(data.usernameEnterprise);*/
        	if ('1' == hidContent) {
        		$("#logoImg").show();
    			$("#logoImg").attr('src', $('#basePath').val() + "/" + data.logoImg);
        	} else {
        		$("#logoImg").hide();
        	}
        	
        	$(document).find("title").text(publisher + "|" + data.title);
        	
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
