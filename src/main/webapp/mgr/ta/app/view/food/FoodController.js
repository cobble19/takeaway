/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('TA.view.food.FoodController', {
    extend: 'Ext.app.ViewController',

    requires: [
        'Ext.MessageBox',
        'TA.store.Food'
    ],
    uses: [
           'TA.store.Food'
           ],

    alias: 'controller.food',
    stores: ['Food'],
    views: ['food.List'],
    refs: [{
        ref: 'foodlist',
        selector: 'foodlist'
    }],
    init: function() {
    	console.log('FoodController init.');
    	this.foodlist = this.getView();
    	console.log('get view: ' + this.foodlist);
		
	},
	onAdd: function(button, e) {
		console.log('onAdd ' + button + " " + e);
		var window = Ext.widget('foodedit');
		window.down('textfield:nth-child(0)').hide();
		window.down('button[id="updateBtn"]').hide();
	},
	add: function(button, e) {
		var window = button.up('window');
		var form = button.up('form').getForm();
		var store = Ext.getStore('Food');
		/*if (form.isValid()) {
			
		}*/
		/*var params = {
			name: 'test',
			description: 'descr'
		};*/
		form.submit({
			url: '../../mgr/food/add',
			method: 'POST',
			/*params: params,*/
			/*waitTitle: 'Waiting',
			waitMsg: 'Waiting...',*/
			success: function(form, action) {
				console.log('form.getValues()=' + form + "  " + form.getValues());
				store.insert(0, new TA.model.Food());
				var record = store.first();
				record.set(form.getValues());
				
				var foodSellerName = window.down('form').down('combobox:nth-child(1)').getRawValue();
				if (!record.data.foodSellerPOJO) {
					record.data.foodSellerPOJO = {};
				}
				record.data.foodSellerPOJO.name = foodSellerName;
				record.set('foodSellerName', foodSellerName);
				
				var foodTypeName = window.down('form').down('combobox:nth-child(2)').getRawValue();
				if (!record.data.foodTypePOJO) {
					record.data.foodTypePOJO = {};
				}
				record.data.foodTypePOJO.name = foodTypeName;
				record.set('foodTypeName', foodTypeName);
				
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
		var window = Ext.widget('foodedit');
		var form = window.down('form');
		var f = form.getForm();

		var record = records[0];
		f.loadRecord(record);

		form.down('combobox:nth-child(1)').setValue(record.get('foodSellerPOJO').foodSellerId);
		form.down('combobox:nth-child(2)').setValue(record.get('foodTypePOJO').foodTypeId);
		
		window.down('textfield:nth-child(0)').setReadOnly(true);
		window.down('button[id="addBtn"]').hide();
		window.down('button[id="addBtn"]').hide();
	},
	update: function(button, e) {
		var window = button.up('window');
		var form = button.up('form').getForm();
		var store = Ext.getStore('Food');
		/*if (form.isValid()) {
			
		}*/
		/*var params = {
				name: 'test',
				description: 'descr'
		};*/
		form.submit({
			url: '../../mgr/food/update',
			method: 'POST',
			/*params: params,*/
			/*waitTitle: 'Waiting',
			waitMsg: 'Waiting...',*/
			success: function(form, action) {
				console.log('form.getRecord()=' + form.getRecord().get('name'));
				record = form.getRecord();
				record.set(form.getValues());

				var foodSellerName = window.down('form').down('combobox:nth-child(1)').getRawValue();
				if (!record.data.foodSellerPOJO) {
					record.data.foodSellerPOJO = {};
				}
				record.data.foodSellerPOJO.name = foodSellerName;
				record.set('foodSellerName', foodSellerName);

				var foodTypeName = window.down('form').down('combobox:nth-child(2)').getRawValue();
				if (!record.data.foodTypePOJO) {
					record.data.foodTypePOJO = {};
				}
				record.data.foodTypePOJO.name = foodTypeName;
				record.set('foodTypeName', foodTypeName);
				
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
		var store = Ext.getStore('Food');
        console.log('delete Record, records =' + records.length);
        var ids = [];
        Ext.each(records, function(record, index) {
        	ids.push(record.get('foodId'));
        });
        
        Ext.Ajax.request({
            url: '../../mgr/food/delete',
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
