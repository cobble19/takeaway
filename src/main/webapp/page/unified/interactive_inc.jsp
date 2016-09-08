<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="create_interactive">
	     				<strong>数字竞猜</strong>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-success btn-xs" target="_blank" href='<cmn:base/>/page/unified/interactive.jsp'>添加</a>
	     					
	     					<input id="searchBtn4Interactive" type="button" class="btn btn-info btn-xs" value="查询">
	     					<input id="deleteBtn4Interactive" type="button" class="btn btn-danger btn-xs" value="删除">
	     					
	     				</div>
                        <div class="table-responsive">
				  		<table id="dbTable4Interactive" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4Interactive" id="chkBoxAll4Interactive">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>名称</th>
				  						<th>开始时间</th>
				  						<th>截止时间</th>
				  						<th>期限</th>
				  						<th>状态</th>
				  						<th>奖品</th>
				  						<th>答案</th>
				  						<th>内容简介</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  				<!-- <tfoot>
				  					<tr>
				  						<th>No.</th>
				  						<th>标识</th>
				  						<th>标题</th>
				  						<th>内容</th>
				  					</tr>
				  				</tfoot> -->
				  			</table>
                            </div>
	     			</div>	<!-- create_interactive end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/interactive_inc.js"></script>
