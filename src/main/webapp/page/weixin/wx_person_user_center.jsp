<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
  
    <%@include file="../common/wx_head.jsp" %>

	  <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/wx_js_sdk_init.js"></script>
	  <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/wx_js_sdk_card.js"></script>
     <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/wx_person_user_center.js"></script>
     <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/weui/swiper.js"></script>
    
    <style type="text/css">
		.head-img {
			/* width: 20px;
			height: 20px; */
			/* margin-right: 5px; */
			display: block;
			-moz-border-radius: 30px; /* Gecko browsers */
			-webkit-border-radius: 30px; /* Webkit browsers */
			border-radius: 30px; /* W3C syntax */
		}
	</style>
    
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
	  <input type="hidden" id="shopIdCard" name="shopIdCard" value="${wxJsSdkConfigCardChoosePOJO.shopId}" />
	  <input type="hidden" id="cardTypeCard" name="cardTypeCard" value="${wxJsSdkConfigCardChoosePOJO.cardType}" />
	  <input type="hidden" id="cardIdCard" name="cardIdCard" value="${wxJsSdkConfigCardChoosePOJO.cardId}" />
	  <input type="hidden" id="timestampCard" name="timestampCard" value="${wxJsSdkConfigCardChoosePOJO.timestamp}" />
	  <input type="hidden" id="nonceStrCard" name="nonceStrCard" value="${wxJsSdkConfigCardChoosePOJO.nonceStr}" />
	  <input type="hidden" id="signTypeCard" name="signTypeCard" value="${wxJsSdkConfigCardChoosePOJO.signType}" />
	  <input type="hidden" id="cardSignCard" name="cardSignCard" value="${wxJsSdkConfigCardChoosePOJO.cardSign}" />
  
  	<div class="weui-panel weui-panel_access">
	  <div class="weui-panel_bd">
	    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
	      <div class="weui-media-box__hd">
	        <img class="weui-media-box__thumb head-img" src="${wxUserInfoApiPOJO.headImgUrl}" alt="">
	      </div>
	      <div class="weui-media-box__bd">
	        <h4 class="weui-media-box__title"><c:out value="${wxUserInfoApiPOJO.nickname}"></c:out></h4>
	        <p class="weui-media-box__desc"><c:out value="${wxUserInfoApiPOJO.city}"></c:out></p>
	      </div>
	    </a>
	  </div>
  	</div>
  	
  	<%-- <div class="weui_cells">
	  <div class="weui_cell" >
	    <div class="weui_cell_hd">
	      <img src="${wxUserInfoApiPOJO.headImgUrl}" alt="icon" title="头像" class="head-img" style="">
	    </div>
	    <div class="weui_cell_bd weui_cell_primary">
	      <p><c:out value="${wxUserInfoApiPOJO.nickname}"></c:out></p>
	    </div>
	    <div class="weui_cell_ft">
	      
	    </div>
	  </div>
	</div> --%>

  	<div class="weui-cells">
	  <a style="min-height:60px;" id="wxCardChooseBtn" name="wxCardChooseBtn" class="weui-cell weui-cell_access" href="#">
	    <div class="weui-cell__hd"><img src="<cmn:base/>/images/PersonalCenter_kqb.png"></div>
        <div class="weui-cell__bd">
	      <p>卡券包</p>
	    </div>
        <div class="weui-cell__ft"></div>
	  </a>
	  <a style="min-height:60px;" class="weui-cell weui-cell_access" href="http://www.deweiyizhan.com/web/ecommerce/ecorder/ecwxcardacquire">
	    <div class="weui-cell__hd"><img src="<cmn:base/>/images/PersonalCenter_qjx.png"></div>
        <div class="weui-cell__bd">
	      <p>取件箱</p>
	    </div>
        <div class="weui-cell__ft"></div>
	  </a>
	  <a style="min-height:60px;" class="weui-cell weui-cell_access" href="http://www.deweiyizhan.com/web/unified/ecCart/eccartmy">
	    <div class="weui-cell__hd"><img src="<cmn:base/>/images/PersonalCenter_scj.png"></div>
        <div class="weui-cell__bd">
	      <p>收藏夹</p>
	    </div>
        <div class="weui-cell__ft"></div>
	  </a>
	</div>
	
  	<!--<div class="weui_cells weui_cells_access">
	  <a class="weui_cell" href="javascript:;">
	    <div class="weui_cell_bd weui_cell_primary">
	      <p style="font-size:12px;">得味中心</p>
	    </div>
        <div class="weui_cell_ft"></div>
	  </a>
	</div> -->
	
  </body>
</html>

