package rw.bnr.heritier.audit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rw.bnr.heritier.audit.dto.AuditLogResponseDto;
import rw.bnr.heritier.audit.service.AuditLogService;
/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : AuditLogViewController.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-13
 * Description  : Handles audit log view navigation
 * --------------------------------------------------------------------
 */
@Controller
@RequiredArgsConstructor
public class AuditLogViewController {

    private final AuditLogService auditLogService;

    @GetMapping("/audit-logs")
    public String auditLogs(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            Model model
    ) {

        Page<AuditLogResponseDto> auditLogs =
                auditLogService.getAuditLogs(
                        PageRequest.of(page, size)
                );

        model.addAttribute(
                "auditLogs",
                auditLogs
        );

        return "audit/list";
    }

}