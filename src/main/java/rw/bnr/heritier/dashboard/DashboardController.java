/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : DashboardController.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Handles dashboard page navigation
 * --------------------------------------------------------------------
 */

package rw.bnr.heritier.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {

        return "dashboard/index";
    }

}