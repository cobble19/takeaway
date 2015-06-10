Ext.define('TA.view.user.List', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.userlist',
	title: 'User',
	width: 200,
	requires: [
	           'TA.store.User',
	           'TA.view.user.UserController',
	           'TA.view.user.UserModel'],
	uses: [
           'TA.store.User',
           'TA.view.user.UserController',
           'TA.view.user.UserModel',
           'TA.view.user.Edit'],
	
	controller: 'user',
	viewmode: {
		type: 'user'
	},
	selType: 'checkboxmodel',
	selModel: {
		model: 'MULTI',
		/*checkOnly: true,*/
		allowDeselect: true,
		enableKeyNav: true,
		showHeaderCheckbox: true
	},
	store: 'User',
	dockedItems: [{
        xtype: 'toolbar',
        //height: 60,
        //dock: 'top',
        items: [{
        	xtype: 'textfield',
			name: 'username',
			fieldLabel: '用户名',
			labelAlign: 'right'
        }]
    }, {
        xtype: 'pagingtoolbar',
        store: 'User',   // same store GridPanel is using
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
			header : '用户唯一标识',
			dataIndex : 'userId',
			flex : 1
		}, {
			header : '用户',
			dataIndex : 'username',
			flex : 1
		}, {
			header : '密码',
			dataIndex : 'password',
			flex : 1
		}, {
			header : '有效',
			dataIndex : 'enable',
			flex : 1
		}, {
			header : '拥有的角色',
			dataIndex : 'roleNames',
			flex : 1,
			renderer : function(value, metaData, record, rowIndex, colIndex, store, view) {
				var ret = '';
				var rolePOJOs = record.get('rolePOJOs');
				if (rolePOJOs != null && rolePOJOs.length > 0) {
					Ext.each(rolePOJOs, function(item, index, allItems) {
						ret += item['roleName'] + ", ";
					});
				}
				return ret;
			}
		}];
		this.callParent(arguments);
	} 
});