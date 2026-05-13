package rw.bnr.heritier.application.service;

import rw.bnr.heritier.application.dto.CreateApplicationRequest;
import rw.bnr.heritier.application.model.LicenseApplication;
import rw.bnr.heritier.user.model.User;

public interface ApplicationWorkflowService {

        LicenseApplication submit(Long id);

        LicenseApplication startReview(
                        Long id,
                        User reviewer);

        LicenseApplication requestMoreInfo(Long id);

        LicenseApplication completeReview(Long id);

        LicenseApplication approve(
                        Long id,
                        User approver);

        LicenseApplication reject(
                        Long id,
                        User approver);

        void completeReview(
                        Long applicationId,
                        User reviewer);

        Long createApplication(
                        CreateApplicationRequest request,
                        String applicantEmail);

        void submit(
                        Long applicationId,
                        User applicant);
}