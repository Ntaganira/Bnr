/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : DocumentServiceImpl.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Handles document uploads and versioning
 * --------------------------------------------------------------------
 */

package rw.bnr.heritier.document.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import rw.bnr.heritier.application.model.LicenseApplication;
import rw.bnr.heritier.application.repository.LicenseApplicationRepository;
import rw.bnr.heritier.common.security.SecurityUtils;
import rw.bnr.heritier.document.model.ApplicationDocument;
import rw.bnr.heritier.document.repository.ApplicationDocumentRepository;
import rw.bnr.heritier.document.service.DocumentService;
import rw.bnr.heritier.exception.BusinessException;
import rw.bnr.heritier.user.model.User;
import rw.bnr.heritier.user.repository.UserRepository;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private final ApplicationDocumentRepository repository;
    private final LicenseApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    @Value("${app.upload-dir}")
    private String uploadDir;

    @Override
    public ApplicationDocument upload(
            Long applicationId,
            MultipartFile file
    ) {

        validateFile(file);

        LicenseApplication application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new BusinessException(
                                        "Application not found"
                                ));

        User currentUser = getCurrentUser();

        int nextVersion = getNextVersion(
                applicationId,
                file.getOriginalFilename()
        );

        String storedFileName =
                UUID.randomUUID() + "_"
                        + file.getOriginalFilename();

        saveFile(file, storedFileName);

        ApplicationDocument document =
                ApplicationDocument.builder()
                        .application(application)
                        .uploadedBy(currentUser)
                        .originalFileName(
                                file.getOriginalFilename()
                        )
                        .storedFileName(storedFileName)
                        .contentType(file.getContentType())
                        .fileSize(file.getSize())
                        .version(nextVersion)
                        .uploadedAt(LocalDateTime.now())
                        .build();

        return repository.save(document);
    }

    private void validateFile(MultipartFile file) {

        if (file.isEmpty()) {

            throw new BusinessException(
                    "Uploaded file cannot be empty"
            );
        }

        long maxSize = 5 * 1024 * 1024;

        if (file.getSize() > maxSize) {

            throw new BusinessException(
                    "File size exceeds 5MB limit"
            );
        }
    }

    private int getNextVersion(
            Long applicationId,
            String fileName
    ) {

        return repository
                .findTopByApplicationIdAndOriginalFileNameOrderByVersionDesc(
                        applicationId,
                        fileName
                )
                .map(doc -> doc.getVersion() + 1)
                .orElse(1);
    }

    private void saveFile(
            MultipartFile file,
            String storedFileName
    ) {

        try {

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {

                Files.createDirectories(uploadPath);
            }

            Path filePath =
                    uploadPath.resolve(storedFileName);

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException ex) {

            throw new BusinessException(
                    "Failed to store uploaded document"
            );
        }
    }

    private User getCurrentUser() {

        String email = SecurityUtils.getCurrentUserEmail();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BusinessException(
                                "Authenticated user not found"
                        ));
    }

}