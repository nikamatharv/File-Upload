package com.colan.FileUpload.controller;

import com.colan.FileUpload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam("uploader") String uploader) {
        try {
            fileUploadService.saveFile(file, uploader);
            return new ResponseEntity<>(new UploadResponse(file.getOriginalFilename(), "File uploaded successfully!"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new UploadResponse("", "Error uploading file: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // A simple response class for sending back success/failure messages
    public static class UploadResponse {
        private String fileName;
        private String message;

        public UploadResponse(String fileName, String message) {
            this.fileName = fileName;
            this.message = message;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
