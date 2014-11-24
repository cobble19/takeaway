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
	        		 if (rec.get('locationAreaPOJO') == null) {
	        			 return "";
	        		 }
	        	   return rec.get('locationAreaPOJO').name ;
	        	 }
	         }, {
	        	 name: 'businessName',
	        	 convert: function (v, rec) {
	        		 if (rec.get('locationBusinessPOJO') == null) {
	        			 return "";
	        		 }
	        	   return rec.get('locationBusinessPOJO').name ;
	        	 }
	         }, {
	        	 name: 'foodSellerTypeName',
	        	 convert: function (v, rec) {
	        		 if (rec.get('foodSellerTypePOJO') == null) {
	        			 return "";
	        		 }
	        	   return rec.get('foodSellerTypePOJO').name ;
	        	 }
	         }
	         ],
	 validators: {
		 name: {type: 'length', min: 2}
	 }
		
});
