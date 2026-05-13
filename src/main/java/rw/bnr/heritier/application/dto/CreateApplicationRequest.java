/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : CreateApplicationRequest.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-13
 * Description  : Represents application creation request
 * --------------------------------------------------------------------
 */

package rw.bnr.heritier.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateApplicationRequest {

    @NotBlank(message = "Institution name is required")
    private String institutionName;

    @NotBlank(message = "Institution type is required")
    private String institutionType;

    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @NotBlank(message = "Business description is required")
    private String businessDescription;

}