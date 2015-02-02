/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('TA.view.foodseller.FoodSellerController', {
    extend: 'Ext.app.ViewController',

    requires: [
        'Ext.MessageBox',
        'TA.store.FoodSeller',
        'TA.model.LocationBusiness'
    ],
    uses: [
           'TA.store.FoodSeller'
           ],

    alias: 'controller.foodseller',
    stores: ['FoodSeller'],
    views: ['foodseller.List'],
    init: function() {
    	console.log('FoodSellerController init.');
	},
	onAdd: function(button, e) {
		console.log('onAdd ' + button + " " + e);
		var window = Ext.widget('foodselleredit');
		window.down('textfield:nth-child(0)').hide();
		window.down('button[id="updateBtn"]').hide();
	},
	add: function(button, e) {
		var window = button.up('window');
		var f = button.up('form').getForm();
		var store = Ext.getStore('FoodSeller');
		/*if (form.isValid()) {
			
		}*/
		/*var params = {
			name: 'test',
			description: 'descr'
		};*/
		f.submit({
			url: '../../mgr/foodSeller/add',
			method: 'POST',
			/*params: params,*/
			/*waitTitle: 'Waiting',
			waitMsg: 'Waiting...',*/
			success: function(form, action) {
				console.log('form.getValues()=' + form + "  " + form.getValues());
				store.insert(0, new TA.model.FoodSeller());
				var record = store.first();
				record.set(form.getValues());
				
				var businessName = window.down('form').down('combobox:nth-child(1)').getRawValue();
				var areaName = window.down('form').down('combobox:nth-child(2)').getRawValue();
				if (!record.data.locationBusinessPOJO) {
					record.data.locationBusinessPOJO = {};
				}
				if (!record.data.locationAreaPOJO) {
					record.data.locationAreaPOJO = {};
				}
				record.data.locationBusinessPOJO.name = businessName;
				record.data.locationAreaPOJO.name = areaName;
				record.set('areaName', areaName);
				record.set('businessName', businessName);
				
				console.log('add success.' + form + " " + Ext.JSON.encode(action.result) + " " + action.response.statusText);
				Ext.toast({
				     html: 'Data Saved',
					 title: 'Success',
					 width: 200,
					 align: 't',
					 region: 'center',
					 anchor: window
				 });
				window.close();
			},
			failure: function(form, action) {
				console.log('add failure');
				Ext.toast({
				     html: 'Data Add failure',
					 title: 'Failure',
					 width: 200,
					 align: 't',
					 region: 'center',
					 anchor: window
				 });
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
		var window = Ext.widget('foodselleredit');
		var form = window.down('form');
		var f = form.getForm();
		var record = records[0];
		f.loadRecord(record);
		form.down('combobox:nth-child(1)').setValue(record.get('locationBusinessPOJO') == null ?  "" : record.get('locationBusinessPOJO').locationBusinessId);
		form.down('textfield[name="relBusinessSellerPOJO.relBusinessSellerId"]').setValue(record.get('relBusinessSellerPOJO') == null ? "" : record.get('relBusinessSellerPOJO').relBusinessSellerId);
		form.down('textfield[name="relBusinessSellerPOJO.foodSellerId"]').setValue(record.get('relBusinessSellerPOJO') == null ? "" : record.get('relBusinessSellerPOJO').foodSellerId);
		
		form.down('combobox:nth-child(2)').setValue(record.get('locationAreaPOJO') == null ? "" : record.get('locationAreaPOJO').locationAreaId);
		form.down('textfield[name="relAreaSellerPOJO.relAreaSellerId"]').setValue(record.get('relAreaSellerPOJO') == null ? "" : record.get('relAreaSellerPOJO').relAreaSellerId);
		form.down('textfield[name="relAreaSellerPOJO.foodSellerId"]').setValue(record.get('relAreaSellerPOJO') == null ? "" : record.get('relAreaSellerPOJO').foodSellerId);
		

		window.down('textfield:nth-child(0)').setReadOnly(true);
		window.down('button[id="addBtn"]').hide();
		window.down('button[id="addBtn"]').hide();
	},
	update: function(button, e) {
		var window = button.up('window');
		var form = window.down('form');
		var f = form.getForm();
		var store = Ext.getStore('FoodSeller');
		console.log('store=' + store);

		
		/*if (form.isValid()) {
			
		}*/
		/*var params = {
				name: 'test',
				description: 'descr'
		};*/
		f.submit({
			url: '../../mgr/foodSeller/update',
			method: 'POST',
			/*params: params,*/
			/*waitTitle: 'Waiting',
			waitMsg: 'Waiting...',*/
			success: function(form, action) {
				console.log('form.getRecord()=' + form.getRecord().get('name'));
				record = form.getRecord();
				record.set(form.getValues());
				
				/*console.log('Fields = ' + record.getFields());
				Ext.each(record.getFields(), function(field, index) {
					console.log(field.getName() + " " + record.get(field.getName()) + " " + index);
				});
				console.log(record.data);*/

				var businessName = window.down('form').down('combobox:nth-child(1)').getRawValue();
				var areaName = window.down('form').down('combobox:nth-child(2)').getRawValue();
				if (record.get('locationBusinessPOJO') == null) {
					var locationBusinessPOJO = {};
					record.set('locationBusinessPOJO', locationBusinessPOJO);
				}
				record.get('locationBusinessPOJO').name = businessName;
				
				if (record.get('locationAreaPOJO') == null) {
					var locationAreaPOJO = {};
					record.set('locationAreaPOJO', locationAreaPOJO);
				}
				record.get('locationAreaPOJO').name = areaName;
				record.set('areaName', areaName);
				record.set('businessName', businessName);
				//record.commit();
				console.log('update success.' + " " + Ext.JSON.encode(action.result) + " " + action.response.statusText);
				Ext.toast({
				     html: 'Data Updated',
					 title: 'Success',
					 width: 200,
					 align: 't',
					 region: 'center',
					 anchor: window
				 });
				window.close();
			},
			failure: function(form, action) {
				console.log('update failure');
				Ext.toast({
				     html: 'Data Update failure',
					 title: 'Failure',
					 width: 200,
					 align: 't',
					 region: 'center',
					 anchor: window
				 });
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
		var store = Ext.getStore('FoodSeller');
        console.log('delete Record, records =' + records.length);
        var ids = [];
        Ext.each(records, function(record, index) {
        	ids.push(record.get('foodSellerId'));
        });
        
        Ext.Ajax.request({
            url: '../../mgr/foodSeller/delete',
            params: {ids:ids},
            method: 'POST',
            success: function(response, opts) {
				store.remove(records);
                var obj = Ext.decode(response.responseText);
                console.dir(obj);
				Ext.toast({
				     html: '成功',
					 title: '删除记录',
					 width: 200,
					 align: 't',
					 region: 'center'
				 });
            },
            failure: function(response, opts) {
                console.log('server-side failure with status code ' + response.status);
				Ext.toast({
				     html: '失败',
					 title: '删除记录',
					 width: 200,
					 align: 't',
					 region: 'center'
				 });
            }
        });
	},
	onSearch: function(button, e) {
		var view = this.getView();
		var store = view.getStore();
		console.log('Count = ' + store.getCount());
		console.log('Total Count = ' + store.getTotalCount());
		name = Ext.ComponentQuery.query('toolbar textfield[name=name]', view)[0].getValue();
		var params = {
				name: name
		};
		if (store.getCount() === 0) {
			params['start'] = 0;
			params['page'] = 1;
		}
		store.load({
			params: params,
			callback: function(records, operation, success) {
				/*console.log(records);
				console.log(operation);*/
				console.log(success);
				Ext.toast({
				     html: success ? '成功' : '失败',
					 title: '查询结束',
					 width: 200,
					 align: 't',
					 region: 'center'
				 });
			}
		});
		console.log('onSearch');
	}
});
