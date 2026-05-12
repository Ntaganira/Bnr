package rw.bnr.heritier.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.bnr.heritier.application.dto.ApplicationResponseDto;
import rw.bnr.heritier.application.mapper.ApplicationMapper;
import rw.bnr.heritier.application.model.LicenseApplication;
import rw.bnr.heritier.application.repository.LicenseApplicationRepository;
import rw.bnr.heritier.application.service.ApplicationQueryService;
import rw.bnr.heritier.common.security.SecurityUtils;
import rw.bnr.heritier.exception.BusinessException;
import rw.bnr.heritier.role.Role;
import rw.bnr.heritier.user.model.User;
import rw.bnr.heritier.user.repository.UserRepository;
/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : ApplicationQueryServiceImpl.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Handles application query operations
 * --------------------------------------------------------------------
 */
@Service
@RequiredArgsConstructor
public class ApplicationQueryServiceImpl
        implements ApplicationQueryService {

    private final LicenseApplicationRepository repository;
    private final ApplicationMapper mapper;
    private final UserRepository userRepository;

    @Override
    public Page<ApplicationResponseDto> getApplications(
            Pageable pageable
    ) {

        User currentUser = getCurrentUser();

        if (currentUser.getRole() == Role.APPLICANT) {

            return repository
                    .findByApplicantEmail(
                            currentUser.getEmail(),
                            pageable
                    )
                    .map(mapper::toResponse);
        }

        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    @Override
    public ApplicationResponseDto getApplicationById(
            Long id
    ) {

        LicenseApplication application =
                repository.findById(id)
                        .orElseThrow(() ->
                                new BusinessException(
                                        "Application not found"
                                ));

        User currentUser = getCurrentUser();

        if (currentUser.getRole() == Role.APPLICANT
                && !application.getApplicant()
                .getEmail()
                .equals(currentUser.getEmail())) {

            throw new BusinessException(
                    "You are not allowed to access this application"
            );
        }

        return mapper.toResponse(application);
    }

    private User getCurrentUser() {

        String email = SecurityUtils.getCurrentUserEmail();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BusinessException(
                                "Authenticated user not found"
                        ));
    }

}