Ext.define('TA.store.Food', {
	extend: 'Ext.data.Store',
	requires: ['TA.model.Food'],
	model: 'TA.model.Food',
	//autoLoad: true,
	pageSize: 2,
	proxy: {
		type: 'ajax',
		actionMethods: {
			read: 'POST'
		},
		url: '../../manager/food',
		/*api: {
			read: '../../manager/food',
			update: '../../manager/food/update',
			create: '../../manager/food/add',
			destroy: '../../manager/food/delete'
		},*/
		reader: {
			type: 'json',
			rootProperty: 'gridModelList',
			successProperty: 'success',
			totalProperty: 'total'
		}/*,
		writer: {
			type: 'json',
			writeAllFields: false,
            encode: true,
            rootProperty: 'gridModelList'
        }*/
	},
	/*listeners: {
		update: function(store, record, operation, modifiedFieldNames, eOpts) {
			console.log("record been updated." + 'operation:' + operation);
		},
		add: function( store, records, index, eOpts ) {
			console.log('record been added. index=' + index);
		}
	},*/
	init: function() {
		console.log('Food store init.');
	},
	initComponent: function() {
		console.log('Food store initComponent.');
		this.callParent(arguments);
	},
	listeners: {
		beforeload: function( store, operation, eOpts ) {
			console.log('beforeload Food Store');
			var nameField = Ext.ComponentQuery.query('foodlist toolbar textfield[name=name]');
			if (nameField == null || nameField.length == 0) {
				return ;
			}
			var name = nameField[0].getValue();
			extraParams = {
				name: name
			};
			Ext.apply(store.proxy.extraParams, extraParams);
		}
	}
	
	
	
});