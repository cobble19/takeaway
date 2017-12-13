<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@page import="java.util.Date" %>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
  
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/interactive2_lottery_voice_detail.js"></script>
    
    <link href="<cmn:base/>/css/lottery/lottery.css" rel="stylesheet">
    
	<!-- JQuery-weui -->
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/weui/weui.css">
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/weui/jquery-weui.css">
    <script src="<cmn:base/>/js/thirdpart/weui/jquery-weui.js"></script>
    <script src="<cmn:base/>/js/thirdpart/weui/city-picker.js"></script>
    <script src="<cmn:base/>/js/thirdpart/weui/swiper.js"></script>
    <script src="<cmn:base/>/js/thirdpart/weui/zepto.js"></script>
  </head>
  <body>
  
	<div class="container-fluid">
		<div class="hidden-xs" style="margin-bottom:40px;">
			<%@include file="../../../reg_login_full.jsp" %>
		</div>
			<div class="row">
				<div class="col-md-12 col-xs-12" >
	            	<span style=" margin-top:10px; margin-bottom:10px; display:block;"><h3 id="title"><c:out value="${interactivePOJO.name}"></c:out></h3></span>
	            	<input type="hidden" id="interactiveId" name="interactiveId" value="<%=request.getParameter("interactiveId") %>"/>
				</div>
			</div>
            <div class="row">
				<div class="col-md-12">
	            	<h5 style=" margin-bottom:10px; display:block;">发布者:<span id="publisher">
	            		<c:if test="${not empty interactivePOJO.userPOJO}">
	            			<c:if test="${not empty interactivePOJO.userPOJO.nickname}">
	            				<c:out value="${interactivePOJO.userPOJO.nickname}"></c:out>
	            			</c:if>
	            			<c:if test="${empty interactivePOJO.userPOJO.nickname and not empty interactivePOJO.userPOJO}">
	            				<c:out value="${interactivePOJO.userPOJO.username}"></c:out>
	            			</c:if>
	            		</c:if>
	            	</span>
				</h5>
				</div>
	        </div>	
	  			<div class="row" style="margin-top: 20px; margin-bottom: 20px;">
	  				<div class="col-md-12 col-xs-12">
	  					<input type="hidden" id="interactiveId" name="interactiveId" value="${interactivePOJO.interactiveId}">
	  					<!-- <h4 class="" style="text-align: bottom;"><span id="title"></span></h4>
	  					<hr/> -->
			  			<p style="font-size:14px;"><span style="font-size:16px;font-family:'幼圆'; font-weight:bold;">活动内容：</span><span id="content"><c:out value="${interactivePOJO.content}"></c:out></span></p>
			  			<p style="font-size:14px; margin-top: 15px;"><span style="font-size:16px;font-family:'幼圆'; font-weight:bold;">活动规则：</span><span id="rule"><c:out value="${interactivePOJO.rule}"></c:out></span></p>
			  			<p style="font-size:14px; margin-top: 15px;"><span style="font-size:16px;font-family:'幼圆'; font-weight:bold;">活动时间：</span>
			  				<span id="startDateTime"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${interactivePOJO.startDateTime}" /></span>
                            
			  				~<span id="endDateTime"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${interactivePOJO.endDateTime}" /></span></p>
			  		</div>
                </div>    
                    	<!-- for update -->
			  			<!-- <hr/> -->
			  			<div class="row" style="margin-bottom:50px; padding:10px;">
                        <div class="col-md-12 col-xs-12" style="margin-top: 5px;">		
					    <span id="errorMsg" style="color: red;"></span>
					    <p class="h5"><c:if test="${not empty myAwardRecordPOJOs}">
					    	您的抽奖结果：
					    	<c:if test="${fn:length(myAwardRecordPOJOs) > 1}">
						    	<c:forEach items="${myAwardRecordPOJOs}" var="myAwardRecordPOJO" varStatus="st">
						    		<c:out value="${st.count}"></c:out>.
						    		<c:out value="${myAwardRecordPOJO.awardPOJO.name}"></c:out>
						    	</c:forEach>
					    	</c:if>
					    	<c:if test="${fn:length(myAwardRecordPOJOs) == 1}">
						    	<c:forEach items="${myAwardRecordPOJOs}" var="myAwardRecordPOJO" varStatus="st">
						    		<c:out value="${myAwardRecordPOJO.awardPOJO.name}"></c:out>
						    	</c:forEach>
					    	</c:if>
					    </c:if></p>
					</div>
                        <div class="col-md-12 col-xs-12 visible-xs" style=" border-bottom:2px solid #ccc; height:30px;"></div>
	  				<div id="interactiveApplySortor" class="col-md-12 col-xs-12" style="margin-top:10px;">
	  						<strong class="text-danger">获奖名单</strong>
	  						<!-- <div id="interactiveApplyContent"> -->
	  						<ul>
	  							<c:forEach items="${interactivePOJO.awardRecordPOJOs}" var="awardRecordPOJO" varStatus="st">
	  								<li><p class="h5">
	  									<c:out value="${st.count}"></c:out>.
	  									恭喜<span class="text-info h5"><c:out value="${awardRecordPOJO.userPOJO.nickname}"></c:out></span>
	  									获得奖品
	  									<span class="text-danger h5"><c:out value="${awardRecordPOJO.awardPOJO.name}"></c:out></span>
	  									<br/><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${awardRecordPOJO.hitDateTime}" />
	  								</p>
                                    </li>
	  							</c:forEach>
	  						</ul>
	  					</div>
                        </div>
					</div>
					<div id="award_tips">
					
					</div>
		<%@include file="../../../bottom.jsp" %>
		</div> <!-- container -->  		
	
  </body>
</html>
