
$(document).ready(function() {
	
	getListUsersActivities();
	
});


function getListUsersActivities()
{
	 var token = $("meta[name='_csrf']").attr("content"); 
	 var header = $("meta[name='_csrf_header']").attr("content");
	 
	 $.ajax({
                type: "POST",
                url: "/nerie/e-resources/get-list-login-activities-res",
           		contentType: "application/json; charset=utf-8",
                async: false,
                beforeSend: function(xhr) {
		            xhr.setRequestHeader(header, token);
		        },
                success: function(res) {
                	var result = JSON.parse(res.split("/*").join("").split("*/").join(""));
                	var userList='';
                	
		        	for (var i = 0; i < result.length; i++) 
		        	{	
						userList+=
					    	'<tr><td class="align-middle">'+i.toString()+'</td>'+
					    	'<td class="align-middle">'+result[i].username+'</td>'+
					    	'<td class="align-middle">'+result[i].ipAddress+'</td>'+
					        '<td class="align-middle" >'+result[i].isSuccess+'</td>'+
					        '<td class="align-middle" >'+result[i].time+'</td>';
					        
					        if(result[i].auditTable!==null && result[i].auditTable.length!==0)
					        {
								var actions='';
								
								for (var j = 0; j < result[i].auditTable.length; j++)
								{
									actions+=result[i].auditTable[j].action+" "+result[i].auditTable[j].entityId +" on Table "+result[i].auditTable[j].entityName+";";
								}
								userList+='<td class="align-middle" >'+actions+'</td>'+
					   				'</tr>';
							}
							else{
								userList+='<td class="align-middle" >'+"No actions taken"+'</td>'+
					   				'</tr>';
							}
			    			$('#userList').html(userList);
		        	}	
		        	
		        
                },
                error: function(data, status, errorThrown) {
                    $('#failure').html("Error: Unable to get list of users"+data.responseText);
	                 $('#failureModal').modal('show');
	                 event.preventDefault();
                }
            });
}
