<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="page/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="page/common/head.jsp"%>
<%-- <jsp:include page="page/common/head_index.jsp"></jsp:include> --%>
<!-- only for index page -->
<!--<script src="<cmn:base/>/js/jquery-1.2.4b.js" type="text/javascript"></script>-->
<script src="<cmn:base/>/js/ui.core.js" type="text/javascript"></script>
<script src="<cmn:base/>/js/ui.tabs.js" type="text/javascript"></script>
<%-- <script type="text/javascript" charset="utf-8"
	src="<cmn:base/>/js/activity_top.js"></script> --%>
<%-- <link href="<cmn:base/>/css/activity_top.css" rel="stylesheet"> --%>
</head>
<body style="padding-top: 100px;">
<security:authentication property="principal.username" var="username" />
<%@include file="reg_login.jsp"%>
<div class="container-fluid">
  <div class="row">
  	<div class="col-md-12">
  		help
  	</div>
  </div>
  <%@include file="bottom.jsp"%>
</div>
</body>
</html>
