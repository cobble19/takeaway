Ext.define('TA.view.foodtype.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.foodtypelist',
	title: 'FoodType',
	width: 200,
	requires: [
	           'TA.store.FoodType',
	           'TA.view.foodtype.FoodTypeController',
	           'TA.view.foodtype.FoodTypeModel'],
	uses: [
           'TA.store.FoodType',
           'TA.view.foodtype.FoodTypeController',
           'TA.view.foodtype.FoodTypeModel',
           'TA.view.foodtype.Edit'],
	
	controller: 'foodtype',
	viewmode: {
		type: 'foodtype'
	},
	selType: 'checkboxmodel',
	selModel: {
		model: 'MULTI',
		/*checkOnly: true,*/
		allowDeselect: true,
		enableKeyNav: true,
		showHeaderCheckbox: true
	},
	store: 'FoodType',
	dockedItems: [{
        xtype: 'toolbar',
        //height: 60,
        //dock: 'top',
        items: [{
        	xtype: 'textfield',
			name: 'name',
			fieldLabel: '食品类型',
			labelAlign: 'right'
        }]
    }, {
        xtype: 'pagingtoolbar',
        store: 'FoodType',   // same store GridPanel is using
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
			{header: '食品类型唯一标识', dataIndex: 'foodTypeId', flex: 1},
			{header: '食品类型', dataIndex: 'name', flex: 1}
		];
		this.callParent(arguments);
	} 
});