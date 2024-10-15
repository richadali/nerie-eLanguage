
$(function() {
	
	var token = $("meta[name='_csrf']").attr("content"); 
	var header = $("meta[name='_csrf_header']").attr("content");

    $("#create-user").submit(function(event) {
		
            var formdata = $("#create-user").serializeArray();
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
		       
			if(jsonData.password!==jsonData.confirmPassword)
			{
				$('#failure').html("Error: Password does not matched!");
                $('#failureModal').modal('show');
                event.preventDefault();
			}

				
			else{
				var publicKey;
				
				getPublicKey(function(d) {
		       	 publicKey=d;
		  		});   
		  		     
		    	var en = new JSEncrypt();
		        en.setPublicKey(publicKey);
			    jsonData.password = en.encrypt(jsonData.password);
				
				var completeData = Object.assign({}, jsonData,{"isSuperuser":false,"isEnabled":true});
			   
	            $.ajax({
	                type: "POST",
	                url: "/nerie/e-resources/register",
	                data: JSON.stringify(completeData),
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
					   $('#failure').html("Error: "+ data.responseText);
	                   $('#failureModal').modal('show');
	                   event.preventDefault();
	                }
	            });
            }
     
    });
    
    $("#reset").click(function(event) {
    	$("#add-word")[0].reset();
    });
    	

});

