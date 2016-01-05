<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
  
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/wx_link_index.js"></script>
    
    <link href="<cmn:base/>/css/dwuc.css" rel="stylesheet">
  </head>
  <body style="padding:0px; background-color:#f1f1f1;">
  
	<div class="container">
		<%-- <%@include file="../../../reg_login_full.jsp" %> --%>
		<!-- <div id="showDiv"> -->
			<div class="row">
				<div id="sec1" class="col-md-12 col-xs-12" style="padding:0px; text-align:center;">
					<c:if test="${not empty wxLinkPOJO0}">
	            		<a href="${wxLinkPOJO0.linkUrl}"><img alt="${wxLinkPOJO0.title}" src="../../files/${wxLinkPOJO0.imgSrc}"></a>
					</c:if>
				</div>
			</div>
			<div class="row" style="background-color:#ffffff; border-left:#f1f1f1 4px solid;border-right:#f1f1f1 4px solid; border-top:#f1f1f1 2px solid; border-bottom:#f1f1f1 2px solid;">
				<div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#ffffff 4px solid;">
					 <div id="sec2" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO1}">
		            		<a href="${wxLinkPOJO1.linkUrl}"><img alt="${wxLinkPOJO1.title}" src="../../files/${wxLinkPOJO1.imgSrc}"></a>
						</c:if>
					 </div>
					 <div class="title col-md-12 col-xs-12">
	            		<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">节目直播/回听</p>
					 </div>
                   </div>
				</div>
                <div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div id="sec3" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO2}">
		            		<a href="${wxLinkPOJO2.linkUrl}"><img alt="${wxLinkPOJO2.title}" src="../../files/${wxLinkPOJO2.imgSrc}"></a>
						</c:if>
					 </div>
					 <div class="col-md-12 col-xs-12">
	            		<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">微社区（论坛）</p>
					 </div>
                   </div> 
                </div>
				<div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div id="sec4" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO3}">
		            		<a href="${wxLinkPOJO3.linkUrl}"><img alt="${wxLinkPOJO3.title}" src="../../files/${wxLinkPOJO3.imgSrc}"></a>
						</c:if>
					 </div>
					 <div class="col-md-12 col-xs-12">
	            		<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">节目介绍</p>
					 </div>
                   </div>
				</div>
            </div>
            <div class="row" style="background-color:#ffffff; border-left:#f1f1f1 4px solid;border-right:#f1f1f1 4px solid; border-top:#f1f1f1 2px solid; border-bottom:#f1f1f1 2px solid;">
				<div class="col-md-4 col-xs-4">
                   <div class="row" style="border-left:#ffffff 4px solid;">
					 <div id="sec5" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO1}">
		            		<a href="${wxLinkPOJO4.linkUrl}"><img alt="${wxLinkPOJO4.title}" src="../../files/${wxLinkPOJO4.imgSrc}"></a>
						</c:if>
					 </div>
					 <div class="col-md-12 col-xs-12">
	            		<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">往期精彩</p>
					 </div>
                   </div>
                </div>
				<div class="col-md-8 col-xs-8">
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div class="col-md-4 col-xs-4">
	            		<p style="text-align:center; margin-top:30px; font-size:12px; line-height:12px; min-height:25px;">近期重要活动</p>
					 </div>
                     <div class="col-md-8 col-xs-8">
                     <div class="row">
					    <div id="sec6" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO1}">
		            		<a href="${wxLinkPOJO5.linkUrl}"><img alt="${wxLinkPOJO5.title}" src="../../files/${wxLinkPOJO5.imgSrc}"></a>
						</c:if>
					    </div>
                     </div>
                     </div>
                   </div>
                </div>
			</div>

			<div class="row" style="background-color:#ffffff; border-left:#f1f1f1 4px solid;border-right:#f1f1f1 4px solid; border-top:#f1f1f1 2px solid; border-bottom:#f1f1f1 2px solid;">
				<div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#ffffff 4px solid;">
					 <div id="sec7" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO6}">
		            		<a href="${wxLinkPOJO6.linkUrl}"><img alt="${wxLinkPOJO6.title}" src="../../files/${wxLinkPOJO6.imgSrc}"></a>
						</c:if>
					 </div>
					 <div class="col-md-12 col-xs-12">
	            		<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">测试 测试</p>
					 </div>
                   </div>
				</div>
				<div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div id="sec8" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO7}">
		            		<a href="${wxLinkPOJO7.linkUrl}"><img alt="${wxLinkPOJO7.title}" src="../../files/${wxLinkPOJO7.imgSrc}"></a>
						</c:if>
					 </div>
					 <div class="col-md-12 col-xs-12">
	            		<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">测试 测试</p>
					 </div>
                   </div>
				</div>
				<div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div id="sec9" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO8}">
		            		<a href="${wxLinkPOJO8.linkUrl}"><img alt="${wxLinkPOJO8.title}" src="../../files/${wxLinkPOJO8.imgSrc}"></a>
						</c:if>
					 </div>
					 <div class="col-md-12 col-xs-12">
	            		<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;">测试 测试</p>
					 </div>
                   </div>
				</div>
			</div>
            <div class="row" style="background-color:#f1f1f1;">
                <p style="text-align:center; margin-bottom:10px; margin-top:20px; font-size:12px; line-height:12px;">2015 合肥交通广播</p>
            </div>
            <div class="row" style="background-color:#333;">
                <p style="text-align:center; margin-bottom:5px; margin-top:5px; font-size:12px; line-height:12px; color:#ffffff;">技术支持:得味驿站</p>
            </div>
		<!-- </div> --><!-- for show -->
	  		
	</div> <!-- container -->
  </body>
</html>

