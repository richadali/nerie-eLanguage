$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        url: '/api/main-categories',
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

    $('#catSelect').on('change', function () {
        let mainCategoryId = $(this).val();

        $.ajax({
            url: `/api/categories/main-category/${mainCategoryId}`,
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

    $('#subSelect').on('change', function () {
        let categoryId = $(this).val();

        $.ajax({
            url: `/api/sub-categories/category/${categoryId}`,
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

    $('form').on('submit', function (e) {
        e.preventDefault();

        let englishWord = $('#wSname').val();
        let categoryId = $('#subSelect').val();
        let subCategoryId = $('#subSubSelect').val();

        if (!englishWord || !categoryId || !subCategoryId) {
            alert('Please fill in all fields');
            return;
        }

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
                xhr.setRequestHeader(header, token);
            },
            success: function (response) {
                alert('Word/Sentence saved successfully');
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
