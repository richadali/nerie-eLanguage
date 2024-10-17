$(document).ready(function () {

                    $.ajax({
                        url: "/nerie/e-resources/english-words/all", // Fetch data from the given URL
                        method: "GET",
                        success: function(response) {
                            console.log(response);
                            var tableContent = '';
                            $("#wordsTable tbody").empty(); // Clear existing table body

                            // Iterate through the response and build table rows
                            $.each(response, function(index, word) {
                                tableContent += '<tr>';
                                tableContent += '<td class="text-center">' + (index + 1) + '</td>';
                                tableContent += '<td class="text-center">' + word.englishWord + '</td>';
                                tableContent += '<td class="text-center">' + (word.category ? word.category.categoryName : '-') + '</td>'; // Added check for category

                                // Check if subCategory exists and display "-" if null
                                tableContent += '<td class="text-center">' + (word.subCategory && word.subCategory.subCategoryName ? word.subCategory.subCategoryName : '-') + '</td>';

                                tableContent += '<td class="text-center"><button class="btn btn-sm btn-danger delete-word" data-id="' + word.id + '">Delete</button></td>';
                                tableContent += '</tr>';
                            });

                            // Append the built rows to the table body
                            $("#wordsTable tbody").append(tableContent);

                            // Initialize or reinitialize DataTable
                            $('#wordsTable').DataTable({
                                destroy: true,
                                processing: true,
                                select: true,
                                paging: true,
                                lengthChange: true,
                                searching: true,
                                info: false,
                                responsive: true,
                                autoWidth: false
                            });
                        },
                        error: function(error) {
                            console.error("Error fetching data:", error);
                        }
                    });

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
        if (!englishWord || !categoryId) {
            alert('Please fill in all fields');
            return;
        }

        // AJAX request to save the word/sentence
        $.ajax({
            url: '/nerie/e-resources/english-words/create',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                englishWord: englishWord,
                categoryId: categoryId,
                subCategoryId: subCategoryId
            }),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (response) {
                // Hide the error message (if visible)
                alert('Word/Sentence saved successfully');
                window.location.reload();

            },
            error: function (xhr) {
                console.log('Error saving the word/sentence:', xhr);
                alert('Failed to save the word/sentence');
            }
        });
    });
});
