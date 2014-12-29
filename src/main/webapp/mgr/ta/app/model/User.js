Ext.define('TA.model.User', {
	extend: 'Ext.data.Model',
	fields: [
	         {
	        	 name: 'userId', type:'int'
	         }, {
	        	 name: 'username', type: 'string'
	         }, {
	        	 name: 'password', type: 'string'
	         }, {
	        	 name: 'enable', type: 'boolean'
	         }
	         ],
	 validators: {
		 username: {type: 'length', min: 2}
	 }
		
});
