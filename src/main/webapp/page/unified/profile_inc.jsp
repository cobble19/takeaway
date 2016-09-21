<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>
        			
	     			<div id="profile">
	     				<strong>基本资料</strong>
	     				<div class=" form-inline" style="margin-bottom:10px; margin-top:15px;">
	     					<label class="" for="username">帐　　号：</label>
	     					<%-- <input type="text" name="username" id="username" value="${myUser.username}" class="form-control"> --%>
	     					 <c:out value="${myUser.username }"></c:out><%-- /<c:out value="${myUser.userId }"></c:out> --%><br/>
	     				</div>
	     				<div class=" form-inline" style="margin-bottom:10px;">
	     					<label>昵　　称：</label><input type="text" name="nickname" id="nickname" value="${myUser.nickname}" class="form-control input-sm"><br/>
	     				</div>
	     				<div class=" form-inline" style="margin-bottom:10px;">
	     					<label>电子邮箱：</label><input type="text" name="email" id="email" value="${myUser.email}" class="form-control input-sm"><br/>
	     				</div>
	     				<button id="updateInfoBtn" class="btn btn-default">修改</button>
	     				<%-- <label>昵称： </label><c:out value="${myUser.nickname }"></c:out> --%><br/>
	     				<!-- <label>密码： </label><button id="pwdChg4OpenDialog" class="btn btn-default">修改密码</button> -->
	     				
	     				<c:if test="${sessionScope.userType eq 'MEDIA'}">
		     				<hr/>
		     				<div class="row">
		     					<div class="col-md-12">
		     						<label>是否已经授权给第三方：</label>
		     						<c:if test="${not empty wxAuthorizerInfoPOJO}">
		     							已经授权给微信开发第三方【得味驿站】, <a href="${wxComLoginUrl}">重新授权</a>
		     						</c:if>
		     						<c:if test="${empty wxAuthorizerInfoPOJO}">
		     							您没有授权对应的公众号给微信开发第三方【得味驿站】, 需要<a href="${wxComLoginUrl}">点击去授权</a>
		     						</c:if>
		     					</div>
		     					<c:if test="${not empty wxAuthorizerInfoPOJO}">
	     							<div class="col-md-12">
	     								<label>授权方昵称:</label><c:out value="${wxAuthorizerInfoPOJO.nickName}"></c:out>
	     								<label>授权方头像:</label><c:out value="${wxAuthorizerInfoPOJO.headImg}"></c:out>
	     								<label>授权方公众号类型:</label><c:out value="${wxAuthorizerInfoPOJO.serviceTypeInfo}"></c:out>
	     								<label>授权方认证类型:</label><c:out value="${wxAuthorizerInfoPOJO.verifyTypeInfo}"></c:out>
	     								<label>授权方公众号的原始ID:</label><c:out value="${wxAuthorizerInfoPOJO.userName}"></c:out>
	     								<label>授权方公众号所设置的微信号:</label><c:out value="${wxAuthorizerInfoPOJO.alias}"></c:out>
	     								<label>二维码图片:</label><c:out value="${wxAuthorizerInfoPOJO.qrcodeFilePath}"></c:out>
	     								<label>授权方appid:</label><c:out value="${wxAuthorizerInfoPOJO.authorizerAppId}"></c:out>
	     								<label>公众号授权给开发者的权限集列表:</label><c:out value="${wxAuthorizerInfoPOJO.funcInfo}"></c:out>
	     								<label>创建时间:</label><c:out value="${wxAuthorizerInfoPOJO.createDateTime}"></c:out>
	     							</div>
		     					</c:if>
		     					
		     				</div>
	     				</c:if>
	     				
	     			</div>	<!-- profile end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/profile_inc.js"></script>
