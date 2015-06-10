Ext.define('TA.model.LocationBusiness', {
	extend: 'Ext.data.Model',
	fields: [
	         {
	        	 name: 'locationBusinessId', type:'int'
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
