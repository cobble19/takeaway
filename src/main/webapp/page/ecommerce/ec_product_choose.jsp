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
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/wx_js_sdk_init.js"></script>
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/wx_js_pay_bridge.js"></script>
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/wx_js_sdk_card.js"></script>
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/ecommerce/ec_product_choose.js"></script>
	</head>

  <body style="padding-top:55px;">
  	<!-- WX JS SDK 有效 -->
  	<input type="hidden" id="appId" name="appId" value="${wxJsSdkConfigRespApiPOJO.appId}" />
  	<input type="hidden" id="timestamp" name="timestamp" value="${wxJsSdkConfigRespApiPOJO.timestamp}" />
  	<input type="hidden" id="nonceStr" name="nonceStr" value="${wxJsSdkConfigRespApiPOJO.nonceStr}" />
  	<input type="hidden" id="signature" name="signature" value="${wxJsSdkConfigRespApiPOJO.signature}" />
  	<input type="hidden" id="jsApiList" name="jsApiList" value="${wxJsSdkConfigRespApiPOJO.jsApiList}" />

  	<input type="hidden" id="url" name="url" value="${wxJsSdkConfigRespApiPOJO.url}" />
  	<input type="hidden" id="ticket" name="ticket" value="${wxJsSdkConfigRespApiPOJO.ticket}" />

	<%--WX CARD--%>
	<input type="hidden" id="shopIdCard" name="shopIdCard" value="${wxJsSdkConfigCardChoosePOJO.shopId}" />
	<input type="hidden" id="cardTypeCard" name="cardTypeCard" value="${wxJsSdkConfigCardChoosePOJO.cardType}" />
	<input type="hidden" id="cardIdCard" name="cardIdCard" value="${wxJsSdkConfigCardChoosePOJO.cardId}" />
	<input type="hidden" id="timestampCard" name="timestampCard" value="${wxJsSdkConfigCardChoosePOJO.timestamp}" />
	<input type="hidden" id="nonceStrCard" name="nonceStrCard" value="${wxJsSdkConfigCardChoosePOJO.nonceStr}" />
	<input type="hidden" id="signTypeCard" name="signTypeCard" value="${wxJsSdkConfigCardChoosePOJO.signType}" />
	<input type="hidden" id="cardSignCard" name="cardSignCard" value="${wxJsSdkConfigCardChoosePOJO.cardSign}" />

  	<!-- 调用微信支付js接口 -->
  	<input type="hidden" id="appIdUo" name="appIdUo" />
  	<input type="hidden" id="timestampUo" name="timestampUo" />
  	<input type="hidden" id="nonceStrUo" name="nonceStrUo" />
  	<input type="hidden" id="prepayIdUo" name="prepayIdUo" />
  	<input type="hidden" id="paySignUo" name="paySignUo" />
  	<input type="hidden" id="packageUo" name="packageUo" />

  	<!-- 公众号二维码 -->
  	<input type="hidden" id="subscribeFlag" name="subscribeFlag" value="${subscribeFlag ? '1' : '0'}" />

  	<div class="container-fluid" style="padding-bottom:200px;">
        <nav style="min-height:50px;" class="navbar navbar-default navbar-fixed-top">
            <div class="row" style="height:50px; padding:0px 15px;">
                <div class="col-xs-1 col-md-1" style="padding:0px;"><button onClick="javascript:window.history.back(-1);" class="btn btn-link pull-right" style="padding:15px 0px;" type="button"><span style="font-size:16px;" class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span></button></div>
                <div class="col-xs-10 col-md-10" style="padding:0px;"><p style="text-align:center;font-size:15px;line-height:50px;"><c:out value="${ecProductPOJO.productName}"></c:out></p></div>
                <div class="col-xs-1 col-md-1" style="padding:0px;">
                  <div class="dropdown">
                    <button class="btn btn-link dropdown-toggle pull-left" style="padding:15px 0px;" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span style="font-size:16px;" class="glyphicon glyphicon-option-horizontal" aria-hidden="true"></span></button>
                    <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1" style="min-width:80px;background:#000;margin:40px 5px;">
                      <li><button onClick="window.location.href='<cmn:base/>/web/ecommerce/ecwxcardactive?authorizerAppId=${wxJsSdkConfigRespApiPOJO.appId}'" class="btn btn-link" style="text-decoration:none;" type="button">
                               <span style="color:#FFF;" class="glyphicon glyphicon-home" aria-hidden="true"></span><span style="font-size:14px;color:#FFF;margin-left:10px;">首页</span></button></li>
                      <li style="margin:3px 0px 3px 36px; background-color:#666;" role="separator" class="divider"></li>         
                      <li class="disabled"><button onClick="" class="btn btn-link" style="text-decoration:none;" type="button">
                               <span style="color:#FFF;" class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span><span style="font-size:14px;color:#FFF;margin-left:10px;">购物车</span></button></li>
                      <li style="margin:3px 0px 3px 36px; background-color:#666;" role="separator" class="divider"></li>
                      <li><button class="btn btn-link" style="text-decoration:none;" type="button" id="wxCardChooseBtn" name="wxCardChooseBtn">
                               <span style="color:#FFF;" class="glyphicon glyphicon-credit-card" aria-hidden="true"></span><span style="font-size:14px;color:#FFF;margin-left:10px;">我的卡券</span></button></li>
                    </ul>
                  </div>
                </div>
            </div>
        </nav>
        <div class="row">
        	<div class="col-md-12 col-sm-12">
<%--         		<form id="myFeeForm" action="<cmn:base/>/web/ecommerce/ecorder/ecproduct/callwxpay" class="form-horizontal"> --%>
        		<form id="myFeeForm" class="form-horizontal">
	        		<!-- 消费金额页面 -->
					<!-- authorizerAppId 从wxJsSdkConfigRespApiPOJO获取, 原来是param.authorizerAppId -->
	        		<input type="hidden" id="authorizerAppId" name="authorizerAppId" value="${wxJsSdkConfigRespApiPOJO.appId}">
	        		<input type="hidden" id="productId" name="productId" value="${ecProductPOJO.productId}">
	        		<input type="hidden" id="unitPrice" name="unitPrice" value="${ecProductPOJO.unitPrice}">
					<input type="hidden" id="quantityStock" name="quantityStock" value="${ecProductPOJO.quantityStock}">
					<input type="hidden" id="wxCardStock" name="wxCardStock" value="${ecProductPOJO.wxCardStock}">
					<input type="hidden" id="limitNumEveryone" name="limitNumEveryone" value="${ecProductPOJO.limitNumEveryone}">
					<input type="hidden" id="limitNumDay" name="limitNumDay" value="${ecProductPOJO.limitNumDay}">
					<input type="hidden" id="startDateTime" name="startDateTime" value="${ecProductPOJO.startDateTime}">
					<input type="hidden" id="endDateTime" name="endDateTime" value="${ecProductPOJO.endDateTime}">
					<input type="hidden" id="wxCardId" name="wxCardId" value="${ecProductPOJO.wxCardId}">

					<input type="hidden" id="orderCount" name="orderCount" value="${orderCount}">
					<input type="hidden" id="orderCountToday" name="orderCountToday" value="${orderCountToday}">
					<input type="hidden" id="wxCardLimit" name="wxCardLimit" value="${wxCardLimit}">
					<input type="hidden" id="wxCardCount" name="wxCardCount" value="${wxCardCount}">

					<input type="hidden" id="homeUrl" name="homeUrl" value="${ecProductPOJO.homeUrl}">

	            <div class="row">
					<div class="col-xs-12" style="padding: 2px; text-align: center;">
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
                      <c:set var="finalStock" value="${ecProductPOJO.wxCardStock > ecProductPOJO.quantityStock ? ecProductPOJO.quantityStock : ecProductPOJO.wxCardStock}"></c:set>
					  <h6 style="color:#aaa9ae"> 库存 <c:out value="${finalStock}" /></h6>
			      </div>
                </div>
				<div class="row">
					<div class="col-xs-12" style="background-color:#f5f5f5; height:10px;"></div>
				</div>
				<div class="row">
					<div class="col-md-12 col-sm-12" style=" margin-top:10px;margin-bottom:5px;">
					 <c:if test="${ecProductPOJO.limitNumDay > finalStock}">
						 <c:set var="currentRemainNum" value="${finalStock}"></c:set>
					 </c:if>
					 <c:if test="${ecProductPOJO.limitNumDay <= finalStock}">
						 <c:set var="currentRemainNum" value="${ecProductPOJO.limitNumDay - orderCountToday}"></c:set>
					 </c:if>
				 		<h6 style=""> 每日商品销售总额上限 <strong style="color:#F00;"><c:out value="${ecProductPOJO.limitNumDay}" /></strong>, 今日目前剩余 <strong style="color:#F00;"><c:out value="${currentRemainNum}" /></strong> </h6>
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
		          <div class="col-xs-12" style=" margin:5px auto; padding-right:0px;"><h6 style=""> 该商品单人购买上限 <strong style="color:#F00;"><c:out value="${ecProductPOJO.limitNumEveryone}" /></strong>, 您已购买 <strong style="color:#F00;"><c:out value="${orderCount}" /></strong> </h6></div>
                  <div class="col-xs-4" style=" margin:5px auto; padding-right:0px;"><h6 style=" vertical-align:middle; line-height:30px;">请选择购买数量:</h6></div>
                  <div class="col-md-5 col-xs-5" style=" margin:5px auto;">
                           <div class="input-group input-group-sm">
                               <span class="input-group-btn">
                                    <button id="num-jian" class="btn btn-default" type="button">-</button>
                               </span>
                               <input style="text-align:center;" type="text" id="quantity" name="quantity" class="form-control" value="1" readonly>
                               <span class="input-group-btn">
                                    <button id="num-jia" class="btn btn-default" type="button">+</button>
                               </span>
                           </div>
			      </div>
		        </div>
		        <div class="row">
		          <div class="col-xs-12" style="background-color:#f5f5f5; height:10px;"></div>
		        </div>
		        <div class="row">
		          <div class="col-xs-12" style="margin-top:10px; margin-bottom:10px;">
					  <h5 style="font-weight:bold; margin-bottom:10px; color:#F00;">商品详情</h5>
			          <!--此处放详情数据-->
			          <span id="buyAbout" style="display:none;">
			          	<!-- 直接显示的话, 只能显示一段html文本, 不是正常的带有格式的内容 -->
			          	<c:out value="${ecProductPOJO.buyAbout}"></c:out>
			          </span>
			          <span id="buyAboutX">
			          </span>
		          </div>
		        </div>
		        <nav style="min-height:25px;" class="navbar navbar-default navbar-fixed-bottom">
					<!-- Nav tabs -->
					<ul class="nav nav-pills" role="tablist"
						style="">
                        <div role="presentation" class="active col-xs-5 col-md-5" style="margin:5px 0px;padding-left:5px; padding-right:5px;">
                        <div class="row">
                        <div class="col-xs-1 col-md-1" style="padding:0px;"></div>
                        <div class="col-xs-3 col-md-3" style="padding:0px;">
                           <button class="btn btn-link" style="color:#09F;padding:0px;text-decoration:none;" type="button" id="wxCardChooseBtn" name="wxCardChooseBtn">
                               <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                               <p style="font-size:7px;color:#666;">我的卡券
								   <%--<a style="color:#FFF;"
									  href='<cmn:base/>/web/weixin/wxmycard?authorizerAppId=${wxJsSdkConfigRespApiPOJO.appId}'
									  class="btn btn-primary" role="button">我的票券</a>--%>
								   </p>
                           </button>
                           </div>
                           <div class="col-xs-1 col-md-1" style="padding:0px;"><span style="color:#CCC;padding:0px;">|</span></div>
                           <div class="col-xs-3 col-md-3" style="padding:0px;">
                           <button onClick="window.location.href='<cmn:base/>/web/ecommerce/ecwxcardactive?authorizerAppId=${wxJsSdkConfigRespApiPOJO.appId}'" class="btn btn-link" style="color:#0C0;padding:0px;text-decoration:none;" type="button">
                               <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                               <p style="font-size:7px;color:#666;">卡券商城
								   <%--<a style="color:#FFF;"
									  href='<cmn:base/>/web/ecommerce/ecwxcardactive?authorizerAppId=${wxJsSdkConfigRespApiPOJO.appId}'
									  class="btn btn-primary" role="button">票券市场</a>--%>
								   </p>
                           </button>
                           </div>
                           <div class="col-xs-1 col-md-1" style="padding:0px;"><span style="color:#CCC;padding:0px;">|</span></div>
                           <div class="col-xs-3 col-md-3" style="padding:0px;">
                           <button onClick="window.location.href='${ecProductPOJO.homeUrl}'" class="btn btn-link" style="color:#F00;padding:0px;text-decoration:none;" type="button">
                               <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                               <p style="font-size:7px;color:#666;">店家详情
								   </p>
                           </button>
                           </div>
                           </div>
						</div>
						<div style="margin:5px 0px;padding-left:5px;padding-right:5px;" role="presentation" class="active col-xs-7 col-md-7">
		                    <input type="button" class="btn btn-warning btn-sm btn-block" id="myFeeBtn" name="myFeeBtn" value="创建订单"/>
						</div>
					</ul>
				</nav>

	        		<div class="row" style="display:none;">
	        				<div class="col-md-12 col-sm-12">
			        		<label>单价(元):</label>
			        		<c:out value="${ecProductPOJO.unitPrice / 100}" />
			        		</div>
	        				<!--<div class="col-md-12 col-sm-12" style="display: none;">
				        		<label>件数:</label>
				        		<input type="hidden" id="quantity" name="quantity" class="form-control" value="1">
			        		</div>-->
	        		</div>
	        		<div class="row" style="display:none;">
	        			<div class="col-md-12 col-sm-12">
			        		<label>费用总计:</label>
			        		<span id="feeTotal"></span>
	        			</div>
	        		</div>
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
										<span style="color: red; font-size: 16px; "><span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>&nbsp;注意</span>
			          				</span>
		          				</h4>
				    			</div>
				    			<div class="modal-body">
                                <span style="font-size: 12px;">该代金券购买成功后，由第三方开放平台“得味驿站”发放。<br/>为了确保您购买后能成功领取代金券，请先关注第三方开放平台“得味驿站”。<br/>您可长按识别二维码或微信扫描二维码关注。
			          				</span>
									<div class="thumbnail" style="margin-bottom:10px;">
										<img height="100" width="100" id="qrcodeImg" src="<cmn:base/>/${wxAuthorizerInfoPOJO.qrcodeFilePath}" alt="">
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





