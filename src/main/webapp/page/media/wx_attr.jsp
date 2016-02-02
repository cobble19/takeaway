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
					<c:if test="${not empty wxSecPOJO0 and not empty wxSecPOJO0.wxAttrPOJOs}">
						<c:forEach items="${wxSecPOJO0.wxAttrPOJOs}" var="wxAttrPOJO" varStatus="st">
							<!-- title -->
							<c:if test="${st.index == 0}">
								<c:set var="var0" value="${wxAttrPOJO.wxAttrData}"></c:set>
							</c:if>
							<!-- imgSrc -->
							<c:if test="${st.index == 1}">
								<c:set var="var1" value="${wxAttrPOJO.wxAttrData}"></c:set>
							</c:if>
							<!-- linkUrl -->
							<c:if test="${st.index == 2}">
								<c:set var="var2" value="${wxAttrPOJO.wxAttrData}"></c:set>
							</c:if>
						</c:forEach>
            			<c:if test="${empty var2 and not empty var1}">
							<img alt="${var0}" src="${var1}">
						</c:if>
						<c:if test="${not empty var2 and not empty var1}">
							<a href="${var2}"><img alt="${var0}" src="${var1}"></a>
						</c:if>
						<div class="col-md-12 col-xs-12">
					 	<c:if test="${not empty var0}">
	            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${var0}"></c:out></p>
					 	</c:if>
					 </div>
					</c:if>
					<%-- <br/>
					分割---------分割线
					<br/>
					<c:if test="${not empty wxSecPOJO1 and not empty wxSecPOJO1.wxAttrPOJOs}">
						<c:forEach items="${wxSecPOJO1.wxAttrPOJOs}" var="wxAttrPOJO" varStatus="st">
							<c:if test="${st.count == 1}">
								<c:out value="${wxAttrPOJO.wxAttrData}"></c:out>
							</c:if>	
							<c:if test="${st.count == 2}">
		            			<c:out value="${wxAttrPOJO.wxAttrData}"></c:out>
							</c:if>									
						</c:forEach>
					</c:if> --%>
				</div>
			</div>
			<div class="row" style="background-color:#ffffff; border-left:#f1f1f1 4px solid;border-right:#f1f1f1 4px solid; border-top:#f1f1f1 2px solid; border-bottom:#f1f1f1 2px solid;">
				<div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#ffffff 4px solid;">
					 <div id="sec2" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxSecPOJO1 and not empty wxSecPOJO1.wxAttrPOJOs}">
							<c:forEach items="${wxSecPOJO1.wxAttrPOJOs}" var="wxAttrPOJO" varStatus="st">
								<!-- title -->
								<c:if test="${st.index == 0}">
									<c:set var="var0" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- imgSrc -->
								<c:if test="${st.index == 1}">
									<c:set var="var1" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- linkUrl -->
								<c:if test="${st.index == 2}">
									<c:set var="var2" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
							</c:forEach>
	            			<c:if test="${empty var2 and not empty var1}">
								<img alt="${var0}" src="${var1}">
							</c:if>
							<c:if test="${not empty var2 and not empty var1}">
								<a href="${var2}"><img alt="${var0}" src="${var1}"></a>
							</c:if>
							<div class="col-md-12 col-xs-12">
						 	<c:if test="${not empty var0}">
		            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${var0}"></c:out></p>
						 	</c:if>
						 </div>
						</c:if>
					 </div>
                   </div>
				</div>
                <div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div id="sec3" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxSecPOJO2 and not empty wxSecPOJO2.wxAttrPOJOs}">
							<c:forEach items="${wxSecPOJO2.wxAttrPOJOs}" var="wxAttrPOJO" varStatus="st">
								<!-- title -->
								<c:if test="${st.index == 0}">
									<c:set var="var0" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- imgSrc -->
								<c:if test="${st.index == 1}">
									<c:set var="var1" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- linkUrl -->
								<c:if test="${st.index == 2}">
									<c:set var="var2" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
							</c:forEach>
	            			<c:if test="${empty var2 and not empty var1}">
								<img alt="${var0}" src="${var1}">
							</c:if>
							<c:if test="${not empty var2 and not empty var1}">
								<a href="${var2}"><img alt="${var0}" src="${var1}"></a>
							</c:if>
							<div class="col-md-12 col-xs-12">
						 	<c:if test="${not empty var0}">
		            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${var0}"></c:out></p>
						 	</c:if>
						 </div>
						</c:if>
                   </div> 
                </div>
                </div>
				<div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div id="sec4" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxSecPOJO3 and not empty wxSecPOJO3.wxAttrPOJOs}">
							<c:forEach items="${wxSecPOJO3.wxAttrPOJOs}" var="wxAttrPOJO" varStatus="st">
								<!-- title -->
								<c:if test="${st.index == 0}">
									<c:set var="var0" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- imgSrc -->
								<c:if test="${st.index == 1}">
									<c:set var="var1" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- linkUrl -->
								<c:if test="${st.index == 2}">
									<c:set var="var2" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
							</c:forEach>
	            			<c:if test="${empty var2 and not empty var1}">
								<img alt="${var0}" src="${var1}">
							</c:if>
							<c:if test="${not empty var2 and not empty var1}">
								<a href="${var2}"><img alt="${var0}" src="${var1}"></a>
							</c:if>
							<div class="col-md-12 col-xs-12">
						 	<c:if test="${not empty var0}">
		            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${var0}"></c:out></p>
						 	</c:if>
						 </div>
						</c:if>
                   </div>
				</div>
            </div>
            </div>
            <div class="row" style="background-color:#ffffff; border-left:#f1f1f1 4px solid;border-right:#f1f1f1 4px solid; border-top:#f1f1f1 2px solid; border-bottom:#f1f1f1 2px solid;">
				<div class="col-md-4 col-xs-4">
                   <div class="row" style="border-left:#ffffff 4px solid;">
					 <div id="sec5" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxSecPOJO4 and not empty wxSecPOJO4.wxAttrPOJOs}">
							<c:forEach items="${wxSecPOJO4.wxAttrPOJOs}" var="wxAttrPOJO" varStatus="st">
								<!-- title -->
								<c:if test="${st.index == 0}">
									<c:set var="var0" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- imgSrc -->
								<c:if test="${st.index == 1}">
									<c:set var="var1" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- linkUrl -->
								<c:if test="${st.index == 2}">
									<c:set var="var2" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
							</c:forEach>
	            			<c:if test="${empty var2 and not empty var1}">
								<img alt="${var0}" src="${var1}">
							</c:if>
							<c:if test="${not empty var2 and not empty var1}">
								<a href="${var2}"><img alt="${var0}" src="${var1}"></a>
							</c:if>
							<div class="col-md-12 col-xs-12">
						 	<c:if test="${not empty var0}">
		            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${var0}"></c:out></p>
						 	</c:if>
						 </div>
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
		            		<c:if test="${not empty wxSecPOJO5 and not empty wxSecPOJO5.wxAttrPOJOs}">
								<c:forEach items="${wxSecPOJO5.wxAttrPOJOs}" var="wxAttrPOJO" varStatus="st">
									<!-- title -->
									<c:if test="${st.index == 0}">
										<c:set var="var0" value="${wxAttrPOJO.wxAttrData}"></c:set>
									</c:if>
									<!-- imgSrc -->
									<c:if test="${st.index == 1}">
										<c:set var="var1" value="${wxAttrPOJO.wxAttrData}"></c:set>
									</c:if>
									<!-- linkUrl -->
									<c:if test="${st.index == 2}">
										<c:set var="var2" value="${wxAttrPOJO.wxAttrData}"></c:set>
									</c:if>
								</c:forEach>
		            			<c:if test="${empty var2 and not empty var1}">
									<img alt="${var0}" src="${var1}">
								</c:if>
								<c:if test="${not empty var2 and not empty var1}">
									<a href="${var2}"><img alt="${var0}" src="${var1}"></a>
								</c:if>
								<div class="col-md-12 col-xs-12">
							 	<c:if test="${not empty var0}">
			            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${var0}"></c:out></p>
							 	</c:if>
							 </div>
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
	            		<c:if test="${not empty wxSecPOJO6 and not empty wxSecPOJO6.wxAttrPOJOs}">
							<c:forEach items="${wxSecPOJO6.wxAttrPOJOs}" var="wxAttrPOJO" varStatus="st">
								<!-- title -->
								<c:if test="${st.index == 0}">
									<c:set var="var0" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- imgSrc -->
								<c:if test="${st.index == 1}">
									<c:set var="var1" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- linkUrl -->
								<c:if test="${st.index == 2}">
									<c:set var="var2" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
							</c:forEach>
	            			<c:if test="${empty var2 and not empty var1}">
								<img alt="${var0}" src="${var1}">
							</c:if>
							<c:if test="${not empty var2 and not empty var1}">
								<a href="${var2}"><img alt="${var0}" src="${var1}"></a>
							</c:if>
							<div class="col-md-12 col-xs-12">
						 	<c:if test="${not empty var0}">
		            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${var0}"></c:out></p>
						 	</c:if>
						 </div>
						</c:if>
                   </div>
				</div>
				</div>
				<div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div id="sec8" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxSecPOJO7 and not empty wxSecPOJO7.wxAttrPOJOs}">
							<c:forEach items="${wxSecPOJO7.wxAttrPOJOs}" var="wxAttrPOJO" varStatus="st">
								<!-- title -->
								<c:if test="${st.index == 0}">
									<c:set var="var0" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- imgSrc -->
								<c:if test="${st.index == 1}">
									<c:set var="var1" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- linkUrl -->
								<c:if test="${st.index == 2}">
									<c:set var="var2" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
							</c:forEach>
	            			<c:if test="${empty var2 and not empty var1}">
								<img alt="${var0}" src="${var1}">
							</c:if>
							<c:if test="${not empty var2 and not empty var1}">
								<a href="${var2}"><img alt="${var0}" src="${var1}"></a>
							</c:if>
							<div class="col-md-12 col-xs-12">
						 	<c:if test="${not empty var0}">
		            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${var0}"></c:out></p>
						 	</c:if>
						 </div>
						</c:if>
                   </div>
				</div>
				<div class="col-md-4 col-xs-4" >
                   <div class="row" style="border-left:#f1f1f1 4px solid;">
					 <div id="sec9" class="col-md-12 col-xs-12" style="text-align:center;">
	            		<c:if test="${not empty wxSecPOJO8 and not empty wxSecPOJO8.wxAttrPOJOs}">
							<c:forEach items="${wxSecPOJO8.wxAttrPOJOs}" var="wxAttrPOJO" varStatus="st">
								<!-- title -->
								<c:if test="${st.index == 0}">
									<c:set var="var0" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- imgSrc -->
								<c:if test="${st.index == 1}">
									<c:set var="var1" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
								<!-- linkUrl -->
								<c:if test="${st.index == 2}">
									<c:set var="var2" value="${wxAttrPOJO.wxAttrData}"></c:set>
								</c:if>
							</c:forEach>
	            			<c:if test="${empty var2 and not empty var1}">
								<img alt="${var0}" src="${var1}">
							</c:if>
							<c:if test="${not empty var2 and not empty var1}">
								<a href="${var2}"><img alt="${var0}" src="${var1}"></a>
							</c:if>
							<div class="col-md-12 col-xs-12">
						 	<c:if test="${not empty var0}">
		            			<p style="text-align:center; margin-bottom:10px; font-size:12px; line-height:12px; min-height:25px;"><c:out value="${var0}"></c:out></p>
						 	</c:if>
						 </div>
						</c:if>
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

