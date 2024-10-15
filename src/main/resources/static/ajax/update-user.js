
$(document).ready(function() {
	var token = $("meta[name='_csrf']").attr("content"); 
	var header = $("meta[name='_csrf_header']").attr("content");
	
    $(document).on('click', '.enabled', function(){ 
		var checkedValue = $(this).prop('checked');
		if(checkedValue===true)
		{
			$("#activate-deactivate").html("Are you sure you want to activate this user?");
		}
		else{
			$("#activate-deactivate").html("Are you sure you want to de-activate this user?")
		}
		var userId = $(this).attr('id');
		var userUpdate={"id":userId,"updateParam":checkedValue};
		userUpdate=JSON.stringify(userUpdate);
		userUpdate="'"+userUpdate+"'";
       	document.getElementById("activate").setAttribute("userUpdate", userUpdate);
		$('#activateModal').modal('show');
 
    });
    
    
    $("#activate").click(function(event) {
		var userUpdate = $(this).attr('userUpdate');
		userUpdate = userUpdate.replace(/'/g, "");

        $.ajax({
                type: "POST",
                url: "/nerie/e-resources/enable-user",
                data: userUpdate,
           		contentType: "application/json; charset=utf-8",
                async: false,
                beforeSend: function(xhr) {
		            xhr.setRequestHeader(header, token);
		        },
                success: function(data, textStatus, jqXHR) {
                    $("#activate-deactivate").html("Updated Successfully");
                },
                error: function(data, status, errorThrown) {
               		 $("#activate-deactivate").html("Error! Unable to process");
                }
            });
    });
    
     $("#close").click(function(event) {
		location.reload();
    });
    
    $("#close-top").click(function(event) {
		location.reload();
    });
    
     $(document).on('click', '.edit-user-button', function(){           
             $("#success-msg").addClass("hidden");
		     $("#error-msg").addClass("hidden");

		 	var userId=$(this).attr('id');
		       $.ajax({
                type: "POST",
                url: "/nerie/e-resources/get-user-by-id",
                data: JSON.stringify({"id": userId}),
           		contentType: "application/json; charset=utf-8",
                async: false,
                beforeSend: function(xhr) {
		            xhr.setRequestHeader(header, token);
		        },
                success: function(res) {

                   var result = JSON.parse(res.split("/*").join("").split("*/").join(""));
                    $('#id').val(result.id);
                    $('#firstName').val(result.firstName);
		            $('#middleName').val(result.middleName);
			        $('#lastName').val(result.lastName);
			        $('#username').val(result.username);
			        $('#office').val(result.userInformation.office);
			        $('#officeAddress').val(result.userInformation.officeAddress);
			        $('#designation').html(result.userInformation.designation);
			        $('#contactNo').html(result.userInformation.contactNo);
					        
                },
                error: function(data, status, errorThrown) {
                   $('#failure').html("Error: "+ data.responseText);
	                $('#failureModal').modal('show');
	                event.preventDefault();
                }
            });

 
   		 });
   		 
    $("#create-user-button").click(function(event) {
            var formdata = $("#create-user").serializeArray();
            var jsonData = {};
            $("#success-msg").addClass("hidden");
		     $("#error-msg").addClass("hidden");
            
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
		       
		   var completeData = Object.assign({}, jsonData,{"role":"ROLE_CM","isSuperuser":false,"isEnabled":true});

            $.ajax({
                type: "POST",
                url: "/nerie/e-resources/update-user",
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
            
            
       
     
    });

});

