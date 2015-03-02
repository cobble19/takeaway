Ext.define('TA.view.recommend.Edit', {
	extend: 'Ext.window.Window',
	title: 'Edit Window',
	alias: 'widget.recommendedit',
	autoShow: true,
	/*height: 200,
	width: 400,*/
	layout: 'fit',
	controller: 'recommend',
	items: [{
		xtype: 'form',
		/*title: 'Edit Form',*/
		bodyPadding: 5,
		width: 870,
		layout: 'anchor',
		defaults: {
			anchor: '100%'
		},
		defaultType: 'textfield',
		items: [{
			fieldLabel: '标识',
			name: 'recommendId'
		}, {
			fieldLabel: '标题',
			name: 'title'
		}, {
			xtype: 'myhtmleditor',
			width: 760,
			height: 200,
			fieldLabel: '内容',
			name: 'content',
			url: '../../htmleditor/pic/add'
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