package rw.bnr.heritier.audit.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.bnr.heritier.audit.model.AuditLog;

/**
 * --------------------------------------------------------------------
 * Project : Bank Licensing Portal
 * File : AuditLogRepository.java
 * Author : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description : Repository for audit trail persistence
 * --------------------------------------------------------------------
 */
public interface AuditLogRepository
                extends JpaRepository<AuditLog, Long> {
        List<AuditLog> findByApplicationIdOrderByTimestampDesc(
                        Long applicationId);

        Page<AuditLog> findAllByOrderByTimestampDesc(
                        Pageable pageable);
}