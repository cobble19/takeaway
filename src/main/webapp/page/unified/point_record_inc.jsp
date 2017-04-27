<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="point_record">
	     				<h3>积分记录管理</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/point_record_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4PointRecord" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4PointRecord" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4PointRecord" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4PointRecord" id="chkBoxAll4PointRecord">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>用户ID</th>
				  						<th>open ID</th>
				  						<th>授权APPID</th>
				  						<th>积分</th>
				  						<th>积分原因</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	<!-- point_record end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/point_record_inc.js"></script>
