/**
 * The main application class. An instance of this class is created by app.js when it calls
 * Ext.application(). This is the ideal place to handle application launch and initialization
 * details.
 */
Ext.define('TA.Application', {
    extend: 'Ext.app.Application',
    
    name: 'TA',

    stores: [
        'TreeMenu',
        'LocationArea',
        'LocationBusiness',
        'FoodSeller',
        'Food',
        'FoodSellerType',
        'FoodType',
        'User',
        'Role',
        'Privilege',
        'Recommend',
        'Activity',
        'Apply'
    ],
    
    launch: function () {
        // TODO - Launch the application
    	//开启悬浮提示功能
        Ext.QuickTips.init();
        //开启/关闭动态加载
        Ext.Loader.setConfig({
            enabled: true
        });
		console.log('Ext Ajax');

		Ext.Ajax.on('beforerequest', this.testFunB, this);
		Ext.Ajax.on('requestcomplete', this.testFunC, this);
		Ext.Ajax.on('requestexception', this.testFunE, this);
    },
	getBase: function() {
		console.log('pathname = ' + location.pathname);
		var base = document.getElementsByTagName('base')[0];
		console.log(base);
		if (base && base.href && (base.href.length > 0)) {
			base = base.href;
			console.log(base);
		} else {
			base = document.URL;
			console.log(base);
		}
		base = base.substr(0, base.indexOf("/", base.indexOf("/", base.indexOf("//") + 2) + 1));
		console.log(base);
		return base;
	},
	testFunB : function(conn, opts) {
		console.log('testFunB... ajax...');
	},
	testFunC : function(conn, response, opts) {
		console.log('testFunC... ajax...');
		
		var ajaxText = response.responseText;
		/*ajaxText = Ext.decode(ajaxText);*/
		/*if (ajaxText.status == false) {
			console.log('ajaxText.status=' + ajaxText.status);
			alert("权限不足或过期。请重新登录。");
			window.location.href = Ext.get('contextPath').dom.value + "/manage/login.jsp";  
		}*/
		if (ajaxText.indexOf('j_spring_security_check') != -1) {
			console.log('Found j_spring_security_check in response');
			alert("权限不足或过期。请重新登录。");
			window.location.href = this.getBase() + "/spring_security_login";  
		}
	},
	testFunE : function(conn, response, opts) {
		console.log('testFunE... ajax...');
		Ext.toast({
		     html: '发生了异常',
			 title: '异常',
			 width: 200,
			 align: 't',
			 region: 'center',
			 anchor: window
		 });
		if (!!response.status && response.status == 403) {
//			alert("权限Forbidden。请重新登录。");
			window.location.href = this.getBase() + "/spring_security_login";  
		}
	}
});
