<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<%@include file="../../page/common/head.jsp"%>

		<%--<link href="<cmn:base/>/css/unified/ec_cart_my.css" rel="stylesheet">--%>
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/ec_cart_my.js"></script>
	</head>
	
  <body style="padding-top: 100px;">
  	<%@include file="../../reg_login.jsp" %>
  	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12" id="ec_cart_my">
				<c:if test="${empty ecCartPOJOs}">
					购物车是空的哦
				</c:if>
				<ul class="list-group">
					<c:forEach var="ecCartPOJO" items="${ecCartPOJOs}" varStatus="st">
						<li class="list-group-item list-group-item-info">
								<a class="btn-link" href="<cmn:base/>/web/ecommerce/ecorder/ecproduct/choose?productId=${ecCartPOJO.productId}&authorizerAppId=${ecCartPOJO.authorizerAppId}">${ecCartPOJO.productName}</a>
								<button id="deleteMyEcCartBtn" name="deleteMyEcCartBtn" ecCartId="${ecCartPOJO.ecCartId}" class="btn btn-info">删除</button>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>


  	<%@include file="../../bottom.jsp"%>
	</div>
  </body>
</html>
	     			
	     			
