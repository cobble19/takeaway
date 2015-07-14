<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="page/common/head.jsp" %>
  	<%-- <jsp:include page="page/common/head_index.jsp"></jsp:include> --%>
  	<!-- only for index page -->
    <!--<script src="<cmn:base/>/js/jquery-1.2.4b.js" type="text/javascript"></script>-->
  	<script src="<cmn:base/>/js/ui.core.js" type="text/javascript"></script>
	<script src="<cmn:base/>/js/ui.tabs.js" type="text/javascript"></script>
	
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/activity_top.js"></script>
	<link href="<cmn:base/>/css/activity_top.css" rel="stylesheet">

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

<div class="container">
<%@include file="reg_login.jsp" %>

	<%-- <div class="row">
        <div class="col-md-1 col-xs-6 logo"></div>
        <div class="col-md-3 col-xs-6 col-md-offset-8">
		<c:if test="${(empty username) or (not empty username and username eq 'anonymousUser')}">
	        <a href='<cmn:base/>/page/person/register.jsp' class="btn btn-success">个人注册</a>
	        <a href='<cmn:base/>/page/enterprise/register.jsp' class="btn btn-success">企业注册</a>
	        <button style="float:right;" type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">注册</button>
	        <button style="float:right; margin-right:10px;" type="button" class="btn btn-info" data-toggle="modal" data-target="#loginModal">登陆</button>
        	<a href="<cmn:base/>/login.jsp" class="btn btn-info">登录</a>
        	<a href="<cmn:base/>/login.jsp" class="sy-dl-wz">登录</a>
        </c:if> 
        <c:if test="${not empty username and (username ne 'anonymousUser')}">
        	<c:choose>
        		<c:when test="${sessionScope.userType eq 'PERSON'}">
        			<a class="sy-dl-wz" href='<cmn:base/>/j_spring_security_logout'>退出</a>
                    <a class="sy-dl-wz" href="<cmn:base/>/web/person/usercenter">个人中心</a>	
        		</c:when>
        		<c:when test="${sessionScope.userType eq 'ENTERPRISE'}">
        			<a class="sy-dl-wz" href="<cmn:base/>/j_spring_security_logout">退出</a>
                    <a class="sy-dl-wz" href="<cmn:base/>/web/enterprise/usercenter">个人中心</a>
        		</c:when>
        		<c:otherwise>
        			用户类型: <c:out value="${sessionScope.userType}"></c:out>
        		</c:otherwise>
        	</c:choose>
            <div class="sy-dl-wz">欢迎:<c:out value="${username}"></c:out></div>
        </c:if>
	</div>
</div> --%>
		
<div class="row" style="padding-left:50px;">


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
		
		// $('#regForm').validate();
    	/* $('#regForm').submit(function(e) {
    		console.log('adb');
			if ($('#regForm').valid()) {
				
			}
            $('#regForm').validate();
    		return false;
    	}); */
		      
       });
       
       
    </script> 
		<div id="rotate" class="col-md-10 col-md-offset-1">    
            <div id="0" class="row">               
               <ul class="row">
                    <div class="row">
                        <div class="qima col-md-2 hidden-xs"  style="margin-left:60px;"></div>
                        <div class="sywz1 col-md-4 hidden-xs"></div>
                        <div class="sywz2 col-md-4 hidden-xs"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-4" style="padding:0px; margin:0px;">
                            <div class="jrtj"></div>
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
                        </div>
                        <div class="col-md-4" style="padding:0px; margin:0px;">
                            <div class="jrtg"></div>
                            <div class="cont-2">
                                <li><img src="images/jrtg.png" width="250" height="295"></li>
                            </div>
                        </div>
                        <div class="col-md-4" style="margin-left:-15px;">
                            <div class="jrhd"></div>
                            <div class="cont-3"></div>
                        </div>
                     </div>           
			   </ul>
			   </div>


            <div id="1">               
               <ul class="sy-jrk">
                   
			       <li>
					   敬请期待
				   </li>
			   </ul>
			   </div>
            <ul class="row sy-lbk">	
               <div class="sy-lb col-md-10 col-md-offset-1 col-xs-12">
				<li><a href="#0"><span>今日头条</span></a></li>
				<li><a href="#1"><span>美食外卖</span></a></li>     
               </div>
            </ul>               		   
		</div>
    <!-- <div class="sy-dl">
        <div class="sy-dl-img"></div>
        
        
    </div> -->
    <!-- 注册 -->
    <!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
    						<li role="presentation">
    							<a href="#enterprise" aria-controls="enterprise" page="page/enterprise/register.jsp" role="tab" data-toggle="tab">企业</a>
    						</li>
    					</ul>
    					
    					<div class="tab-content">
    						<div role="tabpanel" class="tab-pane active" id="person">person</div>
    						<div role="tabpanel" class="tab-pane" id="enterprise">enterprise</div>
    					</div>
    				</div>
    			</div>modal-body
    			<div class="modal-footer">
    				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    				<button type="button" class="btn btn-primary">创建</button>
    			</div>modal-footer
    		</div>
    	</div>
    </div> -->
    
    
    <!-- 登陆 -->
    <!-- <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
    					</ul>
    					
    					<div class="tab-content">
    						<div role="tabpanel" class="tab-pane active" id="loginPerson">person</div>
    						<div role="tabpanel" class="tab-pane" id="loginEnterprise">enterprise</div>
    					</div>
    				</div>
    			</div>modal-body
    			<div class="modal-footer">
    				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    			</div>modal-footer
    		</div>
    	</div>
    </div> -->



	</div>
<div style="display:none;"><%@include file="bottom.jsp" %></div>	
</div>
</body>
</html>
