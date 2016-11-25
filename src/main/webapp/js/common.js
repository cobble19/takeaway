$(document).ready(function() {
	$.ajaxSetup({
		"timeout": 300000
	});
//	$(document).ajaxSetup({
//		"timeout": 30000
//	});
	
	$(document).ajaxError(function(event, jqxhr, settings, exception) {
		console.log('global ajaxError');
//		alert('Global ajaxError, event: ' + event + ", jqxhr: " + jqxhr
//				+ ', setting: ' + settings + ", exception: " + exception);
		var confirm = window.confirm('页面访问异常， 请刷新.');
		if (!!confirm) {
			window.location.reload();
		}
		return;
		
//		alert('页面访问异常， 请刷新.');
//		window.location.reload();
//		alert('ajaxError');
//		window.location.href = $('#basePath').val() + "/login.jsp";
	});
})


showQrcode = function() {
		
	    var authorizerAppId = $('#authorizerAppId').val();
	    
	    /*if (authorizerAppId == null || authorizerAppId == "") {
	    	alert('微信公众号的ID为空， 请重新登录');
	    }*/
	    
	    
	    window.location.href = $('#basePath').val() + "/web/wx/oauth2/third/authorizer/qrcode?authorizerAppId=" + authorizerAppId;
    	
    	return ;
}


/*needSubscribe = function() {
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
		"dataType" : 'json',
		"data": JSON.stringify({
            title: $("#title").val()
        }),
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
}*/


isSubscribe = function() {
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


isWeiXin = function() {
	var ua = navigator.userAgent.toLowerCase();
    return /micromessenger/i.test(ua) || typeof navigator.wxuserAgent !== 'undefined';
}

getParam = getQueryStringRegExp = function(name) {
	var reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)", "i");
	if (reg.test(location.href)) {
		array = reg.exec(location.href);
		console.log(array);
		ret = ('' + array[2].toString()) /* | '' */;
		ret = decodeURI(ret);
		console.log("getParam ret = " + decodeURI(ret));
		return ret;
	}
	/* return decodeURL(RegExp.$2.replace(/+/g, " ")); */
	return "";
};

function getQueryString(name) {
	// 如果链接没有参数，或者链接中不存在我们要获取的参数，直接返回空
	if (location.href.indexOf("?") == -1
			|| location.href.indexOf(name + '=') == -1) {
		return '';
	}

	// 获取链接中参数部分
	var queryString = location.href.substring(location.href.indexOf("?") + 1);

	// 分离参数对 ?key=value&key2=value2
	var parameters = queryString.split("&");

	var pos, paraName, paraValue;
	for (var i = 0; i < parameters.length; i++) {
		// 获取等号位置
		pos = parameters[i].indexOf('=');
		if (pos == -1) {
			continue;
		}

		// 获取name 和 value
		paraName = parameters[i].substring(0, pos);
		paraValue = parameters[i].substring(pos + 1);

		// 如果查询的name等于当前name，就返回当前值，同时，将链接中的+号还原成空格
		if (paraName == name) {
			return decodeURL(paraValue.replace('/+/g', " "));
		}
	}
	return '';
};
