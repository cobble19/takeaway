Ext.define('TA.view.foodsellertype.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.foodsellertypelist',
	title: 'FoodSellerType',
	width: 200,
	requires: [
	           'TA.store.FoodSellerType',
	           'TA.view.foodsellertype.FoodSellerTypeController',
	           'TA.view.foodsellertype.FoodSellerTypeModel'],
	uses: [
           'TA.store.FoodSellerType',
           'TA.view.foodsellertype.FoodSellerTypeController',
           'TA.view.foodsellertype.FoodSellerTypeModel',
           'TA.view.foodsellertype.Edit'],
	
	controller: 'foodsellertype',
	viewmode: {
		type: 'foodsellertype'
	},
	selType: 'checkboxmodel',
	selModel: {
		model: 'MULTI',
		/*checkOnly: true,*/
		allowDeselect: true,
		enableKeyNav: true,
		showHeaderCheckbox: true
	},
	store: 'FoodSellerType',
	dockedItems: [{
        xtype: 'toolbar',
        //height: 60,
        //dock: 'top',
        items: [{
        	xtype: 'textfield',
			name: 'name',
			fieldLabel: '餐饮类型',
			labelAlign: 'right'
        }]
    }, {
        xtype: 'pagingtoolbar',
        store: 'FoodSellerType',   // same store GridPanel is using
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
		console.log("this.store");
		console.log("this.store=" + this.store);
		this.columns = [
			{xtype: 'rownumberer', text: '#编号', width: 60},
			{header: '餐饮类型唯一标识', dataIndex: 'foodSellerTypeId', flex: 1},
			{header: '餐饮类型', dataIndex: 'name', flex: 1},
			{header: '图标', dataIndex: 'icon', flex: 1}
		];
		this.callParent(arguments);
	} 
});