Ext.define('TA.view.foodseller.Edit', {
	extend: 'Ext.window.Window',
	title: 'Edit Window',
	alias: 'widget.foodselleredit',
	autoShow: true,
	/*height: 200,
	width: 400,*/
	layout: 'fit',
	controller: 'foodseller',
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
			name: 'foodSellerId'
		}, {
			fieldLabel: '名称',
			name: 'name'
		}, {
			fieldLabel: '电话',
			name: 'phone'
		}, {
			fieldLabel: '图标',
			xtype: 'filefield',
			name: 'file',
			msgTarget: 'side',
			allowBlank: true,
			buttonText: '选择图片'
		}, {
			fieldLabel: '营业时间',
			name: 'businessHours'
		}, {
			fieldLabel: '送货区域',
			name: 'deliveryArea'
		}, {
			fieldLabel: '送货最低运费',
			name: 'deliveryPriceMin'
		}, {
			fieldLabel: '配送费用',
			name: 'deliveryFee'
		}, {
			fieldLabel: '商家地址',
			name: 'address'
		}, {
			fieldLabel: '注意事项',
			name: 'note'
		}, {
			fieldLabel: '地图地址',
			name: 'mapAddr'
		}, {
			xtype: 'locationbusinesscombobox',
			labelAlign: 'left'
		}, {
			fieldLabel: 'relBusinessSellerPOJO主键',
			name: 'relBusinessSellerPOJO.relBusinessSellerId',
			hidden: true
		}, {
			fieldLabel: 'relBusinessSellerPOJO seller id',
			name: 'relBusinessSellerPOJO.foodSellerId',
			hidden: true
		}, {
			xtype: 'locationareacombobox',
			labelAlign: 'left'
		}, {
			xtype: 'foodsellertypecombobox',
			labelAlign: 'left'
		}, {
			fieldLabel: 'relAreaSellerPOJO主键',
			name: 'relAreaSellerPOJO.relAreaSellerId',
			hidden: true
		}, {
			fieldLabel: 'relAreaSellerPOJO seller id',
			name: 'relAreaSellerPOJO.foodSellerId',
			hidden: true
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