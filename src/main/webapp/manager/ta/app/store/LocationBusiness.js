Ext.define('TA.store.LocationBusiness', {
	extend: 'Ext.data.Store',
	requires: ['TA.model.LocationBusiness'],
	model: 'TA.model.LocationBusiness',
	//autoLoad: true,
	pageSize: 2,
	proxy: {
		type: 'ajax',
		actionMethods: {
			read: 'POST'
		},
		api: {
			read: '../../manager/locationBusiness',
			update: '../../manager/locationBusiness',
			create: '',
			destroy: ''
		},
		reader: {
			type: 'json',
			rootProperty: 'gridModelList',
			successProperty: 'success',
			totalProperty: 'total'
		},
		writer: {
            encode: true,
            root: 'updatedStr'
        }
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
	}
	
	
	
});