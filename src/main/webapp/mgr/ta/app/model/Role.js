Ext.define('TA.model.Role', {
	extend: 'Ext.data.Model',
	fields: [
	         {
	        	 name: 'roleId', type:'int'
	         }, {
	        	 name: 'roleName', type: 'string'
	         }, {
	        	 name: 'privilegeNames', type: 'string'
	         }
	         ],
	 validators: {
		 roleName: {type: 'length', min: 2}
	 }
		
});
