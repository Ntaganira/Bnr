package rw.bnr.heritier.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.bnr.heritier.application.dto.ApplicationResponseDto;
import rw.bnr.heritier.application.service.ApplicationQueryService;
import rw.bnr.heritier.document.service.DocumentService;

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
                return "applications/details";
        }

}