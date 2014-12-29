Ext.define('TA.model.Privilege', {
	extend: 'Ext.data.Model',
	fields: [
	         {
	        	 name: 'privilegeId', type:'int'
	         }, {
	        	 name: 'privilegeName', type: 'string'
	         }, {
	        	 name: 'url', type: 'string'
	         }
	         ],
	 validators: {
		 privilegeName: {type: 'length', min: 2}
	 }
		
});
