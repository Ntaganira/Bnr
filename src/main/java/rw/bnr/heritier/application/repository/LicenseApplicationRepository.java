package rw.bnr.heritier.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.bnr.heritier.application.model.LicenseApplication;

public interface LicenseApplicationRepository
        extends JpaRepository<LicenseApplication, Long> {
}