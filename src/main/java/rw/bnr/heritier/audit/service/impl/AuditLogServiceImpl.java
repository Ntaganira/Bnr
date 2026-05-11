package rw.bnr.heritier.audit.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.bnr.heritier.audit.model.AuditLog;
import rw.bnr.heritier.audit.repository.AuditLogRepository;
import rw.bnr.heritier.audit.service.AuditLogService;

import java.time.LocalDateTime;
/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : AuditLogServiceImpl.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Handles immutable audit trail persistence
 * --------------------------------------------------------------------
 */
@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl
        implements AuditLogService {

    private final AuditLogRepository repository;

    @Override
    public void log(
            Long applicationId,
            String actorEmail,
            String action,
            String previousState,
            String newState
    ) {

        AuditLog auditLog = AuditLog.builder()
                .applicationId(applicationId)
                .actorEmail(actorEmail)
                .action(action)
                .previousState(previousState)
                .newState(newState)
                .timestamp(LocalDateTime.now())
                .build();

        repository.save(auditLog);
    }

}