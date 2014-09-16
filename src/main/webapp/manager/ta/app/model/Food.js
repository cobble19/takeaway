Ext.define('TA.model.Food', {
	extend: 'Ext.data.Model',
	fields: [
	         {
	        	 name: 'foodId', type:'int'
	         }, {
	        	 name: 'foodSellerId', type:'int'
	         }, {
	        	 name: 'name', type: 'string'
	         }, {
	        	 name: 'unitPrice', type: 'string'
	         }
	         ],
	 validators: {
		 name: {type: 'length', min: 2}
	 }
		
});
