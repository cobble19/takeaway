$(function() {
	/*getParam = getQueryStringRegExp = function(name) {
		var reg = new RegExp("(^|\\?|&)"+ name + "=([^&]*)(\\s|&|$)", "i");  
		if (reg.test(location.href)) {
			array = reg.exec(location.href);
			console.log(array);
			ret = ('' + array[2].toString()) | '';
			ret = decodeURI(ret);
			console.log("getParam ret = " + decodeURI(ret));
			return ret;
		}
		return "";
	};*/
	
	checkAlphaNum = function(name) {
		var reg = new RegExp("^[a-zA-Z\d]+$");  
		if (reg.test(name)) {
			return true;
		}
		return false;
	};
	
	/*$personForm = $('#person form');
	console.log('person form');
	$personForm.submit(function() {
		console.log('$personForm form');
		var username = $personForm.find('#username').val();
		if (checkAlphaNum(username)) {
			return true;
		} else {
			alert("用户名只能为字母数字");
			retrun false;
		}
		return false;
	})*/
	/*$personForm.find('#registerBtn').click(function() {
		var username = $personForm.find('#username').val();
		console.log(username);
		$personForm.submit();
		return false;
	})*/
	
})