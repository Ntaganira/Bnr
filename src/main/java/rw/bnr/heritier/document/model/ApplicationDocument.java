package rw.bnr.heritier.document.model;

import jakarta.persistence.*;
import lombok.*;
import rw.bnr.heritier.application.model.LicenseApplication;
import rw.bnr.heritier.user.model.User;

import java.time.LocalDateTime;

/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : ApplicationDocument.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Represents uploaded application documents
 * --------------------------------------------------------------------
 */

@Entity
@Table(name = "application_documents")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalFileName;

    @Column(nullable = false)
    private String storedFileName;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false)
    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private LicenseApplication application;

}