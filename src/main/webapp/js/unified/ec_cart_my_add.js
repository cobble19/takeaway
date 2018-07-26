$(document).ready(function() {
    $('#ecCartMyAddBtn, button[name="ecCartMyAddBtn"]').click(function() {
        $('body').loading({
        	message: '加载中...'
        });

        var params = {};
        params['productId'] = $('#productId').val();
        params['productName'] = $('#productName').val();
        params['authorizerAppId'] = $('#authorizerAppId').val();

        $.ajax({
            "url" : $('#basePath').val() + "/api/unified/ecCart/add",
            "type" : "POST",
            "headers" : {
                "Content-Type" : "application/json"
            },
            "dataType" : 'json',
            // traditional :true,
            "data": JSON.stringify(params),
            success: function(data, textStatus, jqXHR ) {
                $('body').loading('stop');
                if (data.success) {
                    alert('收藏成功');
                    window.location.reload();
                } else {
                    alert('收藏失败');
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log('Load Error!' + errorThrown);
            },
            complete: function(jqXHR, textStatus) {
                console.log('Ajax complete.');
                $('body').loading('stop');
            }
        });
    })	// end delete
	///
});
