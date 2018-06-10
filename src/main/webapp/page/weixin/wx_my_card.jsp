<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cobble.takeaway.util.CommonConstant" %>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<%@include file="../../page/common/head.jsp"%>

		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/progress_dialog.js"></script>
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/wx_js_sdk_init.js"></script>
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/wx_js_sdk_card.js"></script>
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/wx_my_card.js"></script>
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

	<%--WX CARD--%>
	<input type="hidden" id="shopIdCard" name="shopIdCard" value="${wxJsSdkConfigCardChoosePOJO.shopId}" />
	<input type="hidden" id="cardTypeCard" name="cardTypeCard" value="${wxJsSdkConfigCardChoosePOJO.cardType}" />
	<input type="hidden" id="cardIdCard" name="cardIdCard" value="${wxJsSdkConfigCardChoosePOJO.cardId}" />
	<input type="hidden" id="timestampCard" name="timestampCard" value="${wxJsSdkConfigCardChoosePOJO.timestamp}" />
	<input type="hidden" id="nonceStrCard" name="nonceStrCard" value="${wxJsSdkConfigCardChoosePOJO.nonceStr}" />
	<input type="hidden" id="signTypeCard" name="signTypeCard" value="${wxJsSdkConfigCardChoosePOJO.signType}" />
	<input type="hidden" id="cardSignCard" name="cardSignCard" value="${wxJsSdkConfigCardChoosePOJO.cardSign}" />

	<%--<input type="text" id="wxCards" name="wxCards" value="${e:forHtml(wxCards)}" />
	<c:out value="${e:forHtml(wxCards)}"/><br/>:
	<c:out value="${wxCards}"/>--%>

  	<!-- 调用微信支付js接口 -->
  	<%--<input type="hidden" id="appIdUo" name="appIdUo" />
  	<input type="hidden" id="timestampUo" name="timestampUo" />
  	<input type="hidden" id="nonceStrUo" name="nonceStrUo" />
  	<input type="hidden" id="prepayIdUo" name="prepayIdUo" />
  	<input type="hidden" id="paySignUo" name="paySignUo" />
  	<input type="hidden" id="packageUo" name="packageUo" />--%>

  	<!-- 公众号二维码 -->
  	<%--<input type="hidden" id="subscribeFlag" name="subscribeFlag" value="${subscribeFlag ? '1' : '0'}" />--%>

  	<div class="container-fluid" style="padding-bottom:50px;">
        <%--<div class="row">
        	<div class="col-md-12 col-sm-12">
&lt;%&ndash;         		<form id="myFeeForm" action="<cmn:base/>/web/ecommerce/ecorder/ecproduct/callwxpay" class="form-horizontal"> &ndash;%&gt;
        		<form id="myFeeForm" class="form-horizontal">
	        		<!-- 消费金额页面 -->
					<!-- authorizerAppId 从wxJsSdkConfigRespApiPOJO获取, 原来是param.authorizerAppId -->
	        		<input type="hidden" id="authorizerAppId" name="authorizerAppId" value="${wxJsSdkConfigRespApiPOJO.appId}">

		        &lt;%&ndash;<nav class="navbar navbar-default navbar-fixed-bottom">
					<!-- Nav tabs -->
					<ul class="nav nav-pills" role="tablist"
						style="text-align: center; padding-top: 5px">
						<div role="presentation" class="active col-xs-12 col-md-12">
							<input type="button" class="btn btn-success btn-sm btn-block" id="wxCardChooseBtn" name="wxCardChooseBtn" value="TestChoose"/>
							<input type="button" class="btn btn-success btn-sm btn-block" id="wxCardOpenBtn" name="wxCardOpenBtn" value="TestOpen"/>
						</div>
					</ul>
				</nav>&ndash;%&gt;

        		</form>
        		</div>
        </div>--%>

        <!-- 显示公众号二维码 -->
	    <%--<div class="modal fade" id="qrcodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
	</div>--%>



  		<div id="progress">数据加载中。。。</div>

  	<%@include file="../../bottom.jsp"%>
	</div>
  </body>
</html>





