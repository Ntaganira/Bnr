/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : AuditLogResponseDto.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-12
 * Description  : Represents audit log response data
 * --------------------------------------------------------------------
 */

package rw.bnr.heritier.audit.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuditLogResponseDto {

    private String actor;

    private String action;

    private String previousState;

    private String newState;

    private LocalDateTime timestamp;

}