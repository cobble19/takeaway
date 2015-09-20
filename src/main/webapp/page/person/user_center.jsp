<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../../page/common/head.jsp" %>
    
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/person/user_center.js"></script>
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
    		
    		/* $('#sidebar div a').click(function() {
    			var url = $(this).attr('contentPage');
    			$.ajax({
    	    		"url" : url,
    	    		"type" : "GET",
    	    		"headers" : {
    	    			"Content-Type" : "application/json"
    	    		},
    	            success: function(data, textStatus, jqXHR ) {
    	            	$('#content').html(data);
    	            	alert('添加成功！')
    	            },
    	            error: function(jqXHR, textStatus, errorThrown) {
    	            	alert('Load Error!');
    	            },
    	            complete: function(jqXHR, textStatus) {
    	            	console.log('Ajax complete.');
    	            }
    	    	});	// ajax
    		}) */
    	})
    </script>
  </head>
  <body>
  	<div class="container">
        <%@include file="../../reg_login_full.jsp" %>
  		<%-- <div class="row">
		     <div class="sy-dl">
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
  			<div class="col-md-3" id="sidebar">
		          <div class="list-group" style="margin-top:10px;">
		            <a href="#profile" class="list-group-item active">个人资料</a>
		            <a href="#update_password" class="list-group-item">修改密码</a>
		            <a href="#create_activity" class="list-group-item">线下活动</a>
		            <a href="#interactive" class="list-group-item">互动活动</a>
		          </div>
	     	</div>
	     	<div class="col-md-9">
	     		<div id="uc_content" style="padding-top: 10px;">
	     			<div id="profile">
	     				<h3 class="col-md-12" style="margin-bottom:20px;">个人资料</h3>
	     				<label>名称： </label><c:out value="${myUser.username }"></c:out><br/>
	     				<label>昵称： </label><c:out value="${myUser.nickname }"></c:out>
	     			</div>
	     			<div id="create_activity">
	     				<h3>线下活动</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<input id="searchBtn" type="button" class="btn btn-default btn-xs" value="查询">
	     				</div>
				  		<table id="dbTable" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<!-- <th>申请</th> -->
				  						<th>序号</th>
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
	     			
	     			<div id="interactive">
	     				<h3>互动活动</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<input id="searchBtn4Interactive" type="button" class="btn btn-default btn-xs" value="查询">
	     				</div>
				  		<table id="dbTable4Interactive" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<!-- <th>申请</th> -->
				  						<th>序号</th>
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