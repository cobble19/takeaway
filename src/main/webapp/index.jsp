<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cmn" uri="/WEB-INF/tlds/common.tld" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>得味驿站</title>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<cmn:base/>/jquery/jquery-1.11.1.min.js"></script>
    <script src="<cmn:base/>/jquery/jquery-migrate-1.2.1.min.js"></script>
    <!-- Bootstrap -->
    <link href="<cmn:base/>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<cmn:base/>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
	<link href="<cmn:base/>/css/dwsy.css" rel="stylesheet" media="print, projection, screen">
    <link href="css/dwsy.css" rel="stylesheet" media="print, projection, screen">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="<cmn:base/>/js/jquery-1.2.4b.js" type="text/javascript"></script>
<script src="<cmn:base/>/js/ui.core.js" type="text/javascript"></script>
<script src="<cmn:base/>/js/ui.tabs.js" type="text/javascript"></script>
<script type="text/javascript" src="http://api.go2map.com/maps/js/api_v2.5.1.js"></script>
<script src="js/jquery-1.2.4b.js" type="text/javascript"></script>
<script src="js/ui.core.js" type="text/javascript"></script>
<script src="js/ui.tabs.js" type="text/javascript"></script>
<script type="text/javascript">
 /** 
  * 加载指定城市区域的地图 
  */
  function initialize() { 
    var myOptions = { 
      zoom: 12, 
      center: new sogou.maps.Point(13055906,3722562) 
    } 
    var map = new sogou.maps.Map(document.getElementById("map_canvas"), 
                                  myOptions); 
  } 
</script>
</head>



<body onLoad="initialize()">
	<security:authentication property="principal.username" var="username"/>
		
<div class="main">
<div class="logo"></div>

  <script type="text/javascript">
       $(function() {
           $('#rotate > ul').tabs({ fx: { opacity: 'toggle' } }).tabs('rotate', 0);
    	   /* $('#rotate1 > ul').tabs(); */
       });
    </script> 

<div class="sy-dw-t">	
		
		<div id="rotate">
		  
            <ul class="sy-lbk">	
               <div class="sy-lb">
				<li><a href="#0"><span>今日头条</span></a></li>
				<li><a href="#1"><span>精品外卖</span></a></li>     
               </div>
            </ul>
	      
            <div id="0">               
               <ul class="sy-jrk">
                        <div class="qima"></div>
                        <div class="sywz"></div>
                        <div class="jrtj"></div>
                        <div class="jrtg"></div>
                        <div class="jrhd"></div>

                        <div class="cont-1">
                            <div class="cont-4">
                                <li><img src="images/5.png" width="252" height="105"></li>
                            </div>
                            <div class="jryh"></div>
                            <div class="cont-5">
                                <!--今日优惠四个图片开始-->
                                <li><img src="images/1.png" width="124" height="74"></li>
                                <li><img src="images/2.png" width="124" height="74"></li>
                                <li><img src="images/3.png" width="124" height="74"></li>
                                <li><img src="images/4.png" width="124" height="74"></li>
                                <!--今日优惠四个图片结束-->
                            </div>
                        </div>
                        <div class="cont-2"></div>
                        <div class="cont-3"></div>
			   </ul>
			   </div>

            <div id="1">               
               <ul class="sy-jrk">
                   
			       <li>
					   <a href='<cmn:base/>/web/foodSellers?businessId=${locationBusinessPOJO.locationBusinessId}'>${locationBusinessPOJO.name}</a>(${locationBusinessPOJO.countFoodSeller})
				   </li>
			   </ul>
			   </div>
               		   
		</div>
	</div>
    <div class="sy-dl">
        <div class="sy-dl-img"></div>
        <c:if test="${(empty username) or (not empty username and username eq 'anonymousUser')}">
        	<a href="<cmn:base/>/login.jsp" class="sy-dl-wz">登录</a>
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
</body>

</html>

