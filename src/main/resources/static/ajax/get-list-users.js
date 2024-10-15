
$(document).ready(function() {	
	
	getListUsers();
	
});


function getListUsers()
{
	var token = $("meta[name='_csrf']").attr("content"); 
	var header = $("meta[name='_csrf_header']").attr("content");
	 $.ajax({
                type: "POST",
                url: "/nerie/e-resources/get-list-users-res",
           		contentType: "application/json; charset=utf-8",
                async: false,
                beforeSend: function(xhr) {
		            xhr.setRequestHeader(header, token);
		        },
                success: function(res) {
					alert(res);
                	var result = JSON.parse(res.split("/*").join("").split("*/").join(""));
                	var userList='<tr>';
                	
		        	for (var i = 0; i < result.length; i++) 
		        	{	
						userList+=
					    	'<td class="align-middle">'+i.toString()+'</td>'+
					        '<td class="align-middle">'+result[i].username+'</td>'+
					        '<td class="align-middle" >'+result[i].firstName+'</td>'+
					        '<td class="align-middle" >'+result[i].middleName+'</td>'+
					        '<td class="align-middle" >'+result[i].lastName+'</td>'+
					        '<td class="align-middle" >'+result[i].dateJoined+'</td>'+
					        '<td class="align-middle" >'+result[i].role+'</td>'+
					        '<td class="align-middle">';
						      if(result[i].enabled)
						      userList+='<span>Active</span>';
						      else
		      				  userList+='<span>Not Active</span>';
		      				  	
		      				userList+='</td>'+
		      				'<td><input type="checkbox" data-toggle="toggle" id="'+result[i].id+'" class="enabled" ';
		      				if(result[i].enabled)
						      userList+='checked'; 
		      				userList+='/></td>'+
		
					        '<td>'+   
					                     
							'<button type="button" class="btn btn-info edit-user-button edit" data-toggle="modal" data-target="#editmodal" id="'+result[i].id+'">Edit</button>'+
							'</td>'+
					   		'</tr>';
				    
			    			$('#userList').html(userList);
		        	}	
		        	
		        
                },
                error: function(data, status, errorThrown) {
                    $('#failure').html("Error: Unable to get list of users"+ data.responseText);
	                 $('#failureModal').modal('show');
	                 event.preventDefault();
                }
            });
}
