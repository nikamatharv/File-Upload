document.getElementById("fileUploadForm").addEventListener("submit", function(event) {
    event.preventDefault();  // Prevent form from submitting the traditional way

    let formData = new FormData();
    let fileInput = document.getElementById("file");
    let uploaderInput = document.getElementById("uploader");

    formData.append("file", fileInput.files[0]);
    formData.append("uploader", uploaderInput.value);

    // Send the file to the server
    fetch('uploads', {
        method: 'POST',
        body: formData,
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("message").textContent = `File uploaded successfully: ${data.fileName}`;
    })
    .catch(error => {
        document.getElementById("message").textContent = `Error uploading file: ${error.message}`;
    });
});
