<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="rel_wx_index_map">
	     				<h3>微信微官网管理</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/rel_wx_index_map_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4RelWxIndexMap" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4RelWxIndexMap" type="button" class="btn btn-default btn-xs" value="删除">
	     				</div>
				  		<table id="dbTable4RelWxIndexMap" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4RelWxIndexMap" id="chkBoxAll4RelWxIndexMap">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>用户ID</th>
				  						<th>微官网简码</th>
				  						<th>微官网模板ID</th>
				  						<th>静态页面</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	<!-- rel_wx_index_map end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/rel_wx_index_map_inc.js"></script>
