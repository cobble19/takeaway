Ext.define('TA.model.FoodType', {
	extend: 'Ext.data.Model',
	fields: [
	         {
	        	 name: 'foodTypeId', type:'int'
	         }, {
	        	 name: 'name', type: 'string'
	         }
	         ],
	 validators: {
		 name: {type: 'length', min: 2}
	 }
		
});
