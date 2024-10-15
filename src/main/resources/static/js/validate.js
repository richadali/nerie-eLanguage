
function checkValidation(){


const invalidElements = document.querySelectorAll(':invalid');
var error;

if (invalidElements.length > 0) {
    // Iterate over each invalid element
    invalidElements.forEach(element => {
		error=+"\n"+
		element.tagName;
        +" "+element.validationMessage;
    });
} 
	return error;
}

