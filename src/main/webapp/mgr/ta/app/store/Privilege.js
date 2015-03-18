Ext.define('TA.store.Privilege', {
	extend: 'Ext.data.Store',
	requires: ['TA.model.Privilege'],
	model: 'TA.model.Privilege',
//	autoLoad: true,
	pageSize: 20,
	proxy: {
		type: 'ajax',
		actionMethods: {
			read: 'POST'
		},
		url: '../../mgr/privilege',
		/*api: {
			read: '../../mgr/privilege',
			update: '../../mgr/privilege/update',
			create: '../../mgr/privilege/add',
			destroy: '../../mgr/privilege/delete'
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
		console.log('Privilege store init.');
	},
	initComponent: function() {
		console.log('Privilege store initComponent.');
		this.callParent(arguments);
	},
	listeners: {
		beforeload: function( store, operation, eOpts ) {
			console.log('beforeload Privilege Store');
			var nameField = Ext.ComponentQuery.query('privilegelist toolbar textfield[name=privilegeName]');
			if (nameField == null || nameField.length == 0) {
				return ;
			}
			var name = nameField[0].getValue();
			extraParams = {
				privilegeName: name
			};
			Ext.apply(store.proxy.extraParams, extraParams);
		}
	}
	
	
	
});
