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
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_menu_mgr_condition_single.js"></script>
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
    
	

<div id="wx_menu_mgr_condition">
	<h3>微信当前拥有菜单管理</h3>
	<div style="height: 50px; line-height: 50px;">
		<%-- <a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_menu_mgr_add.jsp'>添加</a> --%> 
		<input id="searchBtn4WxMenuMgrCondition" type="button" class="btn btn-default btn-xs" value="查询"> 
		<!-- <input id="deleteBtn4WxMenuMgr" type="button" class="btn btn-default btn-xs" value="删除"> -->
		<input id="searchCurrentBtn4WxMenuMgrCondition" type="button" class="btn btn-default btn-xs" value="获取当前的菜单">

	</div>
	
	<!-- &nbsp;&nbsp;<input type="button" id="deleteMenu2_{{wxMenuMgrCategoryId}}" name="deleteConditionalMenu" value="删除定制菜单" onclick="deleteConditionalMenu2('{{authorizerAppId}}','{{menuId}}')" class="btn btn-info"> -->
	<!-- &nbsp;&nbsp;<input type="button" id="addButtonMR2_{{wxMenuMgrCategoryId}}" name="addButtonMR1" value="添加匹配规则" onclick="addMenuMgrMatchRuleButton2({{wxMenuMgrCategoryId}})" class="btn btn-info"> -->
	<!-- {{#wxMenuMgrMatchRulePOJO}}
	匹配规则: {{wxMenuMgrMatchRulePOJO.groupId}}, 
	{"wxMenuMgrMatchRuleId":{{wxMenuMgrMatchRuleId}},"wxMenuMgrCategoryId":{{wxMenuMgrCategoryId}}
		,"groupId":{{groupId}},"sex":{{sex}},"country":"{{country}}","province":"{{province}}","city":"{{city}}"
	,"clientPlatformType":{{clientPlatformType}},"language":"{{language}}","createDateTime":{{createDateTime}}}
	{{/wxMenuMgrMatchRulePOJO}} -->
	<script id="wxMenuMgrConditionTmpl" type="x-tmpl-mustache">
		<div id="wx_menu_mgr_condition">
		{{#data}}
			{{#display}}
			<hr>
			<b>拥有的菜单: {{name}}</b><br>
			{{#wxMenuMgrCategoryPOJOs}}
				{{^wxMenuMgrMatchRulePOJO}}	{{!如果包含match rule 不显示}}
				<input type="radio" name="wxMenuMgrCategory" id="wxMenuMgrCategory_{{wxMenuMgrCategoryId}}" value="{{wxMenuMgrCategoryId}}">{{name}}
				&nbsp;&nbsp;<input type="button" id="addButton1_{{wxMenuMgrCategoryId}}" name="addButton1" value="添加菜单" onclick="addMenuMgrButton2({{wxMenuMgrCategoryId}},0,1,'{{authorizerAppId}}')" class="btn btn-info">
				
				&nbsp;&nbsp;<input
					type="button" id="addButtonByObj1_1" name="addButtonByObj1"
					value="添加加入会员"
					onclick="addMenuMgrButtonByObj2(eval({&quot;wxMenuMgrCategoryId&quot;:{{wxMenuMgrCategoryId}},&quot;parentButtonId&quot;:0,&quot;level&quot;:1,&quot;authorizerAppId&quot;:&quot;{{authorizerAppId}}&quot;,&quot;name&quot;:&quot;加入会员&quot;,&quot;type&quot;:&quot;click&quot;,&quot;btnKey&quot;:&quot;欢迎您，GUEST&nbsp;&nbsp;1.加入会员请回复001&nbsp;&nbsp;2.重新加入请回复002&nbsp;&nbsp;3.退出会员请回复003&quot;}))"
					class="btn btn-info">
				&nbsp;&nbsp;<input type="button" id="deleteMenu2_{{wxMenuMgrCategoryId}}" name="deleteMenu" value="删除菜单" onclick="deleteMenu2('{{authorizerAppId}}')" class="btn btn-info">
				
				&nbsp;&nbsp;<input type="button" id="publishButton1_{{wxMenuMgrCategoryId}}" name="publishButton1" value="发布此菜单" onclick="publishMenuMgrCategory({{wxMenuMgrCategoryId}},'{{authorizerAppId}}')" class="btn btn-info">
				<br>
				{{#wxMenuMgrButtonPOJOs}}
					&nbsp;&nbsp;<input type="checkbox" name="wxMenuMgrButton" id="wxMenuMgrButton_{{wxMenuMgrButtonId}}" value="{{wxMenuMgrButtonId}}">type: {{type}}, name: {{name}}, key: {{btnKey}}, url: {{url}}, media_id: {{mediaId}}
					&nbsp;&nbsp;<input type="button" id="addButton2_{{wxMenuMgrCategoryPOJO.wxMenuMgrCategoryId}}" name="addButton2" value="添加下级菜单" 
						onclick="addMenuMgrButton2({{wxMenuMgrCategoryId}},{{wxMenuMgrButtonId}},2,'{{authorizerAppId}}')"
						class="btn btn-warning">
					&nbsp;&nbsp;<input type="button" id="addButtonByObj1_{{wxMenuMgrCategoryId}}" name="addButtonByObj2" value="添加加入会员"
						onclick="addMenuMgrButtonByObj2(eval({&quot;wxMenuMgrCategoryId&quot;:{{wxMenuMgrCategoryId}},&quot;parentButtonId&quot;:{{parentButtonId}},&quot;level&quot;:2,&quot;authorizerAppId&quot;:&quot;{{authorizerAppId}}&quot;,&quot;name&quot;:&quot;加入会员&quot;,&quot;type&quot;:&quot;click&quot;,&quot;btnKey&quot;:&quot;欢迎您，GUEST&nbsp;&nbsp;1.加入会员请回复001&nbsp;&nbsp;2.重新加入请回复002&nbsp;&nbsp;3.退出会员请回复003&quot;}))"
						class="btn btn-info">
					&nbsp;&nbsp;<input type="button"
						id="deleteMenuMgrBtn_{{wxMenuMgrButtonId}}" name="deleteMenuMgrBtn" value="删除此菜单"
						onclick="deleteMenuMgrButton2({{wxMenuMgrButtonId}})" class="btn btn-warning"><br>
						{{#wxMenuMgrButtonPOJOs}}
							&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="checkbox" name="wxMenuMgrButton" id="wxMenuMgrButton_{{wxMenuMgrButtonId}}"
							value="{{wxMenuMgrButtonId}}">type: {{type}}, name: {{name}}, key: {{btnKey}}, url: {{url}}, media_id: {{mediaId}}&nbsp;&nbsp;
							<input
							type="button" id="deleteMenuMgrBtn_{{wxMenuMgrButtonId}}" name="deleteMenuMgrBtn"
							value="删除此菜单" onclick="deleteMenuMgrButton2({{wxMenuMgrButtonId}})"
							class="btn btn-warning">
						<br>
						{{/wxMenuMgrButtonPOJOs}}
				{{/wxMenuMgrButtonPOJOs}}
				
				{{/wxMenuMgrMatchRulePOJO}}
						
				<br>
			{{/wxMenuMgrCategoryPOJOs}}
			{{/display}}
		{{/data}}
		<br>
		<br>
	</div>
	</script>
			
	<div id="wxMenuMgrConditionTarget"></div>
	
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
				<!-- content end -->
	     		</div>
	     	</div>
  		</div>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  	<%@include file="../../bottom.jsp"%>	
	</div>
  </body>
</html>


