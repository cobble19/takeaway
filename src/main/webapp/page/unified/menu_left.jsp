<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cobble.takeaway.util.CommonConstant" %>
<%@include file="../../page/common/taglib.jsp" %>

<ul class="nav nav-pills nav-stacked" style="padding: 10px 0px;">
	<li style="margin-left: -20px; margin-bottom: 20px;">
		<h5 style="color: #42b047; font-weight: bold;">
			<span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;用户中心
		</h5>
	</li>
	<li role="presentation" class="active"><a href='<%=request.getContextPath()%>/web/unified/profile'><h6>基本信息</h6></a></li>
	<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/update_password_single.jsp"><h6>修改密码</h6></a></li>
</ul>
<ul class="nav nav-pills nav-stacked" style="padding: 10px 0px;">
	<li style="margin-left: -20px; margin-bottom: 20px;">
		<h5 style="color: #42b047; font-weight: bold;">
			<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>&nbsp;基础功能
		</h5>
	</li>
	<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/wx_menu_mgr_entry_single.jsp"><h6>菜单设置</h6></a></li>
	<c:if
		test="${sessionScope.myUser.userId eq 16 or sessionScope.myUser.userId eq 8}">
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/wx_menu_mgr_single.jsp"><h6>菜单</h6></a></li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/wx_menu_mgr_condition_single.jsp"><h6>定制菜单</h6></a></li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/wx_menu_mgr_category_single.jsp"><h6>菜单category</h6></a></li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/wx_menu_mgr_button_single.jsp"><h6>菜单按钮</h6></a></li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/wx_menu_mgr_full_single.jsp"><h6>菜单full</h6></a></li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/wx_menu_mgr_match_rule_single.jsp"><h6>菜单match rule</h6></a></li>
	</c:if>
	<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/wx_resp_msg_single.jsp"><h6>关键字设置</h6></a></li>

</ul>

<ul class="nav nav-pills nav-stacked" style="padding: 10px 0px;">
	<li style="margin-left: -20px; margin-bottom: 20px;">
		<h5 style="color: #42b047; font-weight: bold;">
			<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>&nbsp;会员功能
		</h5>
	</li>
	<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/point_event_single.jsp"><h6>基础设置</h6></a></li>
	<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/wx_person_user_single.jsp"><h6>会员详情</h6></a></li>
</ul>

<ul class="nav nav-pills nav-stacked" style="padding: 10px 0px;">
	<li style="margin-left: -20px; margin-bottom: 20px;">
		<h5 style="color: #42b047; font-weight: bold;">
			<span class="glyphicon glyphicon-file" aria-hidden="true"></span>&nbsp;信息发布
		</h5>
	</li>
	<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/activity_single.jsp"><h6>报名征集</h6></a></li>
	<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/activity_gather_single.jsp"><h6>报名征集II</h6></a></li>
	<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/activity_registration_single.jsp"><h6>申请报名</h6></a></li>

	<c:if test="${sessionScope.myUser.userId eq 16 
				or sessionScope.myUser.userId eq 8 
				or sessionScope.myUser.userId eq 22
				or sessionScope.authorizerAppId eq 'wxeaa5221eab533277'}">
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/vote_single.jsp"><h6>网络投票</h6></a></li>
	</c:if>
	<c:if test="${sessionScope.myUser.userId eq 16 
				or sessionScope.myUser.userId eq 8 
				or sessionScope.authorizerAppId eq 'wxeaa5221eab533277'}">
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/interactive2_single.jsp"><h6>抽奖活动</h6></a></li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/award_single.jsp"><h6>奖品</h6></a></li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/award_record_single.jsp"><h6>奖品记录</h6></a></li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/point_record_single.jsp"><h6>积分记录</h6></a></li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/point_summary_single.jsp"><h6>积分总结</h6></a></li>
	</c:if>
</ul>

<ul class="nav nav-pills nav-stacked" style="padding: 10px 0px;">
	<li style="margin-left: -20px; margin-bottom: 20px;">
		<h5 style="color: #42b047; font-weight: bold;">
			<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>&nbsp;电子商务
		</h5>
	</li>
	<c:if test="${sessionScope.myUser.userId eq 16 
				or sessionScope.myUser.userId eq 8 
				or sessionScope.myUser.userId eq 22
				or sessionScope.authorizerAppId eq 'wxeaa5221eab533277'}">
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/ec_product_single.jsp"><h6>商品管理</h6></a></li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/ec_order_single.jsp"><h6>订单管理</h6></a></li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/ec_cart_single.jsp"><h6>购物车管理</h6></a></li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/ec_wx_card_single.jsp"><h6>微信卡券管理</h6></a></li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/ec_wx_card_base_single.jsp"><h6>用户微信卡券初始化管理</h6></a></li>
	</c:if>

	<c:if test="${sessionScope.myUser.userId eq 16
				or sessionScope.myUser.userId eq 8
				or sessionScope.myUser.userId eq 22
				or sessionScope.authorizerAppId eq 'wxeaa5221eab533277'}">
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/wx_msg_event_log_single.jsp"><h6>微信事件</h6></a></li>
	</c:if>
</ul>
<ul class="nav nav-pills nav-stacked" style="padding: 10px 0px;">
	<c:if test="${sessionScope.myUser.userId eq 16 or sessionScope.myUser.userId eq 8}">
		<li style="margin-left: -20px; margin-bottom: 20px;">
			<h5 style="color: #42b047; font-weight: bold;">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>&nbsp;o2o互动
			</h5>
		</li>
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/interactive_single.jsp"><h6>数字竞猜</h6></a></li>
	</c:if>
	<%-- <cmn:privilege havePrivilege="<%= com.cobble.takeaway.util.CommonConstant.NUMBER_GUESS%>">
                      	<li role="presentation"><a href="#create_interactive"><h6>数字竞猜</h6></a></li>
                      </cmn:privilege> --%>
</ul>

<ul class="nav nav-pills nav-stacked" style="padding: 10px 0px;">
	<li style="margin-left: -20px; margin-bottom: 20px;">
		<h5 style="color: #42b047; font-weight: bold;">
			<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>&nbsp;模版管理
		</h5>
	</li>
	<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/wx_template_single.jsp"><h6>微官网首页</h6></a></li>
	<c:if test="${sessionScope.myUser.userId eq 16 or sessionScope.myUser.userId eq 8}">
		<li role="presentation"><a href="<%=request.getContextPath()%>/page/unified/rel_wx_index_map_single.jsp"><h6>微官网修改</h6></a></li>
	</c:if>
</ul>

<!-- <li class="list-group-item active col-md-12 col-xs-6"><a href="#profile">媒体资料</a></li>
    <li class="list-group-item col-md-12 col-xs-6"><a href="#update_password">修改密码</a></li>
    <li class="list-group-item col-md-12 col-xs-6"><a href="#create_activity">线下活动管理</a></li>
    <li class="list-group-item col-md-12 col-xs-6"><a href="#create_interactive">线上互动管理</a></li>
    <li class="list-group-item col-md-12 col-xs-6"><a href="#create_wxTemplate">模板管理</a></li> -->			
  		
  		
