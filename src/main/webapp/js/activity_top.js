$(document).ready(function() {
	activityTop();
	interactiveTop();
   /* var table = $('#dbTable').DataTable( {
		"initComplete": function () {
            var api = this.api();
            api.$('td').click( function () {
                // api.search( this.innerHTML ).draw();
            	api.ajax.reload();
            	api.draw();
            } );
			//$("#matsTeleFraudForm").appendTo($("mats-form"));
            
            console.log("page.len = " + api.page.len());
            var info = api.page.info();
            console.log('Currently showing page '+(info.page+1)+' of '+info.pages+' pages.');
            
        },
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
//		"dom" : '<"top"fl<"clear">>rt<"bottom"ip<"clear">>',
        "dom" : 'rt<"clear">',
		"lengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
		"columnDefs" : [ {
			"targets" : 0,
			"render" : function(data, type, full, meta) {
				console.log(data + " " + type + " " + full + " " + meta);
				;
			}
		}, {
			"targets" : 3,
			"render" : function(data, type, full, meta) {
				var content = (data != '') ? data.substring(0, 10): '';
				var link = '<a href="activity_detail.jsp?activityId=' + full.activityId + '">' +
						content + '/>';
				return link
			}
		}, {
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
		} ],
        "columns": [
            {
                "className":      'details-control',
                "orderable":      false,
                "data":           null,
                "defaultContent": '',
                "title": '申请参加'
            },
            { "data": "activityId" },
            { "data": "title" },
            { "data": "content" }
        ],
        "order": [[1, 'asc']]
    } );*/
    
    /*activitySearch(table);*/
     
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
            		"headers" : {
            			"Content-Type" : "application/json"
            		},
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
                    	alert('Load Error!');
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
	    		'<input id="username" name="username" placeholder="姓名" class="form-control"/>' + 
	    	'</div>' +
	    	'<div class="form-group">' + 
	    		'<label for="phone">手机号: </label>' +
	    		'<input id="phone" name="phone" placeholder="手机号" class="form-control"/>' +
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

var activitySearch = function(table) {
	$.ajax({
		"url" : "web/enterprise/activity/list",
		"type" : "POST",
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
        	
        	table.rows.add(data.data).draw()
            .nodes()
            .to$()
            .addClass( 'new' );
        	
        	// add title
        	$("td.details-control").attr('title', '申请参加');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var activityTop = function() {
	console.log('top');
	$.ajax({
		"url" : "web/enterprise/activity/list",
		"type" : "POST",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
        	console.log(" l: " + data.data.length);
        	result = data.data;
        	var top = "";
        	if (result != null) {
        		top = top + "<ul>";
        		for (var i = 0; i < result.length; i++) {
        			activityPOJO = result[i];
        			console.log('activityPOJO: ' + activityPOJO);
        			if (activityPOJO.expired) {
        				continue;
        			}
        			link = '<a href="page/enterprise/activity_detail.jsp?activityId=' + activityPOJO.activityId 
        			+ '"'
        			+ ' title="' + activityPOJO.title + '"'
        			+ '>' 
        			+ (i + 1) + ". " + "  " + activityPOJO.title.substring(0, 10) + "..." + '</a>';
        			top = top + '<li>' + link + "</li>"
        		}
        		top = top + "</ul>";
        		console.log('top: ' + top);
        		$('#activeTop .cont-7').append(top);
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var interactiveTop = function() {
	$.ajax({
		"url" : "web/enterprise/interactive/list",
		/*"type" : "POST",*/
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
        	console.log(" l: " + data.data.length);
        	result = data.data;
        	var top = "";
        	if (result != null) {
        		top = top + "<ul>";
        		for (var i = 0; i < result.length; i++) {
        			interactivePOJO = result[i];
        			console.log('interactivePOJO: ' + interactivePOJO);
        			link = '<a href="page/enterprise/interactive_detail.jsp?interactiveId=' + interactivePOJO.interactiveId 
        			+ '"'
        			+ ' title="' + interactivePOJO.name + '"'
        			+ '>' 
        			+ (i + 1) + ". " + "  " + interactivePOJO.name.substring(0, 10) + "..." + '</a>';
        			top = top + '<li>' + link + "</li>"
        		}
        		top = top + "</ul>";
        		console.log('top: ' + top);
        		$('#activeTop .cont-6').append(top);
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}



 