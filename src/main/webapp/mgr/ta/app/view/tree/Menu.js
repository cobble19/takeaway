Ext.define('TA.view.tree.Menu', {
	extend: 'Ext.tree.Panel',
	//xtype: 'treemenu',
	alias: 'widget.treemenu',
	title: 'Tree Menu',
	width: 200,
	requires: ['Ext.tree.Panel',
	           'TA.store.TreeMenu',
	           'TA.view.tree.MenuController',
	           'TA.view.tree.MenuModel'],
	uses: ['Ext.tree.Panel',
           'TA.store.TreeMenu',
           'TA.view.tree.MenuController',
           'TA.view.tree.MenuModel'],
	
	controller: 'treemenu',
	viewmode: {
		type: 'treemenu'
	},
	rootVisible: true,
	store: 'TreeMenu',
	/*store: {root: {
		expanded: true,
		children: [
		            { text: "detention", leaf: true },
		            { text: "homework", expanded: true, children: [
		                { text: "book report", leaf: true },
		                { text: "algebra", leaf: true}
		            ] },
		            { text: "buy lottery tickets", leaf: true }
		        ]
	}},*/
	initComponent: function() {
		/*this.store = {root: {
			expanded: true,
			children: [
			            { text: "detention", leaf: true },
			            { text: "homework", expanded: true, children: [
			                { text: "book report", leaf: true },
			                { text: "algebra", leaf: true}
			            ] },
			            { text: "buy lottery tickets", leaf: true }
			        ]
		}};*/
		console.log("this.store=" + this.store + "  --- " + Ext.StoreMgr.lookup('TA.store.TreeMenu'));
		this.callParent(arguments);
	} 
});