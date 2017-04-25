<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="wx_resp_msg">
	     				<h3><span style="color: green;" class="glyphicon glyphicon-globe"></span> 系统关键字回复</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<%-- <a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_resp_msg_add.jsp?msgType=0'>添加</a> --%>
	     					
	     					<button  id="searchBtn4WxRespMsgS" type="button" class="btn btn-default btn-xs"
	     						data-toggle="tooltip" data-placement="top" title="查询" value="查询">
	     						<!-- 开启  --><span class="glyphicon glyphicon-search" ></span>
	     					</button>
	     					<!-- <input id="deleteBtn4WxRespMsgS" type="button" class="btn btn-default btn-xs" value="删除"> -->
	     					<button  id="enableBtn4WxRespMsgS" style="display: none;" type="button" class="btn btn-default btn-xs"
	     						data-toggle="tooltip" data-placement="top" title="开启" value="开启">
	     						<!-- 开启  --><span class="glyphicon glyphicon-ok" ></span>
	     					</button>
	     					<button  id="disableBtn4WxRespMsgS" style="display: none;" type="button" class="btn btn-default btn-xs"
	     						data-toggle="tooltip" data-placement="top" title="停用" value="停用">
	     						<!-- 停用  --><span class="glyphicon glyphicon-ban-circle" ></span>
	     					</button>
	     					
	     				</div>
	     				
				  		<table id="dbTable4WxRespMsgGather" class="display table table-striped table-bordered" cellspacing="0" width="100%">
			  				<thead>
			  					<tr>
			  						<th><!-- <input type="checkbox" name="chkBoxAll4WxRespMsgGather" id="chkBoxAll4WxRespMsgGather">全选 --></th>
			  						<th>序号</th>
			  						<th>信息类别</th>
			  						<th>用户ID</th>
			  						<th>公众号APPID</th>
			  						<th>有效标志</th>
			  						<th>操作</th>
			  					</tr>
			  				</thead>
				  		</table>
				  		<!-- <div id="dbTable4WxRespMsgSDiv">
				  			
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
			  						<th>有效标志</th>
			  						<th>创建时间</th>
			  						<th>操作</th>
			  					</tr>
			  				</thead>
				  		</table>
				  		</div> -->
				  			
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
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_resp_msg_inc_s.js?v=3"></script>
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_resp_msg_inc_c.js"></script>
