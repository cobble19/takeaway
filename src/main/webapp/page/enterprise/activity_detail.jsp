<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/activity_detail.js"></script>
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/activity.js"></script>
    
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
  </head>
  <body>
  
	<div class="container">
		<%@include file="../../../reg_login.jsp" %>
		<div id="editDiv">
			<form class="form-horizontal" id="activityForm" role="form" action='<cmn:base/>/web/enterprise/activity/add' method="post">
	  			<input type="hidden" id="activityId" name="activityId" value="<%=request.getParameter("activityId") %>"/>
	 			<div class="form-group">
	 				<label class="control-label" for="titleE">主题:</label>
	 				<div class="">
	 					<input class="form-control" id="titleE" name="title" placeholder="请输入本次活动主题">
	 				</div>
	 			</div>
	 			<div class="form-group">
	 				<label class="control-label" for="contentE">活动介绍:</label>
	 				<div style="" class="">
	 					<!-- <textarea rows="5" cols="20" id="content" name="content" placeholder="请输入本次活动内容"></textarea>
	 					<input class="form-control" type="text" id="content" name="content" placeholder="请输入本次活动内容"> -->
	 					<script id="editor" type="text/plain" name="content"></script>
	 				</div>
	 			</div>
	 			<div class="form-group">
		 			<div class="">
		  				<button type="button" class="btn btn-default" id="addBtn">提交</button>
		  				<button type="button" class="btn btn-default" id="backBtn" >返回</button>
		 			</div>
	 			</div>
	  		</form>
		</div>
		<div id="showDiv">
			<div class="row">
				<div class="col-md-12" >
	            	<span style=" margin-top:50px; margin-bottom:50px; display:block;"><h3 id="title"></h3></span>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
	            	<h5 style=" margin-bottom:10px; display:block;">发布者:<span id="publisher"></span>
	            	<button class="btn btn-success pull-text" type="button" id="editBtn">修改</button>
				</h5>
				</div>
	        </div>	
	  		<div class="row uc-border">
	  			<div class="row">
	  				<div class="col-md-9">
	  					<!-- <h4 class="" style="text-align: bottom;"><span id="title"></span></h4>
	  					<hr/> -->
			  			<p style=" margin:10px; border-right:1px solid #CCC;" id="content"></p>
			  			<!-- for update -->
			  			<div style="" class="">
		 					<!-- <textarea rows="5" cols="20" id="content" name="content" placeholder="请输入本次活动内容"></textarea>
		 					<input class="form-control" type="text" id="content" name="content" placeholder="请输入本次活动内容"> -->
		 					<script id="editor" type="text/plain" name="content"></script>
		 				</div>
			  			<!-- <hr/> -->
	  				</div>
	  				<div class="col-md-3">
	  				<fieldset>
						<legend>申请活动</legend>
							<form id="applyForm" class="form-inline">
								
									<div class="form-group">
										<label for="username">姓名: </label> 
										<input id="username" name="username" required="required" value="" placeholder="姓名" class="form-control" />
									</div>
									<div class="form-group">
										<label for="phone">手机号: </label> 
										<input type="tel" id="phone" name="phone"  required="required" value="" placeholder="手机号" class="form-control" />
									</div>
									<input type="button" id="applyBtn" value="申请参加"
										class="btn btn-default" />
								
							</form>
					</fieldset>
					</div>
	  			</div>
	  		</div>
		</div><!-- for show -->
  		
		<footer><br/><p>&copy; 版权所有</p></footer>
	</div> <!-- container -->
	
  </body>
</html>
