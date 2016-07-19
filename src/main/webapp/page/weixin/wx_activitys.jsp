<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
  
    <%@include file="../common/wx_head.jsp" %>
    
    <%-- <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/wx_person_user_center.js"></script> --%>
    
    <style type="text/css">
		.head-img {
			/* width: 20px;
			height: 20px; */
			/* margin-right: 5px; */
			display: block;
			-moz-border-radius: 30px; /* Gecko browsers */
			-webkit-border-radius: 30px; /* Webkit browsers */
			border-radius: 30px; /* W3C syntax */
		}
	</style>
    
  </head>
  <body>
  
	<div class="weui_tab">
	  <div class="weui_navbar">
	    <a href="#tab1" class="weui_navbar_item weui_bar_item_on">
	      已参加活动
	    </a>
	    <a href="#tab2" class="weui_navbar_item">
	      所有活动
	    </a>
	  </div>
	  <div class="weui_tab_bd">
	    <div id="tab1" class="weui_tab_bd_item weui_tab_bd_item_active">
	      <h1 class="doc-head">已参加活动</h1>
	      
		  	<div class="weui_cells weui_cells_access">
		  		<c:forEach items="${activityPOJOs4WxPerson}" var="activityPOJO" varStatus="st">
				  <a class="weui_cell" href="/page/enterprise/activity_detail.jsp?activityId=${activityPOJO.activityId}">
				    <div class="weui_cell_bd weui_cell_primary">
				      <p style="font-size:12px;"><c:out value="${activityPOJO.title}"></c:out></p>
				    </div>
			        <div class="weui_cell_ft"></div>
				  </a>
		  		</c:forEach>
			</div>
	    </div>
	    <div id="tab2" class="weui_tab_bd_item">
	      <h1 class="doc-head">所有活动</h1>
	      
		  	<div class="weui_cells weui_cells_access">
		  		<c:forEach items="${activityPOJOs}" var="activityPOJO" varStatus="st">
				  <a class="weui_cell" href="/page/enterprise/activity_detail.jsp?activityId=${activityPOJO.activityId}">
				    <div class="weui_cell_bd weui_cell_primary">
				      <p style="font-size:12px;"><c:out value="${activityPOJO.title}"></c:out></p>
				    </div>
			        <div class="weui_cell_ft"></div>
				  </a>
		  		</c:forEach>
			</div>
	    </div>
	  </div>
	</div>
	
  </body>
</html>

