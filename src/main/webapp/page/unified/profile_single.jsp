<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cobble.takeaway.util.CommonConstant" %>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
		<%@include file="../../page/common/head.jsp"%>
		
		<link href="<cmn:base/>/css/unified/activity_list.css" rel="stylesheet">
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/progress_dialog.js"></script>
		<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/profile_single.js"></script>
	</head>
	
  <body style="padding-top: 100px;">
  	<%@include file="../../reg_login.jsp" %>
  	<div class="container-fluid">
        <div  class="row" style=" height:4px; background-color:#44b549;"></div>
        <div  class="row" style=" height:36px; background-color:#e7e8eb;"></div>
  		<div class="row" style="min-height:500px; border:1px solid #CCC;">
  			<div class="col-md-3 col-xs-4" style="padding:30px 0px 0px 0px; border-right:1px solid #e7e7eb; text-align:center;" id="sidebar">
                  <%@include file="menu_left.jsp" %>
            </div>
	     	<div class="col-md-9 col-xs-8" style="min-height:500px;">
	     		<div id="uc_content" style="padding-top:40px;">
						     		
					<div id="profile">
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-success">
									<!-- Default panel contents -->
									<div class="panel-heading">
										<p class="h5" style="font-weight: bold;">用户信息</p>
									</div>
									<!-- List group -->
									<ul class="list-group">
										<li class="list-group-item"><label class="h5" for="username">帐号：<c:out
													value="${myUser.username }"></c:out></label></li>
										<li class="list-group-item"><div class="form-inline">
												<label class="h5">昵称：</label><input type="text" name="nickname"
													id="nickname" value="${myUser.nickname}"
													class="form-control input-sm">
											</div></li>
										<li class="list-group-item"><div class=" form-inline">
												<label class="h5">邮箱：</label><input type="text" name="email"
													id="email" value="${myUser.email}" class="form-control input-sm">
											</div></li>
									</ul>
									<div class="panel-footer" style="text-align: right;">
										<button id="updateInfoBtn" class="btn btn-default btn-xs">修改</button>
									</div>
								</div>
					
					
								<!--<strong>基本资料</strong>
						     				<div class=" form-inline" style="margin-bottom:10px; margin-top:15px;">
						     					<label class="" for="username">帐　　号：</label>-->
								<%-- <input type="text" name="username" id="username" value="${myUser.username}" class="form-control">
						     					 <c:out value="${myUser.username }"></c:out> /<c:out value="${myUser.userId }"></c:out> <br/> --%>
								<!--</div>
						     				<div class=" form-inline" style="margin-bottom:10px;">
						     					<label>昵　　称：</label><input type="text" name="nickname" id="nickname" value="${myUser.nickname}" class="form-control input-sm"><br/>
						     				</div>
						     				<div class=" form-inline" style="margin-bottom:10px;">
						     					<label>电子邮箱：</label><input type="text" name="email" id="email" value="${myUser.email}" class="form-control input-sm"><br/>
						     				</div>
						     				<button id="updateInfoBtn" class="btn btn-default">修改</button>-->
								<%-- <label>昵称： </label><c:out value="${myUser.nickname }"></c:out> --%>
								<!-- <br/>
						     				<label>密码： </label><button id="pwdChg4OpenDialog" class="btn btn-default">修改密码</button> -->
					
								<%-- <c:if test="${sessionScope.userType eq 'MEDIA'}"> --%>
								<div class="panel panel-success">
									<!-- Default panel contents -->
									<div class="panel-heading">
										<p class="h5" style="font-weight: bold;">微信公众号信息</p>
									</div>
									<div class="panel-body">
										<p class="h5">
											<c:if test="${not empty wxAuthorizerInfoPOJO}">
							     							已经授权给微信开发第三方【得味驿站】，您还可以 <a class="h5" target="_blank"
													href="${wxComLoginUrl}">重新授权</a>
											</c:if>
											<c:if test="${empty wxAuthorizerInfoPOJO}">
							     							您没有授权对应的公众号给微信开发第三方【得味驿站】, 需要<a class="h5" target="_blank"
													href="${wxComLoginUrl}">点击去授权</a>
											</c:if>
										</p>
									</div>
					
									<!-- List group -->
									<c:if test="${not empty wxAuthorizerInfoPOJO}">
										<ul class="list-group">
											<li class="list-group-item"><p class="h5">
													1.公众号名称:
													<c:out value="${wxAuthorizerInfoPOJO.nickName}"></c:out>
												</p></li>
											<li class="list-group-item"><p class="h5">
													2.微信号:
													<c:out value="${wxAuthorizerInfoPOJO.alias}"></c:out>
												</p></li>
											<li class="list-group-item"><p class="h5">
													3.公众号类型:
													<c:out value="${wxAuthorizerInfoPOJO.serviceTypeInfo}"></c:out>
												</p></li>
											<li class="list-group-item"><p class="h5">
													4.认证情况:
													<c:out value="${wxAuthorizerInfoPOJO.verifyTypeInfo}"></c:out>
												</p></li>
											<li class="list-group-item"><p class="h5">
													5.原始ID:
													<c:out value="${wxAuthorizerInfoPOJO.userName}"></c:out>
												</p></li>
											<li class="list-group-item"><p class="h5">
													6.appid:</label>
													<c:out value="${wxAuthorizerInfoPOJO.authorizerAppId}"></c:out>
												</p></li>
											<li class="list-group-item"><p class="h5">
													7.公众号头像: <img alt="公众号头像" src="${wxAuthorizerInfoPOJO.headImg}"
														height="40" width="40">
												</p></li>
											<li class="list-group-item"><p class="h5">
													8.二维码图片:
													<c:out value="${wxAuthorizerInfoPOJO.qrcodeFilePath}"></c:out>
													<img alt="二维码图片"
														src='<cmn:base/>${wxAuthorizerInfoPOJO.qrcodeFilePath}'
														height="40" width="40">
												</p></li>
										</ul>
									</c:if>
								</div>
								<%-- </c:if> --%>
							</div>
		<!--<label>微信公众号信息：
		     						<h5><c:if test="${not empty wxAuthorizerInfoPOJO}">
		     							已经授权给微信开发第三方【得味驿站】，您还可以 <a class="h5" target="_blank" href="${wxComLoginUrl}">重新授权</a>
		     						</c:if>
		     						<c:if test="${empty wxAuthorizerInfoPOJO}">
		     							您没有授权对应的公众号给微信开发第三方【得味驿站】, 需要<a class="h5" target="_blank" href="${wxComLoginUrl}">点击去授权</a>
		     						</c:if></h5></label>
		     					
		     					<c:if test="${not empty wxAuthorizerInfoPOJO}">
	     							<div class="col-md-12">
	     								<p class="h5">1.公众号名称:<c:out value="${wxAuthorizerInfoPOJO.nickName}"></c:out></p>
	     								<p class="h5">2.微信号:<c:out value="${wxAuthorizerInfoPOJO.alias}"></c:out></p>
	     								<p class="h5">3.公众号类型:<c:out value="${wxAuthorizerInfoPOJO.serviceTypeInfo}"></c:out></p>
	     								<p class="h5">4.认证情况:<c:out value="${wxAuthorizerInfoPOJO.verifyTypeInfo}"></c:out></p>
	     								<p class="h5">5.原始ID:<c:out value="${wxAuthorizerInfoPOJO.userName}"></c:out></p>
	     								<p class="h5">6.appid:</label><c:out value="${wxAuthorizerInfoPOJO.authorizerAppId}"></c:out></p>                                        
	     								<p class="h5">7.公众号头像:
	     								<img alt="公众号头像" src="${wxAuthorizerInfoPOJO.headImg}" height="40" width="40"></p>                                        
	     								<p class="h5">8.二维码图片:<c:out value="${wxAuthorizerInfoPOJO.qrcodeFilePath}"></c:out>
	     								<img alt="二维码图片" src='<cmn:base/>${wxAuthorizerInfoPOJO.qrcodeFilePath}' height="40" width="40"></p>
	     								<p class="h5" style="display:none;">公众号授权给开发者的权限集列表:</label><c:out value="${wxAuthorizerInfoPOJO.funcInfo}"></c:out></p>
	     								<p class="h5" style="display:none;">创建时间:</label><c:out value="${wxAuthorizerInfoPOJO.createDateTime}"></c:out></p>
	     							</div>
		     					</c:if>-->

					</div>
				</div>
				<!-- profile end -->
				<!-- content end -->
	     		</div>
	     	</div>
  		</div>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  	<%@include file="../../bottom.jsp"%>	
	</div>
  </body>
</html>
