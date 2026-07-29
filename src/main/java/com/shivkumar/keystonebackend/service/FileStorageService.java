package com.shivkumar.keystonebackend.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private Path uploadPath;

    @PostConstruct
    public void init() {

        try {
            uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory.", e);
        }
    }

    /**
     * Upload file to local storage.
     */
    public String storeFile(MultipartFile file) {

        try {

            String originalFileName =
                    StringUtils.cleanPath(file.getOriginalFilename());

            String extension = "";

            int index = originalFileName.lastIndexOf('.');

            if (index > 0) {
                extension = originalFileName.substring(index);
            }

            String storedFileName =
                    UUID.randomUUID() + extension;

            Path targetLocation =
                    uploadPath.resolve(storedFileName);

            Files.copy(
                    file.getInputStream(),
                    targetLocation,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return storedFileName;

        } catch (IOException e) {
            throw new RuntimeException("File upload failed.", e);
        }
    }

    /**
     * Load file for download.
     */
    public Resource loadFile(String fileName) {

        try {

            Path filePath =
                    uploadPath.resolve(fileName).normalize();

            Resource resource =
                    new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            }

            throw new RuntimeException("File not found.");

        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found.", e);
        }
    }

    /**
     * Delete uploaded file.
     */
    public void deleteFile(String fileName) {

        try {

            Path filePath =
                    uploadPath.resolve(fileName).normalize();

            Files.deleteIfExists(filePath);

        } catch (IOException e) {
            throw new RuntimeException("Unable to delete file.", e);
        }
    }
}