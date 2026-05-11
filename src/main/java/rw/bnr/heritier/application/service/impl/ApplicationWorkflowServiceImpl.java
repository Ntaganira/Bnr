package rw.bnr.heritier.application.service.impl;

/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : ApplicationWorkflowServiceImpl.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Handles workflow state transitions
 * --------------------------------------------------------------------
 */

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.bnr.heritier.application.model.LicenseApplication;
import rw.bnr.heritier.application.repository.LicenseApplicationRepository;
import rw.bnr.heritier.application.service.ApplicationWorkflowService;
import rw.bnr.heritier.application.workflow.ApplicationStatus;
import rw.bnr.heritier.exception.BusinessException;
import rw.bnr.heritier.role.Role;
import rw.bnr.heritier.user.model.User;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationWorkflowServiceImpl
        implements ApplicationWorkflowService {

    private final LicenseApplicationRepository repository;

    @Override
    public LicenseApplication submit(Long id) {

        LicenseApplication app = getApplication(id);

        validateTransition(
                app.getStatus(),
                ApplicationStatus.SUBMITTED
        );

        app.setStatus(ApplicationStatus.SUBMITTED);

        return repository.save(app);
    }

    @Override
    public LicenseApplication startReview(
            Long id,
            User reviewer
    ) {

        LicenseApplication app = getApplication(id);

        if (reviewer.getRole() != Role.REVIEWER) {

            throw new BusinessException(
                    "Only reviewers can review applications"
            );
        }

        validateTransition(
                app.getStatus(),
                ApplicationStatus.UNDER_REVIEW
        );

        app.setReviewer(reviewer);

        app.setStatus(ApplicationStatus.UNDER_REVIEW);

        return repository.save(app);
    }

    @Override
    public LicenseApplication requestMoreInfo(Long id) {

        LicenseApplication app = getApplication(id);

        validateTransition(
                app.getStatus(),
                ApplicationStatus.NEEDS_MORE_INFO
        );

        app.setStatus(ApplicationStatus.NEEDS_MORE_INFO);

        return repository.save(app);
    }

    @Override
    public LicenseApplication completeReview(Long id) {

        LicenseApplication app = getApplication(id);

        validateTransition(
                app.getStatus(),
                ApplicationStatus.REVIEW_COMPLETED
        );

        app.setStatus(ApplicationStatus.REVIEW_COMPLETED);

        return repository.save(app);
    }

    @Override
    public LicenseApplication approve(
            Long id,
            User approver
    ) {

        LicenseApplication app = getApplication(id);

        if (approver.getRole() != Role.APPROVER) {

            throw new BusinessException(
                    "Only approvers can approve applications"
            );
        }

        if (app.getReviewer() != null
                && app.getReviewer().getId()
                .equals(approver.getId())) {

            throw new BusinessException(
                    "Reviewer cannot approve same application"
            );
        }

        validateTransition(
                app.getStatus(),
                ApplicationStatus.APPROVED
        );

        app.setApprover(approver);

        app.setStatus(ApplicationStatus.APPROVED);

        return repository.save(app);
    }

    @Override
    public LicenseApplication reject(
            Long id,
            User approver
    ) {

        LicenseApplication app = getApplication(id);

        validateTransition(
                app.getStatus(),
                ApplicationStatus.REJECTED
        );

        app.setApprover(approver);

        app.setStatus(ApplicationStatus.REJECTED);

        return repository.save(app);
    }

    private void validateTransition(
            ApplicationStatus current,
            ApplicationStatus target
    ) {

        if (current == ApplicationStatus.APPROVED
                || current == ApplicationStatus.REJECTED) {

            throw new BusinessException(
                    "Finalized applications cannot be modified"
            );
        }

        boolean valid = switch (current) {

            case DRAFT ->
                    target == ApplicationStatus.SUBMITTED;

            case SUBMITTED ->
                    target == ApplicationStatus.UNDER_REVIEW;

            case UNDER_REVIEW ->
                    target == ApplicationStatus.NEEDS_MORE_INFO
                            || target == ApplicationStatus.REVIEW_COMPLETED;

            case NEEDS_MORE_INFO ->
                    target == ApplicationStatus.RESUBMITTED;

            case RESUBMITTED ->
                    target == ApplicationStatus.UNDER_REVIEW;

            case REVIEW_COMPLETED ->
                    target == ApplicationStatus.APPROVED
                            || target == ApplicationStatus.REJECTED;

            default -> false;
        };

        if (!valid) {

            throw new BusinessException(
                    String.format(
                            "Invalid transition from %s to %s",
                            current,
                            target
                    )
            );
        }
    }

    private LicenseApplication getApplication(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(
                                "Application not found"
                        ));
    }

}