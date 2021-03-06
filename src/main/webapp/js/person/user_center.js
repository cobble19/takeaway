$(document).ready(function() {
	console.log('abc');
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
			"targets" : 0,
			"render" : function(data, type, full, meta) {
				console.log(data + " " + type + " " + full + " " + meta);
				;
			}
		}, */{
			"targets" : 1,
			"visible" : false
		}, {
			"targets" : 2,
			"render" : function(data, type, full, meta) {
//				var content = (data != null) ? data.substring(0, 10): '';
				var link = '<a href="../../page/enterprise/activity_detail.jsp?activityId=' + full.activityId + '">' +
						data + '</a>';
				return link
			}
		}, {
			"targets" : 3,
			"render" : function(data, type, full, meta) {
				var content = (data != null) ? data.substring(0, 30): '';
				return content.replace('<', '');
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
            /*{
                "className":      'details-control',
                "orderable":      false,
                "data":           null,
                "defaultContent": '',
                "title": '申请参加'
            },*/
            {
			    /*"className":      'details-control',*/
			    "orderable":      false,
			    "data":           null,
			    "defaultContent": ''
			},
            { "data": "activityId" },
            { "data": "title" },
            { "data": "content" }
        ],
        "order": [[1, 'asc']]
    } );

    $('#progress').dialog({
    	autoOpen: false,
    	modal: true
    });
    
    table.on( 'order.dt search.dt', function () {
    	table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
    
    activitySearch(table);
    
    $('#searchBtn').click(function() {
    	console.log('search click...');
    	activitySearch(table);
    })
     
    // Add event listener for opening and closing details
    $('#dbTable tbody').on('click', 'td.details-control', function () {
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
            		/*"headers" : {
            			"Content-Type" : "application/json"
            		},*/
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
    } );
    
    // dbTable4Interactive
    var table4Interactive = $('#dbTable4Interactive').DataTable( {
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
			"targets" : 0,
			"render" : function(data, type, full, meta) {
				console.log(data + " " + type + " " + full + " " + meta);
				;
			}
		}, */{
			"targets" : 1,
			"visible" : false
		}, {
			"targets" : 2,
			"render" : function(data, type, full, meta) {
//				var content = (data != null) ? data.substring(0, 10): '';
				var link = '<a href="../../page/enterprise/interactive_detail.jsp?interactiveId=' + full.interactiveId + '">' +
						data + '</a>';
				return link
			}
		}, {
			"targets" : 3,
			"render" : function(data, type, full, meta) {
				var content = (data != null) ? data.substring(0, 30): '';
				return content.replace('<', '');
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
            /*{
                "className":      'details-control',
                "orderable":      false,
                "data":           null,
                "defaultContent": '',
                "title": '申请参加'
            },*/
            {
			    /*"className":      'details-control',*/
			    "orderable":      false,
			    "data":           null,
			    "defaultContent": ''
			},
            { "data": "interactiveId" },
            { "data": "name" },
            { "data": "content" }
        ],
        "order": [[1, 'asc']]
    } );

    $('#updateInfoBtn').click(function() {
    	var confirm = window.confirm('确定修改');
    	if (!confirm) {
    		return;
    	}
    	/*var username = $('#profile').find('#username').val();*/
    	var nickname = $('#profile').find('#nickname').val();
    	var email = $('#profile').find('#email').val();
    	var userId = $('#userId').val();
    	
    	$.ajax({
    		"url" : "../../web/user/updateInfo",
    		"type" : "GET",
    		/*"headers" : {
    			"Content-Type" : "application/json"
    		},*/
    		"dataType" : 'json',
    		traditional :true, 
    		"data": {
                /*"username": username,*/
                "nickname": nickname,
                "email": email,
                "userId": userId
            },
            success: function(data, textStatus, jqXHR ) {
            	alert('修改成功');
//            	window.location.reload();
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Load Error!');;
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});
    })
    
    $('#progress').dialog({
    	autoOpen: false,
    	modal: true
    });
    
    table4Interactive.on( 'order.dt search.dt', function () {
    	table4Interactive.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
    
    interactiveSearch(table4Interactive);
    
    $('#searchBtn4Interactive').click(function() {
    	console.log('search click...');
    	interactiveSearch(table4Interactive);
    });
    
//	=======================

    // dbTable4Prize
    var table4Prize = $('#dbTable4Prize').DataTable( {
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
			"targets" : 0,
			"render" : function(data, type, full, meta) {
				console.log(data + " " + type + " " + full + " " + meta);
				;
			}
		}, */{
			"targets" : 1,
			"visible" : false
		}, {
			"targets" : 2,
			"visible" : false
		}, {
			"targets" : 3,
			"visible" : false
		}, {
			"targets" : 4,
			"render" : function(data, type, full, meta) {
//				var content = (data != null) ? data.substring(0, 10): '';
				var link = '<a target="_blank" href="../../page/enterprise/interactive_detail.jsp?interactiveId=' + full.interactiveId + '">' +
				full.interactivePOJO.name + '</a>';
				return link
			}
		}, {
			"targets" : 5,
			"render" : function(data, type, full, meta) {
				return full.interactivePOJO.prize;
			}
		}, {
			"targets" : 7,
			"render" : function(data, type, full, meta) {
				var isVerified = data;
				var verified = "";
				if (!!isVerified) {
					verified = '已领取';
				} else {
					var prizeEndDateTime = full.interactivePOJO.prizeEndDateTime;
					var curDate = new Date();
					var prizeInvalid = "";
					if (prizeEndDateTime < curDate) {
						prizeInvalid = '已过期';
						return prizeInvalid;
					} else {
						prizeInvalid = '未过期';
						verified = '可领取';
					}
				}
				
				return verified;
			}
		}/*, {
			"targets" : 6,
			"render" : function(data, type, full, meta) {
				var prizeEndDateTime = data;
				var curDate = new Date();
				if (prizeEndDateTime < curDate) {
					return '已过期';
				}
				return '未过期';
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
            {
			    /*"className":      'details-control',*/
			    "orderable":      false,
			    "data":           null,
			    "defaultContent": ''
			},
            { "data": "interactiveApplyId" },
            { "data": "userId" },
            { "data": "interactiveId" },
            { "data": "interactivePOJO.name" },
            { "data": "interactiveId" },
            { "data": "verifyCode" },
            { "data": "isVerified" }/*,
            { "data": "prizeEndDateTime" }*/
        ],
        "order": [[1, 'asc']]
    } );

    $('#progress').dialog({
    	autoOpen: false,
    	modal: true
    });
    
    table4Prize.on( 'order.dt search.dt', function () {
    	table4Prize.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
    
    prizeSearch(table4Prize);
    
    $('#searchBtn4Prize').click(function() {
    	console.log('search click...');
    	prizeSearch(table4Prize);
    });
//  =======================
    
	$('#pwdChg').click(function() {
		var userId = $('#update_password').find('#userId').val();
		var password = $('#update_password').find('#password').val();
		var passwordOld = $('#update_password').find('#passwordOld').val();
		console.log(password + $('#password').val() + " " + $('#password').text()+ " " + $('#password').html())
    	$.ajax({
    		"url" : "../../web/user/updatePassword",
    		"type" : "GET",
    		/*"headers" : {
    			"Content-Type" : "application/json"
    		},*/
    		/*"dataType" : 'json',*/
    		"data": ({
    			userId: userId,
    			password: password,
    			passwordOld: passwordOld
            }),
            success: function(data, textStatus, jqXHR ) {
            	console.log("data = " + data);
            	if (data.success) {
                	alert('修改密码成功！')
            	} else {
                	alert('修改密码失败！ 可能是旧密码不正确！');
            	}
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	alert('加载失败!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});	// ajax
	})
     
    
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

var activitySearch = function(table) {
	$('#progress').dialog('open');
	$.ajax({
		"url" : "../../web/enterprise/activity/list/active",
		"type" : "POST",
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
        	$("td.details-control").attr('title', '申请参加');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');;
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var interactiveSearch = function(table) {
	$('#progress').dialog('open');
	$.ajax({
		"url" : "../../web/enterprise/interactive/list/active",
		"type" : "POST",
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
        	console.log('Load Error!');;
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var prizeSearch = function(table) {
	$('#progress').dialog('open');
	var userId = $('#userId').val(); 
	$.ajax({
		"url" : "../../web/person/interactive/prize/" + userId,
		"type" : "POST",
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
        	console.log('Load Error!');;
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}








 