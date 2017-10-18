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
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/activity_gather_single.js"></script>
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
				
	     			<div id="create_activity_gather">
	     				<h4 style="font-weight:bold;">表单类信息
	     				</h4>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-info btn-xs" target="_blank" data-toggle="tooltip" data-placement="top" 
	     					title="添加" href='<cmn:base/>/page/unified/activity_gather.jsp'>
								<span style="color: ;" class="glyphicon glyphicon-plus"></span>
							</a>
							
	     					<button  id="searchBtn4ActivityGather" type="button" class="btn btn-info btn-xs"
	     						data-toggle="tooltip" data-placement="top" title="查询" value="查询">
	     						<span class="glyphicon glyphicon-search" ></span>
	     					</button>
	     					<button  id="showLinkBtn4ActivityGather" type="button" class="btn btn-info btn-xs"
	     						data-toggle="tooltip" data-placement="top" title="相关链接" value="相关链接">
	     						<span class="glyphicon glyphicon-link" ></span>
	     					</button>
	     					<button  id="deleteBtn4ActivityGather" type="button" class="btn btn-danger btn-xs"
	     						data-toggle="tooltip" data-placement="top" title="删除" value="删除">
	     						<span class="glyphicon glyphicon-trash" ></span>
	     					</button>
	     					
	     				</div>
                        <div class="table-responsive">
				  		<table id="dbTable4ActivityGather" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll" id="chkBoxAll">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>标题</th>
				  						<th>开始时间</th>
				  						<th>截止时间</th>
				  						<th>期限</th>
				  						<th>状态</th>
				  						<th>内容简介</th>
				  						<!-- <th>类型</th> -->
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  				<!-- <tfoot>
				  					<tr>
				  						<th>No.</th>
				  						<th>标识</th>
				  						<th>标题</th>
				  						<th>内容</th>
				  					</tr>
				  				</tfoot> -->
				  			</table>
                            </div>
	     			</div>	<!-- create_activity_gather end -->
	     			<div id="activityGatherDetailDiv" class="row" style="width: 450px; height: 120px;">
				  			<label>单纯表格页面链接</label>: <input type="text" class="" style="width: 400px;" id="activityGatherDetailUrl">
				  			<button class="btn btn-info btn-xs" id="copyBtn4ActivityGather" data-clipboard-target="#activityGatherDetailUrl">
							    复制链接
							</button>
							<br/>
							<br/>
				  			<label>完整内容页面链接</label>: <input type="text" class="" style="width: 400px;" id="activityGatherDetailUrl2">
				  			<button class="btn btn-info btn-xs" id="copyBtn24ActivityGather" data-clipboard-target="#activityGatherDetailUrl2">
							    复制链接
							</button>
				  			<br/>
				  			<span id="copyMsg" style="color: red;"></span>
				  			
			  		</div>
			  		
	     			<div id="activityActiveGatherListDiv" class="row" style="width: 450px; height: 120px;">
				  			<label>正在活动列表页面链接</label>: <input type="text" class="" style="width: 400px;" id="activityGatherActiveListUrl">
				  			<button class="btn btn-info btn-xs" id="copyBtn4activityGatherActiveList" data-clipboard-target="#activityActiveListUrl">
							    复制链接
							</button>
							<br/>
							<br/>
				  			<span id="copyMsg" style="color: red;"></span>
				  			
			  		</div>
			  		<div id="picDiv">
				  		<form id="picForm" class="form-horizontal" role="form" action='' method="post">
				  			<input type="hidden" id="activityGatherId">
				 			<div class="form-group">
				 				<label class="control-label" for="logoImg">图片:</label>
				 				<div class="">
				 					<input class="form-control" id="logoImg" name="logoImg" readonly="readonly" required="required" placeholder="请上传图片">
				 					<input class="" id="pic" name="pic" type="file">
				 					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传图片">
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
			  		
	     			
	     			
	     			
	     			
