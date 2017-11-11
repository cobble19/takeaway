var table4ActivityGather;
$(document).ready(function() {

    table4ActivityGather = $('#dbTable4ActivityGather').DataTable( {
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
		"dom" : '<"top"fl<"clear">>rt<"bottom"ip<"clear">>',
		"lengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
		"columnDefs" : [ {
			"targets" : 0,
			"render" : function(data, type, full, meta) {
				var checkBox = '<input type="checkbox" name="chkBox" value="'
					+ full.activityGatherId
					+ '">';
				return checkBox;
				;
			}
		},{
			"targets" : 1,
			"render" : function(data, type, full, meta) {
				//console.log(data + " " + type + " " + full + " " + meta);
			}
		}, {
			"targets" : [2],
			"visible": false
		}, {
			"targets": [4,5],
			"visible": false,
			"render" : function(data, type, full, meta) {
				var date = new Date(data);
				return date.format('Y-m-d H:i:s');
			}
		}, {
			"targets": [6],
			"render" : function(data, type, full, meta) {
				var startDateTime = new Date(full.startDateTime);
				var endDateTime = new Date(full.endDateTime);
				return startDateTime.format('Y-m-d') + "/" + endDateTime.format('Y-m-d');
			}
		}, {
			"targets": [7],
			"render" : function(data, type, full, meta) {
				var expired = false;
				if (data != null && data) {
					expired = true;
				}
				return expired ? '<font style="color: red;">过期</font>' : '未过期';
			}
		},  {
			"targets" : 8,
			"visible": false,
			"render" : function(data, type, full, meta) {
				var content = (data != null) ? data.substring(0, 10): '';
				content += '...>>';
				var link = '<a target="_blank" href="' + $('#basePath').val() + '/page/unified/activity_gather_detail.jsp?activityGatherId=' + full.activityGatherId + '">' +
						content + '</a>';
				return link;
			}
		},
//		},  {
//			"targets" : 9,
//			"render" : function(data, type, full, meta) {
//				var ret = '未知';
//				if (data == null) {
//					ret = '未知';
//				} else if (data == 1) {
//					ret = '报名申请';
//				} else if (data == 2) {
//					ret = '征集调查';
//				}
//				return ret;
//			}
//		}, {
			{
			"targets" : 9,
			"render" : function(data, type, full, meta) {


				var href4Vote = $('#basePath').val() + '/web/unified/apply2inactivity/vote?activityGatherId='  + full.activityGatherId;
				var link4Vote = '<a target="_blank" data-toggle="tooltip" data-placement="top" title="表单汇总"' +
						' class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="' + href4Vote
				+ '">'
				+ '<span style="color: green;" class="glyphicon glyphicon-thumbs-up"></span>'
				 +
				/**'表单汇总' +*/
				 '</a>';
				 
				var href2 = $('#basePath').val() + '/page/unified/apply2_in_activity.jsp?activityGatherId='  + full.activityGatherId;
				var linkApply2 = '<a target="_blank" data-toggle="tooltip" data-placement="top" title="表单汇总"' +
						' class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="' + href2
				+ '">'
				+ '<span style="color: green;" class="glyphicon glyphicon-list"></span>'
				 +
				/**'表单汇总' +*/
				 '</a>';
				 
				
				var hrefEdit = $('#basePath').val() + '/page/unified/activity_gather_update.jsp?activityGatherId='  + full.activityGatherId;
				var linkEdit = '<a target="_blank" data-toggle="tooltip" data-placement="top" title="修改内容"' +
						' class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="' + hrefEdit
								+ '">'
								+ '<span style="color: green;" class="glyphicon glyphicon-edit"></span>'
								/**+ '修改内容'**/ 
								+ '</a>';

				var url = $('#basePath').val() + '/page/unified/activity_gather_detail.jsp?activityGatherId=' + full.activityGatherId + "&hidContent=1&a=1";
				var urlCopy = '<a target="_blank" data-toggle="tooltip" data-placement="top" title="相关链接"' +
						' class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="#" ' +
						'onClick="openUrlDivII(\'' + url + '\'); return false;">'
				+ '<span style="color: green;" class="glyphicon glyphicon-link"></span>'
				/**+ '相关链接'**/
				+ '</a>';
				var picBtn = '<a target="_blank" data-toggle="tooltip" data-placement="top" title="活动简图"' +
						' class="btn btn-warning btn-xs picBtn" style="margin-bottom:5px;" href="#" onclick="openPicDiv(this); return false;">'
				+ '<span style="color: green;" class="glyphicon glyphicon-picture"></span>'
				/**+ '活动简图'**/ 
				+ '</a>';
				
				var apply2AttrModelBtn = '<a target="_blank" data-toggle="tooltip" data-placement="top" title="创建表单"' +
						' class="btn btn-warning btn-xs picBtn" style="margin-bottom:5px;" href="#" onclick="openApply2AttrModelDiv(this); return false;">'
					+ '<span style="color: green;" class="glyphicon glyphicon-pencil"></span>'
					/**+ '创建表单'**/ 
					+ '</a>';
					
				
				var publishType = (full.publishType == null || full.publishType == 0) ? '发布' : '撤销';
				var publishTypeTitle = (full.publishType == null || full.publishType == 0) ? '发布活动' : '撤销活动';
				var linkPublish = '<a data-toggle="tooltip" data-placement="top" title="' + publishTypeTitle + '"' +
						' class="btn btn-warning btn-xs picBtn" style="margin-bottom:5px;" href="#" onclick="changePublish4ActivityGather(' + full.activityGatherId + ',' + full.publishType + '); return false;">' 
//						+ publishType 
						+ ((full.publishType == null || full.publishType == 0) 
							? '<span style="color: green;" class="glyphicon glyphicon-ok"></span>'
							: '<span style="color: green;" class="glyphicon glyphicon-ban-circle"></span>')
						+ '</a>';
				
				return linkEdit + " " + urlCopy;
//				return /*linkApply + " " + */link4Vote + " " + linkApply2 + " " + linkEdit + " " + urlCopy
//					 + " " + picBtn + " " + apply2AttrModelBtn + " " + linkPublish;
			}
		}/*, {
			"targets" : 0,
			"searchable" : false,
			"orderable" : false
		}, {
			"targets" : [1, 2, 3, 4, 8, 9, 10, 14],
			"visible": false
		}, {
			"targets" : 15,
			"render" : function(data, type, full, meta) {
				var date = new Date();
				date.setTime(data);
				return date.format('Y-m-d H:i:s');
			}
		}, {
			"targets" : 11,
			"render" : function(data, type, full, meta) {
				return data === 0 ? 'BTS': 'Prod';
			}
		}*/ ],
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
            { "data": "activityGatherId" },
            { "data": "title" },
            { "data": "startDateTime" },
            { "data": "endDateTime" },
            {
                /*"className":      'details-control',*/
                "data":           null,
                "defaultContent": ''
            },
            { "data": "expired" },
            { "data": "content" },
//            { "data": "typeCode" },
            {
                /*"className":      'details-control',*/
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            }
        ],
        "order": [[1, 'asc']]
    } );
    
    table4ActivityGather.on( 'order.dt search.dt', function () {
    	table4ActivityGather.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
    
    /*$('a.picBtn').click(function(e) {
    	e.preventDefault();
    	console.log($(this));
    	data = table4ActivityGather.row($(this).parent('tr')).data();
    	console.log(data);
    });*/
    

	$('#picForm').validate();
	
	$('#picForm').find('#uploadBtn').click(function(e) {
		$('#progress').dialog('open');
		var form1 = $(this).parents('form');
		var formData;
	    formData = new FormData();

	    var userId = $('#userId').val();
		
		formData.append('pic', form1.find('input[name=pic]').get(0).files[0]);
	    formData.append('userId', userId);
	    formData.append('wxTemplateId', $('#activityGatherId').val());
	    formData.append('orderNo', 9999);

	    $.ajax({
	        url: '../../htmleditor/pic/add',
	        contentType:"multipart/form-data",
	        data: formData,
	        processData: false,
	        type: 'POST',
	        contentType: false, // tell jQuery not to set contentType
	        success : function(data) {
	    		/*$('#progress').dialog('close');
	            alert('上传成功');*/
	            form1.find('#logoImg').val(data.file_url);
	            
	            addOrUpdatePic();
	        }
	    });	// ajax end
	    
	});	// upload pic to picture files
	
	/*$('#picForm').find('#addBtn').click(function(e) {
		$('#progress').dialog('open');
		var params = {
				userId: $('#userId').val(),
				logoImg: $('#logoImg').val(),
				activityGatherId: $('#activityGatherId').val()
		};
		$.ajax({
			"url" : $('#basePath').val() + "/api/enterprise/activity/addOrUpdate",
			"type" : "POST",
			"headers" : {
				"Content-Type" : "application/json"
			},
			"dataType" : 'json',
			"data": JSON.stringify({
	            title: $("#title").val()
	        }),
			data: params,
	        success: function(data, textStatus, jqXHR ) {
	    		$('#progress').dialog('close');
	    		alert('更新图片成功');
	        	$('#picDiv').dialog('close');
	        	console.log("data = " + data);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	        	console.log('Load Error!');
	        },
	        complete: function(jqXHR, textStatus) {
	        	console.log('Ajax complete.');
	        }
		});
	});*/
    
    $('#chkBoxAll').click(function() {
    	var chkBoxAll = $(this).attr('checked');
    	if (chkBoxAll) {
    		$('#dbTable4ActivityGather').find('input[name=chkBox]').attr('checked', true);
    	} else {
    		$('#dbTable4ActivityGather').find('input[name=chkBox]').attr('checked', false);
    	}
    })

    $('#deleteBtn').click(function() {
    	var ids = [];
    	var chkBox = $('#dbTable4ActivityGather').find('input[name=chkBox]');
    	
    	chkBox.each(function(index, ele) {
//    		console.log($(this).val() + ele.value);
        	/*var tr = $(this).closest('tr');
            var row = table4ActivityGather.row( tr );*/
    		if ($(this).attr('checked')) {
//    			ids.push(row.data().activityGatherId);
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
    		"url" : "../../mgr/activitygather/delete",
    		"type" : "GET",
    		/*"headers" : {
    			"Content-Type" : "application/json"
    		},*/
    		"dataType" : 'json',
    		traditional :true, 
    		"data": {
                "ids": ids
            },
            success: function(data, textStatus, jqXHR ) {
            	$('#progress').dialog('close');
            	activityGatherSearch(table4ActivityGather);
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});
    })
    
    $('#showLinkBtn4ActivityGather').click(function() {
    	openActivityGatherActiveListDiv();
    });
    
    $('#activityGatherDetailDiv').dialog({
    	autoOpen: false,
    	modal: true,
    	width: 660,
    	height: 150
    });
    
    $('#activityGatherActiveListDiv').dialog({
    	autoOpen: false,
    	modal: true,
    	width: 660,
    	height: 150
    });
    
    $('#picDiv').dialog({
    	autoOpen: false,
    	modal: true,
    	width: 450,
    	height: 350
    });
    $('#apply2AttrModelDiv').dialog({
    	autoOpen: false,
    	modal: true,
    	width: 450,
    	height: 350
    });
    
    onClickAddAttr();
    onClickAttrSummit();
    onClickPopAttr();
    deleteAttrModel();
    /// 1
    var clipboard = new Clipboard('#copyBtn4ActivityGather');

    clipboard.on('success', function(e) {
    	try {
            console.info('Action:', e.action);
            console.info('Text:', e.text);
            console.info('Trigger:', e.trigger);
    	} catch (ex) {
    		console.log("ex: " + ex);
    	}

//        e.clearSelection();
        alert('复制成功！');
    });

    clipboard.on('error', function(e) {
    	try {
            console.error('Action:', e.action);
            console.error('Trigger:', e.trigger);
    	} catch (ex) {
    		console.log("ex: " + ex);
    	}
        alert('复制失败！ 请用【Ctrl+C】， 同时按下Ctrl和C');
    });
    
    ///  2
    var clipboard2 = new Clipboard('#copyBtn24ActivityGather');

    clipboard2.on('success', function(e) {
    	try {
            console.info('Action:', e.action);
            console.info('Text:', e.text);
            console.info('Trigger:', e.trigger);
    	} catch (ex) {
    		console.log("ex: " + ex);
    	}

//        e.clearSelection();
        alert('复制成功！');
    });

    clipboard2.on('error', function(e) {
    	try {
            console.error('Action:', e.action);
            console.error('Trigger:', e.trigger);
    	} catch (ex) {
    		console.log("ex: " + ex);
    	}
        alert('复制失败！ 请用【Ctrl+C】， 同时按下Ctrl和C');
    });
    /// 2
    
    ///  3
    var clipboard3 = new Clipboard('#copyBtn4activityActiveList');

    clipboard3.on('success', function(e) {
    	try {
            console.info('Action:', e.action);
            console.info('Text:', e.text);
            console.info('Trigger:', e.trigger);
    	} catch (ex) {
    		console.log("ex: " + ex);
    	}

//        e.clearSelection();
        alert('复制成功！');
    });

    clipboard3.on('error', function(e) {
    	try {
            console.error('Action:', e.action);
            console.error('Trigger:', e.trigger);
    	} catch (ex) {
    		console.log("ex: " + ex);
    	}
        alert('复制失败！ 请用【Ctrl+C】， 同时按下Ctrl和C');
    });
    

    $('#verifyDiv').dialog({
    	autoOpen: false,
    	modal: true
    });
    
    /*$('#pwdDiv').dialog({
    	autoOpen: false,
    	modal: true
    })*/
    
    activityGatherSearch(table4ActivityGather);
    
    $('#searchBtn4ActivityGather').click(function() {
    	console.log('search click...');
    	activityGatherSearch(table4ActivityGather);
    })
     
    // Add event listener for opening and closing details
    $('#activityGatherList tbody').on('click', 'td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = table4ActivityGather.row( tr );
 
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( format(row.data()) ).show();
            tr.addClass('shown');
            $("form").submit(function(e) {
            	e.preventDefault();
            	var username = $("#username").val();
            	var phone = $("#phone").val();
            	var activityGatherId = $("#activityGatherId").val();
            	
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
            			activityGatherId: activityGatherId
                    }),
                    success: function(data, textStatus, jqXHR ) {
                    	console.log("data = " + data);
                    	alert('添加成功！')
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                    	console.log('Load Error!');
                    },
                    complete: function(jqXHR, textStatus) {
                    	console.log('Ajax complete.');
                    }
            	});	// ajax
            	//return false;
            });
        }
    } );
    
    
} );


/* Formatting function for row details - modify as you need */
function format ( d ) {
    // `d` is the original data object for the row
    return '<form id="applyForm" class="form-inline">' +
    		'<input type="hidden" id="activityGatherId" name="activityGatherId" value="' + d.activityGatherId + '"/>' + 
    		'<div class="form-group">' + 
	    		'<label for="username">姓名: </label>' +
	    		'<input id="username" name="username" placeholder="姓名" class="form-control"/>' + 
	    	'</div>' +
	    	'<div class="form-group">' + 
	    		'<label for="phone">手机号: </label>' +
	    		'<input id="phone" name="phone" placeholder="手机号" class="form-control"/>' +
	    	'</div>' + 
	    		'<input type="submit" id="applyBtn" value="申请参加" class="btn btn-default"/>' +
    	'</form>';
}

var activityGatherSearch = function(table) {
	$('#progress').dialog('open');
	var params = {
//            typeCode: $("#typeCode option:selected").val()
	}
	$.ajax({
		"url" : "../../api/unified/activityGatherByUserId",
		"type" : "POST",
		/*"headers" : {
			"Content-Type" : "application/json"
		},*/
		/*contentType:"application/x-www-form-urlencoded",*/
		"dataType" : 'json',
		"data": (params),
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
        	$("td.details-control").attr('title', '申请参加');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var openUrlDivII = function(url) {
	$('#activityGatherDetailDiv').dialog('open');
	
	$('#activityGatherDetailUrl').val(url);
	$('#activityGatherDetailUrl2').val(url.replace('hidContent=1', 'hidContent=0'));
	
}

var openActivityGatherActiveListDiv = function() {
	$('#activityActiveListDiv').dialog('open');
	var url = $('#basePath').val() + '/web/unified/activity/list/active/' + $('#userId').val();
	$('#activityActiveListUrl').val(url);
	
}

var openPicDiv = function(t) {
	data = $('#dbTable4ActivityGather').DataTable().row($(t).closest('tr')).data();
	console.log(data);
	$('#activityGatherId').val(data.activityGatherId);
	$('#logoImg').val(data.logoImg);
	$('#picDiv').dialog('open');
}
var openApply2AttrModelDiv = function(t) {
	data = $('#dbTable4ActivityGather').DataTable().row($(t).closest('tr')).data();
	console.log(data);
	$('#apply2AttrModelForm').find('#activityGatherId').val(data.activityGatherId);
	
	$('#apply2AttrModelDiv').dialog('open');
	showApply2AttrModel();
}

function showApply2AttrModel() {
	var activityGatherId = $('#apply2AttrModelForm').find('#activityGatherId').val();

	$('#progress').dialog('open');

	$.ajax({
		"url" : $('#basePath').val() + "/api/apply2AttrModel/" + activityGatherId,
		"type" : "GET",
		/*"headers" : {
			"Content-Type" : "application/json"
		},*/
		"dataType" : 'json',
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
		/*data: params,*/
        success: function(data, textStatus, jqXHR ) {
    		$('#progress').dialog('close');
        	console.log("data = " + data);
        	if (data.success) {
            	var apply2AttrModels = data.data;
            	if (apply2AttrModels.length > 0) {
            		$('#addAttrBtn').hide();
            		$('#popAttrBtn').hide();
            		$('#addBtn').hide();
            		$('#deleteAttrBtn').show();
            		
            		// 
            		$('#apply2AttrModelForm div.form-group.static-attr').remove();
            		$('#apply2AttrModelForm div.form-group.dynamic-attr').remove();
            		/*var lables = $('#apply2AttrModelForm .control-label');
            		var i = lables.length;*/
            		for (var i = 0; i < apply2AttrModels.length; i++) {
            			var apply2AttrModel = apply2AttrModels[i];
                		var inputText = '<div class="form-group dynamic-attr">'
                						+ '<label class="control-label col-sm-4" for="' + 'attr' + i + '">' + '条目' + (i + 1) + ':' + '</label>'
                						+ '<div class="col-sm-8">'
                						+  '<input type="text" class="form-control attr" required="required" readonly id="' + 'attr' + i + '"'
                						+ ' name="' + 'attr' + i + '"' 
                						+ ' placeholder="请输入' + '条目' + (i + 1) + '标题' + '"'
                						+ ' value="' + apply2AttrModel.apply2AttrModelName + '"'
                						+ '>'
                						+  '<input type="text" class="form-control remark" readonly id="' + 'remark' + i + '"' 
                						+ ' name="' + 'remark' + i + '"' 
                						+ ' placeholder="请输入' + '条目' + (i + 1) + '备注' + '"' 
                						+ ' value="' + apply2AttrModel.apply2AttrModelRemark + '"'
                						+ '>'
                						+ '</div>'
                						+'</div>'
                			;
                		$('#apply2AttrModelForm div.form-group:nth-last-child(1)').before(inputText);
            		}
            		var form = $('#apply2AttrModelForm'); // trigger
            		
            	} else {
            		// remove all and add tpl_static
            		$('#apply2AttrModelForm div.form-group.static-attr').remove();
            		$('#apply2AttrModelForm div.form-group.dynamic-attr').remove();
            		/*$('#apply2AttrModelForm div.form-group:nth-last-child(1)').before($('div#tpl_static div.form-group.static-attr').clone());*/
            		
            		$('#addAttrBtn').show();
            		$('#popAttrBtn').show();
            		$('#addBtn').show();
            		$('#deleteAttrBtn').hide();
            	}
        	} else {
        		alert('查询表单定义失败');
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

function deleteAttrModel() {
	$('#deleteAttrBtn').click(function() {
		var ret = confirm('确认删除此活动的表单 ？ 如果确定， 将删除所有和表单相关的信息！');
		if (!ret) {
			return;
		}
		
		var activityGatherId = $('#apply2AttrModelForm').find('#activityGatherId').val();
	
		$('#progress').dialog('open');
	
		$.ajax({
			"url" : $('#basePath').val() + "/api/apply2AttrModel/delete/" + activityGatherId,
			"type" : "GET",
			/*"headers" : {
				"Content-Type" : "application/json"
			},*/
			"dataType" : 'json',
			/*"data": JSON.stringify({
	            title: $("#title").val()
	        }),*/
			/*data: params,*/
	        success: function(data, textStatus, jqXHR ) {
	    		$('#progress').dialog('close');
	        	console.log("data = " + data);
	        	if (data.success) {
	        		alert('删除成功');
	        		$('#apply2AttrModelDiv').dialog('close');
	        	} else {
	        		alert('删除失败');
	        	}
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

function jsCopy(){ 
    var e=document.getElementById("activityDetailUrl");//对象是content 
    e.select(); //选择对象 
    document.execCommand("Copy"); //执行浏览器复制命令
    alert("已复制好，可贴粘。"); 
}

function addOrUpdatePic() {
	// update DB
    var params = {
			userId: $('#userId').val(),
			logoImg: $('#logoImg').val(),
			activityGatherId: $('#activityGatherId').val()
	};
	$.ajax({
		"url" : $('#basePath').val() + "/api/enterprise/activity/addOrUpdate",
		"type" : "POST",
		/*"headers" : {
			"Content-Type" : "application/json"
		},*/
		"dataType" : 'json',
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
		data: params,
        success: function(data, textStatus, jqXHR ) {
    		$('#progress').dialog('close');
    		alert('更新图片成功');
        	$('#picDiv').dialog('close');
        	console.log("data = " + data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
var onClickPopAttr = function() {
	$('#popAttrBtn').click(function() {
		var form = $('#apply2AttrModelForm');
		var formGroup = $('#apply2AttrModelForm div.form-group .control-label');
		var length = formGroup.length;
		if (length <= 2) {
			alert('必须保留2个条目！');
			return;
		} else {
			$('#apply2AttrModelForm div.form-group:nth-last-child(2)').remove();
		}
	});
}
var onClickAddAttr = function() {
	$('#addAttrBtn').click(function() {
		var lables = $('#apply2AttrModelForm .control-label');
		var i = lables.length;
		var inputText = '<div class="form-group dynamic-attr">'
						+ '<label class="control-label col-sm-4" for="' + 'attr' + i + '">' + '条目' + (i + 1) + ':' + '</label>'
						+ '<div class="col-sm-8">'
						+  '<input type="text" class="form-control attr" required="required" id="' + 'attr' + i + '" name="' + 'attr' + i + '" placeholder="请输入' + '条目' + (i + 1) + '标题' + '">'
						+  '<input type="text" class="form-control remark" id="' + 'remark' + i + '" name="' + 'remark' + i + '" placeholder="请输入' + '条目' + (i + 1) + '备注' + '">'
						+ '</div>'
						+'</div>'
			;
		$('#apply2AttrModelForm div.form-group:nth-last-child(1)').before(inputText);
		var form = $('#apply2AttrModelForm'); // trigger
	});
	/*$('#addPicBtn').click(function() {
		var lables = $('#apply2AttrModelForm .control-label');
		var i = lables.length;
		var inputText = '<div class="form-group">'
						+ '<label class="control-label col-sm-4" for="' + 'attr' + i + '">' + '条目图片、文件' + i + ':' + '</label>'
						+ '<div class="col-sm-8">'
						+  '<input type="text" class="form-control" id="' + 'attr' + i + '" name="' + 'attr' + i + '" readonly="readonly" placeholder="请输入' + '条目' + i + '内容' + '">'
						+  '<input class="" id="pic" name="pic" type="file">'
						+  '<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传">'
						+ '</div>'
						+'</div>'
			;
		$('#apply2AttrModelForm div.form-group:nth-last-child(2)').after(inputText);
		var form = $('#apply2AttrModelForm'); // trigger
	})*/
}

///onClickAttrSummit
var onClickAttrSummit = function() {
		var form = $("form[id=apply2AttrModelForm]");
		
		$(form).find('#addBtn').click(function(e) {
			$('#apply2AttrModelDiv').dialog('open');
			
			inputTexts = $('#apply2AttrModelForm div input.form-control.attr:text');
			
			if (!!inputTexts && inputTexts.length < 1) {
				alert('请至少添加一个条目');
				return;
			}
			
			var ret = confirm('确认添加此活动的表单 ？');
			if (!ret) {
				return;
			}
			
			
			var form1 = $(this).parents('form');
			
			if (!$(form1).valid()) {
//				alert('请正确输入');
				return;
			}
			
			var formData;
		    formData = new FormData();
		    
		    
		    var apply2AttrModelPOJOs = [];
		    /*var count = 0;*/
		    
		    var userId = $('#userId').val();
		    var activityGatherId = $(form1).find('#activityGatherId').val();
		    
		    console.log('inputTexts length: ' + inputTexts.length);

		    for (var i = 0; i < inputTexts.length; i++) {
		    	var inputText = inputTexts[i];
		    	var apply2AttrModelPOJO = {};
		    	apply2AttrModelPOJO.apply2AttrModelName = $(inputText).val();
		    	apply2AttrModelPOJO.apply2AttrModelRemark = $(inputText).next().val();
		    	apply2AttrModelPOJO.orderNo = i;
		    	apply2AttrModelPOJO.activityGatherId = activityGatherId;
		    	apply2AttrModelPOJOs.push(apply2AttrModelPOJO);
		    }

		    /*var count = count + inputTexts.length;*/
		    
		   /* inputRadios = $('#apply2AttrModelForm div.form-group.static-attr-radio input[type=radio]:checked');
		    console.log('inputRadios length: ' + inputRadios.length);
		    for (var i = 0; i < inputRadios.length; i++) {
		    	var index = i + count;
		    	var inputRadio = inputRadios[i];
		    	var apply2AttrModelPOJO = {};
		    	apply2AttrModelPOJO.apply2AttrModelName = $(inputRadio).val();
		    	apply2AttrModelPOJO.orderNo = index;
		    	apply2AttrModelPOJO.activityGatherId = activityGatherId;
		    	apply2AttrModelPOJOs.push(apply2AttrModelPOJO);
		    }*/

		    /*count = count + inputRadios.length;
		    
		    inputTexts = $('#apply2AttrModelForm div.dynamic-attr input:text');

		    console.log('inputTexts dynamic length: ' + inputTexts.length);
		    console.log('inputTexts: ' + JSON.stringify(inputTexts));
		    
		    for (var i = 0; i < inputTexts.length; i++) {
		    	var index = i + count;
		    	var inputText = inputTexts[i];
		    	var apply2AttrModelPOJO = {};
		    	apply2AttrModelPOJO.apply2AttrModelName = $(inputText).val();
		    	apply2AttrModelPOJO.orderNo = index;
		    	apply2AttrModelPOJO.activityGatherId = activityGatherId;
		    	apply2AttrModelPOJOs.push(apply2AttrModelPOJO);
		    }*/
		    
		    var basePath = $("#basePath").val();
		    /*if (imgSrc.indexOf(basePath) > -1) {
		    	imgSrc = imgSrc.substring(imgSrc.indexOf(basePath));
		    }*/
		    
		    /*var wxTemplateId = $('input[name=wxTemplateId]:checked').val()
		    console.log("wxTemplateId: " + wxTemplateId);
		    formData.append('wxTemplateId', wxTemplateId);
		    var userId = $('#userId').val();
		    formData.append('userId', userId);*/
		    formData.append('apply2AttrModelPOJOs', apply2AttrModelPOJOs);
		    
		    console.log("apply2AttrModelPOJOs: " + JSON.stringify(apply2AttrModelPOJOs));
		    
		    $.ajax({
		        url: $("#basePath").val() + '/api/media/apply2AttrModels/add',
		        data: JSON.stringify(apply2AttrModelPOJOs),
//		        processData: false,
		        type: 'POST',
		        contentType: "application/json",
		        success : function(data) {
		        	if (data.success) {
			            alert('创建申请人信息字段成功');
		        	} else {
			            alert('创建申请人信息字段失败！！！msg：' + data.desc);
		        	}

		    		$('#apply2AttrModelDiv').dialog('close');
		        }
		    });	// ajax end
		    
		})
		
}
/// end onClickAttrSummit

/// changePublish4ActivityGather
var changePublish4ActivityGather = function(activityGatherId, publishType) {
	// 取反
	if (publishType == null || publishType == 0) {
		publishType = 1;
	} else {
		publishType = 0;
	}
	
	$('#progress').dialog('open');
	$.ajax({
		"url" : $('#basePath').val() + "/api/unified/activity/publishType",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		/*"data": JSON.stringify({
			wxTemplateId: wxTemplateId
        }),*/
		"data": {
			activityGatherId: activityGatherId,
			publishType: publishType
        },
        success: function(data, textStatus, jqXHR ) {
        	$('#progress').dialog('close');
        	
//        	alert('更新成功, activityGatherId: ' + activityGatherId);	
			var msg = publishType === 0 ? '撤销成功' : '发布成功';
			alert(msg);
        	activityGatherSearch(table4ActivityGather);
        	
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
/// end changePublish4ActivityGather




 