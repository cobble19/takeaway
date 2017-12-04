<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<%@include file="common/wx_head.jsp"%>
    
	<%-- <input id="basePath" type="hidden" value=''>
	<input id="userId" type="hidden" value='${sessionScope.myUser.userId}'>
	<input id="username" type="hidden" value='${sessionScope.myUser.username}'> --%>
  </head>
  <body>
    <div class="weui_msg">
    <security:authentication property="principal.username" var="username"/>
  <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
  <div class="weui_text_area">
    <h2 class="weui_msg_title">操作成功</h2>
    <p class="weui_msg_desc">
        <c:if test="${success == true}">
  			恭喜您:<c:out value="${username}"></c:out>,操作成功！
  		</c:if>
  		<c:if test="${success == false}">
  			错误信息:<c:out value="${msg}"></c:out>
  		</c:if>
  		
  		<c:if test="${addVip eq 'ADD_VIP'}">
  			恭喜您:<c:out value="${msg}"></c:out>
  		</c:if>
    </p>
  </div>
  <%-- <div class="weui_opr_area">
    <p class="weui_btn_area">
        <c:if test="${success == true}">
  			<a href="<cmn:base/>/web/unified/profile" class="weui_btn weui_btn_primary">确定</a>
  		</c:if>
  		<c:if test="${success == false}">
  			<a href="<cmn:base/>/web/unified/profile" class="weui_btn weui_btn_primary">确定</a>
  		</c:if>
  		
  		<c:if test="${addVip eq 'ADD_VIP'}">
  			<a href="#" onClick="WeixinJSBridge.call('closeWindow');" class="weui_btn weui_btn_primary">确定</a>
  		</c:if>
      <!--<a href="javascript:;" class="weui_btn weui_btn_default">取消</a>-->
    </p>
  </div> --%>
  <!--<div class="weui_extra_area">
    <a href="">查看详情</a>
  </div>-->
</div>
    
    
    
    
    
  	<!--<div>
  		<security:authentication property="principal.username" var="username"/>
  		<c:if test="${success == true}">
  			<h1>恭喜您:<c:out value="${username}"></c:out>,授权成功 !</h1>
  		</c:if>
  		<c:if test="${success == false}">
  			<h1>错误信息:<c:out value="${msg}"></c:out></h1>
  		</c:if>
  		
  		<c:if test="${addVip eq 'ADD_VIP'}">
  			<h1>信息:<c:out value="${msg}"></c:out></h1>
  		</c:if>
  		
  		<br/>
  		<a id="toIndex" class="btn btn-link" href="<cmn:base/>/web/unified/usercenter">如果5秒不自动跳转，请点击链接，手动跳转到管理中心</a>
		
		<p>Message: <c:out value="${msg}"></c:out></p> 
		<p><a href="${globalLogoutUrl}">Sign Out</a></p>
	</div>-->
	
	<script type="text/javascript">
		$(function() {
			window.setTimeout(function() {
				window.location.href = $('#basePath').val() + "/web/wx/usercenter/"  + $('#indexCode').val() + "/person";
			}, 1);
		});
	</script>
	
  </body>
</html>