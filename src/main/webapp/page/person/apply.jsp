<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cmn" uri="/WEB-INF/tlds/common.tld" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>申请参加活动</title>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<cmn:base/>/jquery/jquery-1.11.1.min.js"></script>
    <script src="<cmn:base/>/jquery/jquery-migrate-1.2.1.min.js"></script>
    <!-- Bootstrap -->
    <link href="<cmn:base/>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<cmn:base/>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/lang/zh-cn/zh-cn.js"></script>
    
    <%-- <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/apply.js"></script> --%>
    
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">活动发布</h2>
  		</div>
  		<form class="form-horizontal" role="form" action='<cmn:base/>/web/enterprise/activity/add' method="post">
 			<div class="form-group">
 				<label class="control-label" for="title">主题:</label>
 				<div class="">
 					<input class="form-control" id="title" name="title" placeholder="请输入本次活动主题">
 				</div>
 			</div>
 			<div class="form-group">
 				<label class="control-label" for="content">活动介绍:</label>
 				<div style="" class="">
 					<!-- <textarea rows="5" cols="20" id="content" name="content" placeholder="请输入本次活动内容"></textarea>
 					<input class="form-control" type="text" id="content" name="content" placeholder="请输入本次活动内容"> -->
 					<script id="editor" type="text/plain" name="content"></script>
 				</div>
 			</div>
 			<div class="form-group">
	 			<div class="">
	  				<button type="submit" class="btn btn-default" id="addBtn">创建</button>
	 			</div>
 			</div>
  		</form>
  		
		<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
  </body>
</html>