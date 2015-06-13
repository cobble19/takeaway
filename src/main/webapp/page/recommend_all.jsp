<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="common/head.jsp" %>
  </head>
  <body>
  	<div class="main">
        <div class="wm-head"></div>
  		<div class="jrtj-zt">
	  		<c:forEach items="${recommendPOJOList}" var="recommendPOJO"  varStatus="st">
              <%-- <c:if test="${st.last}"> --%>
	  			<div class="jrtj-zt-t">
	  				<h2>${recommendPOJO.title}</h2>
                </div>
                <div class="jrtj-zt-c">
	  				<p>${recommendPOJO.content}</p>
	  			</div>
              <%-- </c:if> --%>
			</c:forEach>
  		</div>
        <div class="bottom"></div>
	</div>
  </body>
</html>