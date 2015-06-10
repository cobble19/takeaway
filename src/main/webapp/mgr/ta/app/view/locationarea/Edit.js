Ext.define('TA.view.locationarea.Edit', {
	extend: 'Ext.window.Window',
	title: 'Edit Window',
	alias: 'widget.locationareaedit',
	autoShow: true,
	/*height: 200,
	width: 400,*/
	layout: 'fit',
	controller: 'locationarea',
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
			name: 'locationAreaId'
		}, {
			fieldLabel: '地区名称',
			name: 'name'
		}, {
			fieldLabel: '描述',
			name: 'description'
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