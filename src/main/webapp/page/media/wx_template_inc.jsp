<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="create_wxTemplate">
	     				<h3>微信首页模板管理</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<%-- <a class="btn btn-primary btn-xs" target="" href='<cmn:base/>/page/media/wx_template_add.jsp'>添加互动</a> --%>
	     					
	     					<input id="searchBtn4WxTemplate" type="button" class="btn btn-default btn-xs" value="查询">
	     					<!-- <input id="deleteBtn4WxTemplate" type="button" class="btn btn-default btn-xs" value="删除"> -->
	     					
	     				</div>
				  		<table id="dbTable4WxTemplate" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4WxTemplate" id="chkBoxAll4WxTemplate">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>模板页面</th>
				  						<th>展示页面</th>
				  						<th>模板名称</th>
				  						<th>模板描述</th>
				  						<th>状态</th>
				  						<th>静态页面</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	<!-- create_wxTemplate end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/wx_template_inc.js"></script>
