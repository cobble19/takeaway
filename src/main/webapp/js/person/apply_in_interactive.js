$(document).ready(function() {
    var table = $('#dbTable').DataTable( {
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
		"columnDefs" : [ /*{
			"targets" : 4,
			"render" : function(data, type, full, meta) {
				if (data == null) {
					return '';
				}

				return (data != null && data === 'F') ? '女': '男';
			}
		}, */{
			"targets" : 0,
			"visible" : false
		}, /*{
			"targets" : 3,
			"render" : function(data, type, full, meta) {
				var content = (data != '') ? data.substring(0, 10): '';
				var link = '<a href="activity_detail.jsp?activityId=' + full.activityId + '">' +
						content + '/>';
				return link
			}
		}*//*, {
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
            /*{
                "className":      'details-control',
                "orderable":      false,
                "data":           null,
                "defaultContent": '',
                "title": '申请参加'
            },*/
            { "data": "userId" },
            {
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            },
            { "data": "nickname" }/*,
            { "data": "username" }*//*,
            { "data": "phone" },
            { "data": "sex" },
            { "data": "description" }*/
        ],
        "order": [[1, 'asc']]/*,
        "ajax":retrieveData*/
    } );
    
    table.on( 'order.dt search.dt', function () {
    	table.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
    
    applyInInteractiveSearch(table);
     
    // Add event listener for opening and closing details
    /*$('#dbTable tbody').on('click', 'td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = table.row( tr );
 
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
            	var activityId = $("#activityId").val();
            	
            	$.ajax({
            		"url" : "../../web/person/apply/add",
            		"type" : "POST",
            		"dataType" : 'json',
            		"data": ({
            			username: username,
            			phone: phone,
            			activityId: activityId
                    }),
                    success: function(data, textStatus, jqXHR ) {
                    	console.log("data = " + data);
                    	alert('添加成功！')
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                    	console.log('Load Error!');;
                    },
                    complete: function(jqXHR, textStatus) {
                    	console.log('Ajax complete.');
                    }
            	});	// ajax
            	//return false;
            });
        }
    } );*/
} );

/* Formatting function for row details - modify as you need */
function format ( d ) {
    // `d` is the original data object for the row
    return '<form id="applyForm" class="form-inline">' +
    		'<input type="hidden" id="activityId" name="activityId" value="' + d.activityId + '"/>' + 
    		'<div class="form-group">' + 
	    		'<label for="username">姓名: </label>' +
	    		'<input id="username" name="username" required="required" value="" placeholder="姓名" class="form-control"/>' + 
	    	'</div>' +
	    	'<div class="form-group">' + 
	    		'<label for="phone">手机号: </label>' +
	    		'<input id="phone" name="phone" type="tel" required="required" value="" placeholder="手机号" class="form-control"/>' +
	    	'</div>' + 
	    		'<input type="submit" id="applyBtn" value="申请参加" class="btn btn-default"/>' +
    	'</form>';
    /*'<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<td>Full name:</td>'+
            '<td>'+d.activityId+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Extension number:</td>'+
            '<td>'+d.title+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Extra info:</td>'+
            '<td>And any further details here (images etc)...</td>'+
        '</tr>'+
    '</table>';*/
}

// query apply by activityId
var applyInInteractiveSearch = function(table) {
	table.clear();
	var interactiveId = getParam('interactiveId');
	console.log('name=' + getParam('name'));
	$.ajax({
		"url" : "../../web/person/interactive/" + interactiveId + "/apply/winner",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
        	console.log("t= " + table.page.info().pages);
        	table.clear();
        	
        	/*if (data.data instanceof Array) {
        		data.content = data.content.substring(0, 10);
        	}*/
        	table.recordsTotal = data.recordsTotal;
        	table.rows.add(data.data).draw()
            .nodes()
            .to$()
            .addClass( 'new' );
        	
        	// add title
        	/*$("td.details-control").attr('title', '申请参加');*/
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');;
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var retrieveData = function(data, callback, settings) {
	var interactiveId = getParam('interactiveId');
	console.log('name=' + getParam('name'));
	$.ajax({
		"url" : "../../web/person/interactive/" + interactiveId + "/apply/winner",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	
        	/*if (data.data instanceof Array) {
        		data.content = data.content.substring(0, 10);
        	}*/
        	/*table.recordsTotal = data.recordsTotal;
        	table.rows.add(data.data).draw()
            .nodes()
            .to$()
            .addClass( 'new' );*/
        	callback(data);
        	
        	// add title
        	/*$("td.details-control").attr('title', '申请参加');*/
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');;
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}


 