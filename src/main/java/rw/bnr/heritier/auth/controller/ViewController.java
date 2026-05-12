/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : ViewController.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Handles authentication view navigation
 * --------------------------------------------------------------------
 */

package rw.bnr.heritier.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String login() {

        return "auth/login";
    }

}