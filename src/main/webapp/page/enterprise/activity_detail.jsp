<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="page/common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/activity_detail.js"></script>
    
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
  </head>
  <body>
  
	<div class="container">
		<div class="row" style="border-bottom:1px solid #CCC; ">
			<div class="col-md-9 col-xs-6">
                 <a href="http://www.deweiyizhan.com" style="font-size:12px;">得味首页</a>&nbsp;&nbsp;
                 <a href="http://#" style="font-size:12px;">活动中心</a>
            </div>
            <div class="col-md-3 col-xs-6">
          		<c:if test="${(empty username) or (not empty username and username eq 'anonymousUser')}">
	        <%-- <a href='<cmn:base/>/page/person/register.jsp' class="btn btn-success">个人注册</a>
	        <a href='<cmn:base/>/page/enterprise/register.jsp' class="btn btn-success">企业注册</a> --%>
	        <button style="float:right;" type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">注册</button>
	        <button style="float:right; margin-right:10px;" type="button" class="btn btn-info" data-toggle="modal" data-target="#loginModal">登陆</button>
        	<%-- <a href="<cmn:base/>/login.jsp" class="btn btn-info">登录</a> --%>
        	<%-- <a href="<cmn:base/>/login.jsp" class="sy-dl-wz">登录</a> --%>
        </c:if> 
        <c:if test="${not empty username and (username ne 'anonymousUser')}">
        	<c:choose>
        		<c:when test="${sessionScope.userType eq 'PERSON'}">
        			<a class="sy-dl-wz" href='<cmn:base/>/j_spring_security_logout'>退出</a>
                    <a class="sy-dl-wz" href="<cmn:base/>/web/person/usercenter">个人中心</a>  			
        		</c:when>
        		<c:when test="${sessionScope.userType eq 'ENTERPRISE'}">
        			<a class="sy-dl-wz" href="<cmn:base/>/j_spring_security_logout">退出</a>
                    <a class="sy-dl-wz" href="<cmn:base/>/web/enterprise/usercenter">个人中心</a>
        		</c:when>
        		<c:otherwise>
        			<%-- 用户类型: <c:out value="${sessionScope.userType}"></c:out> --%>
        		</c:otherwise>
        	</c:choose>
        	<div class="sy-dl-wz">欢迎:<c:out value="${username}"></c:out></div>
        </c:if>
            
            
            </div>
		</div>
		<div class="row">
			<div class="col-md-12" >
            	<span style=" margin-top:50px; margin-bottom:50px; display:block;"><h3 id="title"></h3></span>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
            	<h5 style=" margin-bottom:10px; display:block;">发布者:<span id="publisher"></span></h5>
			</div>
        </div>	
  		<div class="row uc-border">
  			<input type="hidden" id="activityId" name="activityId" value="<%=request.getParameter("activityId") %>"/>
  			<div class="row">
  				<div class="col-md-9">
  					<!-- <h4 class="" style="text-align: bottom;"><span id="title"></span></h4>
  					<hr/> -->
		  			<p style=" margin:10px; border-right:1px solid #CCC;" id="content"></p>
		  			<!-- <hr/> -->
  				</div>
  				<div class="col-md-3">
  				<fieldset>
					<legend>申请活动</legend>
						<form id="applyForm" class="form-inline">
							
								<div class="form-group">
									<label for="username">姓名: </label> 
									<input id="username" name="username" required="required" value="" placeholder="姓名" class="form-control" />
								</div>
								<div class="form-group">
									<label for="phone">手机号: </label> 
									<input type="tel" id="phone" name="phone"  required="required" value="" placeholder="手机号" class="form-control" />
								</div>
								<input type="submit" id="applyBtn" value="申请参加"
									class="btn btn-default" />
							
						</form>
				</fieldset>
				</div>
  			</div>
  			

			<!-- <hr/>
  			<p id="content"></p>
  			<hr/> -->
  		</div>
  		
		<footer><br/><p>&copy; 版权所有</p></footer>
	</div> <!-- container -->
	
    
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
    							<a href="#person" aria-controls="person" page="page/person/register.jsp" role="tab" data-toggle="tab">注册账户</a>
    						</li>
    						<!-- <li role="presentation">
    							<a href="#enterprise" aria-controls="enterprise" page="page/enterprise/register.jsp" role="tab" data-toggle="tab">企业</a>
    						</li> -->
    					</ul>
    					
    					<div class="tab-content">
    						<div role="tabpanel" class="tab-pane active" id="person">person</div>
    						<!-- <div role="tabpanel" class="tab-pane" id="enterprise">enterprise</div> -->
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
    							<a href="#loginPerson" aria-controls="login" page="login.jsp" role="tab" data-toggle="tab">登陆</a>
    						</li>
    						<!-- <li role="presentation">
    							<a href="#loginEnterprise" aria-controls="loginEnterprise" page="login.jsp" role="tab" data-toggle="tab">企业登陆</a>
    						</li> -->
    					</ul>
    					
    					<div class="tab-content">
    						<div role="tabpanel" class="tab-pane active" id="loginPerson">person</div>
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
	<!-- 登陆结束 -->
  </body>
</html>
