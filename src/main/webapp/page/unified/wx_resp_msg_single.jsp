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
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_resp_msg_inc_s.js?v=3"></script>
	    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_resp_msg_inc_c.js"></script>
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
        			
	     			<div id="wx_resp_msg">
	     				<h3><span style="color: green;" class="glyphicon glyphicon-globe"></span> 系统关键字</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<%-- <a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_resp_msg_add.jsp?msgType=0'>添加</a> --%>
	     					
	     					<button  id="searchBtn4WxRespMsgS" type="button" class="btn btn-default btn-xs"
	     						data-toggle="tooltip" data-placement="top" title="查询" value="查询">
	     						<!-- 开启  --><span class="glyphicon glyphicon-search" ></span>
	     					</button>
	     					<!-- <input id="deleteBtn4WxRespMsgS" type="button" class="btn btn-default btn-xs" value="删除"> -->
	     					<button  id="enableBtn4WxRespMsgS" style="display: none;" type="button" class="btn btn-default btn-xs"
	     						data-toggle="tooltip" data-placement="top" title="开启" value="开启">
	     						<!-- 开启  --><span class="glyphicon glyphicon-ok" ></span>
	     					</button>
	     					<button  id="disableBtn4WxRespMsgS" style="display: none;" type="button" class="btn btn-default btn-xs"
	     						data-toggle="tooltip" data-placement="top" title="停用" value="停用">
	     						<!-- 停用  --><span class="glyphicon glyphicon-ban-circle" ></span>
	     					</button>
	     					
	     				</div>
	     				
				  		<table id="dbTable4WxRespMsgGather" class="display table table-striped table-bordered" cellspacing="0" width="100%">
			  				<thead>
			  					<tr>
			  						<th><!-- <input type="checkbox" name="chkBoxAll4WxRespMsgGather" id="chkBoxAll4WxRespMsgGather">全选 --></th>
			  						<th>序号</th>
			  						<th>信息类别</th>
			  						<th>用户ID</th>
			  						<th>公众号APPID</th>
			  						<th>有效标志</th>
			  						<th>关键字组合名称</th>
			  						<th>操作</th>
			  					</tr>
			  				</thead>
				  		</table>
				  		<!-- <div id="dbTable4WxRespMsgSDiv">
				  			
				  		<table id="dbTable4WxRespMsgS" class="display table table-striped table-bordered" cellspacing="0" width="100%">
			  				<thead>
			  					<tr>
			  						<th><input type="checkbox" name="chkBoxAll4WxRespMsgS" id="chkBoxAll4WxRespMsgS">全选</th>
			  						<th>序号</th>
			  						<th>标识</th>
			  						<th>接受关键字</th>
			  						<th>回复关键字</th>
			  						<th>信息类别</th>
			  						<th>用户ID</th>
			  						<th>公众号APPID</th>
			  						<th>有效标志</th>
			  						<th>创建时间</th>
			  						<th>操作</th>
			  					</tr>
			  				</thead>
				  		</table>
				  		</div> -->
				  			
	     				<h3><span style="color: green;" class="glyphicon glyphicon-wrench"></span> 定制关键字</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_resp_msg_add.jsp?msgType=1'>添加</a>
	     					
	     					<input id="searchBtn4WxRespMsgC" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4WxRespMsgC" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4WxRespMsgC" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4WxRespMsgC" id="chkBoxAll4WxRespMsgC">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>接受关键字</th>
				  						<th>回复关键字</th>
				  						<th>信息类别</th>
				  						<th>用户ID</th>
				  						<th>公众号APPID</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
				  			
	     			</div>	<!-- wx_resp_msg end -->
	     			
				<!-- content end -->
	     		</div>
	     	</div>
  		</div>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  	<%@include file="../../bottom.jsp"%>	
	</div>
  </body>
</html>
	     			

