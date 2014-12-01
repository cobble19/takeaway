Ext.define('TA.model.LocationArea', {
	extend: 'Ext.data.Model',
	fields: [
	         {
	        	 name: 'locationAreaId', type:'int'
	         }, {
	        	 name: 'name', type: 'string'
	         }, {
	        	 name: 'description', type: 'string'
	         }
	         ],
	 validators: {
		 name: {type: 'length', min: 2}
	 }
		
});
