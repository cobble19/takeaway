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
    <title>整个链</title>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<cmn:base/>/jquery/jquery-1.11.1.min.js"></script>
    <script src="<cmn:base/>/jquery/jquery-migrate-1.2.1.min.js"></script>
    <!-- Bootstrap -->
    <link href="<cmn:base/>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<cmn:base/>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
	<link href="<cmn:base/>/bootstrap/css/wm.css" rel="stylesheet">
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
	<div class="list-group">
		<!-- <div class="list-group-item">
			<h4 class="list-group-item-heading">庐阳区 (80)</h4>
			<p class="list-group-item-text">
				逍遥津 (15)  步行街 (18)<br>
				三孝口 (20)  步行街 (18)<br/>
				步行街 (18)  长江西路 (30)<br/>
			</p>
		</div> -->
		<c:forEach items="${locationAreaPOJOList}" var="locationAreaPOJO">
			<div class="list-group-item">
				<div class="list-group-item-title"><a href='<cmn:base/>/web/foodSellers?areaId=${locationAreaPOJO.locationAreaId}'>${locationAreaPOJO.name}</a> (${locationAreaPOJO.countFoodSeller})</div>
				<div class="list-group-item-text">
					<c:forEach items="${locationAreaPOJO.locationBusinessPOJOs}" var="locationBusinessPOJO"
					 varStatus="st">
					 	<a href='<cmn:base/>/web/foodSellers?businessId=${locationBusinessPOJO.locationBusinessId}'>${locationBusinessPOJO.name}</a>(${locationBusinessPOJO.countFoodSeller})  
						<c:if test="${st.count % 2 == 0}">
						<br/>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</c:forEach>
	</div>
  </body>
</html>