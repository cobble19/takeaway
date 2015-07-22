<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../../page/common/head.jsp" %>
    
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/user_center.js"></script>
	<link href="<cmn:base/>/css/enterprise/activity_list.css" rel="stylesheet">
	
	<link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
    
    <script>
    	$(document).ready(function() {
    		
    	$('#sidebar a').click(function() {
			$('#sidebar a').each(function() {
				$(this).removeClass('active');
			})
			$this = $(this);
			$this.addClass('active');
			var contentId = $this.attr('href').substring(1);
			console.log('contentId: ' + contentId);
			$('#uc_content > div').each(function(i, e) {
				$(this).hide();
			})
			$('#' + contentId).show();
		});
		
		$('#sidebar a[href=#profile]').trigger('click');
    		
    	})
    </script>
  </head>
  <body>
  	<div class="container">
	  	<%@include file="../../reg_login_full.jsp" %>
  		<%-- <div class="row">
  			<div class="col-md-12">
		        <c:if test="${(empty username) or (not empty username and username eq 'anonymousUser')}">
		        	<a href="<cmn:base/>/spring_security_login">登录</a>
		        </c:if> 
		        <c:if test="${not empty username}">
		        	欢迎： <c:out value="${username}"></c:out>
		        	<c:choose>
		        		<c:when test="${sessionScope.userType eq 'PERSON'}">
		        			<a  href="<cmn:base/>/web/person/usercenter">个人中心</a>
		        			<a href='<cmn:base/>/j_spring_security_logout'>退出</a>
		        		</c:when>
		        		<c:when test="${sessionScope.userType eq 'ENTERPRISE'}">
		        			<a href="<cmn:base/>/web/enterprise/usercenter">个人中心</a>
		        			<a href="<cmn:base/>/j_spring_security_logout">退出</a>
		        		</c:when>
		        		<c:otherwise>
		        			userType 可能错误。<c:out value="${sessionScope.userType}"></c:out>
		        		</c:otherwise>
		        	</c:choose>
		        </c:if>
  			</div> --%>
		   <%--  <div class="sy-dl">
		        <div class="sy-dl-img"></div>
		        <c:if test="${(empty username) or (not empty username and username eq 'anonymousUser')}">
		        	<a href="<cmn:base/>/spring_security_login" class="sy-dl-wz">登录</a>
		        </c:if> 
		        <c:if test="${not empty username}">
		        	欢迎： <c:out value="${username}"></c:out>
		        	<c:choose>
		        		<c:when test="${sessionScope.userType eq 'PERSON'}">
		        			<a class="sy-dl-wz" href="<cmn:base/>/web/person/usercenter">个人中心</a>
		        			<a href='<cmn:base/>/j_spring_security_logout'>退出</a>
		        		</c:when>
		        		<c:when test="${sessionScope.userType eq 'ENTERPRISE'}">
		        			<a class="sy-dl-wz" href="<cmn:base/>/web/enterprise/usercenter">个人中心</a>
		        			<a href="<cmn:base/>/j_spring_security_logout">退出</a>
		        		</c:when>
		        		<c:otherwise>
		        			userType 可能错误。<c:out value="${sessionScope.userType}"></c:out>
		        		</c:otherwise>
		        	</c:choose>
		        </c:if>
		    </div> 
  		</div>--%>
  		<div class="row uc-border" style="margin-top:50px;">
  			<div class="col-md-3 " id="sidebar">
	  				<%--<div class="" style="padding-left: 5px;">
	  					<br/>
	  					<jsp:include page="reg_login.jsp"></jsp:include> 
		  			</div>--%>
		          <div class="list-group" style="margin-top:10px;">
		            <a href="#profile" class="list-group-item active">个人资料</a>
		            <a href="#create_activity" class="list-group-item">发起活动</a>
		            <a href="#update_password" class="list-group-item">修改密码</a>
		          </div> 				
	     	</div>
	     	<div class="col-md-9">
	     		<div id="uc_content" style="padding-top: 10px;">
	     			<div id="profile">
	     				<h3>个人资料</h3>
	     				<label>名称： </label><c:out value="${myUser.username }"></c:out>/<c:out value="${myUser.userId }"></c:out><br/>
	     				<label>昵称： </label><c:out value="${myUser.nickname }"></c:out><br/>
	     				<!-- <label>密码： </label><button id="pwdChg4OpenDialog" class="btn btn-default">修改密码</button> -->
	     				
	     			</div>	<!-- profile end -->
	     			<div id="create_activity">
	     				<h3>发起活动</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" href='<cmn:base/>/page/enterprise/activity.jsp'>添加活动</a>
	     					
	     					<input id="searchBtn" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll" id="chkBoxAll">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>标题</th>
				  						<th>开始时间</th>
				  						<th>截止时间</th>
				  						<th>期限</th>
				  						<th>状态</th>
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
	     			</div>	<!-- create_activity end -->
	     			
					<div id="update_password">
						<div class="row">
							<form class="form-inline" id="pwdForm" role="form" action='' method="post">
					  			<input type="hidden" id="userId" name="userId" value="${myUser.userId}"/>
					 			<div class="form-group">
					 				<label class="" for="passwordOld">旧密码:</label>
				 					<input type="password" class="form-control" id="passwordOld" name="passwordOld" minlength="2" required="required" placeholder="请输入旧密码">
					 			</div>
					 			<div class="form-group ">
					 				<label class="" for="password">新密码:</label>
					 				<input type="password" class="form-control" id="password" name="password" minlength="2" required="required" placeholder="请输入新密码">
					 			</div>
					 			<!-- <button type="button" class="btn btn-default" id="pwdChg">修改密码</button> -->
					 			<div class="form-group">
						 			<div class="">
						  				<button type="button" class="btn btn-default" id="pwdChg">修改密码</button>
						 			</div>
					 			</div>
					  		</form>
						</div>
					</div>	<!-- pwd change end -->
						
	     		</div>
	     	</div>
  		</div>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  		
	</div>
  </body>
</html>