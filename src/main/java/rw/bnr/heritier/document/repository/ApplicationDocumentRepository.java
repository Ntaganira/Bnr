package rw.bnr.heritier.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.bnr.heritier.document.model.ApplicationDocument;

import java.util.List;
import java.util.Optional;

/**
 * --------------------------------------------------------------------
 * Project : Bank Licensing Portal
 * File : ApplicationDocumentRepository.java
 * Author : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description : Repository for application document persistence
 * --------------------------------------------------------------------
 */
public interface ApplicationDocumentRepository
                extends JpaRepository<ApplicationDocument, Long> {

        Optional<ApplicationDocument> findTopByApplicationIdAndOriginalFileNameOrderByVersionDesc(
                        Long applicationId,
                        String originalFileName);

        List<ApplicationDocument> findByApplicationId(
                        Long applicationId);

        int countByApplicationId(Long applicationId);

        Optional<ApplicationDocument> findById(
                        Long id);

}