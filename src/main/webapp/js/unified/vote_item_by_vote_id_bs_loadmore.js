
var loading = false;  //状态标记

$(document).ready(function() {
//	$('#home').infinite(500);
//	var loading = false;  //状态标记
	$(document.body).infinite().on("infinite", function() {
//	  console.log('22222');
	  if(loading) {
	  	$('#homeInfinite').show();
	  	return;
	  } else {
	  	$('#homeInfinite').hide();
	  }
	  loading = true;
	  setTimeout(function() {
//	  	if ('none' == $('#noData').css('display')) {
////		  	console.log('start: ' + $('#start').val());
////		  	var start = parseInt($('#start').val()) + parseInt($('#limit').val());
////		  	$('#start').val(start);
//	//	    $("#homeContent").append("<p> 我是新加载的内容 </p>");
//			voteItemSearchII();
//	  	}

		voteItemSearchII();
		
//	    loading = false;
//	    $('#homeInfinite').hide();
	 }, 1500);   //模拟延迟
	});
	
	///
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
			alert('您已经投过票了！');
			return;
		}
	
		addVoteItem(voteId, ids);
	});
	///
	/// 
//	$('.vote-click').click(function(event) {
//		var userId = $('#userId').val();
//		var voteId = $('#voteId').val();
//		var voteItemId = $(this).attr('voteItemId');
//		
//		var exist = existUser4VoteItem(voteId, voteItemId, userId);
//		if (exist) {
//			alert('您已经投过票了！');
//			return;
//		}
//		addVoteItemX(voteId, voteItemId, userId, $(this));
//	});	// end voteBtn click
	///
	///
//	$('#sortDate').click(function(event) {
//		$('#sortNum').removeClass('btn-info');
//		$('#sortNum').addClass('btn-default');
//		$(this).remove('btn-default')
//		$(this).addClass('btn-info');
//		$('#pagination').html('');
//		$('#homeContent').html('');
//		voteItemSearchII();
//	});
	///
	///
	$('#sortOrderNo').click(function(event) {
		$('#sortNum').removeClass('btn-info');
		$('#sortNum').addClass('btn-default');
		$(this).removeClass('btn-default')
		$(this).addClass('btn-info');
		$('#pagination').html('');
		$('#homeContent').html('');
		$('#sortOrderNo').addClass('order-by-asc');
		voteItemSearchII();
	});
	///
	///
	$('#sortNum').click(function(event) {
		$('#sortOrderNo').removeClass('btn-info');
		$('#sortOrderNo').addClass('btn-default');
		$(this).removeClass('btn-default')
		$(this).addClass('btn-info');
		$('#pagination').html('');
		$('#homeContent').html('');
		$('#sortNum').removeClass('order-by-asc');
		voteItemSearchII();
	});
	///
	///
	$('#resetBtn').click(function() {
		$('#orderNo').val("");
		$('#searchBtn').trigger('click');
	});
	///
	///
//	voteItemSearchII();
	$('#sortOrderNo').trigger('click');
	///
	///
	

})
var voteItemSearchII = function() {
	var voteId = $('#voteId').val();
	var activityId = getParam('activityId');
	var activityTitle = getParam('activityTitle');
	var start = $('#start').val();
	var limit = $('#limit').val();
	var sort = 'create_date_time';
	var orderBy = 'desc';
	var orderNo = $('#orderNo').val();
	
	if ($('#sortDate').hasClass('btn-info')) {
		sort = 'create_date_time';
		if ($('#sortDate').hasClass('order-by-asc')) {
			orderBy = 'asc';
		} else {
			orderBy = 'desc';
		}
		
	} else if ($('#sortNum').hasClass('btn-info')){
		sort = 'total_num';
		if ($('#sortNum').hasClass('order-by-asc')) {
			orderBy = 'asc';
		} else {
			orderBy = 'desc';
		}
	} else if ($('#sortOrderNo').hasClass('btn-info')){
		sort = 'order_no';
		if ($('#sortOrderNo').hasClass('order-by-asc')) {
			orderBy = 'asc';
		} else {
			orderBy = 'desc';
		}
	}
	
	console.log('start: ' + start + ", limit: " + limit);
	if (limit == null || limit == '' || limit == undefined) {
		var limitX = $('#limitX').val();
		limit = limitX || 10;
		$('#limit').val(limit);
	}
	if (start == null || start == '' || start == undefined) {
		var startX = $('#startX').val();
		start = startX || 0;
		$('#start').val(start);
	} else {
//		start = parseInt(start) + parseInt(limit);
	}
	
	var voteItemId = null;
	
	voteItemSearch(voteId, activityId, activityTitle, voteItemId, start, limit, sort, orderBy, orderNo);
}

var voteItemSearch = function(voteId, activityId, activityTitle, voteItemId, start, limit, sort, orderBy, orderNo) {
	
//	$('#progress').dialog('open');
	var basePath = $('#basePath').val();
	$.ajax({
		"url" : basePath + "/api/unified/vote/loadmore/query/" + voteId,
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		"data": {
            "activityId": activityId,
            "activityTitle": activityTitle,
            "voteItemId" : voteItemId,
            "start": start,
            "limit": limit,
            "sort": sort,
            "orderBy": orderBy,
            "paginationFlage": true,
            "orderNo": orderNo
        },
        success: function(data, textStatus, jqXHR ) {
//        	$('#progress').dialog('close');
			
        	var content = "";
        	if (data == null) {
        		console.log('response data is null');
        		$('#noData').show();
				loading = false;
        		$('#homeInfinite').hide();
        		return;
        	}
//        	if (data.success) {
				$('#noData').hide();
        		var template = $('#homeTmpl').html();
				Mustache.parse(template);   // optional, speeds up future uses
				var rendered = Mustache.render(template, data);
//				$('#homeContent').html(rendered);
				$('#homeContent').append(rendered);
				
				var template = $('#paginationTmpl').html();
				Mustache.parse(template);   // optional, speeds up future uses
				var rendered = Mustache.render(template, data);
//				$('#homeContent').html(rendered);
				$('#pagination').html(rendered);
				
				
				// 查询以后, start自动改变
				console.log('当前start: ' + $('#start').val());
			  	var start = parseInt($('#start').val()) + parseInt($('#limit').val());
			  	$('#start').val(start);
				console.log('下一次start: ' + $('#start').val());
				
				
				/// 
				$('.vote-click').unbind('click');
				$('.vote-click').click(function(event) {
					var userId = $('#userId').val();
					var voteId = $('#voteId').val();
					var voteItemId = $(this).attr('voteItemId');
					
					var exist = existUser4VoteItem(voteId, voteItemId, userId);
					if (exist) {
						alert('您已经投过票了！');
						return;
					}
					addVoteItemX(voteId, voteItemId, userId, $(this));
				});	// end voteBtn click
				///
				///
				loading = false;
				$('#homeInfinite').hide();
				///
				
//        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}


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

// 多个投票
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

//	$.showLoading('正在加载...');
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
//        	$.hideLoading();
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
// 一个投票
var addVoteItemX = function(voteId, voteItemId, userId, me) {
		var ids = [];
		ids.push(voteItemId);
		console.log('ids: ' + ids);
		if (ids == null || ids.length <= 0) {
			alert('请选择一条记录');
			return;
		}
		
//		var confirm = window.confirm('确定投票');
//		if (!confirm) {
//			return;
//		}
	
//		$.showLoading('正在加载...');
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
//	        	$.hideLoading();
//	        	window.location.reload();
				me.find('span.glyphicon').removeClass('glyphicon-heart-empty').addClass('glyphicon-heart').css('color', 'red');
				var num = me.find('span.vote-num').text();
				me.find('span.vote-num').text(parseInt(num) + 1);
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





