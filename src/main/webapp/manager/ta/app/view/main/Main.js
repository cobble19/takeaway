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
               'Ext.tree.Panel',
               'Ext.tree.View',
               'TA.view.tree.Menu',
               'TA.view.tree.MenuController',
               'TA.store.TreeMenu'
           ],
    uses: [
           'Ext.tree.Panel',
           'Ext.tree.View',
           'TA.view.tree.Menu',
           'TA.view.tree.MenuController',
           'TA.store.TreeMenu'
           ],
    
    controller: 'main',
    viewModel: {
        type: 'main'
    },

    layout: {
        type: 'border'
    },

    items: [{
    	xtype: 'panel',
    	title: 'WaiMai',
    	//html: '&nbsp;',
    	region: 'north',
    	margin: '0 0 3 0'
    },{
        xtype: 'treemenu',
        bind: {
            title: '{treemenu}'
        },
        region: 'west',
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
        items:[{
            title: 'Tab 1',
            html: '<h2>Content appropriate for the current navigation.</h2>'
        }]
    }, {
    	region: 'south',
    	xtype: 'panel',
    	title: 'footer',
    	html: '&copy; publicczhzh@sina.com'
    }]
});
