<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <div class="weui_tab">
  <div class="weui_navbar">
    <a class="weui_navbar_item" href="<cmn:base/>/wx/${sessionScope.indexCode}">首页</a>
    <a class="weui_navbar_item" href="<cmn:base/>/web/wx/usercenter/${sessionScope.indexCode}/person">会员中心</a>
    <a class="weui_navbar_item" href="<cmn:base/>/web/wx/oauth2/third/authorizer/qrcode?authorizerAppId=${sessionScope.authorizerAppId}">关注我们</a>
  </div>
  <div class="weui_tab_bd">
    <p></p>
  </div>
</div> --%>

<div class="row" style="background-color:#f1f1f1;">
    <div class="col-md-4 col-xs-4" >
    <c:out value="${sessionScope.indexCode}"/> | <c:out value="${indexCode}"/>
    <a style="text-align:center; margin-bottom:10px; margin-top:20px; font-size:12px; line-height:12px; display:block;" href="<cmn:base/>/wx/${sessionScope.indexCode}">首页</a>
    </div>
    <div class="col-md-4 col-xs-4" >
    <a style="text-align:center; margin-bottom:10px; margin-top:20px; font-size:12px; line-height:12px; display:block;" href="<cmn:base/>/web/wx/usercenter/${sessionScope.indexCode}/person">会员中心</a>
    </div>
    <div class="col-md-4 col-xs-4" >
    <a style="text-align:center; margin-bottom:10px; margin-top:20px; font-size:12px; line-height:12px; display:block;" href="<cmn:base/>/web/wx/oauth2/third/authorizer/qrcode?authorizerAppId=${sessionScope.authorizerAppId}">关注我们</a>
    </div>            
</div>

