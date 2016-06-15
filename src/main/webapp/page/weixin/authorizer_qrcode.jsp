<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript">
    $(document).ready(function() {
    	$('#qrcodeDiv').load('http://mmbiz.qpic.cn/mmbiz/6A93MryJWuLwVfT3lcQCICJbZvu6d5MdmmuF1C5Sk3kibYyQLZOiaHZ45sjVic7oXwEl4H2MfnVoibKjTrBV33cFVA/0');
    	
    		window.open('http://mmbiz.qpic.cn/mmbiz/6A93MryJWuLwVfT3lcQCICJbZvu6d5MdmmuF1C5Sk3kibYyQLZOiaHZ45sjVic7oXwEl4H2MfnVoibKjTrBV33cFVA/0');
    	
    })
    </script>
    
  </head>
  <body>
  	<div class="container-fluid">
  		<div class="row">
  			<h2 class="col-sm-offset-3 col-md-offset-2">请关注公众号 1111111111111111111111111<c:out value="${wxAuthorizerInfoPOJO.nickName}"></c:out></h2>
  			<p>
  				<img alt="" src="http://deweiyizhan.com/page/0">
  				<iframe src="http://mmbiz.qpic.cn/mmbiz/6A93MryJWuLwVfT3lcQCICJbZvu6d5MdmmuF1C5Sk3kibYyQLZOiaHZ45sjVic7oXwEl4H2MfnVoibKjTrBV33cFVA/0"></iframe>
  				<div id="qrcodeDiv">dvivdivdi</div>
  				<a href="http://mmbiz.qpic.cn/mmbiz/6A93MryJWuLwVfT3lcQCICJbZvu6d5MdmmuF1C5Sk3kibYyQLZOiaHZ45sjVic7oXwEl4H2MfnVoibKjTrBV33cFVA/0" target="_blank">Rffffff</a>
  				<%-- <%
  					response.sendRedirect("http://mmbiz.qpic.cn/mmbiz/6A93MryJWuLwVfT3lcQCICJbZvu6d5MdmmuF1C5Sk3kibYyQLZOiaHZ45sjVic7oXwEl4H2MfnVoibKjTrBV33cFVA/0");
  				%> --%>
  			</p>
  		</div>
  		
		<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
  </body>
</html>