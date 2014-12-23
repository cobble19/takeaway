/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('TA.view.main.Main', {
    extend: 'Ext.container.Container',

    xtype: 'app-main',
    requires: [
               'Ext.window.Toast',
               'Ext.tree.Panel',
               'Ext.tree.View',
               'Ext.grid.column.RowNumberer',
               'Ext.form.Panel',
               'TA.view.tree.Menu',
               'TA.view.tree.MenuController',
               'TA.store.TreeMenu',
               'TA.view.locationarea.List',
               'TA.view.foodsellertype.List',
               'TA.view.foodtype.List'
           ],
    uses: [
           'Ext.tree.Panel',
           'Ext.tree.View',
           'TA.view.tree.Menu',
           'TA.view.tree.MenuController',
           'TA.store.TreeMenu',
           'TA.view.locationarea.List',
           'TA.view.locationbusiness.List',
           'TA.view.foodseller.List',
           'TA.view.food.List',
           'TA.view.foodsellertype.List'
           ],
    
    controller: 'main',
    viewModel: {
        type: 'main'
    },

    layout: {
        type: 'border'
    },

    items: [{
    	region: 'north',
    	xtype: 'panel',
    	title: 'WaiMai',
    	//html: '&nbsp;',
    	margin: '0 0 3 0'
    },{
        region: 'west',
        xtype: 'treemenu',
        bind: {
            title: '{treemenu}'
        },
        rootVisible: true,
        //html: '<ul><li>This area is commonly used for navigation, for example, using a "tree" component.</li></ul>',
        width: 250,
        split: true,
        collapsible: true,
        animCollapse: true/*,
        tbar: [{
            text: 'Button',
            handler: 'onClickButton'
        }]*//*,
        store: 'TreeMenu'*/
    },{
        region: 'center',
        xtype: 'tabpanel',
        defaults: {
        	closable: true,
            closeAction: 'hide'
        },
        activeTab: 1,
        items:[
	        {
	            title: 'Welcome',
	            html: '<h2>Welcome Takeaway.</h2>',
	            itemId: 'welcome',
            	closable: false
	        }/*, {
	        	xtype: 'locationarealist',
	        	itemId: 'locationarealist',
	            title: 'Location Area',
	            //html: '<h2>Content appropriate for the current navigation.</h2>',
	        }, {
	        	xtype: 'locationbusinesslist',
	        	itemId: 'locationbusinesslist',
	            title: 'Location Business',
	            //html: '<h2>Content appropriate for the current navigation.</h2>',
	        }*/
        ]
    }, {
    	region: 'south',
    	xtype: 'panel',
    	title: '&copy; publicczhzh@sina.com',
    	titleAlign: 'center',
    	titleCollapse: true
    	//html: '&copy; publicczhzh@sina.com'
    }]
});
