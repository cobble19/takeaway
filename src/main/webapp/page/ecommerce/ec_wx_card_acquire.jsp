<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/wx_head.jsp" %>

      <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/wx_js_sdk_init.js"></script>
      <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/wx_js_sdk_card.js"></script>
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/ecommerce/ec_wx_card_acquire.js"></script>
  </head>
  <body style="background-color:#f6f8f9;">
      <!-- WX JS SDK 有效 -->
      <input type="hidden" id="appId" name="appId" value="${wxJsSdkConfigRespApiPOJO.appId}" />
      <input type="hidden" id="timestamp" name="timestamp" value="${wxJsSdkConfigRespApiPOJO.timestamp}" />
      <input type="hidden" id="nonceStr" name="nonceStr" value="${wxJsSdkConfigRespApiPOJO.nonceStr}" />
      <input type="hidden" id="signature" name="signature" value="${wxJsSdkConfigRespApiPOJO.signature}" />
      <input type="hidden" id="jsApiList" name="jsApiList" value="${wxJsSdkConfigRespApiPOJO.jsApiList}" />

      <input type="hidden" id="url" name="url" value="${wxJsSdkConfigRespApiPOJO.url}" />
      <input type="hidden" id="ticket" name="ticket" value="${wxJsSdkConfigRespApiPOJO.ticket}" />

      <%--WX CARD--%>
      <%--<input type="hidden" id="shopIdCard" name="shopIdCard" value="${wxJsSdkConfigCardChoosePOJO.shopId}" />--%>
      <%--<input type="hidden" id="cardTypeCard" name="cardTypeCard" value="${wxJsSdkConfigCardChoosePOJO.cardType}" />--%>
      <%--<input type="hidden" id="cardIdCard" name="cardIdCard" value="${wxJsSdkConfigCardChoosePOJO.cardId}" />--%>
      <%--<input type="hidden" id="timestampCard" name="timestampCard" value="${wxJsSdkConfigCardChoosePOJO.timestamp}" />--%>
      <%--<input type="hidden" id="nonceStrCard" name="nonceStrCard" value="${wxJsSdkConfigCardChoosePOJO.nonceStr}" />--%>
      <%--<input type="hidden" id="signTypeCard" name="signTypeCard" value="${wxJsSdkConfigCardChoosePOJO.signType}" />--%>
      <%--<input type="hidden" id="cardSignCard" name="cardSignCard" value="${wxJsSdkConfigCardChoosePOJO.cardSign}" />--%>
      <%--jssdk WX CARD ADD, should be needed, will removed--%>
      <%--<input type="text" id="wxJsSdkCardAddCardListJson" value="${wxJsSdkCardAddCardListJson}">--%>
  
  

    <div class="weui-cells">
    <c:forEach items="${ecWxCardPOJOs}" var="ecWxCardPOJO" varStatus="st">
      <a name="wxCardAcquireBtn" ecWxCardId="${ecWxCardPOJO.ecWxCardId}" style="min-height:80px;" class="weui-cell weui-cell_access">
        <div class="weui-cell__bd">
          <p>${ecWxCardPOJO.ecOrderPOJO.ecProductPOJO.productName}</p>
        </div>
        <div class="weui-cell__ft">点击领取</div>
      </a>
    </c:forEach>
    </div> 


  
  
  
  
  
  
  
  
  
  

	<!--<div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <ul>
                    <c:forEach items="${ecWxCardPOJOs}" var="ecWxCardPOJO" varStatus="st">
                        <li><span>${ecWxCardPOJO.ecOrderPOJO.ecProductPOJO.productName}</span>
                            <button name="wxCardAcquireBtn" ecWxCardId="${ecWxCardPOJO.ecWxCardId}">获取卡券</button>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </div>
	</div>-> <!-- container -->
  </body>
</html>
        <!--<div class="row" style="padding:6px 5px;;">
        <div class="col-xs-4 col-md-4"><a id="wxCardAddBtn" name="wxCardAddBtn" class="center-block" style="height:35px; width:35px;" href="#"><img class="img-circle" src="<cmn:base/>/images/cardicon1.jpg" alt="获取卡券"></a><p style="text-align:center; font-size:10px; font-weight:bold; line-height:20px;">获取卡券</p></div>
        <div class="col-xs-4 col-md-4"><a class="center-block" style="height:35px; width:35px;" href=''><img class="img-circle" src="<cmn:base/>/images/cardicon2.jpg" alt="购物车"></a><p style="text-align:center; font-size:10px; font-weight:bold; line-height:20px;">购物车</p></div>
        <div class="col-xs-4 col-md-4"><a href="" class="center-block" style="height:35px; width:35px;" href=''><img class="img-circle" src="<cmn:base/>/images/cardicon3.jpg" alt="使用须知"></a><p style="text-align:center; font-size:10px; font-weight:bold; line-height:20px;">使用须知</p></div>
        </div>-->
