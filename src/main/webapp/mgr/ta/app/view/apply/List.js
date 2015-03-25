Ext.define('TA.view.apply.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.applylist',
	title: 'Apply',
	width: 200,
	requires: [
	           'TA.store.Apply',
	           'TA.view.apply.ApplyController',
	           'TA.view.apply.ApplyModel',
	           'TA.view.ux.MyHtmlEditor'],
	uses: [
           'TA.store.Apply',
           'TA.view.apply.ApplyController',
           'TA.view.apply.ApplyModel',
           'TA.view.apply.Edit'],
	
	controller: 'apply',
	viewmode: {
		type: 'apply'
	},
	selType: 'checkboxmodel',
	selModel: {
		model: 'MULTI',
		/*checkOnly: true,*/
		allowDeselect: true,
		enableKeyNav: true,
		showHeaderCheckbox: true
	},
	store: 'Apply',
	dockedItems: [{
        xtype: 'toolbar',
        //height: 60,
        //dock: 'top',
        items: [{
        	xtype: 'textfield',
			name: 'username',
			fieldLabel: '姓名',
			labelAlign: 'right'
        }]
    }, {
        xtype: 'pagingtoolbar',
        store: 'Apply',   // same store GridPanel is using
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
			{header: '推荐唯一标识', dataIndex: 'applyId', flex: 1},
			{header: '姓名', dataIndex: 'username', flex: 1},
			{header: '手机号', dataIndex: 'phone', flex: 1}
		];
		this.callParent(arguments);
	} 
});