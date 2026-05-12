package rw.bnr.heritier.audit.service;

import java.util.List;

import rw.bnr.heritier.audit.dto.AuditLogResponseDto;

/**
 * --------------------------------------------------------------------
 * Project : Bank Licensing Portal
 * File : AuditLogService.java
 * Author : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description : Defines audit logging operations
 * --------------------------------------------------------------------
 */
public interface AuditLogService {

    void log(
            Long applicationId,
            String actorEmail,
            String action,
            String previousState,
            String newState);

    List<AuditLogResponseDto> getApplicationAuditLogs(
            Long applicationId);

}