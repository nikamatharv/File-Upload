package com.colan.FileUpload.repository; // Correct package

import org.springframework.data.jpa.repository.JpaRepository;
import com.colan.FileUpload.model.FileUpload;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
}
