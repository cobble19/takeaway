Ext.define('TA.view.ux.MyHtmlEditor', {
 
    alias: 'widget.myhtmleditor',
 
    extend: 'Ext.form.field.HtmlEditor',
 
    requires: ['Ext.form.field.HtmlEditor'],
	
	controller: 'recommend',
 
    createToolbar: function(){
        var me = this;
        me.callParent(arguments);
        me.toolbar.insert(17, {
            xtype: 'button',
            icon: '../../images/pic4htmleditor.png',
            handler: this.showImgUploader,
            scope: this
        });
        return me.toolbar;
    },
 
    showImgUploader: function(){
        var editor = this;
        var imgform = Ext.create('Ext.tab.Panel', {
            region: 'left',
            border: false,
            activeTab: 0,
            items: [{
                title: '本地图片',
                icon: '../../images/computer.png',
                layout: 'fit',
                items: [{
                    xtype: 'form',
                    border: false,
                    bodyPadding:10,
                    items: [{
                        xtype: 'filefield',
                        labelWidth: 70,
                        fieldLabel: '图片',
                        buttonText: '浏览',
                        name: 'pic',
                        allowBlank: false,
                        blankText: '文件不能为空',
                        anchor: '100%'
                    }, {
                        xtype: 'textfield',
                        labelWidth: 70,
                        fieldLabel: '标题',
                        name: 'title',
                        allowBlank: true,
                        anchor: '100%'
                    }, {
                        layout: 'column',
                        border: false,
                        items: [{
                            layout: 'form',
                            columnWidth:.36,
                            xtype: 'numberfield',
                            labelWidth: 70,
                            fieldLabel: '尺寸(宽x高)',
                            name: 'width'
                        },{
                            columnWidth:.05,
                            xtype: 'label',
                            html: ' px'
                        },{
                            layout: 'form',
                            xtype: 'numberfield',
                            columnWidth:.15,
                            name: 'height'
                        },{
                            columnWidth:.05,
                            xtype: 'label',
                            html: ' px'
                        }]
                    }],
                    dockedItems: [{
                        xtype: 'toolbar',
                        dock: 'bottom',
                        layout: { pack: 'end' },
                        items: [{
                            text: '上传',
                            formBind: true,
                            handler: function(obj) {
                                var f = obj.up('form');
                                if (!f.isValid()) {
                                    return;
                                }
                                var vals = f.getForm().getValues();
                                f.submit({
                                    waitMsg: '图片上传中..',
                                    waitTitle: '提示',
                                    url: editor.url, //点击插入执行的方法,将图片保存到服务器上
                                    success: function(form, action) {
                                        var element = document.createElement('img');
                                        element.src = "../../files/" + action.result.file_url;
                                        element.title = vals.title;
                                        if(vals.width > 0 && vals.height > 0){
                                            element.width = vals.width;
                                            element.height = vals.height;
                                        }
                                        if (Ext.isIE) {
                                            editor.insertAtCursor(element.outerHTML);
                                        } else {
                                            var selection = editor.win.getSelection();
                                            if (!selection.isCollapsed) {
                                                selection.deleteFromDocument();
                                            }
                                            selection.getRangeAt(0).insertNode(element);
                                        }
                                        win.hide();
                                    },
                                    failure: function(form, action) {
                                        form.reset();
                                        if (action.failureType == Ext.form.action.Action.SERVER_INVALID){
                                            Ext.MessageBox.show({
                                                title: '错误',
                                                msg: '服务器出现错误'/*action.result.msg*/,
                                                icon: Ext.MessageBox.ERROR,
                                                buttons: Ext.Msg.OK
                                            });
                                        }
                                    }
                                });
                            }
                        }, {
                            text: '取消',
                            handler: function() {
                                win.hide();
                            }
                        }]
                    }]
                }]
            }, {
                title: '远程图片',
                icon: '../../images/link.png',
                layout: 'fit',
                items: [{
                    xtype: 'form',
                    border: false,
                    bodyPadding:10,
                    items: [{
                        xtype: 'textfield',
                        vtype: 'url',
                        labelWidth: 70,
                        fieldLabel: '图片URL',
                        anchor: '100%',
                        name: 'pic',
                        allowBlank: false,
                        blankText: '图片URL不能为空'
                    }, {
                        layout: 'column',
                        border: false,
                        items: [{
                            layout: 'form',
                            columnWidth:.36,
                            xtype: 'numberfield',
                            labelWidth: 70,
                            fieldLabel: '尺寸(宽x高)',
                            name: 'width'
                        },{
                            columnWidth:.05,
                            xtype: 'label',
                            html: ' px'
                        },{
                            layout: 'form',
                            xtype: 'numberfield',
                            columnWidth:.15,
                            name: 'height'
                        },{
                            columnWidth:.05,
                            xtype: 'label',
                            html: ' px'
                        }]
                    }],
                    dockedItems: [{
                        xtype: 'toolbar',
                        dock: 'bottom',
                        layout: { pack: 'end' },
                        border: false,
                        items: [{
                            text: '添加',
                            formBind: true,
                            handler: function(obj) {
                                var f = obj.up('form');
                                if (!f.isValid()) {
                                    return;
                                }
                                var vals = f.getForm().getValues();
                                var pic = vals.pic;
                                var fileext = pic.substring(pic.lastIndexOf('.'), pic.length).toLowerCase();
                                /*if (fileext != '.jpg' && fileext != '.gif' && fileext != '.jpeg' && fileext != '.png' && fileext != '.bmp') {
                                    f.items.items[0].setValue('');
                                    Ext.Msg.show({
                                        title: '提示',
                                        icon: 'ext-mb-error',
                                        width: 300,
                                        msg: '对不起，系统仅支持标准格式的照片，请调整格式后重新上传，谢谢 ！',
                                        buttons: Ext.MessageBox.OK
                                    });
                                    return;
                                }*/
                                var element = document.createElement('img');
                                element.src = pic;
                                if(vals.width>0 && vals.height>0){
                                    element.width = vals.width;
                                    element.height = vals.height;
                                }
                                if(Ext.isIE) {
                                    editor.insertAtCursor(element.outerHTML);
                                }else{
                                    var selection = editor.win.getSelection();
                                    if(!selection.isCollapsed) {
                                        selection.deleteFromDocument();
                                    }
                                    selection.getRangeAt(0).insertNode(element);
                                }
                                win.hide();
                            }
                        }, {
                            text: '取消',
                            handler: function() {
                                win.hide();
                            }
                        }]
                    }]
                }],
            }]
        });
        var win = Ext.create('Ext.Window', {
            title: '插入图片',
            icon: '../../images/pic4htmleditor.png',
            width: 400,
            height: 235,
            plain: true,
            modal: true,
            closeAction: 'hide',
            resizable: true,
            border: false,
            layout: 'fit',
            items: imgform
        });
        win.show(this);
    }
 
});