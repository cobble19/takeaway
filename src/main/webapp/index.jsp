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

    <link href="<cmn:base/>/css/common.css" rel="stylesheet">
    
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
    	   
		$("#myTab a, #loginTab a").each(function(i, ele) {
			if ($(this).parent().hasClass('active')) {
			   var href = $(this).attr('href');
			   var page = $(this).attr('page');
			   $(href).load(page);
			}
		});	
		$("#myTab a, #loginTab a").click(function(e) {
		 var href = $(this).attr('href');
		 var page = $(this).attr('page');
		 $(href).load(page);
		})
		      
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
    
    




</div>
</body>

</html>

