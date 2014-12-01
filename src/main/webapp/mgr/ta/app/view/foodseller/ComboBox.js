Ext.define('TA.view.foodseller.ComboBox', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.foodsellercombobox',
	
	/*autoRender: true,
	autoShow: true,*/
	autoLoadOnValue: true,
	width: 300,
	fieldLabel: '卖家',
	labelAlign: 'left',
	name: 'foodSellerPOJO.foodSellerId',
    store: 'FoodSeller',
    queryMode: 'remote',
    pageSize: 2,
    displayField: 'name',
    valueField: 'foodSellerId'/*,*/
    //renderTo: Ext.getBody(),
    // Template for the dropdown menu.
    // Note the use of "x-boundlist-item" class,
    // this is required to make the items selectable.
    /*tpl: Ext.create('Ext.XTemplate',
        '<tpl for=".">',
            '<div class="x-boundlist-item">{locationAreaId} - {name}</div>',
        '</tpl>'
    ),
    // template for the content inside text field
    displayTpl: Ext.create('Ext.XTemplate',
        '<tpl for=".">',
            '{locationAreaId} - {name}',
        '</tpl>'
    )*/
});