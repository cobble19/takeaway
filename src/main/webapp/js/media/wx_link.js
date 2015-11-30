$(document).ready(function() {
	
	$('#uploadBtn').click(function() {
		var formData;
	    formData = new FormData();
	    formData.append('pic', $('input[name=pic]').get(0).files[0]);
	
	    $.ajax({
	        url: '../../htmleditor/pic/add',
	        contentType:"multipart/form-data",
	        data: formData,
	        processData: false,
	        type: 'POST',
	        contentType: false, // tell jQuery not to set contentType
	        success : function(data) {
	            alert('上传成功');
	            $('#imgSrc').val(data.file_url);
	        }
	    });
    
	})
	
	
	$('#wxLinkForm').validate();
	$('#addBtn').click(function() {
		if ($('#wxLinkForm').valid()) {
			$('#wxLinkForm').submit();
		}
		return ;
	});
})


