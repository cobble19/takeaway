<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="common/head.jsp" %>
  </head>
  <body>
  <div class="main">
	<div class="wm-body">
			<div class="wm-body-right">
			<div class="wm-body-right-conent">
			  <div class="wm-body-right-conent-k">
			  <c:forEach items="${foodSellerPOJOList}" var="foodSellerPOJO">
			    <div class="wm-body-right-conent-k1">
			    	<a href='<cmn:base/>/web/foodSellerDetail?foodSellerId=${foodSellerPOJO.foodSellerId}'>
					    <div class="wmc22kc-t"><img alt="" src='<cmn:base/>/bootstrap/images/web2-new_14.jpg' ></div>
						<div class="wmc22kc-w">${foodSellerPOJO.name}</div>
					</a>
				</div>
			  </c:forEach>
			  </div>
			</div>
		</div>

	</div><!-- wm-body end -->
	</div>
  </body>
</html>