$(document).ready(function() {
//	console.log('abc');
	
	$('#progress').dialog({
    	autoOpen: false,
    	modal: true
    });

    $('#sidebar a').click(function() {
		$('#sidebar a').each(function() {
			$(this).removeClass('active');
		})
		$this = $(this);
		$this.addClass('active');
		var contentId = $this.attr('href').substring(1);
		console.log('contentId: ' + contentId);
		$('#uc_content > div').each(function(i, e) {
			$(this).hide();
		})
		$('#' + contentId).show();
	});
	
	$('#sidebar a[href=#profile]').trigger('click');
	
    
} );




 
