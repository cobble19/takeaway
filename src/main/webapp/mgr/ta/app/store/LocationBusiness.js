Ext.define('TA.store.LocationBusiness', {
	extend: 'Ext.data.Store',
	requires: ['TA.model.LocationBusiness'],
	model: 'TA.model.LocationBusiness',
//	autoLoad: true,
	pageSize: 20,
	proxy: {
		type: 'ajax',
		actionMethods: {
			read: 'POST'
		},
		url: '../../mgr/locationBusiness',
		/*api: {
			read: '../../mgr/locationBusiness',
			update: '../../mgr/locationBusiness',
			create: '',
			destroy: ''
		},*/
		reader: {
			type: 'json',
			rootProperty: 'gridModelList',
			successProperty: 'success',
			totalProperty: 'total'
		}/*,
		writer: {
            encode: true,
            root: 'updatedStr'
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
		console.log('LocationBusiness store init.');
	},
	initComponent: function() {
		console.log('LocationBusiness store initComponent.');
		this.callParent(arguments);
	},
	listeners: {
		beforeload: function( store, operation, eOpts ) {
			console.log('beforeload LocationBusiness Store');
			var nameField = Ext.ComponentQuery.query('locationbusinesslist toolbar textfield[name=name]');
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