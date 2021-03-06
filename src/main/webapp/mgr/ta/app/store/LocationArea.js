Ext.define('TA.store.LocationArea', {
	extend: 'Ext.data.Store',
	requires: ['TA.model.LocationArea'],
	model: 'TA.model.LocationArea',
//	autoLoad: true,
	pageSize: 20,
	proxy: {
		type: 'ajax',
		actionMethods: {
			read: 'POST'
		},
		url: '../../mgr/locationArea',
		/*api: {
			read: '../../mgr/locationArea',
			update: '../../mgr/locationArea/update',
			create: '../../mgr/locationArea/add',
			destroy: '../../mgr/locationArea/delete'
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
	},
	listeners: {
		beforeload: function( store, operation, eOpts ) {
			console.log('beforeload LocationArea Store');
			var nameField = Ext.ComponentQuery.query('locationarealist toolbar textfield[name=name]');
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
