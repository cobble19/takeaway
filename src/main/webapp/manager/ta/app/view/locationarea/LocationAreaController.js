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
		window.down('textfield:nth-child(0)').hide();
		window.down('button[id="updateBtn"]').hide();
	},
	add: function(button, e) {
		var form = button.up('form').getForm();
		/*if (form.isValid()) {
			
		}*/
		/*var params = {
			name: 'test',
			description: 'descr'
		};*/
		form.submit({
			url: '../../manager/locationArea/add',
			method: 'POST',
			/*params: params,*/
			/*waitTitle: 'Waiting',
			waitMsg: 'Waiting...',*/
			success: function(form, action) {
				console.log('add success.' + form + " " + Ext.JSON.encode(action.result) + " " + action.response.statusText);
			},
			failure: function(form, action) {
				console.log('add failure');
			}
		});
	},
	onUpdate: function(button, e) {
		var view = this.getView();
		var selModel = view.getSelectionModel();
		var records = selModel.getSelection();
		if (records == null || records.length == 0) {
			Ext.Msg.show({
			    title:'警告',
			    message: '请选择记录！',
			    buttons: Ext.Msg.YES,
			    icon: Ext.Msg.WARNING,
			    fn: function(btn) {
			        if (btn === 'yes') {
			            console.log('Yes pressed');
			        } else if (btn === 'no') {
			            console.log('No pressed');
			        } else {
			            console.log('Cancel pressed');
			        } 
			    }
			});
			return ;
		}
		
		console.log('onUpdate');
		var window = Ext.widget('locationareaedit');
		var form = window.down('form');
		var f = form.getForm();
		
		f.loadRecord(records[0]);

		window.down('textfield:nth-child(0)').setReadOnly(true);
		window.down('button[id="addBtn"]').hide();
		window.down('button[id="addBtn"]').hide();
	},
	update: function(button, e) {
		var form = button.up('form').getForm();
		/*if (form.isValid()) {
			
		}*/
		/*var params = {
				name: 'test',
				description: 'descr'
		};*/
		form.submit({
			url: '../../manager/locationArea/update',
			method: 'POST',
			/*params: params,*/
			/*waitTitle: 'Waiting',
			waitMsg: 'Waiting...',*/
			success: function(form, action) {
				console.log('update success.');
			},
			failure: function(form, action) {
				console.log('update failure');
			}
		});
	},
	onDelete: function(button, e) {
		var view = this.getView();
		var selModel = view.getSelectionModel();
		var records = selModel.getSelection();
		var deleteRecord = this.deleteRecord;
		if (records == null || records.length == 0) {
			Ext.Msg.show({
			    title:'警告',
			    message: '请选择记录！',
			    buttons: Ext.Msg.YES,
			    icon: Ext.Msg.WARNING,
			    fn: function(btn) {
			        if (btn === 'yes') {
			            console.log('Yes pressed, records =' + records);
			        } else if (btn === 'no') {
			            console.log('No pressed');
			            return ;
			        } else {
			            console.log('Cancel pressed');
			            return;
			        } 
			    }
			});
		} else {
			Ext.Msg.show({
			    title:'提示',
			    message: '确定删除吗？！',
			    buttons: Ext.Msg.YESNOCANCEL,
			    icon: Ext.Msg.WARNING,
			    fn: function(btn) {
			        if (btn === 'yes') {
			            console.log('Yes pressed, records =' + records.length);
			            deleteRecord(records);
			        } else if (btn === 'no') {
			            console.log('No pressed');
			            return ;
			        } else {
			            console.log('Cancel pressed');
			            return;
			        } 
			    }
			});
		}
		console.log('onDelete');
	},
	deleteRecord: function(records) {
        console.log('delete Record, records =' + records.length);
        Ext.Ajax.request({
            url: '../../manager/locationArea/delete',
            /*params: params,*/
            method: 'POST',
            success: function(response, opts) {
                var obj = Ext.decode(response.responseText);
                console.dir(obj);
            },
            failure: function(response, opts) {
                console.log('server-side failure with status code ' + response.status);
            }
        });
	},
	onSearch: function(button, e) {
		var view = this.getView();
		view.getStore().load();
		console.log('onSearch');
	}
});
