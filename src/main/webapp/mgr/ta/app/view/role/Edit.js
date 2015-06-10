Ext.define('TA.view.role.Edit', {
	extend: 'Ext.window.Window',
	title: 'Edit Window',
	alias: 'widget.roleedit',
	autoShow: true,
	/*height: 200,
	width: 400,*/
	layout: 'fit',
	controller: 'role',
	items: [{
		xtype: 'form',
		/*title: 'Edit Form',*/
		bodyPadding: 5,
		width: 350,
		layout: 'anchor',
		defaults: {
			anchor: '100%'
		},
		defaultType: 'textfield',
		items: [{
			fieldLabel: '标识',
			name: 'roleId'
		}, {
			fieldLabel: '角色名称',
			name: 'roleName'
		}],
		buttons: [/*{
			text: '重置',
			handler: function(button, e) {
				this.up('form').getForm().reset();
			}
		}, */{
			text: '添加',
			id:'addBtn',
			formBind: true,
			disabled: true,
			handler: 'add'
		}, {
			text: '更新',
			id:'updateBtn',
			formBind: true,
			disabled: true,
			handler: 'update'
		}]
	}],
	
});