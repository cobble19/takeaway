<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cmn" uri="/WEB-INF/tlds/common.tld" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>创建活动</title>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<cmn:base/>/jquery/jquery-1.11.1.min.js"></script>
    <script src="<cmn:base/>/jquery/jquery-migrate-1.2.1.min.js"></script>
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
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/lang/zh-cn/zh-cn.js"></script>
    
    <!-- 公共的函数 -->
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/common.js"></script>
    <link href="<cmn:base/>/css/common.css" rel="stylesheet">
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/activity_detail.js"></script>
    
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
  </head>
  <body>
  
	<div class="container">
		<div class="row" style="border-bottom:1px solid #CCC; ">
			<div class="col-md-1">
                 <a href="http://www.deweiyizhan.com" style="font-size:14px;">得味首页</a>
            </div>
            <div class="col-md-1">
                 <a href="http://#" style="font-size:14px;">活动中心</a>
            </div>
            <div class="col-md-8"></div>
            <div class="col-md-2">
          		<c:if test="${(empty username) or (not empty username and username eq 'anonymousUser')}">
	        <%-- <a href='<cmn:base/>/page/person/register.jsp' class="btn btn-success">个人注册</a>
	        <a href='<cmn:base/>/page/enterprise/register.jsp' class="btn btn-success">企业注册</a> --%>
	        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">注册</button>
	        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#loginModal">登陆</button>
        	<%-- <a href="<cmn:base/>/login.jsp" class="btn btn-info">登录</a> --%>
        	<%-- <a href="<cmn:base/>/login.jsp" class="sy-dl-wz">登录</a> --%>
        </c:if> 
        <c:if test="${not empty username and (username ne 'anonymousUser')}">
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
        			<%-- 用户类型: <c:out value="${sessionScope.userType}"></c:out> --%>
        		</c:otherwise>
        	</c:choose>
        </c:if>
            
            
            </div>
		</div>
		<div class="row">
            <h4 class="" style="text-align: bottom; font-weight:bolder; font-size:36px; height:100px; line-height:100px;"><span id="title"></span></h4>
		</div>
		<div class="row">
        发布者:**********
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
									<label for="username">姓名: </label> <input id="username"
										name="username" placeholder="姓名" class="form-control" />
								</div>
								<div class="form-group">
									<label for="phone">手机号: </label> <input id="phone" name="phone"
										placeholder="手机号" class="form-control" />
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