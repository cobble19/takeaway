<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/wx_head.jsp" %>

      <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/wx_js_sdk_init.js"></script>
      <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/weixin/wx_js_sdk_card.js"></script>
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/ecommerce/ec_wx_card_active.js"></script>
  </head>
  <body style="padding-top:75px;">
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
  
	<div class="container-fluid">
		<%-- <%@include file="../../../reg_login_full.jsp" %> --%>
        <nav style="min-height:75px;" class="navbar navbar-default navbar-fixed-top">
            <div class="row" style="background-color:#F03;height:75px; padding:0px 10px;">
                <div class="col-xs-12 col-md-12" style="color:#fff;"><h4 style="text-align:center;line-height:40px; font-weight:bold; font-family:'幼圆';">卡券商城</h4></div>
                
                <div class="col-xs-12 col-md-12">
                <div class="form-group has-success has-feedback" style="margin-bottom:0px;">
                <span class="glyphicon glyphicon-search form-control-feedback" aria-hidden="true"></span>
                <span id="inputSuccess2Status" class="sr-only">(success)</span>
                <input type="text" class="form-control input-sm" placeholder="Search for..." disabled>
                </div>
                </div>
            </div>
        </nav>
        <div class="row" style="padding:6px 5px;;">
        <%--<div class="col-xs-4 col-md-4"><a class="center-block" style="height:35px; width:35px;" href="http://www.deweiyizhan.com/web/weixin/wxmycard?authorizerAppId=wxe0037de41e16f816"><img class="img-circle" src="<cmn:base/>/images/cardicon1.jpg" alt="我的卡券"></a><p style="text-align:center; font-size:10px; font-weight:bold; line-height:20px;">我的卡券</p></div>--%>
        <div class="col-xs-4 col-md-4"><a id="wxCardChooseBtn" name="wxCardChooseBtn" class="center-block" style="height:35px; width:35px;" href="#"><img class="img-circle" src="<cmn:base/>/images/cardicon1.jpg" alt="我的卡券"></a><p style="text-align:center; font-size:10px; font-weight:bold; line-height:20px;">我的卡券</p></div>
        <div class="col-xs-4 col-md-4"><a class="center-block" style="height:35px; width:35px;" href=''><img class="img-circle" src="<cmn:base/>/images/cardicon2.jpg" alt="购物车"></a><p style="text-align:center; font-size:10px; font-weight:bold; line-height:20px;">购物车</p></div>
        <div class="col-xs-4 col-md-4"><a href="https://mp.weixin.qq.com/s?__biz=MzA5NzU2ODk1OQ==&mid=100000051&idx=1&sn=0a5006cfb5b8c23b986732779960a8e5&scene=19#wechat_redirect" class="center-block" style="height:35px; width:35px;" href=''><img class="img-circle" src="<cmn:base/>/images/cardicon3.jpg" alt="使用须知"></a><p style="text-align:center; font-size:10px; font-weight:bold; line-height:20px;">使用须知</p></div>
        </div>
        <!--滚动图片开始-->
        <div class="row" style="margin:0px -3px 20px -3px; box-shadow:5px 2px 6px #999;">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">

  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>


  <div class="carousel-inner" role="listbox">
  <c:forEach items="${ecProductPOJOs}" var="ecProductPOJO" begin="0" end="0" varStatus="st">
    <div class="item active">
      <a href='<cmn:base/>/web/ecommerce/ecorder/ecproduct/choose?productId=${ecProductPOJO.productId}&authorizerAppId=${ecProductPOJO.authorizerAppId}'><img src="<cmn:base/>/${ecProductPOJO.imgUrl}" alt=""></a>
      <div class="carousel-caption"></div>
    </div>
  </c:forEach>
  <c:forEach items="${ecProductPOJOs}" var="ecProductPOJO" begin="1" varStatus="st">
    <div class="item">
      <a href='<cmn:base/>/web/ecommerce/ecorder/ecproduct/choose?productId=${ecProductPOJO.productId}&authorizerAppId=${ecProductPOJO.authorizerAppId}'><img src="<cmn:base/>/${ecProductPOJO.imgUrl}" alt=""></a>
      <div class="carousel-caption"></div>
    </div>
  </c:forEach>  
  </div>


  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</div>
        <!--滚动图片结束-->
        <div class="row" style="margin-top:10px; margin-bottom:10px;">
            <div class="col-xs-4 col-md-4" style="border-bottom:1px solid #CCC; height:8px;"></div>
            <div class="col-xs-4 col-md-4"><h5 style="color:#CCC; text-align:center;">为您推荐</h5></div>
            <div class="col-xs-4 col-md-4" style="border-bottom:1px solid #CCC; height:8px;"></div>
        </div>
        <div class="panel panel-info" style=" box-shadow:5px 2px 6px #999; border:5px solid #bce8f1;">
        <div class="panel-heading" style="font-size:14px; font-weight:bold;">超值半价</div>
        <div class="panel-body">
		<c:forEach items="${ecProductPOJOs}" var="ecProductPOJO" varStatus="st">
			<div class="row" style="margin:0px 1px 15px 1px; box-shadow:0px 0px 4px #0CC;">
               <div class="col-xs-12 col-md-12"  style="padding:0px;">
                    <a href='<cmn:base/>/web/ecommerce/ecorder/ecproduct/choose?productId=${ecProductPOJO.productId}&authorizerAppId=${ecProductPOJO.authorizerAppId}'>
                    <img src="<cmn:base/>/${ecProductPOJO.imgUrl}" alt="${ecProductPOJO.productName}"></a>
               </div>
                    <div style="background-color:#FBFBFB;">
                       <!--<h4>${ecProductPOJO.productName} 现价: ${ecProductPOJO.unitPrice / 100}元</h4><br/>-->
                       <button style="text-decoration:none; padding:2px 5px; font-size:14px; font-weight:bold;" type="button" class="btn btn-link" onClick="window.location.href='<cmn:base/>/web/ecommerce/ecorder/ecproduct/choose?productId=${ecProductPOJO.productId}&authorizerAppId=${ecProductPOJO.authorizerAppId}'">${ecProductPOJO.productName}</button>
                    </div>
                    <div style="padding-bottom:3px; padding-left:5px;background-color:#FBFBFB;"><h5 style="color:#F00;">￥ ${ecProductPOJO.unitPrice / 100}</h5></div>
            </div>    
		</c:forEach>
        </div>
        </div>

        <!--<div class="row" style="background-color:#f1f1f1;">
            <p style="text-align:center; margin-bottom:10px; margin-top:20px; font-size:12px; line-height:12px;">2016 合肥交通广播</p>
        </div>
        <div class="row" style="background-color:#333;">
            <p style="text-align:center; margin-bottom:5px; margin-top:5px; font-size:12px; line-height:12px; color:#ffffff;">技术支持:得味驿站</p>
        </div>-->
	  		
	</div> <!-- container -->
  </body>
</html>

