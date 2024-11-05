$(document).ready(function() {
    const translationsTableBody = $('#translationsTable tbody');
    $('#translationsTable').hide();
    const csrfToken = $('meta[name="_csrf"]').attr('content');
    const csrfHeader = $('meta[name="_csrf_header"]').attr('content');

    $.ajaxSetup({
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        }
    });

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
            alert('Failed to load states. Please try again later.');
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
                    alert('Failed to load languages. Please try again later.');
                }
            });
        }
    });

    // Fetch translations on button click
    $('#fetchTranslations').click(function() {
        const languageId = $('#languageSelect').val(); // Get the selected language ID

        if (languageId) {
            const url = `/nerie/e-resources/translation/fetch-by-language/${languageId}`;
            translationsTableBody.empty(); // Clear existing data
            $('#translationsTable').hide(); // Hide the table initially
            $('#loader').show(); // Show loader

            $.ajax({
                url: url,
                type: 'GET',
                success: function(data) {
                    $('#loader').hide(); // Hide loader

                    // Check if there are any translations
                    if (data.length === 0) {
                        translationsTableBody.append('<tr><td colspan="5" class="text-center">No Data Present</td></tr>');
                        $('#translationsTable').show(); // Show the table even if no data
                        return;
                    }

                    // Populate the DataTable with the received data
                    $.each(data, function(index, translation) {
                        translationsTableBody.append(`
                            <tr>
                                <td>${translation.id}</td>
                                <td>${translation.englishWordsSentences.englishWord}</td>
                                <td>${translation.translated_word}</td>
                                <td>
                                    ${translation.audio ? `<button class="btn btn-primary play-audio" data-audio="${translation.audio}">Play Audio</button>` : 'No Audio'}
                                </td>
                                <td>
                                    ${translation.video ? `<button class="btn btn-primary play-video" data-video="${translation.video}">Play Video</button>` : 'No Video'}
                                </td>
                            </tr>
                        `);
                    });

                    // Initialize or refresh DataTable
                    $('#translationsTable').DataTable().destroy(); // Destroy existing instance
                    $('#translationsTable').DataTable(); // Reinitialize DataTable
                    $('#translationsTable').show(); // Show the table after fetching translations

                    // Event listener for audio buttons
                    $('.play-audio').click(function() {
                        const audioSrc = $(this).data('audio');
                        const audioPlayer = new Audio(`D:/Development Work/NIC Shillong/nerie-elanguage/audio_video/${audioSrc}`);
                        audioPlayer.play();
                    });

                    // Event listener for video buttons
                    $('.play-video').click(function() {
                        const videoSrc = $(this).data('video');
                        const videoPlayer = document.createElement('video');
                        videoPlayer.src = `D:/Development Work/NIC Shillong/nerie-elanguage/audio_video/${videoSrc}`;
                        videoPlayer.controls = true;
                        document.body.appendChild(videoPlayer);
                        videoPlayer.play();
                    });
                },
                error: function(xhr, status, error) {
                    $('#loader').hide(); // Hide loader on error
                    console.error('Error fetching translations:', error);
                    alert('Error fetching translations: ' + xhr.responseText); // Display the error
                }
            });
        } else {
            alert('Please select a language.');
        }
    });
});
