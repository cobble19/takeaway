<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
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
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/profile_inc.js"></script>
