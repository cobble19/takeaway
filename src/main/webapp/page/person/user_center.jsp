<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
	<link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
    <%@include file="../../page/common/head.jsp" %>
    
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/person/user_center.js"></script>
	<link href="<cmn:base/>/css/enterprise/activity_list.css" rel="stylesheet">
    
   
     
    <script>
    	$(document).ready(function() {
    		$('#sidebar a').click(function() {
    			$('#sidebar a').each(function() {
    				$(this).parent('li').removeClass('active');
    			})
    			$this = $(this);
    			$this.parent('li').addClass('active'); 
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
  	<div class="container-fluid">
        <div  class="row" style=" height:4px; background-color:#44b549;"></div>
        <%@include file="../../reg_login_full.jsp" %>
        <div  class="row" style=" height:36px; background-color:#e7e8eb;"></div>
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
        
        
  		<div class="row" style="min-height:500px; border:1px solid #CCC;">
  			<div class="col-md-3 col-xs-4" style="margin-top:30px; margin-left:-15px; margin-right:-15px; text-align:center;" id="sidebar">
                  <ul class="nav nav-pills nav-stacked" style="padding:10px 0px; border-bottom:1px solid #e7e7eb;">
                      <li style="margin-left:-20px; margin-bottom:20px;"><h5><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;帐号中心</h5></li>
                      <li role="presentation" class="active"><a href="#profile"><h6>基本资料</h6></a></li>
                      <li role="presentation"><a href="#update_password"><h6>修改密码</h6></a></li>
                  </ul>
                  <ul class="nav nav-pills nav-stacked" style="padding:10px 0px; border-bottom:1px solid #e7e7eb;">
                      <li style="margin-left:-20px; margin-bottom:20px;"><h5><span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>&nbsp;活动汇总</h5></li>
                      <li role="presentation"><a href="#create_activity"><h6>报名征集类</h6></a></li>
                      <li role="presentation"><a href="#interactive"><h6>游戏互动类</h6></a></li>
                      <li role="presentation"><a href="#prize"><h6>奖品查询</h6></a></li>
                  </ul>                                
        </div>           
        
  		<!-- <div class="row" style="margin-top:20px; border:1px solid #CCC;">
  			<div class="col-md-3 col-xs-12" id="sidebar">
		          <div class="list-group" style="margin-top:10px;">
		            <a href="#profile" class="list-group-item active col-md-12 col-xs-6">个人资料</a>
		            <a href="#update_password" class="list-group-item col-md-12 col-xs-6">修改密码</a>
		            <a href="#create_activity" class="list-group-item col-md-12 col-xs-6">线下活动</a>
		            <a href="#interactive" class="list-group-item col-md-12 col-xs-6">线上互动</a>
		            <a href="#prize" class="list-group-item col-md-12 col-xs-6">奖品查询</a>
		          </div>
	     	</div>-->
	     	<div class="col-md-9 col-xs-8" style="min-height:500px; border-left:1px solid #e7e7eb;">
	     		<div id="uc_content" style="padding-top:40px; padding-left:20px; margin-right:-30px;">
	     			<div id="profile">
	     				<strong>基本资料</strong>
	     				<%-- <label>名称： </label><c:out value="${myUser.username }"></c:out><br/>
	     				<label>昵称： </label><c:out value="${myUser.nickname }"></c:out> --%>
	     				<div class=" form-inline" style="margin-bottom:10px; margin-top:15px;">
	     					<label class="" for="username">帐　　号：</label>
	     					<%-- <input type="text" name="username" id="username" value="${myUser.username}" class="form-control"> --%>
	     					 <c:out value="${myUser.username }"></c:out>/<c:out value="${myUser.userId }"></c:out><br/>
	     				</div>
	     				<div class=" form-inline col-md-12" style="margin-bottom:10px;">
	     					<label>昵　　称：</label><input type="text" name="nickname" id="nickname" value="${myUser.nickname}" class="form-control input-sm"><br/>
	     				</div>
	     				<div class=" form-inline col-md-12" style="margin-bottom:10px;">
	     					<label>电子邮箱：</label><input type="text" name="email" id="email" value="${myUser.email}" class="form-control input-sm"><br/>
	     				</div>
	     				<button id="updateInfoBtn" class="col-md-1 btn btn-default" style="margin-left:20px;">修改</button>
	     			</div>
	     			<div id="create_activity">
	     				<strong>报名征集类</strong>
	     				<div style=" height:50px; line-height:50px;">
	     					<input id="searchBtn" type="button" class="btn btn-info btn-xs" value="查询">
	     				</div>
                        <div class="table-responsive">
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
	     			</div>
	     			
	     			<div id="interactive">
	     				<strong>游戏互动类</strong>
	     				<div style=" height:50px; line-height:50px;">
	     					<input id="searchBtn4Interactive" type="button" class="btn btn-info btn-xs" value="查询">
	     				</div>
                        <div class="table-responsive">
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
	     			</div>
	     			
	     			<div id="prize">
	     				<strong>奖品查询</strong>
	     				<div style=" height:50px; line-height:50px;">
	     					<input id="searchBtn4Prize" type="button" class="btn btn-info btn-xs" value="查询">
	     				</div>
                        <div class="table-responsive">
				  		<table id="dbTable4Prize" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<!-- <th>申请</th> -->
				  						<th>序号</th>
				  						<th>关系标识</th>
				  						<th>用户标识</th>
				  						<th>活动标识</th>
				  						<th>活动名称</th>
				  						<th>奖品信息</th>
				  						<th>验证码</th>
				  						<th>奖品状态</th>
				  						<!-- <th>是否过期</th> -->
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
	     			
					<div id="update_password">
                        <strong>修改密码</strong>
						<div style=" margin-top:15px;">
							<form id="pwdForm" role="form" action='' method="post">
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