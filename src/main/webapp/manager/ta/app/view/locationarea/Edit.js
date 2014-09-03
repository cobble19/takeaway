Ext.define('TA.view.locationarea.Edit', {
	extend: 'Ext.window.Window',
	title: 'Edit Window',
	alias: 'widget.locationareaedit',
	autoShow: true,
	/*height: 200,
	width: 400,*/
	layout: 'fit',
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
		buttons: [{
			text: '重置',
			handler: function(button, e) {
				this.up('form').getForm().reset();
			}
		}, {
			text: '添加',
			formBind: true,
			disabled: true,
			handler: function(button, e) {
				var form = this.up('form').getForm();
				/*if (form.isValid()) {
					
				}*/
				form.submit({
					url: '../../manager/locationArea',
					method: 'POST',
					params: '',
					/*waitTitle: 'Waiting',
					waitMsg: 'Waiting...',*/
					success: function(form, action) {
						console.log('add success.' + form + " " + Ext.JSON.encode(action.result) + " " + action.response.statusText);
					},
					failure: function(form, action) {
						console.log('add failure');
					}
				});
			}
		}, {
			text: '更新',
			formBind: true,
			disabled: true,
			handler: function(button, e) {
				var form = this.up('form').getForm();
				/*if (form.isValid()) {
					
				}*/
				form.submit({
					url: '../../manager/locationArea',
					method: 'POST',
					params: '',
					/*waitTitle: 'Waiting',
					waitMsg: 'Waiting...',*/
					success: function(form, action) {
						console.log('update success.');
					},
					failure: function(form, action) {
						console.log('update failure');
					}
				});
			}
		}]
	}],
	
});