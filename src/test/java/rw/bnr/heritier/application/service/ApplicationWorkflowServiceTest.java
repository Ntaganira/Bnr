/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : ApplicationWorkflowServiceTest.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Tests workflow state transitions and validation
 * --------------------------------------------------------------------
 */

package rw.bnr.heritier.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rw.bnr.heritier.application.model.LicenseApplication;
import rw.bnr.heritier.application.repository.LicenseApplicationRepository;
import rw.bnr.heritier.application.service.impl.ApplicationWorkflowServiceImpl;
import rw.bnr.heritier.application.workflow.ApplicationStatus;
import rw.bnr.heritier.audit.service.AuditLogService;
import rw.bnr.heritier.exception.BusinessException;
import rw.bnr.heritier.role.Role;
import rw.bnr.heritier.user.model.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ApplicationWorkflowServiceTest {

        @Mock
        private LicenseApplicationRepository repository;

        @Mock
        private AuditLogService auditLogService;

        @InjectMocks
        private ApplicationWorkflowServiceImpl workflowService;

        private LicenseApplication application;

        private User reviewer;

        private User approver;

        private User applicant;

        @BeforeEach
        void setUp() {

                MockitoAnnotations.openMocks(this);

                reviewer = User.builder()
                                .id(1L)
                                .email("reviewer@nbr.rw")
                                .role(Role.REVIEWER)
                                .build();

                approver = User.builder()
                                .id(2L)
                                .email("approver@nbr.rw")
                                .role(Role.APPROVER)
                                .build();

                application = LicenseApplication.builder()
                                .id(1L)
                                .institutionName("Kigali Bank")
                                .applicationNumber("APP-001")
                                .status(ApplicationStatus.DRAFT)
                                .applicant(applicant)
                                .build();

                applicant = User.builder()
                                .id(3L)
                                .email("applicant@nbr.rw")
                                .role(Role.APPLICANT)
                                .build();
        }

        @Test
        void shouldSubmitApplicationSuccessfully() {

                application.setApplicant(applicant);

                when(repository.findById(1L))
                                .thenReturn(Optional.of(application));

                when(repository.save(any(LicenseApplication.class)))
                                .thenAnswer(invocation -> invocation.getArgument(0));

                LicenseApplication result = workflowService.submit(1L);

                assertEquals(
                                ApplicationStatus.SUBMITTED,
                                result.getStatus());
        }

        @Test
        void shouldRejectInvalidTransition() {

                application.setStatus(ApplicationStatus.APPROVED);

                when(repository.findById(1L))
                                .thenReturn(Optional.of(application));

                assertThrows(
                                BusinessException.class,
                                () -> workflowService.startReview(
                                                1L,
                                                reviewer));
        }

        @Test
        void reviewerCannotApproveSameApplication() {

                application.setStatus(
                                ApplicationStatus.REVIEW_COMPLETED);

                application.setReviewer(reviewer);

                User sameReviewerAsApprover = User.builder()
                                .id(1L)
                                .email("reviewer@nbr.rw")
                                .role(Role.APPROVER)
                                .build();

                when(repository.findById(1L))
                                .thenReturn(Optional.of(application));

                assertThrows(
                                BusinessException.class,
                                () -> workflowService.approve(
                                                1L,
                                                sameReviewerAsApprover));
        }

        @Test
        void approverCanApproveReviewedApplication() {

                application.setStatus(
                                ApplicationStatus.REVIEW_COMPLETED);

                application.setReviewer(reviewer);

                when(repository.findById(1L))
                                .thenReturn(Optional.of(application));

                when(repository.save(any(LicenseApplication.class)))
                                .thenAnswer(invocation -> invocation.getArgument(0));

                LicenseApplication result = workflowService.approve(
                                1L,
                                approver);

                assertEquals(
                                ApplicationStatus.APPROVED,
                                result.getStatus());
        }

}