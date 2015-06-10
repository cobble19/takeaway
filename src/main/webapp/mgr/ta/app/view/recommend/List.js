Ext.define('TA.view.recommend.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.recommendlist',
	title: 'Recommend',
	width: 200,
	requires: [
	           'TA.store.Recommend',
	           'TA.view.recommend.RecommendController',
	           'TA.view.recommend.RecommendModel',
	           'TA.view.ux.MyHtmlEditor'],
	uses: [
           'TA.store.Recommend',
           'TA.view.recommend.RecommendController',
           'TA.view.recommend.RecommendModel',
           'TA.view.recommend.Edit'],
	
	controller: 'recommend',
	viewmode: {
		type: 'recommend'
	},
	selType: 'checkboxmodel',
	selModel: {
		model: 'MULTI',
		/*checkOnly: true,*/
		allowDeselect: true,
		enableKeyNav: true,
		showHeaderCheckbox: true
	},
	store: 'Recommend',
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
        store: 'Recommend',   // same store GridPanel is using
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
			{header: '推荐唯一标识', dataIndex: 'recommendId', flex: 1},
			{header: '标题', dataIndex: 'title', flex: 1},
			{header: '内容', dataIndex: 'content', flex: 1, hidden: true},
			{header: '内容简写', dataIndex: 'contentDisp', flex: 1}
		];
		this.callParent(arguments);
	} 
});