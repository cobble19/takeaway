<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<security:authentication property="principal.username" var="username"/>
<script src="<cmn:base/>/js/reg_login.js"></script>
    <style type="text/css">
		.navbar {
			min-height:30px;
		}
	</style>

<script type="text/javascript">
	$(function() {
		var url = location.href;
		console.log('url: ' + url);
		// remove nav class=active
		$('ul.nav.nav-pills li').removeClass('active');
		$('ul.nav.nav-pills li a').each(function(index, elm) {
			var href = $(this).attr('href');
			if (url.indexOf(href) > -1) {
				$(this).parent('li').addClass('active');
			}
		});
		
	})
</script>

<%@include file="page/common/taglib.jsp"%>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid hidden-xs">
    <div class="row" style="height:100px;">
      <div class="col-md-1 logo" style="height:100px; margin-left:10px;"></div>
      
      <div class="col-md-8" style="height:100px;">
        <ul class="nav nav-pills nav-justified h4" style=" margin-left:50px; margin-top:25px; font-weight:bold; font-family:'新宋体';">
          <li role="presentation" class="active"><a href="<cmn:base/>/index">首页</a></li>
          <li role="presentation"><a href="<cmn:base/>/gonggao.jsp">公告</a></li>
          <li role="presentation"><a href="<cmn:base/>/functions.jsp">功能</a></li>
          <li role="presentation"><a href="<cmn:base/>/fee.jsp">资费</a></li>
          <li role="presentation"><a href="<cmn:base/>/help.jsp">帮助</a></li>
          <li role="presentation"><a href="<cmn:base/>/about.jsp">关于</a></li>
        </ul>
      </div>
      
      <div class="col-md-3" style="height:100px;">
        <c:if test="${(empty username) or (not empty username and username eq 'anonymousUser')}">
          <a href="<cmn:base/>/register.jsp" style="float:right; margin-top:30px;" class="btn btn-success active" role="button">&nbsp;注册&nbsp;</a>
          <a href="<cmn:base/>/login.jsp" style="float:right; margin-top:30px; margin-right:10px;" class="btn btn-warning active" role="button">&nbsp;登陆&nbsp;</a>
        </c:if>
        
        <c:if test="${not empty username and (username ne 'anonymousUser')}"> 
          <div class="sy-dl-wz col-md-12" style="margin-top:30px; text-align:right;">欢迎:
            <c:if test="${sessionScope.myUser.nickname != null}">
              <c:out value="${sessionScope.myUser.nickname}"></c:out>
            </c:if>
            <c:if test="${sessionScope.myUser.nickname == null or sessionScope.myUser.nickname eq ''}">
              <c:out value="${username}"></c:out>
            </c:if>
          </div>
          
          <div class="col-md-12" style=" text-align:right;">  
	          <a class="sy-dl-wz" style="float:right;" href='<cmn:base/>/j_spring_security_logout'>退出</a>
	          <c:choose>
	            <c:when test="${sessionScope.userType eq 'PERSON'}"> <a class="sy-dl-wz" href="<cmn:base/>/web/unified/profile">个人中心</a> </c:when>
	            <c:when test="${sessionScope.userType eq 'ENTERPRISE'}"> <a class="sy-dl-wz" href="<cmn:base/>/web/unified/profile">管理中心</a> </c:when>
	            <c:when test="${sessionScope.userType eq 'MEDIA'}"> <a class="sy-dl-wz" href="<cmn:base/>/web/unified/profile">媒体中心</a> </c:when>
	            <%-- <c:when test="${sessionScope.userType eq 'ENTERPRISE'}"> <a class="sy-dl-wz" href="<cmn:base/>/web/unified/usercenter">管理中心</a> </c:when>
	            <c:when test="${sessionScope.userType eq 'MEDIA'}"> <a class="sy-dl-wz" href="<cmn:base/>/web/unified/usercenter">媒体中心</a> </c:when> --%>
	            <c:otherwise>
	              <%-- 用户类型: <c:out value="${sessionScope.userType}"></c:out> --%>
	            </c:otherwise>
	          </c:choose>
          </div> 
        </c:if>
      </div>
    </div>
  </div>
</nav>
