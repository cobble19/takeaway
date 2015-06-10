Ext.define('TA.store.FoodSeller', {
	extend: 'Ext.data.Store',
	requires: ['TA.model.FoodSeller'],
	model: 'TA.model.FoodSeller',
	//autoLoad: true,
	pageSize: 20,
	proxy: {
		type: 'ajax',
		actionMethods: {
			read: 'POST'
		},
		url: '../../mgr/foodSeller',
		/*api: {
			read: '../../mgr/foodSeller',
			update: '../../mgr/foodSeller/update',
			create: '../../mgr/foodSeller/add',
			destroy: '../../mgr/foodSeller/delete'
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
		console.log('FoodSeller store init.');
	},
	initComponent: function() {
		console.log('FoodSeller store initComponent.');
		this.callParent(arguments);
	},
	listeners: {
		beforeload: function( store, operation, eOpts ) {
			console.log('beforeload FoodSeller Store');
			var nameField = Ext.ComponentQuery.query('foodsellerlist toolbar textfield[name=name]');
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