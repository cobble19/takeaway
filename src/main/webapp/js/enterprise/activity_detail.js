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
	
})

var onClickApply2Summit = function() {
		var form = $("form[id=apply2Form]");
		
		$(form).find('#apply2Btn').click(function(e) {
			var form1 = $(this).parents('form');
			
			if (!$(form1).valid()) {
//				alert('请正确输入');
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
		    
		})
		
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
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
        	var apply2AttrModels = data.data;
        	$.each(apply2AttrModels, function(i, e) {
        		var inputText = '<div class="form-group ">'
        						+ '<div class="col-sm-8">'
        						+ '<label class="control-label" for="' + 'attr' + i + '">' + (i + 1) + ". " + '<span>' + e.apply2AttrModelName + '</span>' + ':' + '</label>'
        						+ '<br>'
        						+ '<span style="font-size:10px;">' + e.apply2AttrModelRemark + '</span>'
        						+  '<textarea class="form-control" id="' + 'attr' + i + '" name="' + 'attr' + i + '" placeholder="请输入' + '' + e.apply2AttrModelName + '">'
        						+ '</textarea>'
        						+ '</div>'
//        						+ '<div class="col-sm-8">'
//        						+  '<input type="text" class="form-control" id="' + 'attr' + i + '" name="' + 'attr' + i + '" placeholder="请输入' + '' + e.apply2AttrModelName + '">'
//        						+ '</div>'
        						+'</div>'
        			;
        		$('#apply2Form div.form-group:nth-last-child(1)').before(inputText);
        		var form = $('#apply2Form'); // trigger
        	});
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
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
        	alert('Load Error!');
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
        	var publisher = data.userPOJO.nickname != null ? data.userPOJO.nickname : data.userPOJO.username;
        	if (!!data.userPOJO) {
            	$('#publisher').text(publisher);
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
        	
        	$(document).find("title").text(publisher + "|" + data.title);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
