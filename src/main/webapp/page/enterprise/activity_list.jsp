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
    <title>活动列表</title>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<cmn:base/>/jquery/jquery-1.11.1.min.js"></script>
    <script src="<cmn:base/>/jquery/jquery-migrate-1.2.1.min.js"></script>
    
    <script src="<cmn:base/>/js/thirdpart/jquery-ui-1.11.4/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-ui-1.11.4/jquery-ui.min.css">
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-ui-1.11.4/jquery-ui.structure.min.css">
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-ui-1.11.4/jquery-ui.theme.min.css">

    <!-- Bootstrap -->
    <link href="<cmn:base/>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<cmn:base/>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>

	<!--[if lte IE 6]>
	<script type="text/javascript" src="<cmn:base/>/bootstrap/js/bootstrap-ie.js"></script>
	<![endif]-->
	<!-- Just for debugging purposes. Don't actually copy this line! -->
	<!--[if lt IE 9]>
	<script src="<cmn:base/>/bootstrap/js/ie8-responsive-file-warning.js"></script>
	<![endif]-->
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
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/media/js/jquery.dataTables.min.js"></script>
    <link href="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/media/css/jquery.dataTables.min.css" rel="stylesheet">
   
    <script src="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/integration/bootstrap/3/dataTables.bootstrap.js"></script>
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/integration/bootstrap/3/dataTables.bootstrap.css">
    
    
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/enterprise/activity_list.js"></script>
	<link href="<cmn:base/>/css/enterprise/activity_list.css" rel="stylesheet">
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<h2 class="">活动申请</h2>
  		<table id="activityList" class="display table table-striped table-bordered" cellspacing="0" width="100%">
  				<thead>
  					<tr>
  						<th>申请</th>
  						<th>标识</th>
  						<th>标题</th>
  						<th>内容</th>
  					</tr>
  				</thead>
  				<!-- <tfoot>
  					<tr>
  						<th>申请</th>
  						<th>标识</th>
  						<th>标题</th>
  						<th>内容</th>
  					</tr>
  				</tfoot> -->
  			</table>
  		</div>
		<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
  </body>
</html>