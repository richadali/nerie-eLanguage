
$(function() {
	var token = $("meta[name='_csrf']").attr("content"); 
	var header = $("meta[name='_csrf_header']").attr("content");
	
    $("#add-category").submit(function(event) {
            
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
                url: "/nerie/e-resources/add-category",
                data: JSON.stringify(jsonData),
           		contentType: "application/json; charset=utf-8",
                async: false,
                beforeSend: function(xhr) {
		            xhr.setRequestHeader(header, token);
		        },
                success: function(data, textStatus, jqXHR) {
                     $('#success').html('Added Successfully!');
                   $('#successModal').modal('show');
                    event.preventDefault();
                },
                error: function(data, status, errorThrown) {
                
					$('#failure').html("Error: "+data.responseText);
                   $('#failureModal').modal('show');
                   event.preventDefault();
                }
            });
     
    });
    
    $("#reset").click(function(event) {
    	$("#add-category")[0].reset();
    });
    	

});

