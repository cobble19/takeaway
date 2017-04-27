<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="point_summary">
	     				<h3>积分总结管理</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/point_summary_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4PointSummary" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4PointSummary" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4PointSummary" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4PointSummary" id="chkBoxAll4PointSummary">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>用户ID</th>
				  						<th>总积分</th>
				  						<th>已用积分</th>
				  						<th>剩余积分</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	<!-- point_summary end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/point_summary_inc.js"></script>
