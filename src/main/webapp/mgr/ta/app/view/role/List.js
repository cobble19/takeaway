Ext.define('TA.view.role.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.rolelist',
	title: 'Role',
	width: 200,
	requires: [
	           'TA.store.Role',
	           'TA.view.role.RoleController',
	           'TA.view.role.RoleModel'],
	uses: [
           'TA.store.Role',
           'TA.view.role.RoleController',
           'TA.view.role.RoleModel',
           'TA.view.role.Edit'],
	
	controller: 'role',
	viewmode: {
		type: 'role'
	},
	selType: 'checkboxmodel',
	selModel: {
		model: 'MULTI',
		/*checkOnly: true,*/
		allowDeselect: true,
		enableKeyNav: true,
		showHeaderCheckbox: true
	},
	store: 'Role',
	dockedItems: [{
        xtype: 'toolbar',
        //height: 60,
        //dock: 'top',
        items: [{
        	xtype: 'textfield',
			name: 'roleName',
			fieldLabel: '角色',
			labelAlign: 'right'
        }]
    }, {
        xtype: 'pagingtoolbar',
        store: 'Role',   // same store GridPanel is using
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
		this.columns = [ {
			xtype : 'rownumberer',
			text : '#编号',
			width : 60
		}, {
			header : '角色唯一标识',
			dataIndex : 'roleId',
			flex : 1
		}, {
			header : '角色',
			dataIndex : 'roleName',
			flex : 1
		}, {
			header : '拥有的权限',
			dataIndex : 'privilegeNames',
			flex : 1,
			renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
				var ret = '';
				var privilegePOJOs = record.get('privilegePOJOs');
				if (privilegePOJOs != null && privilegePOJOs.length > 0) {
					Ext.each(privilegePOJOs, function(item, index, allItems) {
						ret += item['privilegeName'] + ", ";
					});
				}
				return ret;
			}
		} ];
		this.callParent(arguments);
	} 
});