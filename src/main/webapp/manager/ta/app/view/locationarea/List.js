Ext.define('TA.view.locationarea.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.locationarealist',
	title: 'Location Area',
	width: 200,
	requires: [
	           'TA.store.LocationArea',
	           'TA.view.locationarea.LocationAreaController',
	           'TA.view.locationarea.LocationAreaModel'],
	uses: [
           'TA.store.LocationArea',
           'TA.view.locationarea.LocationAreaController',
           'TA.view.locationarea.LocationAreaModel',
           'TA.view.locationarea.Edit'],
	
	controller: 'locationarea',
	viewmode: {
		type: 'locationarea'
	},
	selType: 'checkboxmodel',
	selModel: {
		model: 'MULTI',
		/*checkOnly: true,*/
		allowDeselect: true,
		enableKeyNav: true,
		showHeaderCheckbox: true
	},
	store: 'LocationArea',
	dockedItems: [{
        xtype: 'toolbar',
        //height: 60,
        //dock: 'top',
        items: [{
        	xtype: 'textfield',
			name: 'locationAreaSearchPOJO.name',
			fieldLabel: '地区名称',
			labelAlign: 'right'
        }]
    }, {
        xtype: 'pagingtoolbar',
        store: 'LocationArea',   // same store GridPanel is using
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
			{header: '地区唯一标识', dataIndex: 'locationAreaId', flex: 1},
			{header: '地区名称', dataIndex: 'name', flex: 1},
			{header: '描述', dataIndex: 'description', flex: 1}
		];
		this.callParent(arguments);
	} 
});