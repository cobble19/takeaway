Ext.define('TA.model.Recommend', {
	extend: 'Ext.data.Model',
	fields: [
	         {
	        	 name: 'recommendId', type:'int'
	         }, {
	        	 name: 'title', type: 'string'
	         }, {
	        	 name: 'content', type: 'string'
	         }
	         ],
	 validators: {
		 title: {type: 'length', min: 2}
	 }
		
});
