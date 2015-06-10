Ext.define('TA.view.food.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.foodlist',
	title: 'Location Area',
	width: 200,
	requires: [
	           'TA.store.Food',
	           'TA.view.food.FoodController',
	           'TA.view.food.FoodModel'],
	uses: [
           'TA.store.Food',
           'TA.view.food.FoodController',
           'TA.view.food.FoodModel',
           'TA.view.food.Edit',
           'TA.view.foodseller.ComboBox',
           'TA.view.foodtype.ComboBox'],
	
	controller: 'food',
	viewmode: {
		type: 'food'
	},
	selType: 'checkboxmodel',
	selModel: {
		model: 'SIMPLE',
		checkOnly: true,
		/*allowDeselect: true,*/
		/*enableKeyNav: true,*/
		showHeaderCheckbox: true
	},
	store: 'Food',
	dockedItems: [{
        xtype: 'toolbar',
        //height: 60,
        //dock: 'top',
        items: [{
        	xtype: 'textfield',
			name: 'name',
			fieldLabel: '名称',
			labelAlign: 'right'
        }]
    }, {
        xtype: 'pagingtoolbar',
        store: 'Food',   // same store GridPanel is using
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
			{header: '唯一标识', dataIndex: 'foodId', flex: 1},
			{header: '名称', dataIndex: 'name', flex: 1},
			{header: '单价', dataIndex: 'unitPrice', flex: 1},
			{header: '卖家', dataIndex: 'foodSellerName', flex: 1},
			{header: '食品类型', dataIndex: 'foodTypeName', flex: 1}
		];
		this.callParent(arguments);
	} 
});