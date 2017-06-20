<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../common/head.jsp"%>
<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/vote_item_by_vote_id_bs.js"></script>
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/activity_detail.js"></script>
</head>
<body>

    <input type="hidden" id="voteId" value="${votePOJO.voteId}">
	<div class="container-fluid">
		<!-- 隐藏 -->
		<div id="qrcodeDiv" class="col-md-12 col-xs-12"
			style="border: 1px solid #CCC; display: none; margin: auto;">
			<div class="thumbnail">
				<div class="caption">
					<h3>请通过长按识别二维码或微信扫描二维码关注公众号</h3>
					<p></p>
				</div>
				<img id="qrcodeImg" src="" alt="">
			</div>
		</div>
		<!-- 隐藏 -->
		<div class="row">
			<div class="col-xs-12"
				style="padding: 0px; text-align: center; margin-bottom: 15px;">
				<img src="http://www.deweiyizhan.com/files/images/22_-1_0_.jpg">
			</div>
		</div>

		<nav class="navbar navbar-default navbar-fixed-bottom">
			<!-- Nav tabs -->
			<ul class="nav nav-pills" role="tablist"
				style="text-align: center; padding-top: 5px">
				<div role="presentation" class="active col-xs-3">
					<a href="#home" style="color: #F00; font-size: 14px;"
						aria-controls="home" role="tab" data-toggle="tab"><span
						style="font-size: 24px;" class="glyphicon glyphicon-home"
						aria-hidden="true"></span>
						<p>首页</p></a>
				</div>
				<div role="presentation" class="col-xs-3">
					<a href="#profile" style="color: #F00; font-size: 14px;"
						aria-controls="profile" role="tab" data-toggle="tab"><span
						style="font-size: 24px;" class="glyphicon glyphicon-file"
						aria-hidden="true"></span>
						<p>规则</p></a>
				</div>
				<div role="presentation" class="col-xs-3">
					<a href="#messages" style="color: #F00; font-size: 14px;"
						aria-controls="messages" role="tab" data-toggle="tab"><span
						style="font-size: 24px;" class="glyphicon glyphicon-pencil"
						aria-hidden="true"></span>
						<p>报名</p></a>
				</div>
				<div role="presentation" class="col-xs-3">
					<a href="#search" style="color: #F00; font-size: 14px;"
						aria-controls="search" role="tab" data-toggle="tab"><span
						style="font-size: 24px;" class="glyphicon glyphicon-search"
						aria-hidden="true"></span>
						<p>查询</p></a>
				</div>
			</ul>
		</nav>



		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="home">
				<div class="row" style="margin-top: 15px;">
					<c:forEach items="${votePOJO.voteItemPOJOs}" var="voteItemPOJO" varStatus="st">
						<div class="media"
							style="border: #F00 solid 1px; margin: 0px 5px; padding: 10px;">
							<div class="media-left">
								<a href="#"> <img style="max-width: none !important;"
									class="media-object"
									src="${voteItemPOJO.wxPersonUserPOJO.headImgUrl}"
									alt="" height="64" width="64">
								</a>
							</div>
							<div class="media-body">
								<h5 class="media-heading">编号: <c:out value="${voteItemPOJO.voteItemId}"></c:out></h5>
								昵称:<c:out value="${voteItemPOJO.wxPersonUserPOJO.nickname}"></c:out>
								<c:forEach items="${voteItemPOJO.apply2POJO.apply2AttrPOJOs}" var="apply2AttrPOJO" varStatus="st2">
						          <li class="weui_media_info_meta" style="color:#000">
									<h5><c:out value="${apply2AttrPOJO.apply2AttrModelName}"></c:out>:<c:out value="${apply2AttrPOJO.apply2AttrData}"></c:out></h5>
						          </li>
			        			</c:forEach>
								<br />
								<%-- <c:out value="${voteItemPOJO.beenVoted}"></c:out> --%>
								<button type="button" class="btn btn-default btn-sm vote-click" style="text-align: right;" 
						          	
						          	 voteItemId="${voteItemPOJO.voteItemId}">
						          	 <c:if test="${voteItemPOJO.beenVoted == true}">
									  	<span class="glyphicon glyphicon-heart" style="color: red;" aria-hidden="true"></span>
									  </c:if>
									  <c:if test="${voteItemPOJO.beenVoted == false}">
									  	<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
									  </c:if>
									   <c:out value="${voteItemPOJO.totalNum}"></c:out>
								</button>
								<!-- <h5 style="text-align: right;">
									<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>&nbsp;9999
								</h5> -->
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div role="tabpanel" class="tab-pane" id="profile">
				活动: <c:out value="${votePOJO.title}"></c:out>
				规则: <p><c:out value="${votePOJO.content}"></c:out></p>
			</div>
			<div role="tabpanel" class="tab-pane" id="messages">
				<div class="row">
					<c:if test="${apply2POJO != null}">
						<c:forEach items="${apply2POJO.apply2AttrPOJOs}" var="apply2AttrPOJO" varStatus="st2">
				          <li class="weui_media_info_meta" style="color:#000">
							<h5><c:out value="${apply2AttrPOJO.apply2AttrModelName}"></c:out>:<c:out value="${apply2AttrPOJO.apply2AttrData}"></c:out></h5>
				          </li>
	        			</c:forEach>
	        			<c:if test="${apply2POJO.voteItemPOJO != null}">
	        				<h5 class="media-heading">编号: <c:out value="${apply2POJO.voteItemPOJO.voteItemId}"></c:out></h5>
	        				昵称:<c:out value="${voteItemPOJO.wxPersonUserPOJO.nickname}"></c:out>
							票数: <c:out value="${apply2POJO.voteItemPOJO.totalNum}"></c:out>
	        			</c:if>
				          
					</c:if>
				</div>
				
				<div class="row">
					<c:if test="${apply2POJO != null}">
						<c:forEach items="${apply2POJO.apply2AttrPOJOs}" var="apply2AttrPOJO" varStatus="st2">
				          <li class="weui_media_info_meta" style="color:#000">
							<h5><c:out value="${apply2AttrPOJO.apply2AttrModelName}"></c:out>:<c:out value="${apply2AttrPOJO.apply2AttrData}"></c:out></h5>
				          </li>
	        			</c:forEach>
	        			<c:if test="${apply2POJO.voteItemPOJO != null}">
	        				<h5 class="media-heading">编号: <c:out value="${apply2POJO.voteItemPOJO.voteItemId}"></c:out>:<c:out value="${apply2POJO.voteItemPOJO.wxPersonUserPOJO.nickname}"></c:out></h5>
							票数: <c:out value="${apply2POJO.voteItemPOJO.totalNum}"></c:out>
	        			</c:if>
				          
					</c:if>
				</div>
				<div class="row hidden-xs" style="height:150px;"></div>
					<!-- 隐藏 -->
		 			<div id="qrcodeDiv" class="col-md-12 col-xs-12" style="border:1px solid #CCC; display:none; margin: auto;">
		 				<div class="thumbnail">
					      <div class="caption">
					        <h3>请通过长按识别二维码或微信扫描二维码关注公众号</h3>
					        <p></p>
					      </div>
					      <img id="qrcodeImg" src="" alt="">
					    </div>
					</div>
				
				<div id="showDiv">
					<div class="row" style="display:none;">
					  <div class="col-md-12 col-xs-12" >
			            	<span style=" margin-top:20px; margin-bottom:20px; display:block;"><h3 id="title"></h3></span>                    
			            	<input type="hidden" id="activityId" name="activityId" value="<%=request.getParameter("activityId") %>"/>
			            	               
			            	<!-- <input type="hidden" id="authorizerAppId" name="authorizerAppId"/> -->
					  </div>
		            </div>
		            <!--<div class="row visible-xs">
		                <div class="col-xs-12">
		                   <img src="" alt="" id="logoImg">
		                </div>
		            </div>-->
					<div class="row" style="display:none;">
						<div class="col-md-12">
						
			            	<input type="hidden" id="startDateTime" name="startDateTime" />
			            	<input type="hidden" id="endDateTime" name="endDateTime" />    
						
			            	<h5 style="margin-bottom:20px; display:block;float: left;margin-right:10px; color:#898989;"><span class="h5" id="createDateTime"></span></h5>
		            	  	<h5 style=" margin-bottom:20px; display:block; float: left; color:#88a8c1;"><span class="h5" id="publisher"></span></h5>
			            	<!--<h5 style="margin-bottom:10px; display:block;">组织者:<span class="h5" id="organiser"></span></h5>-->
						</div>
			        </div>
					<div class="row" style="margin-top:10px; display:none;">
		  			  	<% 
		  			  		String hidContent = request.getParameter("hidContent");
		  			  		if (!"1".equalsIgnoreCase(hidContent)) {
		  			  	%>
			  				<div class="col-md-12 col-xs-12">
			  					<!-- <h4 class="" style="text-align: bottom;"><span id="title"></span></h4>
			  					<hr/> -->
					  			<p id="content"></p>
					  			<!-- for update -->
					  			<div style="" class="">
				 					<!-- <textarea rows="5" cols="20" id="content" name="content" placeholder="请输入本次活动内容"></textarea>
				 					<input class="form-control" type="text" id="content" name="content" placeholder="请输入本次活动内容"> -->
				 					<script id="editor" type="text/plain" name="content"></script>
				 				</div>
					  			<!-- <hr/> -->
			  				</div>
			  				<%
		  			  		}
			  				%>
		                    </div>
		                    <div class="row" style="margin-left:2px; margin-right:2px;">
		                    <div id="lineSplit" class="col-md-12 col-xs-12" style="margin-top:30px; border-bottom:5px ridge #ccc;"></div>
		                    <div id="errorMsg4Activity" class="col-md-12 col-xs-12" 
		                    	style="display: none; border:1px solid #CCC; padding-top:10px; margin-bottom:10px; font-size: xx-large; text-align: center;">
		                    	<span style="color: red; text-align: center;"></span>
		                    </div>
		                    
			  				<div id="apply2Div" class="col-md-12 col-xs-12" style="border:1px solid #CCC; padding-top:10px; margin-bottom:10px;">
				  				<div class="row col-md-12 col-xs-12">
					  				<fieldset>
											<form id="apply2Form" class="form-horizontal">
													<!-- <div class="form-group">
														<label for="usernameX" class="control-label h5">姓名: </label>
														<input id="usernameX" name="username" minlength="2" required="required" value="" placeholder="请填写姓名" class="form-control" />
													</div> -->
													<!-- <div class="form-group">
														<label for="phone" class="control-label h5">手机: </label>
														<input type="tel" id="phone" name="phone" minlength="3"  required="required" value="" placeholder="请填写手机号码" class="form-control" />
													</div>
													<div class="form-group">
														<label for="sex" class="control-label h5">性别: </label>
														<label class="radio-inline"><input type="radio" id="sexM" name="sex" value="M" class="" />男</label>
														<label class="radio-inline"><input type="radio" id="sexF" name="sex" value="F" class="" />女</label>
													</div>
													<div class="form-group">
														<label for="description" class="control-label h5">备注: </label> 
														<textarea id="description" name="description" rows="3" cols="18" value="" placeholder="备注为选填项" class="form-control"  style="margin-bottom:5px;"></textarea>
													</div> -->
													<div class="form-group">
		                                                <div class="col-md-12">
														<input type="button" id="apply2Btn" value="提交" class="btn btn-default" />
		                                                </div>
													</div>
											</form>
									</fieldset>	
			  					</div>
							</div>
							<!-- 隐藏 -->
			  				<div id="applyDiv" class="col-md-12 col-xs-12" style="border:1px solid #CCC; padding-top:10px; margin-bottom:10px; display:none;">
				  				<div class="row col-md-12 col-xs-12">
					  				<fieldset>
											<form id="applyForm" class="form-inline">
													<div class="form-group">
														<label for="usernameX" class="control-label h5">姓名: </label>
														<input id="usernameX" name="username" minlength="2" required="required" value="" placeholder="请填写姓名" class="form-control" />
													</div>
													<div class="form-group">
														<label for="phone" class="control-label h5">手机: </label>
														<input type="tel" id="phone" name="phone" minlength="3"  required="required" value="" placeholder="请填写手机号码" class="form-control" />
													</div>
													<div class="form-group">
														<label for="sex" class="control-label h5">性别: </label>
														<label class="radio-inline"><input type="radio" id="sexM" name="sex" value="M" class="" />男</label>
														<label class="radio-inline"><input type="radio" id="sexF" name="sex" value="F" class="" />女</label>
													</div>
													<div class="form-group">
														<label for="description" class="control-label h5">备注: </label> 
														<textarea id="description" name="description" rows="3" cols="18" value="" placeholder="备注为选填项" class="form-control"  style="margin-bottom:5px;"></textarea>
													</div>
													<div class="form-group">
														<input type="button" id="applyBtn" value="提交" class="btn btn-default" />
													</div>
											</form>
									</fieldset>	
			  					</div>
							</div>
			  			</div>
				</div><!-- for show -->
				
			</div>
			<div role="tabpanel" class="tab-pane" id="search">
				<div class="row">
					<form class="form-inline" id="voteSearchForm" action='<cmn:base/>/web/unified/vote/bs/query/${votePOJO.voteId}?activityId=${votePOJO.activityId}&activityTitle=${votePOJO.title}' method="post">
						<div class="form-group">
							<label class="sr-only" for="search">搜索</label> 
							<input style="width: auto; margin-left: 5px; float: left;" type="text"
								id="voteItemId" name="voteItemId"
								class="form-control" placeholder="请输入编号">
							<button style="margin-left: 5px; float: left;" type="submit"
								class="btn btn-default">搜索</button>
						</div>

					</form>
				</div>
			</div>
		</div>

		<div class="row" style="height: 70px;"></div>
	</div>

	<!-- container -->
</body>
</html>

