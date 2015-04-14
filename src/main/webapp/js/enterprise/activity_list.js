/* Formatting function for row details - modify as you need */
function format ( d ) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<td>Full name:</td>'+
            '<td>'+d.title+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Extension number:</td>'+
            '<td>'+d.title+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Extra info:</td>'+
            '<td>And any further details here (images etc)...</td>'+
        '</tr>'+
    '</table>';
}

var activitySearch = function(table) {
	$.ajax({
		"url" : "../../web/enterprise/activity/list",
		"type" : "POST",
		"headers" : {
			"Content-Type" : "application/json"
		},
		"dataType" : 'json',
		/*"data": JSON.stringify({
            title: $("#title").val()
        }),*/
        success: function(data, textStatus, jqXHR ) {
        	console.log("data = " + data);
        	console.log("t= " + table.page.info().pages);
        	table.clear();
        	table.rows.add(data.data).draw()
            .nodes()
            .to$()
            .addClass( 'new' );
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert('Load Error!');
        },
        complete: function(jqXHR, textStatus) {
        	console.log('Ajax complete.');
        }
	});
}
 
$(document).ready(function() {
	console.log('abc');
    var table = $('#activityList').DataTable( {
		"initComplete": function () {
            var api = this.api();
           /* api.$('td').click( function () {
                // api.search( this.innerHTML ).draw();
            	api.ajax.reload();
            	api.draw();
            } );*/
			//$("#matsTeleFraudForm").appendTo($("mats-form"));
            
            console.log("page.len = " + api.page.len());
            var info = api.page.info();
            console.log('Currently showing page '+(info.page+1)+' of '+info.pages+' pages.');
            
        },
        language: {
        	search: "Filter: "
        },
		"dom" : '<"top"fl<"clear">>rt<"bottom"ip<"clear">>',
		"lengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
		/*"columnDefs" : [ {
			"targets" : 0,
			"searchable" : false,
			"orderable" : false
		}, {
			"targets" : [1, 2, 3, 4, 8, 9, 10, 14],
			"visible": false
		}, {
			"targets" : 15,
			"render" : function(data, type, full, meta) {
				var date = new Date();
				date.setTime(data);
				return date.format('Y-m-d H:i:s');
			}
		}, {
			"targets" : 11,
			"render" : function(data, type, full, meta) {
				return data === 0 ? 'BTS': 'Prod';
			}
		} ],*/
        "columns": [
            {
                "className":      'details-control',
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            },
            { "data": "activityId" },
            { "data": "title" },
            { "data": "content" }
        ],
        "order": [[1, 'asc']]
    } );
    
    activitySearch(table);
     
    // Add event listener for opening and closing details
    $('#activityList tbody').on('click', 'td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = table.row( tr );
 
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( format(row.data()) ).show();
            tr.addClass('shown');
        }
    } );
} );