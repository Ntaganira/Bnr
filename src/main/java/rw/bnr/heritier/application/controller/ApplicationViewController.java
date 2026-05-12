package rw.bnr.heritier.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.bnr.heritier.application.dto.ApplicationResponseDto;
import rw.bnr.heritier.application.service.ApplicationQueryService;
import rw.bnr.heritier.audit.service.AuditLogService;
import rw.bnr.heritier.document.model.ApplicationDocument;
import rw.bnr.heritier.document.service.DocumentService;
import rw.bnr.heritier.exception.BusinessException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * --------------------------------------------------------------------
 * Project : Bank Licensing Portal
 * File : ApplicationViewController.java
 * Author : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description : Handles application management page navigation
 * --------------------------------------------------------------------
 */
@Controller
@RequiredArgsConstructor
public class ApplicationViewController {

        private final ApplicationQueryService queryService;
        private final DocumentService documentService;
        private final AuditLogService auditLogService;

        @GetMapping("/applications")
        public String applications(

                        @RequestParam(defaultValue = "0") int page,

                        @RequestParam(defaultValue = "10") int size,

                        Model model) {

                Page<ApplicationResponseDto> applications = queryService.getApplications(
                                PageRequest.of(page, size));

                model.addAttribute(
                                "applications",
                                applications);

                return "applications/list";
        }

        @GetMapping("/applications/{id}")
        public String applicationDetails(
                        @PathVariable Long id,
                        Model model) {

                ApplicationResponseDto application = queryService.getApplicationById(id);

                model.addAttribute(
                                "app",
                                application);

                model.addAttribute(
                                "documents",
                                documentService.getDocumentsByApplication(id));

                model.addAttribute(
                                "auditLogs",
                                auditLogService.getApplicationAuditLogs(id));
                return "applications/details";
        }

        @GetMapping("/documents/{id}/download")
        public ResponseEntity<Resource> downloadDocument(
                        @PathVariable Long id) {

                try {

                        ApplicationDocument document = documentService.getDocument(id);

                        Path filePath = Paths.get("uploads")
                                        .resolve(
                                                        document.getStoredFileName());

                        Resource resource = new UrlResource(filePath.toUri());

                        return ResponseEntity.ok()

                                        .header(
                                                        HttpHeaders.CONTENT_DISPOSITION,
                                                        "attachment; filename=\""
                                                                        + document.getOriginalFileName()
                                                                        + "\"")

                                        .body(resource);

                } catch (Exception ex) {

                        throw new BusinessException(
                                        "Failed to download document");
                }
        }
}