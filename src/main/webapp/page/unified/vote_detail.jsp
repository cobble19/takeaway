<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/vote_detail.js"></script>
    
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
    
    <style type="text/css">
    	fieldset.scheduler-border {
		    border: 1px groove #ddd !important;
		    padding: 0 1.4em 1.4em 1.4em !important;
		    margin: 0 0 1.5em 0 !important;
		    -webkit-box-shadow:  0px 0px 0px 0px #000;
		            box-shadow:  0px 0px 0px 0px #000;
		}
		
		legend.scheduler-border {
		    font-size: 1.2em !important;
		    font-weight: bold !important;
		    text-align: left !important;
		    
		    width:inherit; /* Or auto */
		    padding:0 10px; /* To give a bit of padding on the left and right */
		    border-bottom:none;
		}
    </style>
  </head>
  <body>
  
	<div class="container-fluid">
		<%-- <%@include file="../../../reg_login_full.jsp" %> --%>
		<div id="showDiv">
			<div class="row">
				<div class="col-md-6 col-xs-6" >
	            	<span style=" margin-top:10px; margin-bottom:50px; display:block;"><h3 id="title"><c:out value="${votePOJO.title}"></c:out></h3></span>
	            	<input type="hidden" id="voteId" name="voteId" value="<%=request.getParameter("voteId") %>"/>
				</div>
				<div class="col-md-6 col-xs-6" >
	            	<div style="font-size: 14px; margin-top: 15px; float: right;  margin-left: 30px;">
						<span style="font-size: 16px; font-family: '幼圆'; font-weight: bold;">投票类型：</span>
						<span id="voteType"><c:out value="${votePOJO.voteType}"/></span>
					</div>
					<div style="font-size: 14px; margin-top: 15px; float: right">	
						<span style="font-size: 16px; font-family: '幼圆'; font-weight: bold;">发布类型：</span>
						<span id="publishType"><c:out value="${votePOJO.publishType}"></c:out></span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12" >
				    <div style="font-size: 14px;">
						<span style="font-size: 16px; font-family: '幼圆'; font-weight: bold;">内容：</span>
						<span id="content"><c:out value="${votePOJO.content}"></c:out></span>
				    </div>
				</div>
			</div>
  			<div class="row" style="">
  				<div class="col-md-6 col-xs-12" style="margin-top: 50px;">

					<!-- <hr/> -->
		  				<fieldset class="scheduler-border">
							<legend class="scheduler-border" style="font-size:16px;font-family:'幼圆'; font-weight:bold;">添加投票项</legend>
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
											<textarea rows="5" cols="" id="descriptionX" name="description" minlength="1" required="required" value="" placeholder="描述" class="form-control"></textarea> 
										</div>
										<div class="form-group">
											<label for="apply2Id" class="control-label">Apply2 ID: </label>
											<input type="text" id="apply2Id" name="apply2Id" value="" placeholder="Apply2 ID" class="form-control" /> 
										</div>
										<div class="form-group">
											<input type="button" id="applyBtn" value="提交" class="btn btn-default" />
										</div>
								</form>
						</fieldset>	
  				</div>
  				<div class="col-md-6 col-xs-12" style="margin-top: 50px;">
  				<fieldset class="scheduler-border">
					<legend class="scheduler-border" style="font-size:16px;font-family:'幼圆'; font-weight:bold;">已添加投票项</legend>
                    
                    <div class="row">
	                   <c:forEach items="${votePOJO.voteItemPOJOs}" var="voteItemPOJO" varStatus="st">
						  <div class="col-sm-6 col-md-4">
						    <div class="thumbnail">
						      <img src="<cmn:base/>/${voteItemPOJO.imgUrl}" title="${voteItemPOJO.title}" alt="${voteItemPOJO.title}">
						      <div class="caption">
						        <p style=" font-weight:bold;"><c:out value="${voteItemPOJO.title}"></c:out></p>
						        <p><c:out value="${voteItemPOJO.description}"></c:out></p>
						        <p>票数：<c:out value="${voteItemPOJO.totalNum}"></c:out></p>
						      </div>
						    </div>
						  </div>
	  					</c:forEach> 
					</div>
                   
                    
                    
                    
					<!--<div class="weui_grids">
						<c:forEach items="${votePOJO.voteItemPOJOs}" var="voteItemPOJO" varStatus="st">
						  <a href="javascript:;" class="weui_grid js_grid" data-id="button">
						    <div class="weui_grid_icon">
						      <img src="<cmn:base/>/${voteItemPOJO.imgUrl}" title="${voteItemPOJO.title}" alt="${voteItemPOJO.title}">
						    </div>
						    <p class="weui_grid_label">
						    <c:out value="${voteItemPOJO.title}"></c:out>|
						    <c:out value="${voteItemPOJO.description}"></c:out>|
						      票数：<c:out value="${voteItemPOJO.totalNum}"></c:out>
						     <input type="checkbox" name="chkBox" id="chkBox_${voteItemPOJO.voteItemId}" value="${voteItemPOJO.voteItemId}">
						      <input type="button" id="delBtn_${voteItemPOJO.voteItemId}" voteItemId="${voteItemPOJO.voteItemId}" value="删除" class="btn btn-default btn-xs btn4del">
						    </p>
						  </a>
						</c:forEach>
					</div> -->
				</fieldset>
				</div>
  			</div>
		</div><!-- for show -->
		<div id="progress">数据加载中。。。</div>
	<%@include file="../../../bottom.jsp" %>  		
	</div> <!-- container -->
  </body>
</html>

