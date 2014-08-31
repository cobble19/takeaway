/**
 * The main application class. An instance of this class is created by app.js when it calls
 * Ext.application(). This is the ideal place to handle application launch and initialization
 * details.
 */
Ext.define('TA.Application', {
    extend: 'Ext.app.Application',
    
    name: 'TA',

    stores: [
        'TreeMenu'
    ],
    
    launch: function () {
        // TODO - Launch the application
    	//开启悬浮提示功能
        Ext.QuickTips.init();
        //开启动态加载
        Ext.Loader.setConfig({
            enabled: true
        });
    }
});
