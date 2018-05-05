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
// 				alert(location.href.split('#')[0]);
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
				$('#myFeeBtn').click(function() {
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
					
					var result = null;
					
					// sync
				    	$.ajax({
				    		"url" : $('#basePath').val() + "/api/ecommerce/ecorder/ecproduct/callwxpay",
				    		"type" : "POST",
				    		"async": false,
				    		/*"headers" : {
				    			"Content-Type" : "application/json"
				    		},*/
				    		/*"dataType" : 'json',*/
				    		"data": (params),
				        "success": function(data, textStatus, jqXHR ) {
				            	console.log("data = " + data);
				            	result = data;
				            	if (data.success) {
								$('#appIdUo').val(data.jsPayMap.appId);
								$('#timestampUo').val(data.jsPayMap.timeStamp);
								$('#nonceStrUo').val(data.jsPayMap.nonceStr);
								$('#prepayIdUo').val(data.jsPayMap.prepayId);
								$('#paySignUo').val(data.jsPayMap.sign);
								$('#packageUo').val(data.jsPayMap.packageUo);
				                	alert('申请订单成功！去付款!')
				            	} else {
				                	alert('申请订单失败, ' + data.errMessage);
				            	}
				         },
				         "error": function(jqXHR, textStatus, errorThrown) {
				            	alert('加载失败!');
				         },
				         "complete": function(jqXHR, textStatus) {
				            	console.log('Ajax complete.');
				         }
				    	});	// ajax
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
				})

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
				///
				$('#quantity').change(function() {
					var unitPrice = parseInt($('#unitPrice').val());
					var quantity = parseInt($('#quantity').val());
					var feeTotal = unitPrice * quantity;
					feeTotal = feeTotal / 100;
					$('#feeTotal').text(feeTotal);
				})
				///
				// execute init
				initFeeTotal();
				function initFeeTotal() {
					var unitPrice = parseInt($('#unitPrice').val());
					var quantity = 1;
					var feeTotal = unitPrice * quantity;
					feeTotal = feeTotal / 100;
					$('#feeTotal').text(feeTotal);
				}
				///
				$('#buyAboutX').html($('#buyAbout').text());
				///
				
			})
		</script>
	</head>
	
  <body>
  	<!-- WX JS SDK 有效 -->
  	<input type="hidden" id="appId" name="appId" value="${wxJsSdkConfigRespApiPOJO.appId}" />
  	<input type="hidden" id="timestamp" name="timestamp" value="${wxJsSdkConfigRespApiPOJO.timestamp}" />
  	<input type="hidden" id="nonceStr" name="nonceStr" value="${wxJsSdkConfigRespApiPOJO.nonceStr}" />
  	<input type="hidden" id="signature" name="signature" value="${wxJsSdkConfigRespApiPOJO.signature}" />
  	<input type="hidden" id="jsApiList" name="jsApiList" value="${wxJsSdkConfigRespApiPOJO.jsApiList}" />
  	
  	<input type="hidden" id="url" name="url" value="${wxJsSdkConfigRespApiPOJO.url}" />
  	<input type="hidden" id="ticket" name="ticket" value="${wxJsSdkConfigRespApiPOJO.ticket}" />
  	
  	<!-- 调用微信支付js接口 -->
  	<input type="hidden" id="appIdUo" name="appIdUo" />
  	<input type="hidden" id="timestampUo" name="timestampUo" />
  	<input type="hidden" id="nonceStrUo" name="nonceStrUo" />
  	<input type="hidden" id="prepayIdUo" name="prepayIdUo" />
  	<input type="hidden" id="paySignUo" name="paySignUo" />
  	<input type="hidden" id="packageUo" name="packageUo" />
  	
  	<div class="container-fluid">
        <div class="row">
        	<div class="col-md-12 col-sm-12">
<%--         		<form id="myFeeForm" action="<cmn:base/>/web/ecommerce/ecorder/ecproduct/callwxpay" class="form-horizontal"> --%>
        		<form id="myFeeForm" class="form-horizontal">
	        		<!-- 消费金额页面 -->
	        		<input type="hidden" id="authorizerAppId" name="authorizerAppId" value="${param.authorizerAppId}">
	        		<input type="hidden" id="productId" name="productId" value="${ecProductPOJO.productId}">
	        		<input type="hidden" id="unitPrice" name="unitPrice" value="${ecProductPOJO.unitPrice}">
                    
                    
	            <div class="row">
					<div class="col-xs-12"
						style="padding: 2px; text-align: center;">
							<img src='<cmn:base/>/${ecProductPOJO.imgUrl}'>
					</div>
			    </div>
		        <div class="row">
		          <div class="col-xs-12" style="margin:10px auto;"><h4 style="color:#F00;">￥<c:out value="${ecProductPOJO.unitPrice / 100}" /></h4></div>
		        </div> 
		        <div class="row">
		          <div class="col-xs-12" style="margin-bottom:5px;"><h5><c:out value="${ecProductPOJO.productName}"></c:out></h5></div>
		        </div>
		        <div class="row">
			      <div class="col-md-12 col-sm-12" style="margin-bottom:5px;">
			        			<h6 style="color:#aaa9ae"><c:out value="${ecProductPOJO.productName}" /> 库存 <c:out value="${ecProductPOJO.quantityStock}" /></h6>
			      </div>
			    </div>
		        <div class="row">
		          <div class="col-xs-12" style="background-color:#f5f5f5; height:10px;"></div>
		        </div> 
		        <div class="row">
		          <div class="col-xs-12" style="margin-top:10px;">
			          <h5>商品详情</h5>
			          <!--此处放详情数据-->
			          <span id="buyAbout" style="display:none;">
			          	<!-- 直接显示的话, 只能显示一段html文本, 不是正常的带有格式的内容 -->
			          	<c:out value="${ecProductPOJO.buyAbout}"></c:out>
			          </span>
			          <span id="buyAboutX">
			          </span>
		          </div>
		        </div>   
		        <nav class="navbar navbar-default navbar-fixed-bottom">
					<!-- Nav tabs -->
					<ul class="nav nav-pills" role="tablist"
						style="text-align: center; padding-top: 5px">
						<div role="presentation" class="active col-xs-12">
		                    <input type="button" class="btn btn-success btn-sm btn-block" id="myFeeBtn" name="myFeeBtn" value="创建订单"/>
						</div>
					</ul>
				</nav>
             
	        		<div class="row" style="display:none;">
	        				<div class="col-md-12 col-sm-12">
			        		<label>单价(元):</label>
			        		<c:out value="${ecProductPOJO.unitPrice / 100}" />
			        		</div>
	        				<div class="col-md-12 col-sm-12" style="display: none;">
				        		<label>件数:</label>
				        		<input type="hidden" id="quantity" name="quantity" class="form-control" value="1">
			        		</div>
	        		</div>
	        		<div class="row" style="display:none;">
	        			<div class="col-md-12 col-sm-12">
			        		<label>费用总计:</label>
			        		<span id="feeTotal"></span>
	        			</div>
	        		</div>
	        		<!--<input type="button" class="btn btn-default" id="myFeeBtn" name="myFeeBtn" value="创建订单"/>-->
        		</form>
        		</div>
        </div>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  	<%@include file="../../bottom.jsp"%>	
	</div>
  </body>
</html>
			  		
	     			
	     			
	     			
	     			
