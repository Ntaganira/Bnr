package rw.bnr.heritier.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.bnr.heritier.application.dto.ApplicationResponseDto;

/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : ApplicationQueryService.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Defines application query operations
 * --------------------------------------------------------------------
 */

public interface ApplicationQueryService {

    Page<ApplicationResponseDto> getApplications(
            Pageable pageable
    );

    ApplicationResponseDto getApplicationById(
            Long id
    );

}