<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cmn" uri="/WEB-INF/tlds/common.tld" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人用户中心</title>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<cmn:base/>/jquery/jquery-1.11.1.min.js"></script>
    <script src="<cmn:base/>/jquery/jquery-migrate-1.2.1.min.js"></script>
    <!-- jQuery Validation -->
    <script src="<cmn:base/>/js/thirdpart/jquery-validation-1.13.1/jquery.validate.min.js"></script>
    <script src="<cmn:base/>/js/thirdpart/jquery-validation-1.13.1/additional-methods.min.js"></script>
    <script src="<cmn:base/>/js/thirdpart/jquery-validation-1.13.1/jquery.validate.message.zh_cn.js"></script>
    <!-- jQuery UI -->
    <link href="<cmn:base/>/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet">
    <script src="<cmn:base/>/jquery-ui-1.11.4/jquery-ui.min.js"></script>
    <!-- Bootstrap -->
    <link href="<cmn:base/>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<cmn:base/>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/media/js/jquery.dataTables.min.js"></script>
    <link href="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/media/css/jquery.dataTables.min.css" rel="stylesheet">
   
    <script src="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/integration/bootstrap/3/dataTables.bootstrap.js"></script>
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/integration/bootstrap/3/dataTables.bootstrap.css">
    
    
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/person/user_center.js"></script>
	<link href="<cmn:base/>/css/enterprise/activity_list.css" rel="stylesheet">
    
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
  		<div class="row">
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
  		</div>
  		<div class="row">
  			<div class="col-md-3" id="sidebar">
		          <div class="list-group">
		            <a href="#profile" class="list-group-item active">个人资料</a>
		            <a href="#create_activity" class="list-group-item">申请活动</a>
		          </div>
	     	</div>
	     	<div class="col-md-9">
	     		<div id="uc_content">
	     			<div id="profile">
	     				<h3>个人资料</h3>
	     				<label>名称： </label><c:out value="${myUser.username }"></c:out>
	     			</div>
	     			<div id="create_activity">
	     				<h3>参加活动</h3>
	     				<div>
	     					<input id="searchBtn" type="button" class="btn btn-default" value="查询">
	     				</div>
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
	     		</div>
	     	</div>
  		</div>
  		
  		<div id="progress">数据加载中。。。</div>
  		
	</div>
  </body>
</html>