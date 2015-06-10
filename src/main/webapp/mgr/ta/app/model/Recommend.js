Ext.define('TA.model.Recommend', {
	extend: 'Ext.data.Model',
	fields: [
	         {
	        	 name: 'recommendId', type:'int'
	         }, {
	        	 name: 'title', type: 'string'
	         }, {
	        	 name: 'content', type: 'string'
	         }, {
	        	 name: 'contentDisp',
	        	 type: 'string',
	        	 convert: function (v, rec) {
	        		 var contentDisp = rec.get('content');
	        		 var length = 20;
	        		 if (contentDisp != null) {
	        			 if (contentDisp.length < 20) {
	        				 length = contentDisp.length;
	        			 }
	        			 return contentDisp.replace("<", "").substring(0, length - 1) + " ...";
	        		 }
	        	   return "";
	        	 }
	         }
	         ],
	 validators: {
		 title: {type: 'length', min: 2}
	 }
		
});
