$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    // Fetch and populate Main Category dropdown
    $.ajax({
        url: '/api/main-categories', // Assuming you have an API to fetch Main Categories
        method: 'GET',
        success: function (data) {
            let mainCategoryDropdown = $('#catSelect');
            data.forEach(function (category) {
                mainCategoryDropdown.append(new Option(category.categoryName, category.id));
            });
        },
        error: function (xhr) {
            console.log('Error fetching main categories:', xhr);
        }
    });

    // On change of Main Category, fetch Categories by Main Category ID
    $('#catSelect').on('change', function () {
        let mainCategoryId = $(this).val();

        $.ajax({
            url: `/api/categories/main-category/${mainCategoryId}`, // Updated endpoint
            method: 'GET',
            success: function (data) {
                let categoryDropdown = $('#subSelect');
                categoryDropdown.empty().append('<option selected disabled>Select Category</option>');
                data.forEach(function (category) {
                    categoryDropdown.append(new Option(category.categoryName, category.id));
                });
            },
            error: function (xhr) {
                console.log('Error fetching categories:', xhr);
            }
        });
    });

    // On change of Category, fetch Sub Categories
    $('#subSelect').on('change', function () {
        let categoryId = $(this).val();

        $.ajax({
            url: `/api/sub-categories/category/${categoryId}`, // Updated to match new API path
            method: 'GET',
            success: function (data) {
                let subCategoryDropdown = $('#subSubSelect');
                subCategoryDropdown.empty().append('<option selected disabled>Select Sub Category</option>');
                data.forEach(function (subCategory) {
                    subCategoryDropdown.append(new Option(subCategory.subCategoryName, subCategory.id));
                });
            },
            error: function (xhr) {
                console.log('Error fetching subcategories:', xhr);
            }
        });
    });

    // Submit form and save English word/sentence
    $('form').on('submit', function (e) {
        e.preventDefault(); // Prevent the default form submission

        // Get the values from the form
        let englishWord = $('#wSname').val();
        let categoryId = $('#subSelect').val();
        let subCategoryId = $('#subSubSelect').val();

        // Validate that the user has selected the required fields
        if (!englishWord || !categoryId || !subCategoryId) {
            alert('Please fill in all fields');
            return;
        }

        // AJAX request to save the word/sentence
        $.ajax({
            url: '/nerie/e-resources/english-words/create',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                EnglishWord: englishWord,
                categoryId: categoryId,
                subCategoryId: subCategoryId
            }),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token); // Set the CSRF token in the request header
            },
            success: function (response) {
                alert('Word/Sentence saved successfully');
                // Optionally, clear the form or reset dropdowns
                $('#wSname').val('');
                $('#subSelect').val('').change();
                $('#subSubSelect').val('');
            },
            error: function (xhr) {
                console.log('Error saving the word/sentence:', xhr);
                alert('Failed to save the word/sentence');
            }
        });
    });
});
