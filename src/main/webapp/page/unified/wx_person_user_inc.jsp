<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="wx_person_user">
	     				<h3>微信个人用户管理</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_person_user_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4WxPersonUser" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4WxPersonUser" type="button" class="btn btn-default btn-xs" value="删除">
	     					<input id="addUserInfosBtn4WxPersonUser" type="button" class="btn btn-default btn-xs" value="创建所有的粉丝">
	     					<input id="addTagBtn4WxPersonUser" type="button" class="btn btn-default btn-xs" value="打标签">
	     					<label for="tagId">Tag Id: </label><input style="border: solid 1px #ccc;" class="input-sm" type="text" id="tagId" value="101"/>
	     					
	     				</div>
				  		<table id="dbTable4WxPersonUser" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4WxPersonUser" id="chkBoxAll4WxPersonUser">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>USER ID</th>
				  						<th>昵称</th>
				  						<th>OPEN ID</th>
				  						<th>授权者APP ID</th>
				  						<th>代理OPEN ID</th>
				  						<th>代理授权者APP ID</th>
				  						<th>性别</th>
				  						<th>语言</th>
				  						<th>城市</th>
				  						<th>省</th>
				  						<th>国家</th>
				  						<th>关注</th>
				  						<th>关注时间</th>
				  						<th>权限</th>
				  						<th>UNION ID</th>
				  						<th>备注</th>
				  						<th>GROUP ID</th>
				  						<th>TAG IDs</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	<!-- wx_person_user end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_person_user_inc.js"></script>
