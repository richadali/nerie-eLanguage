
$(document).ready(function() {
	var token = $("meta[name='_csrf']").attr("content"); 
	var header = $("meta[name='_csrf_header']").attr("content");
	var publicKey;

	$("#refresh-button").click(function(event) {
    	getCaptcha();
    });
	
	getCaptcha();
	
	var loginForm = document.getElementById('login-form');
	loginForm.onsubmit = function(){
		
	  getPublicKey(function(d) {
	        publicKey=d;
	  });     
	    
      var en = new JSEncrypt();
      en.setPublicKey(publicKey);
       
      var pwd = $("#password").val();
	  var encryptedPassword = en.encrypt(pwd);
	  
	  $('#password').val(encryptedPassword);
	   console.log('CAPTCHA Value:', $("#captcha").val());
      loginForm.submit();
    
    }
	 
});

function getCaptcha(){
	   $.ajax({
                type: "GET",
                url: "/nerie/e-resources/get-captcha",
           		contentType: "application/json; charset=utf-8",
                async: false,
                success: function(res) {
                   $("#captchaImage").html("<img src='data:image/jpeg;base64,"+res+"' >")
                    }
            });
	
}

