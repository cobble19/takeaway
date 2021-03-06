<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html>
  <head>
    <%@include file="../common/head.jsp" %>
    
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/apply2_in_activity_vote.js"></script>
	<link href="<cmn:base/>/css/enterprise/activity_list.css" rel="stylesheet">
  </head>
  <body>
    <OBJECT   classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2   height=0   id=WB   width=0   VIEWASTEXT></OBJECT>  
  	<div class="container">
  		<div class="row">
  			<h2 class="">活动[<span id="activityTitle"></span>]的申请人</h2>
  			
  			<button class="btn btn-default" id="searchBtn4Apply2">
  				查询  <span style="" class="glyphicon glyphicon-search"></span>
  				</button>
  			<button class="btn btn-default" id="print" onclick="javascript: window.print();">
  				打印  <span style="" class="glyphicon glyphicon-print"></span>
  				</button>
  			<button class="btn btn-default" id="exportBtn">
  			导出excel <span style="" class="glyphicon glyphicon-export"></span>
  			</button>
  			<button class="btn btn-default" id="settingFilter4VoteBtn">
  			显示信息 <span style="" class="glyphicon glyphicon-cog"></span>
  			</button>
  			<button class="btn btn-default" id="approveBtn4Vote">
  			审批 <span style="" class="glyphicon glyphicon-check"></span>
  			</button>
  			<%-- <a class="btn btn-success" href='<cmn:base/>/api/apply2/v2/export/xls?activityId=${param.activityId}'>导出</a> --%>
  		</div>
  		
  		<div class="row">
	  		<form class="form-inline">
				<div id="queryCondition" style="">
		 			<div class="form-group">
		 				<label class="control-label" for="startDateTime">开始时间:</label>
		 				<div class="">
		 					<input class="form-control" id="startDateTime" name="startDateTime" placeholder="请输入开始时间">
		 				</div>
		 			</div>
		 			<div class="form-group">
		 				<label class="control-label" for="endDateTime">结束时间:</label>
		 				<div class="">
		 					<input class="form-control" id="endDateTime" name="endDateTime" placeholder="请输入结束时间">
		 				</div>
		 			</div>
		 			
		 			<div class="form-group">
		 				显示投票属性:  
		 				<c:forEach items="${apply2AttrModelPOJOs}" var="apply2AttrModelPOJO" varStatus="st">
			 				<label class="checkbox-inline">
							  <input type="checkbox" name="apply2AttrModelId" id="apply2AttrModelId" 
							  	value="${apply2AttrModelPOJO.apply2AttrModelId}"
							  	<c:if test="${apply2AttrModelPOJO.checked == true}">
							  		checked="checked"
							  	</c:if>
							  	> <c:out value="${apply2AttrModelPOJO.apply2AttrModelName}"></c:out>
							</label>
		 				</c:forEach>
		 			</div>
				</div>
			</form>
  		</div>
  		
  		<div class="row">
	  		<table id="dbTable" class="display table table-striped table-bordered" cellspacing="0" width="100%">
  				<thead>
  					<tr id="trHeader">
  						<!-- <th>申请</th> -->
  						<!-- <th>标识</th>
  						<th>序号</th>
  						<th>名称</th>
  						<th>电话</th>
  						<th>性别</th>
  						<th>备注</th> -->
  					</tr>
  				</thead>
  				<!-- <tfoot>
  					<tr>
  						<th>申请</th>
  						<th>标识</th>
  						<th>标题</th>
  						<th>内容</th>
  					</tr>
  				</tfoot> -->
  			</table>
  		</div>
		<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
  </body>
</html>