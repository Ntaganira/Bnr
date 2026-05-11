package rw.bnr.heritier.document.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rw.bnr.heritier.document.model.ApplicationDocument;
import rw.bnr.heritier.document.service.DocumentService;
/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : DocumentController.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Handles document upload endpoints
 * --------------------------------------------------------------------
 */
@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping(
            value = "/upload/{applicationId}",
            consumes = "multipart/form-data"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('APPLICANT','REVIEWER')")
    public ApplicationDocument upload(
            @PathVariable Long applicationId,
            @RequestParam("file") MultipartFile file
    ) {

        return documentService.upload(
                applicationId,
                file
        );
    }

}