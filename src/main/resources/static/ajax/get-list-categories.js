
$(document).ready(function() {
	
	var token = $("meta[name='_csrf']").attr("content"); 
	var header = $("meta[name='_csrf_header']").attr("content");

	getListCategories();
	
	$(document).on('click', '.edit-user-button', function(){ 
		 	var categoryCode=$(this).attr('id');
		 	var category=$(this).attr('category');
            $('#categoryCode').val(categoryCode);
            $('#categoryDescription').val(category);
            });
            
     $("#add-category-button").click(function(event) {
            var formdata = $("#add-category").serializeArray();
            var jsonData = {};
            
            $.each(formdata, function() {
		        if (jsonData[this.name]) {
		           if (!jsonData[this.name].push) {
		               jsonData[this.name] = [jsonData[this.name]];
		           }
		           jsonData[this.name].push(this.value || '');
		       } else {
		           jsonData[this.name] = this.value || '';
		       }
		       });

            $.ajax({
                type: "POST",
                url: "/nerie/e-resources/edit-category",
                data: JSON.stringify(jsonData),
           		contentType: "application/json; charset=utf-8",
                async: false,
                beforeSend: function(xhr) {
		            xhr.setRequestHeader(header, token);
		        },
                success: function(data, textStatus, jqXHR) {
				   $('#success').html('Updated Successfully!');
                   $('#successModal').modal('show');
                   event.preventDefault();
                },
                error: function(data, status, errorThrown) {
					$('#failure').html("Error: "+ data.responseText);
                   $('#failureModal').modal('show');
                   event.preventDefault();
                }
            });
     
    });
    
    $("#close").click(function(event) {
		location.reload();
    });
    
    $("#close-top").click(function(event) {
		location.reload();
    });
    

});


function getListCategories()
{
	 var token = $("meta[name='_csrf']").attr("content"); 
	 var header = $("meta[name='_csrf_header']").attr("content");
	 
	 $.ajax({
                type: "POST",
                url: "/nerie/e-resources/get-list-categories",
           		contentType: "application/json; charset=utf-8",
                async: false,
                beforeSend: function(xhr) {
		            xhr.setRequestHeader(header, token);
		        },
                success: function(res) {
                	var result = JSON.parse(res.split("/*").join("").split("*/").join(""));
                	var catList='<tr>';
                	
		        	for (var i = 0; i < result.length; i++) 
		        	{	
						catList+=
					    	'<td class="align-middle">'+i.toString()+'</td>'+
					        '<td class="align-middle">'+result[i].categoryDescription+'</td>'+
					        '<td>'+             
							'<button type="button" class="btn btn-info edit-user-button edit" data-toggle="modal" data-target="#editmodal" id="'+result[i].categoryCode+'" category="'+result[i].categoryDescription+'">Edit</button>'+
							'</td>'+
					   		'</tr>';
				    
			    			$('#catList').html(catList);
		        	}	
		        	
		        
                },
                error: function(data, status, errorThrown) {
                    alert("Unable to get list of categories");
                }
            });
}


