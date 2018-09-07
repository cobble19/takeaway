$(document).ready(function() {
    $('button[name=wxCardAcquireBtn]').click(function() {
        var ecWxCardId = $(this).attr('ecWxCardId');
        openWxAddCardWin(ecWxCardId);
    })

    function openWxAddCardWin(ecWxCardId) {

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
                alert('data.success: ' + data.success);
                alert('data: ' + JSON.stringify(data));
                if (data.success) {
                    var ecWxCardPOJO = data.ecWxCardPOJO;
                    var cardList = data.cartList;
                    alert('cardList: ' + cardList + ", ecWxCardPOJO: " + ecWxCardPOJO);
                    wxAddCard(cardList, ecWxCardPOJO);
                    alert('openWxAddCardWin 领取成功: ' + JSON.stringify(data));
                } else {
                    alert(data.errMsg);
                }
            },
            "error": function(jqXHR, textStatus, errorThrown) {
                alert('加载失败!');
            },
            "complete": function(jqXHR, textStatus) {
                console.log('Ajax complete.');
            }
        });	// ajax

    }
})


