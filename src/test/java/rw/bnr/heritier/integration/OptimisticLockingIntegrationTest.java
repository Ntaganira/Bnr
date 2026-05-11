package rw.bnr.heritier.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import rw.bnr.heritier.application.model.LicenseApplication;
import rw.bnr.heritier.application.repository.LicenseApplicationRepository;
import rw.bnr.heritier.application.workflow.ApplicationStatus;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : OptimisticLockingIntegrationTest.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Tests optimistic locking and concurrent updates
 * --------------------------------------------------------------------
 */

@SpringBootTest
class OptimisticLockingIntegrationTest {

        @Autowired
        private LicenseApplicationRepository repository;

        @Test
        void shouldPreventConcurrentUpdates() {

                LicenseApplication firstCopy = repository.findById(1L)
                                .orElseThrow();

                LicenseApplication secondCopy = repository.findById(1L)
                                .orElseThrow();

                firstCopy.setStatus(
                                ApplicationStatus.UNDER_REVIEW);

                repository.saveAndFlush(firstCopy);

                secondCopy.setStatus(
                                ApplicationStatus.APPROVED);

                assertThrows(
                                ObjectOptimisticLockingFailureException.class,
                                () -> repository.saveAndFlush(secondCopy));
        }
}