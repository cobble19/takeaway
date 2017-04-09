<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="wx_resp_msg">
	     				<h3><span style="color: green;" class="glyphicon glyphicon-globe"></span> 系统关键字回复</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_resp_msg_add.jsp?sgType=0'>添加</a>
	     					
	     					<input id="searchBtn4WxRespMsgS" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4WxRespMsgS" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4WxRespMsgS" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4WxRespMsgS" id="chkBoxAll4WxRespMsgS">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>接受关键字</th>
				  						<th>回复关键字</th>
				  						<th>信息类别</th>
				  						<th>用户ID</th>
				  						<th>公众号APPID</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
				  			
	     				<h3><span style="color: green;" class="glyphicon glyphicon-wrench"></span> 定制关键字回复</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_resp_msg_add.jsp?msgType=1'>添加</a>
	     					
	     					<input id="searchBtn4WxRespMsgC" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4WxRespMsgC" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4WxRespMsgC" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4WxRespMsgC" id="chkBoxAll4WxRespMsgC">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>接受关键字</th>
				  						<th>回复关键字</th>
				  						<th>信息类别</th>
				  						<th>用户ID</th>
				  						<th>公众号APPID</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
				  			
	     			</div>	<!-- wx_resp_msg end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_resp_msg_inc_s.js"></script>
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_resp_msg_inc_c.js"></script>
