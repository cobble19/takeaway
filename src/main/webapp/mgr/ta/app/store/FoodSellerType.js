Ext.define('TA.store.FoodSellerType', {
	extend: 'Ext.data.Store',
	requires: ['TA.model.FoodSellerType'],
	model: 'TA.model.FoodSellerType',
//	autoLoad: true,
	pageSize: 20,
	proxy: {
		type: 'ajax',
		actionMethods: {
			read: 'POST'
		},
		url: '../../mgr/foodSellerType',
		/*api: {
			read: '../../mgr/foodSellerType',
			update: '../../mgr/foodSellerType/update',
			create: '../../mgr/foodSellerType/add',
			destroy: '../../mgr/foodSellerType/delete'
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
		console.log('FoodSellerType store init.');
	},
	initComponent: function() {
		console.log('FoodSellerType store initComponent.');
		this.callParent(arguments);
	},
	listeners: {
		beforeload: function( store, operation, eOpts ) {
			console.log('beforeload FoodSellerType Store');
			var nameField = Ext.ComponentQuery.query('foodsellertypelist toolbar textfield[name=name]');
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
