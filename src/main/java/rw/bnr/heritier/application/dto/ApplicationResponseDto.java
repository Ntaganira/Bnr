package rw.bnr.heritier.application.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : ApplicationResponseDto.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Represents application response payload
 * --------------------------------------------------------------------
 */
@Data
@Builder
public class ApplicationResponseDto {

    private Long id;

    private String institutionName;

    private String applicationNumber;

    private String status;

    private String applicantEmail;

    private String reviewerEmail;

    private String approverEmail;

    private LocalDateTime submittedAt;

}