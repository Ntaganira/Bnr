package rw.bnr.heritier.dashboard.dto;

import lombok.Builder;
import lombok.Data;

/**
 * --------------------------------------------------------------------
 * Project : Bank Licensing Portal
 * File : DashboardStatsDto.java
 * Author : Heritier Ntaganira
 * Created Date : 2026-05-12
 * Description : Represents dashboard statistics
 * --------------------------------------------------------------------
 */
@Data
@Builder
public class DashboardStatsDto {

    private long totalApplications;

    private long submittedApplications;

    private long underReviewApplications;

    private long approvedApplications;

    private long rejectedApplications;
    
    private long pendingReviewApplications;

    private long pendingApprovalApplications;
}