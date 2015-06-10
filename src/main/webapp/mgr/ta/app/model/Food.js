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
	         }, {
	        	 name: 'foodSellerName',
	        	 type: 'string',
	        	 convert: function (v, rec) {
	        		 if (rec.get('foodSellerPOJO') == null) {
	        			 return "";
	        		 }
	        	   return rec.get('foodSellerPOJO').name ;
	        	 }
	         }, {
	        	 name: 'foodTypeName',
	        	 type: 'string',
	        	 convert: function (v, rec) {
	        		 if (rec.get('foodTypePOJO') == null) {
	        			 return "";
	        		 }
	        	   return rec.get('foodTypePOJO').name ;
	        	 }
	         }
	         ],
	 validators: {
		 name: {type: 'length', min: 2}
	 }
		
});
