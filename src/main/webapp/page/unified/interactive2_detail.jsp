<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
  
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/interactive2_detail.js"></script>
    
    <link href="<cmn:base/>/css/lottery/lottery.css" rel="stylesheet">
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
            <div class="row hidden-xs">
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
	  			<div class="row hidden-xs" style="margin-top: 20px; margin-bottom: 20px;">
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
                            <div class="col-md-8 col-xs-12" style="border:#ffd3d4 solid 14px;">
			  				<div id="lottery" style="border:#FFF solid 12px;">
								<table border="0" cellpadding="0" cellspacing="0" style="border:#fe735e solid 10px;">
									<tr>
										<td class="lottery-unit lottery-unit-0 lottery-orderno-2"><img src="<cmn:base/>/images/lottery/3.png"></td>
										<td class="lottery-unit lottery-unit-1 lottery-orderno-0"><img src="<cmn:base/>/images/lottery/1.png"></td>
										<td class="lottery-unit lottery-unit-2 lottery-orderno-2"><img src="<cmn:base/>/images/lottery/3.png"></td>
									</tr>
									<tr>
										<td class="lottery-unit lottery-unit-7 lottery-orderno-1"><img src="<cmn:base/>/images/lottery/2.png"></td>
										<td class="kscj" colspan="1" rowspan="1"><a href=""><img src="<cmn:base/>/images/lottery/cjstart.jpg"></a></td>
										<td class="lottery-unit lottery-unit-3 lottery-orderno-1"><img src="<cmn:base/>/images/lottery/2.png"></td>
									</tr>
							        <tr>
										<td class="lottery-unit lottery-unit-6 lottery-orderno-2"><img src="<cmn:base/>/images/lottery/3.png"></td>
										<td class="lottery-unit lottery-unit-5 lottery-orderno-1"><img src="<cmn:base/>/images/lottery/2.png"></td>
										<td class="lottery-unit lottery-unit-4 lottery-orderno-2"><img src="<cmn:base/>/images/lottery/3.png"></td>
									</tr>
								</table>
							</div>
			  			</div>
                        <div class="col-md-4 col-xs-12">
                        <div class="col-md-12 col-xs-12" style="margin-top: 5px;">		
					    <span id="errorMsg" style="color: red;"></span>
					    <p class="h5"><c:if test="${not empty myAwardRecordPOJOs}">
					    	您的抽奖结果：
					    	<c:forEach items="${myAwardRecordPOJOs}" var="myAwardRecordPOJO" varStatus="st">
					    		<c:out value="${st.count}"></c:out>.
					    		<c:out value="${myAwardRecordPOJO.awardPOJO.name}"></c:out>
					    	</c:forEach>
					    </c:if></p>
					</div>
                        <div class="col-md-4 col-xs-12 visible-xs" style=" border-bottom:2px solid #ccc; height:30px;"></div>
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
	
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/lottery/lottery.js"></script>
  </body>
</html>
