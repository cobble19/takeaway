$(document).ready(function() {
    $('#deleteMyEcCartBtn, button[name=deleteMyEcCartBtn]').click(function() {
        var ids = [];
        ids.push($(this).attr("ecCartId"));
        console.log('ids: ' + ids);
        if (ids == null || ids.length <= 0) {
            alert('请选择一条记录');
            return;
        }

       var confirm = window.confirm('确定删除');
       if (!confirm) {
           return;
       }
        $('#ec_cart_my').loading({
        	message: '加载中...'
        });

        $.ajax({
            "url" : $('#basePath').val() + "/api/unified/ecCart/delete",
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
                $('#ec_cart_my').loading('stop');
            	alert('删除成功');
                window.location.reload();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log('Load Error!');
            },
            complete: function(jqXHR, textStatus) {
                console.log('Ajax complete.');
            }
        });
    })	// end delete
	///
});
