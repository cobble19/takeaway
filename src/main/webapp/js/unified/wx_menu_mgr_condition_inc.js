
$(document).ready(function() {
	wxMenuMgrConditionSearch();
	
	$('#searchBtn4WxMenuMgrCondition').click(function() {
		wxMenuMgrConditionSearch();
	});
	$('#searchCurrentBtn4WxMenuMgrCondition').click(function() {
		getMenuMgrMenu();
	});
	
    $('#deleteBtn4WxMenuMgrCondition').click(function() {
    	var ids = [];
    	var chkBox = $('#dbTable4WxMenuMgrCondition').find('input[name=chkBox]');
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
//            	wxMenuMgrConditionSearch(table4WxMenuMgrCondition);
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});
    });
    
    
} );

var getWxMenuMgrButtonDispDiv2 = function(wxMenuMgrButtonPOJO) {
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

var addMenuMgrButton2 = function(wxMenuMgrCategoryId, parentButtonId, level, authorizerAppId) {
	console.log('addMenuMgrButton2');
	$('#wxMenuMgrButtonDiv_2 #wxMenuMgrCategoryId').val(wxMenuMgrCategoryId);
	$('#wxMenuMgrButtonDiv_2 #parentButtonId').val(parentButtonId);
	$('#wxMenuMgrButtonDiv_2 #level').val(level);
	$('#wxMenuMgrButtonDiv_2 #authorizerAppIdX').val(authorizerAppId);
	
//	$('#wxMenuMgrButtonDiv').dialog('open');
	$('#wxMenuMgrButtonDiv_2').show();
	
}

var addMenuMgrMatchRuleButton2 = function() {
	console.log('addMenuMgrMatchRuleButton2');
	/*$('#wxMenuMgrCategoryId').val(wxMenuMgrCategoryId);
	$('#authorizerAppIdX').val(authorizerAppId);*/
	
//	$('#wxMenuMgrButtonDiv').dialog('open');
	$('#wxMenuMgrMatchRuleDiv_2').show();
	
}

var deleteMenuMgrButton2 = function(wxMenuMgrButtonId) {
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
        	wxMenuMgrConditionSearch();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var deleteMenu2 = function(authorizerAppId) {
	console.log("authorizerAppId: " + authorizerAppId);
	if (authorizerAppId == null) {
		alert("authorizerAppId: " + authorizerAppId + ", 参数不正确");
		return;
	}

	var confirm = window.confirm('删除公众号的所有菜单?!');
	if (!confirm) {
		return;
	}
	$.ajax({
		"url" : $('#basePath').val() + "/api/unified/wxMenuMgr" + authorizerAppId + "/menu/delete",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		traditional :true, 
		/*"data": {
            "authorizerAppId": authorizerAppId
        },*/
        success: function(data, textStatus, jqXHR ) {
        	$('#progress').dialog('close');
        	if (data.success) {
        		alert('删除成功');
        	}
        	wxMenuMgrConditionSearch();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}


var deleteConditionalMenu2 = function(authorizerAppId, menuId) {
	console.log("authorizerAppId: " + authorizerAppId + ", menuId: " + menuId);
	if (authorizerAppId == null || menuId == null) {
		alert("authorizerAppId: " + authorizerAppId + ", menuId: " + menuId + ", 参数不正确");
		return;
	}

	var confirm = window.confirm('删除公众号的定制菜单?!menuId: ' + menuId);
	if (!confirm) {
		return;
	}
	$.ajax({
		"url" : $('#basePath').val() + "/api/unified/wxMenuMgr" + authorizerAppId + "/menu/conditional/delete",
		"type" : "POST",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
//		traditional :true, 
		"data": {
            "menuId": menuId
        },
        success: function(data, textStatus, jqXHR ) {
        	$('#progress').dialog('close');
        	if (data.success) {
        		alert('删除成功');
        	}
        	wxMenuMgrConditionSearch();
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
        	wxMenuMgrConditionSearch();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var getMenuMgrMenu = function() {
	var authorizerAppId = $('#authorizerAppId').val();
	console.log("authorizerAppId: " + authorizerAppId);
	if (authorizerAppId == null) {
		alert("authorizerAppId: " + authorizerAppId + ", 参数不正确");
		return;
	}

	var confirm = window.confirm('确定获取当前所有menu吗？');
	if (!confirm) {
		return;
	}
	$.ajax({
		"url" : $('#basePath').val() + "/api/unified/wxMenuMgr/menu/get",
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
        	wxMenuMgrConditionSearch();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var wxMenuMgrConditionSearch = function() {
	$('#progress').dialog('open');
	var basePath = $('#basePath').val();
	$.ajax({
		"url" : basePath + "/api/unified/wxMenuMgr/listFull",
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
        		wxMenuMgrFullPOJOs = data.data;
        		if (wxMenuMgrFullPOJOs != null) {
        			for (var m = 0; m < wxMenuMgrFullPOJOs.length; m++) {
        				wxMenuMgrFullPOJO = wxMenuMgrFullPOJOs[m];
        				content += "<hr/>";
        				content += "<b>Full: " + wxMenuMgrFullPOJO.name + "</b><br/>";
        				wxMenuMgrCategoryPOJOs = wxMenuMgrFullPOJO.wxMenuMgrCategoryPOJOs;
                		if (wxMenuMgrCategoryPOJOs != null) {
                			for (var i = 0; i < wxMenuMgrCategoryPOJOs.length; i++) {
                				wxMenuMgrCategoryPOJO = wxMenuMgrCategoryPOJOs[i];
                    			content += /*"Category: " +*/
                    					"<input type='radio' " +
                    					"name='wxMenuMgrCategory' " +
                    					"id='wxMenuMgrCategory_" + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId + "'" +
                    					" value='" + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId + "'"
                    					+ " />"
                    					+ wxMenuMgrCategoryPOJO.name;
                    			
                    			// add level 1 menu
                    			var onclickStr = ' onclick=addMenuMgrButton2(' + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId
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
                    			
                    			// add match rule 
                    			var onclickStr = ' onclick=addMenuMgrMatchRuleButton2(' + ')';
								content += "&nbsp;&nbsp;<input type='button' " +
										"id='addButtonMR2_" + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId + "'"
										+ " name='addButtonMR1'" 
										+ " value=AddButtonMR1"
										+ onclickStr
										+ " class='btn btn-info'"
										+ ">"
										+ "";
								
								// delete full menu
								var onclickStr = ' onclick=deleteMenu2('
												+ "'" + wxMenuMgrCategoryPOJO.authorizerAppId + "'"
												+ ')';
								content += "&nbsp;&nbsp;<input type='button' " +
										"id='deleteMenu2_" + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId + "'"
										+ " name='deleteMenu'" 
										+ " value=DeleteMenu"
										+ onclickStr
										+ " class='btn btn-info'"
										+ ">"
										+ "";
								
								// delete conditional menu
								var onclickStr = ' onclick=deleteConditionalMenu2('
												+ "'" + wxMenuMgrCategoryPOJO.menuId + "'"
												+ ')';
								content += "&nbsp;&nbsp;<input type='button' " +
										"id='deleteMenu2_" + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId + "'"
										+ " name='deleteConditionalMenu'" 
										+ " value=DeleteConditionalMenu"
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
                    					+ " onclick=addMenuMgrButton2(" + wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId 
                    							+ "," + wxMenuMgrButtonPOJO.wxMenuMgrButtonId + ",2" + ",'" + wxMenuMgrCategoryPOJO.authorizerAppId + "'"
                    							+") "
                            			+ " class='btn btn-warning'"
                    					+ "/>"
                    					+ "";
                            			
                            			content += "&nbsp;&nbsp;<input type='button' " +
                    					"id='deleteMenuMgrBtn_" + wxMenuMgrButtonPOJO.wxMenuMgrButtonId + "'"
                    					+ " name='deleteMenuMgrBtn'" 
                    					+ " value=删除此菜单"
                    					+ " onclick=deleteMenuMgrButton2(" + wxMenuMgrButtonPOJO.wxMenuMgrButtonId 
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
        		            					+ getWxMenuMgrButtonDispDiv2(wxMenuMgrButtonPOJO2);
                                    			
                                    			content += "&nbsp;&nbsp;<input type='button' " +
                            					"id='deleteMenuMgrBtn_" + wxMenuMgrButtonPOJO2.wxMenuMgrButtonId + "'"
                            					+ " name='deleteMenuMgrBtn'" 
                            					+ " value=删除此菜单"
                            					+ " onclick=deleteMenuMgrButton2(" + wxMenuMgrButtonPOJO2.wxMenuMgrButtonId 
                            							+") "
                                    			+ " class='btn btn-warning'"
                            					+ "/>"
                            					+ "";
                            				}
                            			}
                    				}
                    			}
                    			content += "<br/>";
                    			// match rule
                    			wxMenuMgrMatchRulePOJO = wxMenuMgrCategoryPOJO.wxMenuMgrMatchRulePOJO;
                    			if (wxMenuMgrMatchRulePOJO != null) {
                    				content += "Match Rule: " + wxMenuMgrMatchRulePOJO.groupId
                    							+ ", " + JSON.stringify(wxMenuMgrMatchRulePOJO)
                    							+ "<br/>";
                    			}
                    			
                			}
                		}
        				
        			}
        		}
        		
        	}
        	//console.log('content: ' + content);
        	$('#wx_menu_mgr_condition_content').text('');
        	$('#wx_menu_mgr_condition_content').append(content);
        	$('#wx_menu_mgr_condition_content').append('<br/>');
        	$('#wx_menu_mgr_condition_content').append('<br/>');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}



 