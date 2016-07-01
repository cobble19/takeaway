<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
  
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/wx_link_user_center.js"></script>
    
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
    
    <style type="text/css">
		.head-img {
			width: 20px;
			height: 20px;
			margin-right: 5px;
			display: block;
			-moz-border-radius: 10px; /* Gecko browsers */
			-webkit-border-radius: 10px; /* Webkit browsers */
			border-radius: 10px; /* W3C syntax */
		}
	</style>
    
  </head>
  <body>
  	<div class="weui_cells">
	  <div class="weui_cell" >
	    <div class="weui_cell_hd">
	      <img src="${wxUserInfoApiPOJO.headImgUrl}" alt="icon" title="头像" class="head-img" style="">
	    </div>
	    <div class="weui_cell_bd weui_cell_primary">
	      <p><c:out value="${wxUserInfoApiPOJO.nickname}"></c:out></p>
	    </div>
	    <div class="weui_cell_ft">
	      
	    </div>
	  </div>
	</div>

  	<div class="weui_cells weui_cells_access">
	  <a class="weui_cell" href="javascript:;">
	    <div class="weui_cell_bd weui_cell_primary">
	      <p>活动</p>
	    </div>
	    <div class="weui_cell_ft">参加活动</div>
	  </a>
	  <a class="weui_cell" href="javascript:;">
	    <div class="weui_cell_bd weui_cell_primary">
	      <p>积分</p>
	    </div>
	    <div class="weui_cell_ft">查看积分</div>
	  </a>
	  <a class="weui_cell" href="javascript:;">
	    <div class="weui_cell_bd weui_cell_primary">
	      <p>优惠券</p>
	    </div>
	    <div class="weui_cell_ft">查看优惠券</div>
	  </a>
	</div>
	
  	<div class="weui_cells weui_cells_access">
	  <a class="weui_cell" href="javascript:;">
	    <div class="weui_cell_bd weui_cell_primary">
	      <p>得味中心</p>
	    </div>
	    <div class="weui_cell_ft">进入得味</div>
	  </a>
	</div>
	
  </body>
</html>

