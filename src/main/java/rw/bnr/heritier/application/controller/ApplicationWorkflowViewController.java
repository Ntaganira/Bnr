package rw.bnr.heritier.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rw.bnr.heritier.application.service.ApplicationWorkflowService;
import rw.bnr.heritier.exception.BusinessException;
import rw.bnr.heritier.user.model.User;
import rw.bnr.heritier.user.repository.UserRepository;

/**
 * --------------------------------------------------------------------
 * Project : Bank Licensing Portal
 * File : ApplicationWorkflowViewController.java
 * Author : Heritier Ntaganira
 * Created Date : 2026-05-12
 * Description : Handles workflow actions from Thymeleaf views
 * --------------------------------------------------------------------
 */
@Controller
@RequiredArgsConstructor
public class ApplicationWorkflowViewController {

        private final ApplicationWorkflowService workflowService;

        private final UserRepository userRepository;

        @PostMapping("/applications/{id}/review")
        public String reviewApplication(
                        @PathVariable Long id,
                        Authentication authentication,
                        RedirectAttributes redirectAttributes) {

                try {

                        User reviewer = getCurrentUser(authentication);

                        workflowService.startReview(
                                        id,
                                        reviewer);

                        redirectAttributes.addFlashAttribute(
                                        "success",
                                        "Application moved to review successfully.");

                } catch (BusinessException ex) {

                        redirectAttributes.addFlashAttribute(
                                        "error",
                                        ex.getMessage());
                }

                return "redirect:/applications/" + id;
        }

        @PostMapping("/applications/{id}/approve")
        public String approveApplication(
                        @PathVariable Long id,
                        Authentication authentication,
                        RedirectAttributes redirectAttributes) {

                try {

                        User approver = getCurrentUser(authentication);

                        workflowService.approve(
                                        id,
                                        approver);

                        redirectAttributes.addFlashAttribute(
                                        "success",
                                        "Application approved successfully.");

                } catch (BusinessException ex) {

                        redirectAttributes.addFlashAttribute(
                                        "error",
                                        ex.getMessage());
                }

                return "redirect:/applications/" + id;
        }

        @PostMapping("/applications/{id}/reject")
        public String rejectApplication(
                        @PathVariable Long id,
                        Authentication authentication,
                        RedirectAttributes redirectAttributes) {

                try {

                        User approver = getCurrentUser(authentication);

                        workflowService.reject(
                                        id,
                                        approver);

                        redirectAttributes.addFlashAttribute(
                                        "success",
                                        "Application rejected successfully.");

                } catch (BusinessException ex) {

                        redirectAttributes.addFlashAttribute(
                                        "error",
                                        ex.getMessage());
                }

                return "redirect:/applications/" + id;
        }

        private User getCurrentUser(
                        Authentication authentication) {

                return userRepository.findByEmail(
                                authentication.getName()).orElseThrow(
                                                () -> new BusinessException(
                                                                "Authenticated user not found"));
        }

        @PostMapping("/applications/{id}/complete-review")
        public String completeReview(

                        @PathVariable Long id,

                        Authentication authentication,

                        RedirectAttributes redirectAttributes) {

                try {

                        User reviewer = getCurrentUser(authentication);

                        workflowService.completeReview(
                                        id,
                                        reviewer);

                        redirectAttributes.addFlashAttribute(
                                        "success",
                                        "Review completed successfully.");

                } catch (BusinessException ex) {

                        redirectAttributes.addFlashAttribute(
                                        "error",
                                        ex.getMessage());
                }

                return "redirect:/applications/" + id;
        }

        @PostMapping("/applications/{id}/submit")
        public String submitApplication(

                        @PathVariable Long id,

                        Authentication authentication,

                        RedirectAttributes redirectAttributes) {

                try {

                        User applicant = getCurrentUser(authentication);

                        workflowService.submit(
                                        id,
                                        applicant);

                        redirectAttributes.addFlashAttribute(
                                        "success",
                                        "Application submitted successfully.");

                } catch (BusinessException ex) {

                        redirectAttributes.addFlashAttribute(
                                        "error",
                                        ex.getMessage());
                }

                return "redirect:/applications/" + id;
        }
}