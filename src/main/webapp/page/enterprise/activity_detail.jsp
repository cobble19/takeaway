<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/activity_detail.js"></script>
    
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
  </head>
  <body>
  
	<div class="container">
		<%@include file="../../../reg_login_full.jsp" %>
		<div id="showDiv">
			<div class="row">
			  <div class="col-md-12 col-xs-12" >
	            	<span class=" hidden-xs" style=" margin-top:50px; margin-bottom:50px; display:block;"><h3 id="title"></h3></span>
	            	<span class="visible-xs" style="margin-left:5px; margin-right:5px; margin-top:10px; margin-bottom:10px; display:block; text-align:center;"><h4 id="title_1"></h4></span>                    
	            	<input type="hidden" id="activityId" name="activityId" value="<%=request.getParameter("activityId") %>"/>
			  </div>
            </div>
			<div class="row hidden-xs">
				<div class="col-md-12">
            	  <h5 style=" margin-bottom:10px; display:block; float: left;margin-right: 20px;">发布者:<span class="h5" id="publisher"></span></h5>
	            	<h5 style="margin-bottom:10px; display:block;">组织者:<span class="h5" id="organiser"></span></h5>
				</div>
	        </div>
            <div class="row">
            <div class="modal fade" id="contentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    	<div class="modal-dialog">
	    		<div class="modal-content">
	    			<div class="modal-header">
	    				<button type="button" class="close" data-dismiss="modal" aria-label="关闭">
	    					<span aria-hidden="true">&times;</span>
	    				</button>
	    				<h4 class="modal-title" id="myModalLabel"><p id="title_2"></p></h4>
	    			</div>
	    			<div class="modal-body">
                      <p id="content_1"></p>
                    </div><!-- modal-body -->
	    			<div class="modal-footer">
	    				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	    				<!-- <button type="button" class="btn btn-primary">创建</button> -->
	    			</div><!-- modal-footer -->
	    		</div>
	    	</div>
	        </div>
            </div>
      <div class="row visible-xs" style="margin-left:5px; margin-right:5px;padding-top:10px; padding-bottom:10px;">
                <div class="col-xs-12">
                    <button type="button" class="btn btn-default btn-block" data-toggle="modal" data-target="#contentModal"><h5>查看活动详情</h5></button>
                    <a class="btn btn-default btn-block" href="#" role="button"><h5>发布者【<span class="h5" id="publisher_1"></span>】</h5></a>
                    <a class="btn btn-default btn-block" href="#" role="button"><h5>组织者【<span class="h5" id="organiser_1"></span>】</h5></a>
                </div>
            </div>	
	  		<div class="row uc-border" style=" margin-top:10px; margin-left:5px; margin-right:5px;">
	  			<div class="row">
	  				<div class="col-md-9 hidden-xs">
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
	  				<div class="col-md-3 col-xs-12">
		  				<div class="row col-md-12 col-xs-offset-1 col-xs-10">
			  				<fieldset>
								<legend>申请活动</legend>
									<form id="applyForm" class="form-inline">
											<div class="form-group">
												<label for="usernameX" class="control-label">姓名: </label>
												<input id="usernameX" name="username" minlength="2" required="required" value="" placeholder="请填写姓名" class="form-control" />
											</div>
											<div class="form-group">
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
												<textarea id="description" name="description" rows="3" cols="18" value="" placeholder="备注为选填项" class="form-control"  style="margin-bottom:5px;"></textarea>
											</div>
											<div class="form-group">
												<input type="button" id="applyBtn" value="申请参加" class="btn btn-default" />
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

