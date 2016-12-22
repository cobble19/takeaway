<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<%@include file="../common/taglib.jsp" %>

<div id="wx_menu_mgr_condition">
	<h3>微信Full菜单管理</h3>
	<div style="height: 50px; line-height: 50px;">
		<%-- <a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_menu_mgr_add.jsp'>添加</a> --%> 
		<input id="searchBtn4WxMenuMgrCondition" type="button" class="btn btn-default btn-xs" value="查询"> 
		<!-- <input id="deleteBtn4WxMenuMgr" type="button" class="btn btn-default btn-xs" value="删除"> -->
		<input id="searchCurrentBtn4WxMenuMgrCondition" type="button" class="btn btn-default btn-xs" value="获取当前的菜单">

	</div>
	<div id="wx_menu_mgr_condition_content">
		<%-- <c:forEach items="${wxMenuMgrCagetoryPOJOs}" var="wxMenuMgrCategoryPOJO" varStatus="st">
			<c:out value="${wxMenuMgrCategoryPOJO.name}"></c:out>
			<c:forEach items="${wxMenuMgrCategoryPOJO.wxMenuMgrButtonPOJOs}" var="wxMenuMgrButtonPOJO" varStatus="st2">
				<c:out value="${wxMenuMgrButtonPOJO.type}"></c:out>
				<c:out value="${wxMenuMgrButtonPOJO.url}"></c:out>
				<c:out value="${wxMenuMgrButtonPOJO.key}"></c:out>
			</c:forEach>
		</c:forEach> --%>
	</div>
	
	<!-- dialog -->
	<div id="wxMenuMgrButtonDiv_2" style="display: none;">
		<form id="wxMenuMgrButtonForm2" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/wxMenuMgrButton/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="parentButtonId">上级标识:</label>
 				<div class="">
 					<input class="form-control" id="parentButtonId" name="parentButtonId" required="required" placeholder="请输入上级标识">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="authorizerAppId">公众号APPID:</label>
 				<div class="">
 					<input class="form-control" id="authorizerAppIdX" name="authorizerAppId" required="required" placeholder="请输入公众号APPID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="wxMenuMgrCategoryId">类别:</label>
 				<div class="">
 					<input class="form-control" id="wxMenuMgrCategoryId" name="wxMenuMgrCategoryId" placeholder="请输入类别ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="menuId">菜单ID:</label>
 				<div class="">
 					<input class="form-control" id="menuId" name="menuId" placeholder="请输入菜单ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="type">类型:</label>
 				<div class="">
 					<input class="form-control" id="type" name="type" required="required" placeholder="请输入类型">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="name">名称:</label>
 				<div class="">
 					<input class="form-control" id="name" name="name" placeholder="请输入名称">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="btnKey">关键字:</label>
 				<div class="">
 					<input class="form-control" id="btnKey" name="btnKey" placeholder="请输入关键字">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="url">URL:</label>
 				<div class="">
 					<input class="form-control" id="url" name="url" placeholder="请输入URL">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="mediaId">媒体ID:</label>
 				<div class="">
 					<input class="form-control" id="mediaId" name="mediaId" placeholder="请输入媒体ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="value">值:</label>
 				<div class="">
 					<input class="form-control" id="value" name="value" placeholder="请输入值">
 				</div>
 			</div>
 			<!-- <div class="form-group">
 				<label class="control-label" for="newsInfo">新闻信息:</label>
 				<div class="">
 					<input class="form-control" id="newsInfo" name="newsInfo" placeholder="请输入新闻信息">
 				</div>
 			</div> -->
 			<div class="form-group">
 				<label class="control-label" for="level">等级:</label>
 				<div class="">
 					<input class="form-control" id="level" name="level" placeholder="请输入等级">
 				</div>
 			</div>
 			<div class="form-group">
	 			<div class="">
	  				<button type="submit" class="btn btn-default" id="addBtn">创建</button>
	 			</div>
 			</div>
  		</form>
	</div>
	
	<!-- dialog -->
	<div id="wxMenuMgrMatchRuleDiv_2" style="display: none;">
		<form id="wxMenuMgrMatchRuleForm2" class="form-horizontal" role="form" action='<cmn:base/>/web/unified/wxMenuMgrMatchRule/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="wxMenuMgrCategoryId">类别:</label>
 				<div class="">
 					<input class="form-control" id="wxMenuMgrCategoryId" name="wxMenuMgrCategoryId" placeholder="请输入类别ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="groupId">标签ID:</label>
 				<div class="">
 					<input class="form-control" id="groupId" name="groupId" required="required" placeholder="请输入标签ID">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="sex">性别:</label>
 				<div class="">
 					<input class="form-control" id="sex" name="sex" placeholder="请输入性别 1-男 2-女 ">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="country">国家:</label>
 				<div class="">
 					<input class="form-control" id="country" name="country" placeholder="请输入国家">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="province">省:</label>
 				<div class="">
 					<input class="form-control" id="province" name="province" placeholder="请输入省">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="city">市:</label>
 				<div class="">
 					<input class="form-control" id="city" name="city" placeholder="请输入市">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="clientPlatformType">客户端版本:</label>
 				<div class="">
 					<input class="form-control" id="clientPlatformType" name="clientPlatformType" placeholder="请输入客户端版本 IOS(1), Android(2),Others(3)">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="language">语言:</label>
 				<div class="">
 					<input class="form-control" id="language" name="language" placeholder="请输入语言 1、简体中文 zh_CN ">
 				</div>
 			</div>
 			<div class="form-group">
	 			<div class="">
	  				<button type="submit" class="btn btn-default" id="addBtn">创建</button>
	 			</div>
 			</div>
  		</form>
	</div>
	
</div>
<!-- wx_menu_mgr end -->
<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_menu_mgr_condition_inc.js"></script>
