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
<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/weui/swiper.js"></script>
<style>
body {
	-webkit-overflow-scrolling: touch;
	overflow-scrolling: touch;
	overflow-y: scroll;
}
.weui-media-box_appmsg .weui-media-box__new {
	margin-right:.8em; 
	width:100px; 
	height:80px; 
	line-height:80px; 
	text-align:center;
}
.weui-flex {
	flex-wrap: wrap;
}
.weui-flex__item {
	display: flex;
	justify-content: center;
	flex-basis: 50%;
}
.weui-media-box_Special {
	display:flex;
	flex-direction: column;
	justify-content: center;
}
.weui-media-box_Specialimg {
	width:150px;
	height:120px;
	line-height:120px;
	text-align:left;
}
.weui-media-box_Specialtext {
	width:150px;
	height:auto;
	text-align:left;
}
.weui-media-box__Specialphoto {
	width: 100%;
	max-height: 100%;
}
.weui-media-box__Specialtitle {
	margin-top:5px;
	font-size:14px;
	color:#000;
}
.weui-media-box__Specialmoney {
	color:#F60;
	font-family:'幼圆';
	font-weight:bold;
	padding-bottom:8px;
	border-bottom:1px rgba(229, 229, 229, 1) solid;
}
</style>
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
<div class="weui-tab">
  <div class="weui-tab__bd">
    <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active" style="padding-bottom:100px;">
      <!--页面一内容-->
      <div class="weui-search-bar" id="searchBar">
        <form class="weui-search-bar__form">
          <div class="weui-search-bar__box"> <i class="weui-icon-search"></i>
            <input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索" required="">
            <a href="javascript:" class="weui-icon-clear" id="searchClear"></a> </div>
          <label class="weui-search-bar__label" id="searchText"> <i class="weui-icon-search"></i> <span>搜索</span> </label>
        </form>
        <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a> </div>
      <!--页面一滚动-->
      <div class="swiper-container" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
        <div class="swiper-wrapper">
          <div class="swiper-slide"><a><img src="http://www.deweiyizhan.com/files/images/dwyz/16_null_null_20180426160701.754.hWhge.png" alt=""></a></div>
          <div class="swiper-slide"><a><img src="http://www.deweiyizhan.com/files/images/dwyz/16_null_null_20180613235541.189.ioMkQ.jpg" alt=""></a></div>
          <div class="swiper-slide"><a><img src="http://www.deweiyizhan.com/files/images/dwyz/16_null_null_20180613235636.108.jKnXv.png" alt=""></a></div>
        </div>
      </div>
      <!--页面一图标-->
      <!--<div class="weui-flex" style="margin-top:20px;">
       <div class="weui-flex__item"><a id="wxCardChooseBtn" name="wxCardChooseBtn" class="center-block" style="height:35px; width:35px;" href="#"><img class="img-circle" src="<cmn:base/>/images/cardicon1.jpg" alt="我的卡券"></a><p style="text-align:center; font-size:10px; font-weight:bold; line-height:20px;">我的卡券</p></div>
         <div class="weui-flex__item"><a class="center-block" style="height:35px; width:35px;" href=''><img class="img-circle" src="<cmn:base/>/images/cardicon2.jpg" alt="购物车"></a><p style="text-align:center; font-size:10px; font-weight:bold; line-height:20px;">购物车</p></div>
         <div class="weui-flex__item"><a href="https://mp.weixin.qq.com/s?__biz=MzA5NzU2ODk1OQ==&mid=100000051&idx=1&sn=0a5006cfb5b8c23b986732779960a8e5&scene=19#wechat_redirect" class="center-block" style="height:35px; width:35px;" href=''><img class="img-circle" src="<cmn:base/>/images/cardicon3.jpg" alt="使用须知"></a><p style="text-align:center; font-size:10px; font-weight:bold; line-height:20px;">使用须知</p></div>
      </div>-->
      <div class="weui-flex" style="margin:40px 20px 0px 20px; font-weight:bold; font-size:20px;">新品推荐</div>
      <div class="weui-flex">
        <div class="weui-flex__item" style="border-bottom:1px solid #ccc; height:8px; margin:0 20px;"></div>
      </div>
      <div class="weui-flex">
        <div class="weui-flex__item">
          <div class="weui-panel weui-panel_access">
            <div class="weui-panel__bd"> <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
              <div class="weui-media-box__new"> <img class="weui-media-box__thumb" src="http://www.deweiyizhan.com/files/images/dwyz/16_null_null_20190131234542.399.vDQGH.jpg"> </div>
              <div class="weui-media-box__bd">
                <h4 class="weui-media-box__title">标题一<span style="float:right; font-size:10px; border:1px #ccc solid; border-radius:5px; padding:2px;">3月19日23：59 结束</span></h4>
                <p class="weui-media-box__desc" style="margin-top:5px;">由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。</p>
                <div style=" margin-top:10px;">
                  <h5 style="color:#F60; font-family:'幼圆'; font-weight:bold;">￥ ${ecProductPOJO.unitPrice / 100}</h5>
                </div>
              </div>
              </a> <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
              <div class="weui-media-box__new"> <img class="weui-media-box__thumb" src="http://www.deweiyizhan.com/files/images/dwyz/16_null_null_20180613235541.189.ioMkQ.jpg"> </div>
              <div class="weui-media-box__bd">
                <h4 class="weui-media-box__title">标题二</h4>
                <p class="weui-media-box__desc">由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。</p>
                <div style=" margin-top:10px;">
                  <h5 style="color:#F00;">￥ ${ecProductPOJO.unitPrice / 100}</h5>
                </div>
              </div>
              </a> </div>
          </div>
        </div>
      </div>
      <div class="weui-flex" style="margin:40px 20px 0px 20px; font-weight:bold; font-size:20px;">特价专区</div>
      <div class="weui-flex">
        <div class="weui-flex__item" style="border-bottom:1px solid #ccc; height:8px; margin:0 20px;"></div>
      </div>
      <div class="weui-flex">
        <div class="weui-flex__item"> <a href="javascript:void(0);" class="weui-media-box weui-media-box_Special">
          <div class="weui-media-box_Specialimg"> <img class="weui-media-box__Specialphoto" src="http://www.deweiyizhan.com/files/images/dwyz/16_null_null_20190131234542.399.vDQGH.jpg"> </div>
          <div class="weui-media-box_Specialtext">
            <h4 class="weui-media-box__Specialtitle">标题一</h4>
            <div style=" margin-top:10px;">
              <h5 class="weui-media-box__Specialmoney">￥ ${ecProductPOJO.unitPrice / 100}</h5>
            </div>
          </div>
          </a> </div>
        <div class="weui-flex__item"> <a href="javascript:void(0);" class="weui-media-box weui-media-box_Special">
          <div class="weui-media-box_Specialimg"> <img class="weui-media-box__Specialphoto" src="http://www.deweiyizhan.com/files/images/dwyz/16_null_null_20190131234542.399.vDQGH.jpg"> </div>
          <div class="weui-media-box_Specialtext">
            <h4 class="weui-media-box__Specialtitle">标题一</h4>
            <div style=" margin-top:10px;">
              <h5 class="weui-media-box__Specialmoney">￥ ${ecProductPOJO.unitPrice / 100}</h5>
            </div>
          </div>
          </a> </div>
        <div class="weui-flex__item"> <a href="javascript:void(0);" class="weui-media-box weui-media-box_Special">
          <div class="weui-media-box_Specialimg"> <img class="weui-media-box__Specialphoto" src="http://www.deweiyizhan.com/files/images/dwyz/16_null_null_20190131234542.399.vDQGH.jpg"> </div>
          <div class="weui-media-box_Specialtext">
            <h4 class="weui-media-box__Specialtitle">标题一</h4>
            <div style=" margin-top:10px;">
              <h5 class="weui-media-box__Specialmoney">￥ ${ecProductPOJO.unitPrice / 100}</h5>
            </div>
          </div>
          </a> </div>
        <div class="weui-flex__item"> <a href="javascript:void(0);" class="weui-media-box weui-media-box_Special">
          <div class="weui-media-box_Specialimg"> <img class="weui-media-box__Specialphoto" src="http://www.deweiyizhan.com/files/images/dwyz/16_null_null_20190131234542.399.vDQGH.jpg"> </div>
          <div class="weui-media-box_Specialtext">
            <h4 class="weui-media-box__Specialtitle">标题一</h4>
            <div style=" margin-top:10px;">
              <h5 class="weui-media-box__Specialmoney">￥ ${ecProductPOJO.unitPrice / 100}</h5>
            </div>
          </div>
          </a> </div>
      </div>
      <div class="weui-flex" style="margin:40px 20px 0px 20px; font-weight:bold; font-size:20px;">免费专区</div>
      <div class="weui-flex">
        <div class="weui-flex__item" style="border-bottom:1px solid #ccc; height:8px; margin:0 20px;"></div>
      </div>
      <div class="weui-flex">
        <div class="weui-flex__item"> <a href="javascript:void(0);" class="weui-media-box weui-media-box_Special">
          <div class="weui-media-box_Specialimg"> <img class="weui-media-box__Specialphoto" src="http://www.deweiyizhan.com/files/images/dwyz/16_null_null_20190131234542.399.vDQGH.jpg"> </div>
          <div class="weui-media-box_Specialtext">
            <h4 class="weui-media-box__Specialtitle">标题一</h4>
            <div style=" margin-top:10px;">
              <h5 class="weui-media-box__Specialmoney">￥ ${ecProductPOJO.unitPrice / 100}</h5>
            </div>
          </div>
          </a> </div>
        <div class="weui-flex__item"> <a href="javascript:void(0);" class="weui-media-box weui-media-box_Special">
          <div class="weui-media-box_Specialimg"> <img class="weui-media-box__Specialphoto" src="http://www.deweiyizhan.com/files/images/dwyz/16_null_null_20190131234542.399.vDQGH.jpg"> </div>
          <div class="weui-media-box_Specialtext">
            <h4 class="weui-media-box__Specialtitle">标题一</h4>
            <div style=" margin-top:10px;">
              <h5 class="weui-media-box__Specialmoney">￥ ${ecProductPOJO.unitPrice / 100}</h5>
            </div>
          </div>
          </a> </div>
        <div class="weui-flex__item"> <a href="javascript:void(0);" class="weui-media-box weui-media-box_Special">
          <div class="weui-media-box_Specialimg"> <img class="weui-media-box__Specialphoto" src="http://www.deweiyizhan.com/files/images/dwyz/16_null_null_20190131234542.399.vDQGH.jpg"> </div>
          <div class="weui-media-box_Specialtext">
            <h4 class="weui-media-box__Specialtitle">标题一</h4>
            <div style=" margin-top:10px;">
              <h5 class="weui-media-box__Specialmoney">￥ ${ecProductPOJO.unitPrice / 100}</h5>
            </div>
          </div>
          </a> </div>
        <div class="weui-flex__item"> <a href="javascript:void(0);" class="weui-media-box weui-media-box_Special">
          <div class="weui-media-box_Specialimg"> <img class="weui-media-box__Specialphoto" src="http://www.deweiyizhan.com/files/images/dwyz/16_null_null_20190131234542.399.vDQGH.jpg"> </div>
          <div class="weui-media-box_Specialtext">
            <h4 class="weui-media-box__Specialtitle">标题一</h4>
            <div style=" margin-top:10px;">
              <h5 class="weui-media-box__Specialmoney">￥ ${ecProductPOJO.unitPrice / 100}</h5>
            </div>
          </div>
          </a> </div>
      </div>
    </div>
    <!--页面一内容结束-->
    <!--<div id="tab2" class="weui-tab__bd-item">
      <h1>页面二</h1>
    </div>-->
    <div id="tab3" class="weui-tab__bd-item">
      <h1>页面三</h1>
    </div>
    <div id="tab4" class="weui-tab__bd-item">
      <%@include file="../weixin/wx_person_user_center.jsp" %> 
    </div>
  </div>
  <div class="weui-tabbar" style="padding-bottom:10px;"> <a href="#tab1" class="weui-tabbar__item weui-bar__item--on">
    <div class="weui-tabbar__icon"> <img src="<cmn:base/>/images/home.png" alt=""> </div>
    <p class="weui-tabbar__label">首页</p>
    </a>
    <!--<a href="#tab2" class="weui-tabbar__item">
      <div class="weui-tabbar__icon">
        <img src="<cmn:base/>/images/search.png" alt="">
      </div>
      <p class="weui-tabbar__label">专场特卖</p>
    </a>-->
    <a href="#tab3" class="weui-tabbar__item">
    <div class="weui-tabbar__icon"> <img src="<cmn:base/>/images/ticket.png" alt=""> </div>
    <p class="weui-tabbar__label">我的卡券</p>
    </a> <a href="#tab4" class="weui-tabbar__item">
    <div class="weui-tabbar__icon"> <img src="<cmn:base/>/images/person.png" alt=""> </div>
    <p class="weui-tabbar__label">个人中心</p>
    </a> </div>
</div>
<div class="container-fluid" style=" display:none;">
  <%-- <%@include file="../../../reg_login_full.jsp" %> --%>
  <div class="row" style="padding:6px 5px;;">
    <%--<div class="col-xs-4 col-md-4"><a class="center-block" style="height:35px; width:35px;" href="http://www.deweiyizhan.com/web/weixin/wxmycard?authorizerAppId=wxe0037de41e16f816"><img class="img-circle" src="<cmn:base/>/images/cardicon1.jpg" alt="我的卡券"></a><p style="text-align:center; font-size:10px; font-weight:bold; line-height:20px;">我的卡券</p></div>--%>
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
          <div class="item active"> <a href='<cmn:base/>/web/ecommerce/ecorder/ecproduct/choose?productId=${ecProductPOJO.productId}&authorizerAppId=${ecProductPOJO.authorizerAppId}'><img src="<cmn:base/>/${ecProductPOJO.imgUrl}" alt=""></a>
            <div class="carousel-caption"></div>
          </div>
        </c:forEach>
        <c:forEach items="${ecProductPOJOs}" var="ecProductPOJO" begin="1" varStatus="st">
          <div class="item"> <a href='<cmn:base/>/web/ecommerce/ecorder/ecproduct/choose?productId=${ecProductPOJO.productId}&authorizerAppId=${ecProductPOJO.authorizerAppId}'><img src="<cmn:base/>/${ecProductPOJO.imgUrl}" alt=""></a>
            <div class="carousel-caption"></div>
          </div>
        </c:forEach>
      </div>
    </div>
  </div>
  <!--滚动图片结束-->
  <div class="panel panel-info">
    <div class="panel-body">
      <c:forEach items="${ecProductPOJOs}" var="ecProductPOJO" varStatus="st">
        <div class="row" style="margin:0px 1px 15px 1px; ">
          <div class="col-xs-12 col-md-12"  style="padding:0px;"> <a href='<cmn:base/>/web/ecommerce/ecorder/ecproduct/choose?productId=${ecProductPOJO.productId}&authorizerAppId=${ecProductPOJO.authorizerAppId}'> <img src="<cmn:base/>/${ecProductPOJO.imgUrl}" alt="${ecProductPOJO.productName}"></a> </div>
          <div style="background-color:#FBFBFB;">
            <!--<h4>${ecProductPOJO.productName} 现价: ${ecProductPOJO.unitPrice / 100}元</h4><br/>-->
            <button style="text-decoration:none; padding:2px 5px; font-size:14px; font-weight:bold;" type="button" class="btn btn-link" onClick="window.location.href='<cmn:base/>/web/ecommerce/ecorder/ecproduct/choose?productId=${ecProductPOJO.productId}&authorizerAppId=${ecProductPOJO.authorizerAppId}'">${ecProductPOJO.productName}</button>
          </div>
          <div style="padding-bottom:3px; padding-left:5px;background-color:#FBFBFB;">
            <h5 style="color:#F00;">￥ ${ecProductPOJO.unitPrice / 100}</h5>
          </div>
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
</div>
<!-- container -->
<script>
      $(".swiper-container").swiper({
        loop: true,
        autoplay: 2000
      });
    </script>
</body>
</html>
