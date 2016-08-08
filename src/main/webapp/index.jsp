<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="page/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="page/common/head.jsp"%>
<%-- <jsp:include page="page/common/head_index.jsp"></jsp:include> --%>
<!-- only for index page -->
<!--<script src="<cmn:base/>/js/jquery-1.2.4b.js" type="text/javascript"></script>-->
<script src="<cmn:base/>/js/ui.core.js" type="text/javascript"></script>
<script src="<cmn:base/>/js/ui.tabs.js" type="text/javascript"></script>

<script type="text/javascript" charset="utf-8"
	src="<cmn:base/>/js/activity_top.js"></script>
<link href="<cmn:base/>/css/activity_top.css" rel="stylesheet">

</head>



<body>
	<security:authentication property="principal.username" var="username" />

	<div class="container-fluid">
		<%@include file="reg_login.jsp"%>


		<%-- <div class="row">
        <div class="col-md-1 col-xs-6 logo"></div>
        <div class="col-md-3 col-xs-6 col-md-offset-8">
		<c:if test="${(empty username) or (not empty username and username eq 'anonymousUser')}">
	        <a href='<cmn:base/>/page/person/register.jsp' class="btn btn-success">个人注册</a>
	        <a href='<cmn:base/>/page/enterprise/register.jsp' class="btn btn-success">企业注册</a>
	        <button style="float:right;" type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">注册</button>
	        <button style="float:right; margin-right:10px;" type="button" class="btn btn-info" data-toggle="modal" data-target="#loginModal">登陆</button>
        	<a href="<cmn:base/>/login.jsp" class="btn btn-info">登录</a>
        	<a href="<cmn:base/>/login.jsp" class="sy-dl-wz">登录</a>
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
        			用户类型: <c:out value="${sessionScope.userType}"></c:out>
        		</c:otherwise>
        	</c:choose>
            <div class="sy-dl-wz">欢迎:<c:out value="${username}"></c:out></div>
        </c:if>
	</div>
</div> --%>

		<div class="row" style="margin-left:45px; margin-right:15px;">

			<div class="col-md-10 col-md-offset-1 col-xs-12">
						<div class="row hidden-xs">
							<div class="qima col-md-2" style="margin-left: 60px;"></div>
							<div class="sywz1 col-md-4"></div>
							<div class="sywz2 col-md-4"></div>
						</div>
			<!-- <div class="sy-dl">
        <div class="sy-dl-img"></div>
        
        
    </div> -->
			<!-- 注册 -->
			<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
    						<li role="presentation">
    							<a href="#enterprise" aria-controls="enterprise" page="page/enterprise/register.jsp" role="tab" data-toggle="tab">企业</a>
    						</li>
    					</ul>
    					
    					<div class="tab-content">
    						<div role="tabpanel" class="tab-pane active" id="person">person</div>
    						<div role="tabpanel" class="tab-pane" id="enterprise">enterprise</div>
    					</div>
    				</div>
    			</div>modal-body
    			<div class="modal-footer">
    				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    				<button type="button" class="btn btn-primary">创建</button>
    			</div>modal-footer
    		</div>
    	</div>
    </div> -->


			<!-- 登陆 -->
			<!-- <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
    					</ul>
    					
    					<div class="tab-content">
    						<div role="tabpanel" class="tab-pane active" id="loginPerson">person</div>
    						<div role="tabpanel" class="tab-pane" id="loginEnterprise">enterprise</div>
    					</div>
    				</div>
    			</div>modal-body
    			<div class="modal-footer">
    				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    			</div>modal-footer
    		</div>
    	</div>
    </div> -->



		</div>
		<div style="display: none;"><%@include file="bottom.jsp"%></div>
	</div>
</body>
</html>
