/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : SecurityUtils.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Provides authenticated user utilities
 * --------------------------------------------------------------------
 */

package rw.bnr.heritier.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static String getCurrentUserEmail() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        return authentication.getName();
    }

}