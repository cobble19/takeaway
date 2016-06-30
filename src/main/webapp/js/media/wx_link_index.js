$(document).ready(function() {
//	showDetail();
})

var showDetail = function() {
	
	$.ajax({
		"url" : $('#basePath').val() + "/web/media/wxLink/list",
		"type" : "GET",
		"headers" : {
			"Content-Type" : "application/json"
		},
		/*"dataType" : 'json',*/
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
        	if (data.gridModelList != null && data.gridModelList.length > 0) {
        		for (var i = 0; i < data.gridModelList.length; i++) {
        			wxLink = data.gridModelList[i];
        			if (i >= 9) {
        				break;
        			}
        			imgSrc = $('#basePath').val() + "/files/" + wxLink.imgSrc
        			$('#sec' + (i+1)).find('a').attr('href', wxLink.linkUrl)
												.attr('alt', wxLink.title)
												.attr('title', wxLink.title);
        			$('#sec' + (i+1)).find('img').attr('src', imgSrc)
        									.attr('alt', wxLink.title)
        									.attr('title', wxLink.title);
        		}
        	}
//        	$("#activityId").html(data.activityId);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	console.log('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
