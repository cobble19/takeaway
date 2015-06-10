Ext.define('TA.store.Apply', {
	extend: 'Ext.data.Store',
	requires: ['TA.model.Apply'],
	model: 'TA.model.Apply',
//	autoLoad: true,
	pageSize: 20,
	proxy: {
		type: 'ajax',
		actionMethods: {
			read: 'POST'
		},
		url: '../../mgr/apply',
		/*api: {
			read: '../../mgr/apply',
			update: '../../mgr/apply/update',
			create: '../../mgr/apply/add',
			destroy: '../../mgr/apply/delete'
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
		console.log('Apply store init.');
	},
	initComponent: function() {
		console.log('Apply store initComponent.');
		this.callParent(arguments);
	},
	listeners: {
		beforeload: function( store, operation, eOpts ) {
			console.log('beforeload Apply Store');
			var nameField = Ext.ComponentQuery.query('locationarealist toolbar textfield[name=title]');
			if (nameField == null || nameField.length == 0) {
				return ;
			}
			var name = nameField[0].getValue();
			extraParams = {
				title: name
			};
			Ext.apply(store.proxy.extraParams, extraParams);
		}
	}
	
	
	
});
