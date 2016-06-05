<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../page/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
  
	<link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
    <%@include file="../../page/common/head.jsp" %>
    
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/user_center.js"></script>
	<link href="<cmn:base/>/css/enterprise/activity_list.css" rel="stylesheet">
	
    
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
		
		$('#sidebar a[href=#profile]').trigger('click');
    		
    	})
    </script>

  </head>
  <body>
  	<div class="container-fluid">
        <div  class="row" style=" height:4px; background-color:#44b549;"></div>
	  	<%@include file="../../reg_login_full.jsp" %>
        <div  class="row" style=" height:36px; background-color:#e7e8eb;"></div>
  		<div class="row" style="min-height:500px; border:1px solid #CCC;">
  			<div class="col-md-3 col-xs-4" style="margin-top:30px; margin-left:-15px; margin-right:-15px; text-align:center;" id="sidebar">
                  <ul class="nav nav-pills nav-stacked" style="padding:10px 0px; border-bottom:1px solid #e7e7eb;">
                      <li style="margin-left:-20px; margin-bottom:20px;"><h5><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;帐号中心</h5></li>
                      <li role="presentation" class="active"><a href="#profile"><h6>基本资料</h6></a></li>
                      <li role="presentation"><a href="#update_password"><h6>修改密码</h6></a></li>
                  </ul>
                  <ul class="nav nav-pills nav-stacked" style="padding:10px 0px; border-bottom:1px solid #e7e7eb;">
                      <li style="margin-left:-20px; margin-bottom:20px;"><h5><span class="glyphicon glyphicon-file" aria-hidden="true"></span>&nbsp;信息发布</h5></li>
                      <li role="presentation"><a href="#create_activity"><h6>表单类信息</h6></a></li>
                  </ul>
                  <ul class="nav nav-pills nav-stacked" style="padding:10px 0px; border-bottom:1px solid #e7e7eb;">
                      <li style="margin-left:-20px; margin-bottom:20px;"><h5><span class="glyphicon glyphicon-star" aria-hidden="true"></span>&nbsp;o2o互动</h5></li>
                      <li role="presentation"><a href="#create_interactive"><h6>数字竞猜</h6></a></li>
                  </ul> 
                  <ul class="nav nav-pills nav-stacked" style="padding:10px 0px;">
                      <li style="margin-left:-20px; margin-bottom:20px;"><h5><span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>&nbsp;模版管理</h5></li>
                      <li role="presentation"><a href="#create_wxTemplate"><h6>微官网首页</h6></a></li>
                  </ul>                    
		          
		            <!-- <li class="list-group-item active col-md-12 col-xs-6"><a href="#profile">媒体资料</a></li>
		            <li class="list-group-item col-md-12 col-xs-6"><a href="#update_password">修改密码</a></li>
		            <li class="list-group-item col-md-12 col-xs-6"><a href="#create_activity">线下活动管理</a></li>
		            <li class="list-group-item col-md-12 col-xs-6"><a href="#create_interactive">线上互动管理</a></li>
		            <li class="list-group-item col-md-12 col-xs-6"><a href="#create_wxTemplate">模板管理</a></li> -->			
	     	</div>
	     	<div class="col-md-9 col-xs-8" style="min-height:500px; border-left:1px solid #e7e7eb;">
	     		<div id="uc_content" style="padding-top:40px; padding-left:20px; margin-right:-30px;">
	     			<div id="profile">
	     				<strong>基本资料</strong>
	     				<div class=" form-inline" style="margin-bottom:10px; margin-top:15px;">
	     					<label class="" for="username">帐　　号：</label>
	     					<%-- <input type="text" name="username" id="username" value="${myUser.username}" class="form-control"> --%>
	     					 <c:out value="${myUser.username }"></c:out><%-- /<c:out value="${myUser.userId }"></c:out> --%><br/>
	     				</div>
	     				<div class=" form-inline" style="margin-bottom:10px;">
	     					<label>昵　　称：</label><input type="text" name="nickname" id="nickname" value="${myUser.nickname}" class="form-control input-sm"><br/>
	     				</div>
	     				<div class=" form-inline" style="margin-bottom:10px;">
	     					<label>电子邮箱：</label><input type="text" name="email" id="email" value="${myUser.email}" class="form-control input-sm"><br/>
	     				</div>
	     				<button id="updateInfoBtn" class="btn btn-default">修改</button>
	     				<%-- <label>昵称： </label><c:out value="${myUser.nickname }"></c:out> --%><br/>
	     				<!-- <label>密码： </label><button id="pwdChg4OpenDialog" class="btn btn-default">修改密码</button> -->
	     				
	     			</div>	<!-- profile end -->
	     			<div id="create_activity">
	     				<strong>表单类信息</strong>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-success btn-xs" href='<cmn:base/>/page/media/activity.jsp'>添加</a>
	     					
	     					<input id="searchBtn" type="button" class="btn btn-info btn-xs" value="查询">
	     					<input id="deleteBtn" type="button" class="btn btn-danger btn-xs" value="删除">
	     					
	     				</div>
	     				<div id="queryCondition" style="">
	     					<fieldset class="scheduler-border">
	     						<legend class="scheduler-border"><h5>查询条件</h5></legend>
		     					<select id="typeCode" name="typeCode" style="font-size:12px;">
		     						<option value="" selected="selected">所有</option>
		     						<option value="1">报名申请</option>
		     						<option value="2">征集调查</option>
		     					</select>
	     					</fieldset>
	     				</div>
                        <div class="table-responsive">
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
				  						<th>类型</th>
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
                            </div>
	     			</div>	<!-- create_activity end -->
	     			
	     			<div id="create_interactive">
	     				<strong>数字竞猜</strong>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-success btn-xs" target="" href='<cmn:base/>/page/media/interactive.jsp'>添加</a>
	     					
	     					<input id="searchBtn4Interactive" type="button" class="btn btn-info btn-xs" value="查询">
	     					<input id="deleteBtn4Interactive" type="button" class="btn btn-danger btn-xs" value="删除">
	     					
	     				</div>
                        <div class="table-responsive">
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
                            </div>
	     			</div>	<!-- create_interactive end -->
	     			
	     			<!-- create_wxTemplate -->
	     			<%@include file="wx_template_inc.jsp" %>
	     			
					<div id="update_password">
                        <strong>修改密码</strong>
						<div style=" margin-top:15px;">
							<form id="pwdForm" role="form" action='' method="post">
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
	  			<input type="hidden" id="activityId">
	 			<div class="form-group">
	 				<label class="control-label" for="logoImg">图片:</label>
	 				<div class="">
	 					<input class="form-control" id="logoImg" name="logoImg" readonly="readonly" required="required" placeholder="请上传图片">
	 					<input class="" id="pic" name="pic" type="file">
	 					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传图片">
	 				</div>
	 			</div>
	 			<!-- <div class="form-group">
		 			<div class="">
		  				<button type="button" class="btn btn-default" id="addBtn">添加图片</button>
		 			</div>
	 			</div> -->
	  		</form>
  		</div>
  		
  		<!-- 申请人信息model数据 -->
  		<div id="apply2AttrModelDiv" class="">
  			<fieldset class="scheduler-border">
  				<legend class="scheduler-border"></legend>
		  		<form id="apply2AttrModelForm" class="form-horizontal" role="form" action='<cmn:base/>/web/media/apply2AttrModel/add' method="post">
	  				<input type="hidden" id="activityId">
		 			<div class="form-group static-attr">
		 				<label class="control-label col-sm-4" for="attr0">条目1:</label>
		 				<div class="col-sm-8">
		 					<input type="text" class="form-control attr" required="required" readonly id="attr0" name="attr0" placeholder="请输入条目1内容" value="个人名称">
		 					<input type="text" class="form-control remark" required="required" readonly id="remark0" name="remark0" placeholder="请输入条目1内容" value="个人名称">
		 				</div>
		 			</div>
		 			<div class="form-group static-attr">
		 				<label class="control-label col-sm-4" for="attr1">条目2:</label>
		 				<div class="col-sm-8">
		 					<input type="text" class="form-control attr" required="required" readonly id="attr1" name="attr1" placeholder="请输入条目2内容" value="手机号码">
		 					<input type="text" class="form-control remark" required="required" readonly id="remark1" name="remark1" placeholder="请输入条目2内容" value="手机号码">
		 				</div>
		 			</div>
		 			<!-- <div class="form-group static-attr">
		 				<label class="control-label col-sm-4" for="attr2">信息类型2:</label>
		 				<div class="col-sm-8">
		 					<input type="text" class="form-control" readonly id="attr2" name="attr2" placeholder="请输入信息类型1内容" value="性别">
		 				</div>
		 			</div> -->
		 			<!-- <div class="form-group">
		 				<label class="control-label" for="imgSrc">图片:</label>
		 				<div class="">
		 					<input class="form-control" id="imgSrc" name="imgSrc" readonly="readonly" required="required" placeholder="请上传图片">
		 					<input class="" id="pic" name="pic" type="file">
		 					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传">
		 				</div>
		 			</div> -->
		 			<div class="form-group">
			 			<div class="">
			 				<!-- <button type="button" class="btn btn-default" id="addPicBtn">增加图片/文件</button> -->
			 				<button type="button" class="btn btn-default" id="addAttrBtn">增加条目</button>
			 				<button type="button" class="btn btn-default" id="popAttrBtn">移除最后一个条目</button>
			  				<button type="button" class="btn btn-default" id="addBtn">确定</button>
			  				<button type="button" class="btn btn-default" id="deleteAttrBtn">删除</button>
			 			</div>
		 			</div>
		  		</form>
  			</fieldset>
  		</div>
  		
  		<div id="tpl_static" style="visibility: hidden;">
  			<div class="form-group static-attr">
		 				<label class="control-label col-sm-4" for="attr0">条目1:</label>
		 				<div class="col-sm-8">
		 					<input type="text" class="form-control attr" required="required" readonly id="attr0" name="attr0" placeholder="请输入条目1内容" value="个人名称">
		 					<input type="text" class="form-control remark" required="required" readonly id="remark0" name="remark0" placeholder="请输入条目1内容" value="个人名称">
		 				</div>
 			</div>
 			<div class="form-group static-attr">
 				<label class="control-label col-sm-4" for="attr1">条目2:</label>
 				<div class="col-sm-8">
 					<input type="text" class="form-control attr" required="required" readonly id="attr1" name="attr1" placeholder="请输入条目2内容" value="手机号码">
 					<input type="text" class="form-control remark" required="required" readonly id="remark1" name="remark1" placeholder="请输入条目2内容" value="手机号码">
 				</div>
 			</div>
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
