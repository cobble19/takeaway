Ext.define('TA.view.locationbusiness.ComboBox', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.locationbusinesscombobox',
	
	width: 300,
	fieldLabel: '商业区',
	labelAlign: 'right',
	name: 'relBusinessSellerPOJO.locationBusinessId',
    store: 'LocationBusiness',
    queryMode: 'remote',
    displayField: 'name',
    valueField: 'locationBusinessId'/*,*/
    //renderTo: Ext.getBody(),
    // Template for the dropdown menu.
    // Note the use of "x-boundlist-item" class,
    // this is required to make the items selectable.
    /*tpl: Ext.create('Ext.XTemplate',
        '<tpl for=".">',
            '<div class="x-boundlist-item">{locationBusinessId} - {name}</div>',
        '</tpl>'
    ),
    // template for the content inside text field
    displayTpl: Ext.create('Ext.XTemplate',
        '<tpl for=".">',
            '{locationBusinessId} - {name}',
        '</tpl>'
    )*/
});