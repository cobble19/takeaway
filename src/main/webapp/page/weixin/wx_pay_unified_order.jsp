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
				$('#testWxJsSdk').click(function() {
					wx.checkJsApi({
					    jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
					    success: function(res) {
					    // 以键值对的形式返回，可用的api值true，不可用为false
					    // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
						    alert('success' + ", res: " + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
					    },
					    fail: function(res) {
					    		alert('checkJsApi fail' + ", res: " + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
					    },
					    complete: function(res) {
					    		alert('complete, ' + ", res: " + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
					    },
					    cancel: function(res) {
					    		alert('cancel, ' + ', res: ' + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
					    },
					    trigger: function(res) {
					    		alert('trigger, ' + ', res: ' + res + ", JSON.stringify(res)): "  + JSON.stringify(res));
					    }
					});
				});
				
				alert(location.href.split('#')[0]);
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
				    alert('wx.ready');
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
					
/* 
					wx.requestPayment({   
						'appId': appId,    
					   'timeStamp': timestamp + '',
					   'nonceStr': nonceStr + '',
					   'package': 'prepay_id=' + prepayId + '',
					   'signType': 'MD5',
					   'paySign': paySign + '',
					   'success':function(res){
					   	alert(res.errMsg + " " + res);
					   },
					   'fail':function(res){
					   	alert(res.errMsg + " " + res);
					   },
					   'complete': function(res) {
					   	alert(res.errMsg + " " + res);
					   }
					}) */
					
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
					    		alert("appId: " + appId + ", timeStamp: " + timestamp + ", nonceStr: " + nonceStr
					    				+ ", package: " + "prepay_id=" + prepayId + ", packageUo: " + packageUo + ", signType: " + "MD5" + ", paySign: " + paySign);
					 	   	alert("微信支付结果: " + res);
					 	   	
					 	 
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
				// 判断当前客户端版本是否支持指定JS接口
				wx.checkJsApi({
				    jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
				    success: function(res) {
				    // 以键值对的形式返回，可用的api值true，不可用为false
				    // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
					    alert('success' + ", res: " + res + ", 1-JSON.stringify(res)): "  + JSON.stringify(res));
				    },
				    fail: function(res) {
				    		alert('checkJsApi fail' + ", res: " + res + ", 1-JSON.stringify(res)): "  + JSON.stringify(res));
				    },
				    complete: function(res) {
				    		alert('complete, ' + ", res: " + res + ", 1-JSON.stringify(res)): "  + JSON.stringify(res));
				    },
				    cancel: function(res) {
				    		alert('cancel, ' + ', res: ' + res + ", 1-JSON.stringify(res)): "  + JSON.stringify(res));
				    },
				    trigger: function(res) {
				    		alert('trigger, ' + ', res: ' + res + ", 1-JSON.stringify(res)): "  + JSON.stringify(res));
				    }
				});
				///
				// 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
				wx.onMenuShareTimeline({
				    title: 'onMenuShareTimelineTitle', // 分享标题
				    link: 'http://www.deweiyizhan.com/onMenuShareTimeline', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				    imgUrl: 'http://www.deweiyizhan.com/images/share_img.jpg', // 分享图标
				    success: function () {
				    		// 用户确认分享后执行的回调函数
				    		alert('success, ' + 'onMenuShareTimeline:')
					},
					cancel: function () {
				    		// 用户取消分享后执行的回调函数
				    		alert('cancel, ' + 'onMenuShareTimeline:')
				    }
				});
				///
				// 获取“分享给朋友”按钮点击状态及自定义分享内容接口
				wx.onMenuShareAppMessage({
					title: 'onMenuShareAppMessageTitle', // 分享标题
					desc: 'desc', // 分享描述
					link: 'http://www.deweiyizhan.com/onMenuShareAppMessage', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
					imgUrl: 'http://www.deweiyizhan.com/images/onMenuShareAppMessage.jpg', // 分享图标
					type: 'link', // 分享类型,music、video或link，不填默认为link
					dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
					success: function () {
						// 用户确认分享后执行的回调函数
						alert('success, onMenuShareAppMessage');
					},
					cancel: function () {
						// 用户取消分享后执行的回调函数
						alert('cancel, onMenuShareAppMessage');
					}
				});
				///
				// 获取“分享到QQ”按钮点击状态及自定义分享内容接口
				wx.onMenuShareQQ({
					title: 'onMenuShareQQ-Title', // 分享标题
					desc: 'onMenuShareQQ-desc', // 分享描述
					link: 'http://www.baidu.com/onMenuShareQQ', // 分享链接
					imgUrl: 'http://www.baidu.com/images/onMenuShareQQ.jpg', // 分享图标
					success: function () {
						// 用户确认分享后执行的回调函数
						alert('success, onMenuShareQQ');
					},
					cancel: function () {
						// 用户取消分享后执行的回调函数
						alert('cancel, onMenuShareQQ');
					}
				});
				///
				/* //获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
				wx.onMenuShareWeibo({
					title: 'onMenuShareWeibo-Title', // 分享标题
					desc: 'onMenuShareWeibo-desc', // 分享描述
					link: 'http://www.baidu.com/onMenuShareWeibo', // 分享链接
					imgUrl: 'http://www.baidu.com/images/onMenuShareWeibo.jpg', // 分享图标
					success: function () {
						// 用户确认分享后执行的回调函数
						alert('success, onMenuShareWeibo');
					},
					cancel: function () {
						// 用户取消分享后执行的回调函数
						alert('cancel, onMenuShareWeibo');
					}
				});
				///
				// 获取“分享到QQ空间”按钮点击状态及自定义分享内容接口
				wx.onMenuShareQZone({
					title: 'onMenuShareQZone-Title', // 分享标题
					desc: 'onMenuShareQZone-desc', // 分享描述
					link: 'http://www.baidu.com/onMenuShareQZone', // 分享链接
					imgUrl: 'http://www.baidu.com/images/onMenuShareQZone.jpg', // 分享图标
					success: function () {
						// 用户确认分享后执行的回调函数
						alert('success, onMenuShareQZone');
					},
					cancel: function () {
						// 用户取消分享后执行的回调函数
						alert('cancel, onMenuShareQZone');
					}
				});
				///
				// 拍照或从手机相册中选图接口
				wx.chooseImage({
					count: 2, // 默认9
					sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
					success: function (res) {
						var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
						alert('success, chooseImage, res: ' + res);
					}
				});
				///
				// 预览图片接口
				wx.previewImage({
					current: 'http://www.baidu.com/images/current_img.jpg', // 当前显示图片的http链接
					urls: ['http://www.baidu.com/images/next_1.jpg'] // 需要预览的图片http链接列表
				});
				///
				// 上传图片接口
				// 备注：上传图片有效期3天，可用微信多媒体接口下载图片到自己的服务器，此处获得的 serverId 即 media_id。
				wx.uploadImage({
					localId: '', // 需要上传的图片的本地ID，由chooseImage接口获得
					isShowProgressTips: 1, // 默认为1，显示进度提示
					success: function (res) {
						var serverId = res.serverId; // 返回图片的服务器端ID
						alert('success, uploadImage, res: ' + res);
					}
				});
				///
				// 下载图片接口
				wx.downloadImage({
					serverId: '', // 需要下载的图片的服务器端ID，由uploadImage接口获得
					isShowProgressTips: 1, // 默认为1，显示进度提示
					success: function (res) {
						var localId = res.localId; // 返回图片下载后的本地ID
						alert('success, downloadImage, res: ' + res);
					}
				});
				///
				// 获取本地图片接口
				wx.getLocalImgData({
					localId: '', // 图片的localID
					success: function (res) {
						var localData = res.localData; // localData是图片的base64数据，可以用img标签显示
						alert('success, getLocalImgData, res: ' + res);
					}
				}); */

			})
		</script>
	</head>
	
  <body style="padding-top: 100px;">
  	<%@include file="../../reg_login.jsp" %>
  	<input type="text" id="appId" name="appId" value="${wxJsSdkConfigRespApiPOJO.appId}" />
  	<input type="text" id="timestamp" name="timestamp" value="${wxJsSdkConfigRespApiPOJO.timestamp}" />
  	<input type="text" id="nonceStr" name="nonceStr" value="${wxJsSdkConfigRespApiPOJO.nonceStr}" />
  	<input type="text" id="signature" name="signature" value="${wxJsSdkConfigRespApiPOJO.signature}" />
  	<input type="text" id="jsApiList" name="jsApiList" value="${wxJsSdkConfigRespApiPOJO.jsApiList}" />
  	
  	<input type="text" id="url" name="url" value="${wxJsSdkConfigRespApiPOJO.url}" />
  	<input type="text" id="ticket" name="ticket" value="${wxJsSdkConfigRespApiPOJO.ticket}" />
  	
  	<input type="text" id="appIdUo" name="appIdUo" value="${jsPayMap.appId}" />
  	<input type="text" id="timestampUo" name="timestampUo" value="${jsPayMap.timeStamp}" />
  	<input type="text" id="nonceStrUo" name="nonceStrUo" value="${jsPayMap.nonceStr}" />
  	<input type="text" id="prepayIdUo" name="prepayIdUo" value="${jsPayMap.prepayId}" />
  	<input type="text" id="paySignUo" name="paySignUo" value="${jsPayMap.sign}" />
  	<input type="text" id="packageUo" name="packageUo" value="${jsPayMap.packageUo}" />
  	
  	<div class="container-fluid">
        <div class="row" style=" height:4px; background-color:#44b549;"></div>
        <input type="button" id="openWxPayWindowBtn" value="打开微信支付窗口"/>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  	<%@include file="../../bottom.jsp"%>	
	</div>
  </body>
</html>
			  		
	     			
	     			
	     			
	     			
