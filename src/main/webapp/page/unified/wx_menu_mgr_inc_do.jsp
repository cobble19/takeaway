<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<%@include file="../common/taglib.jsp" %>

<div id="wx_menu_mgr_inc_do">
	<h3>微信菜单管理</h3>
	<div style="height: 50px; line-height: 50px;">
		<a class="btn btn-primary btn-xs" target="_blank" href='<cmn:base/>/page/unified/wx_menu_mgr_add.jsp'>添加</a> 
		<input id="searchBtn4WxMenuMgr" type="button" class="btn btn-default btn-xs" value="查询"> 
		<input id="deleteBtn4WxMenuMgr" type="button" class="btn btn-default btn-xs" value="删除">

	</div>
	<div>
		<c:forEach items="${wxMenuMgrCagetoryPOJOs}" var="wxMenuMgrCategoryPOJO" varStatus="st">
			<c:out value="${wxMenuMgrCategoryPOJO.name}"></c:out>
			<c:forEach items="${wxMenuMgrCategoryPOJO.wxMenuMgrButtonPOJOs}" var="wxMenuMgrButtonPOJO" varStatus="st2">
				<c:out value="${wxMenuMgrButtonPOJO.type}"></c:out>
				<c:out value="${wxMenuMgrButtonPOJO.url}"></c:out>
				<c:out value="${wxMenuMgrButtonPOJO.key}"></c:out>
			</c:forEach>
		</c:forEach>
	</div>
</div>
<!-- wx_menu_mgr end -->
<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/wx_menu_mgr_inc_do.js"></script>
