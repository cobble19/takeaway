var table4WxMenuMgrEntry;

$(document).ready(function() {
	$('#wxMenuMgrEntryButtonDiv').dialog({
		autoOpen: false,
    	modal: true,
    	width: 650,
    	height: 300
	});
	///
	$('#getMenuBtn4WxMenuMgrEntryFromWx').click(function() {
		getMenuMgrMenuFromWx();
	});
	
	$('#publishMenuBtn4WxMenuMgrEntryToWx').click(function() {
		publishMenuMgrMenuToWx();
	});
	
	///
	$('#wxMenuMgrEntryButtonDiv #level').change(function(){
		var value = $(this).val();
		if (value == 1) {
			$('#wxMenuMgrEntryButtonDiv #parentButtonId').val(0);
		}
	})
	///
	$('#wxMenuMgrEntryButtonDiv #btnKey').parent('div').parent('div').hide();
	$('#wxMenuMgrEntryButtonDiv #url').parent('div').parent('div').hide();
	$('#wxMenuMgrEntryButtonDiv #mediaId').parent('div').parent('div').hide();
	
	$('#wxMenuMgrEntryButtonDiv #type').change(function(){
		var value = $(this).val();
		var text = $(this).find('option:selected').text();
		console.log('value: ' + value + ", text: " + text);
		
		$('#wxMenuMgrEntryButtonDiv #btnKey').attr('readonly', false);
		$('#wxMenuMgrEntryButtonDiv #name').attr('readonly', false);
		if (value == 'click' && text == '文字回复') {
			$('#wxMenuMgrEntryButtonDiv #btnKey').parent('div').parent('div').show();
			$('#wxMenuMgrEntryButtonDiv #url').parent('div').parent('div').hide();
			$('#wxMenuMgrEntryButtonDiv #mediaId').parent('div').parent('div').hide();
		} else if (value == 'view') {
			$('#wxMenuMgrEntryButtonDiv #btnKey').parent('div').parent('div').hide();
			$('#wxMenuMgrEntryButtonDiv #url').parent('div').parent('div').show();
			$('#wxMenuMgrEntryButtonDiv #mediaId').parent('div').parent('div').hide();
		} else if (value == 'media_id') {
			$('#wxMenuMgrEntryButtonDiv #btnKey').parent('div').parent('div').hide();
			$('#wxMenuMgrEntryButtonDiv #url').parent('div').parent('div').hide();
			$('#wxMenuMgrEntryButtonDiv #mediaId').parent('div').parent('div').show();
		} else if (value == 'click' && text == '一键添加加入会员') {
			$('#wxMenuMgrEntryButtonDiv #name').val('加入会员');
			$('#wxMenuMgrEntryButtonDiv #btnKey').val('欢迎您，GUEST  1.加入会员请回复001  2.重新加入请回复002  3.退出会员请回复003');
			
			$('#wxMenuMgrEntryButtonDiv #btnKey').parent('div').parent('div').show();
			$('#wxMenuMgrEntryButtonDiv #url').parent('div').parent('div').hide();
			$('#wxMenuMgrEntryButtonDiv #mediaId').parent('div').parent('div').hide();
			
			$('#wxMenuMgrEntryButtonDiv #btnKey').attr('readonly', true);
			$('#wxMenuMgrEntryButtonDiv #name').attr('readonly', true);
		}
	});
	///
    table4WxMenuMgrEntry = $('#dbTable4WxMenuMgrEntry').DataTable( {
    	"processing": true,
		"initComplete": function () {
            var api = this.api();
           /* api.$('td').click( function () {
                // api.search( this.innerHTML ).draw();
            	api.ajax.reload();
            	api.draw();
            } );*/
			//$("#matsTeleFraudForm").appendTo($("mats-form"));
            
            console.log("page.len = " + api.page.len());
            var info = api.page.info();
            console.log('Currently showing page '+(info.page+1)+' of '+info.pages+' pages.');
            
           // $('#dbTable4WxMenuMgrEntry_wrapper div.top').hide();
            
            
        },
        "language": {
        	"search": "过滤: ",
        	"lengthMenu": "每页显示 _MENU_ 条记录",
        	"zeroRecords": "没有检索到数据",
        	"info": "显示 _START_ 到 _END_ 条 /共 _TOTAL_ 条数据",
        	"infoEmpty": "没有数据",
        	"infoFiltered": "(从 _MAX_ 条数据中检索)",
        	"paginate": {
        		"first": "首页",
        		"previous": "前一页",
        		"next": "后一页",
        		"last": "尾页"
        	}
        	
        },
//		"dom" : '<"top"fl<"clear">>rt<"bottom"ip<"clear">>',
		"dom" : '<"top"<"clear">>rt<"bottom"<"clear">>',	
		"lengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
		"columnDefs" : [ {
			"targets" : 0,
			"visible" : false,
			"render" : function(data, type, full, meta) {
				var checkBox = '<input type="checkbox" name="chkBox" value="'
					+ full.wxMenuMgrEntryId
					+ '">';
				return checkBox;
				;
			}
		},{
			"targets" : 1,
			"visible" : false,
			"render" : function(data, type, full, meta) {
				//console.log(data + " " + type + " " + full + " " + meta);
			}
		}, {
			"targets" : [2],
			"visible": false
		}, {
			"targets" : [3,4,5,6,7,8],
			"visible": true,
			"render" : function(data, type, full, meta) {
				var ret = data == null ? "菜单未设置" : data;
				var editBtn = '<span style="color: green; margin-left: 5px;" data-toggle="tooltip" data-placement="right" title="修改菜单" class="glyphicon glyphicon-edit pull-right" aria-hidden="true" ' +
						'onclick="javascript: menuMgrEntryBtnEdit(this' + ',' + meta.row + ',' + meta.col +
						');"></span>';
				
				var deleteBtn = '<span style="color: red; margin-left: 5px;" data-toggle="tooltip" data-placement="right" title="删除菜单" class="glyphicon glyphicon-trash pull-right" aria-hidden="true" ' +
						'onclick="javascript: menuMgrEntryBtnDelete(this' + ',' + meta.row + ',' + meta.col +
						');"></span>';
				
				return ret + deleteBtn + editBtn;
			}
		}/*, {
			"targets": [9],
			"render" : function(data, type, full, meta) {
				var date = new Date(data);
				return date.format('Y-m-d H:i:s');
			}
		}*/, {
			"targets" : [9],
			"visible" : false,
			"render" : function(data, type, full, meta) {
				var hrefEdit = $('#basePath').val() + '/web/unified/wxMenuMgrEntry/showupdate?wxMenuMgrEntryId='  + full.wxMenuMgrEntryId;
				var linkEdit = '<a class="" style="margin-bottom:5px;" target="_blank" href="' + hrefEdit
								+ '">'
								+ '修改' + '</a>';
				
				var hrefVIAdd = $('#basePath').val() + '/web/media/wxMenuMgrEntryDetail?wxMenuMgrEntryId='  + full.wxMenuMgrEntryId;
				var linkVIAdd = '<a class="" style="margin-bottom:5px;" target="_blank" href="' + hrefVIAdd
								+ '">'
								+ '详细信息' + '</a>';
				
				
				var oper =
					'<div class="btn-group" role="group">'
						+ '<button type="button" class="btn btn-warning btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
							+ '操作选项'
								+ '<span class="caret"></span>'
						+ '</button>'
						+ '<ul class="dropdown-menu dropdown-menu-xs">'
							+ '<li>' + linkEdit + '</li>'
							+ '<li>' + linkVIAdd + '</li>'
						+ '</ul>'
					+ '</div>';
					
					
				var publishBtn = '<span style="color: green; margin-left: 5px;" data-toggle="tooltip" data-placement="right" title="发布菜单" class="glyphicon glyphicon-upload" aria-hidden="true" ' +
						'onclick="javascript: menuMgrEntryBtnPublish(this' + ',' + meta.row + ',' + meta.col +
						');"></span>';
				
					
			      
				return publishBtn;
			}
		} ],
        "columns": [
			{
			    /*"className":      'details-control',*/
			    "orderable":      false,
			    "data":           null,
			    "defaultContent": ''
			},
            {
                /*"className":      'details-control',*/
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            },
            { "data": "wxMenuMgrEntryId" },
            { "data": "level1ButtonPOJO.name" },
            { "data": "level2Button1POJO.name" },
            { "data": "level2Button2POJO.name" },
            { "data": "level2Button3POJO.name" },
            { "data": "level2Button4POJO.name" },
            { "data": "level2Button5POJO.name" },
            {
                /*"className":      'details-control',*/
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            }
        ],
        "order": [[1, 'asc']]
    } );
    
    table4WxMenuMgrEntry.on( 'order.dt search.dt', function () {
    	table4WxMenuMgrEntry.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();

    wxMenuMgrEntrySearch(table4WxMenuMgrEntry);
    $('#searchBtn4WxMenuMgrEntry').click(function() {
    	console.log('search click...');
    	wxMenuMgrEntrySearch(table4WxMenuMgrEntry);
    })
    
    $('#chkBoxAll4WxMenuMgrEntry').click(function() {
    	var chkBoxAll = $(this).attr('checked');
    	if (chkBoxAll) {
    		$('#dbTable4WxMenuMgrEntry').find('input[name=chkBox]').attr('checked', true);
    	} else {
    		$('#dbTable4WxMenuMgrEntry').find('input[name=chkBox]').attr('checked', false);
    	}
    })
    

    $('#deleteBtn4WxMenuMgrEntry').click(function() {
    	alert('unfinished');
    	return;
    	var ids = [];
    	var chkBox = $('#dbTable4WxMenuMgrEntry').find('input[name=chkBox]');
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
    		"url" : "../../mgr/wxMenuMgrEntry/delete",
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
            	wxMenuMgrEntrySearch(table4WxMenuMgrEntry);
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});
    })
    
    ///
    
    
} );


var wxMenuMgrEntrySearch = function(table) {
	$('#progress').dialog('open');
	$.ajax({
		"url" : "../../api/unified/wxMenuMgr/entry/list",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	$('#progress').dialog('close');
        	console.log("data = " + data);
        	console.log("t= " + table.page.info().pages);
        	table.clear();
        	
        	/*if (data.data instanceof Array) {
        		data.content = data.content.substring(0, 10);
        	}*/
        	
        	table.rows.add(data.data).draw()
            .nodes()
            .to$()
            .addClass( 'new' );
        	
        	// add title
//        	$("td.details-control").attr('title', '申请参加');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
///
///
var menuMgrEntryBtnEdit = function(full,row,col) {
	console.log(this + row + col);
	data = table4WxMenuMgrEntry.rows($(full).parent('td').parent('tr')).data();
	console.log(data[0]);
	rowData = data[0];
	
	wxMenuMgrCategoryPOJO = rowData.wxMenuMgrCategoryPOJO;
	parentButtonPOJO = {
		'wxMenuMgrButtonId': 0
	};
	level = 1;
	orderNo = 0;
	switch (col - 3) {
		case 0: {
			// 一级菜单
			buttonPOJO = rowData.level1ButtonPOJO;
			level = buttonPOJO.level == null ? 1 : buttonPOJO.level;
			orderNo = buttonPOJO.orderNo == null ? (col - 3) : buttonPOJO.orderNo;
			break;
		}
		case 1: {
			// 二级菜单的第一个菜单
			parentButtonPOJO = rowData.level1ButtonPOJO;
			buttonPOJO = rowData.level2Button1POJO;
			level = buttonPOJO.level == null ? 2 : buttonPOJO.level;
			orderNo = buttonPOJO.orderNo == null ? (col - 3) : buttonPOJO.orderNo;
			break;
		}
		case 2: {
			parentButtonPOJO = rowData.level1ButtonPOJO;
			buttonPOJO = rowData.level2Button2POJO;
			level = buttonPOJO.level == null ? 2 : buttonPOJO.level;
			orderNo = buttonPOJO.orderNo == null ? (col - 3) : buttonPOJO.orderNo;
			break;
			
		}
		case 3: {
			parentButtonPOJO = rowData.level1ButtonPOJO;
			buttonPOJO = rowData.level2Button3POJO;
			level = buttonPOJO.level == null ? 2 : buttonPOJO.level;
			orderNo = buttonPOJO.orderNo == null ? (col - 3) : buttonPOJO.orderNo;
			break;
			
		}
		case 4: {
			parentButtonPOJO = rowData.level1ButtonPOJO;
			buttonPOJO = rowData.level2Button4POJO;
			level = buttonPOJO.level == null ? 2 : buttonPOJO.level;
			orderNo = buttonPOJO.orderNo == null ? (col - 3) : buttonPOJO.orderNo;
			break;
			
		}
		case 5: {
			parentButtonPOJO = rowData.level1ButtonPOJO;
			buttonPOJO = rowData.level2Button5POJO;
			level = buttonPOJO.level == null ? 2 : buttonPOJO.level;
			orderNo = buttonPOJO.orderNo == null ? (col - 3) : buttonPOJO.orderNo;
			break;
		}
		default: {
			alert('菜单数目不正确');
		}
	}
	console.log('parentButtonPOJO: ' + parentButtonPOJO);
	console.log('buttonPOJO: ' + buttonPOJO);
	
	$('#wxMenuMgrEntryButtonDiv #authorizerAppIdX').val(wxMenuMgrCategoryPOJO.authorizerAppId);
	$('#wxMenuMgrEntryButtonDiv #wxMenuMgrCategoryId').val(wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId);
	$('#wxMenuMgrEntryButtonDiv #parentButtonId').val(parentButtonPOJO.wxMenuMgrButtonId);
	
	$('#wxMenuMgrEntryButtonDiv #wxMenuMgrButtonId').val(buttonPOJO.wxMenuMgrButtonId);
	$('#wxMenuMgrEntryButtonDiv #level').val(level);
	
	$('#wxMenuMgrEntryButtonDiv #name').val(buttonPOJO.name);
	$('#wxMenuMgrEntryButtonDiv #type').val(buttonPOJO.type);
	$('#wxMenuMgrEntryButtonDiv #btnKey').val(buttonPOJO.btnKey)
	$('#wxMenuMgrEntryButtonDiv #mediaId').val(buttonPOJO.mediaId)
	$('#wxMenuMgrEntryButtonDiv #menuId').val(buttonPOJO.menuId)
	$('#wxMenuMgrEntryButtonDiv #newsInfo').val(buttonPOJO.newsInfo)
	$('#wxMenuMgrEntryButtonDiv #url').val(buttonPOJO.url)
	$('#wxMenuMgrEntryButtonDiv #value').val(buttonPOJO.value)
	
	$('#wxMenuMgrEntryButtonDiv #orderNo').val(orderNo)
	
//	var type = $('#wxMenuMgrEntryButtonDiv #type').val();
//	$('#wxMenuMgrEntryButtonDiv #type').val(type);

	$('#wxMenuMgrEntryButtonDiv #btnKey').parent('div').parent('div').hide();
	$('#wxMenuMgrEntryButtonDiv #url').parent('div').parent('div').hide();
	$('#wxMenuMgrEntryButtonDiv #mediaId').parent('div').parent('div').hide();
	$('#wxMenuMgrEntryButtonDiv #type').val('click');
	$('#wxMenuMgrEntryButtonDiv #type').change();
	
	$('#wxMenuMgrEntryButtonDiv').dialog('open');
}
////

////
var menuMgrEntryBtnDelete = function(spanBtn,row,col) {
	console.log(this);
	data = table4WxMenuMgrEntry.rows($(spanBtn).parent('td').parent('tr')).data();
	console.log(data[0]);
	rowData = data[0];
//	$('#wxMenuMgrEntryButtonDiv form').find('#').val(rowData.)
	
	
	switch (col - 3) {
		case 0: {
			// 一级菜单
			buttonPOJO = rowData.level1ButtonPOJO;
			level = buttonPOJO.level == null ? 1 : buttonPOJO.level;
			break;
		}
		case 1: {
			// 二级菜单的第一个菜单
			parentButtonPOJO = rowData.level1ButtonPOJO;
			buttonPOJO = rowData.level2Button1POJO;
			level = buttonPOJO.level == null ? 2 : buttonPOJO.level;
			break;
		}
		case 2: {
			parentButtonPOJO = rowData.level1ButtonPOJO;
			buttonPOJO = rowData.level2Button2POJO;
			level = buttonPOJO.level == null ? 2 : buttonPOJO.level;
			break;
			
		}
		case 3: {
			parentButtonPOJO = rowData.level1ButtonPOJO;
			buttonPOJO = rowData.level2Button3POJO;
			level = buttonPOJO.level == null ? 2 : buttonPOJO.level;
			break;
			
		}
		case 4: {
			parentButtonPOJO = rowData.level1ButtonPOJO;
			buttonPOJO = rowData.level2Button4POJO;
			level = buttonPOJO.level == null ? 2 : buttonPOJO.level;
			break;
			
		}
		case 5: {
			parentButtonPOJO = rowData.level1ButtonPOJO;
			buttonPOJO = rowData.level2Button5POJO;
			level = buttonPOJO.level == null ? 2 : buttonPOJO.level;
			break;
		}
		default: {
			alert('菜单数目不正确');
		}
	}
	
	var ids = [];
	ids.push(buttonPOJO.wxMenuMgrButtonId);
	console.log('ids: ' + ids);
	if (ids == null || ids.length <= 0) {
		alert('请选择一条记录');
		return;
	}

	var confirm = window.confirm('确定删除');
	if (!confirm) {
		return;
	}
	$('#progress').dialog('open');
	$.ajax({
		"url" : $('#basePath').val() + "/mgr/wxMenuMgrButton/delete",
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
        	wxMenuMgrEntrySearch(table4WxMenuMgrEntry);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
///

///
var menuMgrEntryBtnPublish = function(spanBtn,row,col) {
	console.log(this);
	data = table4WxMenuMgrEntry.rows($(spanBtn).parent('td').parent('tr')).data();
	console.log(data[0]);
	rowData = data[0];
	
	wxMenuMgrCategoryPOJO = rowData.wxMenuMgrCategoryPOJO;
//	$('#wxMenuMgrEntryButtonDiv form').find('#').val(rowData.)
	
	var wxMenuMgrCategoryId = wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId;
	var authorizerAppId = wxMenuMgrCategoryPOJO.authorizerAppId;
	
	console.log('wxMenuMgrCategoryId: ' + wxMenuMgrCategoryId + ", authorizerAppId: " + authorizerAppId);
	if (wxMenuMgrCategoryId == null || authorizerAppId == null) {
		alert('wxMenuMgrCategoryId: ' + wxMenuMgrCategoryId + ", authorizerAppId: " + authorizerAppId + ", 参数不正确");
		return;
	}

	var confirm = window.confirm('确定修改公众号菜单？！，以前的菜单将被覆盖');
	if (!confirm) {
		return;
	}
	$('#progress').dialog('open');
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
        	wxMenuMgrEntrySearch(table4WxMenuMgrEntry);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
///
///
var getMenuMgrMenuFromWx = function() {
	console.log(this);
	data = table4WxMenuMgrEntry.row(0).data();
	console.log(data);
	rowData = data;
	var authorizerAppId = null;
	var wxMenuMgrCategoryPOJO = null;
	if (!!rowData) {
		wxMenuMgrCategoryPOJO = rowData.wxMenuMgrCategoryPOJO;
	}
	if (!!wxMenuMgrCategoryPOJO) {
		authorizerAppId = wxMenuMgrCategoryPOJO.authorizerAppId;
	}
	
//	var wxMenuMgrCategoryId = wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId;
	if (authorizerAppId == null || authorizerAppId == undefined) {
		authorizerAppId = $('#authorizerAppId').val();
	}
	
	console.log("authorizerAppId: " + authorizerAppId);
	if (authorizerAppId == null) {
		alert("authorizerAppId: " + authorizerAppId + ", 参数不正确");
		return;
	}

	var confirm = window.confirm('获取当前菜单');
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
        	wxMenuMgrConditionSearch2();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
///

///
var publishMenuMgrMenuToWx = function() {
	console.log(this);
	data = table4WxMenuMgrEntry.row(0).data();
	console.log(data);
	rowData = data;
	
	var wxMenuMgrCategoryPOJO = null;
	if (rowData != null && !!rowData) {
		wxMenuMgrCategoryPOJO = rowData.wxMenuMgrCategoryPOJO;
	} else {
		alert('请先设置菜单');
		return;
	}
	if (wxMenuMgrCategoryPOJO != null && !!wxMenuMgrCategoryPOJO) {
		authorizerAppId = wxMenuMgrCategoryPOJO.authorizerAppId;
	} else {
		alert('请先设置菜单');
		return;
	}
//	wxMenuMgrCategoryPOJO = rowData.wxMenuMgrCategoryPOJO;
	
	var wxMenuMgrCategoryId = wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId;
	var authorizerAppId = wxMenuMgrCategoryPOJO.authorizerAppId;
	
	console.log('wxMenuMgrCategoryId: ' + wxMenuMgrCategoryId + ", authorizerAppId: " + authorizerAppId);
	if (wxMenuMgrCategoryId == null || authorizerAppId == null) {
		alert('wxMenuMgrCategoryId: ' + wxMenuMgrCategoryId + ", authorizerAppId: " + authorizerAppId + ", 参数不正确");
		return;
	}

	var confirm = window.confirm('确定修改公众号菜单？！，以前的菜单将被覆盖');
	if (!confirm) {
		return;
	}
	$('#progress').dialog('open');
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
        	wxMenuMgrEntrySearch(table4WxMenuMgrEntry);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}



 