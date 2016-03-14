$(document).ready(function() {
	applyInActivitySearch();
	
	
    /*var table = $('#dbTable').DataTable(  );*/
    
    
    /*table.on( 'order.dt search.dt', function () {
    	table.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();*/
    
//    applyInActivitySearch(table);
     
} );

// query apply by activityId
var applyInActivitySearch = function() {
	var activityId = getParam('activityId');
	console.log('title=' + getParam('activityTitle'));
	$.ajax({
		"url" : $('#basePath').val() + "/api/activity/2/" + activityId,
		"type" : "GET",
		/*"headers" : {
			"Content-Type" : "application/json"
		},*/
		"dataType" : 'json',
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
        	buildTable(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var buildTable = function(result) {
	columns = [];
	var temp = result.columns;
	$.each(temp, function(i, e) {
		column = {
				/*"data": '\"' + e + '\"'*/
				"data": e
		};
		columns.push(column);
	})
	$.each(result.trHeaderNames, function(i, e){
		$('#trHeader').append('<th>' + e + '</th>');
	})
	
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
        "order": [[columns.length - 1, 'asc']]
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
	return ;
}


 