<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cobble.takeaway.util.CommonConstant" %>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
		<%@include file="../../page/common/head.jsp"%>
		
		<link href="<cmn:base/>/css/unified/activity_list.css" rel="stylesheet">
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/common_div.js"></script>
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_person_user_inc.js"></script>
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
        			
	     			<div id="wx_person_user">
	     				<h3>微信个人用户管理</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_person_user_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4WxPersonUser" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4WxPersonUser" type="button" class="btn btn-default btn-xs" value="删除">
	     					<input id="addUserInfosBtn4WxPersonUser" type="button" class="btn btn-default btn-xs" value="创建所有的粉丝">
	     					<input id="addTagBtn4WxPersonUser" type="button" class="btn btn-default btn-xs" value="打标签">
	     					<label for="tagId">Tag Id: </label><input style="border: solid 1px #ccc;" class="input-sm" type="text" id="tagId" value="101"/>
	     					
	     				</div>
				  		<table id="dbTable4WxPersonUser" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4WxPersonUser" id="chkBoxAll4WxPersonUser">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>USER ID</th>
				  						<th>昵称</th>
				  						<th>OPEN ID</th>
				  						<th>授权者APP ID</th>
				  						<th>代理OPEN ID</th>
				  						<th>代理授权者APP ID</th>
				  						<th>性别</th>
				  						<th>语言</th>
				  						<th>城市</th>
				  						<th>省</th>
				  						<th>国家</th>
				  						<th>关注</th>
				  						<th>关注时间</th>
				  						<th>权限</th>
				  						<th>UNION ID</th>
				  						<th>备注</th>
				  						<th>GROUP ID</th>
				  						<th>TAG IDs</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	
	     			<!-- wx_person_user end -->
				<!-- content end -->
	     		</div>
	     	</div>
  		</div>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  	<%@include file="../../bottom.jsp"%>	
	</div>
  </body>
</html>
	     			
