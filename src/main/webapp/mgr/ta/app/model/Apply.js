Ext.define('TA.model.Apply', {
	extend: 'Ext.data.Model',
	fields: [
	         {
	        	 name: 'applyId', type:'int'
	         }, {
	        	 name: 'username', type: 'string'
	         }, {
	        	 name: 'phone', type: 'string'
	         }
	         ],
	 validators: {
		 username: {type: 'length', min: 2}
	 }
		
});
