$(document).ready(function() {
//	showDetail();
	mouseOverSec();
	clickSec();
	
    $('#wxAttrDiv').dialog({
    	autoOpen: false,
    	modal: true
    });
    
    onClickWxTemplateIdRd();
    
    onClickDeploy();
    onClickAddAttr();
    
    $('#progress').dialog({
    	autoOpen: false,
    	modal: true
    });
})

var onClickAddAttr = function() {
	$('#addAttrBtn').click(function() {
		var inputs = $('#wxAttrForm input:text');
		var i = inputs.length;
		var inputText = '<div class="form-group">'
						+ '<label class="control-label col-sm-4" for="' + 'attr' + i + '">' + '条目' + i + ':' + '</label>'
						+ '<div class="col-sm-8">'
						+  '<input type="text" class="form-control" id="' + 'attr' + i + '" name="' + 'attr' + i + '" placeholder="请输入' + '条目' + i + '内容' + '">'
						+ '</div>'
						+'</div>'
			;
		$('#wxAttrForm div.form-group:nth-last-child(2)').after(inputText);
	})
	$('#addPicBtn').click(function() {
		var inputs = $('#wxAttrForm input:text');
		var i = inputs.length;
		var inputText = '<div class="form-group">'
						+ '<label class="control-label col-sm-4" for="' + 'attr' + i + '">' + '条目图片、文件' + i + ':' + '</label>'
						+ '<div class="col-sm-8">'
						+  '<input type="text" class="form-control" id="' + 'attr' + i + '" name="' + 'attr' + i + '" readonly="readonly" placeholder="请输入' + '条目' + i + '内容' + '">'
						+  '<input class="" id="pic" name="pic" type="file">'
						+  '<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传">'
						+ '</div>'
						+'</div>'
			;
		$('#wxAttrForm div.form-group:nth-last-child(2)').after(inputText);
		triggerUploadBtnEvent(wxSecOrderNo);
	})
}
var wxSecOrderNo;
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
	        	console.log('Load Error!');
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

var triggerUploadBtnEvent = function(wxSecOrderNo) {
	console.log('triggerUploadBtnEvent...');
	
	var form = $("form[id=wxAttrForm]");
	$(form).find('input[type=button]').click(function(e) {

		console.log("uploadBtn click...");
		var form1 = $(this).parents('form');
		var div = $(this).parent('div');
		var formData;
	    formData = new FormData();

	    var userId = $('#userId').val();
		var wxTemplateId = $('input[name=wxTemplateId]:checked').val()
		
		formData.append('pic', div.find('input[name=pic]').get(0).files[0]);
	    formData.append('userId', userId);
	    formData.append('wxTemplateId', wxTemplateId);
	    formData.append('orderNo', wxSecOrderNo);

	    $.ajax({
	        url: '../../htmleditor/pic/add',
	        contentType:"multipart/form-data",
	        data: formData,
	        processData: false,
	        type: 'POST',
	        contentType: false, // tell jQuery not to set contentType
	        success : function(data) {
	            alert('上传成功');
	            div.find('input[type=text]').val(data.file_url);
	        }
	    });	// ajax end
	    
	})
}

var clickSec = function() {
	$('div.sec').click(function() {
		$('#wxAttrDiv').dialog('open');
		wxSecOrderNo = $(this).attr('id').replace(new RegExp("[A-Za-z]+","g"),"");
		var form = $("form[id=wxAttrForm]");
		
		/*var title = $(this).find('img').attr('alt');
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
		
		var form = $("form[id=wxAttrForm]");
		$(form).find('input[name=title]').val(title);
		$(form).find('input[name=imgSrc]').val(imgSrc);
		$(form).find('input[name=linkUrl]').val(linkUrl);
		$(form).find('input[name=orderNo]').val(orderNo);
		
		$(form).validate();
		*/
		triggerUploadBtnEvent(wxSecOrderNo);
		/*$(form).find('input[type=button]').click(function(e) {
			console.log("uploadBtn click...");
			var form1 = $(this).parents('form');
			var div = $(this).parent('div');
			var formData;
		    formData = new FormData();

		    var userId = $('#userId').val();
			var wxTemplateId = $('input[name=wxTemplateId]:checked').val()
			
			formData.append('pic', div.find('input[name=pic]').get(0).files[0]);
		    formData.append('userId', userId);
		    formData.append('wxTemplateId', wxTemplateId);
		    formData.append('orderNo', wxSecOrderNo);

		    $.ajax({
		        url: '../../htmleditor/pic/add',
		        contentType:"multipart/form-data",
		        data: formData,
		        processData: false,
		        type: 'POST',
		        contentType: false, // tell jQuery not to set contentType
		        success : function(data) {
		            alert('上传成功');
		            div.find('input[type=text]').val(data.file_url);
		        }
		    });	// ajax end
		    
		})*/	// upload pic to picture files
		
		$(form).find('#addBtn').click(function(e) {
			var form1 = $(this).parents('form');
			
			if (!$(form1).valid()) {
//				alert('请正确输入');
				return;
			}
			
			var formData;
		    formData = new FormData();
		    
		    inputTexts = $('#wxAttrForm div.form-group input:text');
		    var wxAttrPOJOs = [];
		    
		    var userId = $('#userId').val();
		    var wxTemplateId = $('input[name=wxTemplateId]:checked').val()
		    
		    console.log('inputTexts length: ' + inputTexts.length);

		    for (var i = 0; i < inputTexts.length; i++) {
		    	var inputText = inputTexts[i];
		    	var wxAttrPOJO = {};
		    	wxAttrPOJO.wxAttrData = $(inputText).val();
		    	wxAttrPOJO.orderNo = i;
		    	wxAttrPOJO.wxSecOrderNo = wxSecOrderNo;
		    	wxAttrPOJO.userId = userId;
		    	wxAttrPOJO.wxTemplateId = wxTemplateId;
		    	wxAttrPOJOs.push(wxAttrPOJO);
//		    	listParamPOJO.listParams.push(wxAttrPOJO);
		    }
		    
		    var basePath = $("#basePath").val();
		    /*if (imgSrc.indexOf(basePath) > -1) {
		    	imgSrc = imgSrc.substring(imgSrc.indexOf(basePath));
		    }*/
		    
		    /*var wxTemplateId = $('input[name=wxTemplateId]:checked').val()
		    console.log("wxTemplateId: " + wxTemplateId);
		    formData.append('wxTemplateId', wxTemplateId);
		    var userId = $('#userId').val();
		    formData.append('userId', userId);*/
		    
		    console.log("formData: " + formData);
		    
		    $.ajax({
		        url: '../../web/media/wxAttrs/add',
		       /* contentType:"application/x-www-form-urlencoded",*/
		        data: JSON.stringify(wxAttrPOJOs),
//		        processData: false,
		        type: 'POST',
		        contentType: "application/json", // tell jQuery not to set contentType
		        success : function(data) {
		            alert('创建链接成功');

		    		$('#wxAttrDiv').dialog('close');
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

/*var showDetail = function() {
	
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
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
*/