package rw.bnr.heritier.audit.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : AuditLog.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Stores immutable audit trail records
 * --------------------------------------------------------------------
 */

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long applicationId;

    @Column(nullable = false)
    private String actorEmail;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String previousState;

    @Column(nullable = false)
    private String newState;

    @Column(nullable = false)
    private LocalDateTime timestamp;

}