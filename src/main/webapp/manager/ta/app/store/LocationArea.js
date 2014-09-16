Ext.define('TA.store.LocationArea', {
	extend: 'Ext.data.Store',
	requires: ['TA.model.LocationArea'],
	model: 'TA.model.LocationArea',
	//autoLoad: true,
	pageSize: 2,
	proxy: {
		type: 'ajax',
		actionMethods: {
			read: 'POST'
		},
		url: '../../manager/locationArea',
		/*api: {
			read: '../../manager/locationArea',
			update: '../../manager/locationArea/update',
			create: '../../manager/locationArea/add',
			destroy: '../../manager/locationArea/delete'
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
		console.log('LocationArea store init.');
	},
	initComponent: function() {
		console.log('LocationArea store initComponent.');
		this.callParent(arguments);
	}
	
	
	
});