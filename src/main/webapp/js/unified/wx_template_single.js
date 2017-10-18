var table4WxTemplate;

$(document).ready(function() {
    table4WxTemplate = $('#dbTable4WxTemplate').DataTable( {
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
					+ full.wxTemplateId
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
			"targets" : [2, 3, 4, 6, 8],
			"visible": false
		}, {
			"targets": [7],
			"render" : function(data, type, full, meta) {
				var status = (data == null || data == 0) ? '未启用' : '启用';
//				var enableLink = '<a href=# onclick=enableDisplay(' + full.wxTemplateId + ')>' + status + '</a>';
				return status;
			}
		}, {
			"targets" : [9],
			"render" : function(data, type, full, meta) {
				var href = $('#basePath').val() + '/web/media/wxTemplate?wxTemplateId='  + full.wxTemplateId;
				var configLink = '<a class="btn btn-warning btn-xs" target="_blank" style="margin-right:5px;" href="' + href
				+ '">' +
				'配置' + '</a>';
				var status = (data == null || data == 0) ? '未启用' : '启用';
				var enableLink = '<a class="btn btn-success btn-xs" href=# onclick=enableDisplay(' + full.wxTemplateId + ')>' + status + '</a>';
				
				return configLink + enableLink;
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
            { "data": "wxTemplateId" },
            { "data": "wxTemplatePage" },
            { "data": "wxPage" },
            { "data": "wxTemplateName" },
            { "data": "wxTemplateDesc" },
            { "data": "relWxIndexMapPOJO.userId" },
            { "data": "relWxIndexMapPOJO.wxStaticPage" },
            {
                /*"className":      'details-control',*/
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            }
        ],
        "order": [[1, 'asc']]
    } );
    
    table4WxTemplate.on( 'order.dt search.dt', function () {
    	table4WxTemplate.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();

    wxTemplateSearch(table4WxTemplate);
    $('#searchBtn4WxTemplate').click(function() {
    	console.log('search click...');
    	wxTemplateSearch(table4WxTemplate);
    })
    
    $('#chkBoxAll4WxTemplate').click(function() {
    	var chkBoxAll = $(this).attr('checked');
    	if (chkBoxAll) {
    		$('#dbTable4WxTemplate').find('input[name=chkBox]').attr('checked', true);
    	} else {
    		$('#dbTable4WxTemplate').find('input[name=chkBox]').attr('checked', false);
    	}
    })
    

    $('#deleteBtn4WxTemplate').click(function() {
    	var ids = [];
    	var chkBox = $('#dbTable4WxTemplate').find('input[name=chkBox]');
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
    		"url" : "../../mgr/wxTemplate/delete",
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
            	wxTemplateSearch(table4WxTemplate);
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

var enableDisplay = function(wxTemplateId) {
	$('#progress').dialog('open');
	$.ajax({
		"url" : $('#basePath').val() + "/api/web/media/wxTemplate/enable",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		/*"data": JSON.stringify({
			wxTemplateId: wxTemplateId
        }),*/
		"data": {
			wxTemplateId: wxTemplateId
        },
        success: function(data, textStatus, jqXHR ) {
        	$('#progress').dialog('close');
        	
        	alert('更新成功' + wxTemplateId);
        	wxTemplateSearch(table4WxTemplate);
        	
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var wxTemplateSearch = function(table) {
	$('#progress').dialog('open');
	$.ajax({
		"url" : "../../web/media/wxTemplate/list",
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



 