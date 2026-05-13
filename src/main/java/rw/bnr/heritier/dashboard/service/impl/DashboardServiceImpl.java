package rw.bnr.heritier.dashboard.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.bnr.heritier.application.repository.LicenseApplicationRepository;
import rw.bnr.heritier.application.workflow.ApplicationStatus;
import rw.bnr.heritier.dashboard.dto.DashboardStatsDto;
import rw.bnr.heritier.dashboard.service.DashboardService;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
                implements DashboardService {

        private final LicenseApplicationRepository repository;

        @Override
        public DashboardStatsDto getStatistics() {

                return DashboardStatsDto.builder()

                                .totalApplications(
                                                repository.count())

                                .submittedApplications(
                                                repository.countByStatus(
                                                                ApplicationStatus.SUBMITTED))

                                .underReviewApplications(
                                                repository.countByStatus(
                                                                ApplicationStatus.UNDER_REVIEW))

                                .approvedApplications(
                                                repository.countByStatus(
                                                                ApplicationStatus.APPROVED))

                                .rejectedApplications(
                                                repository.countByStatus(
                                                                ApplicationStatus.REJECTED))

                                .pendingReviewApplications(
                                                repository.countByStatus(
                                                                ApplicationStatus.SUBMITTED))

                                .pendingApprovalApplications(
                                                repository.countByStatus(
                                                                ApplicationStatus.UNDER_REVIEW))

                                .build();
        }

}