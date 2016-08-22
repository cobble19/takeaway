<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<security:authentication property="principal.username" var="username"/>
<script src="<cmn:base/>/js/reg_login.js"></script>
    <style type="text/css">
		.navbar-fixed-top {
            z-index: 1050;
		}
	</style>
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="row" style="height:100px;">
      <div class="col-md-1 logo hidden-xs" style="height:100px; margin-left:10px;"></div>
      <div class="col-xs-6 logo visible-xs"></div>
      <div class="col-md-8 hidden-xs" style="height:100px;">
        <ul class="nav nav-pills" style="float:right; margin-top:25px; font-weight:bold; font-family:'新宋体';">
          <li role="presentation" class="active"><a href="http://www.deweiyizhan.com">首页</a></li>
          <li role="presentation" style="margin-left:35px;"><a href="#">公告</a></li>
          <li role="presentation" style="margin-left:35px;"><a href="#">功能</a></li>
          <li role="presentation" style="margin-left:35px;"><a href="#">资费</a></li>
          <li role="presentation" style="margin-left:35px;"><a href="#">帮助</a></li>
          <li role="presentation" style="margin-left:35px;"><a href="#">关于</a></li>
        </ul>
      </div>
      <div class="col-md-3 col-xs-6" style="height:100px;">
        <c:if test="${(empty username) or (not empty username and username eq 'anonymousUser')}">
          <button style="float:right; margin-top:30px;" type="button" class="btn btn-success" disabled="disabled" data-toggle="modal" data-target="#myModal">&nbsp;注册&nbsp;</button>
          <button style="float:right; margin-top:30px; margin-right:10px;" type="button" class="btn btn-warning" data-toggle="modal" data-target="#loginModal">&nbsp;登陆&nbsp;</button>
        </c:if>
        <c:if test="${not empty username and (username ne 'anonymousUser')}"> <a class="sy-dl-wz" href='<cmn:base/>/j_spring_security_logout'>退出</a>
          <c:choose>
            <c:when test="${sessionScope.userType eq 'PERSON'}"> <a class="sy-dl-wz" href="<cmn:base/>/web/person/usercenter">个人中心</a> </c:when>
            <c:when test="${sessionScope.userType eq 'ENTERPRISE'}"> <a class="sy-dl-wz" href="<cmn:base/>/web/enterprise/usercenter">商家中心</a> </c:when>
            <c:when test="${sessionScope.userType eq 'MEDIA'}"> <a class="sy-dl-wz" href="<cmn:base/>/web/media/usercenter">媒体中心</a> </c:when>
            <c:otherwise>
              <%-- 用户类型: <c:out value="${sessionScope.userType}"></c:out> --%>
            </c:otherwise>
          </c:choose>
          <div class="sy-dl-wz">欢迎:
            <%-- <c:out value="${username}"></c:out> --%>
            <%-- <c:out value="${sessionScope.myUser.nickname}"></c:out> --%>
            <c:if test="${sessionScope.myUser.nickname != null}">
              <c:out value="${sessionScope.myUser.nickname}"></c:out>
            </c:if>
            <c:if test="${sessionScope.myUser.nickname == null or sessionScope.myUser.nickname eq ''}">
              <c:out value="${username}"></c:out>
            </c:if>
          </div>
        </c:if>
      </div>
    </div>
    <div class="row">
      <!-- 注册 -->
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="关闭"> <span aria-hidden="true">&times;</span> </button>
              <h4 class="modal-title" id="myModalLabel">注册账户</h4>
            </div>
            <div class="modal-body">
              <div role="tabpanel">
                <ul class="nav nav-tabs nav-justified" role="tablist" id="myTab">
                  <li role="presentation" class="active"> <a href="#person" aria-controls="person" role="tab" data-toggle="tab" id="personReg">注册个人账户</a> </li>
                  <li role="presentation" class=""> <a href="#enterprise" aria-controls="enterprise" role="tab" data-toggle="tab" id="enterpriseReg">注册商家账户</a> </li>
                </ul>
                <div class="tab-content">
                  <div role="tabpanel" class="tab-pane active" id="person">
                    <div class="row">
                      <h3 style="margin-top:5px; margin-bottom:5px;" class="col-sm-offset-1 col-xs-offset-1 col-md-offset-1">个人用户注册</h3>
                    </div>
                    <form id="regForm" class="form-horizontal" role="form" action='<cmn:base/>/web/user/person/reg' method="post">
                      <div class="form-group">
                        <label class="control-label col-sm-3 col-md-2" for="username">用户名:</label>
                        <div class="col-sm-9 col-md-6">
                          <input class="form-control" id="username" name="username" required="required" minlength="1" value="" placeholder="请输入用户名">
                          <span id="usernameError" style="color: red;"></span> </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-sm-3 col-md-2" for="nickname">昵称:</label>
                        <div class="col-sm-9 col-md-6">
                          <input class="form-control" id="nickname" name="nickname" required="required" value="" placeholder="请输入昵称">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-sm-3 col-md-2" for="password">密码:</label>
                        <div class="col-sm-9 col-md-6">
                          <input class="form-control" type="password" id="password" name="password" required="required" value="" placeholder="请输入密码">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-sm-3 col-md-2" for="email">Email:</label>
                        <div class="col-sm-9 col-md-6">
                          <input class="form-control" type="email" id="email" name="email" required="required" value="" placeholder="请输入email">
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9 col-md-offset-2 col-md-4">
                          <button type="submit" class="btn btn-default" id="registerBtn">注册</button>
                        </div>
                      </div>
                    </form>
                  </div>
                  <div role="tabpanel" class="tab-pane" id="enterprise">
                    <div class="row">
                      <h3 style="margin-top:5px; margin-bottom:5px;" class="col-sm-offset-1 col-xs-offset-1 col-md-offset-1">商家用户注册</h3>
                    </div>
                    <form id="regForm" class="form-horizontal" role="form" action='<cmn:base/>/web/user/enterprise/reg' method="post">
                      <div class="form-group">
                        <label class="control-label col-sm-3 col-md-2" for="username">用户名:</label>
                        <div class="col-sm-9 col-md-6">
                          <input class="form-control" id="username" name="username" required="required" minlength="1" value="" placeholder="请输入用户名">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-sm-3 col-md-2" for="nickname">昵称:</label>
                        <div class="col-sm-9 col-md-6">
                          <input class="form-control" id="nickname" name="nickname" required="required" value="" placeholder="请输入昵称">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-sm-3 col-md-2" for="password">密码:</label>
                        <div class="col-sm-9 col-md-6">
                          <input class="form-control" type="password" id="password" name="password" required="required" value="" placeholder="请输入密码">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-sm-3 col-md-2" for="email">Email:</label>
                        <div class="col-sm-9 col-md-6">
                          <input class="form-control" type="email" id="email" name="email" required="required" value="" placeholder="请输入email">
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9 col-md-offset-2 col-md-4">
                          <button type="submit" class="btn btn-default" id="registerBtn">注册</button>
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
            <!-- modal-body -->
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
            <!-- modal-footer -->
          </div>
        </div>
      </div>
      <!-- 登陆 -->
      <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="关闭"> <span aria-hidden="true">&times;</span> </button>
              <h4 class="modal-title" id="myModalLabel">账号登陆</h4>
            </div>
            <div class="modal-body">
              <div role="tabpanel">
                <ul class="nav nav-tabs nav-justified" role="tablist" id="loginTab">
                  <li role="presentation" class="active"> <a href="#loginPerson" aria-controls="login" role="tab" data-toggle="tab">登陆</a> </li>
                  <!-- <li role="presentation">
	    							<a href="#loginEnterprise" aria-controls="loginEnterprise" role="tab" data-toggle="tab">企业登陆</a>
	    						</li> -->
                </ul>
                <div class="tab-content">
                  <div role="tabpanel" class="tab-pane active" id="loginPerson">
                    <form action="<cmn:base/>/j_spring_security_check" id="loginForm" method="post">
                      <table>
                        <tbody>
                          <tr>
                            <td class="login-label form-lable">用户名:</td>
                            <td><input class="login-input form-control" id="name" name="j_username" type="text" placeholder="登陆账号"/></td>
                          </tr>
                          <tr>
                            <td class="login-label form-lable">密码:</td>
                            <td><input class="login-input form-control" id="password" name="j_password" type="password" placeholder="密码"/></td>
                          </tr>
                          <tr>
                            <td></td>
                            <td><input class="login-btn btn btn-primary" id="loginBtn" title="登陆" value="登陆" type="submit"/></td>
                          </tr>
                        </tbody>
                      </table>
                    </form>
                  </div>
                  <!-- <div role="tabpanel" class="tab-pane" id="loginEnterprise">enterprise</div> -->
                </div>
              </div>
            </div>
            <!-- modal-body -->
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
              <!-- <button type="button" class="btn btn-primary">创建</button> -->
            </div>
            <!-- modal-footer -->
          </div>
        </div>
      </div>
    </div>
  </div>
</nav>
