Ext.define('TA.store.TreeMenu', {
	extend: 'Ext.data.TreeStore',
	//autoLoad: true,
	storeId: 'TreeMenu',
	/*root: {
		expanded: true,
		children: [
		            { text: "detention", leaf: true },
		            { text: "homework", expanded: true, children: [
		                { text: "book report", leaf: true },
		                { text: "algebra", leaf: true}
		            ] },
		            { text: "buy lottery tickets", leaf: true }
		        ]
	},*/
	root: {
		expanded: true
	},
    rootVisible: true,
	proxy: {
		type: 'ajax',
		url: '../data/menuTree.json'
	},
	init: function() {
		console.log('TreeMenu init.');
	},
	initComponent: function() {
		console.log('TreeMenu initComponent.');
		this.callParent(arguments);
	}
	
	
	
});