package rw.bnr.heritier.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import rw.bnr.heritier.application.model.LicenseApplication;
/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : LicenseApplicationRepository.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Repository for license application persistence
 * --------------------------------------------------------------------
 */
public interface LicenseApplicationRepository
                extends JpaRepository<LicenseApplication, Long> {

        Page<LicenseApplication> findByApplicantEmail(
                        String email,
                        Pageable pageable);
}