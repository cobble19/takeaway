Ext.define('TA.view.user.Edit', {
	extend: 'Ext.window.Window',
	title: 'Edit Window',
	alias: 'widget.useredit',
	autoShow: true,
	/*height: 200,
	width: 400,*/
	layout: 'fit',
	controller: 'user',
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
			name: 'userId'
		}, {
			fieldLabel: '用户名',
			name: 'username'
		}, {
			fieldLabel: '密码',
			name: 'password'
		}, {
			fieldLabel: '有效',
			name: 'enable'
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