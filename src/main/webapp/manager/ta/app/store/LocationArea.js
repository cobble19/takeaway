Ext.define('TA.store.LocationArea', {
	extend: 'Ext.data.Store',
	requires: ['TA.model.LocationArea'],
	model: 'TA.model.LocationArea',
	autoLoad: true,
	pageSize: 2,
	proxy: {
		type: 'ajax',
		actionMethods: {
			read: 'POST'
		},
		api: {
			read: '../../manager/locationArea',
			update: '../../manager/locationArea',
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
		console.log('LocationArea store init.');
	},
	initComponent: function() {
		console.log('LocationArea store initComponent.');
		this.callParent(arguments);
	}
	
	
	
});