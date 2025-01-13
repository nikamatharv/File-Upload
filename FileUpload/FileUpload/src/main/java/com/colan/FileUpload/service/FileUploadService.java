package com.colan.FileUpload.service;

import com.colan.FileUpload.model.FileUpload;
import com.colan.FileUpload.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {

    @Autowired
    private FileUploadRepository fileUploadRepository;

    private static final String UPLOAD_DIR = "uploads/";
    public FileUploadService() {
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();  // Create directory if it doesn't exist
        }
    }
    public void saveFile(MultipartFile file, String uploader) throws Exception {
        // Your logic to save the file and its metadata
        String filename = file.getOriginalFilename();
        Long fileSize = file.getSize();

        // Save file to server
        File serverFile = new File(UPLOAD_DIR + filename);
        file.transferTo(serverFile);

        // Save file metadata in DB
        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(filename);
        fileUpload.setFileSize(fileSize); // Corrected this line
        fileUpload.setUploader(uploader);
        fileUploadRepository.save(fileUpload);

        // Process the file
        processFile(serverFile);
    }

    private void processFile(File file) throws IOException {
        // Processing logic for CSV or Excel files
    }
}
