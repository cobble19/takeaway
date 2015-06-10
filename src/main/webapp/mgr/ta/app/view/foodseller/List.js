Ext.define('TA.view.foodseller.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.foodsellerlist',
	title: '卖家',
	width: 200,
	requires: [
	           'TA.store.FoodSeller',
	           'TA.view.foodseller.FoodSellerController',
	           'TA.view.foodseller.FoodSellerModel',
	           'TA.view.locationbusiness.ComboBox'],
	uses: [
           'TA.store.FoodSeller',
           'TA.view.foodseller.FoodSellerController',
           'TA.view.foodseller.FoodSellerModel',
           'TA.view.foodseller.Edit',
           'TA.view.locationbusiness.ComboBox',
           'TA.view.locationarea.ComboBox',
           'TA.view.foodsellertype.ComboBox'],
	
	controller: 'foodseller',
	viewmode: {
		type: 'foodseller'
	},
	selType: 'checkboxmodel',
	selModel: {
		model: 'MULTI',
		/*checkOnly: true,*/
		allowDeselect: true,
		enableKeyNav: true,
		showHeaderCheckbox: true
	},
	store: 'FoodSeller',
	dockedItems: [{
        xtype: 'toolbar',
        //height: 60,
        //dock: 'top',
        items: [{
        	xtype: 'textfield',
			name: 'name',
			fieldLabel: '名称',
			labelAlign: 'right'
        }/*, {
        	xtype: 'locationbusinesscombobox',
			name: 'locationBusinessId',
			fieldLabel: '商业区',
			labelAlign: 'right',
			width: 100
        }*/]
    }, {
        xtype: 'pagingtoolbar',
        store: 'FoodSeller',   // same store GridPanel is using
        dock: 'bottom',
        displayInfo: true
    }],
    tbar: [{
    	xtype: 'button',
    	text: '添加',
    	handler: 'onAdd'
    }, {
    	xtype: 'button',
    	text: '修改',
    	handler: 'onUpdate'
    }, {
    	xtype: 'button',
    	text: '删除',
    	handler: 'onDelete'
    }, {
    	xtype: 'button',
    	text: '查询',
    	handler: 'onSearch'
    }],
	initComponent: function() {
		this.columns = [
			{xtype: 'rownumberer', text: '#编号', width: 60},
			{header: '唯一标识', dataIndex: 'foodSellerId', flex: 1},
			{header: '卖家名称', dataIndex: 'name', flex: 1},
			{header: '电话', dataIndex: 'phone', flex: 1},
			{header: '图标', dataIndex: 'logoUrl', flex: 1},
			{header: '营业时间', dataIndex: 'businessHours', flex: 1},
			{header: '送货区域', dataIndex: 'deliveryArea', flex: 1},
			{header: '送货最低运费', dataIndex: 'deliveryPriceMin', flex: 1},
			{header: '配送费用', dataIndex: 'deliveryFee', flex: 1},
			{header: '商家地址', dataIndex: 'address', flex: 1},
			{header: '注意事项', dataIndex: 'note', flex: 1},
			{header: '地图地址', dataIndex: 'mapAddr', flex: 1},
			{header: '商业区', dataIndex: 'businessName', flex: 1},
			{header: '地区', dataIndex: 'areaName', flex: 1},
			{header: '餐饮分类', dataIndex: 'foodSellerTypeName', flex: 1}
		];
		this.callParent(arguments);
	} 
});