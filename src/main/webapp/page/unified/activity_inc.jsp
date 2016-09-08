<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        			
	     			
	     			<div id="create_activity">
	     				<strong>表单类信息</strong>
	     				<div style=" height:50px; line-height:50px;">
	     					<a class="btn btn-success btn-xs" href='<cmn:base/>/page/unified/activity.jsp'>添加</a>
	     					
	     					<input id="searchBtn" type="button" class="btn btn-info btn-xs" value="查询">
	     					<input id="deleteBtn" type="button" class="btn btn-danger btn-xs" value="删除">
	     					
	     				</div>
	     				<div id="queryCondition" style="">
	     					<fieldset class="scheduler-border">
	     						<legend class="scheduler-border"><h5>查询条件</h5></legend>
		     					<select id="typeCode" name="typeCode" style="font-size:12px;">
		     						<option value="" selected="selected">所有</option>
		     						<option value="1">报名申请</option>
		     						<option value="2">征集调查</option>
		     					</select>
	     					</fieldset>
	     				</div>
                        <div class="table-responsive">
				  		<table id="dbTable" class="display table table-striped table-bordered" cellspacing="0" width="100%">
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
				  						<th>类型</th>
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
	     			</div>	<!-- create_activity end -->
	     			<div id="activityDetailDiv" class="row" style="width: 450px; height: 120px;">
				  			<input type="text" class="" style="width: 300px;" id="activityDetailUrl"><br/>
				  			<!-- <a href="#none" id="copyInput">点击复制单中的文本</a> -->
				  			<!-- <input type="button" id="copyInput" class="btn btn-info" value="复制链接"> -->
				  			<span id="copyMsg" style="color: red;"></span>
				  			<button class="btn btn-info" id="copyBtn" data-clipboard-target="#activityDetailUrl">
							    复制链接
							</button>
			  		</div>
			  		<div id="picDiv">
				  		<form id="picForm" class="form-horizontal" role="form" action='' method="post">
				  			<input type="hidden" id="activityId">
				 			<div class="form-group">
				 				<label class="control-label" for="logoImg">图片:</label>
				 				<div class="">
				 					<input class="form-control" id="logoImg" name="logoImg" readonly="readonly" required="required" placeholder="请上传图片">
				 					<input class="" id="pic" name="pic" type="file">
				 					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传图片">
				 				</div>
				 			</div>
				 			<!-- <div class="form-group">
					 			<div class="">
					  				<button type="button" class="btn btn-default" id="addBtn">添加图片</button>
					 			</div>
				 			</div> -->
				  		</form>
			  		</div>
			  		
			  		<!-- 申请人信息model数据 -->
			  		<div id="apply2AttrModelDiv" class="">
			  			<fieldset class="scheduler-border">
			  				<legend class="scheduler-border"></legend>
					  		<form id="apply2AttrModelForm" class="form-horizontal" role="form" action='<cmn:base/>/web/media/apply2AttrModel/add' method="post">
				  				<input type="hidden" id="activityId">
					 			<!-- <div class="form-group static-attr">
					 				<label class="control-label col-sm-4" for="attr0">条目1:</label>
					 				<div class="col-sm-8">
					 					<input type="text" class="form-control attr" required="required" readonly id="attr0" name="attr0" placeholder="请输入条目1内容" value="个人名称">
					 					<input type="text" class="form-control remark" required="required" readonly id="remark0" name="remark0" placeholder="请输入条目1内容" value="个人名称">
					 				</div>
					 			</div>
					 			<div class="form-group static-attr">
					 				<label class="control-label col-sm-4" for="attr1">条目2:</label>
					 				<div class="col-sm-8">
					 					<input type="text" class="form-control attr" required="required" readonly id="attr1" name="attr1" placeholder="请输入条目2内容" value="手机号码">
					 					<input type="text" class="form-control remark" required="required" readonly id="remark1" name="remark1" placeholder="请输入条目2内容" value="手机号码">
					 				</div>
					 			</div> -->
					 			<!-- <div class="form-group static-attr">
					 				<label class="control-label col-sm-4" for="attr2">信息类型2:</label>
					 				<div class="col-sm-8">
					 					<input type="text" class="form-control" readonly id="attr2" name="attr2" placeholder="请输入信息类型1内容" value="性别">
					 				</div>
					 			</div> -->
					 			<!-- <div class="form-group">
					 				<label class="control-label" for="imgSrc">图片:</label>
					 				<div class="">
					 					<input class="form-control" id="imgSrc" name="imgSrc" readonly="readonly" required="required" placeholder="请上传图片">
					 					<input class="" id="pic" name="pic" type="file">
					 					<input class="btn btn-info" id="uploadBtn" name="uploadBtn" type="button" value="上传">
					 				</div>
					 			</div> -->
					 			<div class="form-group">
						 			<div class="">
						 				<!-- <button type="button" class="btn btn-default" id="addPicBtn">增加图片/文件</button> -->
						 				<button type="button" class="btn btn-default" id="addAttrBtn">增加条目</button>
						 				<button type="button" class="btn btn-default" id="popAttrBtn">移除最后一个条目</button>
						  				<button type="button" class="btn btn-default" id="addBtn">确定</button>
						  				<button type="button" class="btn btn-default" id="deleteAttrBtn">删除</button>
						 			</div>
					 			</div>
					  		</form>
			  			</fieldset>
			  		</div>
			  		
			  		<div id="tpl_static" style="visibility: hidden;">
			  			<div class="form-group static-attr">
					 				<label class="control-label col-sm-4" for="attr0">条目1:</label>
					 				<div class="col-sm-8">
					 					<input type="text" class="form-control attr" required="required" readonly id="attr0" name="attr0" placeholder="请输入条目1内容" value="个人名称">
					 					<input type="text" class="form-control remark" required="required" readonly id="remark0" name="remark0" placeholder="请输入条目1内容" value="个人名称">
					 				</div>
			 			</div>
			 			<div class="form-group static-attr">
			 				<label class="control-label col-sm-4" for="attr1">条目2:</label>
			 				<div class="col-sm-8">
			 					<input type="text" class="form-control attr" required="required" readonly id="attr1" name="attr1" placeholder="请输入条目2内容" value="手机号码">
			 					<input type="text" class="form-control remark" required="required" readonly id="remark1" name="remark1" placeholder="请输入条目2内容" value="手机号码">
			 				</div>
			 			</div>
			  		</div>
  		
	     			<script type="text/javascript" charset="utf-8" src="<cmn:base/>/js/unified/activity_inc.js"></script>
	     			
	     			
	     			
