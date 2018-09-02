$(function() {
    ///
    //$('#qrcodeModal').modal('hide');

    function onceClick() {
        disableFeeBtn();
    }

    function disableFeeBtn() {
        $('#myFeeBtn').attr("readonly", true).addClass('disabled')
            .attr('disabled', true);
    }
    function enableFeeBtn() {
        $('#myFeeBtn').attr("readonly", false).removeClass('disabled')
            .attr('disabled', false);
    }
    $('button[name=wxCardChooseBtn]').click(function() {
        openWxChooseCardWin();
    })

    $('#myFeeBtn').click(function() {
        onceClick();
        myFeeClick();
    });
    function myFeeClick() {
        var subscribeFlag = $('#subscribeFlag').val();
        console.log('subscribeFlag: ' + subscribeFlag);
        if (subscribeFlag == '0') {
            $('#qrcodeModal').modal('show');
            enableFeeBtn();
            return;
        }

// 					$('#myFeeForm').submit();
        var authorizerAppId = $('#appId').val();
        var productId = $('#productId').val();
        var unitPrice = $('#unitPrice').val();
        var quantity = $('#quantity').val();



        var params = {};
        params.authorizerAppId = authorizerAppId;
        params.productId = productId;
        params.unitPrice = unitPrice;
        params.quantity = quantity;

        var result = {};

        // sync
        $.ajax({
            "url" : $('#basePath').val() + "/api/ecommerce/ecorder/ecproduct/callwxpay",
            "type" : "POST",
            "async": true,
            /*"headers" : {
                "Content-Type" : "application/json"
            },*/
            /*"dataType" : 'json',*/
            "data": (params),
            "success": function(data, textStatus, jqXHR ) {
                enableFeeBtn();
                console.log("data = " + data);
                result = data;
                if (data.success) {
                    // Uo: Unified Order
                    $('#appIdUo').val(data.jsPayMap.appId);
                    $('#timestampUo').val(data.jsPayMap.timeStamp);
                    $('#nonceStrUo').val(data.jsPayMap.nonceStr);
                    $('#prepayIdUo').val(data.jsPayMap.prepayId);
                    $('#paySignUo').val(data.jsPayMap.sign);
                    $('#packageUo').val(data.jsPayMap.packageUo);
                    // for update ecOrder and wpOrder's jsPayResultCode, to see wx_js_pay_bridge.js
                    $('#orderId').val(data.ecOrderPOJO.orderId);
                    $('#wpOrderId').val(data.wpOrderPOJO.wpOrderId);
                    alert('申请订单成功！去付款吧!')

                    // call wxpay
                    if (typeof WeixinJSBridge == "undefined") {
                        if( document.addEventListener ){
                            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
                        }else if (document.attachEvent){
                            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
                        }
                    }else{
                        onBridgeReady();
                    }
                    // end call wxpay
                } else {
                    alert('申请订单失败, ' + data.errMessage);
                }
            },
            "error": function(jqXHR, textStatus, errorThrown) {
                enableFeeBtn();
                alert('加载失败!');
            },
            "complete": function(jqXHR, textStatus) {
                enableFeeBtn();
                console.log('Ajax complete.');
            }
        });	// ajax
    }
    ///

    ///
    $('#quantity').change(function() {
        var unitPrice = parseInt($('#unitPrice').val());
        var quantity = parseInt($('#quantity').val());
        var feeTotal = unitPrice * quantity;
        feeTotal = feeTotal / 100;  // 分->元
        $('#feeTotal').text(feeTotal);
    })
    ///
    // execute init
    initFeeTotal();
    function initFeeTotal() {
        var unitPrice = parseInt($('#unitPrice').val());
        var quantity = 1;
        var feeTotal = unitPrice * quantity;
        feeTotal = feeTotal / 100;  // 分->元
        $('#feeTotal').text(feeTotal);
    }
    ///
    // 为了正确显示带格式的html
    $('#buyAboutX').html($('#buyAbout').text());
    ///
    $('#qrcodeModal').on('hide.bs.modal', function (e) {
        window.location.reload();
    });
    ///
    function validProduct() {
        var ret = true;
        var startDateTime = new Date($('#startDateTime').val().replace('CST', '')).getTime();
        var endDateTime = new Date($('#endDateTime').val().replace('CST', '')).getTime();
        var curDateTime = new Date().getTime();
        if (startDateTime > curDateTime) {
            $('#myFeeBtn').val('商品还没有开始销售').attr("readonly", true).addClass('disabled')
                .attr('disabled', true);
            ret = false;
        }
        if (endDateTime < curDateTime) {
            $('#myFeeBtn').val('商品销售已经结束').attr("readonly", true).addClass('disabled')
                .attr('disabled', true);
            ret = false;
        }
        var quantityStock = parseInt($('#quantityStock').val());
        var wxCardStock = parseInt($('#wxCardStock').val());

        var limitNumEveryone = parseInt($('#limitNumEveryone').val());
        var limitNumDay = quantityStock + 9999;
        if ($('#limitNumDay').val() != null && $('#limitNumDay').val() != '') {
            limitNumDay = parseInt($('#limitNumDay').val());
        }

        var quantity = parseInt($('#quantity').val());
        var orderCount = parseInt($('#orderCount').val());
        var orderCountToday = parseInt($('#orderCountToday').val());
        var wxCardLimit = parseInt($('#wxCardLimit').val());
        var wxCardCount = parseInt($('#wxCardCount').val());
        if (quantityStock < quantity || wxCardStock < quantity) {
            $('#myFeeBtn').val('商品已全部售罄').attr("readonly", true).addClass('disabled')
                .attr('disabled', true);
            ret = false;
        }
        if (orderCount + quantity > limitNumEveryone || orderCount + quantity > wxCardLimit) {
            $('#myFeeBtn').val('您已达购买上限，感谢您的惠顾').attr("readonly", true).addClass('disabled')
                .attr('disabled', true);
            ret = false;
        }
        if (orderCountToday + quantity > limitNumDay || orderCountToday + quantity > limitNumDay) {
            $('#myFeeBtn').val('今日商品已售罄，请您明日再来').attr("readonly", true).addClass('disabled')
                .attr('disabled', true);
            ret = false;
        }
        if (wxCardCount + quantity > limitNumEveryone || wxCardCount + quantity > wxCardLimit) {
            $('#myFeeBtn').val('您已达购买上限，感谢您的惠顾').attr("readonly", true).addClass('disabled')
                .attr('disabled', true);
            ret = false;
        }

        return ret;
    }
    ///
    validProduct();
    ///
    var $numJiaBtn = $("#num-jia");
    var $numJianBtn = $("#num-jian");
    var $quantity = $("#quantity");
    var $limitNumEveryone = $("#limitNumEveryone");
    var $orderCount = $('#orderCount');
    $numJiaBtn.click(function () {
        var quantity = parseInt($quantity.val());
        var limitNumEveryone = parseInt($limitNumEveryone.val());
        var orderCount = parseInt($orderCount.val());
        var availableCount = limitNumEveryone - orderCount;
        if(quantity < availableCount) {
            quantity = quantity + 1;
            $quantity.val(quantity);
        }
    })
    $numJianBtn.click(function () {
        var quantity = parseInt($quantity.val());
        var limitNumEveryone = parseInt($limitNumEveryone.val());
        if(quantity <= 1) {
            quantity = 1;
        } else {
            quantity = quantity - 1;
        }
        $quantity.val(quantity);
    })
    ///
    ///

}) // end jQuery