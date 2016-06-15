<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/activity_detail.js"></script>
    
  </head>
  <body>
  
	<div class="container">
		<div class="hidden-xs">
			<%@include file="../../../reg_login_full.jsp" %>
		</div>
		
		<div id="showDiv">
			<div class="row">
			  <div class="col-md-12 col-xs-12" >
	            	<span class=" hidden-xs" style=" margin-top:50px; margin-bottom:50px; display:block;"><h3 id="title"></h3></span>
	            	<span class="visible-xs" style="margin-left:5px; margin-right:5px; margin-top:10px; margin-bottom:10px; display:block; text-align:center;"><h4 id="title_1"></h4></span>                    
	            	<input type="hidden" id="activityId" name="activityId" value="<%=request.getParameter("activityId") %>"/>
	            	<input type="hidden" id="wxAuthorizerAppId" name="wxAuthorizerAppId"/>
			  </div>
            </div>
            <div class="row visible-xs">
                <div class="col-xs-12">
                   <img src="" alt="" id="logoImg">
                </div>
            </div>
			<div class="row hidden-xs">
				<div class="col-md-12">
            	  <h5 style=" margin-bottom:10px; display:block; float: left;margin-right: 20px;">发布者:<span class="h5" id="publisher"></span></h5>
	            	<h5 style="margin-bottom:10px; display:block;">组织者:<span class="h5" id="organiser"></span></h5>
				</div>
	        </div>
			<div class="row" style=" margin-top:10px; margin-left:5px; margin-right:5px;">
  			  <div class="row">
  			  	<% 
  			  		String hidContent = request.getParameter("hidContent");
  			  		if (!"1".equalsIgnoreCase(hidContent)) {
  			  	%>
	  				<div class="col-md-12 col-xs-12">
	  					<!-- <h4 class="" style="text-align: bottom;"><span id="title"></span></h4>
	  					<hr/> -->
			  			<p style=" margin:10px;" id="content"></p>
			  			<!-- for update -->
			  			<div style="" class="">
		 					<!-- <textarea rows="5" cols="20" id="content" name="content" placeholder="请输入本次活动内容"></textarea>
		 					<input class="form-control" type="text" id="content" name="content" placeholder="请输入本次活动内容"> -->
		 					<script id="editor" type="text/plain" name="content"></script>
		 				</div>
			  			<!-- <hr/> -->
	  				</div>
	  				<%
  			  		}
	  				%>
                    <div class="col-md-12 col-xs-12" style="margin-top:30px;border-bottom:5px ridge #ccc;"></div>
	  				<div id="apply2Div" class="col-md-12 col-xs-12" style="border:1px solid #CCC; padding-top:10px; margin-bottom:10px;">
		  				<div class="row col-md-12 col-xs-12">
			  				<fieldset>
									<form id="apply2Form" class="form-horizontal">
											<!-- <div class="form-group">
												<label for="usernameX" class="control-label h5">姓名: </label>
												<input id="usernameX" name="username" minlength="2" required="required" value="" placeholder="请填写姓名" class="form-control" />
											</div> -->
											<!-- <div class="form-group">
												<label for="phone" class="control-label h5">手机: </label>
												<input type="tel" id="phone" name="phone" minlength="3"  required="required" value="" placeholder="请填写手机号码" class="form-control" />
											</div>
											<div class="form-group">
												<label for="sex" class="control-label h5">性别: </label>
												<label class="radio-inline"><input type="radio" id="sexM" name="sex" value="M" class="" />男</label>
												<label class="radio-inline"><input type="radio" id="sexF" name="sex" value="F" class="" />女</label>
											</div>
											<div class="form-group">
												<label for="description" class="control-label h5">备注: </label> 
												<textarea id="description" name="description" rows="3" cols="18" value="" placeholder="备注为选填项" class="form-control"  style="margin-bottom:5px;"></textarea>
											</div> -->
											<div class="form-group">
                                                <div class="col-md-12">
												<input type="button" id="apply2Btn" value="提交" class="btn btn-default" />
                                                </div>
											</div>
									</form>
							</fieldset>	
	  					</div>
					</div>
					<!-- 隐藏 -->
	  				<div id="applyDiv" class="col-md-12 col-xs-12" style="border:1px solid #CCC; padding-top:10px; margin-bottom:10px; display:none;">
		  				<div class="row col-md-12 col-xs-12">
			  				<fieldset>
									<form id="applyForm" class="form-inline">
											<div class="form-group">
												<label for="usernameX" class="control-label h5">姓名: </label>
												<input id="usernameX" name="username" minlength="2" required="required" value="" placeholder="请填写姓名" class="form-control" />
											</div>
											<div class="form-group">
												<label for="phone" class="control-label h5">手机: </label>
												<input type="tel" id="phone" name="phone" minlength="3"  required="required" value="" placeholder="请填写手机号码" class="form-control" />
											</div>
											<div class="form-group">
												<label for="sex" class="control-label h5">性别: </label>
												<label class="radio-inline"><input type="radio" id="sexM" name="sex" value="M" class="" />男</label>
												<label class="radio-inline"><input type="radio" id="sexF" name="sex" value="F" class="" />女</label>
											</div>
											<div class="form-group">
												<label for="description" class="control-label h5">备注: </label> 
												<textarea id="description" name="description" rows="3" cols="18" value="" placeholder="备注为选填项" class="form-control"  style="margin-bottom:5px;"></textarea>
											</div>
											<div class="form-group">
												<input type="button" id="applyBtn" value="提交" class="btn btn-default" />
											</div>
									</form>
							</fieldset>	
	  					</div>
					</div>
	  			</div>
	  		</div>
		</div><!-- for show -->
	<%@include file="../../../bottom.jsp" %>  		
	</div> <!-- container -->
  </body>
</html>

