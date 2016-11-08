<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/interactive2_detail.js"></script>
    
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
    
    <link href="<cmn:base/>/css/lottery/lottery.css" rel="stylesheet">
  </head>
  <body>
  
	<div class="container">
		<%@include file="../../../reg_login_full.jsp" %>
		<div id="showDiv">
			<div class="row">
				<div class="col-md-12" >
	            	<span style=" margin-top:50px; margin-bottom:50px; display:block;"><h3 id="title"></h3></span>
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
	  		<div class="row uc-border">
	  			<div class="row" style="margin-top: 20px; margin-left: 20px; margin-bottom: 20px;">
	  				<div class="col-md-9 col-xs-12" style=" border-right:1px solid #CCC; ">
	  					<input type="hidden" id="interactiveId" name="interactiveId" value="${interactivePOJO.interactiveId}">
	  					<!-- <h4 class="" style="text-align: bottom;"><span id="title"></span></h4>
	  					<hr/> -->
			  			<p style="font-size:14px;"><span style="font-size:16px;font-family:'幼圆'; font-weight:bold;">活动内容：</span><span id="content"><c:out value="${interactivePOJO.content}"></c:out></span></p>
			  			<p style="font-size:14px; margin-top: 15px;"><span style="font-size:16px;font-family:'幼圆'; font-weight:bold;">活动规则：</span><span id="rule"><c:out value="${interactivePOJO.rule}"></c:out></span></p>
			  			<p style="font-size:14px; margin-top: 15px;"><span style="font-size:16px;font-family:'幼圆'; font-weight:bold;">活动时间：</span>
			  				<span id="startDateTime"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${interactivePOJO.startDateTime}" /></span>
			  				~<span id="endDateTime"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${interactivePOJO.endDateTime}" /></span></p>
			  				
			  			<p style="font-size:14px; margin-top: 15px; display: none;"><span style="font-size:16px;font-family:'幼圆'; font-weight:bold;">获奖人数：</span>排名前<span id="numOfWinner"><c:out value="${interactivePOJO.numOfWinner}"></c:out></span>名获得奖励。</p>
			  			<!-- for update -->
			  			<%-- <div style="" class="row col-md-12">
		 					<script id="editor" type="text/plain" name="${interactivePOJO.content}"></script>
		 				</div> --%>
			  			<!-- <hr/> -->
			  			<div class="row">
			  				<%-- <div id="lottery" class="col-md-12 col-xs-12 col-sm-12">
			  					<div class="row">
			  						<div class="col-md-3 col-xs-3 col-sm-3 lottery-unit lottery-unit-0">
			  							<img src="<cmn:base/>/images/lottery/1.png">
			  						</div>
			  						<div class="col-md-3 col-xs-3 col-sm-3 lottery-unit lottery-unit-1">
			  							<img src="<cmn:base/>/images/lottery/2.png">
			  						</div>
			  						<div class="col-md-3 col-xs-3 col-sm-3 lottery-unit lottery-unit-2">
			  							<img src="<cmn:base/>/images/lottery/4.png">
			  						</div>
			  						<div class="col-md-3 col-xs-3 col-sm-3 lottery-unit lottery-unit-3">
			  							<img src="<cmn:base/>/images/lottery/3.png">
			  						</div>
			  					</div>
			  					<div class="row">
			  						<div class="col-md-3 col-xs-3 col-sm-3">
			  							<div class="row">
			  								<div class="col-md-12 lottery-unit lottery-unit-11">
			  								 <img src="<cmn:base/>/images/lottery/7.png">
			  								 </div>
			  							</div>
			  							<div class="row">
			  								<div class="col-md-12 lottery-unit lottery-unit-4">
			  								 <img src="<cmn:base/>/images/lottery/5.png">
			  								 </div>
			  							</div>
			  						</div>
			  						<div class="col-md-6 col-xs-6 col-sm-6">
			  							<div class="row">
			  								<div class="col-md-12">
			  								 	<a href="#">choujiang</a>
			  								 </div>
			  							</div>
			  						</div>
			  						<div class="col-md-3 col-xs-3 col-sm-3">
			  							<div class="row">
			  								<div class="col-md-12 lottery-unit lottery-unit-10">
			  								 <img src="<cmn:base/>/images/lottery/1.png">
			  								 </div>
			  							</div>
			  							<div class="row">
			  								<div class="col-md-12 lottery-unit lottery-unit-5">
			  								 <img src="<cmn:base/>/images/lottery/6.png">
			  								 </div>
			  							</div>
			  						</div>
			  						
			  					</div>
			  					<div class="row">
			  						<div class="col-md-3 col-xs-3 col-sm-3 lottery-unit lottery-unit-9">
			  							<img src="<cmn:base/>/images/lottery/3.png">
			  						</div>
			  						<div class="col-md-3 col-xs-3 col-sm-3 lottery-unit lottery-unit-8">
			  							<img src="<cmn:base/>/images/lottery/6.png">
			  						</div>
			  						<div class="col-md-3 col-xs-3 col-sm-3 lottery-unit lottery-unit-7">
			  							<img src="<cmn:base/>/images/lottery/8.png">
			  						</div>
			  						<div class="col-md-3 col-xs-3 col-sm-3 lottery-unit lottery-unit-6">
			  							<img src="<cmn:base/>/images/lottery/7.png">
			  						</div>
			  					</div>
			  				</div> --%>
			  				<div id="lottery">
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="lottery-unit lottery-unit-0"><img src="<cmn:base/>/images/lottery/1.png"></td>
										<td class="lottery-unit lottery-unit-1"><img src="<cmn:base/>/images/lottery/2.png"></td>
										<td class="lottery-unit lottery-unit-2"><img src="<cmn:base/>/images/lottery/4.png"></td>
							            <td class="lottery-unit lottery-unit-3"><img src="<cmn:base/>/images/lottery/3.png"></td>
									</tr>
									<tr>
										<td class="lottery-unit lottery-unit-11"><img src="<cmn:base/>/images/lottery/7.png"></td>
										<td style="background-color:#cc0" colspan="2" rowspan="2"><a href="#">抽奖</a></td>
										<td class="lottery-unit lottery-unit-4"><img src="<cmn:base/>/images/lottery/5.png"></td>
									</tr>
									<tr>
										<td class="lottery-unit lottery-unit-10"><img src="<cmn:base/>/images/lottery/1.png"></td>
										<td class="lottery-unit lottery-unit-5"><img src="<cmn:base/>/images/lottery/6.png"></td>
									</tr>
							        <tr>
										<td class="lottery-unit lottery-unit-9"><img src="<cmn:base/>/images/lottery/3.png"></td>
										<td class="lottery-unit lottery-unit-8"><img src="<cmn:base/>/images/lottery/6.png"></td>
										<td class="lottery-unit lottery-unit-7"><img src="<cmn:base/>/images/lottery/8.png"></td>
							            <td class="lottery-unit lottery-unit-6"><img src="<cmn:base/>/images/lottery/7.png"></td>
									</tr>
								</table>
							</div>
			  			</div>
			  			
							<div class="row col-md-12" style="margin-top: 50px;">
							
									<span id="errorMsg" style="color: red;">badd</span>
						</div>
	  				</div>
	  				<div class="col-md-3 col-xs-12">
	  					<div id="interactiveApplySortor" class="row col-md-12" style="">
	  						<h4>获奖名单</h4>
	  						<!-- <div id="interactiveApplyContent"> -->
	  						<ul>
	  							<c:forEach items="${interactivePOJO.awardRecordPOJOs}" var="awardRecordPOJO" varStatus="st">
	  								<li>
	  									<c:out value="${st.count}"></c:out>:
	  									<c:out value="${awardRecordPOJO.userPOJO.nickname}"></c:out>
	  									/
	  									<c:out value="${awardRecordPOJO.awardPOJO.name}"></c:out>
	  									/
	  									<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${awardRecordPOJO.hitDateTime}" />
	  								</li>
	  							</c:forEach>
	  						</ul>
	  						</div>
	  					</div>
					</div>
	  			</div>
	  		</div><!-- for show -->
		</div> <!-- container -->
	<%@include file="../../../bottom.jsp" %>  		
	
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/lottery/lottery.js"></script>
  </body>
</html>

