<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			<div id="update_password">
                        <form id="pwdForm" role="form" action='' method="post">
					  			<input type="hidden" id="userId" name="userId" value="${myUser.userId}"/>
                        <div class="panel panel-success">
  <!-- Default panel contents -->
  <div class="panel-heading"><p class="h5" style="font-weight:bold;">密码修改</p></div>

  <!-- List group -->
    <ul class="list-group">                        
    <li class="list-group-item">					 			<div class="form-inline">
					 				<label class="h5" for="passwordOld">旧密码:</label>
				 					<input type="password" class="form-control input-sm" id="passwordOld" name="passwordOld" minlength="2" required="required" placeholder="请输入旧密码">
					 			</div></li>
    <li class="list-group-item"><div class="form-inline">
					 				<label class="h5" for="password">新密码:</label>
					 				<input type="password" class="form-control input-sm" id="password" name="password" minlength="2" required="required" placeholder="请输入新密码">
					 			</div></li>
  </ul>
  <div class="panel-footer" style="text-align:right;"><button type="button" class="btn btn-xs btn-default" id="pwdChg">修改密码</button></div>
</div></form>
</div>
                        
                        
                        <!--<strong>修改密码</strong>
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
					 			<!--<div class="form-group">
						 			<div class="">
						  				<button type="button" class="btn btn-default" id="pwdChg">修改密码</button>
						 			</div>
					 			</div>
					  		</form>
						</div>
					</div>-->	<!-- pwd change end -->
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/update_password_inc.js"></script>
