package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.entity.ServiceAttachment;
import com.shivkumar.keystonebackend.service.ServiceAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/attachments")
@RequiredArgsConstructor
public class ServiceAttachmentController {

    private final ServiceAttachmentService attachmentService;

    /**
     * Upload attachment
     */
    @PostMapping("/upload/{serviceReportId}")
    public ResponseEntity<ServiceAttachment> uploadFile(
            @PathVariable Long serviceReportId,
            @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(
                attachmentService.uploadFile(serviceReportId, file)
        );
    }

    /**
     * Get all attachments of a service report
     */
    @GetMapping("/{serviceReportId}")
    public ResponseEntity<List<ServiceAttachment>> getAttachments(
            @PathVariable Long serviceReportId) {

        return ResponseEntity.ok(
                attachmentService.getAttachments(serviceReportId)
        );
    }

    /**
     * Download attachment
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String fileName) {

        Resource resource = attachmentService.downloadFile(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\""
                )
                .body(resource);
    }

    /**
     * Delete attachment
     */
    @DeleteMapping("/{attachmentId}")
    public ResponseEntity<String> deleteAttachment(
            @PathVariable Long attachmentId) {

        attachmentService.deleteAttachment(attachmentId);

        return ResponseEntity.ok("Attachment deleted successfully.");
    }
}