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
				var link = '<a href="' + $('#basePath').val() + '/page/enterprise/activity_detail.jsp?activityId=' + full.activityId + '">' +
						content + '</a>';
				return link;
			}
		},  {
			"targets" : 9,
			"render" : function(data, type, full, meta) {
				var ret = '未知';
				if (data == null) {
					ret = '未知';
				} else if (data == 1) {
					ret = '报名申请';
				} else if (data == 2) {
					ret = '征集调查';
				}
				return ret;
			}
		}, {
			"targets" : 10,
			"render" : function(data, type, full, meta) {
				var href = $('#basePath').val() + '/page/person/apply_in_activity.jsp?activityId='  + full.activityId
				+ '&activityTitle=' + ((full.title));
				var linkApply = '<a class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="' + href
				+ '">' +
				'查看报名详情' + '</a>';

				var href2 = $('#basePath').val() + '/page/person/apply2_in_activity.jsp?activityId='  + full.activityId
				+ '&activityTitle=' + ((full.title));
				var linkApply2 = '<a class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="' + href2
				+ '">' +
				'表单详情' + '</a>';
				
				var hrefEdit = $('#basePath').val() + '/page/media/activity_update.jsp?activityId='  + full.activityId;
				var linkEdit = '<a class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="' + hrefEdit
								+ '">'
								+ '修改内容' + '</a>';

				var url = $('#basePath').val() + '/page/enterprise/activity_detail.jsp?activityId=' + full.activityId + "&hidContent=1&a=1";
				var urlCopy = '<a class="btn btn-warning btn-xs" style="margin-bottom:5px;" href="#" onClick="openUrlDiv(\'' + url + '\')">'
				+ '相关链接' + '</a>';
				var picBtn = '<a class="btn btn-warning btn-xs picBtn" style="margin-bottom:5px;" href="#" onclick="openPicDiv(this)">'
				+ '活动简图' + '</a>';
				
				var apply2AttrModelBtn = '<a class="btn btn-warning btn-xs picBtn" style="margin-bottom:5px;" href="#" onclick="openApply2AttrModelDiv(this)">'
					+ '创建表单' + '</a>';
				
				return /*linkApply + " " + */linkApply2 + " " + linkEdit + " " + urlCopy + " " + picBtn + " " + apply2AttrModelBtn;
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
            { "data": "typeCode" },
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
    
    /*$('a.picBtn').click(function(e) {
    	e.preventDefault();
    	console.log($(this));
    	data = table.row($(this).parent('tr')).data();
    	console.log(data);
    });*/
    

	$('#picForm').validate();
	
	$('#picForm').find('#uploadBtn').click(function(e) {
		$('#progress').dialog('open');
		var form1 = $(this).parents('form');
		var formData;
	    formData = new FormData();

	    var userId = $('#userId').val();
		
		formData.append('pic', form1.find('input[name=pic]').get(0).files[0]);
	    formData.append('userId', userId);
	    formData.append('wxTemplateId', $('#activityId').val());
	    formData.append('orderNo', 9999);

	    $.ajax({
	        url: '../../htmleditor/pic/add',
	        contentType:"multipart/form-data",
	        data: formData,
	        processData: false,
	        type: 'POST',
	        contentType: false, // tell jQuery not to set contentType
	        success : function(data) {
	    		/*$('#progress').dialog('close');
	            alert('上传成功');*/
	            form1.find('#logoImg').val(data.file_url);
	            
	            addOrUpdatePic();
	        }
	    });	// ajax end
	    
	});	// upload pic to picture files
	
	/*$('#picForm').find('#addBtn').click(function(e) {
		$('#progress').dialog('open');
		var params = {
				userId: $('#userId').val(),
				logoImg: $('#logoImg').val(),
				activityId: $('#activityId').val()
		};
		$.ajax({
			"url" : $('#basePath').val() + "/api/enterprise/activity/addOrUpdate",
			"type" : "POST",
			"headers" : {
				"Content-Type" : "application/json"
			},
			"dataType" : 'json',
			"data": JSON.stringify({
	            title: $("#title").val()
	        }),
			data: params,
	        success: function(data, textStatus, jqXHR ) {
	    		$('#progress').dialog('close');
	    		alert('更新图片成功');
	        	$('#picDiv').dialog('close');
	        	console.log("data = " + data);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	        	alert('Load Error!');
	        },
	        complete: function(jqXHR, textStatus) {
	        	console.log('Ajax complete.');
	        }
		});
	});*/
    
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
    
    $('#activityDetailDiv').dialog({
    	autoOpen: false,
    	modal: true,
    	width: 450,
    	height: 150
    });
    
    $('#picDiv').dialog({
    	autoOpen: false,
    	modal: true,
    	width: 450,
    	height: 350
    });
    $('#apply2AttrModelDiv').dialog({
    	autoOpen: false,
    	modal: true,
    	width: 450,
    	height: 350
    });
    
    onClickAddAttr();
    onClickAttrSummit();
    onClickPopAttr();

    var clipboard = new Clipboard('#copyBtn');

    clipboard.on('success', function(e) {
    	try {
            console.info('Action:', e.action);
            console.info('Text:', e.text);
            console.info('Trigger:', e.trigger);
    	} catch (ex) {
    		console.log("ex: " + ex);
    	}

//        e.clearSelection();
        alert('复制成功！');
    });

    clipboard.on('error', function(e) {
    	try {
            console.error('Action:', e.action);
            console.error('Trigger:', e.trigger);
    	} catch (ex) {
    		console.log("ex: " + ex);
    	}
        alert('复制失败！ 请用【Ctrl+C】， 同时按下Ctrl和C');
    });
    

    $('#verifyDiv').dialog({
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
				var link = '<a href="../../page/enterprise/interactive_detail.jsp?interactiveId=' + full.interactiveId + '">' +
						content + '</a>';
				return link;
			}
		}, {
			"targets" : 11,
			"render" : function(data, type, full, meta) {
				var href = $('#basePath').val() + '/page/person/apply_in_interactive.jsp?interactiveId='  + full.interactiveId
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

				var hrefEdit = $('#basePath').val() + '/page/media/interactive_update.jsp?interactiveId='  + full.interactiveId;
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
    		$(this).find('input[name=chkBox]').attr('checked', true);
    	} else {
    		$(this).find('input[name=chkBox]').attr('checked', false);
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
            	alert('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});
    })
    
    
    /*$('#verifyBtn').click(function() {

    	var confirm = window.confirm('执行验证');
    	if (!confirm) {
    		return;
    	}
    	var interactiveId = $('#verifyDiv').find('#interactiveId').val();
    	var verifyCode = $('#verifyDiv').find('#verifyCode').val();
    	$.ajax({
    		"url" : "../../web/person/interactiveApply/verify",
    		"type" : "GET",
    		"headers" : {
    			"Content-Type" : "application/json"
    		},
    		"dataType" : 'json',
    		traditional :true, 
    		"data": {
                "interactiveId": interactiveId,
                "verifyCode": verifyCode
            },
            success: function(data, textStatus, jqXHR ) {
            	$('#verifyDiv').dialog('close');
            	alert(data.desc);
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	alert('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});
    })*/
        
    
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
	var params = {
            typeCode: $("#typeCode option:selected").val()
	}
	$.ajax({
		"url" : "../../web/enterprise/activityByUserId",
		"type" : "POST",
		/*"headers" : {
			"Content-Type" : "application/json"
		},*/
		/*contentType:"application/x-www-form-urlencoded",*/
		"dataType" : 'json',
		"data": (params),
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
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}

var openUrlDiv = function(url) {
	$('#activityDetailDiv').dialog('open');
	
	$('#activityDetailUrl').val(url);
	
}

var openPicDiv = function(t) {
	data = $('#dbTable').DataTable().row($(t).closest('tr')).data();
	console.log(data);
	$('#activityId').val(data.activityId);
	$('#logoImg').val(data.logoImg);
	$('#picDiv').dialog('open');
}
var openApply2AttrModelDiv = function(t) {
	data = $('#dbTable').DataTable().row($(t).closest('tr')).data();
	console.log(data);
	$('#apply2AttrModelForm').find('#activityId').val(data.activityId);
	$('#apply2AttrModelDiv').dialog('open');
}

function jsCopy(){ 
    var e=document.getElementById("activityDetailUrl");//对象是content 
    e.select(); //选择对象 
    document.execCommand("Copy"); //执行浏览器复制命令
    alert("已复制好，可贴粘。"); 
}

function addOrUpdatePic() {
	// update DB
    var params = {
			userId: $('#userId').val(),
			logoImg: $('#logoImg').val(),
			activityId: $('#activityId').val()
	};
	$.ajax({
		"url" : $('#basePath').val() + "/api/enterprise/activity/addOrUpdate",
		"type" : "POST",
		/*"headers" : {
			"Content-Type" : "application/json"
		},*/
		"dataType" : 'json',
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
		data: params,
        success: function(data, textStatus, jqXHR ) {
    		$('#progress').dialog('close');
    		alert('更新图片成功');
        	$('#picDiv').dialog('close');
        	console.log("data = " + data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
var onClickPopAttr = function() {
	$('#popAttrBtn').click(function() {
		var form = $('#apply2AttrModelForm');
		var formGroup = $('#apply2AttrModelForm div.form-group .control-label');
		var length = formGroup.length;
		if (length <= 2) {
			alert('必须保留2个条目！');
			return;
		} else {
			$('#apply2AttrModelForm div.form-group:nth-last-child(2)').remove();
		}
	});
}
var onClickAddAttr = function() {
	$('#addAttrBtn').click(function() {
		var lables = $('#apply2AttrModelForm .control-label');
		var i = lables.length;
		var inputText = '<div class="form-group dynamic-attr">'
						+ '<label class="control-label col-sm-4" for="' + 'attr' + i + '">' + '条目' + (i + 1) + ':' + '</label>'
						+ '<div class="col-sm-8">'
						+  '<input type="text" class="form-control attr" required="required" id="' + 'attr' + i + '" name="' + 'attr' + i + '" placeholder="请输入' + '条目' + (i + 1) + '标题' + '">'
						+  '<input type="text" class="form-control remark" id="' + 'remark' + i + '" name="' + 'remark' + i + '" placeholder="请输入' + '条目' + (i + 1) + '备注' + '">'
						+ '</div>'
						+'</div>'
			;
		$('#apply2AttrModelForm div.form-group:nth-last-child(1)').before(inputText);
		var form = $('#apply2AttrModelForm'); // trigger
	});
	/*$('#addPicBtn').click(function() {
		var lables = $('#apply2AttrModelForm .control-label');
		var i = lables.length;
		var inputText = '<div class="form-group">'
						+ '<label class="control-label col-sm-4" for="' + 'attr' + i + '">' + '条目图片、文件' + i + ':' + '</label>'
						+ '<div class="col-sm-8">'
						+  '<input type="text" class="form-control" id="' + 'attr' + i + '" name="' + 'attr' + i + '" readonly="readonly" placeholder="请输入' + '条目' + i + '内容' + '">'
						+  '<input class="" id="pic" name="pic" type="file">'
						+  '<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传">'
						+ '</div>'
						+'</div>'
			;
		$('#apply2AttrModelForm div.form-group:nth-last-child(2)').after(inputText);
		var form = $('#apply2AttrModelForm'); // trigger
	})*/
}

var onClickAttrSummit = function() {
		var form = $("form[id=apply2AttrModelForm]");
		
		$(form).find('#addBtn').click(function(e) {
			$('#apply2AttrModelDiv').dialog('open');
			var form1 = $(this).parents('form');
			
			if (!$(form1).valid()) {
//				alert('请正确输入');
				return;
			}
			
			var formData;
		    formData = new FormData();
		    
		    
		    inputTexts = $('#apply2AttrModelForm div input.form-control.attr:text');
		    var apply2AttrModelPOJOs = [];
		    /*var count = 0;*/
		    
		    var userId = $('#userId').val();
		    var activityId = $(form1).find('#activityId').val();
		    
		    console.log('inputTexts length: ' + inputTexts.length);

		    for (var i = 0; i < inputTexts.length; i++) {
		    	var inputText = inputTexts[i];
		    	var apply2AttrModelPOJO = {};
		    	apply2AttrModelPOJO.apply2AttrModelName = $(inputText).val();
		    	apply2AttrModelPOJO.apply2AttrModelRemark = $(inputText).next().val();
		    	apply2AttrModelPOJO.orderNo = i;
		    	apply2AttrModelPOJO.activityId = activityId;
		    	apply2AttrModelPOJOs.push(apply2AttrModelPOJO);
		    }

		    /*var count = count + inputTexts.length;*/
		    
		   /* inputRadios = $('#apply2AttrModelForm div.form-group.static-attr-radio input[type=radio]:checked');
		    console.log('inputRadios length: ' + inputRadios.length);
		    for (var i = 0; i < inputRadios.length; i++) {
		    	var index = i + count;
		    	var inputRadio = inputRadios[i];
		    	var apply2AttrModelPOJO = {};
		    	apply2AttrModelPOJO.apply2AttrModelName = $(inputRadio).val();
		    	apply2AttrModelPOJO.orderNo = index;
		    	apply2AttrModelPOJO.activityId = activityId;
		    	apply2AttrModelPOJOs.push(apply2AttrModelPOJO);
		    }*/

		    /*count = count + inputRadios.length;
		    
		    inputTexts = $('#apply2AttrModelForm div.dynamic-attr input:text');

		    console.log('inputTexts dynamic length: ' + inputTexts.length);
		    console.log('inputTexts: ' + JSON.stringify(inputTexts));
		    
		    for (var i = 0; i < inputTexts.length; i++) {
		    	var index = i + count;
		    	var inputText = inputTexts[i];
		    	var apply2AttrModelPOJO = {};
		    	apply2AttrModelPOJO.apply2AttrModelName = $(inputText).val();
		    	apply2AttrModelPOJO.orderNo = index;
		    	apply2AttrModelPOJO.activityId = activityId;
		    	apply2AttrModelPOJOs.push(apply2AttrModelPOJO);
		    }*/
		    
		    var basePath = $("#basePath").val();
		    /*if (imgSrc.indexOf(basePath) > -1) {
		    	imgSrc = imgSrc.substring(imgSrc.indexOf(basePath));
		    }*/
		    
		    /*var wxTemplateId = $('input[name=wxTemplateId]:checked').val()
		    console.log("wxTemplateId: " + wxTemplateId);
		    formData.append('wxTemplateId', wxTemplateId);
		    var userId = $('#userId').val();
		    formData.append('userId', userId);*/
		    formData.append('apply2AttrModelPOJOs', apply2AttrModelPOJOs);
		    
		    console.log("apply2AttrModelPOJOs: " + JSON.stringify(apply2AttrModelPOJOs));
		    
		    $.ajax({
		        url: $("#basePath").val() + '/api/media/apply2AttrModels/add',
		        data: JSON.stringify(apply2AttrModelPOJOs),
//		        processData: false,
		        type: 'POST',
		        contentType: "application/json",
		        success : function(data) {
		        	if (data.success) {
			            alert('创建申请人信息字段成功');
		        	} else {
			            alert('创建申请人信息字段失败！！！msg：' + data.desc);
		        	}

		    		$('#apply2AttrModelDiv').dialog('close');
		        }
		    });	// ajax end
		    
		})
		
}

/*var verify = function(interactiveId) {
	console.log('111');
	$("#interactiveId").val(interactiveId);
	$('#verifyDiv').dialog('open');
}*/

 
