/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : GlobalExceptionHandler.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-13
 * Description  : Handles application-wide exceptions
 * --------------------------------------------------------------------
 */

package rw.bnr.heritier.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            MethodArgumentTypeMismatchException.class
    )
    public String handleInvalidPathVariable(
            MethodArgumentTypeMismatchException ex,
            Model model
    ) {

        model.addAttribute(
                "message",
                "Invalid resource identifier."
        );

        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(
            Exception ex,
            Model model
    ) {

        model.addAttribute(
                "message",
                "An unexpected error occurred."
        );

        return "error/500";
    }

}