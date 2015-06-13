<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="page/common/head.jsp" %>
  </head>
  <body>
    <!-- <h1>Hello, world!</h1> -->
	<div class="container">
		<div class="row">
			<c:forEach items="${foodSellerPOJOList}" var="foodSellerPOJO" varStatus="st">
				<div class="col-md-4">
					<div class="panel panel-default">
						<!-- <div class="panel-heading">
						</div> -->
						<div class="panel-body">
							<span class="ta-name"><a href="<cmn:base/>/web/foodSeller/${foodSellerPOJO.foodSellerId}">${foodSellerPOJO.name}</a></span>
							<%-- <span class="ta-tag">${tag}</span>
							<span class="ta-star">${star}</span>
							<span class="ta-count">${amount}</span> --%>
						</div>
						<div class="panel-footer">
							<span>预计送餐时间：</span>
						</div>
					</div>
				</div>
			</c:forEach>
		</div> <!-- row end -->
	</div>
  </body>
</html>