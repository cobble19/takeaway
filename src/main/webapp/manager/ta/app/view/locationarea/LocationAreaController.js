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
		// this button will spit out a different number every time you click it.
        // so firstly we must check if that number is already set:
        if (this.clickCount) {
            // looks like the property is already set, so lets just add 1 to that number and alert the user
            this.clickCount++;
            alert('You have clicked the button "' + this.clickCount + '" times.\n\nTry clicking it again..');
        } else {
            // if the clickCount property is not set, we will set it and alert the user
            this.clickCount = 1;
            alert('You just clicked the button for the first time!\n\nTry pressing it again..');
        }
	}
});
