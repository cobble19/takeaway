/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('TA.view.tree.MenuController', {
    extend: 'Ext.app.ViewController',

    requires: [
        'Ext.MessageBox',
        'TA.store.TreeMenu'
    ],
    uses: [
           'TA.store.TreeMenu'
           ],

    alias: 'controller.treemenu',
    stores: ['TreeMenu'],
    views: ['tree.Menu'],
    init: function() {
    	console.log('MenuController init.');
    	console.log('get view: ' + this.getView());
		this.control({
			'treemenu': {
				select: this.onSelect,
				afterrender: this.onAfterrender
			}
		});
	},
	onSelect: function(selModel, record, index, options) {
		var pageId = record.get('pageId');
		console.log("selected id= " + record.getId()
				+ ", pageId= " + pageId + ", text= " + record.get('text') + ", index= " + index);
		if (record.get('leaf')) {
			var tabpanel = this.getView().up().down('tabpanel');
			//Ext.getCmp('content-panel').layout.getActiveItem().getStore().load();
			var child = tabpanel.child('#' + pageId);
			if (!!child) { // have existed
				child.tab.show();
				tabpanel.setActiveTab(pageId);
			} else {
				var newTab = tabpanel.add({
					itemId: pageId,
					xtype: pageId,
					title: record.get('text')
				});
				tabpanel.setActiveTab(newTab);
			}
			tabpanel.getActiveTab().getStore().load();
		}
	},
	onAfterrender: function(tree, options) {
		console.log('tree after render');
		tree.getSelectionModel().select(1);
	},

    onClickButton: function () {
        Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    }
});
