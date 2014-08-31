Ext.define('TA.view.locationbusiness.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.locationbusinesslist',
	title: 'Location Business',
	width: 200,
	requires: [
	           'TA.store.LocationBusiness',
	           'TA.view.locationbusiness.LocationBusinessController',
	           'TA.view.locationbusiness.LocationBusinessModel'],
	uses: [
           'TA.store.LocationBusiness',
           'TA.view.locationbusiness.LocationBusinessController',
           'TA.view.locationbusiness.LocationBusinessModel'],
	
	controller: 'locationbusiness',
	viewmode: {
		type: 'locationbusiness'
	},
	selType: 'checkboxmodel',
	selModel: {
		model: 'MULTI',
		/*checkOnly: true,*/
		allowDeselect: true,
		enableKeyNav: true,
		showHeaderCheckbox: true
	},
	store: 'LocationBusiness',
	dockedItems: [{
        xtype: 'toolbar',
        //height: 60,
        //dock: 'top',
        items: [{
        	xtype: 'textfield',
			name: 'locationBusinessSearchPOJO.name',
			fieldLabel: '商业区名称',
			labelAlign: 'right'
        }]
    }, {
        xtype: 'pagingtoolbar',
        store: 'LocationBusiness',   // same store GridPanel is using
        dock: 'bottom',
        displayInfo: true
    }],
    tbar: [{
    	xtype: 'button',
    	text: '添加',
    	handler: 'onAdd'
    }],
	initComponent: function() {
		console.log("this.store");
		console.log("this.store=" + this.store);
		this.columns = [
			{xtype: 'rownumberer', text: '#编号', width: 60},
			{header: '商业区唯一标识', dataIndex: 'locationBusinessId', flex: 1},
			{header: '商业区名称', dataIndex: 'name', flex: 1},
			{header: '描述', dataIndex: 'description', flex: 1}
		];
		this.callParent(arguments);
	} 
});