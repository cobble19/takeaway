<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
  </head>
  <body>
  
	<div class="container">
		<%-- <%@include file="../../../reg_login_full.jsp" %> --%>
		<c:forEach items="${activityPOJOs}" var="activityPOJO" varStatus="st">
			<div class="media">
			  <div class="media-left media-middle">
			    <a href='<cmn:base/>/page/enterprise/activity_detail.jsp?activityId=${activityPOJO.activityId}'>
			      <img class="media-object" src="<cmn:base/>/${activityPOJO.logoImg}" alt="${activityPOJO.title}">
			    </a>
			  </div>
			  <div class="media-body">
			    <h4 class="media-heading">${activityPOJO.title}</h4>
			    ${activityPOJO.content}
			  </div>
			</div>
		</c:forEach>
		
        <div class="row" style="background-color:#f1f1f1;">
            <p style="text-align:center; margin-bottom:10px; margin-top:20px; font-size:12px; line-height:12px;">2016 合肥交通广播</p>
        </div>
        <div class="row" style="background-color:#333;">
            <p style="text-align:center; margin-bottom:5px; margin-top:5px; font-size:12px; line-height:12px; color:#ffffff;">技术支持:得味驿站</p>
        </div>
	  		
	</div> <!-- container -->
  </body>
</html>

