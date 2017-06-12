<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../common/head.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<!-- 隐藏 -->
		<div id="qrcodeDiv" class="col-md-12 col-xs-12"
			style="border: 1px solid #CCC; display: none; margin: auto;">
			<div class="thumbnail">
				<div class="caption">
					<h3>请通过长按识别二维码或微信扫描二维码关注公众号</h3>
					<p></p>
				</div>
				<img id="qrcodeImg" src="" alt="">
			</div>
		</div>
		<!-- 隐藏 -->
		<div class="row">
			<div class="col-xs-12"
				style="padding: 0px; text-align: center; margin-bottom: 15px;">
				<img src="http://www.deweiyizhan.com/files/images/22_-1_0_.jpg">
			</div>
		</div>

		<nav class="navbar navbar-default navbar-fixed-bottom">
			<!-- Nav tabs -->
			<ul class="nav nav-pills" role="tablist"
				style="text-align: center; padding-top: 5px">
				<div role="presentation" class="active col-xs-3">
					<a href="#home" style="color: #F00; font-size: 14px;"
						aria-controls="home" role="tab" data-toggle="tab"><span
						style="font-size: 24px;" class="glyphicon glyphicon-home"
						aria-hidden="true"></span>
						<p>首页</p></a>
				</div>
				<div role="presentation" class="col-xs-3">
					<a href="#profile" style="color: #F00; font-size: 14px;"
						aria-controls="profile" role="tab" data-toggle="tab"><span
						style="font-size: 24px;" class="glyphicon glyphicon-file"
						aria-hidden="true"></span>
						<p>规则</p></a>
				</div>
				<div role="presentation" class="col-xs-3">
					<a href="#messages" style="color: #F00; font-size: 14px;"
						aria-controls="messages" role="tab" data-toggle="tab"><span
						style="font-size: 24px;" class="glyphicon glyphicon-pencil"
						aria-hidden="true"></span>
						<p>报名</p></a>
				</div>
				<div role="presentation" class="col-xs-3">
					<a href="#search" style="color: #F00; font-size: 14px;"
						aria-controls="search" role="tab" data-toggle="tab"><span
						style="font-size: 24px;" class="glyphicon glyphicon-search"
						aria-hidden="true"></span>
						<p>查询</p></a>
				</div>
			</ul>
		</nav>



		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="home">
				<div class="row" style="margin-top: 15px;">
					<div class="media"
						style="border: #F00 solid 1px; margin: 0px 5px; padding: 10px;">
						<div class="media-left">
							<a href="#"> <img style="max-width: none !important;"
								class="media-object"
								src="http://www.deweiyizhan.com/files/images/22_-1_2_.gif"
								alt="" height="64" width="64">
							</a>
						</div>
						<div class="media-body">
							<h5 class="media-heading">No.1:奋斗的东少</h5>
							<h5>姓名:曾昭东</h5>
							<h5>口号:你的电台为你发声！</h5>
							<br />
							<h5 style="text-align: right;">
								<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>&nbsp;9999
							</h5>
						</div>
					</div>
					<div class="media"
						style="border: #F00 solid 1px; margin: 0px 5px; padding: 10px;">
						<div class="media-left">
							<a href="#"> <img style="max-width: none !important;"
								class="media-object"
								src="http://www.deweiyizhan.com/files/images/22_-1_2_.gif"
								alt="" height="64" width="64">
							</a>
						</div>
						<div class="media-body">
							<h5 class="media-heading">No.2:奋斗的东少</h5>
							<h5>姓名:曾昭东</h5>
							<h5>口号:你的电台为你发声！</h5>
							<br />
							<h5 style="text-align: right;">
								<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>&nbsp;9999
							</h5>
						</div>
					</div>
					<div class="media"
						style="border: #F00 solid 1px; margin: 0px 5px; padding: 10px;">
						<div class="media-left">
							<a href="#"> <img style="max-width: none !important;"
								class="media-object"
								src="http://www.deweiyizhan.com/files/images/22_-1_2_.gif"
								alt="" height="64" width="64">
							</a>
						</div>
						<div class="media-body">
							<h5 class="media-heading">No.3:奋斗的东少</h5>
							<h5>姓名:曾昭东</h5>
							<h5>口号:你的电台为你发声！</h5>
							<br />
							<h5 style="text-align: right;">
								<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>&nbsp;9999
							</h5>
						</div>
					</div>
				</div>
			</div>
			<div role="tabpanel" class="tab-pane" id="profile">...</div>
			<div role="tabpanel" class="tab-pane" id="messages">...</div>
			<div role="tabpanel" class="tab-pane" id="search">
				<div class="row">
					<form class="form-inline">
						<div class="form-group">
							<label class="sr-only" for="search">搜索</label> <input
								style="width: auto; margin-left: 5px; float: left;" type="text"
								class="form-control" placeholder="请输入编号">
							<button style="margin-left: 5px; float: left;" type="submit"
								class="btn btn-default">搜索</button>
						</div>

					</form>
				</div>
			</div>
		</div>

		<div class="row" style="height: 70px;"></div>
	</div>

	<!-- container -->
</body>
</html>
