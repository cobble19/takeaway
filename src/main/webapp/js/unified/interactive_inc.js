
$(document).ready(function() {
    

    var table4Interactive = $('#dbTable4Interactive').DataTable( {
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
					+ full.interactiveId
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
//				var startDateTime = new Date(full.startDateTime);
				var endDateTime = new Date(full.endDateTime);
				var curDate = new Date();
				if (endDateTime < curDate) {
					expired = true;
				}
				return expired ? '<font style="color: red;">过期</font>' : '未过期';
			}
		}, {
			"targets": [8]/*,
			"render" : function(data, type, full, meta) {
				var startDateTime = new Date(full.startDateTime);
				var endDateTime = new Date(full.endDateTime);
				return startDateTime.format('Y-m-d') + "/" + endDateTime.format('Y-m-d');
			}*/
		}, {
			"targets": [9]/*,
			"render" : function(data, type, full, meta) {
				var expired = false;
				if (data != null && data) {
					expired = true;
				}
				return expired ? '<font style="color: red;">过期</font>' : '未过期';
			}*/
		},  {
			"targets" : 10,
			"visible": false,
			"render" : function(data, type, full, meta) {
				var content = (data != null) ? data.substring(0, 10): '';
				content += '...>>';
				var link = '<a href="../../page/unified/interactive_detail.jsp?interactiveId=' + full.interactiveId + '">' +
						content + '</a>';
				return link;
			}
		}, {
			"targets" : 11,
			"render" : function(data, type, full, meta) {
				var href = $('#basePath').val() + '/page/unified/apply_in_interactive.jsp?interactiveId='  + full.interactiveId
				+ '&name=' + ((full.name));
				var linkApply = '<a class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="' + href
				+ '">' +
				'查看获奖人' + '</a>';
				
				/*var hrefEdit = $('#basePath').val() + '/page/enterprise/interactive_update.jsp?interactiveId='  + full.interactiveId;
				var linkEdit = '<a class="btn btn-warning btn-xs" onclick="verify('
								+ full.interactiveId
					       		+ ');" style="margin-bottom:5px;" href="' + '#'
								+ '">'
								+ '验证获奖人' + '</a>';*/

				var hrefEdit = $('#basePath').val() + '/page/unified/interactive_update.jsp?interactiveId='  + full.interactiveId;
				var linkEdit = '<a class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="' + hrefEdit
								+ '">'
								+ '修改' + '</a>';
				
				return linkApply + "<br/>" + linkEdit;
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
            { "data": "interactiveId" },
            { "data": "name" },
            { "data": "startDateTime" },
            { "data": "endDateTime" },
            /*{
                "className":      'details-control',
                "data":           null,
                "defaultContent": ''
            },*/
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
            { "data": "prize" },
            { "data": "answer" },
            { "data": "content" },
            {
                /*"className":      'details-control',*/
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            }
        ],
        "order": [[1, 'asc']]
    } );
    
    table4Interactive.on( 'order.dt search.dt', function () {
    	table4Interactive.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();

    interactiveSearch(table4Interactive);
    $('#searchBtn4Interactive').click(function() {
    	console.log('search click...');
    	interactiveSearch(table4Interactive);
    })
    
    $('#chkBoxAll4Interactive').click(function() {
    	var chkBoxAll = $(this).attr('checked');
    	if (chkBoxAll) {
    		$('#dbTable4Interactive').find('input[name=chkBox]').attr('checked', true);
    	} else {
    		$('#dbTable4Interactive').find('input[name=chkBox]').attr('checked', false);
    	}
    })
    
    $('#deleteBtn4Interactive').click(function() {
    	var ids = [];
    	var chkBox = $('#dbTable4Interactive').find('input[name=chkBox]');
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
    		"url" : "../../mgr/interactive/delete",
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
            	interactiveSearch(table4Interactive);
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

var interactiveSearch = function(table) {
	$('#progress').dialog('open');
	$.ajax({
		"url" : "../../web/media/interactive",
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




