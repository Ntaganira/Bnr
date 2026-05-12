/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : DocumentResponseDto.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-12
 * Description  : Represents uploaded document response data
 * --------------------------------------------------------------------
 */

package rw.bnr.heritier.document.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DocumentResponseDto {

    private Long id;

    private String fileName;

    private Long fileSize;

    private String contentType;

    private Integer versionNumber;

    private String uploadedBy;

    private LocalDateTime uploadedAt;

}