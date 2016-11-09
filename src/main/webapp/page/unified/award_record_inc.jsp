<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="award_record">
	     				<h3>奖品记录管理</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/award_record_add.jsp'>添加</a>
	     					
	     					<input id="searchBtn4AwardRecord" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4AwardRecord" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4AwardRecord" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4AwardRecord" id="chkBoxAll4AwardRecord">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>互动ID</th>
				  						<th>奖品ID</th>
				  						<th>用户ID</th>
				  						<th>活动</th>
				  						<th>奖品</th>
				  						<th>用户</th>
				  						<th>获奖时间</th>
				  						<th>创建时间</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  			</table>
	     			</div>	<!-- award_record end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/award_record_inc.js"></script>
