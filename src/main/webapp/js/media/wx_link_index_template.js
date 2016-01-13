$(document).ready(function() {
	showDetail();
	mouseOverSec();
	clickSec();
	
    $('#wxLinkDiv').dialog({
    	autoOpen: false,
    	modal: true
    });
    
    onClickWxTemplateIdRd();
    
    onClickDeploy();
    
    $('#progress').dialog({
    	autoOpen: false,
    	modal: true
    });
})

var onClickDeploy = function() {
	$('#deployHtml').click(function() {
		$('#progress').dialog('open');
		var wxTemplateId = $('input[name=wxTemplateId]:checked').val()
	    console.log("wxTemplateId: " + wxTemplateId);
		
		$.ajax({
			"url" : $('#basePath').val() + "/web/media/wxLink/toHtml",
			"type" : "GET",
			"headers" : {
				"Content-Type" : "application/json"
			},
			"dataType" : 'json',
			"data": {
				wxTemplateId: wxTemplateId
	        },
	        success: function(data, textStatus, jqXHR ) {
	        	console.log("data = " + data);
	        	alert('发布静态页面成功');
	        	$('#progress').dialog('close');
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	        	alert('Load Error!');
	        },
	        complete: function(jqXHR, textStatus) {
	        	console.log('Ajax complete.');
	        }
		});
	})
}

var onClickWxTemplateIdRd = function() {
	$('input[name=wxTemplateId]').click(function() {
		var wxTemplateId = $(this).val();
		window.location.href = $('#basePath').val() + "/web/media/wxTemplate" + "?wxTemplateId=" + wxTemplateId;
	})
}

var clickSec = function() {
	$('div.sec').click(function() {
		$('#wxLinkDiv').dialog('open');
		orderNo = $(this).attr('id').replace(new RegExp("[A-Za-z]+","g"),"");
//		console.log('orderNo:' + orderNo);
		/*if (orderNo == wxLink.orderNo) {
			$(form).find('input[name=title]').val(wxLink.title);
			$(form).find('input[name=imgSrc]').val(wxLink.imgSrc);
			$(form).find('input[name=linkUrl]').val(wxLink.linkUrl);
		}*/
		
		var title = $(this).find('img').attr('alt');
		var imgSrc = $(this).find('img').attr('src');
		var linkUrl = $(this).find('input[name=linkUrl]').text();
		console.log(title);
		console.log(imgSrc);
		console.log(linkUrl);
		console.log(orderNo);
		
	    var basePath = $("#basePath").val();
	    if (imgSrc.indexOf(basePath) > -1) {
	    	imgSrc = imgSrc.substring(basePath.length + 1);
	    }
		
		var form = $("form[id=wxLinkForm]");
		$(form).find('input[name=title]').val(title);
		$(form).find('input[name=imgSrc]').val(imgSrc);
		$(form).find('input[name=linkUrl]').val(linkUrl);
		$(form).find('input[name=orderNo]').val(orderNo);
		
		$(form).validate();
//		console.log($(form).valid());
		$(form).find('#uploadBtn').click(function(e) {
			var form1 = $(this).parents('form');
			var formData;
		    formData = new FormData();
		    formData.append('pic', form1.find('input[name=pic]').get(0).files[0]);

		    $.ajax({
		        url: '../../htmleditor/pic/add',
		        contentType:"multipart/form-data",
		        data: formData,
		        processData: false,
		        type: 'POST',
		        contentType: false, // tell jQuery not to set contentType
		        success : function(data) {
		            alert('上传成功');
		            form1.find('#imgSrc').val(data.file_url);
		        }
		    });	// ajax end
		    
		})	// upload pic to picture files
		
		$(form).find('#addBtn').click(function(e) {
			var form1 = $(this).parents('form');
			
			if (!$(form1).valid()) {
//				alert('请正确输入');
				return;
			}
			
			var formData;
		    formData = new FormData();
		    var imgSrc = $(form1.find('input[name=imgSrc]').get(0)).val();
		    
		    if (imgSrc == null || imgSrc == "") {
		    	alert("请上传图片");
		    	return;
		    }
		    
		    var basePath = $("#basePath").val();
		    if (imgSrc.indexOf(basePath) > -1) {
		    	imgSrc = imgSrc.substring(imgSrc.indexOf(basePath));
		    }
		    
		    formData.append('title', $(form1.find('input[name=title]').get(0)).val());
		    formData.append('imgSrc', imgSrc);
		    formData.append('linkUrl', $(form1.find('input[name=linkUrl]').get(0)).val());
		    formData.append('display', 1/*$(form1.find('input[name=display]').get(0)).val()*/);
		    formData.append('orderNo', $(form1.find('input[name=orderNo]').get(0)).val());
		    var wxTemplateId = $('input[name=wxTemplateId]:checked').val()
		    console.log("wxTemplateId: " + wxTemplateId);
		    formData.append('wxTemplateId', wxTemplateId);
		    
		    $.ajax({
		        url: '../../web/media/wxLink/add',
		        contentType:"application/x-www-form-urlencoded",
		        data: formData,
		        processData: false,
		        type: 'POST',
		        contentType: false, // tell jQuery not to set contentType
		        success : function(data) {
		            alert('创建链接成功');

		    		$('#wxLinkDiv').dialog('close');
		    		window.location.href = window.location.href;
		        }
		    });	// ajax end
		    
		})
		
	});
}

var mouseOverSec = function() {
	$('div.sec').mouseover(function() {
		/*$(this).css('background-color', 'red');*/
		$(this).addClass('mover');
	}).mouseout(function() {
		/*$(this).css('backgroud-color', '');*/
		$(this).removeClass('mover');
	});
}

var showDetail = function() {
	
	var wxTemplateId = $('input[name=wxTemplateId]:checked').val()
    console.log("wxTemplateId: " + wxTemplateId);
	
	$.ajax({
		"url" : $('#basePath').val() + "/web/media/wxLink/list",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		"data": {
			wxTemplateId: wxTemplateId
        },
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
        	if (data.gridModelList != null && data.gridModelList.length > 0) {
        		for (var i = 0; i < data.gridModelList.length; i++) {
        			wxLink = data.gridModelList[i];
        			if (i >= 9) {
        				break;
        			}
        			var orderNo = wxLink.orderNo;
        			imgSrc = $('#basePath').val() + "/" + wxLink.imgSrc
        			var sec = $('#sec' + (orderNo));
        			sec.find('a').attr('href', wxLink.linkUrl)
												.attr('alt', wxLink.title)
												.attr('title', wxLink.title);
        			sec.find('img').attr('src', imgSrc)
        									.attr('alt', wxLink.title)
        									.attr('title', wxLink.title);
        			sec.find('p.title').text(wxLink.title);
        			sec.find('input[name=linkUrl]').text(wxLink.linkUrl);
        		}
        	}
//        	$("#activityId").html(data.activityId);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
