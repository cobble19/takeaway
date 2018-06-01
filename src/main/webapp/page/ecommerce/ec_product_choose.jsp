<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cobble.takeaway.util.CommonConstant" %>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<%@include file="../../page/common/head.jsp"%>
		
        <link rel="stylesheet" href="<cmn:base/>/js/thirdpart/countdown/jquery.countdown.css" />
		<script src="<cmn:base/>/js/thirdpart/countdown/jquery.countdown.js"></script>
		
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/progress_dialog.js"></script>
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/ecommerce/count_down.js"></script>
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
				    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，
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
				function closeSelfWindow() {
					wx.closeWindow();
					WeixinJSBridge.call('closeWindow');
				}
				///
				//$('#qrcodeModal').modal('hide');
				$('#myFeeBtn').click(function() {

					var subscribeFlag = $('#subscribeFlag').val();
					console.log('subscribeFlag: ' + subscribeFlag);
					if (subscribeFlag == '0') {
						$('#qrcodeModal').modal('show');
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
				                	alert('申请订单成功！去付款吧!')
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
				    	if (result.success == true) {
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
                        $('#myFeeBtn').val('购买超过限额').attr("readonly", true).addClass('disabled')
                            .attr('disabled', true);
                        ret = false;
					}
                    if (orderCountToday + quantity > limitNumDay || orderCountToday + quantity > limitNumDay) {
                        $('#myFeeBtn').val('购买超过每天限额').attr("readonly", true).addClass('disabled')
                            .attr('disabled', true);
                        ret = false;
                    }
                    if (wxCardCount + quantity > limitNumEveryone || wxCardCount + quantity > wxCardLimit) {
                        $('#myFeeBtn').val('购买超过限额').attr("readonly", true).addClass('disabled')
                            .attr('disabled', true);
                        ret = false;
                    }

                    return ret;
                }
                validProduct();
				///

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
  	
  	<!-- 公众号二维码 -->
  	<input type="hidden" id="subscribeFlag" name="subscribeFlag" value="${subscribeFlag ? '1' : '0'}" />
  	
  	<div class="container-fluid">
        <div class="row">
        	<div class="col-md-12 col-sm-12">
<%--         		<form id="myFeeForm" action="<cmn:base/>/web/ecommerce/ecorder/ecproduct/callwxpay" class="form-horizontal"> --%>
        		<form id="myFeeForm" class="form-horizontal">
	        		<!-- 消费金额页面 -->
	        		<input type="hidden" id="authorizerAppId" name="authorizerAppId" value="${param.authorizerAppId}">
	        		<input type="hidden" id="productId" name="productId" value="${ecProductPOJO.productId}">
	        		<input type="hidden" id="unitPrice" name="unitPrice" value="${ecProductPOJO.unitPrice}">
					<input type="hidden" id="quantityStock" name="quantityStock" value="${ecProductPOJO.quantityStock}">
					<input type="hidden" id="wxCardStock" name="wxCardStock" value="${ecProductPOJO.wxCardStock}">
					<input type="hidden" id="limitNumEveryone" name="limitNumEveryone" value="${ecProductPOJO.limitNumEveryone}">
					<input type="hidden" id="limitNumDay" name="limitNumDay" value="${ecProductPOJO.limitNumDay}">
					<input type="hidden" id="startDateTime" name="startDateTime" value="${ecProductPOJO.startDateTime}">
					<input type="hidden" id="endDateTime" name="endDateTime" value="${ecProductPOJO.endDateTime}">

					<input type="hidden" id="orderCount" name="orderCount" value="${orderCount}">
					<input type="hidden" id="orderCountToday" name="orderCountToday" value="${orderCountToday}">
					<input type="hidden" id="wxCardLimit" name="wxCardLimit" value="${wxCardLimit}">
					<input type="hidden" id="wxCardCount" name="wxCardCount" value="${wxCardCount}">
                    
                    
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
			        <h6 style="color:#aaa9ae"> 库存 <c:out value="${ecProductPOJO.wxCardStock > ecProductPOJO.quantityStock ? ecProductPOJO.quantityStock : ecProductPOJO.wxCardStock}" /></h6>
			      </div>
			    </div>
			    <div class="row">
			      <div class="col-md-12 col-sm-12" id="countDownStartDiv" style="margin-bottom:5px; display: none;">
					开始倒计时: <div id="countDownStart" style="display: none;"></div>
					<p id="noteStart"></p>
			      </div>
			      <div class="col-md-12 col-sm-12" id="countDownEndDiv" style="margin-bottom:5px; display: none;">
					结束倒计时: <div id="countDownEnd" style="display: none;"></div>
					<p id="noteEnd"></p>
			      </div>
			    </div>
		        <div class="row">
		          <div class="col-xs-12" style="background-color:#f5f5f5; height:10px;"></div>
		        </div> 
		        <div class="row">
		          <div class="col-xs-12" style="margin-top:10px; margin-bottom:10px;">
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
						<div role="presentation" class="active col-xs-12 col-md-12">
		                    <input type="button" class="btn btn-success btn-sm btn-block" id="myFeeBtn" name="myFeeBtn" value="创建订单"/>
						</div>
					</ul>
				</nav>
				
		        <%-- <c:if test="${subscribeFlag == true}">
		        <nav class="navbar navbar-default navbar-fixed-bottom">
					<!-- Nav tabs -->
					<ul class="nav nav-pills" role="tablist"
						style="text-align: center; padding-top: 5px">
						<div role="presentation" class="active col-xs-12">
		                    <input type="button" class="btn btn-success btn-sm btn-block" id="myFeeBtn" name="myFeeBtn" value="创建订单"/>
						</div>
					</ul>
				</nav>
		        </c:if> --%>
		        <%-- <c:if test="${subscribeFlag != true}">
		        <div class="row">
		          <div class="col-xs-12" style="margin-top:10px;">
		          	<span style="color: red;">为了确保您购买后能成功领取代金券，请先关注第三方开放平台“得味驿站”。您可长按识别二维码或微信扫描二维码关注。
		          	</span>
		          </div>
		          <div class="col-xs-12" style="">
		          	<div class="thumbnail">
				      <img id="qrcodeImg" src="<cmn:base/>/${wxAuthorizerInfoPOJO.qrcodeFilePath}" alt="">
				    </div>
		          </div>
		        </div>
		        </c:if> --%>
		        
             
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
        
        <!-- 显示公众号二维码 -->
	    <div class="modal fade" id="qrcodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="row">
		        <div class="col-md-1 col-sm-1 col-xs-1">
		        </div>
		        
				<div class="col-md-10 col-sm-10 col-xs-10">
				    	<div class="modal-dialog">
				    		<div class="modal-content">
				    			<div class="modal-header">
				    				<button type="button" class="close" data-dismiss="modal" aria-label="关闭">
				    					<span aria-hidden="true">&times;</span>
				    				</button>
				    				<h4 class="modal-title" id="myModalLabel">
					    				<span style="color: red; font-size: 12px;">为了确保您购买后能成功领取代金券，请先关注第三方开放平台“得味驿站”。您可长按识别二维码或微信扫描二维码关注。
			          				</span>
		          				</h4>
				    			</div>
				    			<div class="modal-body">
				    				<div class="thumbnail">
							      <img id="qrcodeImg" src="<cmn:base/>/${wxAuthorizerInfoPOJO.qrcodeFilePath}" alt="">
							    </div>
				    			</div><!-- modal-body -->
				    			<div class="modal-footer">
				    				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				    				<!-- <button type="button" class="btn btn-primary">创建</button> -->
				    			</div><!-- modal-footer -->
				    		</div><!-- /.modal-content -->
				    	</div><!-- /.modal-dialog -->
			    </div><!-- /.modal -->
			    
			    <div class="col-md-1 col-sm-1 col-xs-1">
		       </div>      
	      </div>
	</div>  
			
	        
  		
  		<div id="progress">数据加载中。。。</div>
  		
  	<%@include file="../../bottom.jsp"%>	
	</div>
  </body>
</html>
			  		
	     			
	     			
	     			
	     			
