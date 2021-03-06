/**
 * This class is the view model for the Main view of the application.
 */
Ext.define('TA.view.main.MainModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.main',

    data: {
        name: 'TA',
        tips: '<span style="color: #CCC">请使用<span style="color: #F00">ctrl</span>和<span style="color: #F00">shift</span>来进行选择和多选</span>'
    },
    formulas: {
    	username: function(get) {
    		/*var username = Ext.get('username').dom.value;*/
    		var username = "";
    		Ext.Ajax.request({
    			async: false,
    		    url: '../../web/currentUser',
    		    method: 'GET',
    		    success: function(response){
    		        var text = response.responseText;
    		        var obj = Ext.JSON.decode(text);
    		        // process server response here
    		        username = obj.username;
    	    		console.log('success User name: ' + username)
    		    }
    		});
    		console.log('User name: ' + username)
    		return username;
    	}
    }

    //TODO - add data, formulas and/or methods to support your view
});