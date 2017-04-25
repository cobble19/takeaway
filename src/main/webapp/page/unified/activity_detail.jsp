<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/activity_detail.js"></script>
    
  </head>
  <body>
  
	<div class="container-fluid">
		<div class="hidden-xs">
			<%@include file="../../../reg_login.jsp" %>
		</div>
		
		<div class="row hidden-xs" style="height:150px;"></div>
			<!-- 隐藏 -->
 			<div id="qrcodeDiv" class="col-md-12 col-xs-12" style="border:1px solid #CCC; display:none; margin: auto;">
 				<div class="thumbnail">
			      <div class="caption">
			        <h3>请通过长按识别二维码或微信扫描二维码关注公众号</h3>
			        <p></p>
			      </div>
			      <img id="qrcodeImg" src="" alt="">
			    </div>
			</div>
		
		<div id="showDiv">
			<div class="row">
			  <div class="col-md-12 col-xs-12" >
	            	<span style=" margin-top:20px; margin-bottom:20px; display:block;"><h3 id="title"></h3></span>                    
	            	<input type="hidden" id="activityId" name="activityId" value="<%=request.getParameter("activityId") %>"/>
	            	<!-- <input type="hidden" id="authorizerAppId" name="authorizerAppId"/> -->
			  </div>
            </div>
            <!--<div class="row visible-xs">
                <div class="col-xs-12">
                   <img src="" alt="" id="logoImg">
                </div>
            </div>-->
			<div class="row">
				<div class="col-md-12">
	            	<h5 style="margin-bottom:20px; display:block;float: left;margin-right:10px; color:#898989;"><span class="h5" id="createDateTime"></span></h5>
            	  	<h5 style=" margin-bottom:20px; display:block; float: left; color:#88a8c1;"><span class="h5" id="publisher"></span></h5>
	            	<!--<h5 style="margin-bottom:10px; display:block;">组织者:<span class="h5" id="organiser"></span></h5>-->
				</div>
	        </div>
			<div class="row" style="margin-top:10px;">
  			  	<% 
  			  		String hidContent = request.getParameter("hidContent");
  			  		if (!"1".equalsIgnoreCase(hidContent)) {
  			  	%>
	  				<div class="col-md-12 col-xs-12">
	  					<!-- <h4 class="" style="text-align: bottom;"><span id="title"></span></h4>
	  					<hr/> -->
			  			<p id="content"></p>
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
                    </div>
                    <div class="row" style="margin-left:2px; margin-right:2px;">
                    <div id="lineSplit" class="col-md-12 col-xs-12" style="margin-top:30px; border-bottom:5px ridge #ccc;"></div>
                    <div id="errorMsg4Activity" class="col-md-12 col-xs-12" 
                    	style="border:1px solid #CCC; padding-top:10px; margin-bottom:10px; font-size: xx-large; text-align: center;">
                    	<span style="color: red; text-align: center;"></span>
                    </div>
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
		</div><!-- for show -->
	<%@include file="../../../bottom.jsp" %>  		
	</div> <!-- container -->
  </body>
</html>
