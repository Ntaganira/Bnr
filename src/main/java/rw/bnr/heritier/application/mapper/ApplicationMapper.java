package rw.bnr.heritier.application.mapper;

import org.springframework.stereotype.Component;
import rw.bnr.heritier.application.dto.ApplicationResponseDto;
import rw.bnr.heritier.application.model.LicenseApplication;

/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : ApplicationMapper.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Maps application entities to DTOs
 * --------------------------------------------------------------------
 */

@Component
public class ApplicationMapper {

    public ApplicationResponseDto toResponse(
            LicenseApplication application
    ) {

        return ApplicationResponseDto.builder()
                .id(application.getId())
                .institutionName(
                        application.getInstitutionName()
                )
                .applicationNumber(
                        application.getApplicationNumber()
                )
                .status(
                        application.getStatus().name()
                )
                .applicantEmail(
                        application.getApplicant() != null
                                ? application.getApplicant().getEmail()
                                : null
                )
                .reviewerEmail(
                        application.getReviewer() != null
                                ? application.getReviewer().getEmail()
                                : null
                )
                .approverEmail(
                        application.getApprover() != null
                                ? application.getApprover().getEmail()
                                : null
                )
                .submittedAt(
                        application.getSubmittedAt()
                )
                .build();
    }

}