$(document).ready(function() {
    $('a[name=wxCardAcquireBtn]').click(function() {
        var ecWxCardId = $(this).attr('ecWxCardId');
        openWxAddCardWin(ecWxCardId);
    })

    function openWxAddCardWin(ecWxCardId) {
        $('body').loading();
        var params = {};
        // params['ecWxCardId'] = ecWxCardId;
        $.ajax({
            "url" : $('#basePath').val() + "/api/ecommerce/ecorder/ecwxcardacquire" + '?ecWxCardId=' + ecWxCardId,
            "type" : "GET",
            "async": true,
            "headers" : {
                "Content-Type" : "application/json"
            },
            /*"dataType" : 'json',*/
            "data": (params),
            "success": function(data, textStatus, jqXHR ) {
                console.log("data = " + data);
                var result = data;
                // alert('openWxAddCardWin data.success: ' + result.success);
                // alert('openWxAddCardWin data: ' + JSON.stringify(result));
                if (result.success) {
                    var ecWxCardPOJO = result.ecWxCardPOJO;
                    var cardList = result.cardList;
                    wxAddCard(cardList, ecWxCardPOJO);
                    // alert('openWxAddCardWin 领取成功: ' + JSON.stringify(result));
                } else {
                    alert("openWxAddCardWin errMsg: " + result.errMsg);
                }
            },
            "error": function(jqXHR, textStatus, errorThrown) {
                alert('openWxAddCardWin 加载失败!');
            },
            "complete": function(jqXHR, textStatus) {
                console.log('openWxAddCardWin Ajax complete.');
                // alert('openWxAddCardWin Ajax complete.');
                $('body').loading('stop');
            }
        });	// ajax

    }
})


