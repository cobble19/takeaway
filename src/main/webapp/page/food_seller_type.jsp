<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="common/head.jsp" %>
  </head>
  <body>
	<div class="list-type">
	        <div class="list-type-item1"></div>
		<c:forEach items="${foodSellerTypePOJOList }" var="foodSellerTypePOJO">
			<div class="list-type-item">
				<a title="${foodSellerTypePOJO.name}" href='<cmn:base/>/web/foodSellers?sellerTypeId=${foodSellerTypePOJO.foodSellerTypeId}'>
				</a>
			</div>
		</c:forEach>
	</div>
  </body>
</html>