Ext.define('TA.model.FoodSellerType', {
	extend: 'Ext.data.Model',
	fields: [
	         {
	        	 name: 'foodSellerTypeId', type:'int'
	         }, {
	        	 name: 'name', type: 'string'
	         }, {
	        	 name: 'icon', type: 'string'
	         }
	         ],
	 validators: {
		 name: {type: 'length', min: 2}
	 }
		
});
