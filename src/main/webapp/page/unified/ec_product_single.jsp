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
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/ec_product_single.js"></script>
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
        			
	     			<div id="ecProduct">
	     				<h3>商品</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/ec_product_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4EcProduct" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4EcProduct" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4EcProduct" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4EcProduct" id="chkBoxAll4EcProduct">全选</th>
				  						<th>序号</th>
				  						<th>标识ID</th>
				  						<th>拥有者ID</th>
				  						<th>AppID</th>
										<th>商品官方网站</th>
				  						<th>商品名称</th>
				  						<th>图片</th>
				  						<th>购买须知</th>
				  						<th>商品总数</th>
				  						<th>商品库存</th>
				  						<th>单价(分)</th>
										<th>每人限额</th>
										<th>每天限额</th>
				  						<th>微信卡券ID</th>
										<th>微信卡券库存</th>
				  						<th>是否需要关注</th>
										<th>开始时间</th>
										<th>截止时间</th>
				  						<th>创建时间</th>
				  						<th>最后修改时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	
	     			<!-- ecProduct end -->
				<!-- content end -->
	     		</div>
	     	</div>
  		</div>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  	<%@include file="../../bottom.jsp"%>	
	</div>
  </body>
</html>
	     			
	     			
