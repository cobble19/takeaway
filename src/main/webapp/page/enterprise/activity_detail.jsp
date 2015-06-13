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
		<div class="row" style="border-bottom:1px solid #CCC; ">
			<div class="col-md-9 col-xs-6">
                 <a href="http://www.deweiyizhan.com" style="font-size:12px;">得味首页</a>&nbsp;&nbsp;
                 <a href="http://#" style="font-size:12px;">活动中心</a>
            </div>
            <div class="col-md-3 col-xs-6">
				<jsp:include page="${basePath}/reg_login.jsp"></jsp:include>            
            </div>
		</div>
		<div class="row">
			<div class="col-md-12" >
            	<span style=" margin-top:50px; margin-bottom:50px; display:block;"><h3 id="title"></h3></span>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
            	<h5 style=" margin-bottom:10px; display:block;">发布者:<span id="publisher"></span></h5>
			</div>
        </div>	
  		<div class="row uc-border">
  			<input type="hidden" id="activityId" name="activityId" value="<%=request.getParameter("activityId") %>"/>
  			<div class="row">
  				<div class="col-md-9">
  					<!-- <h4 class="" style="text-align: bottom;"><span id="title"></span></h4>
  					<hr/> -->
		  			<p style=" margin:10px; border-right:1px solid #CCC;" id="content"></p>
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
								<input type="submit" id="applyBtn" value="申请参加"
									class="btn btn-default" />
							
						</form>
				</fieldset>
				</div>
  			</div>
  			

			<!-- <hr/>
  			<p id="content"></p>
  			<hr/> -->
  		</div>
  		
		<footer><br/><p>&copy; 版权所有</p></footer>
	</div> <!-- container -->
	
  </body>
</html>
