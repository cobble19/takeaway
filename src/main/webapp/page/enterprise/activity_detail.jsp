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
	            	<span class=" hidden-xs" style=" margin-left:5px; margin-top:50px; margin-bottom:50px; display:block;"><h3 id="title"></h3></span>
                    <span class=" visible-xs" style=" margin-top:10px; margin-bottom:10px; margin-left:5px; margin-right:5px; display:block; text-align:center;"><h4 id="title_1"></h4></span>
	            	<input type="hidden" id="activityId" name="activityId" value="<%=request.getParameter("activityId") %>"/>
			  </div>
            </div>
			<div class="row hidden-xs">
				<div class="col-md-12">
	            	<h5 style=" margin-left:5px; margin-bottom:10px; display:block;">发布者:<span class="h5" id="publisher"></span>
				</h5>
				</div>
	        </div>
            <div class="row visible-xs" style="margin-left:5px; margin-right:5px; margin-bottom:20px; padding-top:10px; padding-bottom:10px;">
                <div class="col-xs-12">
                    <button type="button" class="btn btn-default btn-block" data-toggle="modal" data-target="#contentModal"><h5>查看活动详情</h5></button>
                    <a class="btn btn-default btn-block" href="#" role="button"><h5>发布者【<span class="h5" id="publisher_1"></span>】</h5></a>
                </div>
            </div>
            <!--手机显示活动内容-->
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
	    			</div><!-- modal-footer -->
	    		</div>
	    	</div>
	        </div><!-- modal end-->
            </div>
            <!--手机显示活动内容-->	
	  		<div class="row uc-border" style="margin-left:5px; margin-right:5px;">
	  			<div class="row">
	  				<div class="col-md-9 col-xs-12 hidden-xs">
	  					<!-- <h4 class="" style="text-align: bottom;"><span id="title"></span></h4>
	  					<hr/> -->
			  			<p style=" margin:10px; border-right:1px solid #CCC;" id="content"></p>
			  			<!-- for update -->
			  			<div style="" class="">
		 					<!-- <textarea rows="5" cols="20" id="content" name="content" placeholder="请输入本次活动内容"></textarea>
		 					<input class="form-control" type="text" id="content" name="content" placeholder="请输入本次活动内容"> -->
