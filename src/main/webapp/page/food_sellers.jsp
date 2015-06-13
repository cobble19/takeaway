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
    <div class="wm-head"></div>
	<div class="wm-body">
			<div class="wm-body-left">
				<%-- <c:redirect url="<cmn:base/>/web/foodSellerType"></c:redirect> --%>
				<jsp:include page="../web/foodSellerType"></jsp:include>
				<!-- <ul class="list-group">
					<li class="list-group-item">
						<img alt="图标" src="" title="图标"></img>
						中式快餐
					</li>
					<li class="list-group-item">西式快餐</li>
					<li class="list-group-item">甜点</li>
				</ul> -->
				<br/>
				<jsp:include page="../web/tree"></jsp:include>
			</div>
			<div class="wm-body-right">
		    <div class="wm-body-right-s">
			<form action='<cmn:base/>/web/foodSellers/search'>
								<label style="float:left; line-height:47px; margin-left:30px; font-size:14px;" for="search">快速搜索：    </label>
								<div class="search-1"></div>
								<input class="search-2" id="keyword" name="keyword" type="text" placeholder="搜索" value="${param.keyword}">
								<div style="vertical-align: middle; float:left; margin-left:85px;line-height:47px;">评分最高 | 速度最快 | 起送价格最低</div>
							</form>
			</div>
			<div class="wm-body-right-wz"><div class="wm-body-right-wz-text">您所在位置：</div></div>
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
	<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
  </body>
</html>