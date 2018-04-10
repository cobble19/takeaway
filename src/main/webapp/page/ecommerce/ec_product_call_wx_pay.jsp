<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cobble.takeaway.util.CommonConstant" %>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<%@include file="../../page/common/head.jsp"%>
		
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/progress_dialog.js"></script>
		<script type="text/javascript">
			$(function() {
				var appId = $('#appId').val();
				var timestamp = $('#timestamp').val();
				var nonceStr = $('#nonceStr').val();
				var signature = $('#signature').val();
				var jsApiList = ($('#jsApiList').val().replace('[','').replace(']','').replace(/\s+/g,'')).split(',');
				///
				// 通过config接口注入权限验证配置
				wx.config({
				    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，
				    				//若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId: appId, // 必填，公众号的唯一标识
				    timestamp: timestamp, // 必填，生成签名的时间戳
				    nonceStr: nonceStr, // 必填，生成签名的随机串
				    signature: signature,// 必填，签名，见附录1
				    jsApiList: jsApiList // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
				///
				// 通过ready接口处理成功验证
				wx.ready(function(){
				    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，
				    // config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，
				    // 则须把相关接口放在ready函数中调用来确保正确执行。
				    // 对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
// 				    alert('wx.ready');
				});
				///
				// 通过error接口处理失败验证
				wx.error(function(res){
				    // config信息验证失败会执行error函数，如签名过期导致验证失败，
				    // 具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
				    alert('wx.error' + ", res: " + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
				});
				///
				// jsapi调用微信支付api
				function onBridgeReady(){
					var appId = $('#appIdUo').val();
					var timestamp = $('#timestampUo').val();
					var nonceStr = $('#nonceStrUo').val();
					var prepayId = $('#prepayIdUo').val();
					var paySign = $('#paySignUo').val();
					var packageUo = $('#packageUo').val();
					
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
					     	   	alert(res.err_msg);
					        } else if (res.err_msg == "get_brand_wcpay_request:cancel" ) {
					     	   	alert(res.err_msg);
					        } else if (res.err_msg == "get_brand_wcpay_request:fail" ) {
					     	   	alert(res.err_msg);
					        } else {
					     	   	alert(res);
					        }
					    }
					); 
				}
				$('#openWxPayWindowBtn').click(function() {
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
				})
					
				///
				
			})
		</script>
	</head>
	
  <body style="padding-top: 100px;">
  	<input type="hidden" id="appId" name="appId" value="${wxJsSdkConfigRespApiPOJO.appId}" />
  	<input type="hidden" id="timestamp" name="timestamp" value="${wxJsSdkConfigRespApiPOJO.timestamp}" />
  	<input type="hidden" id="nonceStr" name="nonceStr" value="${wxJsSdkConfigRespApiPOJO.nonceStr}" />
  	<input type="hidden" id="signature" name="signature" value="${wxJsSdkConfigRespApiPOJO.signature}" />
  	<input type="hidden" id="jsApiList" name="jsApiList" value="${wxJsSdkConfigRespApiPOJO.jsApiList}" />
  	
  	<input type="hidden" id="url" name="url" value="${wxJsSdkConfigRespApiPOJO.url}" />
  	<input type="hidden" id="ticket" name="ticket" value="${wxJsSdkConfigRespApiPOJO.ticket}" />
  	
  	<input type="hidden" id="appIdUo" name="appIdUo" value="${jsPayMap.appId}" />
  	<input type="hidden" id="timestampUo" name="timestampUo" value="${jsPayMap.timeStamp}" />
  	<input type="hidden" id="nonceStrUo" name="nonceStrUo" value="${jsPayMap.nonceStr}" />
  	<input type="hidden" id="prepayIdUo" name="prepayIdUo" value="${jsPayMap.prepayId}" />
  	<input type="hidden" id="paySignUo" name="paySignUo" value="${jsPayMap.sign}" />
  	<input type="hidden" id="packageUo" name="packageUo" value="${jsPayMap.packageUo}" />
  	
  	<div class="container-fluid">
        <div class="row" style=" height:4px; background-color:#44b549;"></div>
        <c:if test="${success == true}">
        		<input type="button" id="openWxPayWindowBtn" value="去支付"/>
        </c:if>
        <c:if test="${success == true}">
        		<span style="color: red;"><c:out value="${errMessage}"></c:out></span>
        </c:if>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  	<%@include file="../../bottom.jsp"%>	
	</div>
  </body>
</html>
			  		
	     			
	     			
	     			
	     			
