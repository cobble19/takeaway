var table4WxRespMsgS;
var htmlTable4WxRespMsgS;

$(document).ready(function() {
	
    $('#searchBtn4WxRespMsgS').click(function() {
    	console.log('search click...');
    	//wxRespMsgSSearch(table4WxRespMsgS);
    	//buildWxRespMsgS();
    	window.location.reload();
    })
    
	buildWxRespMsgS();
	
    
} );

var buildWxRespMsgS = function() {
	try {
		table4WxRespMsgS.clear();
		table4WxRespMsgS.destroy();
		table4WxRespMsgS = null;
		
		$('#dbTable4WxRespMsgSDiv').remove();
	} catch(e) {
		console.log(e);
	}
	
	htmlTable4WxRespMsgS = '<div id="dbTable4WxRespMsgSDiv">' +
					'<table id="dbTable4WxRespMsgS" class="display table table-striped table-bordered" cellspacing="0" width="100%">' +
			  				'<thead>' +
			  					'<tr>' +
			  						'<th><input type="checkbox" name="chkBoxAll4WxRespMsgS" id="chkBoxAll4WxRespMsgS">全选</th>' +
			  						'<th>序号</th>' +
			  						'<th>标识</th>' +
			  						'<th>接受关键字</th>' +
			  						'<th>回复关键字</th>' +
			  						'<th>信息类别</th>' +
			  						'<th>用户ID</th>' +
			  						'<th>公众号APPID</th>' +
			  						'<th>有效标志</th>' +
			  						'<th>创建时间</th>' +
			  						'<th>操作</th>' +
			  					'</tr>' +
			  				'</thead>' +
				  		'</table>' +
				  	'</div>';
	$($('#wx_resp_msg > table').get(0)).after($(htmlTable4WxRespMsgS));
    table4WxRespMsgS = $('#dbTable4WxRespMsgS').DataTable( {
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
		"dom" : '<"top"<"clear">>t<"bottom"<"clear">>',
		"lengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
		"columnDefs" : [ {
			"targets" : 0,
			"visible": false,
			"render" : function(data, type, full, meta) {
				var checkBox = '<input type="checkbox" name="chkBox" value="'
					+ full.wxRespMsgId
					+ '">';
				return checkBox;
				;
			}
		},{
			"targets" : 1,
			"visible": false,
			"render" : function(data, type, full, meta) {
				//console.log(data + " " + type + " " + full + " " + meta);
			}
		}, {
			"targets" : [2, 5, 6, 7, 8, 9],
			"visible": false
		}, {
			"targets": [4],
			"render" : function(data, type, full, meta) {
				var ret = data;
				if (data == '001') {
					ret = '加入会员';
				} else if (data == '002') {
					ret = '重新加入'
				} else if (data == '003') {
					ret = '退出会员';
				}
				return ret;
			}
		}, {
			"targets": [5],
			"render" : function(data, type, full, meta) {
				var ret = '';
				if (data == null || data == 0) {
					ret = '系统关键字';
				} else if (data == 1) {
					ret = '客户关键字'
				} else {
					ret = '未知关键字';
				}
				return ret;
			}
		}, {
			"targets": [9],
			"render" : function(data, type, full, meta) {
				var date = new Date(data);
				return date.format('Y-m-d H:i:s');
			}
		}, {
			"targets" : [10],
			"render" : function(data, type, full, meta) {
				var hrefEdit = $('#basePath').val() + '/web/unified/wxRespMsg/showupdate?wxRespMsgId='  + full.wxRespMsgId
								+ '&msgType=0';
				
				var linkEdit = '<a target="_blank" data-toggle="tooltip" data-placement="top" title="修改内容"' +
						' class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="' + hrefEdit
								+ '">'
								+ '<span style="color: green;" class="glyphicon glyphicon-edit"></span>'
								/**+ '修改内容'**/ 
								+ '</a>';
				
//				var hrefVIAdd = $('#basePath').val() + '/web/media/wxRespMsgDetail?wxRespMsgId='  + full.wxRespMsgId
//								+ '&msgType=0';
//				var linkVIAdd = '<a class="" style="margin-bottom:5px;" target="_blank" href="' + hrefVIAdd
//								+ '">'
//								+ '详细信息' + '</a>';
//				
//				
//				var oper =
//					'<div class="btn-group" role="group">'
//						+ '<button type="button" class="btn btn-warning btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
//							+ '操作选项'
//								+ '<span class="caret"></span>'
//						+ '</button>'
//						+ '<ul class="dropdown-menu dropdown-menu-xs">'
//							+ '<li>' + linkEdit + '</li>'
////							+ '<li>' + linkVIAdd + '</li>'
//						+ '</ul>'
//					+ '</div>';
					
			      
				return linkEdit;
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
            { "data": "wxRespMsgId" },
            { "data": "msgReceive" },
            { "data": "msgSend" },
            { "data": "msgType" },
            { "data": "userId" },
            { "data": "authorizerAppId" },
            { "data": "enableFlag" },
            { "data": "createDateTime" },
            {
                /*"className":      'details-control',*/
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            }
        ],
        "order": [[1, 'asc']]
    } );
    
    table4WxRespMsgS.on( 'order.dt search.dt', function () {
    	table4WxRespMsgS.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();

    wxRespMsgSSearch(table4WxRespMsgS);
//    $('#searchBtn4WxRespMsgS').click(function() {
//    	console.log('search click...');
//    	//wxRespMsgSSearch(table4WxRespMsgS);
//    	buildWxRespMsgS();
//    })
    
    $('#chkBoxAll4WxRespMsgS').click(function() {
    	var chkBoxAll = $(this).attr('checked');
    	if (chkBoxAll) {
    		$('#dbTable4WxRespMsgS').find('input[name=chkBox]').attr('checked', true);
    	} else {
    		$('#dbTable4WxRespMsgS').find('input[name=chkBox]').attr('checked', false);
    	}
    })
    
///
    $('#deleteBtn4WxRespMsgS').click(function() {
    	var ids = [];
    	var chkBox = $('#dbTable4WxRespMsgS').find('input[name=chkBox]');
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
    		"url" : "../../mgr/wxRespMsg/delete",
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
            	wxRespMsgSSearch(table4WxRespMsgS);
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
    ///
    $('#enableBtn4WxRespMsgS').click(function() {
    	var confirm = window.confirm('启动系统关键字');
    	if (!confirm) {
    		return;
    	}
    	$.ajax({
    		"url" : $('#basePath').val() + '/api/unified/wxRespMsg/enable',
    		"type" : "GET",
    		"headers" : {
    			"Content-Type" : "application/json"
    		},
    		"dataType" : 'json',
    		traditional :true, 
//    		"data": {
//                "ids": ids
//            },
            success: function(data, textStatus, jqXHR ) {
            	$('#progress').dialog('close');
            	window.location.reload();
//            	wxRespMsgSSearch(table4WxRespMsgS);
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
    ///
    $('#disableBtn4WxRespMsgS').click(function() {
    	var confirm = window.confirm('停用系统关键字');
    	if (!confirm) {
    		return;
    	}
    	$.ajax({
    		"url" : $('#basePath').val() + '/api/unified/wxRespMsg/disable',
    		"type" : "GET",
    		"headers" : {
    			"Content-Type" : "application/json"
    		},
    		"dataType" : 'json',
    		traditional :true, 
//    		"data": {
//                "ids": ids
//            },
            success: function(data, textStatus, jqXHR ) {
            	$('#progress').dialog('close');
            	window.location.reload();
//            	wxRespMsgSSearch(table4WxRespMsgS);
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
//    showTable4WxRespMsgGather();
    ///
//    var tempTable = $('#dbTable4WxRespMsgS');
//     // Add event listener for opening and closing details
//    $('#dbTable4WxRespMsgGather tbody').on('click', 'td.details-control', function () {
//    	console.log('tempTable22: ' + tempTable);
//    	tempTable = $('#dbTable4WxRespMsgS');
//    	console.log('tempTable22: ' + tempTable);
//    	if (tempTable.length() <= 0) {
//    		$('#wx_resp_msg').append(htmlTable4WxRespMsgS);
//    	}
//    	
//        var tr = $(this).closest('tr');
//        var row = $('#dbTable4WxRespMsgGather').DataTable().row( tr );
//        if ( row.child.isShown() ) {
//            // This row is already open - close it
//            row.child.hide();
//            tr.removeClass('shown');
//        }
//        else {
//            // Open this row
//            row.child( table4WxRespMsgS ).show();
//            tr.addClass('shown');
//        }
//    } );
    ///
    
     $('#dbTable4WxRespMsgS').on('draw.dt',function() {
 		console.log('dbTable4WxRespMsgS draw.dt')
        showTable4WxRespMsgGather();
     });
     
     
     $('#dbTable4WxRespMsgS').on('init.dt',function() {
 		console.log('init.dt')
        //onDetailClickEvent();
     });
    ///
    
}

var wxRespMsgSSearch = function(table) {
	$('#progress').dialog('open');
	$.ajax({
		"url" : $('#basePath').val() + '/api/unified/wxRespMsg/wxRespMsgByUserId',
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		traditional :true, 
		"data": {
            'msgType': 0
        },
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
            
//            showTable4WxRespMsgGather();
            
//            $('#dbTable4WxRespMsgGather td.details-control').trigger('click');
        	
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
var enableOrDisable = function() {
	var rowData = table4WxRespMsgS.row(0).data();
	
	var enableFlag = rowData.enableFlag;
	if (enableFlag == 0) {
		$('#enableBtn4WxRespMsgS').trigger('click');
	} else {
		$('#disableBtn4WxRespMsgS').trigger('click');
	}
}
///
///
var showTable4WxRespMsgGather = function() {
	var tableId = 'dbTable4WxRespMsgGather';
	var result = {};
	var columns = [
			{
			    "className":      'details-control',
			    "orderable":      false,
			    "data":           null,
			    "defaultContent": '',
			    "width": '10%'
			},
            {
                /*"className":      'details-control',*/
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            },
            { "data": "msgType" },
            { "data": "userId" },
            { "data": "authorizerAppId" },
            { "data": "enableFlag" },
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
            }
	];
	result.columns = columns;
	var columnDefs = [ 
			{
				"targets" : 0,
				"render" : function(data, type, full, meta) {
//					var checkBox = '<input type="checkbox" name="chkBox" value="'
//						+ full.userId
//						+ '">';
//					return checkBox;
					;
				}
			},{
				"targets" : 1,
				"render" : function(data, type, full, meta) {
					//console.log(data + " " + type + " " + full + " " + meta);
				}
			}, {
				"targets" : [1, 2, 3, 4, 6],
				"visible": false
			}, {
				"targets": [2],
				"render" : function(data, type, full, meta) {
					var ret = '';
					if (data == null || data == 0) {
						ret = '系统关键字';
					} else if (data == 1) {
						ret = '客户关键字'
					} else {
						ret = '未知关键字';
					}
					return ret;
				}
			}, {
				"targets": [6],
				"render" : function(data, type, full, meta) {
					var ret = '绑定会员功能';
					return ret;
				}
			}, {
				"targets" : [7],
				"render" : function(data, type, full, meta) {
//					var hrefEdit = $('#basePath').val() + '/web/unified/wxRespMsg/showupdate?wxRespMsgId='  + full.userId
//									+ '&msgType=0';
//					var linkEdit = '<a class="" style="margin-bottom:5px;" target="_blank" href="' + hrefEdit
//									+ '">'
//									+ '修改' + '</a>';
//					
//					var hrefVIAdd = $('#basePath').val() + '/web/media/wxRespMsgDetail?wxRespMsgId='  + full.userId
//									+ '&msgType=0';
//					var linkVIAdd = '<a class="" style="margin-bottom:5px;" target="_blank" href="' + hrefVIAdd
//									+ '">'
//									+ '详细信息' + '</a>';

					var enableFlag = full.enableFlag;

					var hrefEdit = $('#basePath').val() + '/web/unified/wxRespMsg/showupdate?wxRespMsgId='  + full.wxRespMsgId
									+ '&msgType=0';
					hrefEdit = '#';
					
					var linkEdit = '<button data-toggle="tooltip" data-placement="top" title="OK/BAN"' +
						' class="btn btn-warning btn-xs" style="margin-bottom:5px;" onclick="javascript: enableOrDisable();">'
								+ (enableFlag ? '<span style="color: green;" class="glyphicon glyphicon-ban-circle"></span>'
												: '<span style="color: green;" class="glyphicon glyphicon-ok"></span>')
								/**+ '修改内容'**/ 
								+ '</button>';
					
					
				      
					return linkEdit;
				}
			}];
		result.columnDefs = columnDefs;
		
		var rowData = table4WxRespMsgS.row(0).data();
		var userId = $('#userId').val();
		var authorizerAppId = $('#authorizerAppId').val();
		var data = [
			{
				"userId": userId,
				"authorizerAppId": authorizerAppId,
				"msgType": 1,
				"enableFlag": rowData.enableFlag == null ? 0 : rowData.enableFlag
			}];
		result.data = data;
		buildTable4WxRespMsgGather(tableId, result);
		
//		$('#dbTable4WxRespMsgGather').on('draw.dt',function() {
//	 		console.log(' dbTable4WxRespMsgGatherdraw.dt')
//	     	onDetailClickEvent();
//	     });
	     onDetailClickEvent();
	     $('#dbTable4WxRespMsgGather tbody td.details-control').trigger('click');
		
		
    ///
//    var tempTable = $('#dbTable4WxRespMsgS');
     // Add event listener for opening and closing details
//    $('#dbTable4WxRespMsgGather tbody').on('click', 'td.details-control', function () {
//        var tr = $(this).closest('tr');
//        var row = $('#dbTable4WxRespMsgGather').DataTable().row( tr );
//        if ( row.child.isShown() ) {
//            // This row is already open - close it
//            row.child.hide();
//            tr.removeClass('shown');
//        }
//        else {
//            // Open this row
//            row.child( tempTable ).show();
//            tr.addClass('shown');
//        }
//    } );
    ///
}
///
///
var buildTable4WxRespMsgGather = function(tableId, result) {
	var columns = result.columns || [];
//	var temp = result.columns;
//	$.each(temp, function(i, e) {
//		column = {
//				/*"data": '\"' + e + '\"'*/
//				"data": e
//		};
//		columns.push(column);
//	});
	
	var columnDefs = result.columnDefs || [];
//	columnDef = {
//			"targets": [columns.length - 1],
//			"visible": true,
//			"render" : function(data, type, full, meta) {
//				var createDateTime = new Date(full.createDateTime);
//				return createDateTime.format('Y-m-d H:i:s');
//			}
//		};
//	columnDefs.push(columnDef);
	
//	$.each(result.trHeaderNames, function(i, e){
//		$('#trHeader').append('<th>' + e + '</th>');
//	})
	
	var settings = {
		"initComplete": function () {
            var api = this.api();
            console.log("page.len = " + api.page.len());
            var info = api.page.info();
            console.log('Currently showing page '+(info.page+1)+' of '+info.pages+' pages.');
            
        },
        'bPaginate': true,
        "pagingType": "full_numbers",
        "language": {
        	"search": "过滤: ",
        	"lengthMenu": "每页显示 _MENU_ 条记录",
        	"zeroRecords": "没有检索到数据",
        	"info": "显示 _START_ 到 _END_ /共 _TOTAL_ 条数据",
        	"infoEmpty": "没有数据",
        	"infoFiltered": "(从 _MAX_ 条数据中检索)",
        	"paginate": {
        		"first": "首页",
        		"previous": "前一页",
        		"next": "后一页",
        		"last": "尾页"
        	}
        	
        },
		"dom" : '<"top"<"clear">>rt<"bottom"<"clear">>',
		"lengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
		/*"columnDefs" : [ {
			"targets" : 4,
			"render" : function(data, type, full, meta) {
				if (data == null) {
					return '';
				}

				return (data != null && data === 'F') ? '女': '男';
			}
		}, {
			"targets" : 0,
			"visible" : false
		}  ],*/
        "columns": columns/*[
            { "data": "applyId" },
            {
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            },
            { "data": "username" },
            { "data": "phone" },
            { "data": "sex" },
            { "data": "description" }
        ]*/,
        "columnDefs": columnDefs,
        "order": [[columns.length - 1, 'asc']]
    }
	var $table = $('#' + tableId);
	$table.DataTable().clear();
	$table.DataTable().destroy();
	$table.DataTable(settings);
	
	var data = result.data;

//	table.recordsTotal = data.recordsTotal;
	$table.DataTable().rows.add(data).draw()
    .nodes()
    .to$()
    .addClass( 'new' );
    
    ///;
    ///
    
	return ;
}
///
var onDetailClickEvent = function() {
	
    var temp = $('#dbTable4WxRespMsgSDiv');
     // Add event listener for opening and closing details
    $('#dbTable4WxRespMsgGather tbody').on('click', 'td.details-control', function () {
    	console.log('tempTableWrapper: ' + temp);
    	temp = $('#dbTable4WxRespMsgSDiv');
    	console.log('tempTableWrapper: ' + temp);
    	if (temp.length <= 0) {
    		$('#wx_resp_msg').append(htmlTable4WxRespMsgS);
    	}
    	
        var tr = $(this).closest('tr');
        var row = $('#dbTable4WxRespMsgGather').DataTable().row( tr );
        tr = $(tr);
        // row.child.isShown()
        //console.log(tr.next());
        if ( tr.hasClass('shown') ) {
            // This row is already open - close it
//            row.child.hide();
			tr.next().find('#dbTable4WxRespMsgS').parents('tr').hide();
            tr.removeClass('shown');
        }
        else {
        	if (tr.next().find('#dbTable4WxRespMsgSDiv').length > 0) {
				tr.next().find('#dbTable4WxRespMsgS').parents('tr').show();
				tr.addClass('shown');
        	} else {
	            // Open this row
	//            row.child( tempTableWrapper ).show();
				row.child( temp ).show();
	//			$('#dbTable4WxRespMsgS').parents('tr').show();
	            tr.addClass('shown');
        	}
        }
    } )
}
///



 