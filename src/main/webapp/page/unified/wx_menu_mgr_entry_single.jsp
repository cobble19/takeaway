<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cobble.takeaway.util.CommonConstant" %>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<%@include file="../../page/common/head.jsp"%>
		
		<link href="<cmn:base/>/css/unified/activity_list.css" rel="stylesheet">
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/progress_dialog.js"></script>
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_menu_mgr_entry_single.js"></script>
		<style type="text/css">
			<!--
			/* .no-close .ui-dialog-titlebar-close {
			  display: none;
			} */
			-->
		</style>	
	</head>
	
  <body style="padding-top: 100px;">
  	<%@include file="../../reg_login.jsp" %>
  	<div class="container-fluid">
        <div  class="row" style=" height:4px; background-color:#44b549;"></div>
        <div  class="row" style=" height:36px; background-color:#e7e8eb;"></div>
  		<div class="row" style="min-height:500px; border:1px solid #CCC;">
  			<div class="col-md-3 col-xs-4" style="padding:30px 0px 0px 0px; border-right:1px solid #e7e7eb; text-align:center;" id="sidebar">
                  <%@include file="menu_left.jsp" %>
            </div>
	     	<div class="col-md-9 col-xs-8" style="min-height:500px;">
	     		<div id="uc_content" style="padding-top:40px;">
        			

        			
	     			<div id="wx_menu_mgr_entry">
	     				<h3>公众号菜单</h3>
	     				<div style="margin: 10px auto;">
     						<button id="getMenuBtn4WxMenuMgrEntryFromWx" class="btn btn-default btn-sm" 
     							data-toggle="tooltip" data-placement="top" title="获取微信公众号当前显示的菜单">
     							获取菜单 <span style="color: green;" class="glyphicon glyphicon-download"></span>
     						</button>
     						<button id="publishMenuBtn4WxMenuMgrEntryToWx" class="btn btn-default btn-sm" 
     							data-toggle="tooltip" data-placement="top" title="发布最新菜单到微信公众号上">
     							发布菜单 <span style="color: green;" class="glyphicon glyphicon-upload"></span>
     						</button>
     						<button id="deleteMenuBtn4WxMenuMgrEntryFromWx" class="btn btn-default btn-sm" 
     							data-toggle="tooltip" data-placement="top" title="将微信公众号当前显示的菜单清空">
     							删除菜单 <span style="color: green;" class="glyphicon glyphicon-remove"></span>
     						</button>
	     					
	     				</div>
	     				
     					<c:if test="${not empty sessionScope.wxMenuMgrEntrySuccess and not sessionScope.wxMenuMgrEntrySuccess}">
	     					<div style="" class="alert alert-warning alert-dismissible" role="alert">
							  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							  <strong>警告!</strong> <c:out value="${sessionScope.wxMenuMgrEntryMsg}"></c:out>
							</div>	
							<%
								session.setAttribute("wxMenuMgrEntrySuccess", null);
							%>
						</c:if>
     				
						<div style="" class="alert alert-success alert-dismissible" role="alert">
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						  <strong>提示:</strong> 菜单修改后必须发布才能生效!
						</div>
				  		<table id="dbTable4WxMenuMgrEntry" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4WxMenuMgrEntry" id="chkBoxAll4WxMenuMgrEntry">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>一级菜单</th>
				  						<th>二级菜单1</th>
				  						<th>二级菜单2</th>
				  						<th>二级菜单3</th>
				  						<th>二级菜单4</th>
				  						<th>二级菜单5</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	<!-- wx_menu_mgr_entry end -->
	     			
	     			
	   <div id="wxMenuMgrEntryButtonDiv" style="display: none;">
		<form id="wxMenuMgrButtonForm" style="width: 600px;" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/wxMenuMgrButton/add' method="post">
 			<div class="form-group" style="display: none;">
 				<label class="col-sm-3 control-label" for="wxMenuMgrButtonId">标识:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="wxMenuMgrButtonId" name="wxMenuMgrButtonId" placeholder="请输入标识">
 				</div>
 			</div>
 			<div class="form-group" style="display: none;">
 				<label class="col-sm-3 control-label" for="parentButtonId">上级标识:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="parentButtonId" name="parentButtonId" required="required" placeholder="请输入上级标识">
 				</div>
 			</div>
 			<div class="form-group" style="display: none;">
 				<label class="col-sm-3 control-label" for="authorizerAppId">公众号APPID:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="authorizerAppIdX" name="authorizerAppId" required="required" placeholder="请输入公众号APPID">
 				</div>
 			</div>
 			<div class="form-group" style="display: none;">
 				<label class="col-sm-3 control-label" for="wxMenuMgrCategoryId">类别:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="wxMenuMgrCategoryId" name="wxMenuMgrCategoryId" placeholder="请输入类别ID">
 				</div>
 			</div>
 			<div class="form-group" style="display: none;">
 				<label class="col-sm-3 control-label" for="menuId">菜单ID:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="menuId" name="menuId" placeholder="请输入菜单ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="col-sm-3 control-label" for="type">菜单类型:</label>
 				<div class="col-sm-9">
 					<!-- <input class="form-control" id="type" name="type" required="required" placeholder="请输入类型"> -->
 					<select class="form-control" id="type" name="type" required="required">
						<option value="click" selected="selected">文字回复</option>
						<option value="view">跳转链接</option>
						<option value="media_id">媒体回复（包括图文、图片、语音）</option>
						<!-- <option value="click">一键添加加入会员</option>
						<option value="click">一键添加今日签到</option> -->
						<option value="click">一键添加会员中心</option>
					</select>
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="col-sm-3 control-label" for="name">菜单名称:</label>
 				<div class="col-sm-9">
 					<input class="form-control" required="required" id="name" name="name" placeholder="请输入菜单名称">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="col-sm-3 control-label" for="btnKey">菜单内容:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="btnKey" name="btnKey" placeholder="请输入菜单内容">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="col-sm-3 control-label" for="url">URL:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="url" name="url" placeholder="请输入URL">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="col-sm-3 control-label" for="mediaId">媒体ID:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="mediaId" name="mediaId" placeholder="请输入媒体ID">
 				</div>
 			</div>
 			<!-- <div class="form-group">
 				<label class="col-sm-3 control-label" for="value">值:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="value" name="value" placeholder="请输入值">
 				</div>
 			</div> -->
 			<!-- <div class="form-group">
 				<label class="col-sm-3 control-label" for="newsInfo">新闻信息:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="newsInfo" name="newsInfo" placeholder="请输入新闻信息">
 				</div>
 			</div> -->
 			<div class="form-group" style="display: none;">
 				<label class="col-sm-3 control-label" for="level">等级:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="level" name="level" placeholder="请输入等级">
 				</div>
 			</div>
 			<div class="form-group" style="display: none;">
 				<label class="col-sm-3 control-label" for="orderNo">菜单顺序:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="orderNo" name="orderNo" placeholder="请输入菜单顺序">
 				</div>
 			</div>
 			<div class="form-group">
	 			<div class="col-sm-offset-3 col-sm-9">
	  				<button type="submit" class="btn btn-default" id="addBtn">创建</button>
	  				<button type="button" class="btn btn-default" id="closeBtn">关闭</button>
	  				<!-- <button type="button" class="btn btn-default" id="addMemberTmplBtn">加入会员模板</button> -->
	 			</div>
 			</div>
  		</form>
	</div>
				<!-- content end -->
	     		</div>
	     	</div>
  		</div>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  	<%@include file="../../bottom.jsp"%>	
	</div>
  </body>
</html>
	
