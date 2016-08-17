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
.weui_panel:before{border-top:none;}
.weui_media_box:before{border-top:none;}
</style>
</head>
<body>
<div class="weui_panel weui_panel_access">
  <div class="weui_panel_hd">
      <h1 class="weui_media_title" style="text-align:center; color:#000;">
        <c:out value="${votePOJO.title}"></c:out>
      </h1>
      <p class="weui_media_desc">
        <c:out value="${votePOJO.content}"></c:out>
      </p>
  </div>
  <div class="weui_panel_bd">
    <c:forEach items="${votePOJO.voteItemPOJOs}" var="voteItemPOJO" varStatus="st">
      <div class="weui_media_box weui_media_text" style="border-bottom:1px solid #E5E5E5;">
        <h4 class="weui_media_title">&lt;<c:out value="${st.index+1}"></c:out>&gt;&nbsp;<c:out value="${voteItemPOJO.title}"></c:out>
          <input type="checkbox" name="chkBox" id="chkBox_${voteItemPOJO.voteItemId}" value="${voteItemPOJO.voteItemId}">
        </h4>
        <div class="weui_media_box weui_media_appmsg">
	        <div class="swiper-wrapper"> 
	        	<img class="weui_media_appmsg_thumb" src="<cmn:base/>/${voteItemPOJO.imgUrl}" alt="${voteItemPOJO.title}"> 
	        </div>
        </div>
        <ul class="weui_media_info">
          <li class="weui_media_info_meta" style="color:#000">票数：
            <c:out value="${voteItemPOJO.totalNum}"></c:out>
          </li>
          <li class="weui_media_info_meta"><a href="javascript:;" class="open-popup" data-target="#voteItem_description" style="color:#000">详细信息...</a></li>
        </ul>
      </div>
      <div id="voteItem_description" class="weui-popup-container">
        <div class="weui-popup-overlay"></div>
        <div class="weui-popup-modal">
          <c:out value="${voteItemPOJO.description}"></c:out>
          <a href="javascript:;" class="weui_btn weui_btn_plain_primary close-popup">关闭</a>
        </div>
      </div>
    </c:forEach>
  </div>
  <div class="weui_panel_bd">
    <div class="weui_media_box weui_media_text">
    <input type="hidden" id="voteId" value="${votePOJO.voteId}">
    <input type="button" id="addVoteItemBtn" class="weui_btn weui_btn_mini weui_btn_primary" value="投票">
    </div>
  </div>
</div>
<!--	<div class="weui_grids">
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
    <div>
  		<input type="hidden" id="voteId" value="${votePOJO.voteId}">
  		<h3><c:out value="${votePOJO.title}"></c:out></h3>
  		<span><c:out value="${votePOJO.content}"></c:out></span>
  		<input type="button" id="addVoteItemBtn" class="btn btn-default" value="投票">
  	</div>-->
</body>
</html>
