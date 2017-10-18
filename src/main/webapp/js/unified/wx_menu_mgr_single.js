
$(document).ready(function() {
	wxMenuMgrSearch();
	
	$('#searchBtn4WxMenuMgr').click(function() {
		wxMenuMgrSearch();
	});
	$('#searchCurrentBtn4WxMenuMgr').click(function() {
		getMenuMgrMenuInfo();
	});
	
    $('#deleteBtn4WxMenuMgr').click(function() {
    	var ids = [];
    	var chkBox = $('#dbTable4WxMenuMgr').find('input[name=chkBox]');
    	chkBox.each(function(index, ele) {
    		if ($(this).attr('checked')) {
    			ids.push($(this).val());
    		}
    	})
    	console.log('ids: ' + ids);
    	if (ids == null || ids.length <= 0) {
    		alert('请选择一条记录');
    		return;
    	}

    	var confirm = window.confirm('确定删除');
    	if (!confirm) {
    		return;
    	}
    	$.ajax({
    		"url" : "../../mgr/wxMenuMgr/delete",
    		"type" : "GET",
    		"headers" : {
    			"Content-Type" : "application/json"
    		},
    		"dataType" : 'json',
    		traditional :true, 
    		"data": {
                "ids": ids
            },
            success: function(data, textStatus, jqXHR ) {
            	$('#progress').dialog('close');
//            	wxMenuMgrSearch(table4WxMenuMgr);
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});
    });
    
    $('#wxMenuMgrButtonDiv').dialog({
    	autoOpen: false,
    	modal: true
    });
    
    
} );

var getWxMenuMgrButtonDispDiv = function(wxMenuMgrButtonPOJO) {
	var ret = "";
	if (wxMenuMgrButtonPOJO == null) {
		return ret;
	}
	if (wxMenuMgrButtonPOJO.type == 'view') {
		ret = 'type: view, name: ' + wxMenuMgrButtonPOJO.name + ', url: ' + wxMenuMgrButtonPOJO.url;
	} else if (wxMenuMgrButtonPOJO.type == 'click') {
		ret = 'type: click, name: ' + wxMenuMgrButtonPOJO.name + ', key: ' + wxMenuMgrButtonPOJO.btnKey;
	} else if (wxMenuMgrButtonPOJO.type == 'media_id') {
		ret = 'type: media_id, name: ' + wxMenuMgrButtonPOJO.name + ', value: ' + wxMenuMgrButtonPOJO.value;
	} else {
		ret = 'type: ' + wxMenuMgrButtonPOJO.type + ', name: ' + wxMenuMgrButtonPOJO.name + ', value: ' + wxMenuMgrButtonPOJO.value;
	}
	
	return ret;
}

var addMenuMgrButton = function(wxMenuMgrCategoryId, parentButtonId, level, authorizerAppId) {
	$('#wxMenuMgrCategoryId').val(wxMenuMgrCategoryId);
	$('#parentButtonId').val(parentButtonId);
	$('#level').val(level);
	$('#authorizerAppIdX').val(authorizerAppId);
	
//	$('#wxMenuMgrButtonDiv').dialog('open');
	$('#wxMenuMgrButtonDiv_1').show();
	
}

var deleteMenuMgrButton = function(wxMenuMgrButtonId) {
	var ids = [];
	ids.push(wxMenuMgrButtonId);
	console.log('ids: ' + ids);
	if (ids == null || ids.length <= 0) {
		alert('请选择一条记录');
		return;
	}

	var confirm = window.confirm('确定删除');
	if (!confirm) {
		return;
	}
	$.ajax({
		"url" : "../../mgr/wxMenuMgrButton/delete",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		traditional :true, 
		"data": {
            "ids": ids
        },
        success: function(data, textStatus, jqXHR ) {
        	$('#progress').dialog('close');
        	wxMenuMgrSearch();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var publishMenuMgrCategory = function(wxMenuMgrCategoryId, authorizerAppId) {
	console.log('wxMenuMgrCategoryId: ' + wxMenuMgrCategoryId + ", authorizerAppId: " + authorizerAppId);
	if (wxMenuMgrCategoryId == null || authorizerAppId == null) {
		alert('wxMenuMgrCategoryId: ' + wxMenuMgrCategoryId + ", authorizerAppId: " + authorizerAppId + ", 参数不正确");
		return;
	}

	var confirm = window.confirm('确定修改公众号菜单？！，以前的菜单将被覆盖');
	if (!confirm) {
		return;
	}
	$.ajax({
		"url" : $('#basePath').val() + "/api/unified/wxMenuMgr/publish",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		traditional :true, 
		"data": {
            "wxMenuMgrCategoryId": wxMenuMgrCategoryId,
            "authorizerAppId": authorizerAppId
        },
        success: function(data, textStatus, jqXHR ) {
        	$('#progress').dialog('close');
        	if (data.success) {
        		alert('发布成功');
        	}
        	wxMenuMgrSearch();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var getMenuMgrMenuInfo = function() {
	var authorizerAppId = $('#authorizerAppId').val();
	console.log("authorizerAppId: " + authorizerAppId);
	if (authorizerAppId == null) {
		alert("authorizerAppId: " + authorizerAppId + ", 参数不正确");
		return;
	}

	var confirm = window.confirm('确定获取当前menuinfo吗？');
	if (!confirm) {
		return;
	}
	$.ajax({
		"url" : $('#basePath').val() + "/api/unified/wxMenuMgr/menuinfo",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		traditional :true, 
		"data": {
            "authorizerAppId": authorizerAppId
        },
        success: function(data, textStatus, jqXHR ) {
        	$('#progress').dialog('close');
        	if (data.success) {
        		alert('获取成功');
        	}
        	wxMenuMgrSearch();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var wxMenuMgrSearch = function() {
	$('#progress').dialog('open');
	var basePath = $('#basePath').val();
	$.ajax({
		"url" : basePath + "/api/unified/wxMenuMgr/list",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		"data": {
            "authorizerAppId": $("#authorizerAppId").val()
        },
        success: function(data, textStatus, jqXHR ) {
        	$('#progress').dialog('close');
        	var content = "";
        	if (data.success) {
        		wxMenuMgrCategoryPOJOs = data.data;
        		if (wxMenuMgrCategoryPOJOs != null) {
        			for (var i = 0; i < wxMenuMgrCategoryPOJOs.length; i++) {
        				wxMenuMgrCategoryPOJO = wxMenuMgrCategoryPOJOs[i];
        				content += "<hr/>";
            			content += /*"Category: " +*/
            					"<input type='radio' " +
            					"name='wxMenuMgrCategory' " +
            					"id='wxMenuMgrCategory_" + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId + "'" +
            					" value='" + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId + "'"
            					+ " />"
            					+ wxMenuMgrCategoryPOJO.name;
            			var onclickStr = ' onclick=addMenuMgrButton(' + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId
            								+ ',0' + ',1' + ',' + "'" + wxMenuMgrCategoryPOJO.authorizerAppId + "'"
					    					+ ')';
            			content += "&nbsp;&nbsp;<input type='button' " +
            					"id='addButton1_" + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId + "'"
            					+ " name='addButton1'" 
            					+ " value=AddButton1"
            					+ onclickStr
            					+ " class='btn btn-info'"
            					+ ">"
            					+ "";
            			
            			var onclickPublishStr = ' onclick=publishMenuMgrCategory(' + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId
											+ ',' + "'" + wxMenuMgrCategoryPOJO.authorizerAppId + "'"
					    					+ ')';
						content += "&nbsp;&nbsp;<input type='button' " +
								"id='publishButton1_" + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId + "'"
								+ " name='publishButton1'" 
								+ " value=PublishButton1"
								+ onclickPublishStr
								+ " class='btn btn-info'"
								+ ">"
								+ "";
            			
            			wxMenuMgrButtonPOJOs = wxMenuMgrCategoryPOJO.wxMenuMgrButtonPOJOs;
            			
            			
            			if (wxMenuMgrButtonPOJOs != null) {
            				for (var j = 0; j < wxMenuMgrButtonPOJOs.length; j++) {
            					wxMenuMgrButtonPOJO = wxMenuMgrButtonPOJOs[j];
            					
                    			content += "<br/>&nbsp;&nbsp;" /*+ "Button Level 1: "*/ +
			            					"<input type='checkbox' " +
			            					"name='wxMenuMgrButton' " +
			            					"id='wxMenuMgrButton_" + wxMenuMgrButtonPOJO.wxMenuMgrButtonId + "'" +
			            					" value='" + wxMenuMgrButtonPOJO.wxMenuMgrButtonId + "'"
			            					+ " />"
			            					+ getWxMenuMgrButtonDispDiv(wxMenuMgrButtonPOJO);
                    			content += "&nbsp;&nbsp;<input type='button' " +
            					"id='addButton2_" + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId + "'"
            					+ " name='addButton2'" 
            					+ " value=AddButton2"
            					+ " onclick=addMenuMgrButton(" + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId 
            							+ "," + wxMenuMgrButtonPOJO.wxMenuMgrButtonId + ",2" + ",'" + wxMenuMgrCategoryPOJO.authorizerAppId + "'"
            							+") "
                    			+ " class='btn btn-warning'"
            					+ "/>"
            					+ "";
                    			
                    			content += "&nbsp;&nbsp;<input type='button' " +
            					"id='deleteMenuMgrBtn_" + wxMenuMgrButtonPOJO.wxMenuMgrButtonId + "'"
            					+ " name='deleteMenuMgrBtn'" 
            					+ " value=删除此菜单"
            					+ " onclick=deleteMenuMgrButton(" + wxMenuMgrButtonPOJO.wxMenuMgrButtonId 
            							+") "
                    			+ " class='btn btn-warning'"
            					+ "/>"
            					+ "";
                    			
                    			wxMenuMgrButtonPOJOs2 = wxMenuMgrButtonPOJO.wxMenuMgrButtonPOJOs;
                    			if (wxMenuMgrButtonPOJOs2 != null) {
                    				for (var k = 0; k < wxMenuMgrButtonPOJOs2.length; k++) {
                    					wxMenuMgrButtonPOJO2 = wxMenuMgrButtonPOJOs2[k];
                            			content += "<br/>&nbsp;&nbsp;&nbsp;&nbsp;" /*+ "Button Level 2: "*/ +
		            					"<input type='checkbox' " +
		            					"name='wxMenuMgrButton' " +
		            					"id='wxMenuMgrButton_" + wxMenuMgrButtonPOJO2.wxMenuMgrButtonId + "'" +
		            					" value='" + wxMenuMgrButtonPOJO2.wxMenuMgrButtonId + "'"
		            					+ " />"
		            					+ getWxMenuMgrButtonDispDiv(wxMenuMgrButtonPOJO2);
                            			
                            			content += "&nbsp;&nbsp;<input type='button' " +
                    					"id='deleteMenuMgrBtn_" + wxMenuMgrButtonPOJO2.wxMenuMgrButtonId + "'"
                    					+ " name='deleteMenuMgrBtn'" 
                    					+ " value=删除此菜单"
                    					+ " onclick=deleteMenuMgrButton(" + wxMenuMgrButtonPOJO2.wxMenuMgrButtonId 
                    							+") "
                            			+ " class='btn btn-warning'"
                    					+ "/>"
                    					+ "";
                    				}
                    			}
            				}
            			}
            			content += "<br/>";
        			}
        		}
        	}
        	//console.log('content: ' + content);
        	$('#wx_menu_mgr_content').text('');
        	$('#wx_menu_mgr_content').append(content);
        	$('#wx_menu_mgr_content').append('<br/>');
        	$('#wx_menu_mgr_content').append('<br/>');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}



 