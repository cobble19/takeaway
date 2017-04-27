<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="point_event">
	     				<h3>积分事件管理</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/point_event_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4PointEvent" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4PointEvent" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4PointEvent" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4PointEvent" id="chkBoxAll4PointEvent">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>用户ID</th>
				  						<th>授权APPID</th>
				  						<th>事件名称</th>
				  						<th>每次积分数</th>
				  						<th>获取积分频率</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	<!-- point_event end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/point_event_inc.js"></script>
