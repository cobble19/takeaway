<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/person/apply_in_activity.js"></script>
	<link href="<cmn:base/>/css/enterprise/activity_list.css" rel="stylesheet">
  </head>
  <body>
    <OBJECT   classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2   height=0   id=WB   width=0   VIEWASTEXT></OBJECT>  
  	<div class="container">
  		<div class="row">
  			<h2 class="">活动[<c:out value="${param.activityTitle }"></c:out>]的申请人</h2>
  			<button class="btn btn-success" id="print" onclick="javascript: window.print();">打印</button>
  		<table id="dbTable" class="display table table-striped table-bordered" cellspacing="0" width="100%">
  				<thead>
  					<tr>
  						<!-- <th>申请</th> -->
  						<th>标识</th>
  						<th>名称</th>
  						<th>电话</th>
  						<th>性别</th>
  						<th>备注</th>
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