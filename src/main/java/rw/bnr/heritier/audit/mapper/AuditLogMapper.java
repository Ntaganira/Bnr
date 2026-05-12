package rw.bnr.heritier.audit.mapper;

import org.springframework.stereotype.Component;
import rw.bnr.heritier.audit.dto.AuditLogResponseDto;
import rw.bnr.heritier.audit.model.AuditLog;

@Component
public class AuditLogMapper {

    public AuditLogResponseDto toResponse(
            AuditLog audit
    ) {

        return AuditLogResponseDto.builder()
                .actor(audit.getActorEmail())
                .action(audit.getAction())
                .previousState(audit.getPreviousState())
                .newState(audit.getNewState())
                .timestamp(audit.getTimestamp())
                .build();
    }

}