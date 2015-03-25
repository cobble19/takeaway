Ext.define('TA.view.apply.Edit', {
	extend: 'Ext.window.Window',
	title: 'Edit Window',
	alias: 'widget.applyedit',
	autoShow: true,
	/*height: 200,
	width: 400,*/
	layout: 'fit',
	controller: 'apply',
	items: [{
		xtype: 'form',
		bodyPadding: 5,
		width: 870,
		layout: 'anchor',
		defaults: {
			anchor: '100%'
		},
		defaultType: 'textfield',
		items: [{
			fieldLabel: '标识',
			name: 'applyId'
		}, {
			fieldLabel: '标题',
			name: 'username'
		}, {
			fieldLabel: '内容',
			name: 'phone'
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