package rw.bnr.heritier.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import rw.bnr.heritier.application.dto.ApplicationResponseDto;
import rw.bnr.heritier.application.mapper.ApplicationMapper;
import rw.bnr.heritier.application.service.ApplicationWorkflowService;
import rw.bnr.heritier.common.security.SecurityUtils;
import rw.bnr.heritier.user.model.User;
import rw.bnr.heritier.user.repository.UserRepository;

/**
 * --------------------------------------------------------------------
 * Project : Bank Licensing Portal
 * File : ApplicationWorkflowController.java
 * Author : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description : Handles application workflow operations
 * --------------------------------------------------------------------
 */

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationWorkflowController {

        private final ApplicationWorkflowService workflowService;
        private final UserRepository userRepository;
        private final ApplicationMapper applicationMapper;

        @PostMapping("/{id}/submit")
        @PreAuthorize("hasRole('APPLICANT')")
        public ApplicationResponseDto submit(
                        @PathVariable Long id) {

                return applicationMapper.toResponse(
                                workflowService.submit(id));
        }

        @PostMapping("/{id}/review")
        @PreAuthorize("hasRole('REVIEWER')")
        public ApplicationResponseDto startReview(
                        @PathVariable Long id) {

                User reviewer = getCurrentUser();

                return applicationMapper.toResponse(
                                workflowService.startReview(id, reviewer));
        }

        @PostMapping("/{id}/approve")
        @PreAuthorize("hasRole('APPROVER')")
        public ApplicationResponseDto approve(
                        @PathVariable Long id) {

                User approver = getCurrentUser();

                return applicationMapper.toResponse(
                                workflowService.approve(id, approver));
        }

        @PostMapping("/{id}/reject")
        @PreAuthorize("hasRole('APPROVER')")
        public ApplicationResponseDto reject(
                        @PathVariable Long id) {

                User approver = getCurrentUser();

                return applicationMapper.toResponse(
                                workflowService.reject(id, approver));
        }

        private User getCurrentUser() {

                String email = SecurityUtils.getCurrentUserEmail();

                return userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException(
                                                "Authenticated user not found"));
        }

}