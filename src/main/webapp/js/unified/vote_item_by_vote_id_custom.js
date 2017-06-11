$(document).ready(function() {
	
	$('#addVoteItemBtn').click(function() {
		onAddVoteIem();
	});
	
	$('#voteBtn').click(function(event) {
		var exist = existUser4VoteItem();
		if (exist) {
			$.alert('您已经投过票了！');
			return;
		}
		
		var ids = [];
		var voteItemId = $(this).attr('voteItemId');
		ids.push(voteItemId);
		console.log('ids: ' + ids);
		if (ids == null || ids.length <= 0) {
			alert('请选择一条记录');
			return;
		}
		
		var voteId = $('#voteId').val();
	
		var confirm = window.confirm('确定投票');
		if (!confirm) {
			return;
		}
	
		$.showLoading('正在加载...');
		$.ajax({
			"url" : $('#basePath').val() + "/api/media/voteItem/addVote",
			"type" : "GET",
			"headers" : {
				"Content-Type" : "application/json"
			},
			"dataType" : 'json',
			traditional :true, 
			"data": {
	            "ids": ids,
	            voteId: voteId
	        },
	        success: function(data, textStatus, jqXHR ) {
	        	$.hideLoading();
	        	window.location.reload();
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	        	console.log('Load Error!');
	        },
	        complete: function(jqXHR, textStatus) {
	        	console.log('Ajax complete.');
	        }
		});	// end ajax
		
	});	// end voteBtn click

})


var existUser4VoteItem = function() {
		var exist = false;
		
		/*var ids = [];
		var chkBox = $('input[name=chkBox]');
		chkBox.each(function(index, ele) {
			if ($(this).attr('checked')) {
				ids.push($(this).val());
			}
		})*/
		var userId = $('#userId').val();
		var voteId = $('#voteId').val();
		
		/*if (ids == null || ids.length <= 0) {
			alert('请选择一条记录');
			return;
		}*/
    	
    	$.ajax({
    		"url" : $('#basePath').val() + "/api/media/voteItem/existUser4VoteItem",
    		"type" : "POST",
    		"async": false,
    		"dataType" : 'json',
    		"data": ({
    			voteId: voteId,
    			userId: userId
            }),
            success: function(data, textStatus, jqXHR ) {
            	if (data.success) {
            		exist = true;
            	} else {
            		exist = false;
            	}
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.log('Ajax error');
            },
            complete: function(jqXHR, textStatus) {
            	console.log('Ajax complete.');
            }
    	});	// ajax
    	return exist;
}

var onAddVoteItem = function() {
	var exist = existUser4VoteItem();
	if (exist) {
		$.alert('您已经投过票了！');
		return;
	}
	
	var ids = [];
	var chkBox = $('input[name=chkBox]');
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
	
	var voteId = $('#voteId').val();

	var confirm = window.confirm('确定投票');
	if (!confirm) {
		return;
	}

	$.showLoading('正在加载...');
	$.ajax({
		"url" : $('#basePath').val() + "/api/media/voteItem/addVote",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		traditional :true, 
		"data": {
            "ids": ids,
            voteId: voteId
        },
        success: function(data, textStatus, jqXHR ) {
        	$.hideLoading();
        	window.location.reload();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
///
///

///





