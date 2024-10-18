$(document).ready(function() {
    $.ajax({
        url: '/nerie/e-resources/states/all',
        type: 'GET',
        success: function(data) {
            var stateSelect = $('#stateSelect');
            stateSelect.empty();
            stateSelect.append('<option selected disabled>Select State</option>'); // Default option

            $.each(data, function(index, state) {
                stateSelect.append('<option value="' + state.id + '">' + state.stateName + '</option>');
            });
        },
        error: function(xhr, status, error) {
            console.error('Error fetching states:', error);
        }
    });


    $('#stateSelect').change(function() {
        var stateId = $(this).val(); // Get selected state ID

        if (stateId) {
            $.ajax({
                url: '/nerie/e-resources/languages/by-state/' + stateId,
                type: 'GET',
                success: function(data) {
                    var languageSelect = $('#languageSelect');
                    languageSelect.empty();
                    languageSelect.append('<option selected disabled>Select Language</option>'); // Default option

                    $.each(data, function(index, language) {
                        languageSelect.append('<option value="' + language.id + '">' + language.languageName + '</option>');
                    });
                },
                error: function(xhr, status, error) {
                    console.error('Error fetching languages:', error);
                }
            });
        }
    });

    $.ajax({
                url: '/nerie/e-resources/english-words/all',
                method: 'GET',
                success: function (data) {
                console.log(data);
                    // Store the data in a global variable for reuse
                    window.wordsAndSentences = data;
                    // Initially, show nothing until a type is selected
                    $('#wordSentenceSelect').html('<option selected disabled>Select Word/Sentence</option>');
                },
                error: function () {
                    console.error('Failed to load words and sentences');
                }
    });

    // Event listener for radio button selection
        $('input[name="typeOptions"]').on('change', function () {
            const selectedType = $(this).val(); // Get selected value: 'words' or 'sentences'
            const data = window.wordsAndSentences || [];

            // Clear the dropdown before populating new options
            $('#wordSentenceSelect').html('<option selected disabled>Select Word/Sentence</option>');

            let options = '';

            // Filter based on the selected option
            if (selectedType === 'words') {
                data.forEach(function (item) {
                    // Assuming the item has a category field with a category ID
                    if (item.category && item.category.id === 11) { // Adjust based on your actual data structure
                        options += `<option value="${item.id}">${item.englishWord}</option>`;
                    }
                });
            } else if (selectedType === 'sentences') {
                data.forEach(function (item) {
                    // Assuming the item does not have a category ID of 33
                    if (item.category && item.category.id !== 11) { // Adjust based on your actual data structure
                        options += `<option value="${item.id}">${item.englishWord}</option>`;
                    }
                });
            }

            // Update the dropdown with the filtered data
            $('#wordSentenceSelect').html(options);
        });


        document.addEventListener('DOMContentLoaded', function () {
            const translateForm = document.getElementById('translate-form');

            translateForm.addEventListener('submit', function (event) {
                event.preventDefault(); // Prevent the default form submission

                // Create a FormData object to hold form data
                const formData = new FormData(translateForm);

                // Send AJAX request to the server
                fetch('/nerie/e-resources/translation/create', {
                    method: 'POST',
                    body: formData
                })
                .then(response => {
                    // Check if the response is okay (status in the range 200-299)
                    if (!response.ok) {
                        throw new Error('Network response was not ok: ' + response.statusText);
                    }
                    return response.text(); // Assuming the response is text (could be JSON)
                })
                .then(data => {
                    alert(data); // Show success message
                    translateForm.reset(); // Reset the form after success
                })
                .catch(error => {
                    console.error('There was a problem with the fetch operation:', error);
                    alert('Error: ' + error.message); // Show error message
                });
            });
        });

});