<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="common/head.jsp" %>
  </head>
  <body>
	<div id="firstpane" class="menu_list">
		<!-- <div class="list-group-item">
			<h4 class="list-group-item-heading">庐阳区 (80)</h4>
			<p class="list-group-item-text">
				逍遥津 (15)  步行街 (18)<br>
				三孝口 (20)  步行街 (18)<br/>
				步行街 (18)  长江西路 (30)<br/>
			</p>
		</div> -->
		<c:forEach items="${locationAreaPOJOList}" var="locationAreaPOJO">
		
				<p class="menu_head current"><a href='<cmn:base/>/web/foodSellers?areaId=${locationAreaPOJO.locationAreaId}'>${locationAreaPOJO.name}</a> (${locationAreaPOJO.countFoodSeller})</p>
				<div style="display:none" class="menu_body">
					<c:forEach items="${locationAreaPOJO.locationBusinessPOJOs}" var="locationBusinessPOJO"
					 varStatus="st">
					 	<a href='<cmn:base/>/web/foodSellers?businessId=${locationBusinessPOJO.locationBusinessId}'>${locationBusinessPOJO.name}</a>(${locationBusinessPOJO.countFoodSeller})  
						<c:if test="${st.count % 2 == 0}">
						<br/>
						</c:if>
					</c:forEach>
				</div>
			
		</c:forEach>
	</div>
<script type=text/javascript>
$(document).ready(function(){
	$("#firstpane .menu_body:eq(0)").show();
	$("#firstpane p.menu_head").click(function(){
		$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	$("#secondpane .menu_body:eq(0)").show();
	$("#secondpane p.menu_head").mouseover(function(){
		$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	
});
</script>
  </body>
</html>