package rw.bnr.heritier.exception.dto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : ErrorResponse.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Standard API error response payload
 * --------------------------------------------------------------------
 */
@Data
@Builder
public class ErrorResponse {

    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String message;

    private String path;

}