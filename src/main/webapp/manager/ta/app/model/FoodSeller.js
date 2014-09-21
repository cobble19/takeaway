Ext.define('TA.model.FoodSeller', {
	extend: 'Ext.data.Model',
	fields: [
	         {
	        	 name: 'foodSellerId', type:'int'
	         }, {
	        	 name: 'name', type: 'string'
	         }, {
	        	 name: 'phone', type: 'string'
	         }, {
	        	 name: 'areaName',
	        	 convert: function (v, rec) {
	        	   return rec.get('locationAreaPOJO').name ;
	        	 }
	         }, {
	        	 name: 'businessName',
	        	 convert: function (v, rec) {
	        	   return rec.get('locationBusinessPOJO').name ;
	        	 }
	         }
	         ],
	 validators: {
		 name: {type: 'length', min: 2}
	 }
		
});
