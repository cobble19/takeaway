Ext.define('TA.view.privilege.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.privilegelist',
	title: 'Privilege',
	width: 200,
	requires: [
	           'TA.store.Privilege',
	           'TA.view.privilege.PrivilegeController',
	           'TA.view.privilege.PrivilegeModel'],
	uses: [
           'TA.store.Privilege',
           'TA.view.privilege.PrivilegeController',
           'TA.view.privilege.PrivilegeModel',
           'TA.view.privilege.Edit'],
	
	controller: 'privilege',
	viewmode: {
		type: 'privilege'
	},
	selType: 'checkboxmodel',
	selModel: {
		model: 'MULTI',
		/*checkOnly: true,*/
		allowDeselect: true,
		enableKeyNav: true,
		showHeaderCheckbox: true
	},
	store: 'Privilege',
	dockedItems: [{
        xtype: 'toolbar',
        //height: 60,
        //dock: 'top',
        items: [{
        	xtype: 'textfield',
			name: 'privilegeName',
			fieldLabel: '权限',
			labelAlign: 'right'
        }]
    }, {
        xtype: 'pagingtoolbar',
        store: 'Privilege',   // same store GridPanel is using
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
		{
			xtype : 'rownumberer',
			text : '#编号',
			width : 60
		},
		{
			header : '权限唯一标识',
			dataIndex : 'privilegeId',
			flex : 1
		},
		{
			header : '权限',
			dataIndex : 'privilegeName',
			flex : 1
		},
		{
			header : 'URL',
			dataIndex : 'url',
			flex : 1
		},
		{
			header : '拥有的角色',
			dataIndex : 'roleNames',
			flex : 1,
			renderer : function(value, metaData, record, rowIndex, colIndex, store, view) {
				var ret = '';
				var rolePOJOs = record.get('rolePOJOs');
				if (rolePOJOs != null
						&& rolePOJOs.length > 0) {
					Ext.each(rolePOJOs, function(item,
							index, allItems) {
						ret += item['roleName'] + ", ";
					});
				}
				return ret;
			}
		} ];
		this.callParent(arguments);
	} 
});