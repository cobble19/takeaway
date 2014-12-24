Ext.define('TA.view.food.Edit', {
	extend: 'Ext.window.Window',
	title: 'Edit Window',
	alias: 'widget.foodedit',
	autoShow: true,
	/*height: 200,*/
	width: 450,
	layout: 'fit',
	controller: 'food',
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
			name: 'foodId'
		}, {
			xtype: 'foodsellercombobox'
			/*fieldLabel: '卖家',
			name: 'foodSellerId'*/
		}, {
			fieldLabel: '名称',
			name: 'name'
		}, {
			fieldLabel: '单价',
			name: 'unitPrice'
		}, {
			xtype: 'foodtypecombobox'
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