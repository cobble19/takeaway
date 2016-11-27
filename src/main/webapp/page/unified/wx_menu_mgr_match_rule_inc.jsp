<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="wx_menu_mgr_match_rule">
	     				<h3>微信菜单match rule管理</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_menu_mgr_match_rule_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4WxMenuMgrMatchRule" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4WxMenuMgrMatchRule" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4WxMenuMgrMatchRule" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4WxMenuMgrMatchRule" id="chkBoxAll4WxMenuMgrMatchRule">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>标签ID</th>
				  						<th>性别</th>
				  						<th>国家</th>
				  						<th>省</th>
				  						<th>市</th>
				  						<th>客户端版本</th>
				  						<th>语言</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	<!-- wx_menu_mgr_match_rule end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_menu_mgr_match_rule_inc.js"></script>
