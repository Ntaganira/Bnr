/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : DocumentService.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Defines document upload operations
 * --------------------------------------------------------------------
 */

package rw.bnr.heritier.document.service;

import org.springframework.web.multipart.MultipartFile;
import rw.bnr.heritier.document.model.ApplicationDocument;

public interface DocumentService {

    ApplicationDocument upload(
            Long applicationId,
            MultipartFile file
    );

    void uploadDocument(
            Long applicationId,
            MultipartFile file,
            String uploaderEmail
    );
}