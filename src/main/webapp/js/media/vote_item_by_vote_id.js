$(document).ready(function() {
	$('#addVoteItemBtn').click(function() {
		onAddVoteItem();
	});

})

var onAddVoteItem = function() {
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






