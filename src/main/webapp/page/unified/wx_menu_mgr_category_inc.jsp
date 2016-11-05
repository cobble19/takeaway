<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="wx_menu_mgr_category">
	     				<h3>微信菜单category管理</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_menu_mgr_category_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4WxMenuMgrCategory" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4WxMenuMgrCategory" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4WxMenuMgrCategory" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4WxMenuMgrCategory" id="chkBoxAll4WxMenuMgrCategory">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>公众号APPID</th>
				  						<th>名称</th>
				  						<th>描述</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	<!-- wx_menu_mgr_category end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_menu_mgr_category_inc.js"></script>
