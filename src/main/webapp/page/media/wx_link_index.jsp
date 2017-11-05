<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
  <head>
  
    <%@include file="../common/head.jsp" %>
    
    <script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/media/wx_link_index.js"></script>
    
  </head>
  <body style="padding:0px; background-color:#f1f1f1;">
  
	<div class="container">
		<%-- <%@include file="../../../reg_login_full.jsp" %> --%>
		<!-- <div id="showDiv"> -->
			<div class="row">
				<div id="sec1" class="col-md-12 col-xs-12" style="padding:0px; text-align:center;">
					<c:if test="${not empty wxLinkPOJO0}">
						<c:if test="${empty wxLinkPOJO0.linkUrl and not empty wxLinkPOJO0.imgSrc}">
							<img alt="${wxLinkPOJO0.title}" src="${wxLinkPOJO0.imgSrc}">
						</c:if>
						<c:if test="${not empty wxLinkPOJO0.linkUrl and not empty wxLinkPOJO0.imgSrc}">
							<a href="${wxLinkPOJO0.linkUrl}"><img alt="${wxLinkPOJO0.title}" src="${wxLinkPOJO0.imgSrc}"></a>
						</c:if>
					</c:if>
				</div>
			</div>
			<div class="row" style="background-color:#ffffff; border-left:#f1f1f1 4px solid;border-right:#f1f1f1 4px solid; border-top:#f1f1f1 2px solid; border-bottom:#f1f1f1 2px solid;">
				<div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#ffffff 4px solid;">
					 <div id="sec2" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO1}">
	            			<c:if test="${empty wxLinkPOJO1.linkUrl and not empty wxLinkPOJO1.imgSrc}">
								<img alt="${wxLinkPOJO1.title}" src="${wxLinkPOJO1.imgSrc}">
							</c:if>
							<c:if test="${not empty wxLinkPOJO1.linkUrl and not empty wxLinkPOJO1.imgSrc}">
								<a href="${wxLinkPOJO1.linkUrl}"><img alt="${wxLinkPOJO1.title}" src="${wxLinkPOJO1.imgSrc}"></a>
							</c:if>
						</c:if>
					 </div>
					 <div class="title col-md-12 col-xs-12">
	            		<c:if test="${not empty wxLinkPOJO1}">
	            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${wxLinkPOJO1.title}"></c:out> </p>
	            		</c:if>
					 </div>
                   </div>
				</div>
                <div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div id="sec3" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO2}">
	            			<c:if test="${empty wxLinkPOJO2.linkUrl and not empty wxLinkPOJO2.imgSrc}">
								<img alt="${wxLinkPOJO2.title}" src="${wxLinkPOJO2.imgSrc}">
							</c:if>
							<c:if test="${not empty wxLinkPOJO2.linkUrl and not empty wxLinkPOJO2.imgSrc}">
								<a href="${wxLinkPOJO2.linkUrl}"><img alt="${wxLinkPOJO2.title}" src="${wxLinkPOJO2.imgSrc}"></a>
							</c:if>
		            		
						</c:if>
					 </div>
					 <div class="col-md-12 col-xs-12">
	            		<c:if test="${not empty wxLinkPOJO2}">
	            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${wxLinkPOJO2.title}"></c:out></p>
	            		</c:if>
					 </div>
                   </div> 
                </div>
				<div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div id="sec4" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO3}">
	            			<c:if test="${empty wxLinkPOJO3.linkUrl and not empty wxLinkPOJO3.imgSrc}">
								<img alt="${wxLinkPOJO3.title}" src="${wxLinkPOJO3.imgSrc}">
							</c:if>
							<c:if test="${not empty wxLinkPOJO3.linkUrl and not empty wxLinkPOJO3.imgSrc}">
								<a href="${wxLinkPOJO3.linkUrl}"><img alt="${wxLinkPOJO3.title}" src="${wxLinkPOJO3.imgSrc}"></a>
							</c:if>
		            		
						</c:if>
					 </div>
					 <div class="col-md-12 col-xs-12">
	            		<c:if test="${not empty wxLinkPOJO3}">
	            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${wxLinkPOJO3.title}"></c:out></p>
	            		</c:if>
					 </div>
                   </div>
				</div>
            </div>
            <div class="row" style="background-color:#ffffff; border-left:#f1f1f1 4px solid;border-right:#f1f1f1 4px solid; border-top:#f1f1f1 2px solid; border-bottom:#f1f1f1 2px solid;">
				<div class="col-md-4 col-xs-4">
                   <div class="row" style="border-left:#ffffff 4px solid;">
					 <div id="sec5" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO4}">
	            			<c:if test="${empty wxLinkPOJO4.linkUrl and not empty wxLinkPOJO4.imgSrc}">
								<img alt="${wxLinkPOJO4.title}" src="${wxLinkPOJO4.imgSrc}">
							</c:if>
							<c:if test="${not empty wxLinkPOJO4.linkUrl and not empty wxLinkPOJO4.imgSrc}">
								<a href="${wxLinkPOJO4.linkUrl}"><img alt="${wxLinkPOJO4.title}" src="${wxLinkPOJO4.imgSrc}"></a>
							</c:if>
		            		
						</c:if>
					 </div>
					 <div class="col-md-12 col-xs-12">
	            		<c:if test="${not empty wxLinkPOJO4}">
	            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${wxLinkPOJO4.title}"></c:out></p>
	            		</c:if>
					 </div>
                   </div>
                </div>
				<div class="col-md-8 col-xs-8">
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div class="col-md-4 col-xs-4">
					 	<c:if test="${not empty wxLinkPOJO5}">
	            			<p style="text-align:center; margin-top:30px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${wxLinkPOJO5.title}"></c:out></p>
					 	</c:if>
					 </div>
                     <div class="col-md-8 col-xs-8">
                     <div class="row">
					    <div id="sec6" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO5}">
	            			<c:if test="${empty wxLinkPOJO5.linkUrl and not empty wxLinkPOJO5.imgSrc}">
								<img alt="${wxLinkPOJO5.title}" src="${wxLinkPOJO5.imgSrc}">
							</c:if>
							<c:if test="${not empty wxLinkPOJO5.linkUrl and not empty wxLinkPOJO5.imgSrc}">
								<a href="${wxLinkPOJO5.linkUrl}"><img alt="${wxLinkPOJO5.title}" src="${wxLinkPOJO5.imgSrc}"></a>
							</c:if>
		            		
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
	            			<c:if test="${empty wxLinkPOJO6.linkUrl and not empty wxLinkPOJO6.imgSrc}">
								<img alt="${wxLinkPOJO6.title}" src="${wxLinkPOJO6.imgSrc}">
							</c:if>
							<c:if test="${not empty wxLinkPOJO6.linkUrl and not empty wxLinkPOJO6.imgSrc}">
								<a href="${wxLinkPOJO6.linkUrl}"><img alt="${wxLinkPOJO6.title}" src="${wxLinkPOJO6.imgSrc}"></a>
							</c:if>
		            		
						</c:if>
					 </div>
					 <div class="col-md-12 col-xs-12">
					 	<c:if test="${not empty wxLinkPOJO6}">
	            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${wxLinkPOJO6.title}"></c:out></p>
					 	</c:if>
					 </div>
                   </div>
				</div>
				<div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div id="sec8" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO7}">
	            			<c:if test="${empty wxLinkPOJO7.linkUrl and not empty wxLinkPOJO7.imgSrc}">
								<img alt="${wxLinkPOJO7.title}" src="${wxLinkPOJO7.imgSrc}">
							</c:if>
							<c:if test="${not empty wxLinkPOJO6.linkUrl and not empty wxLinkPOJO6.imgSrc}">
								<a href="${wxLinkPOJO7.linkUrl}"><img alt="${wxLinkPOJO7.title}" src="${wxLinkPOJO7.imgSrc}"></a>
							</c:if>
		            		
						</c:if>
					 </div>
					 <div class="col-md-12 col-xs-12">
						 <c:if test="${not empty wxLinkPOJO7}">
						 	<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${wxLinkPOJO7.title}"></c:out></p>
						 </c:if>
					 </div>
                   </div>
				</div>
				<div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div id="sec9" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxLinkPOJO8}">
	            			<c:if test="${empty wxLinkPOJO8.linkUrl and not empty wxLinkPOJO8.imgSrc}">
								<img alt="${wxLinkPOJO8.title}" src="${wxLinkPOJO8.imgSrc}">
							</c:if>
							<c:if test="${not empty wxLinkPOJO8.linkUrl and not empty wxLinkPOJO8.imgSrc}">
								<a href="${wxLinkPOJO8.linkUrl}"><img alt="${wxLinkPOJO8.title}" src="${wxLinkPOJO8.imgSrc}"></a>
							</c:if>
		            		
						</c:if>
					 </div>
					 <div class="col-md-12 col-xs-12">
					 	<c:if test="${not empty wxLinkPOJO8}">
	            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${wxLinkPOJO8.title}"></c:out></p>
					 	</c:if>
					 </div>
                   </div>
				</div>
			</div>
            <!-- <div class="row" style="background-color:#f1f1f1;">
                <p style="text-align:center; margin-bottom:10px; margin-top:20px; font-size:12px; line-height:12px;">2016 合肥交通广播</p>
            </div>
            <div class="row" style="background-color:#333;">
                <p style="text-align:center; margin-bottom:5px; margin-top:5px; font-size:12px; line-height:12px; color:#ffffff;">技术支持:得味驿站</p>
            </div> -->
            
            <div class="row" style="background-color:#333;">
                <%@include file="../../page/media/wx_link_bottom.jsp" %>
            </div>
            <div class="row" style="background-color:#333;">
                <p style="text-align:center; margin-bottom:5px; margin-top:5px; font-size:12px; line-height:12px; color:#ffffff;">&copy;2016 合肥交通广播</p>
            </div>
		<!-- </div> --><!-- for show -->
	  		
	</div> <!-- container -->
  </body>
</html>

