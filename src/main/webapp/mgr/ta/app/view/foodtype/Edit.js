Ext.define('TA.view.foodtype.Edit', {
	extend: 'Ext.window.Window',
	title: 'Edit Window',
	alias: 'widget.foodtypeedit',
	autoShow: true,
	/*height: 200,
	width: 400,*/
	layout: 'fit',
	controller: 'foodtype',
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
			name: 'foodTypeId'
		}, {
			fieldLabel: '食品类型',
			name: 'name'
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