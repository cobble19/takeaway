<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="common/head.jsp" %>
  </head>
  <body>
    <!-- <h1>Hello, world!</h1> -->
	<div class="container">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<span>食物</span>
				</div>
				<%-- <div class="panel-body">
					<span class="ta-name"><a href="<cmn:base/>/web/foodSeller/${foodSellerPOJO.foodSellerId}">${foodSellerPOJO.name}</a></span>
					<span class="ta-tag">${tag}</span>
					<span class="ta-star">${star}</span>
					<span class="ta-count">${amount}</span>
				</div>
				<div class="panel-footer">
					<span>预计送餐时间：</span>
				</div> --%>
				<table class="table">
					<!-- <thead>
						<tr>
							<th>名称</th>
							
						</tr>
					</thead> -->
					<tbody>
						<c:forEach items="${foodPOJOList}" var="foodPOJO" varStatus="st">
							<tr>
								<td>${foodPOJO.name}</td>
								<td>${foodPOJO.unitPrice}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div> <!-- row end -->
	</div>
  </body>
</html>