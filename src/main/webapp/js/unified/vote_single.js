var table4Vote;

$(document).ready(function() {
    table4Vote = $('#dbTable4Vote').DataTable( {
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
					+ full.voteId
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
			"targets" : [6],
			"visible": false
		}, {
			"targets": [8],
			"render" : function(data, type, full, meta) {
				var status = (data == null || data == 0) ? '未发布' : '已发布';
				return status;
			}
		}, {
			"targets": [9,10,11],
			"visible": true,
			"render" : function(data, type, full, meta) {
				var createDateTime = new Date(data);
				return createDateTime.format('Y-m-d H:i:s');
			}
		}, {
			"targets" : [14],
			"render" : function(data, type, full, meta) {
				var hrefEdit = $('#basePath').val() + '/page/unified/vote_update.jsp?voteId='  + full.voteId;
				var linkEdit = '<a class="" style="margin-bottom:5px;" target="_blank" href="' + hrefEdit
								+ '">'
								+ '修改' + '</a>';
				
				var hrefVIAdd = $('#basePath').val() + '/web/unified/voteDetail?voteId='  + full.voteId;
				var linkVIAdd = '<a class="" style="margin-bottom:5px;" target="_blank" href="' + hrefVIAdd
								+ '">'
								+ '追加投票项' + '</a>';
								
				var hrefVote = $('#basePath').val() + '/web/unified/vote/loadmore/query/'  + full.voteId + '?activityId=' + full.activityId
								 + '&start=0&limit=10&paginationFlage=true';
				var linkVote = '<a class="" style="margin-bottom:5px;" target="_blank" href="' + hrefVote
								+ '">'
								+ '投票' + '</a>';
				
				var publishType = (full.publishType == null || full.publishType == 0) ? '未发布' : '已发布';
				var linkPublish = '<a class="btn btn-success btn-xs" href=# onclick=changePublish(' + full.voteId + ',' + full.publishType + ')>' + publishType + '</a>';
				
				var oper =
					'<div class="btn-group" role="group">'
						+ '<button type="button" class="btn btn-warning btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
							+ '操作选项'
								+ '<span class="caret"></span>'
						+ '</button>'
						+ '<ul class="dropdown-menu dropdown-menu-xs">'
							+ '<li>' + linkEdit + '</li>'
							+ '<li>' + linkVIAdd + '</li>'
							+ '<li>' + linkVote + '</li>'
						+ '</ul>'
					+ '</div>';
					
			      
				return oper + linkPublish;
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
            { "data": "voteId" },
            { "data": "userId" },
            { "data": "title" },
            { "data": "logoImg" },
            { "data": "content" },
            { "data": "voteType" },
            { "data": "publishType" },
            { "data": "createDateTime" },
            { "data": "startDateTime" },
            { "data": "endDateTime" },
            { "data": "activityId" },
            { "data": "apply2AttrModelIds" },
            {
                /*"className":      'details-control',*/
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            }
        ],
        "order": [[1, 'asc']]
    } );
    
    table4Vote.on( 'order.dt search.dt', function () {
    	table4Vote.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();

    voteSearch(table4Vote);
    $('#searchBtn4Vote').click(function() {
    	console.log('search click...');
    	voteSearch(table4Vote);
    })
    
    $('#chkBoxAll4Vote').click(function() {
    	var chkBoxAll = $(this).attr('checked');
    	if (chkBoxAll) {
    		$('#dbTable4Vote').find('input[name=chkBox]').attr('checked', true);
    	} else {
    		$('#dbTable4Vote').find('input[name=chkBox]').attr('checked', false);
    	}
    })
    

    $('#deleteBtn4Vote').click(function() {
    	var ids = [];
    	var chkBox = $('#dbTable4Vote').find('input[name=chkBox]');
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
    		"url" : $('#basePath').val() + "/mgr/vote/delete",
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
            	voteSearch(table4Vote);
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

var changePublish = function(voteId, publishType) {
	// 取反
	if (publishType == null || publishType == 0) {
		publishType = 1;
	} else {
		publishType = 0;
	}
	
	$('#progress').dialog('open');
	$.ajax({
		"url" : $('#basePath').val() + "/api/unified/vote/publishType",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		/*"data": JSON.stringify({
			wxTemplateId: wxTemplateId
        }),*/
		"data": {
			voteId: voteId,
			publishType: publishType
        },
        success: function(data, textStatus, jqXHR ) {
        	$('#progress').dialog('close');
        	
        	alert('更新成功' + voteId);
        	voteSearch(table4Vote);
        	
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var voteSearch = function(table) {
	$('#progress').dialog('open');
	$.ajax({
		"url" : $('#basePath').val() + "/api/unified/vote/voteByUserId",
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



 