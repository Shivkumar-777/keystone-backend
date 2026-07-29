package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.entity.ServiceAttachment;
import com.shivkumar.keystonebackend.entity.ServiceReport;
import com.shivkumar.keystonebackend.enums.FileType;
import com.shivkumar.keystonebackend.repository.ServiceAttachmentRepository;
import com.shivkumar.keystonebackend.repository.ServiceReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceAttachmentService {

    private final ServiceAttachmentRepository attachmentRepository;
    private final ServiceReportRepository serviceReportRepository;
    private final FileStorageService fileStorageService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    /**
     * Upload attachment
     */
    public ServiceAttachment uploadFile(Long serviceReportId,
                                        MultipartFile file) {

        ServiceReport report = serviceReportRepository.findById(serviceReportId)
                .orElseThrow(() -> new RuntimeException("Service Report not found"));

        String storedFileName = fileStorageService.storeFile(file);

        ServiceAttachment attachment = ServiceAttachment.builder()
                .fileName(storedFileName)
                .originalFileName(file.getOriginalFilename())
                .filePath(uploadDir + "/" + storedFileName)
                .fileUrl("/api/attachments/download/" + storedFileName)
                .fileSize(file.getSize())
                .contentType(file.getContentType())
                .fileType(determineFileType(file.getOriginalFilename()))
                .serviceReport(report)
                .build();

        return attachmentRepository.save(attachment);
    }

    /**
     * Get all attachments for a service report
     */
    public List<ServiceAttachment> getAttachments(Long serviceReportId) {

        return attachmentRepository.findByServiceReportId(serviceReportId);
    }

    /**
     * Download file
     */
    public Resource downloadFile(String fileName) {

        return fileStorageService.loadFile(fileName);
    }

    /**
     * Delete attachment
     */
    public void deleteAttachment(Long attachmentId) {

        ServiceAttachment attachment = attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

        fileStorageService.deleteFile(attachment.getFileName());

        attachmentRepository.delete(attachment);
    }

    /**
     * Detect file type
     */
    private FileType determineFileType(String fileName) {

        if (fileName == null) {
            return FileType.OTHER;
        }

        String extension = StringUtils.getFilenameExtension(fileName);

        if (extension == null) {
            return FileType.OTHER;
        }

        return switch (extension.toLowerCase()) {

            case "jpg", "jpeg", "png", "gif", "bmp", "webp"
                    -> FileType.IMAGE;

            case "pdf"
                    -> FileType.PDF;

            case "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt"
                    -> FileType.DOCUMENT;

            default
                    -> FileType.OTHER;
        };
    }
}