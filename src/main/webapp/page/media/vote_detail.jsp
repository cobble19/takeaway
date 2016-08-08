<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/vote_detail.js"></script>
    
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
  </head>
  <body>
  
	<div class="container">
		<%@include file="../../../reg_login_full.jsp" %>
		<div id="showDiv">
			<div class="row">
				<div class="col-md-12" >
	            	<span style=" margin-top:50px; margin-bottom:50px; display:block;"><h3 id="title"></h3></span>
	            	<input type="hidden" id="voteId" name="voteId" value="<%=request.getParameter("voteId") %>"/>
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
	  					<h4 class="" style="text-align: bottom;"><span id="title"></span></h4>
	  					<hr/>
						<p style="font-size: 14px;">
							<span style="font-size: 16px; font-family: '幼圆'; font-weight: bold;">内容：</span>
							<span id="content"></span>
						</p>
						<p style="font-size: 14px; margin-top: 15px;">
							<span style="font-size: 16px; font-family: '幼圆'; font-weight: bold;">投票类型：</span>
							<span id="voteType"></span>
						</p>
						<p style="font-size: 14px; margin-top: 15px;">
							<span style="font-size: 16px; font-family: '幼圆'; font-weight: bold;">发布类型：</span>
							<span id="publishType"></span>
						</p>

						<!-- <hr/> -->
			  			
							<div class="row col-md-12" style="margin-top: 50px;">
									<span id="errorMsg" style="color: red;"></span>
			  				<fieldset>
								<legend style="font-size:16px;font-family:'幼圆'; font-weight:bold;">添加投票项</legend>
									<form id="applyForm" class="">
											<div class="form-group">
												<label for="titleX" class="control-label">标题: </label>
												<input type="text" id="titleX" name="title" minlength="1" required="required" value="" placeholder="标题" class="form-control" />
											</div>
											
								 			<div class="form-group">
								 				<label class="control-label" for="imgUrl">图片:</label>
								 				<div class="">
								 					<input class="form-control" id="imgUrlX" name="imgUrl" readonly="readonly" required="required" placeholder="请上传图片">
								 					<input class="" id="pic" name="pic" type="file">
								 					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传">
								 				</div>
								 			</div>
											<!-- <div class="form-group">
												<label for="imgUrlX" class="control-label">图片: </label>
												<input type="text" id="imgUrlX" name="imgUrl" minlength="1" required="required" value="" placeholder="图片" class="form-control" />
											</div> -->
											<div class="form-group">
												<label for="descriptionX" class="control-label">描述: </label>
												<input type="text" id="descriptionX" name="description" minlength="1" required="required" value="" placeholder="描述" class="form-control" />
											</div>
											<div class="form-group">
												<input type="button" id="applyBtn" value="提交" class="btn btn-default" />
											</div>
									</form>
							</fieldset>	
						</div>
	  				</div>
	  				<div class="col-md-3 col-xs-12">
					</div>
	  			</div>
	  		</div>
		</div><!-- for show -->
	<%@include file="../../../bottom.jsp" %>  		
	</div> <!-- container -->
  </body>
</html>

