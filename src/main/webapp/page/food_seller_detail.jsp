<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cmn" uri="/WEB-INF/tlds/common.tld" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>餐厅</title>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<cmn:base/>/jquery/jquery-1.11.1.min.js"></script>
    <script src="<cmn:base/>/jquery/jquery-migrate-1.2.1.min.js"></script>
    <!-- Bootstrap -->
    <link href="<cmn:base/>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<cmn:base/>/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<cmn:base/>/bootstrap/js/bootstrap.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
     <!--引用百度地图API-->
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BE59fc66ef5ecb52879a5b5a97cee9b3"></script>
    
    
    <style>
		table td{
			border: 0px;
			border-top-width: 0px !important;
			border-bottom-width: 0px !important;
		}
	</style>
  </head>
  <body>
    <!-- <h1>Hello, world!</h1> -->
   <div class="main">
    <div class="wm-head"></div>
	<div class="wm-body">
			<div class="wm-body-left">
				<%-- <c:redirect url="<cmn:base/>/web/foodSellerType"></c:redirect> --%>
				<jsp:include page="../web/foodSellerType"></jsp:include>
				<br/>
				<jsp:include page="../web/tree"></jsp:include>
			</div>
			<div class="wm-body-right">
		    <div class="wm-body-right-s">
			<form action='<cmn:base/>/web/foodSellers/search'>
								<label style="float:left; line-height:47px; margin-left:30px; font-size:14px;" for="search">快速搜索：    </label>
								<div class="search-1"></div>
								<input class="search-2" id="keyword" name="keyword" type="text" placeholder="搜索" value="${param.keyword}">
								<div style="vertical-align: middle; float:left; margin-left:85px;line-height:47px;">评分最高 | 速度最快 | 起送价格最低</div>
							</form>
			</div>
			<div class="wm-body-right-wz"><div class="wm-body-right-wz-text">您所在位置：合肥市>>${foodSellerPOJO.locationAreaPOJO.name }>>${foodSellerPOJO.locationBusinessPOJO.name }</div></div>
			<div class="xian"></div>
			<div class="wmt-k">
			    <div class="wmt-k1">
				    <div class="logo"><%-- <img alt="" src="<cmn:base/>/bootstrap/images/web3-new_06.jpg"> --%>
				    	<img alt="" src="<cmn:base/>/files/${foodSellerPOJO.logoUrl}">
				    </div>
					<div class="n-d">
					    <div class="c-name">${foodSellerPOJO.name}</div>
						<div class="c-num">
						    <div class="k1"></div>
						    <div class="tel-t"></div>
							<div class="k2"></div>
							<div class="tel-s">${foodSellerPOJO.phone}</div>
						</div>
					</div>
				    <div class="xing"></div>
					<div class="k3"></div>
					<div class="xinxi">
					    <div class="time"><a href="#caidan">查看菜单</a> | <a href="#ditu">查看地图</a> | 营业时间：${foodSellerPOJO.businessHours}</div>
						<div class="fanw">送餐范围：${foodSellerPOJO.deliveryArea}</div>
						<div class="qis">外卖起送价格：${foodSellerPOJO.deliveryPriceMin}元</div>
						<div class="qis">配送费用：${foodSellerPOJO.deliveryFee}元</div>
						<div class="diz">商家地址：${foodSellerPOJO.address}</div>
						<div class="diz">地图地址：${foodSellerPOJO.mapAddr}</div>
					</div>
							<br/>
							<div id="caidan">
							<c:forEach items="${foodSellerPOJO.foodTypePOJOs}" var="foodTypePOJO">
							<table class="table table-hover">
								<thead style="color: #AABBCC">
									<th colspan="4">${foodTypePOJO.name}</th>
								</thead>
								<c:forEach items="${foodTypePOJO.foodPOJOs}" var="foodPOJO" varStatus="st">
									<c:if test="${st.index % 2 == 0}">
										<tr>
									</c:if>
										<td style="width: 25%; text-align: left;">${foodPOJO.name }</td>
										<td style="width: 25%; text-align: center;">${foodPOJO.unitPrice}元</td>
										<c:if test="${st.index / 2 == 0 && st.isLast()}"> <!-- 增加2个td， 不然在一行就只有2个td -->
											<td></td><td></td>
										</c:if>	
									<c:if test="${st.index % 2 == 1 || st.isLast()}">
										</tr>
									</c:if>
								</c:forEach>
							</table>
							</c:forEach>
							<div><hr style="height: 5px;"/></div>
							</div>
							
							
							<div id="cobbleMap">
								<h2>地图查询</h2>
								<div style="width:600px;height:450px;border:#ccc solid 1px;font-size:12px" id="map"></div>
							</div>
					</div>
					<div class="col-md-1"></div>
				</div>
			</div>
    </div><!-- wm-body end -->
	<footer><hr><p>&copy; 版权所有</p></footer>
	</div>
  </body>
  
    <script type="text/javascript">
    //创建和初始化地图函数：
    function initMap(){
      createMap();//创建地图
      setMapEvent();//设置地图事件
      addMapControl();//向地图添加控件
      addMapOverlay();//向地图添加覆盖物
    }
    function createMap(){ 
      map = new BMap.Map("map"); 
      map.centerAndZoom(new BMap.Point(117.239591,31.840134),15);
    }
    function setMapEvent(){
      map.enableScrollWheelZoom();
      map.enableKeyboard();
      map.enableDragging();
      map.enableDoubleClickZoom()
    }
    function addClickHandler(target,window){
      target.addEventListener("click",function(){
        target.openInfoWindow(window);
      });
    }
    function addMapOverlay(){
      var markers = [
        {content:"",title:"港汇广场",imageOffset: {width:-46,height:-21},position:{lat:31.841054,lng:117.239807}}
      ];
      for(var index = 0; index < markers.length; index++ ){
        var point = new BMap.Point(markers[index].position.lng,markers[index].position.lat);
        var marker = new BMap.Marker(point,{icon:new BMap.Icon("./images/icon.png",new BMap.Size(20,25),{
          imageOffset: new BMap.Size(markers[index].imageOffset.width,markers[index].imageOffset.height)
        })});
        var label = new BMap.Label(markers[index].title,{offset: new BMap.Size(25,5)});
        var opts = {
          width: 200,
          title: markers[index].title,
          enableMessage: false
        };
        var infoWindow = new BMap.InfoWindow(markers[index].content,opts);
        marker.setLabel(label);
        addClickHandler(marker,infoWindow);
        map.addOverlay(marker);
      };
    }
    //向地图添加控件
    function addMapControl(){
      var scaleControl = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
      scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
      map.addControl(scaleControl);
      var navControl = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
      map.addControl(navControl);
      var overviewControl = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:true});
      map.addControl(overviewControl);
    }
    var map;
      initMap();
  </script>
</html>