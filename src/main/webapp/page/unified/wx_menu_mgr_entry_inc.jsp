<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="wx_menu_mgr_entry">
	     				<h3>微信菜单列表管理</h3>
	     				<div style=" height:100px; line-height:50px;">
	     					<%-- <a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_menu_mgr_entry_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4WxMenuMgrEntry" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4WxMenuMgrEntry" type="button" class="btn btn-default btn-xs" value="删除"> --%>
	     					
     						<button id="getMenuBtn4WxMenuMgrEntryFromWx" class="btn btn-default btn-sm" 
     							data-toggle="tooltip" data-placement="top" title="从微信服务器获取菜单">
     							<span style="color: green;" class="glyphicon glyphicon-download">
     						</button>
     						<button id="publishMenuBtn4WxMenuMgrEntryToWx" class="btn btn-default btn-sm" 
     							data-toggle="tooltip" data-placement="top" title="发布菜单到微信服务器">
     							<span style="color: green;" class="glyphicon glyphicon-upload">
     						</button>
	     					<c:if test="${not empty sessionScope.wxMenuMgrEntrySuccess and not sessionScope.wxMenuMgrEntrySuccess}">
		     					<div style="padding: 0 20px 0 0;; margin: 0" class="alert alert-warning alert-dismissible" role="alert">
								  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								  <strong>警告!</strong> <c:out value="${sessionScope.wxMenuMgrEntryMsg}"></c:out>
								</div>	
							</c:if>
	     					
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
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_menu_mgr_entry_inc.js"></script>
	     			
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
						<option value="addMember">一键添加"加入会员"菜单</option>
					</select>
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="col-sm-3 control-label" for="name">菜单名称:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="name" name="name" placeholder="请输入菜单名称">
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
 			<div class="form-group">
 				<label class="col-sm-3 control-label" for="orderNo">菜单顺序:</label>
 				<div class="col-sm-9">
 					<input class="form-control" id="orderNo" name="orderNo" placeholder="请输入菜单顺序">
 				</div>
 			</div>
 			<div class="form-group">
	 			<div class="col-sm-offset-3 col-sm-9">
	  				<button type="submit" class="btn btn-default" id="addBtn">创建</button>
	  				<!-- <button type="button" class="btn btn-default" id="addMemberTmplBtn">加入会员模板</button> -->
	 			</div>
 			</div>
  		</form>
	</div>