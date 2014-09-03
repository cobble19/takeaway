/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('TA.view.locationarea.LocationAreaController', {
    extend: 'Ext.app.ViewController',

    requires: [
        'Ext.MessageBox',
        'TA.store.LocationArea'
    ],
    uses: [
           'TA.store.LocationArea'
           ],

    alias: 'controller.locationarea',
    stores: ['LocationArea'],
    views: ['locationarea.List'],
    init: function() {
    	console.log('LocationAreaController init.');
    	console.log('get view: ' + this.getView());
		
	},
	onAdd: function(button, e) {
		console.log('onAdd' + button + " " + e);
		var window = Ext.widget('locationareaedit');
	},
	onUpdate: function(button, e) {
		console.log('onUpdate');
	},
	onDelete: function(button, e) {
		console.log('onDelete');
	},
	onSearch: function(button, e) {
		console.log('onSearch');
	}
});
