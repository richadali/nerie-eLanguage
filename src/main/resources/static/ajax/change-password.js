
$(function() {
	
	var token = $("meta[name='_csrf']").attr("content"); 
	var header = $("meta[name='_csrf_header']").attr("content");

    $("#change-password").submit(function(event) {
            var formdata = $("#change-password").serializeArray();
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
		       
		    if(jsonData.newPassword!==jsonData.confirmNewPassword)
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
			    jsonData.newPassword = en.encrypt(jsonData.newPassword);
			    jsonData.oldPassword = en.encrypt(jsonData.oldPassword);
			    jsonData.confirmNewPassword = en.encrypt(jsonData.confirmNewPassword);
	
	            $.ajax({
	                type: "POST",
	                url: "/nerie/e-resources/change-password",
	                data: JSON.stringify(jsonData),
	           		contentType: "application/json; charset=utf-8",
	                async: false,
	                 beforeSend: function(xhr) {
			            xhr.setRequestHeader(header, token);
			        },
	                success: function(data, textStatus, jqXHR) {
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
    	$("#change-password")[0].reset();
    });
    	

});

