$(document).ready(function() {
    // Load states into the dropdown
    $.ajax({
        url: '/nerie/e-resources/states/all',
        type: 'GET',
        success: function(data) {
            const stateSelect = $('#stateSelect');
            stateSelect.empty().append('<option selected disabled>Select State</option>');
            $.each(data, function(index, state) {
                stateSelect.append(`<option value="${state.id}">${state.stateName}</option>`);
            });
        },
        error: function(xhr, status, error) {
            console.error('Error fetching states:', error);
        }
    });

    // Load languages based on selected state
    $('#stateSelect').change(function() {
        const stateId = $(this).val();
        if (stateId) {
            $.ajax({
                url: `/nerie/e-resources/languages/by-state/${stateId}`,
                type: 'GET',
                success: function(data) {
                    const languageSelect = $('#languageSelect');
                    languageSelect.empty().append('<option selected disabled>Select Language</option>');
                    $.each(data, function(index, language) {
                        languageSelect.append(`<option value="${language.id}">${language.languageName}</option>`);
                    });
                },
                error: function(xhr, status, error) {
                    console.error('Error fetching languages:', error);
                }
            });
        }
    });

    // Load words/sentences data initially
    $.ajax({
        url: '/nerie/e-resources/english-words/all',
        method: 'GET',
        success: function(data) {
            window.wordsAndSentences = data;
            $('#englishWord').html('<option selected disabled>Select Word/Sentence</option>');
        },
        error: function() {
            console.error('Failed to load words and sentences');
        }
    });

    // Handle selection of Words/Sentences type
    $('input[name="typeOptions"]').on('change', function() {
        const selectedType = $(this).val();
        const data = window.wordsAndSentences || [];
        let options = '';

        // Clear the dropdown and populate with relevant options
        $('#englishWord').html('<option selected disabled>Select Word/Sentence</option>');
        data.forEach(function(item) {
            if ((selectedType === 'words' && item.category.id === 11) ||
                (selectedType === 'sentences' && item.category.id !== 11)) {
                options += `<option value="${item.id}">${item.englishWord}</option>`;
            }
        });
        $('#englishWord').html(options);
    });

    // Form submission handling
    $('#translate-form').on('submit', function(event) {
        event.preventDefault(); // Prevent form from reloading the page

        // Get CSRF token and header name from meta tags
        const csrfToken = $('meta[name="_csrf"]').attr('content');
        const csrfHeaderName = $('meta[name="_csrf_header"]').attr('content');

        // Fetch the user ID before submitting the form
        $.ajax({
            type: "POST",
            url: "/nerie/e-resources/get-user-by-username",
            contentType: "application/json; charset=utf-8",
            async: false,
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeaderName, csrfToken); // Set CSRF token
            },
            success: function(res) {
                console.log('Response:', res); // Log the raw response
                try {
                    const result = JSON.parse(res.split("/*").join("").split("*/").join(""));
                    const userId = result.id; // Assuming your API response has an 'id' field

                    const formData = new FormData(document.getElementById('translate-form'));
                    formData.append('userId', userId); // Add userId to formData

                    // Ensure ID is included, e.g. if needed:
                    const translationId = $('#translationId').val(); // Assuming you have a hidden input for translation ID
                    if (translationId) {
                        formData.append('id', translationId); // Append the ID if it exists
                    }

                    // Log each key-value pair in the FormData
                    for (let [key, value] of formData.entries()) {
                        console.log(key + ': ' + value);
                    }

                    // Use jQuery AJAX to send the POST request with CSRF token and userId
                    $.ajax({
                        url: '/nerie/e-resources/translation/create',
                        type: 'POST',
                        data: formData,
                        processData: false,  // Required for FormData
                        contentType: false,  // Required for FormData
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader(csrfHeaderName, csrfToken); // Set CSRF token in the header
                        },
                        success: function(response) {
                            alert(response); // Display success message
                            $('#translate-form')[0].reset(); // Reset form
                        },
                        error: function(xhr, status, error) {
                            console.error('AJAX error:', error);
                            alert('Error: ' + xhr.responseText || error); // Display error message
                        }
                    });
                } catch (e) {
                    console.error('Failed to parse JSON:', e);
                    $('#failure').html("Error: Response is not valid JSON.");
                    $('#failureModal').modal('show');
                }
            },
            error: function(data, status, errorThrown) {
                $('#failure').html("Error: Unable to get user ID");
                $('#failureModal').modal('show');
                event.preventDefault();
            }
        });
    });
});
