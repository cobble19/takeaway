<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="create_vote">
	     				<h3>投票</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/vote_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4Vote" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4Vote" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4Vote" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4Vote" id="chkBoxAll4Vote">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>发布者标识</th>
				  						<th>标题</th>
				  						<th>内容</th>
				  						<th>投票类型</th>
				  						<th>发布类型</th>
				  						<th>创建时间</th>
				  						<th>活动ID</th>
				  						<th>显示属性模块ID</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	<!-- create_vote end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/vote_inc.js"></script>
