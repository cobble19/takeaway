<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="wx_menu_mgr_button">
	     				<h3>微信菜单管理</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_menu_mgr_button_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4WxMenuMgrButton" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4WxMenuMgrButton" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4WxMenuMgrButton" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4WxMenuMgrButton" id="chkBoxAll4WxMenuMgrButton">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>上级标识</th>
				  						<th>公众号APPID</th>
				  						<th>类别</th>
				  						<th>菜单ID</th>
				  						<th>类型</th>
				  						<th>名称</th>
				  						<th>关键字</th>
				  						<th>URL</th>
				  						<th>媒体ID</th>
				  						<th>值</th>
				  						<th>新闻信息</th>
				  						<th>等级</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	<!-- wx_menu_mgr_button end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_menu_mgr_button_inc.js"></script>
