$(function() {

})

///
function openWxChooseCardWin() {
    var shopId = $('#shopIdCard').val();
    var cardType = $('#cardTypeCard').val();
    var cardId = $('#cardIdCard').val();
    var timestamp = $('#timestampCard').val();
    var nonceStr = $('#nonceStrCard').val();
    var signType = $('#signTypeCard').val();
    var cardSign = $('#cardSignCard').val();
    wxChooseCard(shopId, cardType, cardId, timestamp, nonceStr, signType, cardSign);
}
///
function wxChooseCard(shopId, cardType, cardId, timestamp, nonceStr, signType, cardSign) {
    // alert("shopId: " + shopId + ", cardType: " + cardType
    //     + ", cardId: " + cardId + ", timestamp: " + timestamp
    //     + ", nonceStr: " + nonceStr + ", signType: " + signType
    //     + ", cardSign: " + cardSign);
    wx.chooseCard({
        shopId: '' + shopId, // 门店Id
        cardType: '' + cardType, // 卡券类型
        cardId: '' + cardId, // 卡券Id
        timestamp: timestamp, // 卡券签名时间戳
        nonceStr: '' + nonceStr, // 卡券签名随机串
        signType: '' + signType, // 签名方式，默认'SHA1'
        cardSign: '' + cardSign, // 卡券签名
        success: function (res) {
            var cardList= res.cardList; // 用户选中的卡券列表信息
            /**
             * [{"card_id":"","encrypt_code":"", "app_id":""}]
             */
            var cardArr = JSON.parse(cardList);
            var cardsResult = [];
            $.each(cardArr, function(index, element) {
                var card = {};
                var cardId = element.card_id;
                var encryptCode = element.encrypt_code;
                var code = getWxCardCodeDecrypt(encryptCode);
                card["cardId"] = cardId;
                card["code"] = code;
                cardsResult.push(card);
            });
            //alert('cardList: ' + cardList);
            //alert('chooseCard success' + ", res: " + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
            wxOpenCard(cardsResult);
        },
        fail: function(res) {
            alert('chooseCard fail' + ", res: " + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
        },
        complete: function(res) {
            //alert('chooseCard complete, ' + ", res: " + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
        },
        cancel: function(res) {
            //alert('chooseCard cancel, ' + ', res: ' + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
        },
        trigger: function(res) {
            //alert('chooseCard trigger, ' + ', res: ' + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
        }
    });
}
///
///
function getWxCardCodeDecrypt(encryptCode) {
    var ret = "";
    var params = {
        "encrypt_code": encryptCode
    }
    // sync
    $.ajax({
        "url" : $('#basePath').val() + "/api/wx/card/code/decrypt",
        "type" : "POST",
        "async": false,
        "headers" : {
            "Content-Type" : "application/json"
        },
        /*"dataType" : 'json',*/
        "data": JSON.stringify(params),
        "success": function(data, textStatus, jqXHR ) {
            console.log("data = " + data);
            ret = data.code;
        },
        "error": function(jqXHR, textStatus, errorThrown) {
            alert('加载失败!');
        },
        "complete": function(jqXHR, textStatus) {
            console.log('Ajax complete.');
        }
    });	// ajax
    return ret;
}
///

///
function wxOpenCard(cardList) {
    wx.openCard({
        cardList: cardList,// 需要打开的卡券列表
//                        cardList: [{
//                            cardId: 'pvyahuPhj9_WZn7fCnKUfPh3hzCE',
//                            code: '476505961685'
//                        }],// 需要打开的卡券列表
        success: function (res) {
            //alert('openCard success' + ", res: " + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
        },
        fail: function(res) {
            alert('打开微信卡券失败' + ", res: " + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
        },
        complete: function(res) {
            //alert('openCard complete, ' + ", res: " + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
        },
        cancel: function(res) {
            //alert('openCard cancel, ' + ', res: ' + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
        },
        trigger: function(res) {
            //alert('openCard trigger, ' + ', res: ' + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
        }
    });
}
///