<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <c:if test="${not empty documentTitle}">
    	<title>${documentTitle}</title>
    </c:if>
    <c:if test="${empty documentTitle}">
    	<title>得味驿站</title>
    </c:if>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<cmn:base/>/jquery/jquery-1.11.1.min.js"></script>
    <script src="<cmn:base/>/jquery/jquery-migrate-1.2.1.min.js"></script>
    <!-- jQuery Validation -->
    <script src="<cmn:base/>/js/thirdpart/jquery-validation-1.13.1/jquery.validate.min.js"></script>
    <script src="<cmn:base/>/js/thirdpart/jquery-validation-1.13.1/additional-methods.min.js"></script>
    <script src="<cmn:base/>/js/thirdpart/jquery-validation-1.13.1/jquery.validate.message.zh_cn.js"></script>
    
    
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>
    
    <style>
		form label.error {
			color: #f00; font-size:12px;
		}
	</style>
    <!-- jQuery UI -->
    <%-- <script src="<cmn:base/>/js/thirdpart/jquery-ui-1.11.4/jquery-ui.js"></script>
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-ui-1.11.4/jquery-ui.min.css">
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-ui-1.11.4/jquery-ui.structure.min.css">
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-ui-1.11.4/jquery-ui.theme.min.css"> --%>
	<!-- jQuery datetime -->
    <%-- <script src="<cmn:base/>/js/thirdpart/jquery-datetimepicker-2.4.3/jquery.datetimepicker.js"></script>
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/jquery-datetimepicker-2.4.3/jquery.datetimepicker.css"> --%>
	
    
    <!-- Bootstrap -->
    <link href="<cmn:base/>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<cmn:base/>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
	<!-- UEditor -->    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/ueditor/lang/zh-cn/zh-cn.js"></script>
    
<%-- <script src="<cmn:base/>/js/jquery-1.2.4b.js" type="text/javascript"></script> --%>
	<!-- conflict with jQuery-ui -->
    <!--<script src="<cmn:base/>/js/ui.core.js" type="text/javascript"></script>
	<script src="<cmn:base/>/js/ui.tabs.js" type="text/javascript"></script>-->
	<!-- <script type="text/javascript" src="http://api.go2map.com/maps/js/api_v2.5.1.js"></script> -->
	<!-- <script src="js/jquery-1.2.4b.js" type="text/javascript"></script> -->
	
	
	<!-- <script src="js/ui.core.js" type="text/javascript"></script>
	<script src="js/ui.tabs.js" type="text/javascript"></script> -->

	<!-- DataTables -->
    <%-- <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/media/js/jquery.dataTables.min.js"></script>
    <link href="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/media/css/jquery.dataTables.min.css" rel="stylesheet"> --%>
    <!-- DataTables integration Bootstrap -->
    <%-- <script src="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/integration/bootstrap/3/dataTables.bootstrap.js"></script>
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/DataTables-1.10.2-trial/integration/bootstrap/3/dataTables.bootstrap.css"> --%>
	<!-- Date.format('Y-m-d H:i:s'); -->
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/date.format.js"></script>
	
	<%-- <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/jquery-zeroclipboard/jquery.zeroclipboard.js"></script> --%>
	
	<%-- <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/jquery-clipboard/jquery.clipboard.js"></script> --%>
	
	<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/clipboard/clipboard.min.js"></script>
	
	<%-- <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/thirdpart/zeroclipboard/ZeroClipboard.min.js"></script> --%>
	
	<!-- JQuery-weui -->
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/weui/weui.css">
	<link rel="stylesheet" href="<cmn:base/>/js/thirdpart/weui/jquery-weui.css">
    <script src="<cmn:base/>/js/thirdpart/weui/jquery-weui.js"></script>
    <%-- <script src="<cmn:base/>/js/thirdpart/weui/city-picker.js"></script>
    <script src="<cmn:base/>/js/thirdpart/weui/swiper.js"></script>
    <script src="<cmn:base/>/js/thirdpart/weui/zepto.js"></script> --%>
    
    <!-- Mustache -->
    <script src="<cmn:base/>/js/thirdpart/mustache/mustache.js"></script>
	
	
	
    
    <!-- customer -->
    <!-- 公共的函数 -->
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/common.js"></script>
    <link href="<cmn:base/>/css/common.css" rel="stylesheet">
	<link href="<cmn:base/>/css/dwsy.css" rel="stylesheet" media="print, projection, screen">
    <!-- <link href="css/dwsy.css" rel="stylesheet" media="print, projection, screen"> -->
    <!--<link href="<cmn:base/>/css/wm.css" rel="stylesheet">-->
    
	<input id="basePath" type="hidden" value='<cmn:base/>'>
	<c:set var="basePath"><cmn:base/></c:set>
	<input id="userId" type="hidden" value='${sessionScope.myUser.userId}'>
	<input id="username" type="hidden" value='${sessionScope.myUser.username}'>
	
	<input id="unionId" type="hidden" value='${sessionScope.unionId}'>
	<input id="openId" type="hidden" value='${sessionScope.openId}'>
	<input id="authorizerAppId" type="hidden" value='${sessionScope.authorizerAppId}'>
	<input id="indexCode" type="hidden" value='${sessionScope.indexCode}'>
	
<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/vote_item_by_vote_id_bs_loadmore.js"></script>
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/activity_detail.js"></script>
    
    <style type="text/css">
    	.t-focus {
    		color: #ff0000 !important;
    	}
    </style>
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

<script id="homeTmpl" type="x-tmpl-mustache">
				<div class="row" style="margin-top: 15px;">
					{{#data}}
						<div class="media"
							style="border: #F00 solid 1px; margin: 0px 5px; padding: 10px;">
							<div class="media-left">
								<a href="#"> <img style="max-width: none !important;"
									class="media-object"
									src="{{wxPersonUserPOJO.headImgUrl}}"
									alt="" height="64" width="64">
								</a>
							</div>
							<div class="media-body">
								<h5 class="media-heading">编号: {{voteItemId}}</h5>
								昵称:{{wxPersonUserPOJO.nickname}}
								{{#apply2POJO.apply2AttrPOJOs}}
						          <li class="weui_media_info_meta" style="color:#000">
									<h5>{{apply2AttrModelName}}:{{apply2AttrData}}</h5>
						          </li>
			        			{{/apply2POJO.apply2AttrPOJOs}}
								<br />
								<%-- <c:out value="${voteItemPOJO.beenVoted}"></c:out> --%>
								<button type="button" class="btn btn-default btn-sm vote-click" style="text-align: right;" 
						          	
						          	 voteItemId="{{voteItemId}}">
						          	  {{#beenVoted}}
									  	<span class="glyphicon glyphicon-heart" style="color: red;" aria-hidden="true"></span>
									  {{/beenVoted}}
									  {{^beenVoted}}
									  	<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
									  {{/beenVoted}}
									   <c:out value="{{totalNum}}"></c:out>
								</button>
								<!-- <h5 style="text-align: right;">
									<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>&nbsp;9999
								</h5> -->
							</div>
						</div>
					{{/data}}
					{{^data}}
						<span>没有数据</span>
					{{/data}}
				</div>
</script>
<script id="paginationTmpl" type="x-tmpl-mustache">
<input type="hidden" id="start" name="start" value="{{start}}" />
<input type="hidden" id="limit" name="limit" value="{{limit}}" />
<input type="hidden" id="paginationFlage" name="paginationFlage" value="{{paginationFlage}}" />
</script>
		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="home">
				<!-- <button class="btn btn-default" id="sortDate">时间排序</button> -->
				<button class="btn btn-default" id="sortOrderNo">编号排序</button>
				<button class="btn btn-default" id="sortNum">票数排序</button>
				<div class="row" id="pagination">
				</div>
				<div class="row" id="homeContent">
				</div>
				<div class="row" id="noData" style="display:none;">
					<span>没有更多的数据了!</span>
				</div>
				
				<div class="weui-infinite-scroll" id="homeInfinite">
				  <div class="infinite-preloader"></div><!-- 菊花 -->
				  正在加载... <!-- 文案，可以自行修改 -->
				</div>
			</div>
			<div role="tabpanel" class="tab-pane" id="profile">
				活动: <c:out value="${votePOJO.title}"></c:out>
				规则: <p><c:out value="${votePOJO.content}"></c:out></p>
			</div>
			<div role="tabpanel" class="tab-pane" id="messages">
				<%-- <div class="row">
					<c:if test="${apply2POJO != null}">
						<c:forEach items="${apply2POJO.apply2AttrPOJOs}" var="apply2AttrPOJO" varStatus="st2">
				          <li class="weui_media_info_meta" style="color:#000">
							<h5><c:out value="${apply2AttrPOJO.apply2AttrModelName}"></c:out>:<c:out value="${apply2AttrPOJO.apply2AttrData}"></c:out></h5>
				          </li>
	        			</c:forEach>
	        			<c:if test="${apply2POJO.voteItemPOJO != null}">
	        				<h5 class="media-heading">编号: <c:out value="${apply2POJO.voteItemPOJO.voteItemId}"></c:out></h5>
	        				昵称:<c:out value="${apply2POJO.voteItemPOJO.wxPersonUserPOJO.nickname}"></c:out>
							票数: <c:out value="${apply2POJO.voteItemPOJO.totalNum}"></c:out>
	        			</c:if>
				          
					</c:if>
				</div> --%>
				
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
		                    <c:if test="${apply2POJO != null and voteItemPOJO == null}">
		                    	待审批
		                    </c:if>
		                    
		                    <c:if test="${apply2POJO != null and voteItemPOJO != null}">
		                    	已审批
		                    	<div class="row">
									<c:if test="${apply2POJO != null}">
										<c:forEach items="${apply2POJO.apply2AttrPOJOs}" var="apply2AttrPOJO" varStatus="st2">
								          <li class="weui_media_info_meta" style="color:#000">
											<h5><c:out value="${apply2AttrPOJO.apply2AttrModelName}"></c:out>:<c:out value="${apply2AttrPOJO.apply2AttrData}"></c:out></h5>
								          </li>
					        			</c:forEach>
					        			<c:if test="${voteItemPOJO != null}">
					        				<h5 class="media-heading">编号: <c:out value="${voteItemPOJO.voteItemId}"></c:out></h5>
					        				昵称:<c:out value="${voteItemPOJO.wxPersonUserPOJO.nickname}"></c:out>
											票数: <c:out value="${voteItemPOJO.totalNum}"></c:out>
					        			</c:if>
								          
									</c:if>
								</div>
								
		                    </c:if>
		                    <c:if test="${apply2POJO == null}">
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
							</c:if>
			  			</div>
				</div><!-- for show -->
				
			</div>
			<div role="tabpanel" class="tab-pane" id="search">
				<div class="row">
					<form class="form-inline" id="voteSearchForm" action='<cmn:base/>/web/unified/vote/loadmore/query/${votePOJO.voteId}?activityId=${votePOJO.activityId}&activityTitle=${votePOJO.title}' method="post">
						<div class="form-group">
							<label class="sr-only" for="search">搜索</label> 
							<input style="width: auto; margin-left: 5px; float: left;" type="text"
								id="voteItemId" name="voteItemId" value=""
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

