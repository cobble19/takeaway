<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/ecommerce/ec_wx_card_active.js"></script>
  </head>
  <body>
  
	<div class="container">
		<%-- <%@include file="../../../reg_login_full.jsp" %> --%>
        <div class="row" style="margin-bottom:15px; margin-top:15px;"><h4 style="text-align:center; border-bottom:1px #06F solid; padding-bottom:5px; color:#06F;">微信卡券活动</h4></div>
		<c:forEach items="${ecProductPOJOs}" var="ecProductPOJO" varStatus="st">
			<div class="row">
               <div class="col-xs-12 col-md-4">
                 <div class="thumbnail">
                    <img src="<cmn:base/>/${ecProductPOJO.imgUrl}" alt="${ecProductPOJO.productName}">
                    <div class="caption">
                       <h4>${ecProductPOJO.productName} 现价: ${ecProductPOJO.unitPrice / 100}元</h4><br/>
                       <p><a style="color:#FFF;"
                             href='<cmn:base/>/web/ecommerce/ecorder/ecproduct/choose?productId=${ecProductPOJO.productId}&authorizerAppId=${ecProductPOJO.authorizerAppId}'
                             class="btn btn-primary" role="button">点击参与卡券活动</a></p>
                    </div>
                 </div>
               </div>
            </div>    
		</c:forEach>

        <!--<div class="row" style="background-color:#f1f1f1;">
            <p style="text-align:center; margin-bottom:10px; margin-top:20px; font-size:12px; line-height:12px;">2016 合肥交通广播</p>
        </div>-->
        <div class="row" style="background-color:#333;">
            <p style="text-align:center; margin-bottom:5px; margin-top:5px; font-size:12px; line-height:12px; color:#ffffff;">技术支持:得味驿站</p>
        </div>
	  		
	</div> <!-- container -->
  </body>
</html>

