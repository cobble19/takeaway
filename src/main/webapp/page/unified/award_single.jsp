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
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/progress_dialog.js"></script>
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/award_single.js"></script>
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
        			
	     			<div id="award">
	     				<h3>奖品</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/award_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4Award" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4Award" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4Award" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4Award" id="chkBoxAll4Award">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>互动ID</th>
				  						<th>名称</th>
				  						<th>描述</th>
				  						<th>奖品总数</th>
				  						<th>奖品余额</th>
				  						<th>权重</th>
				  						<th>序号</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	
	     			<!-- award end -->
				<!-- content end -->
	     		</div>
	     	</div>
  		</div>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  	<%@include file="../../bottom.jsp"%>	
	</div>
  </body>
</html>
	     			
	     			
