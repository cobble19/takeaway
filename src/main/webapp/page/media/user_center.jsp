<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../../page/common/head.jsp" %>
    
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/user_center.js"></script>
	<link href="<cmn:base/>/css/enterprise/activity_list.css" rel="stylesheet">
	
	<link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
    
    <script>
    	$(document).ready(function() {
    		
    	$('#sidebar a').click(function() {
			$('#sidebar a').each(function() {
				$(this).removeClass('active');
			})
			$this = $(this);
			$this.addClass('active');
			var contentId = $this.attr('href').substring(1);
			console.log('contentId: ' + contentId);
			$('#uc_content > div').each(function(i, e) {
				$(this).hide();
			})
			$('#' + contentId).show();
		});
		
		$('#sidebar a[href=#profile]').trigger('click');
    		
    	})
    </script>
  </head>
  <body>
  	<div class="container">
	  	<%@include file="../../reg_login_full.jsp" %>
  		<div class="row" style="margin-top:20px; min-height:500px; border:1px solid #CCC;">
  			<div class="col-md-3 col-xs-12" id="sidebar">
		          <div class="list-group" style="margin-top:10px;">
		            <a href="#profile" class="list-group-item active col-md-12 col-xs-6">媒体资料</a>
		            <a href="#update_password" class="list-group-item col-md-12 col-xs-6">修改密码</a>
		            <a href="#create_activity" class="list-group-item col-md-12 col-xs-6">线下活动管理</a>
		            <a href="#create_interactive" class="list-group-item col-md-12 col-xs-6">线上互动管理</a>
		            <a href="#create_wxTemplate" class="list-group-item col-md-12 col-xs-6">模板管理</a>
		          </div> 				
	     	</div>
	     	<div class="col-md-9 col-xs-9">
	     		<div id="uc_content" style="padding-top: 10px;">
	     			<div id="profile" class="row">
	     				<h3 class="col-md-12" style="margin-bottom:20px;">媒体资料</h3>
	     				<div class=" form-inline col-md-12" style="margin-bottom:10px;">
	     					<label class="" for="username">帐　　号：</label>
	     					<%-- <input type="text" name="username" id="username" value="${myUser.username}" class="form-control"> --%>
	     					 <c:out value="${myUser.username }"></c:out><%-- /<c:out value="${myUser.userId }"></c:out> --%><br/>
	     				</div>
	     				<div class=" form-inline col-md-12" style="margin-bottom:10px;">
	     					<label>昵　　称：</label><input type="text" name="nickname" id="nickname" value="${myUser.nickname}" class="form-control input-sm"><br/>
	     				</div>
	     				<div class=" form-inline col-md-12" style="margin-bottom:10px;">
	     					<label>电子邮箱：</label><input type="text" name="email" id="email" value="${myUser.email}" class="form-control input-sm"><br/>
	     				</div>
	     				<button id="updateInfoBtn" class="col-md-1 btn btn-default" style="margin-left:20px;">修改</button>
	     				<%-- <label>昵称： </label><c:out value="${myUser.nickname }"></c:out> --%><br/>
	     				<!-- <label>密码： </label><button id="pwdChg4OpenDialog" class="btn btn-default">修改密码</button> -->
	     				
	     			</div>	<!-- profile end -->
	     			<div id="create_activity">
	     				<h3>线下活动管理</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" href='<cmn:base/>/page/media/activity.jsp'>添加活动</a>
	     					
	     					<input id="searchBtn" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll" id="chkBoxAll">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>标题</th>
				  						<th>开始时间</th>
				  						<th>截止时间</th>
				  						<th>期限</th>
				  						<th>状态</th>
				  						<th>内容简介</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  				<!-- <tfoot>
				  					<tr>
				  						<th>No.</th>
				  						<th>标识</th>
				  						<th>标题</th>
				  						<th>内容</th>
				  					</tr>
				  				</tfoot> -->
				  			</table>
	     			</div>	<!-- create_activity end -->
	     			
	     			<div id="create_interactive">
	     				<h3>线上活动管理</h3>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-primary btn-xs" target="" href='<cmn:base/>/page/media/interactive.jsp'>添加互动</a>
	     					
	     					<input id="searchBtn4Interactive" type="button" class="btn btn-default btn-xs" value="查询">
	     					<input id="deleteBtn4Interactive" type="button" class="btn btn-default btn-xs" value="删除">
	     					
	     				</div>
				  		<table id="dbTable4Interactive" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll4Interactive" id="chkBoxAll4Interactive">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>名称</th>
				  						<th>开始时间</th>
				  						<th>截止时间</th>
				  						<th>期限</th>
				  						<th>状态</th>
				  						<th>奖品</th>
				  						<th>答案</th>
				  						<th>内容简介</th>
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  				<!-- <tfoot>
				  					<tr>
				  						<th>No.</th>
				  						<th>标识</th>
				  						<th>标题</th>
				  						<th>内容</th>
				  					</tr>
				  				</tfoot> -->
				  			</table>
	     			</div>	<!-- create_interactive end -->
	     			
	     			<!-- create_wxTemplate -->
	     			<%@include file="wx_template_inc.jsp" %>
	     			
					<div id="update_password">
                        <h3 style="margin-bottom:20px;">修改密码</h3>
						<div class="row" style="margin-left:5px;">
							<form class="form-inline" id="pwdForm" role="form" action='' method="post">
					  			<input type="hidden" id="userId" name="userId" value="${myUser.userId}"/>
					 			<div class="form-group">
					 				<label class="" for="passwordOld">旧密码:</label>
				 					<input type="password" class="form-control" id="passwordOld" name="passwordOld" minlength="2" required="required" placeholder="请输入旧密码">
					 			</div>
					 			<div class="form-group ">
					 				<label class="" for="password">新密码:</label>
					 				<input type="password" class="form-control" id="password" name="password" minlength="2" required="required" placeholder="请输入新密码">
					 			</div>
					 			<!-- <button type="button" class="btn btn-default" id="pwdChg">修改密码</button> -->
					 			<div class="form-group">
						 			<div class="">
						  				<button type="button" class="btn btn-default" id="pwdChg">修改密码</button>
						 			</div>
					 			</div>
					  		</form>
						</div>
					</div>	<!-- pwd change end -->
						
	     		</div>
	     	</div>
  		</div>
  		
  		<div id="progress">数据加载中。。。</div>
  		
  		<div id="activityDetailDiv" class="row" style="width: 450px; height: 120px;">
	  			<input type="text" class="" style="width: 300px;" id="activityDetailUrl"><br/>
	  			<!-- <a href="#none" id="copyInput">点击复制单中的文本</a> -->
	  			<!-- <input type="button" id="copyInput" class="btn btn-info" value="复制链接"> -->
	  			<span id="copyMsg" style="color: red;"></span>
	  			<button class="btn btn-info" id="copyBtn" data-clipboard-target="#activityDetailUrl">
				    复制链接
				</button>
  		</div>
  		<div id="picDiv">
	  		<form id="picForm" class="form-horizontal" role="form" action='' method="post">
	  			<input type="text" id="activityId">
	 			<div class="form-group">
	 				<label class="control-label" for="logoImg">图片:</label>
	 				<div class="">
	 					<input class="form-control" id="logoImg" name="logoImg" readonly="readonly" required="required" placeholder="请上传图片">
	 					<input class="" id="pic" name="pic" type="file">
	 					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传">
	 				</div>
	 			</div>
	 			<div class="form-group">
		 			<div class="">
		  				<button type="button" class="btn btn-default" id="addBtn">添加图片</button>
		 			</div>
	 			</div>
	  		</form>
  		</div>
		<!-- <button class="zclip" data-zclip-text="Testing 1-2-3!">Click to copy!</button> -->
  		
  		<!-- <div id="verifyDiv">
			<form class="form-inline" id="verifyForm" role="form" action='' method="post">
				<input type="hidden" id="interactiveId" name="interactiveId"/>
	 			<div class="form-group">
	 				<label class="" for="verifyCode">验证码:</label>
 					<input type="text" class="form-control" id="verifyCode" name="verifyCode" minlength="8" required="required" placeholder="请输入验证码">
	 			</div>
	 			<div class="form-group">
		 			<div class="">
		  				<button type="button" class="btn btn-default" id="verifyBtn">验证</button>
		 			</div>
	 			</div>
	  		</form>
		</div> -->
  		
	</div>
  </body>
</html>
