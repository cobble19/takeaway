Ext.define('TA.view.activity.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.activitylist',
	title: 'Activity',
	width: 200,
	requires: [
	           'TA.store.Activity',
	           'TA.view.activity.ActivityController',
	           'TA.view.activity.ActivityModel',
	           'TA.view.ux.MyHtmlEditor'],
	uses: [
           'TA.store.Activity',
           'TA.view.activity.ActivityController',
           'TA.view.activity.ActivityModel',
           'TA.view.activity.Edit'],
	
	controller: 'activity',
	viewmode: {
		type: 'activity'
	},
	selType: 'checkboxmodel',
	selModel: {
		model: 'MULTI',
		/*checkOnly: true,*/
		allowDeselect: true,
		enableKeyNav: true,
		showHeaderCheckbox: true
	},
	store: 'Activity',
	dockedItems: [{
        xtype: 'toolbar',
        //height: 60,
        //dock: 'top',
        items: [{
        	xtype: 'textfield',
			name: 'title',
			fieldLabel: '标题',
			labelAlign: 'right'
        }]
    }, {
        xtype: 'pagingtoolbar',
        store: 'Activity',   // same store GridPanel is using
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
			{header: '推荐唯一标识', dataIndex: 'activityId', flex: 1},
			{header: '标题', dataIndex: 'title', flex: 1},
			{header: '内容', dataIndex: 'content', flex: 1, hidden: true},
			{header: '内容简写', dataIndex: 'contentDisp', flex: 1}
		];
		this.callParent(arguments);
	} 
});