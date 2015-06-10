Ext.define('TA.store.FoodType', {
	extend: 'Ext.data.Store',
	requires: ['TA.model.FoodType'],
	model: 'TA.model.FoodType',
//	autoLoad: true,
	pageSize: 20,
	proxy: {
		type: 'ajax',
		actionMethods: {
			read: 'POST'
		},
		url: '../../mgr/foodType',
		/*api: {
			read: '../../mgr/foodType',
			update: '../../mgr/foodType/update',
			create: '../../mgr/foodType/add',
			destroy: '../../mgr/foodType/delete'
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
		console.log('FoodType store init.');
	},
	initComponent: function() {
		console.log('FoodType store initComponent.');
		this.callParent(arguments);
	},
	listeners: {
		beforeload: function( store, operation, eOpts ) {
			console.log('beforeload FoodType Store');
			var nameField = Ext.ComponentQuery.query('foodtypelist toolbar textfield[name=name]');
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
