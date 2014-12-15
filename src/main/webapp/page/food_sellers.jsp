<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cmn" uri="/WEB-INF/tlds/common.tld" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>餐厅列表</title>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<cmn:base/>/jquery/jquery-1.11.1.min.js"></script>
    <script src="<cmn:base/>/jquery/jquery-migrate-1.2.1.min.js"></script>
    <!-- Bootstrap -->
    <link href="<cmn:base/>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<cmn:base/>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
	<link href="<cmn:base/>/bootstrap/css/wm.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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
				    <div class="wmc22kc-t"><a href="#"><img alt="" src='<cmn:base/>/bootstrap/images/web2-new_14.jpg' ></a></div>
					<div class="wmc22kc-w"><a href="#">${foodSellerPOJO.name}</a></div>
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