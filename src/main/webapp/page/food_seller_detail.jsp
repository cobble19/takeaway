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
    <title>餐厅</title>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<cmn:base/>/jquery/jquery-1.11.1.min.js"></script>
    <script src="<cmn:base/>/jquery/jquery-migrate-1.2.1.min.js"></script>
    <!-- Bootstrap -->
    <link href="<cmn:base/>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<cmn:base/>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <style>
		table td{
			border: 0px;
			border-top-width: 0px !important;
			border-bottom-width: 0px !important;
		}
	</style>
  </head>
  <body>
    <!-- <h1>Hello, world!</h1> -->
    <nav class="navbar navbar-default" role="navigation">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">缩放</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="#">主页</a>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="#">注册</a></li>
	        <li><a href="#">登录</a></li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="../web/foodSellerType"></jsp:include>
				<br/>
				<jsp:include page="../web/tree"></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<div class="row" style="background-color: #C0C0C0; height: 50px; padding: 10px 0;">
							<label for="search">搜索：    </label>
							<input id="search" type="text" placeholder="搜索">
							<div class="pull-right" style="vertical-align: middle;">评分最高 | 速度最快 | 起送价格最低</div>
						</div>
						<br/>
						<div class="row">
							您所在位置>>合肥市>>${foodSellerPOJO.locationAreaPOJO.name }>>${foodSellerPOJO.locationBusinessPOJO.name }
						</div>
						<br/>
						<div class="row">
						</div>
						<div class="row">
							<div class="media">
								<a class="media-left pull-left" href="#">
									<img alt="" src="<cmn:base/>/images/fbb.jpg" style="width:80px;height: 80px;">
								</a>
								<div class="media-body" style="padding-left: 30px;">
									<h3 class="media-heading">${foodSellerPOJO.name}</h3>
									<h4>
										<img alt="Tel" src="<cmn:base/>/images/fbb.jpg" style="width:20px;height: 20px; ">
										<span>${foodSellerPOJO.phone}</span>	
									</h4>
									<br/>
									<span>
										查看菜单 | 查看地图 | 营业时间：${foodSellerPOJO.businessHours}<br/>
										送餐范围：${foodSellerPOJO.deliveryArea}<br/>
										外卖起送价格：${foodSellerPOJO.deliveryPriceMin}元<br/>
										商家地址：${foodSellerPOJO.address}<br/>
										提示：${foodSellerPOJO.note}<br/>
									</span>
								</div>
							</div>
							<br/>
							<c:forEach items="${foodSellerPOJO.foodTypePOJOs}" var="foodTypePOJO">
							<table class="table table-hover">
								<thead style="color: #AABBCC">
									<th colspan="4">${foodTypePOJO.name}</th>
								</thead>
								<c:forEach items="${foodTypePOJO.foodPOJOs}" var="foodPOJO" varStatus="st">
								<c:if test="${st.index / 2 == 0}">
									<tr>
								</c:if>
									<td style="width: 25%; text-align: left;">${foodPOJO.name }</td><td style="width: 25%; text-align: center;">${foodPOJO.unitPrice}元</td>
									<c:if test="${st.index / 2 == 0 && st.isLast()}"> <!-- 增加2个td， 不然在一行就2个td -->
										<td></td><td></td>
									</c:if>	
								<c:if test="${st.index / 2 == 1 || st.isLast()}">
								</tr>
								</c:if>
								</c:forEach>
							</table>
							</c:forEach>
							
							<div>
								<h2>地图查询</h2>
								<p>mapppppppppppppp</p>
							</div>
						</div> <!-- row -->
					</div>
					<div class="col-md-1"></div>
				</div>
			</div>
		</div> <!-- row end -->
	</div>
	<footer><hr><p>&copy; 版权所有</p></footer>
  </body>
</html>