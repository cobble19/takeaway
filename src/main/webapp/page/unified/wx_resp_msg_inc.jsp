<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="wx_resp_msg">
	     				<h3>关键字回复</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_resp_msg_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4WxRespMsg" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4WxRespMsg" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4WxRespMsg" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4WxRespMsg" id="chkBoxAll4WxRespMsg">全选</th>
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
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_resp_msg_inc.js"></script>
