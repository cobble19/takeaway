$(document).ready(function() {
	applyInActivitySearch();
	
	var activityTitle = getParam('activityTitle');
	$('#activityTitle').text(activityTitle);
	
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
	
	$('#exportBtn').click(function() {
		var activityId = getParam('activityId');
		var startDateTime = $('#startDateTime').val();
		var endDateTime = $('#endDateTime').val();
		window.location.href = $('#basePath').val() + "/api/apply2/v2/export/xls?activityId=" +
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
		"url" : $('#basePath').val() + "/api/apply2/v2/" + activityId,
		"type" : "GET",
		/*"headers" : {
			"Content-Type" : "application/json"
		},*/
		"dataType" : 'json',
		"data": params,
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
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
	columns = [];
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
			
			var ret = voteItemId == null ? "未审批" : "已审批";
			var title = voteItemId == null ? "审批" : "撤销审批";
			var icon = voteItemId == null ? ' glyphicon-ok' : ' glyphicon-cancle'
			var approveVoteItemBtn = '<button id="approveVoteItem" class="btn btn-default btn-sm" ' + 
		     							' data-toggle="tooltip" data-placement="top" title="' + title + '"' +
		     							' onclick="javascript: approveVoteItem(this' + ',' + JSON.stringify(data).replace(/"/g, '&quot;') 
		     							+ ',' + 0 + ',' + JSON.stringify(full).replace(/"/g, '&quot;')
		     							 + ',' + JSON.stringify(meta).replace(/"/g, '&quot;') + ')"' + 
		     							'> ' + 
		     							'审批 <span style="color: green;" class="glyphicon ' + icon + '"></span>' +
		     						'</button>';
			
			return ret + approveVoteItemBtn;
		}
	});
	columnDefs.push(columnDef);
	
	columnDef = {
		"targets": columns.length - 4,
		"visible": true,
		"render" : function(data, type, full, meta) {
			var createDateTime = new Date(full.createDateTime);
			return createDateTime.format('Y-m-d H:i:s');
		}
	};
	columnDefs.push(columnDef);
	
	
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
	return ;
}
///
///
var approveVoteItem = function(btn, data, type, full, meta) {
	console.log(this);
	var activityId = getParam('activityId');
	var me = $(btn);
	var data = $('#dbTable').DataTable().rows(me.parent('td').parent('tr')).data();
	console.log(data[0]);
	var rowData = data[0];
	
	var voteItemId = rowData.voteItemId;
	
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
	
	var confirmMsg = voteItemId == null ? '通过审批?' : '撤销审批?'

	var confirm = window.confirm(confirmMsg);
	if (!confirm) {
		return;
	}
	$('#progress').dialog('open');
	$.ajax({
		"url" : $('#basePath').val() + "/api/apply2/v2/" + activityId + "/approve",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		traditional :true, 
		"data": {
            "apply2Id": apply2Id,
            "voteItemId": voteItemId,
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


 