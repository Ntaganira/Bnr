package rw.bnr.heritier.document.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rw.bnr.heritier.document.service.DocumentService;
import rw.bnr.heritier.exception.BusinessException;

@Controller
@RequiredArgsConstructor
public class DocumentViewController {

    private final DocumentService documentService;

    @PostMapping("/applications/{id}/documents")
    public String uploadDocument(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file,
            Authentication authentication,
            RedirectAttributes redirectAttributes
    ) {

        try {

            documentService.uploadDocument(
                    id,
                    file,
                    authentication.getName()
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Document uploaded successfully."
            );

        } catch (BusinessException ex) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    ex.getMessage()
            );
        }

        return "redirect:/applications/" + id;
    }

}