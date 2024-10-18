
$(document).ready(function() {
	
	var token = $("meta[name='_csrf']").attr("content"); 
	var header = $("meta[name='_csrf_header']").attr("content");
	
	$.ajax({
                type: "POST",
                url: "/nerie/e-resources/get-user-by-username",
           		contentType: "application/json; charset=utf-8",
                async: false,
                beforeSend: function(xhr) {
		            xhr.setRequestHeader(header, token);
		        },
                success: function(res) {
                	var result = JSON.parse(res.split("/*").join("").split("*/").join(""));
                	var userInfo='<tr>';
                	var otherInfo='<tr>';
  
						userInfo+=
					        '<td class="align-middle">'+result.username+'</td>'+
					        '<td class="align-middle" >'+result.firstName+'</td>'+
					        '<td class="align-middle" >'+result.middleName+'</td>'+
					        '<td class="align-middle" >'+result.lastName+'</td>'+
					        '<td class="align-middle" >'+result.role+'</td>'+
					        '<td class="align-middle">';
						      if(result.enabled)
						      userInfo+='<span>Active</span>';
						      else
		      				  userInfo+='<span>Not Active</span>';
		      				  	
		      				userInfo+='</td>'+ 
					   		'</tr>';
				    
			    			$('#userInfo').html(userInfo);
			    			
			    			
			    			otherInfo+=
					        '<td class="align-middle">'+result.userInformation.office+'</td>'+
					        '<td class="align-middle" >'+result.userInformation.officeAddress+'</td>'+
					        '<td class="align-middle" >'+result.userInformation.designation+'</td>'+
					        '<td class="align-middle" >'+result.userInformation.contactNo+'</td>'+
          
					   		'</tr>';
				    
			    			$('#otherInfo').html(otherInfo);
		        	
		        
                },
                error: function(data, status, errorThrown) {
                     $('#failure').html("Error: Unable to get profile data");
	                   $('#failureModal').modal('show');
	                   event.preventDefault();
                }
            });
	
});

