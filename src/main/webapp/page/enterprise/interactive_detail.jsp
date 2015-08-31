<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/interactive_detail.js"></script>
    
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
  </head>
  <body>
  
	<div class="container">
		<%@include file="../../../reg_login_full.jsp" %>
		<div id="showDiv">
			<div class="row">
				<div class="col-md-12" >
	            	<span style=" margin-top:50px; margin-bottom:50px; display:block;"><h3 id="title"></h3></span>
	            	<input type="hidden" id="interactiveId" name="interactiveId" value="<%=request.getParameter("interactiveId") %>"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
	            	<h5 style=" margin-bottom:10px; display:block;">发布者:<span id="publisher"></span>
				</h5>
				</div>
	        </div>	
	  		<div class="row uc-border">
	  			<div class="row" style="margin-top: 20px; margin-left: 20px; margin-bottom: 20px;">
	  				<div class="col-md-9 col-xs-12" style=" border-right:1px solid #CCC; ">
	  					<!-- <h4 class="" style="text-align: bottom;"><span id="title"></span></h4>
	  					<hr/> -->
			  			<p><span style="border-bottom: 1px solid #ccc;">活动内容</span><span id="content"></span></p>
			  			<p><span style="border-bottom: 1px solid #ccc; margin-top: 15px;">活动规则</span><span id="rule"></span></p>
			  			<!-- for update -->
			  			<div style="" class="row col-md-12">
		 					<!-- <textarea rows="5" cols="20" id="content" name="content" placeholder="请输入本次活动内容"></textarea>
		 					<input class="form-control" type="text" id="content" name="content" placeholder="请输入本次活动内容"> -->
		 					<script id="editor" type="text/plain" name="content"></script>
		 				</div>
			  			<!-- <hr/> -->
			  			
							<div class="row col-md-12" style="margin-top: 50px;">
			  				<fieldset>
								<legend>你的答案</legend>
									<form id="applyForm" class="form-inline">
											<div class="form-group">
												<label for="usernameX" class="control-label">答案: </label>
												<input type="number" id="answer" name="answer" minlength="1" required="required" value="" placeholder="答案" class="form-control" />
											</div>
											<!-- <div class="form-group">
												<label for="phone" class="control-label">手机: </label>
												<input type="tel" id="phone" name="phone" minlength="3"  required="required" value="" placeholder="请填写手机号码" class="form-control" />
											</div>
											<div class="form-group">
												<label for="sex" class="control-label">性别: </label>
					<label class="radio-inline"><input type="radio" id="sexM" name="sex" value="M" class="" />男</label>
					<label class="radio-inline"><input type="radio" id="sexF" name="sex" value="F" class="" />女</label>
											</div>
											<div class="form-group">
												<label for="description" class="control-label">备注: </label> 
												<textarea id="description" name="description" rows="3" cols="" value="" placeholder="备注为选填项" class="form-control" ></textarea>
											</div> -->
											<div class="form-group">
												<input type="button" id="applyBtn" value="提交" class="btn btn-default" />
											</div>
									</form>
							</fieldset>	
						</div>
	  				</div>
	  				<div class="col-md-3 col-xs-12">
	  					<div id="interactiveApplySortor" class="row col-md-12" style="">
	  						<h2>活动排名</h2>
	  						<div id="interactiveApplyContent">
	  						</div>
	  					</div>
					</div>
	  			</div>
	  		</div>
		</div><!-- for show -->
	<%@include file="../../../bottom.jsp" %>  		
	</div> <!-- container -->
  </body>
</html>

