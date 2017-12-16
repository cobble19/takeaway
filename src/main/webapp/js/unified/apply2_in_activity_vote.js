$(document).ready(function() {
	applyInActivitySearch();
	
	
	$('#startDateTime').datetimepicker({
		lang:'ch',
		timepicker:true,
		/*format: 'Y-m-d H:i'*/
		value: '2015-02-20 20:20:20'
	});
	$('#endDateTime').datetimepicker({
		lang:'ch',
		timepicker:true,
		/*format: 'Y-m-d H:i'*/
		value: '2015-02-20 21:22:23'
	});
//	$('#startDateTime, #endDateTime').change(function() {
//		var startDateTime = $('#startDateTime').val();
//		var endDateTime = $('#endDateTime').val();
//		if (startDateTime >= endDateTime) {
//		}
//	})

	$('#searchBtn4Apply2').click(function() {
		applyInActivitySearch();
	});
	$('#settingFilter4VoteBtn').click(function() {
		settingFilter4Vote();
	});
	
	$('#exportBtn').click(function() {
		var activityId = getParam('activityId');
		var startDateTime = $('#startDateTime').val();
		var endDateTime = $('#endDateTime').val();
		window.location.href = $('#basePath').val() + "/api/apply2/v2/export/xls/vote?activityId=" +
				activityId + "&startDateTime=" + startDateTime + "&endDateTime=" + endDateTime;
	});
	
	
    /*var table = $('#dbTable').DataTable(  );*/
    
    
    /*table.on( 'order.dt search.dt', function () {
    	table.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();*/
    
//    applyInActivitySearch(table);
     
} );
/// end $function
// query apply by activityId
var applyInActivitySearch = function() {
	var params = {};
	var activityId = getParam('activityId');
	console.log('title=' + getParam('activityTitle'));
	
	var startDateTime = $('#startDateTime').val();
	var endDateTime = $('#endDateTime').val();
	if (startDateTime == null || startDateTime == '') {
//		startDateTime = new Date();
	} else {
		params.startDateTime = startDateTime;
	}
	if (endDateTime == null || endDateTime == '') {
//		endDateTime = new Date();
	} else {
		params.endDateTime = endDateTime;
	}
	
	$.ajax({
		"url" : $('#basePath').val() + "/api/apply2/v2/" + activityId + "/vote",
		"type" : "GET",
		/*"headers" : {
			"Content-Type" : "application/json"
		},*/
		"dataType" : 'json',
		"data": params,
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
        	var activityPOJO = data.activityPOJO;
        	if (activityPOJO != null) {
            	$('#activityTitle').text(activityPOJO.title);
        	}
        	buildTable(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');;
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
///
///
var buildTable = function(result) {
	// init/clear  $('#trHeader')
	$('#trHeader').html('');
	columns = [];
	
	var chkColumn = {
			    /*"className":      'details-control',*/
			    "orderable":      false,
			    "data":           null,
			    "defaultContent": ''
			};
    var noColumn = {
                /*"className":      'details-control',*/
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            };
    columns.push(chkColumn);
    columns.push(noColumn);
	
	var temp = result.columns;
	$.each(temp, function(i, e) {
		column = {
				/*"data": '\"' + e + '\"'*/
				"data": e
		};
		columns.push(column);
	});
	column = ({
                /*"className":      'details-control',*/
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            });
	columns.push(column);
	
	columnDefs = [];
	
	columnDef = ({
		"targets": columns.length - 1,
		"visible": true,
		"render" : function(data, type, full, meta) {
			// voteItemId
			console.log("length - 1: " + full);
			var voteItemId = full.voteItemId;
			var approveFlag = full.approveFlag;
			var unapproved = (approveFlag == null || approveFlag == 0);
			
			var ret = unapproved ? "未审批" : "已审批";
			var title = unapproved ? "审批" : "撤销审批";
			var icon = unapproved ? ' glyphicon-ok-circle' : ' glyphicon-ban-circle'
//			var approveVoteItemBtn = '<button id="approveVoteItem" class="btn btn-default btn-sm" ' + 
//		     							' data-toggle="tooltip" data-placement="top" title="' + title + '"' +
//		     							' onclick="javascript: approveVoteItem(this' + ',' + JSON.stringify(data).replace(/"/g, '&quot;') 
//		     							+ ',' + 0 + ',' + JSON.stringify(full).replace(/"/g, '&quot;')
//		     							 + ',' + JSON.stringify(meta).replace(/"/g, '&quot;') + ')"' + 
//		     							'> ' + title + 
//		     							' <span style="color: green;" class="glyphicon ' + icon + '"></span>' +
//		     						'</button>';
			var approveVoteItemBtn = '<button id="approveVoteItem" class="btn btn-default btn-sm" ' + 
		     							' data-toggle="tooltip" data-placement="top" title="' + title + '"' +
		     							' onclick="javascript: approveVoteItem(this' + ')"' + 
		     							'> ' + title + 
		     							' <span style="color: green;" class="glyphicon ' + icon + '"></span>' +
		     						'</button>';
			
			return ret + approveVoteItemBtn;
		}
	});
	columnDefs.push(columnDef);
	
	columnDef = {
		"targets": columns.length - 6,
		"visible": true,
		"render" : function(data, type, full, meta) {
			var createDateTime = new Date(full.createDateTime);
			return createDateTime.format('Y-m-d H:i:s');
		}
	};
	columnDefs.push(columnDef);
	columnDef = {
		"targets": [columns.length - 2,columns.length - 4,columns.length - 5],
		"visible": false
	};
	columnDefs.push(columnDef);
	
	var chkTarget = {
			"targets" : 0,
			"render" : function(data, type, full, meta) {
				var checkBox = '<input type="checkbox" name="chkBox" value="'
					+ full.activityId
					+ '">';
				return checkBox;
				;
			}
		};
	var numTarget = {
			"targets" : 1,
			"render" : function(data, type, full, meta) {
				//console.log(data + " " + type + " " + full + " " + meta);
			}
		};
	columnDefs.push(chkTarget);
	columnDefs.push(numTarget);
	
	$('#trHeader').append('<th><input type="checkbox" name="chkBoxAll" id="chkBoxAll">全选</th>');
	$('#trHeader').append('<th>序号</th>');
	
	$.each(result.trHeaderNames, function(i, e){
		$('#trHeader').append('<th>' + e + '</th>');
	});
	$('#trHeader').append('<th>审批</th>');
	
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
		"dom" : '<"top"fl<"clear">>rt<"bottom"ip<"clear">>',
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
        "order": [[columns.length - 2, 'asc']]
    }
	
	$('#dbTable').DataTable().clear();
	$('#dbTable').DataTable().destroy();
	$('#dbTable').DataTable(settings);
	
	var data = result.apply2POJOList;

//	table.recordsTotal = data.recordsTotal;
	$('#dbTable').DataTable().rows.add(data).draw()
    .nodes()
    .to$()
    .addClass( 'new' );
    $('#dbTable').DataTable().draw();
    
    
    $('#dbTable').DataTable().on( 'order.dt search.dt', function () {
    	$('#dbTable').DataTable().column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
    
    
    $('#chkBoxAll').click(function() {
    	var chkBoxAll = $(this).attr('checked');
    	if (chkBoxAll) {
    		$('#dbTable').find('input[name=chkBox]').attr('checked', true);
    	} else {
    		$('#dbTable').find('input[name=chkBox]').attr('checked', false);
    	}
    })

    $('#approveBtn4Vote').click(function() {
    	var chkBox = $('#dbTable').find('input[name=chkBox]');
    	
    	var voteItemPOJOs = [];
    	
		var apply2AttrModelIds = "";
		
		$('input[name=apply2AttrModelId]:checked').each(function(index, ele) {
			apply2AttrModelIds += $(this).val() + ",";
		});
		apply2AttrModelIds = apply2AttrModelIds.substring(0, apply2AttrModelIds.length - 1);
    	
    	chkBox.each(function(index, ele) {
//    		console.log($(this).val() + ele.value);
        	var tr = $(this).closest('tr');
            var row = $('#dbTable').DataTable().row( tr );
    		if ($(this).attr('checked')) {
    			var rowData = row.data();
				var voteItemId = rowData.voteItemId;
				var apply2Id = rowData.apply2Id;
				
				var approveFlag = rowData.approveFlag;
				if (approveFlag == null) {
					approveFlag = 0;
				}
				
    			var voteItemPOJO = {
					"apply2Id": apply2Id,
			        "voteItemId": voteItemId,
			        "approveFlag": approveFlag
				}
    			voteItemPOJOs.push(voteItemPOJO);
    		}
    	})
//    	console.log('voteItemPOJO: ' + voteItemPOJO);
    	if (voteItemPOJOs == null || voteItemPOJOs.length <= 0) {
    		alert('请选择一条记录');
    		return;
    	}

    	var confirm = window.confirm('确定审批');
    	if (!confirm) {
    		return;
    	}
    	submitVoteItemApprove(voteItemPOJOs, apply2AttrModelIds);
    })
    
	return ;
}
///
///
/// data, type, full, meta,数据量太大, 不用了
var approveVoteItem = function(btn, data, type, full, meta) {
	console.log(this);
	var activityId = getParam('activityId');
	var me = $(btn);
	var data = $('#dbTable').DataTable().rows(me.parent('td').parent('tr')).data();
	console.log(data[0]);
	var rowData = data[0];
	
	var voteItemId = rowData.voteItemId;
	var approveFlag = rowData.approveFlag;
	if (approveFlag == null) {
		approveFlag = 0;
	}
	var unapproved = (approveFlag == null || approveFlag == 0);
	
	
	var apply2Id = rowData.apply2Id;
	
	var apply2AttrModelIds = "";
	
	$('input[name=apply2AttrModelId]:checked').each(function(index, ele) {
		apply2AttrModelIds += $(this).val() + ",";
	});
	apply2AttrModelIds = apply2AttrModelIds.substring(0, apply2AttrModelIds.length - 1);
	
	console.log('voteItemId: ' + voteItemId + ", apply2Id: " + apply2Id);
	if (apply2Id == null) {
		alert('voteItemId: ' + voteItemId + ", apply2Id: " + apply2Id + ", 参数不正确");
		return;
	}
	
	var confirmMsg = unapproved ? '审批通过?' : '撤销审批?'

	var confirm = window.confirm(confirmMsg);
	if (!confirm) {
		return;
	}
	
	var voteItemPOJOs = [];
//	var voteItemPOJO = {
//		"apply2Id": apply2Id,
//        "voteItemId": voteItemId
//	}
	var voteItemPOJO = {};
	voteItemPOJO.apply2Id = apply2Id;
	voteItemPOJO.voteItemId = voteItemId;
	voteItemPOJO.approveFlag = approveFlag
	voteItemPOJOs.push(voteItemPOJO);
	
	submitVoteItemApprove(voteItemPOJOs, apply2AttrModelIds);
}
///
///
var submitVoteItemApprove = function(voteItemPOJOs, apply2AttrModelIds) {
	
	var activityId = getParam('activityId');
	
//	var voteItemParamPOJO = {
//		"voteItemPOJOs": voteItemPOJOs
//	};
	var voteItemParamPOJO = {};
	voteItemParamPOJO.voteItemPOJOs = voteItemPOJOs;
	
	var params = {};
	for (var i = 0; i < voteItemPOJOs.length; i++) {
		var voteItemPOJO = voteItemPOJOs[i];
		for (var key in voteItemPOJO) {
			params['voteItemPOJOs[' + i + '].' + key] = voteItemPOJO[key];
		}
	}
	params.apply2AttrModelIds = apply2AttrModelIds;
	
	$('#progress').dialog('open');
	$.ajax({
		"url" : $('#basePath').val() + "/api/apply2/v2/" + activityId + "/vote/approve",
		"type" : "POST",
//		"headers" : {
//			"Content-Type" : "application/json"
//		},
		"dataType" : 'json',
//		traditional :true, 
		"data": params,
        success: function(data, textStatus, jqXHR ) {
        	$('#progress').dialog('close');
        	if (data.success) {
        		alert('审批' + '成功');
        	}
        	window.location.reload();
//        	applyInActivitySearch();
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

var settingFilter4Vote = function() {
	console.log(this);
	var activityId = getParam('activityId');
	
	var apply2AttrModelIds = "";
	
	$('input[name=apply2AttrModelId]:checked').each(function(index, ele) {
		apply2AttrModelIds += $(this).val() + ",";
	});
	apply2AttrModelIds = apply2AttrModelIds.substring(0, apply2AttrModelIds.length - 1);
	
	console.log('activityId: ' + activityId + ", apply2AttrModelIds: " + apply2AttrModelIds);
	if (activityId == null) {
		alert('activityId: ' + activityId + ", apply2AttrModelIds: " + apply2AttrModelIds + ", 参数不正确");
		return;
	}
	
	var confirmMsg = '设置需要显示的信息'

	var confirm = window.confirm(confirmMsg);
	if (!confirm) {
		return;
	}
	$('#progress').dialog('open');
	$.ajax({
		"url" : $('#basePath').val() + "/api/apply2/v2/" + activityId + "/vote/settingfilter4vote",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		traditional :true, 
		"data": {
            "activityId": activityId,
            "apply2AttrModelIds": apply2AttrModelIds
        },
        success: function(data, textStatus, jqXHR ) {
        	$('#progress').dialog('close');
        	if (data.success) {
        		alert(confirmMsg + '成功');
        	}
        	window.location.reload();
//        	applyInActivitySearch();
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





 