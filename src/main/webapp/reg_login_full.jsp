<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="page/common/head.jsp" %>

</head>

<body>
	<security:authentication property="principal.username" var="username"/>
			<div class="row" style="border-bottom:1px solid #CCC; ">
			<div class="col-md-8 col-xs-6">
                 <a href='<cmn:base/>' style="font-size:12px;">得味首页</a>&nbsp;&nbsp;
                 <a href="http://#" style="font-size:12px;">活动中心</a>
            </div>
            <div class="col-md-4 col-xs-6">
			<c:if test="${(empty username) or (not empty username and username eq 'anonymousUser')}">
	        <button style="float:right;" type="button" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">注册</button>
	        <button style="float:right; margin-right:10px;" type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#loginModal">登陆</button>
	        </c:if> 
	        <c:if test="${not empty username and (username ne 'anonymousUser')}">
                <a class="sy-dl-wz" href='<cmn:base/>/j_spring_security_logout'>退出</a>
	        	<c:choose>
	        		<c:when test="${sessionScope.userType eq 'PERSON'}">
	        			<a class="sy-dl-wz" href="<cmn:base/>/web/person/usercenter">个人中心</a>
	        		</c:when>
	        		<c:when test="${sessionScope.userType eq 'ENTERPRISE'}">
	        			<a class="sy-dl-wz" href="<cmn:base/>/web/enterprise/usercenter">管理中心</a>
	        		</c:when>
	        		<c:otherwise>
	        			<%-- 用户类型: <c:out value="${sessionScope.userType}"></c:out> --%>
	        		</c:otherwise>
	        	</c:choose>
                <div class="sy-dl-wz">欢迎:
                	<%-- <c:out value="${username}"></c:out> --%><%-- <c:out value="${sessionScope.myUser.nickname}"></c:out> --%>
                	<c:if test="${sessionScope.myUser.nickname != null}">
                		<c:out value="${sessionScope.myUser.nickname}"></c:out>
                	</c:if>
                	<c:if test="${sessionScope.myUser.nickname == null or sessionScope.myUser.nickname eq ''}">
                		<c:out value="${username}"></c:out>
                	</c:if>
                </div>
	        </c:if>
		</div>
	</div>
		
	<div class="row">
	    <!-- 注册 -->
	    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    	<div class="modal-dialog">
	    		<div class="modal-content">
	    			<div class="modal-header">
	    				<button type="button" class="close" data-dismiss="modal" aria-label="关闭">
	    					<span aria-hidden="true">&times;</span>
	    				</button>
	    				<h4 class="modal-title" id="myModalLabel">注册账户</h4>
	    			</div>
	    			<div class="modal-body">
	    				<div role="tabpanel">
	    					<ul class="nav nav-tabs nav-justified" role="tablist" id="myTab">
	    						<li role="presentation" class="active">
	    							<a href="#person" aria-controls="person" role="tab" data-toggle="tab">注册账户</a>
	    						</li>
	    					</ul>
	    					
	    					<div class="tab-content">
	    						<div role="tabpanel" class="tab-pane active" id="person">
									<div class="row">
							  			<h2 class="col-sm-offset-3 col-md-offset-2">个人用户注册</h2>
							  		</div>
							  		<form id="regForm" class="form-horizontal" role="form" action='<cmn:base/>/web/user/person/reg' method="post">
							 			<div class="form-group">
							 				<label class="control-label col-sm-3 col-md-2" for="username">用户名:</label>
							 				<div class="col-sm-9 col-md-6">
							 					<input class="form-control" id="username" name="username" required="required" minlength="1" value="" placeholder="请输入用户名">
							 				</div>
							 			</div>
							 			<div class="form-group">
							 				<label class="control-label col-sm-3 col-md-2" for="password">密码:</label>
							 				<div class="col-sm-9 col-md-6">
							 					<input class="form-control" type="password" id="password" name="password" required="required" value="" placeholder="请输入密码">
							 				</div>
							 			</div>
							 			<div class="form-group">
							 				<label class="control-label col-sm-3 col-md-2" for="email">Email:</label>
							 				<div class="col-sm-9 col-md-6">
							 					<input class="form-control" type="email" id="email" name="email" required="required" value="" placeholder="请输入email">
							 				</div>
							 			</div>
							 			<div class="form-group">
								 			<div class="col-sm-offset-3 col-sm-9 col-md-offset-2 col-md-4">
								  				<button type="submit" class="btn btn-default" id="registerBtn">注册</button>
								 			</div>
							 			</div>
							  		</form>
								</div>
	    						<!-- <div role="tabpanel" class="tab-pane" id="enterprise">enterprise</div> -->
	    					</div>
	    				</div>
	    			</div><!-- modal-body -->
	    			<div class="modal-footer">
	    				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	    			</div><!-- modal-footer -->
	    		</div>
	    	</div>
	    </div>
	    
	    
	    <!-- 登陆 -->
	    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    	<div class="modal-dialog">
	    		<div class="modal-content">
	    			<div class="modal-header">
	    				<button type="button" class="close" data-dismiss="modal" aria-label="关闭">
	    					<span aria-hidden="true">&times;</span>
	    				</button>
	    				<h4 class="modal-title" id="myModalLabel">账号登陆</h4>
	    			</div>
	    			<div class="modal-body">
	    				<div role="tabpanel">
	    					<ul class="nav nav-tabs nav-justified" role="tablist" id="loginTab">
	    						<li role="presentation" class="active">
	    							<a href="#loginPerson" aria-controls="login" role="tab" data-toggle="tab">登陆</a>
	    						</li>
	    						<!-- <li role="presentation">
	    							<a href="#loginEnterprise" aria-controls="loginEnterprise" role="tab" data-toggle="tab">企业登陆</a>
	    						</li> -->
	    					</ul>
	    					
	    					<div class="tab-content">
	    						<div role="tabpanel" class="tab-pane active" id="loginPerson">
									<form action="<cmn:base/>/j_spring_security_check" id="loginForm" method="post">
										<table>
											<tbody>
												<tr>
													<td class="login-label form-lable">用户名:</td>
													<td>
														<input class="login-input form-control" id="name" name="j_username" type="text" placeholder="登陆账号"/>
													</td>
												</tr>
												<tr>
													<td class="login-label form-lable">密码:</td>
													<td>
														<input class="login-input form-control" id="password" name="j_password" type="password" placeholder="密码"/>
													</td>
												</tr>
												<tr>
													<td>
													</td>
													<td>
														<input class="login-btn btn btn-primary" id="loginBtn" title="登陆" value="登陆" type="submit"/>
													</td>
												</tr>
											</tbody>
										</table>
									</form>
								</div>
	    						<!-- <div role="tabpanel" class="tab-pane" id="loginEnterprise">enterprise</div> -->
	    					</div>
	    				</div>
	    			</div><!-- modal-body -->
	    			<div class="modal-footer">
	    				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	    				<!-- <button type="button" class="btn btn-primary">创建</button> -->
	    			</div><!-- modal-footer -->
	    		</div>
	    	</div>
	    </div>
	    
	    
	
	
	
	
	</div>
	
</body>

</html>

