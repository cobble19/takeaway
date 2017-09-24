<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
				
	     			<div id="create_activity_registration">
	     				<h4 style="font-weight:bold;">表单类信息
	     				</h4>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-info btn-xs" target="_blank" data-toggle="tooltip" data-placement="top" 
	     					title="添加" href='<cmn:base/>/page/unified/activity_registration.jsp'>
								<span style="color: ;" class="glyphicon glyphicon-plus"></span>
							</a>
							
	     					<button  id="searchBtn4ActivityRegistration" type="button" class="btn btn-info btn-xs"
	     						data-toggle="tooltip" data-placement="top" title="查询" value="查询">
	     						<span class="glyphicon glyphicon-search" ></span>
	     					</button>
	     					<button  id="showLinkBtn4ActivityRegistration" type="button" class="btn btn-info btn-xs"
	     						data-toggle="tooltip" data-placement="top" title="相关链接" value="相关链接">
	     						<span class="glyphicon glyphicon-link" ></span>
	     					</button>
	     					<button  id="deleteBtn4ActivityRegistration" type="button" class="btn btn-danger btn-xs"
	     						data-toggle="tooltip" data-placement="top" title="删除" value="删除">
	     						<span class="glyphicon glyphicon-trash" ></span>
	     					</button>
	     					
	     				</div>
                        <div class="table-responsive">
				  		<table id="dbTable4ActivityRegistration" class="display table table-striped table-bordered" cellspacing="0" width="100%">
				  				<thead>
				  					<tr>
				  						<th><input type="checkbox" name="chkBoxAll" id="chkBoxAll">全选</th>
				  						<th>序号</th>
				  						<th>标识</th>
				  						<th>标题</th>
				  						<th>开始时间</th>
				  						<th>截止时间</th>
				  						<th>期限</th>
				  						<th>状态</th>
				  						<th>内容简介</th>
				  						<!-- <th>类型</th> -->
				  						<th>操作</th>
				  					</tr>
				  				</thead>
				  				<!-- <tfoot>
				  					<tr>
				  						<th>No.</th>
				  						<th>标识</th>
				  						<th>标题</th>
				  						<th>内容</th>
				  					</tr>
				  				</tfoot> -->
				  			</table>
                            </div>
	     			</div>	<!-- create_activity_registration end -->
	     			<div id="activityRegistrationDetailDiv" class="row" style="width: 450px; height: 120px;">
				  			<label>单纯表格页面链接</label>: <input type="text" class="" style="width: 400px;" id="activityRegistrationDetailUrl">
				  			<button class="btn btn-info btn-xs" id="copyBtn4ActivityRegistration" data-clipboard-target="#activityRegistrationDetailUrl">
							    复制链接
							</button>
							<br/>
							<br/>
				  			<label>完整内容页面链接</label>: <input type="text" class="" style="width: 400px;" id="activityRegistrationDetailUrl2">
				  			<button class="btn btn-info btn-xs" id="copyBtn24ActivityRegistration" data-clipboard-target="#activityRegistrationDetailUrl2">
							    复制链接
							</button>
				  			<br/>
				  			<span id="copyMsg" style="color: red;"></span>
				  			
			  		</div>
			  		
	     			<div id="activityActiveGatherListDiv" class="row" style="width: 450px; height: 120px;">
				  			<label>正在活动列表页面链接</label>: <input type="text" class="" style="width: 400px;" id="activityRegistrationActiveListUrl">
				  			<button class="btn btn-info btn-xs" id="copyBtn4activityRegistrationActiveList" data-clipboard-target="#activityActiveListUrl">
							    复制链接
							</button>
							<br/>
							<br/>
				  			<span id="copyMsg" style="color: red;"></span>
				  			
			  		</div>
			  		<div id="picDiv">
				  		<form id="picForm" class="form-horizontal" role="form" action='' method="post">
				  			<input type="hidden" id="activityRegistrationId">
				 			<div class="form-group">
				 				<label class="control-label" for="logoImg">图片:</label>
				 				<div class="">
				 					<input class="form-control" id="logoImg" name="logoImg" readonly="readonly" required="required" placeholder="请上传图片">
				 					<input class="" id="pic" name="pic" type="file">
				 					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传图片">
				 				</div>
				 			</div>
				  		</form>
			  		</div>
			  		
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/activity_registration_inc.js"></script>
	     			
	     			
	     			
