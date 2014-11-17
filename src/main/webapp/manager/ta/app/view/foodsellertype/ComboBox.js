Ext.define('TA.view.foodsellertype.ComboBox', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.foodsellertypecombobox',
	
	/*autoRender: true,
	autoShow: true,*/
	autoLoadOnValue: true,
	width: 300,
	fieldLabel: '地区',
	labelAlign: 'right',
	name: 'relFoodSellerTypePOJO.foodSellerTypeId',
    store: 'FoodSellerType',
    queryMode: 'remote',
    pageSize: 2,
    displayField: 'name',
    valueField: 'foodSellerTypeId'/*,*/
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