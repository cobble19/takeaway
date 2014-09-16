Ext.define('TA.model.FoodSeller', {
	extend: 'Ext.data.Model',
	fields: [
	         {
	        	 name: 'foodSellerId', type:'int'
	         }, {
	        	 name: 'name', type: 'string'
	         }, {
	        	 name: 'phone', type: 'string'
	         }
	         ],
	 validators: {
		 name: {type: 'length', min: 2}
	 }
		
});
