$(function() {


})
///
// jsapi调用微信支付api
function onBridgeReady(){
    var appId = $('#appIdUo').val();
    var timestamp = $('#timestampUo').val();
    var nonceStr = $('#nonceStrUo').val();
    var prepayId = $('#prepayIdUo').val();
    var paySign = $('#paySignUo').val();
    var packageUo = $('#packageUo').val();
    onBridgeInvoke(appId, timestamp, nonceStr, packageUo, paySign);

}
///
///
function onBridgeInvoke(appId, timestamp, nonceStr, packageUo, paySign) {
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId": appId,     //公众号名称，由商户传入
            "timeStamp":timestamp,         //时间戳，自1970年以来的秒数
            "nonceStr":nonceStr, //随机串
            "package":packageUo,
            "signType":"MD5",         //微信签名方式：
            "paySign": paySign //微信签名
        },
        function(res){
// 					    		alert("appId: " + appId + ", timeStamp: " + timestamp + ", nonceStr: " + nonceStr
// 					    				+ ", package: " + "prepay_id=" + prepayId + ", packageUo: " + packageUo + ", signType: " + "MD5" + ", paySign: " + paySign);
// 					 	   	alert("微信支付结果: " + res);


            // 使用以下方式判断前端返回,微信团队郑重提示：
            //res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠
            if (res.err_msg == "get_brand_wcpay_request:ok" ) {
                /* alert(res.err_msg); */
                alert('支付成功');
                closeSelfWindow();
            } else if (res.err_msg == "get_brand_wcpay_request:cancel" ) {
                /* alert(res.err_msg); */
                alert('已经取消支付');
            } else if (res.err_msg == "get_brand_wcpay_request:fail" ) {
                /* alert(res.err_msg); */
                alert('支付失败');
            } else {
                /* alert(res); */
                alert('你遇到了特殊的情况, 请通过公众号或者公众号资料的联系方式联系管理员')
            }
        }
    );
}
///
