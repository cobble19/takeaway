<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cmn" uri="/WEB-INF/tlds/common.tld" %>

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
	<link href="<cmn:base/>/bootstrap/css/wm.css" rel="stylesheet" media="print, projection, screen">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="<cmn:base/>/bootstrap/js/jquery-1.2.4b.js" type="text/javascript"></script>
<script src="<cmn:base/>/bootstrap/js/ui.core.js" type="text/javascript"></script>
<script src="<cmn:base/>/bootstrap/js/ui.tabs.js" type="text/javascript"></script>
<script type="text/javascript" src="http://api.go2map.com/maps/js/api_v2.5.1.js"></script>
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
<div class="main">
    <div class="sy-top"></div>
	
    <div class="sy-tj">
        <div class="jrtj">
		    <div class="jrtj-t"></div>
		    <div class="jrtj-i"><img src="<cmn:base/>/bootstrap/images/wm_05.gif"></div>
	        <a class="djjr" href="http://www.baidu.com"></a>		
		</div>
		<div class="jryh">
		    <div class="jryh-t"></div>
			<div class="jryh-i"><img src="<cmn:base/>/bootstrap/images/wm_06.gif"></div>
			<a class="djjr" href="http://www.baidu.com"></a>		
		</div>
		<div class="jrtg">
		    <div class="jrtg-t"></div>
			<div class="jrtg-i"><img src="<cmn:base/>/bootstrap/images/wm_07.gif"></div>
			<a class="djjr" href="http://www.baidu.com"></a>		
		</div>
		<div class="jrsj">
		    <div class="jrsj-t"></div>
			<div class="jrsj-x"></div>
			<div class="jrsj-z"></div>
		</div>
    </div>
	<div class="sy-wm">
	    <div class="an-dq">按地区查询</div>
		<div class="an-dt"><a>按地图查询</a></div>
		<div class="sy-search">
		<form action='<cmn:base/>/web/foodSellers/search'>
								<input class="sy-search-k" id="keyword" name="keyword" type="text" placeholder="搜索" value="请输入你想搜索的餐厅">
							</form>
        </div>
	</div>
	
    <script type="text/javascript">
       $(function() {
           $('#rotate1 > ul').tabs({ fx: { opacity: 'toggle' } }).tabs('rotate', 0);
       });
    </script> 
	
    <div class="sy-wm-t">
	    <div class="sy-img1"></div>
		<div class="sy-img2"></div>
		
		
		
		
		<div id="rotate1">
		    <ul class="dq-lbk">
				<c:forEach items="${locationAreaPOJOList}" var="locationAreaPOJO">
				<li><a href='<cmn:base/>/web/foodSellers?areaId=${locationAreaPOJO.locationAreaId}'><span>${locationAreaPOJO.name}</span></a></li>
				</c:forEach>
            </ul>
			<c:forEach items="${locationAreaPOJOList}" var="locationAreaPOJO">
            <div id="${locationAreaPOJO.locationAreaId}">               
               <ul class="sy-dqk">
                   <c:forEach items="${locationAreaPOJO.locationBusinessPOJOs}" var="locationBusinessPOJO" varStatus="st">
			       <li>
					   <a href='<cmn:base/>/web/foodSellers?businessId=${locationBusinessPOJO.locationBusinessId}'>${locationBusinessPOJO.name}</a>(${locationBusinessPOJO.countFoodSeller})
				   </li>
				   </c:forEach>
			   </ul>
			   </div>
			   </c:forEach>
		    <!--<div class="dq-lbk"></div>
		    <div class="sy-dqk"></div>-->
		</div>
		
		
		

	</div>
	
	
	
	
	
	
	
	
	
	
	
	

</div>
</body>

</html>

