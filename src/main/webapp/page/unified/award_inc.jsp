<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
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
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	<!-- award end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/award_inc.js"></script>
