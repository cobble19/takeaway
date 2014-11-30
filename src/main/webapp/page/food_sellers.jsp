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
			<div class="col-md-9">
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<div class="row" style="background-color: #C0C0C0; height: 50px; padding: 10px 0;">
							<form action='<cmn:base/>/web/foodSellers/search'>
								<label for="search">搜索：    </label>
								<input id="keyword" name="keyword" type="text" placeholder="搜索" value="${param.keyword}">
								<div class="pull-right" style="vertical-align: middle;">评分最高 | 速度最快 | 起送价格最低</div>
							</form>
						</div>
						<br/>
						<div class="row">
							<!-- 您所在位置>>合肥市>>庐阳区>>长江西路 -->
						</div>
						<br/>
						<div class="row">
							<c:forEach items="${foodSellerPOJOList}" var="foodSellerPOJO">
								<div class="col-md-4">
									<div class="thumbnail">
										<img alt="" src='<cmn:base/>/images/fbb.jpg'
											style="width: 300px; height: 300px;">
										<div class="caption">
											<h3>${foodSellerPOJO.name}</h3>
										</div>
									</div>
								</div>
							</c:forEach>
							<%-- <div class="col-md-4">
								<div class="thumbnail">
									<img alt="" src='<cmn:base/>/images/fbb.jpg'
										style="width: 300px; height: 300px;">
									<div class="caption">
										<h3>美女范冰冰</h3>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="thumbnail">
									<img alt="" src='<cmn:base/>/images/fbb.jpg'
										style="width: 300px; height: 300px;">
									<div class="caption">
										<h3>美女范冰冰</h3>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="thumbnail">
									<img alt="" src='<cmn:base/>/images/fbb.jpg'
										style="width: 300px; height: 300px;">
									<div class="caption">
										<h3>美女范冰冰</h3>
									</div>
								</div>
							</div> --%>
						</div>
					</div>
					<div class="col-md-1"></div>
				</div>
			</div>
		</div> <!-- row end -->
	</div>
	<footer><hr><p>&copy; 版权所有</p></footer>
  </body>
</html>