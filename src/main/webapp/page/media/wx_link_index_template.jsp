<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
  
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/wx_link_index_template.js"></script>
    
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
    
    <style type="text/css">
    	.sec {
    		border: 2px solid #ffffff;
    	}
    	.mover {
    		border: 2px solid #aabbcc;
    		cursor: pointer;
    	}
    </style>
    
  </head>
  <body style="padding:0px; background-color:#f1f1f1;">
  	
  	<div class="container">
  		<div class="row navbar-fixed-top" >
		  <div class="alert alert-success" style="text-align: center;" role="alert">
		    <strong>请点击下方各区域进行修改，修改完成后，点击发布按钮才可生效！！</strong> 
			<button id="deployHtml" class="btn btn-success" type="button">发布</button>
		  </div>
  		</div>
  		<div class="row" style="margin-top: 70px;">
  			<div class="col-md-2" style="display: none;">
  				<c:forEach items="${wxTemplatePOJOs}" var="wxTemplatePOJO" varStatus="st">
  					<c:if test="${empty wxTemplatePOJO.checked}">
  						<input type="radio" name="wxTemplateId" value="${wxTemplatePOJO.wxTemplateId}"> <c:out value="${wxTemplatePOJO.wxTemplateName}"></c:out>
  					</c:if>
  					<c:if test="${!empty wxTemplatePOJO.checked}">
  						<input type="radio" name="wxTemplateId" checked value="${wxTemplatePOJO.wxTemplateId}"> <c:out value="${wxTemplatePOJO.wxTemplateName}"></c:out>
  					</c:if>
  					<br/>
  				</c:forEach>
  				
  			</div>
  			<div class="col-md-10">
			  	<!-- 主体 -->
				<div class="container">
					<%-- <%@include file="../../../reg_login_full.jsp" %> --%>
					<!-- <div id="showDiv"> -->
						<div class="row">
							<div id="sec0" class="col-md-12 col-xs-12 sec" style="padding:0px; text-align:center;">
								 	<input type="hidden" name="linkUrl">
				            		<img alt="" src="">
							</div>
						</div>
						<div class="row" style="background-color:#ffffff; border-left:#f1f1f1 4px solid;border-right:#f1f1f1 4px solid; border-top:#f1f1f1 2px solid; border-bottom:#f1f1f1 2px solid;">
							<div class="col-md-4 col-xs-4" >
			                   <div class="row" style="border-left:#ffffff 4px solid;">
								 <div id="sec1" class="col-md-12 col-xs-12 sec" style="text-align:center;">
								 	<input type="hidden" name="linkUrl">
				            		<img alt="" src="">
									 <div class="col-md-12 col-xs-12">
					            		<p class="title" style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">节目直播/回听</p>
									 </div>
								 </div>
			                   </div>
							</div>
			                <div class="col-md-4 col-xs-4" >
			                   <div class="row" style="border-left:#f1f1f1 4px solid;">
								 <div id="sec2" class="col-md-12 col-xs-12 sec" style="text-align:center;">
								 	<input type="hidden" name="linkUrl">
				            		<img alt="" src="">
									 <div class="col-md-12 col-xs-12">
					            		<p class="title" style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">微社区（论坛）</p>
									 </div>
								 </div>
			                   </div> 
			                </div>
							<div class="col-md-4 col-xs-4" >
			                   <div class="row" style="border-left:#f1f1f1 4px solid;">
								 <div id="sec3" class="col-md-12 col-xs-12 sec" style="text-align:center;">
								 	<input type="hidden" name="linkUrl">
				            		<img alt="" src="">
									 <div class="col-md-12 col-xs-12">
					            		<p class="title" style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">节目介绍</p>
									 </div>
								 </div>
			                   </div>
							</div>
			            </div>
			            <div class="row" style="background-color:#ffffff; border-left:#f1f1f1 4px solid;border-right:#f1f1f1 4px solid; border-top:#f1f1f1 2px solid; border-bottom:#f1f1f1 2px solid;">
							<div class="col-md-4 col-xs-4">
			                   <div class="row" style="border-left:#ffffff 4px solid;">
								 <div id="sec4" class="col-md-12 col-xs-12 sec" style="text-align:center;">
								 	<input type="hidden" name="linkUrl">
				            		<img alt="" src="">
									 <div class="col-md-12 col-xs-12">
					            		<p class="title" style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">往期精彩</p>
									 </div>
								 </div>
			                   </div>
			                </div>
							<div class="col-md-8 col-xs-8">
								<div class="row" style="border-left: #f1f1f1 4px solid;">
									<div id="sec5" class="col-md-12 col-xs-12 sec"
										style="text-align: center;">
										<input type="hidden" name="linkUrl">
										<div class="col-md-4 col-xs-4">
											<p class="title"
												style="text-align: center; margin-top: 30px; font-size: 12px; line-height: 12px; min-height: 25px;">近期重要活动</p>
										</div>
										<div class="col-md-8 col-xs-8">
											<img alt="" src="">
										</div>
									</div>
								</div>
							</div>
					</div>
			
						<div class="row" style="background-color:#ffffff; border-left:#f1f1f1 4px solid;border-right:#f1f1f1 4px solid; border-top:#f1f1f1 2px solid; border-bottom:#f1f1f1 2px solid;">
							<div class="col-md-4 col-xs-4" >
			                   <div class="row" style="border-left:#ffffff 4px solid;">
								 <div id="sec6" class="col-md-12 col-xs-12 sec" style="text-align:center;">
								 	<input type="hidden" name="linkUrl">
				            		<img alt="" src="">
									 <div class="col-md-12 col-xs-12">
					            		<p class="title" style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">测试 测试</p>
									 </div>
								 </div>
			                   </div>
							</div>
							<div class="col-md-4 col-xs-4" >
			                   <div class="row" style="border-left:#f1f1f1 4px solid;">
								 <div id="sec7" class="col-md-12 col-xs-12 sec" style="text-align:center;">
								 	<input type="hidden" name="linkUrl">
				            		<img alt="" src="">
									 <div class="col-md-12 col-xs-12">
					            		<p class="title" style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">测试 测试</p>
									 </div>
								 </div>
			                   </div>
							</div>
							<div class="col-md-4 col-xs-4" >
			                   <div class="row" style="border-left:#f1f1f1 4px solid;">
								 <div id="sec8" class="col-md-12 col-xs-12 sec" style="text-align:center;">
								 	<input type="hidden" name="linkUrl">
				            		<img alt="" src="">
									 <div class="col-md-12 col-xs-12">
					            		<p class="title" style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">测试 测试</p>
									 </div>
								 </div>
			                   </div>
							</div>
						</div>
			           <!--  <div class="row" style="background-color:#f1f1f1;">
			                <p style="text-align:center; margin-bottom:10px; margin-top:20px; font-size:12px; line-height:12px;">2015 合肥交通广播</p>
			            </div>
			            <div class="row" style="background-color:#333;">
			                <p style="text-align:center; margin-bottom:5px; margin-top:5px; font-size:12px; line-height:12px; color:#ffffff;">技术支持:得味驿站</p>
			            </div> -->
			            
			            <%@include file="../../page/media/wx_link_bottom.jsp" %>
                        
                        <div class="row" style="background-color:#333;">
	                         <p style="text-align:center; margin-bottom:5px; margin-top:5px; font-size:12px; line-height:12px; color:#ffffff;">&copy;2016 合肥交通广播</p>
                        </div>   
			            
				  		<div id="wxLinkDiv" class="">
				  			<fieldset class="scheduler-border">
				  				<legend class="scheduler-border"></legend>
						  		<form id="wxLinkForm" class="form-horizontal" role="form" action='<cmn:base/>/web/media/wxLink/add' method="post">
						 			<div class="form-group">
						 				<label class="control-label" for="title">名称:</label>
						 				<div class="">
						 					<input class="form-control" id="title" name="title" placeholder="请输入图片主题">
						 				</div>
						 			</div>
						 			<div class="form-group">
						 				<label class="control-label" for="imgSrc">图片:</label>
						 				<div class="">
						 					<input class="form-control" id="imgSrc" name="imgSrc" readonly="readonly" required="required" placeholder="请上传图片">
						 					<input class="" id="pic" name="pic" type="file">
						 					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传">
						 				</div>
						 			</div>
						 			<div class="form-group">
						 				<label class="control-label" for="linkUrl">链接:</label>
						 				<div class="">
						 					<input class="form-control" id="linkUrl" name="linkUrl" placeholder="请输入链接">
						 				</div>
						 			</div>
						 			<!-- <div class="form-group">
						 				<label class="control-label" for="display">是否显示:</label>
						 				<div class="">
						 					<input class="form-control" id="display" name="display" placeholder="请输入是否显示（0-不显示，1-显示）">
						 				</div>
						 			</div> -->
						 			<!-- <div class="form-group">
						 				<label class="control-label" for="orderNo">排序:</label>
						 				<div class="">
						 					<input class="form-control" id="orderNo" name="orderNo" placeholder="请输入排序序号">
						 				</div>
						 			</div> -->
						 			<input type="hidden" class="" id="orderNo" name="orderNo">
						 			<div class="form-group">
							 			<div class="">
							  				<button type="button" class="btn btn-default" id="addBtn">确定</button>
							 			</div>
						 			</div>
						  		</form>
				  			</fieldset>
				  		</div>
			            
					<!-- </div> --><!-- for show -->
				  		
				</div> <!-- container -->
  			</div>	<!-- col-md-10 -->
  		</div>	<!-- row -->
  	</div>	<!-- container -->
  	
  	<div id="progress">数据加载中。。。</div>
  
  </body>
</html>

