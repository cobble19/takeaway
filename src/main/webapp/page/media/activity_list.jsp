<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/activity_list.js"></script>
	<link href="<cmn:base/>/css/enterprise/activity_list.css" rel="stylesheet">
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">活动申请</h2>
  		<table id="dbTable" class="display table table-striped table-bordered" cellspacing="0" width="100%">
  				<thead>
  					<tr>
  						<th>申请</th>
  						<th>标识</th>
  						<th>标题</th>
  						<th>内容</th>
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