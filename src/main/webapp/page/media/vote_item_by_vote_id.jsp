<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
  
    <%@include file="../common/wx_head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/vote_item_by_vote_id.js"></script>
    
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
  	<div>
  		<input type="hidden" id="voteId" value="${votePOJO.voteId}">
  		<h3><c:out value="${votePOJO.title}"></c:out></h3>
  		<span><c:out value="${votePOJO.content}"></c:out></span>
  		<input type="button" id="addVoteItemBtn" class="btn btn-default" value="投票">
  	</div>
	<div class="weui_grids">
		<c:forEach items="${votePOJO.voteItemPOJOs}" var="voteItemPOJO" varStatus="st">
		  <a href="javascript:;" class="weui_grid js_grid" data-id="button">
		    <div class="weui_grid_icon">
		      <img src="<cmn:base/>/${voteItemPOJO.imgUrl}" alt="${voteItemPOJO.title}">
		    </div>
		    <p class="weui_grid_label">
		    <c:out value="${voteItemPOJO.description}"></c:out>
		      票数：<c:out value="${voteItemPOJO.totalNum}"></c:out>
		      <input type="checkbox" name="chkBox" id="chkBox_${voteItemPOJO.voteItemId}" value="${voteItemPOJO.voteItemId}">
		    </p>
		  </a>
		</c:forEach>
	</div>
  </body>
</html>

