package rw.bnr.heritier.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import rw.bnr.heritier.dashboard.service.DashboardService;
/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : DashboardController.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Handles dashboard page navigation
 * --------------------------------------------------------------------
 */
@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String dashboard(
            Model model) {

        model.addAttribute(
                "stats",
                dashboardService.getStatistics());

        return "dashboard/index";
    }

}