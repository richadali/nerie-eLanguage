// JavaScript to handle the PDF URL on button click
  document.addEventListener('DOMContentLoaded', function () {
    $('#pdfModal').on('show.bs.modal', function (event) {
      var button = $(event.relatedTarget); // Button that triggered the modal
      var pdfUrl = button.data('pdf-url'); // Extract info from data-* attributes
      var modal = $(this);
      modal.find('#pdfViewer').attr('src', pdfUrl); // Set the PDF source in the iframe
    });

    // Clear the iframe when the modal is closed
    $('#pdfModal').on('hidden.bs.modal', function () {
      $(this).find('#pdfViewer').attr('src', '');
    });
  });