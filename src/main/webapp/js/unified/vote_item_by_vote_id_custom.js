$(document).ready(function() {
	
	$('#addVoteItemBtn').click(function() {
		var voteId = $('#voteId').val();
		var ids = [];
		var chkBox = $('input[name=chkBox]');
		chkBox.each(function(index, ele) {
			if ($(this).attr('checked')) {
				ids.push($(this).val());
			}
		});
		
		
		var exist = existUser4VoteItem(voteId, null, userId);
		if (exist) {
			$.alert('您已经投过票了！');
			return;
		}
	
		onAddVoteIem(voteId, ids);
	});
	
	$('#voteBtn').click(function(event) {
		var userId = $('#userId').val();
		var voteId = $('#voteId').val();
		var voteItemId = $(this).attr('voteItemId');
		
		var exist = existUser4VoteItem(voteId, voteItemId, userId);
		if (exist) {
			$.alert('您已经投过票了！');
			return;
		}
		onAddVoteIemX(voteId, voteItemId, userId);
		
	});	// end voteBtn click

})


var existUser4VoteItem = function(voteId, voteItemId, userId) {
		var exist = false;
		
		/*var ids = [];
		var chkBox = $('input[name=chkBox]');
		chkBox.each(function(index, ele) {
			if ($(this).attr('checked')) {
				ids.push($(this).val());
			}
		})*/
		
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
    			voteItemId: voteItemId,
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

var addVoteItem = function(voteId, voteItemIds) {
	var ids = [];
	voteItemIds.each(function(index, ele) {
		ids.push($(this).val());
	});
	console.log('ids: ' + ids);
	if (ids == null || ids.length <= 0) {
		alert('请选择一条记录');
		return;
	}
	
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
var addVoteItemX = function(voteId, voteItemId, userId, $ele) {
		var ids = [];
		ids.push(voteItemId);
		console.log('ids: ' + ids);
		if (ids == null || ids.length <= 0) {
			alert('请选择一条记录');
			return;
		}
		
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
	            voteId: voteId,
	            userId: userId
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
}
///





