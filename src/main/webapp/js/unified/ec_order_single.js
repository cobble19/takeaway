var table4EcOrder;

$(document).ready(function() {
    table4EcOrder = $('#dbTable4EcOrder').DataTable( {
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
					+ full.ecOrderId
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
			"targets" : [9, 10],
			"render" : function(data, type, full, meta) {
				var date = new Date();
				date.setTime(data);
				return date.format('Y-m-d H:i:s');
			}
		}, {
			"targets" : [11],
			"render" : function(data, type, full, meta) {
				var hrefEdit = $('#basePath').val() + '/web/unified/ecOrder/showupdate?orderId='  + full.orderId;
				var linkEdit = '<a class="" style="margin-bottom:5px;" target="_blank" href="' + hrefEdit
								+ '">'
								+ '修改' + '</a>';
				
				var hrefVIAdd = $('#basePath').val() + '/web/unified/ecOrderDetail?orderId='  + full.orderId;
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
					
			      
				return oper;
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
            { "data": "orderId" },
            { "data": "userId" },
            { "data": "openId" },
            { "data": "productId" },
            { "data": "unitPrice" },
            { "data": "quantity" },
            { "data": "discountRate" },
            { "data": "createDateTime" },
            { "data": "lastModifiedDateTime" },
            {
                /*"className":      'details-control',*/
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            }
        ],
        "order": [[1, 'asc']]
    } );
    
    table4EcOrder.on( 'order.dt search.dt', function () {
    	table4EcOrder.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();

    ecOrderSearch(table4EcOrder);
    $('#searchBtn4EcOrder').click(function() {
    	console.log('search click...');
    	ecOrderSearch(table4EcOrder);
    })
    
    $('#chkBoxAll4EcOrder').click(function() {
    	var chkBoxAll = $(this).attr('checked');
    	if (chkBoxAll) {
    		$('#dbTable4EcOrder').find('input[name=chkBox]').attr('checked', true);
    	} else {
    		$('#dbTable4EcOrder').find('input[name=chkBox]').attr('checked', false);
    	}
    })
    

    $('#deleteBtn4EcOrder').click(function() {
    	var ids = [];
    	var chkBox = $('#dbTable4EcOrder').find('input[name=chkBox]');
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
    		"url" : "../../mgr/ecOrder/delete",
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
            	ecOrderSearch(table4EcOrder);
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


var ecOrderSearch = function(table) {
	$('#progress').dialog('open');
	$.ajax({
		"url" : "../../api/unified/ecOrder/ecOrderByUserId",
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



 