<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="page/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@include file="page/common/head.jsp"%>
	<%-- <jsp:include page="page/common/head_index.jsp"></jsp:include> --%>
	<!-- only for index page -->
	<script src="<cmn:base/>/js/ui.core.js" type="text/javascript"></script>
	<script src="<cmn:base/>/js/ui.tabs.js" type="text/javascript"></script>
</head>
<body style="padding-top: 100px;">
	<security:authentication property="principal.username" var="username" />
	<%@include file="reg_login.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>
				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item active" style="background: #0099cc;">
						<img src='<cmn:base/>/images/index_1.jpg' class="img-responsive center-block" alt="">
						<div class="carousel-caption"></div>
					</div>
					<div class="item" style="background: #0099cc;">
						<img src="<cmn:base/>/images/index_2.jpg" class="img-responsive center-block" alt="">
						<div class="carousel-caption"></div>
					</div>
				</div>
				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"> 
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> 
				<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next"> 
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
		<div class="row" style="border-top:10px solid #CCC; background:#f4faf6;"> 
			<img src="<cmn:base/>/images/index_3.jpg" class="img-responsive center-block" alt=""> 
		</div>
	</div>
	<%@include file="bottom.jsp"%>
</body>
</html>
