var table4PointSummary;

$(document).ready(function() {
    table4PointSummary = $('#dbTable4PointSummary').DataTable( {
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
					+ full.pointSummaryId
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
			"visible": true
		}, {
			"targets" : [7],
			"render" : function(data, type, full, meta) {
				var date = new Date();
				date.setTime(data);
				return date.format('Y-m-d H:i:s');
			}
		}, {
			"targets" : [8],
			"render" : function(data, type, full, meta) {
//				var hrefEdit = $('#basePath').val() + '/page/unified/point_record_update.jsp?pointSummaryId='  + full.pointSummaryId;
//				var linkEdit = '<a target="_blank" class="" style="margin-bottom:5px;" href="' + hrefEdit
//								+ '">'
//								+ '修改' + '</a>';
//				
//				var hrefVIAdd = $('#basePath').val() + '/web/unified/pointSummaryDetail?pointSummaryId='  + full.pointSummaryId;
//				var linkVIAdd = '<a target="_blank" class="" style="margin-bottom:5px;" href="' + hrefVIAdd
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
//							+ '<li>' + linkVIAdd + '</li>'
//						+ '</ul>'
//					+ '</div>';
//					
//			      
//				return oper;

				var hrefEdit = $('#basePath').val() + '/web/unified/pointSummary/showupdate?pointSummaryId='  + full.pointSummaryId
								;
				
				var linkEdit = '<a target="_blank" data-toggle="tooltip" data-placement="top" title="修改内容"' +
						' class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="' + hrefEdit
								+ '">'
								+ '<span style="color: green;" class="glyphicon glyphicon-edit"></span>'
								/**+ '修改内容'**/ 
								+ '</a>';
					
			      
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
            { "data": "pointSummaryId" },
            { "data": "userId" },
            { "data": "pointTotal" },
            { "data": "pointUsed" },
            { "data": "pointRemainder" },
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
    
    table4PointSummary.on( 'order.dt search.dt', function () {
    	table4PointSummary.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();

    pointSummarySearch(table4PointSummary);
    $('#searchBtn4PointSummary').click(function() {
    	console.log('search click...');
    	pointSummarySearch(table4PointSummary);
    })
    
    $('#chkBoxAll4PointSummary').click(function() {
    	var chkBoxAll = $(this).attr('checked');
    	if (chkBoxAll) {
    		$('#dbTable4PointSummary').find('input[name=chkBox]').attr('checked', true);
    	} else {
    		$('#dbTable4PointSummary').find('input[name=chkBox]').attr('checked', false);
    	}
    })
    

    $('#deleteBtn4PointSummary').click(function() {
    	var ids = [];
    	var chkBox = $('#dbTable4PointSummary').find('input[name=chkBox]');
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
    		"url" : "../../mgr/pointSummary/delete",
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
            	pointSummarySearch(table4PointSummary);
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});
    })
    
    
} );

var pointSummarySearch = function(table) {
	$('#progress').dialog('open');
	$.ajax({
		"url" : "../../api/unified/pointSummary/pointSummaryByUserId",
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



 