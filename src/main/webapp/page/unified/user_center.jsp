<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cobble.takeaway.util.CommonConstant" %>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
  
	<link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
    <%@include file="../../page/common/head.jsp" %>
    
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/user_center.js"></script>
	<link href="<cmn:base/>/css/unified/activity_list.css" rel="stylesheet">
	
    
    <script>
    	$(document).ready(function() {
    		
	    	$('#sidebar a').click(function() {
				 $('#sidebar a').each(function() {
					$(this).parent('li').removeClass('active');
				}) 
				$this = $(this);
				$this.parent('li').addClass('active'); 
				var contentId = $this.attr('href').substring(1);
				console.log('contentId: ' + contentId);
				$('#uc_content > div').each(function(i, e) {
					$(this).hide();
				})
				$('#' + contentId).show();
			});
			
	
			var hash; 
			hash=(!window.location.hash)?"#profile":window.location.hash; 
			window.location.hash=hash; 
			console.log('hash: ' + hash);
			$('#sidebar a[href=' + hash + ']').trigger('click');
    		
    	})	// end ready
    </script>

  </head>
  <body style="padding-top: 100px;">
  	<%@include file="../../reg_login.jsp" %>
  	<div class="container-fluid">
        <div  class="row" style=" height:4px; background-color:#44b549;"></div>
        <div  class="row" style=" height:36px; background-color:#e7e8eb;"></div>
  		<div class="row" style="min-height:500px; border:1px solid #CCC;">
  			<div class="col-md-3 col-xs-4" style="margin-top:30px; margin-left:-15px; margin-right:-15px; text-align:center;" id="sidebar">
                  <ul class="nav nav-pills nav-stacked" style="padding:10px 0px; border-bottom:1px solid #e7e7eb;">
                      <li style="margin-left:-20px; margin-bottom:20px;"><h5><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;用户中心</h5></li>
                      <li role="presentation" class="active"><a href="#profile"><h6>基本信息</h6></a></li>
                      <li role="presentation"><a href="#update_password"><h6>修改密码</h6></a></li>
                   </ul>
                   <ul class="nav nav-pills nav-stacked" style="padding:10px 0px; border-bottom:1px solid #e7e7eb;">
                      <li style="margin-left:-20px; margin-bottom:20px;"><h5><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>&nbsp;基础功能</h5></li>
                      <c:if test="${sessionScope.userType eq 'MEDIA' or sessionScope.myUser.userId eq 16 or sessionScope.myUser.userId eq 8}">
	                      <li role="presentation"><a href="#wx_menu_mgr"><h6>菜单</h6></a></li>
	                      <li role="presentation"><a href="#wx_menu_mgr_condition"><h6>定制菜单</h6></a></li>
	                      <li role="presentation"><a href="#wx_menu_mgr_category"><h6>菜单category</h6></a></li>
	                      <li role="presentation"><a href="#wx_menu_mgr_button"><h6>菜单按钮</h6></a></li>
	                      <li role="presentation"><a href="#wx_menu_mgr_full"><h6>菜单full</h6></a></li>
	                      <li role="presentation"><a href="#wx_menu_mgr_match_rule"><h6>菜单match rule</h6></a></li>
	                      <li role="presentation"><a href="#wx_person_user"><h6>微信个人用户</h6></a></li>
                      </c:if>
                  </ul>
                  <ul class="nav nav-pills nav-stacked" style="padding:10px 0px; border-bottom:1px solid #e7e7eb;">
                      <li style="margin-left:-20px; margin-bottom:20px;"><h5><span class="glyphicon glyphicon-file" aria-hidden="true"></span>&nbsp;信息发布</h5></li>
                      <li role="presentation"><a href="#create_activity"><h6>报名征集</h6></a></li>
                      <cmn:privilege havePrivilege="<%= com.cobble.takeaway.util.CommonConstant.NUMBER_GUESS%>">
                      	<li role="presentation"><a href="#create_vote"><h6>网络投票</h6></a></li>
                      </cmn:privilege>
                      <c:if test="${sessionScope.userType eq 'MEDIA' or sessionScope.myUser.userId eq 16 or sessionScope.myUser.userId eq 8}">
                      	  <li role="presentation"><a href="#create_interactive2"><h6>抽奖活动</h6></a></li>
	                      <li role="presentation"><a href="#award"><h6>奖品</h6></a></li>
	                      <li role="presentation"><a href="#award_record"><h6>奖品记录</h6></a></li>
                      </c:if>
                  </ul>
                  <ul class="nav nav-pills nav-stacked" style="padding:10px 0px; border-bottom:1px solid #e7e7eb;">
                      <li style="margin-left:-20px; margin-bottom:20px;"><h5><span class="glyphicon glyphicon-star" aria-hidden="true"></span>&nbsp;o2o互动</h5></li>
                      <li role="presentation"><a href="#create_interactive"><h6>数字竞猜</h6></a></li>
                      <%-- <cmn:privilege havePrivilege="<%= com.cobble.takeaway.util.CommonConstant.NUMBER_GUESS%>">
                      	<li role="presentation"><a href="#create_interactive"><h6>数字竞猜</h6></a></li>
                      </cmn:privilege> --%>
                  </ul> 
                  <ul class="nav nav-pills nav-stacked" style="padding:10px 0px;">
                      <li style="margin-left:-20px; margin-bottom:20px;"><h5><span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>&nbsp;模版管理</h5></li>
                      <c:if test="${sessionScope.userType eq 'MEDIA'}">
                      	<li role="presentation"><a href="#create_wxTemplate"><h6>微官网首页</h6></a></li>
                      </c:if>
                  </ul>                    
		          
		            <!-- <li class="list-group-item active col-md-12 col-xs-6"><a href="#profile">媒体资料</a></li>
		            <li class="list-group-item col-md-12 col-xs-6"><a href="#update_password">修改密码</a></li>
		            <li class="list-group-item col-md-12 col-xs-6"><a href="#create_activity">线下活动管理</a></li>
		            <li class="list-group-item col-md-12 col-xs-6"><a href="#create_interactive">线上互动管理</a></li>
		            <li class="list-group-item col-md-12 col-xs-6"><a href="#create_wxTemplate">模板管理</a></li> -->			
	     	</div>
	     	<div class="col-md-9 col-xs-8" style="min-height:500px; border-left:1px solid #e7e7eb;">
	     		<div id="uc_content" style="padding-top:40px; padding-left:20px; margin-right:-30px;">
	     			<!-- profile -->
	     			<%@include file="profile_inc.jsp" %>
	     			<!-- create_activity -->
	     			<%@include file="activity_inc.jsp" %>
	     			<!-- award -->
	     			<%@include file="award_inc.jsp" %>
	     			<!-- award_record -->
	     			<%@include file="award_record_inc.jsp" %>
	     			<!-- wx_menu_mgr -->
	     			<%@include file="wx_menu_mgr_inc.jsp" %>
	     			<!-- wx_menu_mgr_condition -->
	     			<%@include file="wx_menu_mgr_condition_inc.jsp" %>
	     			<!-- wx_menu_mgr_category -->
	     			<%@include file="wx_menu_mgr_category_inc.jsp" %>
	     			<!-- wx_menu_mgr_button -->
	     			<%@include file="wx_menu_mgr_button_inc.jsp" %>
	     			<!-- wx_menu_mgr_category -->
	     			<%@include file="wx_menu_mgr_full_inc.jsp" %>
	     			<!-- wx_menu_mgr_category -->
	     			<%@include file="wx_menu_mgr_match_rule_inc.jsp" %>
	     			<!-- wx_person_user -->
	     			<%@include file="wx_person_user_inc.jsp" %>
	     			<!-- create_interactive -->
	     			<%@include file="interactive_inc.jsp" %>
	     			<%@include file="interactive2_inc.jsp" %>
	     			<!-- create_wxTemplate -->
	     			<%@include file="wx_template_inc.jsp" %>
	     			<!-- create_vote -->
	     			<%@include file="vote_inc.jsp" %>
	     			<!-- #update_password -->
					<%@include file="update_password_inc.jsp" %>
						
	     		</div>
	     	</div>
  		</div>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  	<%@include file="../../bottom.jsp"%>	
	</div>
  </body>
</html>
