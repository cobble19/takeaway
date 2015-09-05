$(document).ready(function() {
	console.log('abc');
    var table = $('#dbTable').DataTable( {
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
					+ full.activityId
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
				if (data != null && data) {
					expired = true;
				}
				return expired ? '<font style="color: red;">过期</font>' : '未过期';
			}
		},  {
			"targets" : 8,
			"visible": false,
			"render" : function(data, type, full, meta) {
				var content = (data != null) ? data.substring(0, 10): '';
				content += '...>>';
				var link = '<a href="../../page/enterprise/activity_detail.jsp?activityId=' + full.activityId + '">' +
						content + '</a>';
				return link;
			}
		}, {
			"targets" : 9,
			"render" : function(data, type, full, meta) {
				var href = '../../page/person/apply_in_activity.jsp?activityId='  + full.activityId
				+ '&activityTitle=' + ((full.title));
				var linkApply = '<a class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="' + href
				+ '">' +
				'查看申请人' + '</a>';
				
				var hrefEdit = $('#basePath').val() + '/page/enterprise/activity_update.jsp?activityId='  + full.activityId;
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
            { "data": "activityId" },
            { "data": "title" },
            { "data": "startDateTime" },
            { "data": "endDateTime" },
            {
                /*"className":      'details-control',*/
                "data":           null,
                "defaultContent": ''
            },
            { "data": "expired" },
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
    
    table.on( 'order.dt search.dt', function () {
    	table.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
    
    $('#chkBoxAll').click(function() {
    	var chkBoxAll = $(this).attr('checked');
    	if (chkBoxAll) {
    		$('#dbTable').find('input[name=chkBox]').attr('checked', true);
    	} else {
    		$('#dbTable').find('input[name=chkBox]').attr('checked', false);
    	}
    })
    
    $('#chkBoxAll4Interactive').click(function() {
    	var chkBoxAll = $(this).attr('checked');
    	if (chkBoxAll) {
    		$('#dbTable4Interactive').find('input[name=chkBox]').attr('checked', true);
    	} else {
    		$('#dbTable4Interactive').find('input[name=chkBox]').attr('checked', false);
    	}
    })
    
    $('#deleteBtn').click(function() {
    	var ids = [];
    	var chkBox = $('#dbTable').find('input[name=chkBox]');
    	
    	chkBox.each(function(index, ele) {
//    		console.log($(this).val() + ele.value);
        	/*var tr = $(this).closest('tr');
            var row = table.row( tr );*/
    		if ($(this).attr('checked')) {
//    			ids.push(row.data().activityId);
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
    		"url" : "../../mgr/activity/delete",
    		"type" : "GET",
    		/*"headers" : {
    			"Content-Type" : "application/json"
    		},*/
    		"dataType" : 'json',
    		traditional :true, 
    		"data": {
                "ids": ids
            },
            success: function(data, textStatus, jqXHR ) {
            	$('#progress').dialog('close');
            	activitySearch(table);
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	alert('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});
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
    		/*"headers" : {
    			"Content-Type" : "application/json"
    		},*/
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
            	alert('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});
    })
    
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
            	alert('Load Error!');
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
    
    /*$('#pwdDiv').dialog({
    	autoOpen: false,
    	modal: true
    })*/
    
    activitySearch(table);
    
    $('#searchBtn').click(function() {
    	console.log('search click...');
    	activitySearch(table);
    })
     
    // Add event listener for opening and closing details
    $('#activityList tbody').on('click', 'td.details-control', function () {
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
                    	alert('Load Error!');
                    },
                    complete: function(jqXHR, textStatus) {
                    	console.log('Ajax complete.');
                    }
            	});	// ajax
            	//return false;
            });
        }
    } );
    
    
    $('#sidebar a').click(function() {
		$('#sidebar a').each(function() {
			$(this).removeClass('active');
		})
		$this = $(this);
		$this.addClass('active');
		var contentId = $this.attr('href').substring(1);
		console.log('contentId: ' + contentId);
		$('#uc_content > div').each(function(i, e) {
			$(this).hide();
		})
		$('#' + contentId).show();
	});
	
	$('#sidebar a[href=#profile]').trigger('click');
	
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
	
	/*$('#pwdChg4OpenDialog').click(function() {
		$('#pwdDiv').dialog('open');
		$('#pwdChg').click(function() {
			var userId = $('#pwdDiv').find('#userId').val();
			var password = $('#pwdDiv').find('#password').val();
			console.log(password + $('#password').val() + " " + $('#password').text()+ " " + $('#password').html())
        	$.ajax({
        		"url" : "../../web/user/updatePassword",
        		"type" : "GET",
        		"headers" : {
        			"Content-Type" : "application/json"
        		},
        		"dataType" : 'json',
        		"data": ({
        			userId: userId,
        			password: password
                }),
                success: function(data, textStatus, jqXHR ) {
                	console.log("data = " + data);
                	$('#pwdDiv').dialog('close');
                	alert('修改密码成功！')
                },
                error: function(jqXHR, textStatus, errorThrown) {
                	$('#pwdDiv').dialog('close');
                	alert('加载失败!');
                },
                complete: function(jqXHR, textStatus) {
                	console.log('Ajax complete.');
                	$('#pwdDiv').dialog('close');
                }
        	});	// ajax
		})
	});*/
	
	// interactive 互动活动
    
    $('#searchBtn4Interactive').click(function() {
    	console.log('search click...');
    	interactiveSearch(table4Interactive);
    })
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
			"targets": [6]/*,
			"render" : function(data, type, full, meta) {
				var startDateTime = new Date(full.startDateTime);
				var endDateTime = new Date(full.endDateTime);
				return startDateTime.format('Y-m-d') + "/" + endDateTime.format('Y-m-d');
			}*/
		}, {
			"targets": [7]/*,
			"render" : function(data, type, full, meta) {
				var expired = false;
				if (data != null && data) {
					expired = true;
				}
				return expired ? '<font style="color: red;">过期</font>' : '未过期';
			}*/
		},  {
			"targets" : 8,
			"visible": false,
			"render" : function(data, type, full, meta) {
				var content = (data != null) ? data.substring(0, 10): '';
				content += '...>>';
				var link = '<a href="../../page/enterprise/interactive_detail.jsp?interactiveId=' + full.interactiveId + '">' +
						content + '</a>';
				return link;
			}
		}, {
			"targets" : 9,
			"render" : function(data, type, full, meta) {
				/*var href = '../../page/person/apply_in_activity.jsp?activityId='  + full.interactiveId
				+ '&activityTitle=' + ((full.name));
				var linkApply = '<a class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="' + href
				+ '">' +
				'查看申请人' + '</a>';*/
				
				var hrefEdit = $('#basePath').val() + '/page/enterprise/interactive_update.jsp?interactiveId='  + full.interactiveId;
				var linkEdit = '<a class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="' + hrefEdit
								+ '">'
								+ '修改' + '</a>';
				return linkEdit;
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
    
    $('#chkBoxAll4Interactive').click(function() {
    	var chkBoxAll = $(this).attr('checked');
    	if (chkBoxAll) {
    		$(this).find('input[name=chkBox]').attr('checked', true);
    	} else {
    		$(this).find('input[name=chkBox]').attr('checked', false);
    	}
    })
    
    $('#deleteBtn4Interactive').click(function() {
    	var ids = [];
    	var chkBox = $('#dbTable4Interactive').find('input[name=chkBox4Interactive]');
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
    		/*"headers" : {
    			"Content-Type" : "application/json"
    		},*/
    		"dataType" : 'json',
    		traditional :true, 
    		"data": {
                "ids": ids
            },
            success: function(data, textStatus, jqXHR ) {
            	$('#progress').dialog('close');
            	activitySearch(table4Interactive);
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	alert('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});
    })
    
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
	$('#progress').dialog('open');
	$.ajax({
		"url" : "../../web/enterprise/activityByUserId",
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
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var interactiveSearch = function(table) {
	$('#progress').dialog('open');
	$.ajax({
		"url" : "../../web/enterprise/interactive",
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
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}



 