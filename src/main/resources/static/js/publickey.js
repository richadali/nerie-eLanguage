
function getPublicKey(callback){

		$.ajax({
	                type: "GET",
	                url: "/nerie/e-resources/get-publickey",
	           		contentType: "application/json; charset=utf-8",
	                async: false,
	                success: function(res) {
	                	callback(res);
	                },
	                error: function(data, status, errorThrown) {
	                    alert("Server error! Kindly try again after sometime");
	                }
	     });
	     
}

